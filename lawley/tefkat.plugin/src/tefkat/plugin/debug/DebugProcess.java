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

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;

/**
 * @author lawley
 *
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
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getAttribute(java.lang.String)
     */
    public String getAttribute(String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
//        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IProcess#getExitValue()
     */
    public int getExitValue() throws DebugException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
//        return 0;
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
        throw new UnsupportedOperationException();
//        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
//        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();

    }

}
