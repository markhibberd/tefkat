package tde.ui.views.workbench;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tde.ui.views.workbench.selectionmodel.EObjectNode;
import tde.ui.views.workbench.selectionmodel.Node;

public class SelectionContentProvider implements ITreeContentProvider {
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
    public void dispose() {}

    public Object[] getChildren(Object parentElement) {
        return node(parentElement).children().toArray();
    }

    public Object getParent(Object element) {
        return node(element).parent();
    }

    public boolean hasChildren(Object element) {
        return element instanceof EObjectNode;
    }

    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    private Node node(Object o) { return (Node) o; }
}
