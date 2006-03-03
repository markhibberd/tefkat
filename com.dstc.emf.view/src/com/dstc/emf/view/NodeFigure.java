/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.emf.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.ecore.EObject;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeFigure extends Figure {
    
    private EObject data;
    private double angle;
    
    private List below = new ArrayList();
    private NodeFigure above = null;
    private int rank = 1;
    
    /**
     * 
     */
    public NodeFigure(EObject obj) {
        data = obj;
        LayoutManager layout = new ToolbarLayout();
        setLayoutManager(layout);
        
        Label label = newLabel(obj.hashCode() + ":" + obj.eClass().getName());
        add(label);
    }
    
    public void setFigureAbove(NodeFigure fig) {
        if (null != above) {
            above.removeFigureBelow(this);
        }
        if (fig == null) {
            above = null;
            updateRank(1);
        } else {
            above = fig;
            fig.addFigureBelow(this);
            updateRank(fig.getRank() + 1);
        }
    }
    
    public NodeFigure getFigureAbove() {
        return above;
    }
    
    private void addFigureBelow(NodeFigure fig) {
        below.add(fig);
    }
    
    private void removeFigureBelow(NodeFigure fig) {
        below.remove(fig);
    }
    
    public List getFiguresBelow() {
        return below;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    public double getAngle() {
        return angle;
    }
    
    private void updateRank(int newRank) {
        setRank(newRank);
        for (int i = below.size() - 1; i >= 0; i--) {
            NodeFigure b = (NodeFigure) below.get(i);
            b.updateRank(newRank + 1);
        }
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int newRank) {
        rank = newRank;
    }
    
    Figure kvFig = null;
    
    public void addKeyVal(Object key, Object val) {
        if (null == kvFig) {
            kvFig = new CompartmentFigure();
            add(kvFig);
        }
        kvFig.add(newLabel(key + ": " + val));
    }
    
    private Label newLabel(String text) {
        Label label = new Label(text);
        return label;
    }
    
    public static class CompartmentFigure extends Figure {

        public CompartmentFigure() {
            ToolbarLayout layout = new ToolbarLayout();
            layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
            layout.setStretchMinorAxis(false);
            layout.setSpacing(2);
            setLayoutManager(layout);
            setBorder(new CompartmentFigureBorder());
        }

        public class CompartmentFigureBorder extends AbstractBorder {
            public Insets getInsets(IFigure figure) {
                return new Insets(1, 0, 0, 0);
            }

            public void paint(IFigure figure, Graphics graphics, Insets insets) {
                graphics.drawLine(getPaintRectangle(figure, insets)
                        .getTopLeft(), tempRect.getTopRight());
            }
        }
    }
}
