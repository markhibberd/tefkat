/*
 * Created on 15/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.engine;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
