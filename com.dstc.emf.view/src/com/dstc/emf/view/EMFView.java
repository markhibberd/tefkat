
package com.dstc.emf.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;

public class EMFView extends ViewPart {
    
    private Canvas canvas;
    private EMFFigure viewer;
    private AbstractLayout contentsLayout;

    private Action action0, action1, action2, action3;
    
    /**
     * The constructor.
     */
    public EMFView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent) {
        canvas = new Canvas(parent, SWT.NONE);
        try {
            LightweightSystem lws = new LightweightSystem(canvas);
            viewer = new EMFFigure();
            
            Font font = new Font(parent.getDisplay(), "Arial", 6, SWT.NORMAL);
            
            contentsLayout = new RadialTreeLayout();
            viewer.setLayoutManager(contentsLayout);
            viewer.setFont(font);
            lws.setContents(viewer);
            makeActions();
            contributeToActionBars();
        } catch (Exception e) {
            e.printStackTrace();
            Label label = new Label(canvas, SWT.CENTER | SWT.WRAP);
            label.setText("Unable to create the Tefkat View.  This may be becuse you do not have the draw2d plugin installed.  The exception messages is: " + e);
        }
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        if (!canvas.isDisposed()) {
            canvas.setFocus();
        }
    }
    
    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager manager) {
//        if (null != action0) {
//            manager.add(action0);
//        }
////        if (null != action1) {
////            manager.add(action1);
////        }
//        if (null != action2) {
//            manager.add(action2);
//        }
//        if (null != action3) {
//            manager.add(action3);
//        }
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        if (null != action0) {
            manager.add(action0);
        }
        if (null != action1) {
            manager.add(action1);
        }
        if (null != action2) {
            manager.add(action2);
        }
        if (null != action3) {
            manager.add(action3);
        }
    }
    
    private boolean details = false;
    private void makeActions() {
//        action0 = new Action() {
//            public void run() {
//                Shell shell = ViewPlugin.getDefault().getWorkbench().getDisplay().getActiveShell();
//                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//                ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell,
//                        root,
//                        "Select the EMF Resource");
//                if (dialog.open() == ResourceSelectionDialog.OK) {
//                    Object[] results = dialog.getResult();
//                    if (results != null && results.length > 0 && (results[0] instanceof IResource)) {
//                        IResource resource = (IResource) results[0];
//                        URI uri = URI.createPlatformResourceURI(resource.getFullPath().toString());
//                        Resource res = getResourceSet().getResource(uri, true);
//                        if (null != res) {
//                            addResource(res);
//                            viewer.revalidate();
//                        }
//                    }
//                }
//            }
//        };
//        action0.setText("Add EMF Resource...");
//        action0.setToolTipText("Adds nodes and edges from another resource.");
        
//        action1 = new Action() {
//            public void run() {
//                if (details) {
//                    details = false;
//                    return;
//                }
//                details = true;
//                viewer.showDetails();
//                viewer.revalidate();
//            }
//        };
//        action1.setText("Show Node Details");
//        action1.setToolTipText("Displays the attributes and values of each node");
//        //action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
        
//        action2 = new Action() {
//            private boolean max = true;
//            public void run() {
//                if (max) {
//                    max = false;
//                    viewer.setMaxDepth(0);
//                    setText("Show Nodes");
//                    setToolTipText("Makes all Nodes visible");
//                } else {
//                    max = true;
//                    viewer.setMaxDepth(Integer.MAX_VALUE);
//                    setText("Hide Nodes");
//                    setToolTipText("Makes all Nodes invisible - Edges are still shown");
//                }
//                viewer.revalidate();
//            }
//        };
//        action2.setText("Hide Nodes");
//        action2.setToolTipText("Makes all Nodes invisible - Edges are still shown");
        
        action3 = new Action() {
            private boolean smallFont = true;
            public void run() {
                if (smallFont) {
                    smallFont = false;
                    Font font = new Font(canvas.getDisplay(), "Arial", 10, SWT.NORMAL);
                    viewer.setFont(font);
                    setText("Small Font");
                    setToolTipText("Make font small");
                } else {
                    smallFont = true;
                    Font font = new Font(canvas.getDisplay(), "Arial", 6, SWT.NORMAL);
                    viewer.setFont(font);
                    setText("Large Font");
                    setToolTipText("Make font large");
                }
                viewer.invalidateTree();
            }
        };
        action3.setText("Large Font");
        action3.setToolTipText("Make font large");
    };
    
    public void addResource(Resource resource) {
        viewer.addResource(resource);
    }
    
    public void removeResource(Resource resource) {
        viewer.removeResource(resource);
    }
    
    public void clear() {
        viewer.clear();
    }

    private ResourceSet _resourceSet = null;

    private ResourceSet getResourceSet() {
        if (null == _resourceSet) {
            _resourceSet = new ResourceSetImpl();
            Map map = _resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
            map.put("*", new XMIResourceFactoryImpl());
        }
        return _resourceSet;
    }

    /**
     * Ensure view is in sync with Resources
     */
    public void refresh() {
        viewer.refresh();
    }

}
