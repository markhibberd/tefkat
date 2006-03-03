/*
 * Created on 30/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.dstc.tefkat.config.TefkatConfig.Configuration;
import com.dstc.tefkat.config.TefkatConfig.TransformationTask;
import com.dstc.tefkat.config.TefkatConfig.impl.TefkatConfigPackageImpl;
import com.dstc.tefkat.engine.Tefkat;
import com.dstc.tefkat.plugin.TefkatPlugin;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
                                    try {
//                                        System.out.println("transforming: " + task);
                                        final Tefkat engine = plugin.getTefkat();
//                                        IProcess process = new TefkatProcess(launch, engine);

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
