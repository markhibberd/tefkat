/*
 * Copyright (c) 2005- michael lawley and others.
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
 * @author lawley
 *
 */
public class BindingPair extends Binding {
    
    private final Object val;

    public BindingPair(Object value) {
        this(null, value);
    }

    public BindingPair(Binding unifier, Object value) {
        super(unifier);
        val = value;
    }
    
    public Object getValue() {
        return val;
    }

    public boolean equals(Object obj) {
        return obj instanceof BindingPair &&
            super.equals(obj) && 
            val.equals(((BindingPair) obj).getValue());
    }
    
    public int hashCode() {
        return super.hashCode() + val.hashCode();
    }

    public String toString() {
        return val + "/" + super.toString();
    }

}
