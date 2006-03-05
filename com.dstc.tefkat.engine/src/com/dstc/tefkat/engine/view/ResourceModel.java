/*
 * Copyright (c) 2003- michael lawley and others.
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

package com.dstc.tefkat.engine.view;

import java.util.HashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author lawley
 *
 */
public final class ResourceModel extends AbstractResourceModel {

    /**
     * 
     */
    private static final long serialVersionUID = -1665468096456556731L;

    protected final class ResourceListener implements Adapter {
        public void notifyChanged(Notification notification) {
//            System.err.println("Change: " + notification.getEventType());
            
            Object[] oldCells = getDescendants(ResourceModel.this, modelCells.toArray()).toArray();
            remove(oldCells);
            addAll();
        }
        public Notifier getTarget() {
            return null;
        }
        public void setTarget(Notifier newTarget) {
            // System.err.println("Target: " + newTarget);
        }
        public boolean isAdapterForType(Object type) {
            return false;
        }
    }

    private final Resource res;
    
    public ResourceModel(Resource resource) {
        res = resource;
        
        cellMap = new HashMap();
        
        res.eAdapters().add(new ResourceListener());

        addAll();
    }

    protected void addAll() {
        // Add all nodes and their containment edges
        modelCells = res.getContents();
        addAll(modelCells);
    }
}
