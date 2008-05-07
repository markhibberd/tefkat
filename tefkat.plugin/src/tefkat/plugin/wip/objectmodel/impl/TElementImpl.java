package tefkat.plugin.wip.objectmodel.impl;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.plugin.TefkatPlugin;
import tefkat.plugin.wip.objectmodel.TElement;

public abstract class TElementImpl implements TElement {
    private final IResource iresource;
    private final Resource eresource;

    public TElementImpl(IResource iresource) {
        this(iresource, toEMFResource(iresource));
    }

    public TElementImpl(IResource iresource, Resource eresource) {
        this.iresource = iresource; this.eresource = eresource;
    }

    public IResource iResource() { return iresource; }
    public Resource eResource() { return eresource; }

    private static Resource toEMFResource(IResource src) {
        if (src == null) return null;
        if (!src.exists()) return null;
        if (src instanceof IProject) return null;
        URI uri = URI.createPlatformResourceURI(src.getFullPath().toString(), false);
        TefkatPlugin plugin = TefkatPlugin.getPlugin();
        try {
            return plugin.getResourceSet().getResource(uri, true);
        } catch (Throwable t) {
            System.err.println("bug workaround: the resource framework does not use the tefkat config model by default, when using an xmi extension");
            System.err.println(uri);
            // FIXME if you hit the resource a second time it works this double try can be removed once weird behaviour is resolved
            try {
                return plugin.getResourceSet().getResource(uri, true);
            } catch (Throwable t2) {
                System.err.println("cant get " + uri + " as an emf resource: " + t2.getMessage());
                return null;
            }
        }
    }
}
