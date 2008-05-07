package tde.ui.views.workbench.selectionmodel;

import java.util.ArrayList;
import java.util.List;

public class StringNode implements Node {
    public Node parent;
    public String s;

    public StringNode(Node parent, String s) { this.parent = parent; this.s = s; }

    public List<Node> children() { return new ArrayList<Node>(); }

    public String label() { return s; }

    public Node parent() { return parent; }
}
