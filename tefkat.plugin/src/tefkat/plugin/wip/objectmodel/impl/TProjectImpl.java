package tefkat.plugin.wip.objectmodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;

import tefkat.plugin.wip.objectmodel.TConfig;
import tefkat.plugin.wip.objectmodel.TProject;
import tefkat.plugin.wip.objectmodel.TefkatObjectModel;

public class TProjectImpl extends TElementImpl implements TProject {
    private final List<TConfig> configs = new ArrayList<TConfig>();
    private TefkatObjectModel model;

    public TProjectImpl(IResource p) {
        super(p);
    }

    public void setObjectModel(TefkatObjectModel model) {
        this.model = model;
    }

    public void add(TConfig config) {
        config.set(this);
        configs.add(config);
    }

}
