/*
 * Copyright (c) 2005 michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 */

package tefkat.engine;

public abstract class Function2 implements Function {
    
    final public Object call(Context context, Object[] params) {
        throw new UnsupportedOperationException("Internal Error: Wrong method called. Should have been call(Context, Binding, Object[])");
    }

    public abstract Object call(Context context, Binding binding, Object[] params) throws ResolutionException, NotGroundException;
}
