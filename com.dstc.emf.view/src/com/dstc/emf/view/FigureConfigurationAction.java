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
 */
package com.dstc.emf.view;

import org.eclipse.draw2d.IFigure;

/**
 * @author lawley
 *
 */
public interface FigureConfigurationAction {
    
    /**
     * Set various properties (color, opacity, etc) of the figure
     * 
     * @param figure
     */
    public void configure(IFigure figure);
    
}
