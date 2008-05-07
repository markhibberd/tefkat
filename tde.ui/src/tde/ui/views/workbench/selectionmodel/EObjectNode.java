package tde.ui.views.workbench.selectionmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EObjectNode implements Node {
    public Node parent;
    public EObject e;

    public EObjectNode(Node parent,  EObject e) { this.parent = parent; this.e = e; }

    public List<Node> children() {
        List<Node> children = new ArrayList<Node>();
        for (EStructuralFeature f : e.eClass().getEStructuralFeatures()) {
            Object o = e.eGet(f);
            if (o instanceof EObject) {
                children.add(new EObjectNode(this, (EObject) o));
            } else if (o instanceof EList) {
                children.add(new EListNode(f.getName(), this, (EList) o));
            } else {
                if (o != null) {
                    children.add(new StringNode(this, f.getName() + ": " + o.toString()));
                }
            }
        }
        return children;
    }

    public String label() { return e.eClass().getName(); }

    public Node parent() { return parent; }
}
