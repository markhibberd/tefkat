package tefkat.plugin.wip.explorer.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public class TefkatActionProvider extends CommonActionProvider {
    private PlaceholderAction action;

    public void init(ICommonActionExtensionSite aSite) {
        action = new PlaceholderAction();
    }

    public void fillActionBars(IActionBars actionBars) {
        if(action.isEnabled()) actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, action);
    }

    public void fillContextMenu(IMenuManager menu) {
        if(action.isEnabled()) menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, action);
    }


}
