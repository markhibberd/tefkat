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
package tefkat.plugin.debug;

import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EObject;

import tefkat.model.Var;
import tefkat.model.TRule;


/**
 * @author lawley
 *
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
            Var key = (Var) vars.get(i);
            variables[i] = new DebugVariable(this, key, null);
        }
    }

    /* (non-Javadoc)
     * @see tefkat.plugin.debug.AbstractStackFrame#getVariableValue(java.lang.Object)
     */
    protected IValue getVariableValue(Object var) {
        return null;
    }

    /* (non-Javadoc)
     * @see tefkat.plugin.debug.AbstractStackFrame#getTerm()
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
