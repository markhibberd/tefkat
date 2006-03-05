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

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.dstc.tefkat.config.TefkatConfig.Configuration;
import com.dstc.tefkat.config.TefkatConfig.TransformationTask;
import com.dstc.tefkat.config.TefkatConfig.impl.TefkatConfigPackageImpl;
import com.dstc.tefkat.engine.Tefkat;
import com.dstc.tefkat.engine.TefkatListener;
import com.dstc.tefkat.plugin.TefkatPlugin;

/**
 * @author lawley
 *
 */
public class EngineLaunchConfigurationDelegate implements
        ILaunchConfigurationDelegate {

    static {
        TefkatConfigPackageImpl.init();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration, java.lang.String, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
     */
    public void launch(ILaunchConfiguration configuration, final String mode,
            final ILaunch launch, final IProgressMonitor monitor) throws CoreException {
        monitor.beginTask("Tefkat Build", 3);
        
        final String configURI = configuration.getAttribute(IEngineLaunchConfigurationConstants.CONFIGURATION_URI, (String) null);
        final boolean force = configuration.getAttribute(IEngineLaunchConfigurationConstants.FORCE, false);

        final TefkatPlugin plugin = TefkatPlugin.getPlugin();
        // clear out loaded resources so that a reload is forced
        // - things may have changed so we don't want any cached state.
        //
        plugin.clearResourceSet();

        try {
            final Exception[] error = { null };
            final ResourceSet rs = plugin.getResourceSet();

            Thread thread = new Thread() {

                public void run() {
                    Resource config = rs.getResource(URI.createPlatformResourceURI(configURI), true);
                    for (Iterator configItr = config.getContents().iterator(); configItr.hasNext(); ) {
                        Object obj = configItr.next();
                        if (obj instanceof Configuration) {
                            Configuration conf = ((Configuration) obj);
                            for (Iterator transItr = conf.getTransformationTasks().iterator(); transItr.hasNext(); ) {
                                final TransformationTask task = (TransformationTask) transItr.next();

                                if (task.isEnabled()) {
//                                  System.out.println("transforming: " + task);
                                    final Tefkat engine = plugin.getTefkat();

                                    try {
//                                        IProcess process = new TefkatProcess(launch, engine);

                                        final IWorkbench workbench = PlatformUI.getWorkbench();
                                        workbench.getDisplay().syncExec(new Runnable() {
                                            public void run() {
                                                IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
                                                IWorkbenchPage page = window.getActivePage();
                                                IViewPart view = page.findView("com.dstc.tefkat.plugin.TefkatView");
                                                if (null != view && page.isPartVisible(view)) {
                                                    TefkatListener listener = (TefkatListener) view.getAdapter(TefkatListener.class);
                                                    if (null != listener) {
                                                        engine.addTefkatListener(listener);
                                                    }
                                                }
                                                view = page.findView("com.dstc.tefkat.plugin.TefkatTransformationView");
                                                if (null != view && page.isPartVisible(view)) {
                                                    TefkatListener listener = (TefkatListener) view.getAdapter(TefkatListener.class);
                                                    if (null != listener) {
                                                        engine.addTefkatListener(listener);
                                                    }
                                                }
                                            }
                                        });

                                        if (mode.equals(ILaunchManager.DEBUG_MODE)) {
                                            DebugTarget target = new DebugTarget(launch, engine);
                                            TefkatPlugin.TEFKAT_RESOURCE_FACTORY.addParserListener(target);
                                            launch.addDebugTarget(target);
                                            DebugSourceLocator locator = new DebugSourceLocator();
                                            launch.setSourceLocator(locator);
                                        } else {
                                            // FIXME add this back in...
//                                            launch.addProcess(process);
                                        }
                                        monitor.subTask("Starting " + task);
                                        engine.transform(task, true, force);
                                    } catch (Exception e) {
                                        error[0] = e;
                                    } finally {
                                        final IWorkbench workbench = PlatformUI.getWorkbench();
                                        workbench.getDisplay().syncExec(new Runnable() {
                                            public void run() {
                                                IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
                                                IWorkbenchPage page = window.getActivePage();
                                                IViewPart view = page.findView("com.dstc.tefkat.plugin.TefkatView");
                                                if (null != view) {
                                                    TefkatListener listener = (TefkatListener) view.getAdapter(TefkatListener.class);
                                                    if (null != listener) {
                                                        engine.removeTefkatListener(listener);
                                                    }
                                                }
                                                view = page.findView("com.dstc.tefkat.plugin.TefkatTransformationView");
                                                if (null != view) {
                                                    TefkatListener listener = (TefkatListener) view.getAdapter(TefkatListener.class);
                                                    if (null != listener) {
                                                        engine.removeTefkatListener(listener);
                                                    }
                                                }
                                            }
                                        });
                                    	
                                        DebugTarget target = (DebugTarget) launch.getDebugTarget();
                                        if (null != target) {
                                            TefkatPlugin.TEFKAT_RESOURCE_FACTORY.removeParserListener(target);
                                        }
                                    }
                                }
                            }
                        } else {
//                            System.err.println("Warning: " + obj + " is not a Configuration instance.");
                        }
                    }
                    monitor.done();
                }
            };
            // If !debugging, poll monitor so that we can propagate interrupt requests
            // This has the (desired) effect of effectively running the transformation
            // synchronously
            thread.start();
            if (!mode.equals(ILaunchManager.DEBUG_MODE)) {
                while (thread.isAlive()) {
                    if (monitor.isCanceled()) {
                        plugin.getTefkat().setInterrupted(true);
                        thread.join(1000);
                        break;
                    }
                    Thread.sleep(1000);
                }
                if (error[0] != null) {
                    transformationFailed(error[0]);
                }
            }
        } catch (Exception e) {
            transformationFailed(e);
        }
    }

    private void transformationFailed(Exception e) throws CoreException {
        String message = e.getMessage();
        if (null == message) {
            message = e.toString();
            e.printStackTrace();
        }
        IStatus status = new Status(IStatus.ERROR, TefkatPlugin.PLUGIN_ID,
                IStatus.OK, message, e);
        throw new CoreException(status);
    }

}
