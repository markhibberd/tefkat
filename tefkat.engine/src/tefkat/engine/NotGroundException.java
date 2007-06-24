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

package tefkat.engine;

import tefkat.model.TefkatException;

/**
 * @author lawley
 *
 */
public class NotGroundException extends TefkatException {

    /**
     * 
     */
    private static final long serialVersionUID = -4499738378813153849L;
    final private Node node;

    /**
     * @param message
     */
    public NotGroundException(Node node, String message) {
        super(message);
        this.node = node;
    }

    /**
     * @param message
     * @param cause
     */
    public NotGroundException(Node node, String message, Throwable cause) {
        super(message, cause);
        this.node = node;
    }

    /**
     * @param cause
     */
    public NotGroundException(Node node, Throwable cause) {
        super(cause);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
}

