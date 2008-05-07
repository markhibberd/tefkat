package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResource;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.plugin.wip.objectmodel.impl.TConfigImpl;
import tefkat.plugin.wip.objectmodel.impl.TFactoryImpl;

public interface TConfig extends TElement {
    TFactory<TConfig> factory = new TFactoryImpl<TConfig>(TConfig.class) {
        public TConfig nu(IResource resource, Object... args) { return new TConfigImpl(resource); }
    };

    Configuration toConfiguration();

    void add(TTransformTask task);

    void set(TProject project);
}
