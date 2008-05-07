package tde.ui.old.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.config.TefkatConfig.Model;
import tefkat.model.Transformation;
import tefkat.plugin.TefkatPlugin;

public class TransformInstance extends ModelInstance {
    public TransformInstance(TransformTask task, Model model) {
        super(task, model);
    }

    public String name() {
        TefkatPlugin plugin = TefkatPlugin.getPlugin();
        String location = model.getLocationUri();
        URI uri = URI.createURI(location);
        Resource r = plugin.getResourceSet().getResource(uri, true);
        for (EObject e : r.getContents()) {
            if (e instanceof Transformation) return ((Transformation) e).getName() + " (" + uriSimple(uri) + ")";
        }
        return uriSimple(uri);
    }

    private String uriSimple(URI uri) {
        String full = uri.toString();
        return full.substring(full.lastIndexOf('/') + 1);
    }
}
