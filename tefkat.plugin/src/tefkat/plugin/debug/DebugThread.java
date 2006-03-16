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

package tefkat.plugin.debug;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

import tefkat.plugin.TefkatPlugin;


/**
 * @author lawley
 *
 */
public class DebugThread extends AbstractDebugElement implements IThread {

    final private IStackFrame[] emptyStack = new NodeSelectedLiteralStackFrame[0];
    
    private boolean stepping = false;
    private IBreakpoint[] breakpoints;

    /**
     * 
     */
    public DebugThread(DebugTarget target) {
        super(target);
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#getStackFrames()
     */
    public IStackFrame[] getStackFrames() throws DebugException {
        if (hasStackFrames()) {
            return ((DebugTarget) target).getStackFrames();
        } else {
            return emptyStack;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#hasStackFrames()
     */
    public boolean hasStackFrames() throws DebugException {
        return isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#getPriority()
     */
    public int getPriority() throws DebugException {
        return 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#getTopStackFrame()
     */
    public IStackFrame getTopStackFrame() throws DebugException {
        IStackFrame[] frames = getStackFrames();
        if (frames.length > 0) {
            return frames[0];
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#getName()
     */
    public String getName() throws DebugException {
        return "Thread[1]";
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IThread#getBreakpoints()
     */
    public IBreakpoint[] getBreakpoints() {
        if (null == breakpoints) {
            breakpoints = new IBreakpoint[0];
        }
        return breakpoints;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
     */
    public boolean canResume() {
        return !isTerminated() && isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
     */
    public boolean canSuspend() {
        return !isTerminated() && !isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
     */
    public boolean isSuspended() {
        return getDebugTarget().isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#resume()
     */
    public void resume() throws DebugException {
        getDebugTarget().resume();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
     */
    public void suspend() throws DebugException {
        getDebugTarget().suspend();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#canStepInto()
     */
    public boolean canStepInto() {
        return !isTerminated() && isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#canStepOver()
     */
    public boolean canStepOver() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#canStepReturn()
     */
    public boolean canStepReturn() {
        return canStepInto();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#isStepping()
     */
    public boolean isStepping() {
        return stepping;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#stepInto()
     */
    public void stepInto() throws DebugException {
        ((DebugTarget) getDebugTarget()).step();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#stepOver()
     */
    public void stepOver() throws DebugException {
        throw new DebugException(new Status(IStatus.WARNING, TefkatPlugin.PLUGIN_ID, IStatus.OK, "Step Over is not implemented", null));
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IStep#stepReturn()
     */
    public void stepReturn() throws DebugException {
        ((DebugTarget) getDebugTarget()).stepReturn();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate() {
        return !isTerminated();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated() {
        return getDebugTarget().isTerminated();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException {
        getDebugTarget().terminate();
    }

    /**
     * Sets whether this thread is stepping
     * 
     * @param stepping whether stepping
     */
    protected void setStepping(boolean stepping) {
        this.stepping = stepping;
    }

}
