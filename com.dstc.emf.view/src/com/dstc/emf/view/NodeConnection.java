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
 * 
 */
package com.dstc.emf.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

/**
 * @author lawley
 *
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
