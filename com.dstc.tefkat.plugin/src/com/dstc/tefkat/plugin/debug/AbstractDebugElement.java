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

package com.dstc.tefkat.plugin.debug;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

/**
 * @author lawley
 *
 */
public abstract class AbstractDebugElement
        extends PlatformObject implements IDebugElement {
    
    protected DebugTarget target;

    public AbstractDebugElement(DebugTarget target) {
        this.target = target;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getModelIdentifier()
     */
    public String getModelIdentifier() {
        return IEngineLaunchConfigurationConstants.ID_ENGINE_DEBUG_MODEL;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getDebugTarget()
     */
    public IDebugTarget getDebugTarget() {
        return target;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getLaunch()
     */
    public ILaunch getLaunch() {
        // TODO Auto-generated method stub
        return getDebugTarget().getLaunch();
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (adapter == IDebugElement.class) {
            return this;
        }
        return super.getAdapter(adapter);
    }

    /**
     * Fires a debug event
     * 
     * @param event the event to be fired
     */
    protected void fireEvent(DebugEvent event) {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (null != plugin) {
            plugin.fireDebugEventSet(new DebugEvent[] {event});
        }
    }
    
    /**
     * Fires a <code>CREATE</code> event for this element.
     */
    protected void fireCreationEvent() {
        fireEvent(new DebugEvent(this, DebugEvent.CREATE));
    }       
    
    /**
     * Fires a <code>RESUME</code> event for this element with
     * the given detail.
     * 
     * @param detail event detail code
     */
    public void fireResumeEvent(int detail) {
        fireEvent(new DebugEvent(this, DebugEvent.RESUME, detail));
    }

    /**
     * Fires a <code>SUSPEND</code> event for this element with
     * the given detail.
     * 
     * @param detail event detail code
     */
    public void fireSuspendEvent(int detail) {
        fireEvent(new DebugEvent(this, DebugEvent.SUSPEND, detail));
    }
    
    /**
     * Fires a <code>TERMINATE</code> event for this element.
     */
    protected void fireTerminateEvent() {
        fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
    }
    
}
