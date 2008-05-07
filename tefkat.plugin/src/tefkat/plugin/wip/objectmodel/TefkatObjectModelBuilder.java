package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.config.TefkatConfig.TransformationTask;

@SuppressWarnings("unchecked") // raw collections
public class TefkatObjectModelBuilder {
    public void build(TefkatObjectModel model, IWorkspaceRoot root) {
        model.clear();
        collectProjects(model, root);
    }

    private void collectProjects(TefkatObjectModel model, IWorkspaceRoot root) {
        IProject[] projects = root.getProjects();
        for (IProject p : projects) {
            TProject project = TProject.factory.nu(p);
            model.add(project);
            collectConfigs(project);
        }
    }

    private void collectConfigs(final TProject project) {
        IResource r = project.iResource();
        try {
            r.accept(new IResourceVisitor() {
                public boolean visit(final IResource resource) {
                    String name = resource.getName().toLowerCase();
                    if (name.endsWith(".tefkatconfig") || name.equals("tefkat.xmi") || name.equals("tefkat.xml")) {
                        TConfig config = TConfig.factory.nu(resource);
                        project.add(config);
                        collectTransformationTasks(config);
                    }
                    return true;
                }


            });
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private void collectTransformationTasks(TConfig config) {
        Configuration c = config.toConfiguration();
        for (TransformationTask tt : (EList<TransformationTask>) c.getTransformationTasks()) {
            TTransformTask task = TTransformTask.factory.nu(config.iResource(), tt);
            config.add(task);
        }
    }
}
