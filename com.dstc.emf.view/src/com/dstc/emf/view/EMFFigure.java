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
 */

package com.dstc.emf.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * @author lawley
 *
 */
public class EMFFigure extends Figure {
    
    private final static boolean DEBUG = false;

    public final static FigureConfigurationAction DEFAULT_NODE_CONFIG = new FigureConfigurationAction() {
        
    	final private Dimension D = new Dimension(8, 8);
        private Color classColor = new Color(null,255,206,255);

        public void configure(IFigure figure) {
        	figure.setMinimumSize(D);
        	figure.setSize(D);
            figure.setBorder(new LineBorder(ColorConstants.black,1));
            figure.setBackgroundColor(classColor);
            figure.setOpaque(true);
        }
    };

    private boolean includeRefs = false;
    private int maxdepth = Integer.MAX_VALUE;

    public EMFFigure() {
        super();
    }
    
    public void removeNotify() {
        // We're going away -> remove our listener
        for (Iterator itr = resNodeConfig.keySet().iterator(); itr.hasNext(); ) {
            Resource res = (Resource) itr.next();
            res.eAdapters().remove(resourceListener);
        }
        super.removeNotify();
    }
    
    private Map nodeFigMap = new HashMap();
    private Map edgeFigMap = new HashMap();
    private Map resNodeConfig = new HashMap();
    
    private Adapter resourceListener = new Adapter() {
        public void notifyChanged(Notification notification) {
            int eventType = notification.getEventType();
            if (Notification.ADD == eventType) {
                Object newValue = notification.getNewValue();
                if (newValue instanceof EObject) {
                    List l = new ArrayList(1);
                    l.add(newValue);
                    addAll(l);
                }
            } else if (Notification.REMOVE == eventType) {
                Object oldValue = notification.getOldValue();
                if (oldValue instanceof EObject) {
                    // REMOVE may be because object is now owned by another
                    // rather than directly by the resource
                    List l = new ArrayList(1);
                    l.add(oldValue);
                    updateAll(l);
                }
            } else if (Notification.SET == eventType) {
                refresh();
            }
        }

        public void setTarget(Notifier newTarget) {
        }

        public boolean isAdapterForType(Object type) {
            return false;
        }

        public Notifier getTarget() {
            return null;
        }
    };
    
    private int resourceCount = 0;
    private final List configActions = new ArrayList();
    
    private Color theColor = new Color(null, 255, 127, 255);
    
    private LayoutManager radialLayout = null;
    
    public void addResource(Resource res) {
        resourceCount++;
        resNodeConfig.put(res, getConfigAction());
        addAll(res.getContents());
        res.eAdapters().add(resourceListener);
//        res.setTrackingModification(true);
//        System.out.println(res + ": " + res.isTrackingModification());
    }
    
    public void removeResource(final Resource res) {
        resourceCount--;
        resNodeConfig.remove(res);
        
        res.eAdapters().remove(resourceListener);
        removeAll(res.getContents());
    }
    
    private FigureConfigurationAction getConfigAction() {
        FigureConfigurationAction action;
        
        if (resourceCount > configActions.size()) {
            final Color resColor = theColor;
            theColor = new Color(null, 255 & (theColor.getRed() - 32), 255 & (theColor.getGreen() + 32), 255 - theColor.getRed());
            
            action = new FigureConfigurationAction() {
                public void configure(IFigure figure) {
                    DEFAULT_NODE_CONFIG.configure(figure);
                    figure.setBackgroundColor(resColor);
                }
            };
            configActions.add(action);
        } else {
            action = (FigureConfigurationAction) configActions.get(resourceCount - 1);
        }
        
        return action;
    }

    public void clear() {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                removeAll();
                nodeFigMap.clear();
                edgeFigMap.clear();
                resourceCount = 0;
                theColor = new Color(null, 255, 127, 255);
            }
        });
    }
    
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager instanceof RadialTreeLayout) {
            radialLayout = layoutManager;
        } else {
            radialLayout = null;
        }
    }

    protected void addAll(final List nodes) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
//                    System.out.println("Add");
//                    System.out.println("Before Nodes + Edges: " + getChildren().size());
                    addAllNodes(null, nodes);
                    addReferencedObjects();
                    addReferenceEdges();
                    
                    depthBound();
                    // TODO find out how to force a repaint
                    // viewer.repaint();
                    invalidate();
                    repaint();
//                    System.out.println("After  Nodes + Edges: " + getChildren().size());
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    
    protected void removeAll(final List nodes) {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
//                System.out.println("Remove");
//                System.out.println("Before Nodes + Edges = " + getChildren().size());
                removeAllNodes(nodes);

                depthBound();
                invalidate();
                repaint();
//                System.out.println("After  Nodes + Edges = " + getChildren().size());
            }
        });
    }
    
    protected void removeAllNodes(final List nodes) {
        for (Iterator itr = nodes.iterator(); itr.hasNext(); ) {
            EObject obj = (EObject) itr.next();

            removeReferenceEdges(obj, obj.eContents(), true);
            removeReferenceEdges(obj, obj.eCrossReferences(), false);

            removeAllNodes(obj.eContents());

            Figure fig = (Figure) nodeFigMap.get(obj);
            if (null != fig) {
                remove(fig);
                nodeFigMap.remove(obj);
            }
        }        
    }

    protected void updateAll(final List nodes) {
//        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
//            public void run() {
//                ArrayList cells = new ArrayList();
//        
//                for (int i = nodes.size() - 1; i >= 0; i--) {
//                    Figure fig = addNode((EObject) nodes.get(i));
//            
//                    //fig.g
//                }
//        
//                //        addReferencedObjects();
//                //        addContainmentEdges(nodes);
//                //        addReferenceEdges();
//            }
//        });
    }
    
    private void addAllNodes(EObject parent, List l) {
        for (Iterator itr = l.iterator(); itr.hasNext(); ) {
            EObject obj = (EObject) itr.next();

            if (nodeFigMap.containsKey(obj)) {
                continue;
            }

            addNode(obj);

//            // add edge from parent if there is one
//            if (null != parent) {
//                addEdge(parent, obj, true);
//            }

            addAllNodes(obj, obj.eContents());
        }
    }
    
    private void addReferencedObjects() {
        if (includeRefs) {
            List l = new ArrayList();
            for (Iterator cellItr = nodeFigMap.keySet().iterator(); cellItr.hasNext(); ) {
                EObject srcObj = (EObject) cellItr.next();

                for (Iterator refItr = srcObj.eCrossReferences().iterator(); refItr.hasNext(); ) {
                    Object dstObj = refItr.next();
                    
                    if (dstObj instanceof EObject) {
                        l.add(dstObj);
                    }
                }
            }
            if (l.size() > 0) {
                addAllNodes(null, l);
            }
        }
    }
    
    private void addReferenceEdges() {
        for (Iterator cellItr = nodeFigMap.keySet().iterator(); cellItr.hasNext(); ) {
            EObject srcObj = (EObject) cellItr.next();

            addReferenceEdges(srcObj, srcObj.eContents(), true);
            addReferenceEdges(srcObj, srcObj.eCrossReferences(), false);
        }
    }
    
    private void addReferenceEdges(EObject srcObj, List refObjs, boolean containment) {
        for (Iterator refItr = refObjs.iterator(); refItr.hasNext(); ) {
            Object dstObj = refItr.next();
            if (nodeFigMap.containsKey(dstObj)) {
                addEdge(srcObj, (EObject) dstObj, containment);
            }
        }
    }
    
    private void removeReferenceEdges(EObject obj, List refObjs, boolean containment) {
        for (Iterator refItr = refObjs.iterator(); refItr.hasNext(); ) {
            EObject refObj = (EObject) refItr.next();
            String key = edgeKey(obj, refObj, containment);
            PolylineConnection connector = (PolylineConnection) edgeFigMap.get(key);
            if (null != connector) {
                if (null != radialLayout) {
                    NodeFigure fig = (NodeFigure) connector.getTargetAnchor().getOwner();
                    radialLayout.setConstraint(fig, null);
//                    System.out.println("-- " + fig + "\t" + connector.getSourceAnchor().getOwner());
                }
                remove(connector);
                edgeFigMap.remove(key);
//                if (DEBUG) System.out.println("Del edge: " + key);
//            } else {
//                System.out.println("No edge: " + key);
            }
        }
    }

    private NodeFigure addNode(EObject node) {
        NodeFigure fig = (NodeFigure) nodeFigMap.get(node);
        
        if (null == fig) {
            fig = new NodeFigure(node);
            Resource res = node.eResource();
            if (null == res) {
                System.err.println("Unexpected NULL Resource for " + node);
            } else {
                FigureConfigurationAction action = (FigureConfigurationAction) resNodeConfig.get(res);
                if (null == action) {
                    System.err.println("Unexpected NULL FigureConfigurationAction for " + res);
                } else {
                    action.configure(fig);
                }
            }
            nodeFigMap.put(node, fig);
            add(fig);
        }
        
        return fig;
    }
    
    private String edgeKey(EObject source, EObject target, boolean containment) {
        return source.hashCode() + "\t" + target.hashCode() + "\t" + containment;
    }
    
    private void addEdge(EObject source, EObject target, boolean containment) {
    	if (!containment) return;
    	
        String keyST = edgeKey(source, target, containment);
//        String keyTS = edgeKey(source, target, containment);
        
        PolylineConnection connector = (PolylineConnection) edgeFigMap.get(keyST);
        if (null == connector) {
            NodeFigure sourceFigure = addNode(source);
            NodeFigure targetFigure = addNode(target);
            
            connector = new NodeConnection(sourceFigure, targetFigure);
            
            edgeFigMap.put(keyST, connector);
//            edgeFigMap.put(keyTS, connector);
            add(connector);
            
//            if (DEBUG) System.out.println("New edge: " + keyST);

            if (containment && null != radialLayout) {
                radialLayout.setConstraint(targetFigure, sourceFigure);
//                System.out.println("++n " + targetFigure + "\t" + sourceFigure);
            }
        } else if (null == connector.getParent()) {
            add(connector);

            if (containment && null != radialLayout) {
                NodeFigure sourceFigure = addNode(source);
                NodeFigure targetFigure = addNode(target);
                
                radialLayout.setConstraint(targetFigure, sourceFigure);
//                System.out.println("++o " + targetFigure + "\t" + sourceFigure);
            }
            
//            if (DEBUG) System.out.println("Add edge: " + keyST);
        } else {
//            if (DEBUG) System.out.println("Old edge: " + keyST);
        }
    }
    
    public void setMaxDepth(final int depth) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                maxdepth = depth;
                depthBound();
                revalidate();
                repaint();
            }
        });
    }
    
    public void showDetails() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                for (Iterator itr = nodeFigMap.entrySet().iterator(); itr.hasNext(); ) {
                    Map.Entry entry = (Map.Entry) itr.next();
                    EObject node = (EObject) entry.getKey();
                    NodeFigure fig = (NodeFigure) entry.getValue();
                    EClass eClass = node.eClass();
                    List attributes = eClass.getEAllAttributes();
                    for (Iterator attrItr = attributes.iterator(); attrItr.hasNext(); ) {
                        EAttribute attr = (EAttribute) attrItr.next();
                        fig.addKeyVal(attr.getName(), node.eGet(attr));
                    }
                }
            }
        });
    }
    
    private void depthBound() {
        for (Iterator itr = nodeFigMap.values().iterator(); itr.hasNext(); ) {
            NodeFigure fig = (NodeFigure) itr.next();
            fig.setVisible(fig.getRank() <= maxdepth);
        }
        for (Iterator itr = edgeFigMap.values().iterator(); itr.hasNext(); ) {
            NodeConnection fig = (NodeConnection) itr.next();
            fig.setVisible(fig.getRank() <= maxdepth);
        }
    }

    public void refresh() {
        addAll(Collections.EMPTY_LIST);
    }
    
}
