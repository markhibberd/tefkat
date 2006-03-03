/*
 * Created on 11/10/2004
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

import com.dstc.tefkat.engine.Node;
import com.dstc.tefkat.model.AbstractVar;
import com.dstc.tefkat.model.VarScope;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeSelectedLiteralStackFrame extends AbstractStackFrame {

    private Node node;
    private IThread thread;
    private IVariable[] variables;
    
    /**
     * 
     */
    public NodeSelectedLiteralStackFrame(IThread thread, Node node) {
        super((DebugTarget) thread.getDebugTarget());
        
        this.thread = thread;
        this.node = node;
        
        EObject parent = node.selectedLiteral();
        while (parent != null && !(parent instanceof VarScope)) {
            parent = parent.eContainer();
        }
        if (null == parent) {
            variables = new IVariable[0];
        } else {
            List vars = ((VarScope) parent).getVars();
            variables = new IVariable[vars.size()];
            for (int i = 0; i < vars.size(); i++) {
                AbstractVar key = (AbstractVar) vars.get(i);
                variables[i] = new DebugVariable(this, key, null);
            }
        }
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

    protected EObject getElement() {
        EObject element = node.selectedLiteral();
//        Node parent = node.getParentNode();
//        while (null == element && null != parent) {
//            element = parent.selectedLiteral();
//        }
        
//        if (null == element) {
//            new Exception().printStackTrace();
//        }
        return element;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStackFrame#getName()
     */
    public String getName() throws DebugException {
        return String.valueOf(node.selectedLiteral());
    }

    protected IValue getVariableValue(Object var) {
        if (var instanceof AbstractVar) {
            Object val = node.lookup((AbstractVar) var);
            return new DebugValue(this, val);
        } else {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof NodeSelectedLiteralStackFrame) {
            NodeSelectedLiteralStackFrame sf = (NodeSelectedLiteralStackFrame) obj;
            return node.equals(sf.node);
        }
        return false;
    }
    
    public int hashCode() {
        return node.hashCode() + 42;
    }
    
}
