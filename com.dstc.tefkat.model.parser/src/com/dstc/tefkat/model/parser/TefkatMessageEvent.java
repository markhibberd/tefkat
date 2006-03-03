/*
 * Created on 29/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.model.parser;

import antlr.debug.MessageEvent;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
