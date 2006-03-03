/*
 * Created on 8/09/2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
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
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public final class ResourceModel extends AbstractResourceModel {

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
