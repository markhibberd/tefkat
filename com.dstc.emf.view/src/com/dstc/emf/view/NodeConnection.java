/*
 * Created on 24/01/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.emf.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeConnection extends PolylineConnection {

    private NodeFigure sourceFigure;
    private NodeFigure targetFigure;

    /**
     * 
     */
    public NodeConnection(NodeFigure sourceFigure, NodeFigure targetFigure) {
        this.sourceFigure = sourceFigure;
        this.targetFigure = targetFigure;
        ChopboxAnchor sourceAnchor = new ChopboxAnchor(sourceFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(targetFigure);
        setSourceAnchor(sourceAnchor);
        setTargetAnchor(targetAnchor);
        PolygonDecoration decoration = new PolygonDecoration();
        setTargetDecoration(decoration);
    }

    /**
     * @return
     */
    public int getRank() {
        return Math.max(sourceFigure.getRank(), targetFigure.getRank());
    }

}
