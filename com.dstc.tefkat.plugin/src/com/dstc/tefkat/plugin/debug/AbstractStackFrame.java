/*
 * Created on 18/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.emf.ecore.EObject;


/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractStackFrame extends AbstractDebugElement
        implements IStackFrame {

    /**
     * @param target
     */
    public AbstractStackFrame(DebugTarget target) {
        super(target);
    }

    protected abstract IValue getVariableValue(Object var);
    
    protected abstract EObject getElement();

    public int getLineNumber() throws DebugException {
        return -1;
    }

    public int getCharStart() throws DebugException {
        return target.getStartChar(getElement());
    }

    public int getCharEnd() throws DebugException {
        return target.getEndChar(getElement());
    }

    public IRegisterGroup[] getRegisterGroups() throws DebugException {
        return null;
    }

    public boolean hasRegisterGroups() throws DebugException {
        return false;
    }

    public boolean canStepInto() {
        return getThread().canStepInto();
    }

    public boolean canStepOver() {
        return getThread().canStepOver();
    }

    public boolean canStepReturn() {
        return getThread().canStepReturn();
    }

    public boolean isStepping() {
        return getThread().isStepping();
    }

    public void stepInto() throws DebugException {
        getThread().stepInto();
    }

    public void stepOver() throws DebugException {
        getThread().stepOver();
    }

    public void stepReturn() throws DebugException {
        getThread().stepReturn();
    }

    public boolean canResume() {
        return getThread().canResume();
    }

    public boolean canSuspend() {
        return getThread().canSuspend();
    }

    public boolean isSuspended() {
        return getThread().isSuspended();
    }

    public void resume() throws DebugException {
        getThread().resume();
    }

    public void suspend() throws DebugException {
        getThread().suspend();
    }

    public boolean canTerminate() {
        return getThread().canTerminate();
    }

    public boolean isTerminated() {
        return getThread().isTerminated();
    }

    public void terminate() throws DebugException {
        getThread().terminate();
    }
    
}
