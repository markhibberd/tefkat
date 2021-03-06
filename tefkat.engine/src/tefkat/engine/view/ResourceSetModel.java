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

package tefkat.engine.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * @author lawley
 *
 */
public final class ResourceSetModel extends AbstractResourceModel {
    /**
     * 
     */
    private static final long serialVersionUID = 6765862732137235095L;
    
    private List resources = null;

    protected final class ResourceListener implements Adapter {
        public void notifyChanged(Notification notification) {
            int eventType = notification.getEventType();
            if (Notification.ADD == eventType) {
                Object newValue = notification.getNewValue();
                if (newValue instanceof EObject) {
                    final List l = new ArrayList(1);
                    l.add(newValue);
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            addAll(l);
                        }
                    });
                }
            } else if (Notification.REMOVE == eventType) {
                Object oldValue = notification.getOldValue();
                if (oldValue instanceof EObject) {
                    // REMOVE may be because object is now owned by another
                    // rather than directly by the resource
                    final List l = new ArrayList(1);
                    l.add(oldValue);
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            updateAll(l);
                        }
                    });
                }
            }
        }
        public Notifier getTarget() {
            return null;
        }
        public void setTarget(Notifier newTarget) {
            //System.err.println("Target: " + newTarget);
        }
        public boolean isAdapterForType(Object type) {
            return false;
        }
    }

    private final ResourceSet resSet;
    
    public ResourceSetModel(ResourceSet resourceSet) {
        modelCells = new ArrayList();
        resSet = resourceSet;
        
        cellMap = new HashMap();
    }

    public void addAll() {
        if (null == resources) {
//            new Exception().printStackTrace();
            resources = new ArrayList();
            resources.addAll(resSet.getResources());
            for (Iterator itr = resources.iterator(); itr.hasNext(); ) {
                Resource res = (Resource) itr.next();
                res.eAdapters().add(new ResourceListener());
            }
        }
        // Add all nodes and their containment edges
        modelCells.clear();
        for (Iterator itr = resources.iterator(); itr.hasNext(); ) {
            Resource res = (Resource) itr.next();
            modelCells.addAll(res.getContents());
        }
        addAll(modelCells);
    }
    
    public void addResource(Resource res) {
        if (null == resources) {
            resources = new ArrayList();
        }
        resources.add(res);
        res.eAdapters().add(new ResourceListener());

        modelCells.addAll(res.getContents());
        addAll(res.getContents());
    }

}
