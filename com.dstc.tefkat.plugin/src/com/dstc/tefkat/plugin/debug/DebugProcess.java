/*
 * Created on 11/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DebugProcess implements IProcess {

    private ILaunch launch;
    
    /**
     * 
     */
    public DebugProcess(ILaunch launch) {
        this.launch = launch;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getLabel()
     */
    public String getLabel() {
        return launch.toString();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getLaunch()
     */
    public ILaunch getLaunch() {
        return launch;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getStreamsProxy()
     */
    public IStreamsProxy getStreamsProxy() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#setAttribute(java.lang.String, java.lang.String)
     */
    public void setAttribute(String key, String value) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getAttribute(java.lang.String)
     */
    public String getAttribute(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getExitValue()
     */
    public int getExitValue() throws DebugException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException {
        // TODO Auto-generated method stub

    }

}
