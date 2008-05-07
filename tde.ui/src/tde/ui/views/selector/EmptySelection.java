package tde.ui.views.selector;

import org.eclipse.emf.ecore.EObject;


public class EmptySelection implements SelectionViewerSelection {
    public boolean isEmpty() {
        return true;
    }
    public EObject selected() {
        return null;
    }
}
