package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResource;

public interface TFactory<T> {
    T nu(IResource resource, Object... args);
}
