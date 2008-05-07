package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;

public interface TElement {
    IResource iResource();
    Resource eResource();
}
