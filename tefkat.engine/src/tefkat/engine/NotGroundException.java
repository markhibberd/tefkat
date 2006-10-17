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

    /**
     * @param message
     */
    public NotGroundException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public NotGroundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public NotGroundException(Throwable cause) {
        super(cause);
    }
}

