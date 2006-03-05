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

package com.dstc.tefkat.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;

import com.dstc.emf.view.EMFView;
import com.dstc.tefkat.engine.Binding;
import com.dstc.tefkat.engine.TefkatListener;
import com.dstc.tefkat.engine.TefkatListenerAdapter;
import com.dstc.tefkat.model.ContainerExtent;
import com.dstc.tefkat.model.Extent;
import com.dstc.tefkat.model.ReferenceExtent;
import com.dstc.tefkat.model.Transformation;

/**
 * @author lawley
 *
 */
public class TefkatView extends EMFView {
    
    private boolean showSources = false;
    private boolean showTargets = true;
    private boolean showTrace = false;
    
    private Resource traceResource = null;

    private List actions = new ArrayList();

    private final TefkatListener listener = new TefkatListenerAdapter() {
        public void transformationStarted(Transformation trans, Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        	if (trace instanceof ContainerExtent) {
        		traceResource = ((ContainerExtent) trace).getResource();
        	} else {
        		for (Iterator itr = ((ReferenceExtent) trace).getResources().iterator(); itr.hasNext(); ) {
        			traceResource = (Resource) itr.next();
        		}
        	}

            clear();
            refresh();
            if (showSources) {
                for (int i = 0; i < srcs.length; i++) {
                	if (srcs[i] instanceof ContainerExtent) {
                		addResource(((ContainerExtent) srcs[i]).getResource());
                	} else {
                		for (Iterator itr = ((ReferenceExtent) srcs[i]).getResources().iterator(); itr.hasNext(); ) {
                			addResource((Resource) itr.next());
                		}
                	}
                }
            }
            if (showTargets) {
                for (int i = 0; i < tgts.length; i++) {
                	if (tgts[i] instanceof ContainerExtent) {
                		addResource(((ContainerExtent) tgts[i]).getResource());
                	} else {
                		for (Iterator itr = ((ReferenceExtent) tgts[i]).getResources().iterator(); itr.hasNext(); ) {
                			addResource((Resource) itr.next());
                		}
                	}
                }
            }
            if (showTrace) {
            	if (trace instanceof ContainerExtent) {
            		addResource(((ContainerExtent) trace).getResource());
            	} else {
            		for (Iterator itr = ((ReferenceExtent) trace).getResources().iterator(); itr.hasNext(); ) {
            			addResource((Resource) itr.next());
            		}
            	}
            }
        }

        public void transformationFinished() {
            refresh();
        }
    };

    public TefkatView() {
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        
        makeActions();
        
        IActionBars bars = getViewSite().getActionBars();
        IToolBarManager manager = bars.getToolBarManager();
        
        for (Iterator itr = actions.iterator(); itr.hasNext(); ) {
            manager.add((Action) itr.next());
        }
    }
    
    private void makeActions() {
        Action action0 = new Action() {
            public void run() {
                showTrace = !showTrace;
                if (showTrace) {
                    addResource(traceResource);
                    setText("Hide Trace");
                    setToolTipText("Hides the Trace Instances");
                } else {
                    removeResource(traceResource);
                    setText("Show Trace");
                    setToolTipText("Display the Trace Instances");
                }
            }
        };
        action0.setText("Show Trace");
        action0.setToolTipText("Display the Trace Instances");
        actions.add(action0);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (TefkatListener.class.equals(adapter)) {
            return listener;
        }
        return super.getAdapter(adapter);
    }
}
