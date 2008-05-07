package tde.ui.old.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tde.tools.ResourceUtil;
import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;
import tefkat.config.TefkatConfig.Configuration;
import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.plugin.TefkatPlugin;

public class TefkatConfig implements Parent, Child, ResourceHolder {
    private List<TransformTask> transforms;
    private Resource resource;
    private IResource iresource;
    private TDEProject project;
    @SuppressWarnings("unchecked")
    public TefkatConfig(TDEProject project, IResource src) {
        this.project = project;
        this.transforms = new ArrayList<TransformTask>();
        this.iresource = src;
        resource = toEMFResource(src);
        Configuration config = toConfig(resource);
        List<TransformationTask> tasks = config.getTransformationTasks();
        for (TransformationTask t : tasks) transforms.add(new TransformTask(this, t));
    }

    private Configuration toConfig(Resource resource) {
        EList<EObject> model = resource.getContents();
        if (model.size() != 1) throw new AssertionError("configs should only ever have one root element (config:Configuration)");
        EObject config = model.get(0);
        if (!(config instanceof Configuration)) throw new AssertionError("tefkat plug-in must be installed, and used to load config");
        return (Configuration) config;
    }

    private Resource toEMFResource(IResource src) {
        URI uri = URI.createPlatformResourceURI(src.getFullPath().toString(), false);
        TefkatPlugin plugin = TefkatPlugin.getPlugin();
        try {
            return plugin.getResourceSet().getResource(uri, true);
        } catch (Throwable t) {
            System.err.println("bug workaround: the resource framework does not use the tefkat config model by default, when using an xmi extension");
            // FIXME if you hit the resource a second time it works this double try can be removed once weird behaviour is resolved
            return plugin.getResourceSet().getResource(uri, true);
        }
    }


    public void aggregateChildren(List<Child> children) {
        children.addAll(transforms);
    }

    public String name() {
        URI uri = resource.getURI();
        String name = uri.toPlatformString(false);
        String prj = project.name();
        return name.substring(prj.length() + 2);
    }

    public Parent parent() {
        return project;
    }

    @Override
    public IResource getResource() {
        return iresource;
    }
}
