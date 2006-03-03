/*
 * Created on 3/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.dstc.tefkat.model.Transformation;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TefkatView extends EMFView {
    
    private boolean showSources = true;
    private boolean showTargets = true;
    private boolean showTrace = false;
    
    private Resource traceResource = null;

    private List actions = new ArrayList();

    private final TefkatListener listener = new TefkatListenerAdapter() {
        public void transformationStarted(Transformation trans, Resource[] srcs, Resource[] tgts, Resource trace, Binding context) {
            traceResource = trace;

            clear();
            refresh();
            if (showSources) {
                for (int i = 0; i < srcs.length; i++) {
                    addResource(srcs[i]);
                }
            }
            if (showTargets) {
                for (int i = 0; i < tgts.length; i++) {
                    addResource(tgts[i]);
                }
            }
            if (showTrace) {
                addResource(trace);
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
