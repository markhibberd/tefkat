package tefkat.plugin.wip.objectmodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.plugin.wip.objectmodel.TConfig;
import tefkat.plugin.wip.objectmodel.TProject;
import tefkat.plugin.wip.objectmodel.TTransformTask;

public class TConfigImpl extends TElementImpl implements TConfig {
    private final List<TTransformTask> tasks = new ArrayList<TTransformTask>();
    private Configuration config;
    private TProject project;

    public TConfigImpl(IResource resource) {
        super(resource);
    }

    public Configuration toConfiguration() {
        if (config == null) config = toConfig(eResource());
        return this.config;
    }

    public void set(TProject project) {
        this.project = project;
    }

    public void add(TTransformTask task) {
        task.setConfig(this);
        tasks.add(task);
    }

    private static Configuration toConfig(Resource resource) {
        EList<EObject> model = resource.getContents();
        if (model.size() != 1) throw new AssertionError("configs should only ever have one root element (config:Configuration)");
        EObject config = model.get(0);
        if (!(config instanceof Configuration)) throw new AssertionError("tefkat plug-in must be installed, and used to load config");
        return (Configuration) config;
    }

}
