/*
 * Created on 29/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EObject;

import com.dstc.tefkat.model.AbstractVar;
import com.dstc.tefkat.model.TRule;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleStackFrame extends AbstractStackFrame {
    
    private IThread thread;
    private TRule rule;
    private IVariable[] variables;

    /**
     * @param target
     */
    public RuleStackFrame(IThread thread, TRule rule) {
        super((DebugTarget) thread.getDebugTarget());
        
        this.thread = thread;
        this.rule = rule;
        
        List vars = rule.getVars();
        variables = new IVariable[vars.size()];
        for (int i = 0; i < vars.size(); i++) {
            AbstractVar key = (AbstractVar) vars.get(i);
            variables[i] = new DebugVariable(this, key, null);
        }
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.plugin.debug.AbstractStackFrame#getVariableValue(java.lang.Object)
     */
    protected IValue getVariableValue(Object var) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.plugin.debug.AbstractStackFrame#getTerm()
     */
    protected EObject getElement() {
        return rule;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStackFrame#getThread()
     */
    public IThread getThread() {
        return thread;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStackFrame#getVariables()
     */
    public IVariable[] getVariables() throws DebugException {
        return variables;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStackFrame#hasVariables()
     */
    public boolean hasVariables() throws DebugException {
        return variables.length > 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStackFrame#getName()
     */
    public String getName() throws DebugException {
        return rule.getName();
    }

}
