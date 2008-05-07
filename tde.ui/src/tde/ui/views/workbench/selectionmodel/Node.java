package tde.ui.views.workbench.selectionmodel;

import java.util.List;

public interface Node {
    String label();
    List<Node> children();
    Node parent();
}
