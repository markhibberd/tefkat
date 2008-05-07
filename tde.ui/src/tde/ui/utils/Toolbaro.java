package tde.ui.utils;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IViewSite;

public class Toolbaro {
    public static void createToolbar(IViewSite viewSite, IAction... actions) {
        IToolBarManager mgr = viewSite.getActionBars().getToolBarManager();
        for (IAction action : actions) mgr.add(action);
    }
}
