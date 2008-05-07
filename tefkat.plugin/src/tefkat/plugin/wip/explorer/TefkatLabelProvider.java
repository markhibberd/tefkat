package tefkat.plugin.wip.explorer;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.IDescriptionProvider;

public class TefkatLabelProvider extends LabelProvider implements ILabelProvider, IDescriptionProvider {
    public Image getImage(Object element) {
        if (element instanceof TefkatObjectModelPlaceHolder) {
            // TODO icons
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
        }
        return null;
    }

    public String getText(Object element) {
        if (element instanceof TefkatObjectModelPlaceHolder) {
            return "something from model";
        }
        return null;
    }

    public String getDescription(Object anElement) {
        if (anElement instanceof TefkatObjectModelPlaceHolder) {
            return "description of something from model";
        }
        return null;
    }
}
