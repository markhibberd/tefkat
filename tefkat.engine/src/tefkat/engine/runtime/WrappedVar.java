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

import org.eclipse.emf.ecore.EClass;


/**
 * @author lawley
 *
 */
public class WrappedVar {

    final private Var var;
    private EClass type;
    private Extent extent;
    private boolean isExact;
    
    public WrappedVar(Var var) {
        if (null == var) {
            throw new IllegalArgumentException("Var must not be null");
        }
        this.var = var;
    }
    
    public Var getVar() {
        return var;
    }
    
    /**
     * Posiible cases:
     *   oldType == null
     * 
     *   oldType == newType     0       0000
     *   oldType < newType      1       0001
     *   oldType > newType      2       0010
     *   oldType <> newType     3       0011
     * 
     *   oldExact && newExact   0       0000
     *   oldExact && !newExact  4       0100
     *   !oldExact && newExact  8       1000
     *   !oldExact && !newExact 12      1100
     * 
     * So there are 4 * 4 + 1 = 17 possibilities
     * 
     * @param type
     * @param isExact
     * @return
     */
    public boolean setType(EClass type, boolean isExact) {
        if (null == this.type) {
            // The most common case
            this.type = type;
            this.isExact = isExact;
            return true;
        }

        int cmp;
        if (this.type.equals(type)) {
            cmp = 0;
        } else if (this.type.isSuperTypeOf(type)) {
            cmp = 1;
        } else if (type.isSuperTypeOf(this.type)) {
            cmp = 2;
        } else {
            cmp = 3;
        }

        if (this.isExact && isExact) {
            cmp |= 0;
        } else if (this.isExact && !isExact) {
            cmp |= 4;
        } else if (isExact) {
            cmp |= 8;
        } else {
            cmp |= 12;
        }
        
        switch (cmp) {
        // types are equal - just update exactness
        case 0:
        case 4:
        case 8:
        case 12:
            this.isExact |= isExact;
            return true;

        // types are not comparable - can never match
        case 3:
        case 7:
        case 11:
        case 15:
            return false;

        // must be exactly two different types - can never match
        case 1:
        case 2:
            return false;
            
        // must be exactly a supertype and also a strict subtype - can never match
        case 5:
            return false;
            
        // must be exactly a subtype
        case 6:
            return true;

        // converse of 6
        case 9:
            this.type = type;
            this.isExact = isExact;
            return true;
            
        // converse of 5
        case 10:
            return false;

        // just narrow the type (no exactness)
        case 13:
            this.type = type;
            return true;
        
        // widening the type with no exactness is a NO-OP
        case 14:
            return true;
            
        default:
            throw new Error("INTERNAL ERROR: Should never reach here!");
        }
    }
    
    public EClass getType() {
        return type;
    }
    
    public void setExtent(Extent extent) {
        this.extent = extent;
    }
    
    public Extent getExtent() {
        return extent;
    }
    
    public boolean isExact() {
        return isExact;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof WrappedVar) {
            return var.equals(((WrappedVar) obj).getVar());
        } else {
            return false;
        }
    }

    public int hashCode() {
        // Base on var's hashCode to preserve contract that equal
        // Objects have the same hashCode,
        // but add one so that this and var hash to different values
        return var.hashCode() + 1;
    }
    
    public String toString() {
        return var +
               (isExact ? "!" : "/") +
               (null == type ? "_" : type.getName() +
               (null == extent ? "@_" : ("@" + extent)));
    }
}
