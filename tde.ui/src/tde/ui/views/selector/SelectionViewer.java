package tde.ui.views.selector;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import tde.ui.utils.Disposer;

/**
 * Naive viewer implementation for object selection model.
 */
// FIXME this is a nightmare, but i don't have time to look at for now
public class SelectionViewer extends Viewer {
    private static final String[] COLUMN_LABELS = {"name", "value"};
    private static final int EDIT_COLUMN = 1;
    private Tree tree;
    private TreeEditor treeEditor;
    private EObject input;
    private CellEditor cellEditor;
    private IStatusLineManager statusLineManager;
    private List<SelectionViewerEntry> firstLevel;

    // based on look and feel of PropertySheet
    public SelectionViewer(Composite parent) {
        tree = new Tree(parent, SWT.FULL_SELECTION | SWT.SINGLE | SWT.HIDE_SELECTION);
        tree.setLinesVisible(true);
        tree.setHeaderVisible(true);
        addColumns();
        hookControl();
        treeEditor = new TreeEditor(tree);
    }

    public Control getControl() {
        return tree;
    }

    public Object getInput() {
        return input;
    }

    public ISelection getSelection() {
        return SelectionBuilder.build(input, firstLevel);
    }

    public void setSelection(ISelection selection, boolean reveal) {}

    public void refresh() {
     // FIXME not sure if this is even needed, if it is needs to be fixed as it is brokeno
//        rebuildModel();
    }

    private void rebuildModel() {
        tree.removeAll(); // FIXME do I have to dispose of items or should this do it
        buildLevel(tree, firstLevel);
    }

    private void buildLevel(Tree tree, List<SelectionViewerEntry> level) {
        for (SelectionViewerEntry entry : level) {
            entry.createTreeItem(tree);
        }
    }

    private void buildLevel(TreeItem ti, List<SelectionViewerEntry> level) {
        for (SelectionViewerEntry entry : level) {
            entry.createTreeItem(ti);
        }
    }


    public void setInput(Object in) {
        applyEditorValue();
        deactivateCellEditor();
        if (!(in instanceof EObject)) {
            System.err.println("Only supports eobjects");
            return;
        }
        input = (EObject) in;
        buildTree();
    }

    private void buildTree() {
        tree.removeAll();
        firstLevel = SelectionViewerEntry.getChildren(this, input);
        rebuildModel();
    }

    private void applyEditorValue() {
        TreeItem treeItem = treeEditor.getItem();
        if (treeItem == null || treeItem.isDisposed()) return;
        SelectionViewerEntry entry = (SelectionViewerEntry) treeItem.getData();
        entry.apply();
    }


    private void handleTreeExpand(TreeEvent event) {
        TreeItem ti = (TreeItem) event.item;
        TreeItem[] children = ti.getItems();
        if (children.length != 1) return;  // already populated
        if (children[0].getData() != null) return;  // already populated
        children[0].dispose();
        SelectionViewerEntry entry = (SelectionViewerEntry) ti.getData();
        buildLevel(ti, entry.getChildren());
    }

    private void handleTreeCollapse(TreeEvent event) {
        deactivateCell();
    }

    private void handleSelect(TreeItem selection) {
        deactivateCell();
        SelectionViewerEntry activeEntry = (SelectionViewerEntry) selection.getData();
        setMessage(activeEntry.getDescription());
        activateCellEditor(selection);
    }

    public void select() {
        SelectionChangedEvent changeEvent = new SelectionChangedEvent(this, SelectionBuilder.build(input, firstLevel));
        fireSelectionChanged(changeEvent);
    }

    private void activateCellEditor(TreeItem item) {
        tree.showSelection();
        SelectionViewerEntry activeEntry = (SelectionViewerEntry) item.getData();
        cellEditor = activeEntry.getEditor(tree);
        if (cellEditor == null) return;
        cellEditor.activate();
        Control control = cellEditor.getControl();
        if (control == null) {
            cellEditor.deactivate();
            cellEditor = null;
            return;
        }
        CellEditor.LayoutData layout = cellEditor.getLayoutData();
        treeEditor.horizontalAlignment = layout.horizontalAlignment;
        treeEditor.grabHorizontal = layout.grabHorizontal;
        treeEditor.minimumWidth = layout.minimumWidth;
        treeEditor.setEditor(control, item, EDIT_COLUMN);
        setError(cellEditor.getErrorMessage());
        cellEditor.setFocus();
    }


    // based upon a PropertySheet look and feel
    private void addColumns() {
        for (int i = 0; i < COLUMN_LABELS.length; i++) {
            TreeColumn column = new TreeColumn(tree, SWT.NONE);
            column.setText(COLUMN_LABELS[i]);
        }

        tree.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                Rectangle area = tree.getClientArea();
                TreeColumn[] columns = tree.getColumns();
                if (area.width > 0) {
                    columns[0].setWidth(area.width * 40 / 100);
                    columns[1].setWidth(area.width - columns[0].getWidth() - 4);
                    tree.removeControlListener(this);
                }
            }
        });
    }

    private void deactivateCell() {
        if (cellEditor != null) {
            applyEditorValue();
            deactivateCellEditor();
        }
    }

    public void deactivateCellEditor() {
        treeEditor.setEditor(null, null, EDIT_COLUMN);
        if (cellEditor != null) {
            cellEditor.deactivate();
            cellEditor = null;
        }
    }
    public void reload() {
        setInput(getInput());
    }


    public void clear() {
        applyEditorValue();
        deactivateCellEditor();
        for (SelectionViewerEntry child : firstLevel) Disposer.dispose(child);
        tree.removeAll();
        input = null;
        firstLevel = null;
    }

    /* status bar **************************************/

    public void setStatusLineManager(IStatusLineManager manager) {
        statusLineManager = manager;
    }

    protected void updateStatusLine(Widget item) {
        setMessage(null);
        setError(null);
        if (item == null) return;
        SelectionViewerEntry entry = (SelectionViewerEntry) item.getData();
        setMessage(entry.getDescription());
    }

    public void setError(String msg) {
        statusLineManager.setErrorMessage(msg);
    }

    public void setMessage(String msg) {
        statusLineManager.setMessage(msg);
    }


    /*
     * based upon a PropertySheet hookup
     */
    private void hookControl() {
        tree.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (cellEditor == null || !cellEditor.isActivated()) updateStatusLine(e.item);
            }
            public void widgetDefaultSelected(SelectionEvent e) { handleSelect((TreeItem) e.item); }
        });

        tree.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent event) {
                Point pt = new Point(event.x, event.y);
                TreeItem item = tree.getItem(pt);
                if (item != null) handleSelect(item);
            }
        });

        tree.addTreeListener(new TreeListener() {
            public void treeExpanded(TreeEvent event) { handleTreeExpand(event); }
            public void treeCollapsed(TreeEvent event) { handleTreeCollapse(event); }
        });


        tree.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.character == SWT.ESC) {
                    deactivateCellEditor();
                } else if (e.keyCode == SWT.F5) {
                    reload();
                }
            }
        });
    }
}
