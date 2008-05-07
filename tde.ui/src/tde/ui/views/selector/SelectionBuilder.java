package tde.ui.views.selector;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class SelectionBuilder {
    public static SelectionViewerSelection build(EObject root, List<SelectionViewerEntry> children) {
        if (root == null) return new EmptySelection();
        return new Selection(root); // TODO implement wild cards, all edits currently ignored
    }
}
