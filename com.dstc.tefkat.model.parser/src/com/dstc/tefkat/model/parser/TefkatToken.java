/*
 * Created on 19/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.model.parser;

import antlr.CommonHiddenStreamToken;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TefkatToken extends CommonHiddenStreamToken {
    
    protected int offset;

    public TefkatToken() {
        super();
    }

    public TefkatToken(int t, String txt) {
        super(t, txt);
    }
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int o) {
        offset = o;
    }

}
