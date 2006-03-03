/*
 * Created on 22/04/2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.dstc.tefkat.engine.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;

/**
 * @author lawley
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class AbstractResourceModel extends DefaultGraphModel {

    protected List modelCells;

    protected Map cellMap;

    protected static final float[] DASH_PATTERN = { 2f, 4f };

    protected static int width = 80;

    protected static int height = 40;

    protected static int gap = 5;
    
    protected boolean includeRefs = false;

    protected abstract void addAll();

    public DefaultGraphCell getGraphCell(Object obj) {
        return (DefaultGraphCell) cellMap.get(obj);
    }

    /**
     * Supplied objects were added to the Resource - update the model with
     * Cells and Edges as required.
     * 
     * @param nodes
     */
    protected void addAll(List nodes) {
        ArrayList cells = new ArrayList();
        Map attrs = new HashMap();
        ConnectionSet cs = new ConnectionSet();

        addAllNodes(cells, attrs, cs, null, nodes, 0, 0);
        addReferencedObjects(cells, attrs, cs);
        addReferenceEdges(cells, attrs, cs);

        insert(cells.toArray(), attrs, cs, null, null);
    }
    
    /**
     * Supplied objects were removed from direct ownership by the Resource 
     * (but are still transitively owned) - update the model with Edges as
     * required.
     * 
     * @param nodes
     */
    protected void updateAll(List nodes) {
        ArrayList cells = new ArrayList();
        Map attrs = new HashMap();
        ConnectionSet cs = new ConnectionSet();
        
        List edges = new ArrayList();
        for (int i = 0; i < nodes.size(); i++) {
            Iterator itr = edges(nodes.get(i));
            // System.err.println(((EObject) nodes.get(i)).eContainer());
            while (itr.hasNext()) {
                Object edge = itr.next();
                edges.add(edge);
            }
        }
        remove(edges.toArray());

        addReferencedObjects(cells, attrs, cs);
        addContainmentEdges(nodes, cells, attrs, cs);
        addReferenceEdges(cells, attrs, cs);

        // System.err.println(edges.size() + " " + cells.size());
        
        insert(cells.toArray(), attrs, cs, null, null);
    }

    protected int addAllNodes(
        List cells,
        Map attrs,
        ConnectionSet cs,
        Port parent,
        List l,
        int startX,
        int startY) {
        
        int y = startY;

        Iterator itr = l.iterator();
        while (itr.hasNext()) {
            EObject obj = (EObject) itr.next();
            
            if (cellMap.containsKey(obj)) {
            	continue;
            }
            
            // create vertex
            DefaultGraphCell node = new DefaultGraphCell(obj);
            cellMap.put(obj, node);

            Map nodeAttrs = GraphConstants.createMap();
            attrs.put(node, nodeAttrs);
            // setup vertex attrs
            y = y + height;
            GraphConstants.setBounds(
                nodeAttrs,
                new Rectangle(startX, y, width - gap, height - gap));
            GraphConstants.setBorderColor(nodeAttrs, Color.black);
            // add a port
            DefaultPort port = new DefaultPort();
            node.add(port);

            cells.add(node);

            // add edge from parent if there is one
            if (null != parent) {
                DefaultEdge edge = new DefaultEdge();
                Map edgeAttrs = GraphConstants.createMap();
                edgeAttrs.put(RadialTreeLayoutAlgorithm.CONTAINMENT_EDGE, Boolean.TRUE);
                attrs.put(edge, edgeAttrs);
                GraphConstants.setLineEnd(edgeAttrs, GraphConstants.ARROW_CLASSIC);

                cs.connect(edge, parent, port);

                cells.add(edge);
            }

            y = addAllNodes(
                    cells,
                    attrs,
                    cs,
                    port,
                    obj.eContents(),
                    startX + width,
                    y);
        }

        return y;
    }

    private void addReferenceEdges(List cells, Map attrs, ConnectionSet cs) {
        // Add edges for any other references

        // Now add edges
        for (Iterator cellItr = cellMap.keySet().iterator(); cellItr.hasNext();) {
            EObject srcObj = (EObject) cellItr.next();

            Port srcPort = (Port) getGraphCell(srcObj).getChildAt(0);
            for (Iterator refItr = srcObj.eCrossReferences().iterator(); refItr.hasNext();) {
                Object dstObj = refItr.next();
                DefaultGraphCell dstCell = getGraphCell(dstObj);
                if (null != dstCell) {
                    Port dstPort = (Port) dstCell.getChildAt(0);

                    DefaultEdge edge = new DefaultEdge();
                    Map edgeAttrs = GraphConstants.createMap();
                    attrs.put(edge, edgeAttrs);
                    GraphConstants.setDashPattern(edgeAttrs, DASH_PATTERN);
                    GraphConstants.setLineEnd(edgeAttrs, GraphConstants.ARROW_CLASSIC);

                    cs.connect(edge, srcPort, dstPort);

                    cells.add(edge);
//                } else {
//                    System.err.println("No Cell for: "
//                            + ((EObject) dstObj).eClass().getName() + ":"
//                            + dstObj.hashCode());
                }
            }
        }
    }

    private void addContainmentEdges(List nodes, List cells, Map attrs, ConnectionSet cs) {
        // Now add edges
        for (Iterator cellItr = nodes.iterator(); cellItr.hasNext();) {
            EObject srcObj = (EObject) cellItr.next();
            
            Port srcPort = (Port) getGraphCell(srcObj).getChildAt(0);
            
            // Add edge to container if there is one
            Object parentObj = srcObj.eContainer();
            DefaultGraphCell parentCell = getGraphCell(parentObj);
            if (null != parentCell) {
                Port parentPort = (Port) parentCell.getChildAt(0);

                DefaultEdge edge = new DefaultEdge();
                Map edgeAttrs = GraphConstants.createMap();
                edgeAttrs.put(RadialTreeLayoutAlgorithm.CONTAINMENT_EDGE, Boolean.TRUE);
                attrs.put(edge, edgeAttrs);
                GraphConstants.setLineEnd(edgeAttrs, GraphConstants.ARROW_CLASSIC);

                cs.connect(edge, parentPort, srcPort);

                cells.add(edge);
            }
            
            // Add edges to contained things
            for (Iterator refItr = srcObj.eContents().iterator(); refItr.hasNext();) {
                Object dstObj = refItr.next();
                DefaultGraphCell dstCell = getGraphCell(dstObj);
                if (null != dstCell) {
                    Port dstPort = (Port) dstCell.getChildAt(0);

                    DefaultEdge edge = new DefaultEdge();
                    Map edgeAttrs = GraphConstants.createMap();
                    edgeAttrs.put(RadialTreeLayoutAlgorithm.CONTAINMENT_EDGE, Boolean.TRUE);
                    attrs.put(edge, edgeAttrs);
                    GraphConstants.setLineEnd(edgeAttrs, GraphConstants.ARROW_CLASSIC);

                    cs.connect(edge, srcPort, dstPort);

                    cells.add(edge);
                }
            }
        }
    }

    /**
     * @param cells
     * @param attrs
     * @param cs
     */
    private void addReferencedObjects(List cells, Map attrs, ConnectionSet cs) {
        // Make sure all referenced objects are added
        if (includeRefs) {
            List l = new ArrayList();
            for (Iterator cellItr = cellMap.keySet().iterator(); cellItr.hasNext();) {
                EObject srcObj = (EObject) cellItr.next();

                for (Iterator refItr = srcObj.eCrossReferences().iterator(); refItr.hasNext();) {
                    Object dstObj = refItr.next();
                    DefaultGraphCell dstCell = getGraphCell(dstObj);
                    if (null == dstCell) {
                        l.add(dstObj);
                    }
                }
            }
            if (l.size() > 0) {
                addAllNodes(cells, attrs, cs, null, l, 0, 0);
            }
        }
    }

}
