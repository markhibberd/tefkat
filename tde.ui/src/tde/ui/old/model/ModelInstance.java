package tde.ui.old.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;

import tde.tools.ResourceUtil;
import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;
import tefkat.config.TefkatConfig.Model;
import tefkat.plugin.TefkatPlugin;

public abstract class ModelInstance implements Child, ResourceHolder {
    TransformTask task;
    Model model;
    public ModelInstance(TransformTask task, Model model) {
        this.task = task;
        this.model = model;
    }

    public Parent parent() {
        return task;
    }

    public String name() {
        URI uri = URI.createURI(model.getLocationUri());
        String name = uri.toPlatformString(false);
        return name.substring(name.indexOf('/', 1) + 1);
    }

    public IResource getResource() {
        return ResourceUtil.getFile(TefkatPlugin.getPlugin().getResourceSet(), model.getLocationUri());
    }
}
