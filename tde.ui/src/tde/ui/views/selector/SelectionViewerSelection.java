package tde.ui.views.selector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

public interface SelectionViewerSelection extends ISelection {
    EObject selected();
}
