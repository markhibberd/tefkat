package tde.ui.old.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;

public class TDEProject implements Child, Parent {
    private TDEDomainModel domain;
    private List<TefkatConfig> configs;
    private IProject project;
    public TDEProject(TDEDomainModel domain, IProject project) {
        this.project = project;
        this.configs = configs();
        this.domain = domain;
    }

    public boolean isTefkatProject() { return configs.size() > 0; }

    private List<TefkatConfig> configs() {
        final List<TefkatConfig> configs = new ArrayList<TefkatConfig>();
        collectConfigs(this, configs);
        return configs;
    }

    private void collectConfigs(final TDEProject tde, final List<TefkatConfig> configs) {
        try {
            project.accept(new IResourceVisitor() {
                public boolean visit(final IResource resource) {
                    if (resource.getName().endsWith(".tefkatconfig") || resource.equals("tefkat.xmi") || resource.equals("tefkat.xml")) {
                        configs.add(new TefkatConfig(tde, resource));
                    }
                    return true;
                }
            });
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }


    public Parent parent() {
        return domain;
    }

    public String name() {
        return project.getName();
    }

    public void aggregateChildren(List<Child> children) {
        children.addAll(configs);
    }
}
