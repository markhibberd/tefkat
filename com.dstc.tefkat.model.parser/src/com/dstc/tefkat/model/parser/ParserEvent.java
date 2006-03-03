/*
 * Created on 26/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.model.parser;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParserEvent {

    private Object obj;
    private int startChar;
    private int endChar;

    /**
     * 
     */
    public ParserEvent(Object obj, int startChar, int endChar) {
        this.obj = obj;
        this.startChar = startChar;
        this.endChar = endChar;
    }

    /**
     * @return Returns the end char index.
     */
    public int getEndChar() {
        return endChar;
    }
    /**
     * @return Returns the obj.
     */
    public Object getObj() {
        return obj;
    }
    /**
     * @return Returns the start char index.
     */
    public int getStartChar() {
        return startChar;
    }
}
