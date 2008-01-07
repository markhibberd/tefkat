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

/**
 * This exception is used for control-flow manipulation
 * not errors - we don't need a stack trace and avoid calculating
 * it can save a large amount of time.
 * (see <http://www.javaspecialists.co.za/archive/newsletter.do?issue=129>)
 * 
 * @author lawley
 */
public final class NotGroundException extends TefkatException {

    private static final long serialVersionUID = 1L;
    
    final private Node node;

    /**
     * @param message
     */
    public NotGroundException(Node node, String message) {
        super(message);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    /**
     * See class comment
     */
    @Override
    public Throwable fillInStackTrace() {
        return null;
    }

}

