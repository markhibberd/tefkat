/*
 * Created on 16/12/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.emf.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RadialTreeLayout extends AbstractLayout {
    
    private static final double TWO_PI = Math.PI * 2.0;

    /**
     * Map from Child to Parent
     */
    private Map constraints = new HashMap();
    
    private Map layoutThreads = new HashMap();

    public RadialTreeLayout() {
    }

    /* (non-Javadoc)
     * @see org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure, int, int)
     */
    protected Dimension calculatePreferredSize(IFigure fig, int wHint, int hHint) {
        Rectangle rect = new Rectangle();
        for (Iterator children = fig.getChildren().iterator(); children.hasNext(); ) {
            IFigure child = (IFigure) children.next();
            Rectangle r = (Rectangle) constraints.get(child);
            if (r == null) {
                continue;
            }
        
            if (r.width == -1 || r.height == -1) {
                Dimension preferredSize = child.getPreferredSize(r.width, r.height);
                r = r.getCopy();
                if (r.width == -1) {
                    r.width = preferredSize.width;
                }
                if (r.height == -1) {
                    r.height = preferredSize.height;
                }
            }
            rect.union(r);
        }
        Dimension d = rect.getSize();
        Insets insets = fig.getInsets();
        return new Dimension(d.width + insets.getWidth(),
                             d.height + insets.getHeight()).union(getBorderPreferredSize(fig));
    }

    private void buildNodeList(List parents, List allNodes) {
        for (Iterator itr = parents.iterator(); itr.hasNext(); ) {
            NodeFigure node = (NodeFigure) itr.next();
            List children = node.getFiguresBelow();
            allNodes.addAll(children);
            buildNodeList(children, allNodes);
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
     */
    public void layout(IFigure parent) {
        LayoutThread thread = (LayoutThread) layoutThreads.get(parent);
        if (null == thread) {
            thread = new LayoutThread(parent);
            layoutThreads.put(parent, thread);
        }
        thread.doLayout();  // FIXME - should call layout() but it needs to get SWT stuff on yet another thread(!)
    }

    class LayoutThread extends Thread {
        private boolean requested = false;
        private IFigure parent;
        
        LayoutThread(IFigure parent) {
            this.parent = parent;
        }
        
        final public synchronized void layout() {
            requested = true;
            if (!isAlive()) {
                start();
            }
            notifyAll();
        }
        
        final public void run() {
            boolean doWork = false;
            
            while (true) {
                synchronized (this) {
                    if (requested) {
                        doWork = true;
                        requested = false;
                    } else {
                        doWork = false;
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                if (doWork) {
                    try {
                        doLayout();
                        try {
                            Thread.sleep(500);  // throttle the layout
                        } catch (InterruptedException e) {
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
        
        final void doLayout() {
            List roots = new ArrayList();
            for (Iterator itr = parent.getChildren().iterator(); itr.hasNext(); ) {
                Object obj = itr.next();
                if (obj instanceof NodeFigure && null == ((NodeFigure) obj).getFigureAbove()) {
                    roots.add(obj);
                }
            }
        
            List sortedNodes = new ArrayList();
            sortedNodes.addAll(roots);
            buildNodeList(roots, sortedNodes);
            
            List leaves = new ArrayList(sortedNodes);
            leaves.removeAll(constraints.values());

            // Note how the number of "Edges" increases as Show/Hide Trace is toggled
            // ...there's a bug
            System.out.println("Nodes: " + sortedNodes.size() + " Roots: " + roots.size() + " Leaves: " + leaves.size() + " Constraints: " + constraints.size());   // TODO
                                                                                                                                                                // delete
        
            if (leaves.size() < 1) {
                return;
            }
            
            double arc = TWO_PI / leaves.size();
            double angle = 0;
            
            int maxRank = 1;
            
//          Set the angle of each leaf node
            for (Iterator itr = leaves.iterator(); itr.hasNext(); ) {
                NodeFigure leaf = (NodeFigure) itr.next();
                leaf.setAngle(angle);
                angle += arc;
                if (leaf.getRank() > maxRank) {
                    maxRank = leaf.getRank();
                }
            }
            
            List nodes = new ArrayList(leaves);
            while (nodes.size() > 0) {
                NodeFigure node = (NodeFigure) nodes.remove(0);
                NodeFigure nodeAbove = node.getFigureAbove();
                if (null != nodeAbove) {
                    List siblings = nodeAbove.getFiguresBelow();
                    NodeFigure firstChild = (NodeFigure) siblings.get(0);
                    NodeFigure lastChild = (NodeFigure) siblings.get(siblings.size() - 1);
                    nodeAbove.setAngle((firstChild.getAngle() + lastChild.getAngle()) / 2.0);

                    nodes.removeAll(siblings);
                    nodes.add(nodeAbove);
                }
            }
        
            Rectangle bounds = parent.getClientArea();
            double rad_x = bounds.width / (maxRank + maxRank + 1);
            double rad_y = bounds.height / (maxRank + maxRank + 1);
            Point centre = bounds.getLocation().getTranslated(bounds.width / 2, bounds.height / 2);
            
            for (int i = sortedNodes.size() - 1; i >= 0; i--) {
                NodeFigure fig = (NodeFigure) sortedNodes.get(i);
                Dimension preferredSize = fig.getPreferredSize(-1, -1);
                int x = (int) ((fig.getRank() * rad_x) * Math.cos(fig.getAngle())) - preferredSize.width/2;
                int y = (int) ((fig.getRank() * rad_y) * Math.sin(fig.getAngle())) - preferredSize.height/2;
                Rectangle figBounds = new Rectangle(x, y, preferredSize.width, preferredSize.height);
                figBounds = figBounds.getTranslated(centre);
                fig.setBounds(figBounds);
            }
        }
    }

    /**
     * <code>obj</code> is the parent figure of <code>fig</code>
     * 
     * @see org.eclipse.draw2d.LayoutManager#setConstraint(org.eclipse.draw2d.IFigure, java.lang.Object)
     */
    public void setConstraint(IFigure fig, Object obj) {
        if (fig instanceof NodeFigure) {
            if (obj instanceof NodeFigure) {
                constraints.put(fig, obj);
                ((NodeFigure) fig).setFigureAbove((NodeFigure) obj);
            } else if (null == obj) {
                constraints.remove(fig);
                ((NodeFigure) fig).setFigureAbove(null);
            }
        }
        super.setConstraint(fig, obj);
    }

}
