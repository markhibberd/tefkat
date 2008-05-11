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
    private int charStart;
    private int charEnd;
    
    /**
     * @param obj
     * @param level
     * @param message
     */
    public TefkatMessageEvent(Object obj, int level, String message, int line, int column) {
        this(obj, level, message, line, column, -1, -1);
    }
    
    public TefkatMessageEvent(Object obj, int level, String message, int line, int column, int start, int end) {
        super(obj, level, message);
        this.location = "Line " + line + ", column " + column + " (" + start + ", " + end + ")";
        this.line = line;
        this.column = column;
        this.charStart = start;
        this.charEnd = end;
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
    
    public int getCharStart() {
        return charStart;
    }
    
    public int getCharEnd() {
        return charEnd;
    }

    public String toString() {
        return line + ", " + column + ", " + super.toString();
    }
}
