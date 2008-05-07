package tefkat.plugin.wip.objectmodel.impl;

import org.eclipse.core.resources.IResource;

import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.plugin.wip.objectmodel.TConfig;
import tefkat.plugin.wip.objectmodel.TTransformTask;

public class TTransformTaskImpl extends TElementImpl implements TTransformTask {
    private final TransformationTask task;
    private TConfig config;

    public TTransformTaskImpl(IResource resource, TransformationTask task) {
        super(resource, task.eResource());
        this.task = task;
    }

    public void setConfig(TConfig config) {
        this.config = config;
    }
}
