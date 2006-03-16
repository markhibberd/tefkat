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

import antlr.CommonHiddenStreamToken;

/**
 * @author lawley
 *
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
