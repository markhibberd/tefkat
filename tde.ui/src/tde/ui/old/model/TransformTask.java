package tde.ui.old.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;

import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TransformationTask;



public class TransformTask implements Parent, Child, ResourceHolder {
    private boolean enabled;
    private List<SourceInstance> srcs = new ArrayList<SourceInstance>();;
    private List<TargetInstance> trgs = new ArrayList<TargetInstance>();
    private TraceInstance trace;
    private TransformInstance xform;
    private TefkatConfig config;

    @SuppressWarnings("unchecked")
    public TransformTask(TefkatConfig config, TransformationTask t) {
        this.config = config;
        this.enabled = t.isEnabled();
        if (t.getTrace() != null) this.trace = new TraceInstance(this, t.getTrace());
        this.xform = new TransformInstance(this, t.getTransformation());
        for (Model m : (EList<Model>) t.getSourceModels()) srcs.add(new SourceInstance(this, m));
        for (Model m : (EList<Model>) t.getTargetModels()) trgs.add(new TargetInstance(this, m));
    }

    public void aggregateChildren(List<Child> children) {
        children.add(new InstanceGroup(this, srcs, "src"));
        children.add(new InstanceGroup(this, trgs, "trg"));
        if (trace != null) children.add(trace);
        children.add(xform);
    }

    public String name() {
        return "transformation: " + xform.name();
    }

    public Parent parent() {
        return config;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public IResource getResource() {
        return config.getResource();
    }
}
