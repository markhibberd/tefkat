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

package com.dstc.tefkat.engine;

import java.util.Collection;

/**
 * @author lawley
 *
 */
public interface ExtentListener {

    public void highlightEdge(Object src, Object dst, int reason);

    public void highlightNode(Object object, int reason);

    public void highlightNodes(Collection objects, int reason);

    public void highlightNodes(Binding binding, int reason);

}
