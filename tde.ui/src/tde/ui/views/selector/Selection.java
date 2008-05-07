package tde.ui.views.selector;

import org.eclipse.emf.ecore.EObject;


public class Selection implements SelectionViewerSelection  {
    private EObject e;
    public Selection(EObject e) { this.e = e; }
    public boolean isEmpty() { return false; }
    public EObject selected() { return e; }
}
