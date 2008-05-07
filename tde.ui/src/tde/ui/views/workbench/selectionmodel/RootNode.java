package tde.ui.views.workbench.selectionmodel;

import java.util.ArrayList;
import java.util.List;

public class RootNode implements Node {
    public Node child;

    public void set(Node child) { this.child = child; }

    public List<Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(child);
        return children;
    }

    public String label() { return ""; }

    public Node parent() { return null; }
}
