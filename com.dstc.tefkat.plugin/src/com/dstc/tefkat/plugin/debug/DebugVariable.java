/*
 * Created on 2/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DebugVariable extends AbstractDebugElement implements IVariable {

    private AbstractStackFrame frame;
    private Object var;
    private Object val;
    
    private IValue oldValue = null;
    private int counter = 0;

    /**
     * @param target
     */
    public DebugVariable(AbstractStackFrame frame, Object var, Object val) {
        super((DebugTarget) frame.getDebugTarget());
        
        this.frame = frame;
        this.var = var;
        this.val = val;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IVariable#getValue()
     */
    public IValue getValue() throws DebugException {
        IValue value = frame.getVariableValue(var);
        if (null == value) {
            value = new DebugValue(frame, val);
        }
        if (!value.equals(oldValue)) {
            counter = ((DebugTarget) getDebugTarget()).suspendCount;
            oldValue = value;
        }
        return value;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IVariable#getName()
     */
    public String getName() throws DebugException {
        return String.valueOf(var);
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IVariable#getReferenceTypeName()
     */
    public String getReferenceTypeName() throws DebugException {
        return "Object";
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IVariable#hasValueChanged()
     */
    public boolean hasValueChanged() throws DebugException {
        return counter == ((DebugTarget) getDebugTarget()).suspendCount;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValueModification#setValue(java.lang.String)
     */
    public void setValue(String expression) throws DebugException {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValueModification#setValue(org.eclipse.debug.core.model.IValue)
     */
    public void setValue(IValue value) throws DebugException {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValueModification#supportsValueModification()
     */
    public boolean supportsValueModification() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValueModification#verifyValue(java.lang.String)
     */
    public boolean verifyValue(String expression) throws DebugException {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValueModification#verifyValue(org.eclipse.debug.core.model.IValue)
     */
    public boolean verifyValue(IValue value) throws DebugException {
        return false;
    }

    public boolean equals(Object obj) {
        if (null != var && obj instanceof DebugVariable) {
            DebugVariable dv = (DebugVariable) obj;
            return var.equals(dv.var);
        }
        return false;
    }
    
    public int hashCode() {
        return null == var ? -1 : var.hashCode() + 42;
    }

}
