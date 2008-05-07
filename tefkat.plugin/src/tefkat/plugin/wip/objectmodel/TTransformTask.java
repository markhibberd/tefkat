package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResource;

import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.plugin.wip.objectmodel.impl.TConfigImpl;
import tefkat.plugin.wip.objectmodel.impl.TFactoryImpl;
import tefkat.plugin.wip.objectmodel.impl.TTransformTaskImpl;

public interface TTransformTask extends TElement {
    TFactory<TTransformTask> factory = new TFactoryImpl<TTransformTask>(TTransformTask.class) {
        public TTransformTask nu(IResource resource, Object... args) { return new TTransformTaskImpl(resource, (TransformationTask) args[0]); }
    };
    void setConfig(TConfig config);
;

}
