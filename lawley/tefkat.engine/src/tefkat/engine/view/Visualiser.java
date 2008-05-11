/*
 * Copyright (c) 2004- michael lawley and others.
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

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

import tefkat.engine.Binding;
import tefkat.engine.ExtentListener;


/**
 * @author lawley
 * 
 */
public class Visualiser implements ExtentListener {
    private static int delay = 500;

    private JGraph _graph;

    private static Color ruleUsedColor;

    private static Color activeTermColor;

    private static Color inactiveTermColor;

    private static Color delayedTermColor;
    
    public Visualiser(JGraph graph, int timeout) {
        _graph = graph;
        delay = timeout;

        ruleUsedColor = Color.MAGENTA;
        activeTermColor = Color.ORANGE;
        inactiveTermColor = Color.LIGHT_GRAY;
        delayedTermColor = Color.CYAN;
    }

    public void setTimeout(int timeout) {
        delay = timeout;
    }

    public void highlightEdge(Object src, Object dst, int reason) {
        if (src instanceof EObject && dst instanceof EObject) {
            try {
                final Map map = new HashMap();
                AbstractResourceModel model = (AbstractResourceModel) _graph
                        .getModel();
                doHighlight(map, model, src, dst, reason);

                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        _graph.getGraphLayoutCache().edit(map, null, null, null);
                    }
                });

                Thread.yield();
                pause();
            } catch (RuntimeException e) {
            }
        }
    }

    public void highlightNode(Object object, int reason) {
        if (object instanceof EObject) {
            try {
                final Map map = new HashMap();
                AbstractResourceModel model = (AbstractResourceModel) _graph.getModel();
                doHighlight(map, model, object, reason);

                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        _graph.getGraphLayoutCache().edit(map, null, null, null);
                    }
                });

                Thread.yield();
                pause();
            } catch (RuntimeException e) {
            }
        }
    }

    public void highlightNodes(Collection objects, int reason) {
        try {
            final Map map = new HashMap();
            AbstractResourceModel model = (AbstractResourceModel) _graph
                    .getModel();
            Iterator itr = objects.iterator();
            while (itr.hasNext()) {
                Object obj = itr.next();
                doHighlight(map, model, obj, reason);
            }

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    _graph.getGraphLayoutCache().edit(map, null, null, null);
                }
            });

            Thread.yield();
            pause();
        } catch (RuntimeException e) {
        }
    }

    public void highlightNodes(Binding binding, int reason) {
        try {
            final Map map = new HashMap();
            AbstractResourceModel model = (AbstractResourceModel) _graph
                    .getModel();
            Iterator itr = binding.entrySet().iterator();
            while (itr.hasNext()) {
                Object obj = ((Map.Entry) itr.next()).getValue();
                doHighlight(map, model, obj, reason);
            }

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    _graph.getGraphLayoutCache().edit(map, null, null, null);
                }
            });

            Thread.yield();
            pause();
        } catch (RuntimeException e) {
        }
    }

    private static void pause() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }

    private static void doHighlight(Map map, AbstractResourceModel model,
            Object obj, int reason) {
        if (obj instanceof EObject) {
            Object cell = model.getGraphCell(obj);
            if (null == cell) {
                return;
            }
            Map props = model.getAttributes(cell);
            if (null == props) {
                props = GraphConstants.createMap();
            }
            if ((reason & 1) > 0) {
                adjustBackground(props, 0);
            }
            if ((reason & 2) > 0) {
                adjustBackground(props, 1);
            }
            if ((reason & 4) > 0) {
                adjustBackground(props, 2);
            }
            if ((reason & 8) > 0) {
                GraphConstants.setBackground(props, ruleUsedColor);
            }
            if ((reason & 16) > 0) {
                GraphConstants.setBackground(props, activeTermColor);
            }
            if ((reason & 32) > 0) {
                GraphConstants.setBackground(props, inactiveTermColor);
            }
            if ((reason & 64) > 0) {
                GraphConstants.setBackground(props, delayedTermColor);
            }
            GraphConstants.setOpaque(props, true);
            map.put(cell, props);
        }
    }

    private static void doHighlight(Map map, AbstractResourceModel model,
            Object src, Object dst, int reason) {
        if (src instanceof EObject && dst instanceof EObject) {
            DefaultGraphCell srcCell = model.getGraphCell(src);
            DefaultPort srcPort = (DefaultPort) srcCell.getChildAt(0);
            DefaultPort dstPort = (DefaultPort) model.getGraphCell(dst)
                    .getChildAt(0);
            for (Iterator edgeItr = model.edges(srcPort); edgeItr.hasNext();) {
                DefaultEdge edge = (DefaultEdge) edgeItr.next();
                Object portA = edge.getSource();
                Object portB = edge.getTarget();
                if ((portB.equals(dstPort) && portA.equals(srcPort))
                        || (portA.equals(dstPort) && portB.equals(srcPort))) {
                    Map props = model.getAttributes(edge);
                    if (null == props) {
                        props = GraphConstants.createMap();
                    }
                    if ((reason & 1) > 0) {
                        adjustLine(props);
                    }
                    if ((reason & 2) > 0) {
                        adjustLine(props);
                    }
                    if ((reason & 4) > 0) {
                        adjustLine(props);
                    }
                    GraphConstants.setOpaque(props, true);
                    map.put(edge, props);
                    break;
                }
            }
        }
    }

    private static void adjustBackground(Map map, int i) {
        Color c = GraphConstants.getBackground(map);
        if (null == c) {
            c = Color.white;
        }
        float[] compArray = { 0, 0, 0 };
        c.getColorComponents(compArray);
        compArray[i] *= 0.95;

        GraphConstants.setBackground(map, new Color(compArray[0], compArray[1],
                compArray[2]));
    }

    private static void adjustLine(Map map) {
        float width = GraphConstants.getLineWidth(map);
        width *= 1.02;

        GraphConstants.setLineWidth(map, width);
    }

}
