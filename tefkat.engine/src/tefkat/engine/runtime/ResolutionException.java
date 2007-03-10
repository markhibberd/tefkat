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
 *
 */

package tefkat.engine.runtime;

public class ResolutionException extends TefkatException {
    /**
     * 
     */
    private static final long serialVersionUID = 98242606762649962L;
    
    transient private Node node;

    public ResolutionException(Node node, String message, Throwable throwable) {
        super(message, throwable);
        this.node = node;
    }

    public ResolutionException(Node node, String message) {
        super(message);
        this.node = node;
    }

    public void setNode(Node node) {
        if (null == this.node) {
            this.node = node;
        } else {
            throw new Error("Cannot change Node reference", this);
        }
    }
    
    public Node getNode() {
        return node;
    }
    
    public String getMessage() {
        return super.getMessage() + (null == node ? "" : " -- " + node);
    }
    
    public String getLocalizedMessage() {
        return super.getLocalizedMessage() + (null == node ? "" : " -- " + node);
    }
}

