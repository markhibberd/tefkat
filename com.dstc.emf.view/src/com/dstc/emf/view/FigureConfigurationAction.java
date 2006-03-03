/*
 * Created on 17/12/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.emf.view;

import org.eclipse.draw2d.IFigure;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface FigureConfigurationAction {
    
    /**
     * Set various properties (color, opacity, etc) of the figure
     * 
     * @param figure
     */
    public void configure(IFigure figure);
    
}
