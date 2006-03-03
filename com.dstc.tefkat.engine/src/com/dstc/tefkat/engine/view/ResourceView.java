/*
 * Created on 8/09/2003
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
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ResourceView extends JGraph {

    static CellViewRenderer renderer = new VertexRenderer() {
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
        if (null != str && str.startsWith("xmorph.impl")) {
            str = str.substring(12);
        }
        return str;
    }

    /* (non-Javadoc)
     * @see org.jgraph.JGraph#createVertexView(java.lang.Object, org.jgraph.graph.CellMapper)
     */
    protected VertexView createVertexView(Object v, CellMapper cm) {
        return new VertexView(v, this, cm) {
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
