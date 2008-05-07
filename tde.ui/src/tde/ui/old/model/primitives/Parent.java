package tde.ui.old.model.primitives;

import java.util.List;

public interface Parent extends Element {
    void aggregateChildren(List<Child> children);
}
