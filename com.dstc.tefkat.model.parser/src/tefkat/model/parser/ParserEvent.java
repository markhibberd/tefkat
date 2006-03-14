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

/**
 * @author lawley
 *
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
