/*
 * Copyright (c) 2003- michael lawley and others.
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

package tefkat.engine.view;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.VertexView;

public class RadialTreeLayoutAlgorithm {

    /**
     * Based on the paper "Radial Tree Graph Drawing Algorithm for Representing
     * Large Hierarchies" by Greg Book and Neeta Keshary.
     * 
     * Algorithm is modified from that in the above paper since it contains bugs
     * and the sample code contains major inefficiencies.
     * 
     * Since this algorithm needs to be applied to a tree but we have a directed
     * graph, a spanning tree is first constructed then the algorithm is applied
     * to it.
     */

    /**
     * If WIDTH is specified, then CENTRE_X and RADIUS_X are calculated from it
     * based on the maximum depth of the spanning tree.
     */
    public static final String KEY_WIDTH = "Width";

    /**
     * If HEIGHT is specified, then CENTRE_Y and RADIUS_Y are calculated from it
     * based on the maximum depth of the spanning tree.
     */
    public static final String KEY_HEIGHT = "Height";

    public static final String KEY_CENTRE_X = "CentreX";

    public static final String KEY_CENTRE_Y = "CentreY";

    public static final String KEY_RADIUS_X = "RadiusX";

    public static final String KEY_RADIUS_Y = "RadiusY";
    
    public static final String CONTAINMENT_EDGE = "ContainmentEdge";

    private static final String RADIAL_TREE_VISITED = "RadialTreeVisited";

    private static final double TWO_PI = Math.PI * 2.0;

    private double RADIUSX;

    private double RADIUSY;

    private double ROOTX;

    private double ROOTY;
    
    private List leaves = null;

    JGraph jgraph;

    private double depth;

    public RadialTreeLayoutAlgorithm() {
    }

    public void perform(JGraph jgraph, boolean applyToAll, Properties configuration) {

        this.jgraph = jgraph;
        Object[] selectedCells = (applyToAll ? jgraph.getRoots() : jgraph.getSelectionCells());

        if (selectedCells.length == 0) {
            return; // nothing to do
        }

System.err.println("Layout " + selectedCells.length);	// TODO delete
        
        CellView[] selectedCellViews = jgraph.getGraphLayoutCache().getMapping(selectedCells, true);

        // search all roots
        List roots = getRoots(jgraph, selectedCellViews);
        
        TreeNode tree = getSpanningTree(selectedCellViews, roots);

        depth = tree.getDepth() + 0.25;
        
        if (configuration.containsKey(KEY_WIDTH)) {
            double WIDTH = Double.parseDouble(configuration
                    .getProperty(KEY_WIDTH));
            ROOTX = WIDTH / 2.0;
            RADIUSX = ROOTX / depth;
        } else {
            ROOTX = Double.parseDouble(configuration.getProperty(KEY_CENTRE_X));
            RADIUSX = Double.parseDouble(configuration
                    .getProperty(KEY_RADIUS_X));
        }

        if (configuration.containsKey(KEY_HEIGHT)) {
            double HEIGHT = Double.parseDouble(configuration
                    .getProperty(KEY_HEIGHT));
            ROOTY = HEIGHT / 2.0;
            RADIUSY = ROOTY / depth;
        } else {
            ROOTY = Double.parseDouble(configuration.getProperty(KEY_CENTRE_Y));
            RADIUSY = Double.parseDouble(configuration
                    .getProperty(KEY_RADIUS_Y));
        }

        Map viewMap = new HashMap();
//        layoutTree0(viewMap, tree);
        layoutTree(viewMap, tree);
        jgraph.getGraphLayoutCache().edit(viewMap, null, null, null);
        
    }
    
    private void layoutTree(Map viewMap, TreeNode root) {
        double arc = TWO_PI / leaves.size();
        double angle = 0;
        
        // System.err.println(arc);
        
        // Set the angle of each leaf node
        for (int i = 0; i < leaves.size(); i++) {
            TreeNode leaf = (TreeNode) leaves.get(i);
            leaf.angle = angle;
            angle += arc;
            leaf.x = computeX(leaf.rank, leaf.angle);
            leaf.y = computeY(leaf.rank, leaf.angle);

            VertexView view = (VertexView) leaf.getView();
            if (null != view) {
                placeView(viewMap, view, leaf.x, leaf.y);
            }
        }
        
        // 
        //System.err.println("------------------------------------------------");
        List nodes = new ArrayList(leaves);
        while (nodes.size() > 0) {
            TreeNode node = (TreeNode) nodes.remove(0);
            TreeNode parent = node.getParent();
            if (null != parent) {
                TreeNode firstChild = (TreeNode) parent.children.get(0);
                TreeNode lastChild = (TreeNode) parent.children.get(parent.children.size()-1);
                parent.angle = (firstChild.angle + lastChild.angle) / 2;

                parent.x = computeX(parent.rank, parent.angle);
                parent.y = computeY(parent.rank, parent.angle);

                VertexView view = (VertexView) parent.getView();
                if (null != view) {
                    placeView(viewMap, view, parent.x, parent.y);
                }

                nodes.removeAll(parent.children);
                nodes.add(parent);
            }
        }
    }

    /**
     * @param rank
     * @param angle
     * @return
     */
    private double computeY(int rank, double angle) {
        return ROOTY + ((rank * RADIUSY) * Math.sin(angle));
    }

    /**
     * @param rank
     * @param angle
     * @return
     */
    private double computeX(int rank, double angle) {
        return ROOTX + ((rank * RADIUSX) * Math.cos(angle));
    }

    private void placeView(Map viewMap, VertexView view, double x, double y) {
        Rectangle bounds = (Rectangle) view.getBounds().clone();
        bounds.x = (int) Math.round(x);
        bounds.y = (int) Math.round(y);
        Object cell = view.getCell();
        Map map = GraphConstants.createMap();
        GraphConstants.setBounds(map, bounds);
        viewMap.put(cell, map);
    }

    private List getChildren(VertexView view, List level) {
        ArrayList children = new ArrayList();
        Object vertex = view.getCell();
        GraphModel model = jgraph.getModel();
        int portCount = model.getChildCount(vertex);

        // iterate any NodePort
        for (int i = 0; i < portCount; i++) {
            Object port = model.getChild(vertex, i);

            // iterate any Edge in the port
            Iterator itrEdges = model.edges(port);

            while (itrEdges.hasNext()) {
                Object edge = itrEdges.next();

                // only include containment edges
                if (edge instanceof DefaultEdge &&
                        ((DefaultEdge) edge).getAttributes().containsKey(CONTAINMENT_EDGE)) {
                    // if the Edge is a forward edge we should follow this edge
                    if (port == model.getSource(edge)) {
                        Object targetPort = model.getTarget(edge);
                        Object targetVertex = model.getParent(targetPort);
                        VertexView targetVertexView = (VertexView) jgraph
                                .getGraphLayoutCache().getMapping(targetVertex,
                                        false);
                        if (level.contains(targetVertexView)) {
                            children.add(targetVertexView);
                        }
                    }
                }
                
            }
        }
        return children;
    }

    private List getRoots(JGraph jgraph, CellView[] cellViews) {
        List roots = new ArrayList();

        GraphModel model = jgraph.getModel();

        for (int i = 0; i < cellViews.length; i++) {
            if (cellViews[i] instanceof VertexView) {
                VertexView vertexView = (VertexView) cellViews[i];
                boolean isRoot = true;
                Object vertex = vertexView.getCell();
                int portCount = model.getChildCount(vertex);
                for (int j = 0; isRoot && j < portCount; j++) {
                    Object port = model.getChild(vertex, j);

                    Iterator itrEdges = model.edges(port);
                    while (isRoot && itrEdges.hasNext()) {
                        Object edge = itrEdges.next();

                        if (edge instanceof DefaultEdge &&
                                ((DefaultEdge) edge).getAttributes().containsKey(CONTAINMENT_EDGE)) {
                            if (model.getTarget(edge) == port) {
                                isRoot = false;
                            }
                        }
                    }
                }
                if (isRoot) {
                    roots.add(vertexView);
                }
            }
        }

        return roots;
    }

    /**
     * Algorithm assumes a single root node so if there are multiple roots
     * (nodes with no incoming edges), then we construct the spanning tree with
     * an invisible root node that is the parent of the real roots.
     */
    private TreeNode getSpanningTree(CellView[] cellViews, List roots) {
        List vertexViews = new ArrayList(cellViews.length);

        leaves = new ArrayList();
        
        // first: mark all as not visited
        for (int i = 0; i < cellViews.length; i++) {
            if (cellViews[i] instanceof VertexView) {
                VertexView vertexView = (VertexView) cellViews[i];
                vertexView.getAttributes().remove(RADIAL_TREE_VISITED);
                vertexViews.add(vertexView);
            }
        }

        TreeNode node;

        if (roots.size() == 0) {
            // pick an arbitrary node
            roots.add(vertexViews.get(0));
        }
        

        if (roots.size() > 1) {
            node = new TreeNode(null);
            // Initialize the list of leaf nodes
            leaves.add(node);
            buildSpanningTree(vertexViews, node, roots);
        } else {
            VertexView vertexView = (VertexView) roots.get(0);
            node = new TreeNode(vertexView);
            vertexView.getAttributes().put(RADIAL_TREE_VISITED, Boolean.TRUE);
            leaves.add(node);
            buildSpanningTree(vertexViews, node, getChildren(vertexView, vertexViews));
        }

        return node;
    }

    /**
     * Breadth-first traversal of the graph.
     */
    private void buildSpanningTree(List vertexViews, TreeNode node, List children) {
        List childNodes = new ArrayList(children.size());
        
        for( Iterator itr = children.iterator(); itr.hasNext(); ) {
            VertexView vertexView = (VertexView) itr.next();
            if (null == vertexView.getAttributes().get(RADIAL_TREE_VISITED)) {
                vertexView.getAttributes().put(RADIAL_TREE_VISITED, Boolean.TRUE);
                TreeNode childNode = new TreeNode(vertexView);
                node.addChild(childNode);
                childNodes.add(childNode);
            }
        }
        
        if (childNodes.size() > 0) {
            int idx = leaves.indexOf(node);
            // int n = leaves.size();
            leaves.remove(idx);
            leaves.addAll(idx, childNodes);
            // System.out.println(n + " " + childNodes.size() + " " + leaves.size());
        }

        for (Iterator itr = childNodes.iterator(); itr.hasNext(); ) {
            TreeNode childNode = (TreeNode) itr.next();
            VertexView vertexView = childNode.getView();
            buildSpanningTree(vertexViews, childNode, getChildren(vertexView, vertexViews));
        }
    }

    private static class TreeNode {

        public int rank = 0;
        
        private VertexView view;

        private TreeNode parent = null;
        private List children = new ArrayList();

        public double angle, x, y, rightBisector, leftBisector, rightTangent,
                leftTangent;

        TreeNode(VertexView view) {
            this.view = view;
        }

        public int getDepth() {
            int depth = 0;
            Iterator itr = children.iterator();
            while (itr.hasNext()) {
                TreeNode node = (TreeNode) itr.next();
                int childDepth = node.getDepth();
                if (childDepth >= depth) {
                    depth = childDepth + 1;
                }
            }
            return depth;
        }

        public VertexView getView() {
            return view;
        }

        public void addChild(TreeNode node) {
            children.add(node);
            node.parent = this;
            node.updateRank(rank + 1);
        }

        public List getChildren() {
            return children;
        }

        public boolean hasChildren() {
            return children.size() > 0;
        }
        
        public TreeNode getParent() {
            return parent;
        }

        public double leftLimit() {
            return Math.min(normalize(leftBisector), (leftTangent));
        }

        public double rightLimit() {
            return Math.max(normalize(rightBisector), (rightTangent));
        }

        private double normalize(double angle) {
            /*
             while (angle > TWO_PI) {
             angle -= TWO_PI;
             }
             while (angle < -TWO_PI) {
             angle += TWO_PI;
             }
             */
            return angle;
        }
        
        private void updateRank(int newRank) {
            rank = newRank;
            for (int i = 0; i < children.size(); i++) {
                ((TreeNode) children.get(i)).updateRank(rank + 1);
            }
        }
    }

}
