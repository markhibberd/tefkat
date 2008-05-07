package tde.ui.views.workbench.selectionmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class EListNode implements Node {
    public Node parent;
    public EList list;
    public String name;

    public EListNode(String name, Node parent,  EList list) { this.name = name; this.parent = parent; this.list = list; }

    public List<Node> children() {
        List<Node> children = new ArrayList<Node>();
        for (Object o : list) {
            if (o instanceof EObject) {
                children.add(new EObjectNode(this, (EObject) o));
            } else {
                if (o != null) {
                    children.add(new StringNode(this, o.toString()));
                }
            }
        }
        return children;
    }

    public String label() { return name + "s"; }

    public Node parent() { return parent; }
}
