package tde.ui.views.workbench;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import tde.ui.views.workbench.selectionmodel.Node;

public class SelectionLabelProvider extends LabelProvider implements ILabelProvider {
    public Image getImage(Object element) {
        return null;
    }

    public String getText(Object element) {
        if (element instanceof Node) {
            return ((Node) element).label();
        }
        return null;
    }
}
