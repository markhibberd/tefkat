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

package tefkat.plugin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TransformationTask;


/**
 * @author lawley
 *
 */
public class TefkatBuilder extends IncrementalProjectBuilder {
    public static final String TEFKAT_GRAMMAR =
        TefkatPlugin.PLUGIN_ID + ".GrammarFile";
    public static final String TEFKAT_MODEL = TefkatPlugin.PLUGIN_ID + ".Model";
    public static final String TEFKAT_PACKAGE =
        TefkatPlugin.PLUGIN_ID + ".Package";
    public static final String TEFKAT_CLASS = TefkatPlugin.PLUGIN_ID + ".Class";

    private static boolean filechecking = true;
    static {
        String value =
            Platform.getDebugOption("tefkat/trace/filechecking");
        if (value != null && value.equalsIgnoreCase("true")) {
            TefkatBuilder.filechecking = true;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.internal.events.InternalBuilder#build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
     */
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
        throws CoreException {

        if (TefkatBuilder.filechecking) {
            System.out.println("Tefkat build...");
        }

        IProject project = getProject();
        IResourceDelta delta = getDelta(project);
        IResourceDelta[] deltas = null;
        if (null != delta) {
            deltas = delta.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.CHANGED);
        }

        Resource res = null;

        try {
            IResource file = project.findMember(TefkatPlugin.CONFIGURATION_FILE);
            URI uri =
                URI.createPlatformResourceURI(file.getFullPath().toString());
            res = TefkatPlugin.getPlugin().getResourceSet().getResource(uri, true);
            if (res.getContents().size() < 1) {
            	return null;
            }
            Configuration config = (Configuration) res.getContents().get(0);
            List transTasks = config.getTransformationTasks();
            Set todo = new HashSet();
            
            if (deltas != null) {
                if (TefkatBuilder.filechecking) {
                    System.out.println("Delta checking:");
                }
                for (int i = 0; i < deltas.length; i++) {
                    IResource resource = deltas[i].getResource();
                    checkResource(transTasks, resource, todo);
                }
            } else {
                addAllTransformations(transTasks, todo);
            }
                    
            if (todo.size() > 0) {
                doBuild(monitor, project, todo);
            }
            
            if (TefkatBuilder.filechecking) {
                System.out.println("Tefkat Plugin has been run " + todo.size() + " times.");
            }
        } finally {
            // unload so that subsequent runs see any changes to the config file
        	// TODO FIXME - Need to at least unload the Config Resource,
        	// but probably all the others as well
        	res.unload();
            //TefkatPlugin.getPlugin().unloadResources();
        }
        
        return null;
    }

    private void doBuild(IProgressMonitor monitor, IProject project, Set todo)
        throws CoreException {
        try {
            // Count one step for refreshing the workspace
            // plus, for each task:
            //   one for loading the Resources
            //   four for running the transformation
            //   one for storing the results
            monitor.beginTask("Tefkat Build", 1 + (6 * todo.size()));
            for (Iterator itr = todo.iterator(); itr.hasNext(); ) {
                Object task = itr.next();
                if (isInterrupted() || monitor.isCanceled()) {
                    return;
                }
                if (task instanceof TransformationTask) {
                    TefkatPlugin.getPlugin().run((TransformationTask) task, monitor);
                }
            }
            monitor.subTask("Find Tefkat Generated files");
            project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            monitor.worked(1);
//            needRebuild();    // I don't think we need to call this any more - was calling it because of possible chaining in the tefkat.xml spec
        } finally {
            monitor.done();
        }
    }

    private void addAllTransformations(List transTasks, Set todo) {
        for (Iterator itr = transTasks.iterator(); itr.hasNext();) {
            TransformationTask task = (TransformationTask) itr.next();
            if (task.isEnabled()) {
                todo.add(task);
                if (TefkatBuilder.filechecking) {
                    System.out.println("\t" + task + "added");
                }
            }
        }
    }

    private void checkResource(List transTasks, IResource resource, Set todo)
        throws CoreException {
        if (resource instanceof IContainer) {
            if (TefkatBuilder.filechecking) {
                System.out.println("\t" + resource + " checking contents...");
            }
            IContainer folder = (IContainer) resource;
            IResource[] members = folder.members();
            for (int i = 0; i < members.length; i++) {
                checkResource(transTasks, members[i], todo);
            }
        } else {
            String resourcePath = resource.getProjectRelativePath().toString();
            // if the config file has changed, rebuild all
            if (resourcePath.equals(TefkatPlugin.CONFIGURATION_FILE)) {
                addAllTransformations(transTasks, todo);
                return;
            }
            
            URI resourceUri = URI.createURI(resource.getFullPath().toString());
            resourceUri = TefkatPlugin.getPlugin().getResourceSet().getURIConverter().normalize(resourceUri);
            String resourceName = resourceUri.toString();
            
            for (Iterator itr = transTasks.iterator(); itr.hasNext();) {
                TransformationTask task = (TransformationTask) itr.next();
                if (task.isEnabled()) {
                    String transformation = task.getTransformation().getLocationUri();
                    List sources = task.getSourceModels();
                    if (TefkatBuilder.filechecking) {
                        System.out.println("\t" + resourceName);
                    }
                    if (resourceName.equals(transformation)) {
                        todo.add(task);
                        if (TefkatBuilder.filechecking) {
                            System.out.println("\t\tAdded " + task);
                        }
                    } else {
                        for (Iterator srcItr = sources.iterator(); srcItr.hasNext(); ) {
                            Model model = (Model) srcItr.next();
                            if (resourceName.equals(model.getLocationUri())) {
                                todo.add(task);
                                if (TefkatBuilder.filechecking) {
                                    System.out.println("\t\tAdded " + task);
                                }
                                break; // no need to continue this inner loop
                            }
                        }
                    }
                }
            }
        }
    }

}
