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

package com.dstc.tefkat.engine.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import org.eclipse.emf.ecore.EObject;
import org.jgraph.JGraph;
import org.jgraph.graph.CellMapper;
import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

/**
 * @author lawley
 *
 */
public class ResourceView extends JGraph {

    /**
     * 
     */
    private static final long serialVersionUID = 2153555135139051987L;

    private static final String PACKAGE_PREFIX = "com.dstc.tefkat.model.impl.";
    
    static CellViewRenderer renderer = new VertexRenderer() {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6458030594465954749L;

	public void paint(Graphics g) {
            int b = borderWidth;
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            boolean tmp = selected;
            if (super.isOpaque()) {
                g.setColor(super.getBackground());
                g.fillOval(b - 1, b - 1, d.width - b, d.height - b);
            }
            try {
                setBorder(null);
                setOpaque(false);
                selected = false;
                super.paint(g);
            } finally {
                selected = tmp;
            }
            if (bordercolor != null) {
                g.setColor(bordercolor);
                g2.setStroke(new BasicStroke(b));
                g.drawOval(b - 1, b - 1, d.width - b, d.height - b);
            }
            if (selected) {
                g2.setStroke(GraphConstants.SELECTION_STROKE);
                g.setColor(graph.getHighlightColor());
                g.drawOval(b - 1, b - 1, d.width - b, d.height - b);
            }
        }
    };


    public ResourceView(AbstractResourceModel resourceModel) {
        super(resourceModel);
        setEditable(false);
        // setScale(0.75);
    }

    public String convertValueToString(Object value) {
        if (value instanceof EdgeView) {
            return super.convertValueToString(value);
        }
        if (value instanceof CellView) {
            Object cell = ((CellView) value).getCell();
            Object userObj = ((DefaultGraphCell) cell).getUserObject();
            return ((EObject) userObj).eClass().getName() + " " + value.hashCode();
        }
        String str = super.convertValueToString(value);
        if (null != str && str.startsWith(PACKAGE_PREFIX)) {
            str = str.substring(PACKAGE_PREFIX.length());
        }
        return str;
    }

    /* (non-Javadoc)
     * @see org.jgraph.JGraph#createVertexView(java.lang.Object, org.jgraph.graph.CellMapper)
     */
    protected VertexView createVertexView(Object v, CellMapper cm) {
        return new VertexView(v, this, cm) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1650125773511030753L;

			// Returns Perimeter Point for Ellipses
            public Point getPerimeterPoint(Point source, Point p) {
                // Compute relative bounds
                Rectangle r = getBounds();
                int x = r.x;
                int y = r.y;
                int a = (r.width+1)/2;
                int b = (r.height+1)/2;

                // Get center
                int xCenter = (int) (x + a);
                int yCenter = (int) (y + b);

                // Compute angle
                int dx = p.x - xCenter;
                int dy = p.y - yCenter;
                double t = Math.atan2(dy, dx);

                // Compute Perimeter Point
                int xout = xCenter+(int) (a*Math.cos(t))-1;
                int yout = yCenter+(int) (b*Math.sin(t))-1;

                // Return perimeter point
                return new Point (xout, yout);
            }

            // Returns the Renderer for this View
            public CellViewRenderer getRenderer() {
                return ResourceView.renderer;
            }
        };
    }

}
