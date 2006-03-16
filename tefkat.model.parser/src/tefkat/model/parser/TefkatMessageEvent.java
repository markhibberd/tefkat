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
 */
package tefkat.model.parser;

import antlr.debug.MessageEvent;

/**
 * @author lawley
 *
 */
public class TefkatMessageEvent extends MessageEvent {

    private String location;
    private int line;
    private int column;
    
    /**
     * @param obj
     * @param level
     * @param message
     */
    public TefkatMessageEvent(Object obj, int level, String message, int line, int column) {
        super(obj, level, message);
        this.location = String.valueOf(obj);
        this.line = line;
        this.column = column;
    }
    
    public String getLocation() {
        return location;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }

}
