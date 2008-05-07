package tefkat.plugin.wip.objectmodel.impl;

import java.util.ArrayList;
import java.util.List;

import tefkat.plugin.wip.objectmodel.TProject;
import tefkat.plugin.wip.objectmodel.TefkatObjectModel;

public class TefkatObjectModelImpl extends TElementImpl implements TefkatObjectModel {
    private final List<TProject> projects = new ArrayList<TProject>();
    public TefkatObjectModelImpl() { super(null); }

    public void add(TProject project) {
        project.setObjectModel(this);
        projects.add(project);
    }

    public void clear() {
        projects.clear();
    }
}
