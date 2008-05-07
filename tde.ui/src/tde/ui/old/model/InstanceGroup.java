package tde.ui.old.model;

import java.util.List;

import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;

public class InstanceGroup implements Parent, Child {
    private Parent parent;
    private List<? extends Child> children;
    private String name;
    public InstanceGroup(Parent parent, List<? extends Child> children, String name) {
        this.parent = parent;
        this.children = children;
        this.name = name;
    }
    public void aggregateChildren(List<Child> children) {
        children.addAll(this.children);
    }
    public String name() {
        return name;
    }
    public Parent parent() {
        return parent;
    }
}
