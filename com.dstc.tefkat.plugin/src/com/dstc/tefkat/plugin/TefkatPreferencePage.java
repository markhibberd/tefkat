/*
 * Copyright (c) 2005- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 * 
 */

package com.dstc.tefkat.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author lawley
 *
 */
public class TefkatPreferencePage
    extends PreferencePage
    implements IWorkbenchPreferencePage {

    private final static String ENABLED_COLUMN = "enabled";
    private final static String SOURCE_COLUMN  = "source";
    private final static String TARGET_COLUMN  = "target";

    // Set column names
    private final static String[] columnNames = new String[] { 
                    ENABLED_COLUMN, 
                    SOURCE_COLUMN,
                    TARGET_COLUMN
                    };
    
    private Table table;
    private TableViewer tableViewer;
    private URIMapList mapList = new URIMapList();
    private IPropertyChangeListener preferenceListener = new IPropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getProperty().equals(TefkatPlugin.URIMAP_PREFERENCE)) {
                if (null != event.getOldValue()) {
                    List list = TefkatPlugin.convertFromString((String) event.getOldValue());
                    for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
                        URIMap map = (URIMap) itr.next();
                        if (map.enabled) {
                            URI uri = URI.createURI(map.source);
                            URIConverter.URI_MAP.remove(uri);
                        }
                    }
                }
                if (null != event.getNewValue()) {
                    List list = TefkatPlugin.convertFromString((String) event.getNewValue());
                    for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
                        URIMap map = (URIMap) itr.next();
                        if (map.enabled) {
                            URI sourceUri = URI.createURI(map.source);
                            URI targetUri = URI.createURI(map.target);
                            URIConverter.URI_MAP.put(sourceUri, targetUri);
                        }
                    }
                }
            }
        }
    };

    protected Control createContents(Composite parent) {
        noDefaultAndApplyButton();
        
        setDescription("Enabled entries in the mapping table below will be used to convert Resource URIs into actual URI locations (eg URLs)");
//        setMessage("Enabled entries in the mapping table below will be used to convert Resource URIs into actual URI locations (eg URLs)");
        
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
        parent.setLayoutData(gridData);

        // Set numColumns to 2 for the buttons 
        GridLayout layout = new GridLayout(2, false);
        layout.marginWidth = 4;
        parent.setLayout(layout);

        // Create the table 
        createTable(parent);
        
        // Create and setup the TableViewer
        createTableViewer();
        tableViewer.setContentProvider(new MapListContentProvider());
        tableViewer.setLabelProvider(new MapListLabelProvider());
        mapList.getMaps().addAll(TefkatPlugin.convertFromString(getPreferenceStore().getString(TefkatPlugin.URIMAP_PREFERENCE)));
        tableViewer.setInput(mapList);
        
        createButtons(parent);
        
        return null;
    }

    private void createTable(Composite parent) {
        int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
                    SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

//        final int NUMBER_COLUMNS = 3;

        table = new Table(parent, style);
        
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalSpan = 2;
        table.setLayoutData(gridData);
                                
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        
        // 1st column with checkboxes - NOTE: The SWT.CENTER has no effect!!
        TableColumn column = new TableColumn(table, SWT.CENTER, 0);     

        column.setText("!");
        column.setWidth(20);
        
        // 2nd column with URI Source
        column = new TableColumn(table, SWT.LEFT, 1);
        column.setText("URI Source");
        column.setWidth(200);

        // 3rd column with URI Target
        column = new TableColumn(table, SWT.LEFT, 2);
        column.setText("URI Target");
        column.setWidth(200);
    }

    private void createTableViewer() {
        tableViewer = new TableViewer(table);
        tableViewer.setUseHashlookup(true);
        
        tableViewer.setColumnProperties(columnNames);

        // Create the cell editors
        CellEditor[] editors = new CellEditor[columnNames.length];

        // Column 1 : Enabled (Checkbox)
        editors[0] = new CheckboxCellEditor(table);

        // Column 2 : Source (URI)
        editors[1] = new TextCellEditor(table);
        ((Text) editors[1].getControl()).addVerifyListener(
                new VerifyListener() {
                    public void verifyText(VerifyEvent e) {
                        // These are the valid chars in a URI per RFC 2396
                        e.doit = e.text.matches("[-#:;?@&=+$,/._~*'()%0-9a-zA-Z]*");
                    }
                });

        // Column 3 : Target (URI) 
        editors[2] = new TextCellEditor(table);
        ((Text) editors[2].getControl()).addVerifyListener(
                new VerifyListener() {
                    public void verifyText(VerifyEvent e) {
                        // These are the valid chars in a URI per RFC 2396
                        e.doit = e.text.matches("[-#:;?@&=+$,/._~*'()%0-9a-zA-Z]*");
                    }
                });

//      Assign the cell editors to the viewer 
        tableViewer.setCellEditors(editors);
        // Set the cell modifier for the viewer
        tableViewer.setCellModifier(new TefkatCellModifier());
    }
    
    private void createButtons(Composite parent) {
        // Create and configure the "Add" button
        Button add = new Button(parent, SWT.PUSH | SWT.CENTER);
        add.setText("Add");
        
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        gridData.widthHint = 80;
        add.setLayoutData(gridData);
        add.addSelectionListener(new SelectionAdapter() {
            // Add a URIMap to the URIMapList and refresh the view
            public void widgetSelected(SelectionEvent e) {
                mapList.addMap();
            }
        });

        //      Create and configure the "Delete" button
        Button delete = new Button(parent, SWT.PUSH | SWT.CENTER);
        delete.setText("Delete");
        gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        gridData.widthHint = 80; 
        delete.setLayoutData(gridData); 

        delete.addSelectionListener(new SelectionAdapter() {
            //      Remove the selection and refresh the view
            public void widgetSelected(SelectionEvent e) {
                URIMap map = (URIMap) ((IStructuredSelection) 
                        tableViewer.getSelection()).getFirstElement();
                if (map != null) {
                    mapList.removeMap(map);
                }                               
            }
        });
    }
    
    public void init(IWorkbench workbench) {
        setPreferenceStore(TefkatPlugin.getPlugin().getPreferenceStore());
        getPreferenceStore().addPropertyChangeListener(preferenceListener);
    }
    
    public void dispose() {
        getPreferenceStore().removePropertyChangeListener(preferenceListener);
        super.dispose();
    }

    public boolean performOk() {
        boolean result = super.performOk();
        if (result) {
            String value = TefkatPlugin.convertToString(mapList.getMaps());
            getPreferenceStore().setValue(TefkatPlugin.URIMAP_PREFERENCE, value);
        }
        return result;
    }
    
    static class URIMap {
        boolean enabled = true;
        String source = "";
        String target = "";
    }
    
    class TefkatCellModifier implements ICellModifier {

        public boolean canModify(Object element, String property) {
            return true;
        }

        public Object getValue(Object element, String property) {
            int columnIndex = Arrays.asList(columnNames).indexOf(property);
            Object result = null;
            
            URIMap map = (URIMap) element;

            switch (columnIndex) {
            case 0 : // ENABLED_COLUMN 
                result = Boolean.valueOf(map.enabled);
                break;
            case 1 : // SOURCE_COLUMN
                result = map.source;
                break;
            case 2 : // TARGET_COLUMN
                result = map.target;
                break;
            default :
                result = "";
            }
            
            return result;
        }

        public void modify(Object element, String property, Object value) {
            if (element instanceof Item) {
                element = ((Item) element).getData();
            }
            int columnIndex = Arrays.asList(columnNames).indexOf(property);
            URIMap map = (URIMap) element;
            
            switch (columnIndex) {
            case 0 : // ENABLED_COLUMN
                map.enabled = ((Boolean) value).booleanValue();
                break;
            case 1 : // SOURCE_COLUMN
                map.source = ((String) value).trim();
                break;
            case 2 : // TARGET_COLUMN
                map.target = ((String) value).trim();
                break;
            default :
            }
            
            mapList.mapChanged(map);
        }
    }

    class URIMapList {
        
        private List maps = new ArrayList();
        private Set changeListeners = new HashSet();
        
        public URIMapList() {
//            add("http:///", "platform:/");
        }
        
        public void addChangeListener(IMapListViewer viewer) {
            changeListeners.add(viewer);
        }
        
        public void removeChangeListener(IMapListViewer viewer) {
            changeListeners.remove(viewer);
        }
        
        public List getMaps() {
            return maps;
        }

        public void addMap() {
            URIMap map = new URIMap();
            maps.add(map);
            for (final Iterator itr = changeListeners.iterator(); itr.hasNext(); ) {
                ((IMapListViewer) itr.next()).addMap(map);
            }
        }

        public void removeMap(URIMap map) {
            for (final Iterator itr = changeListeners.iterator(); itr.hasNext(); ) {
                ((IMapListViewer) itr.next()).removeMap(map);
            }
        }

        public void mapChanged(URIMap map) {
            for (final Iterator itr = changeListeners.iterator(); itr.hasNext(); ) {
                ((IMapListViewer) itr.next()).updateMap(map);
            }
        }
    }
    
    interface IMapListViewer {
        public void addMap(URIMap map);
        public void removeMap(URIMap map);
        public void updateMap(URIMap map);
    }

    class MapListContentProvider
        implements IStructuredContentProvider, IMapListViewer {

        public Object[] getElements(Object inputElement) {
            return mapList.getMaps().toArray();
        }

        public void dispose() {
            mapList.removeChangeListener(this);
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            if (null != oldInput) {
                ((URIMapList) oldInput).removeChangeListener(this);
            }
            if (null != newInput) {
                ((URIMapList) newInput).addChangeListener(this);
            }
        }

        public void addMap(URIMap map) {
            tableViewer.add(map);
        }

        public void removeMap(URIMap map) {
            tableViewer.remove(map);
        }

        public void updateMap(URIMap map) {
            tableViewer.update(map, null);
        }
        
    }
    
    static class MapListLabelProvider
        extends LabelProvider
        implements ITableLabelProvider {
        
        private static final String CHECKED_IMAGE   = "checked";
        private static final String UNCHECKED_IMAGE = "unchecked";

        private static final ImageRegistry imageRegistry = new ImageRegistry();
        
        static {
            String iconPath = "icons/"; 
            imageRegistry.put(CHECKED_IMAGE, ImageDescriptor.createFromFile(
                    TefkatPlugin.class, 
                    iconPath + CHECKED_IMAGE + ".gif"
                )
            );
            imageRegistry.put(UNCHECKED_IMAGE, ImageDescriptor.createFromFile(
                    TefkatPlugin.class, 
                    iconPath + UNCHECKED_IMAGE + ".gif"
                )
            );
        }
        
        public Image getColumnImage(Object element, int columnIndex) {
            return columnIndex == 0 ? getImage(((URIMap) element).enabled) : null;
        }

        private Image getImage(boolean b) {
            return b ? imageRegistry.get(CHECKED_IMAGE) : imageRegistry.get(UNCHECKED_IMAGE);
        }

        public String getColumnText(Object element, int columnIndex) {
            String result = "";
            
            URIMap map = (URIMap) element;

            switch (columnIndex) {
            case 1 : // SOURCE_COLUMN
                result = map.source;
                break;
            case 2 : // TARGET_COLUMN
                result = map.target;
                break;
            default :
            }
            
            return result;
        }
        
    }
    
}
