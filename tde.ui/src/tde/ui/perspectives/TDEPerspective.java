package tde.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import tde.TDEPlugin;
import tefkat.plugin.TefkatPlugin;

public class TDEPerspective implements IPerspectiveFactory {
    private static final String ID_CONSOLE_VIEW = "org.eclipse.ui.console.ConsoleView"; // FIXME this exists somewhere else, find me
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();

        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float) 0.26, editorArea);
        left.addView(TefkatPlugin.TEFKAT_EXPLORER);
        left.addView(IPageLayout.ID_RES_NAV);

        IFolderLayout bottomLeft = layout.createFolder("bottom.left", IPageLayout.BOTTOM, (float) 0.50, "left");
        bottomLeft.addView(TDEPlugin.ID_TDE_SELECTOR);
        bottomLeft.addView(IPageLayout.ID_OUTLINE);


        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.75, editorArea);
        bottom.addView(TDEPlugin.ID_TDE_WORKBENCH);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(IPageLayout.ID_TASK_LIST);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        bottom.addView(ID_CONSOLE_VIEW);

        // TODO decide right or bottom left - or both (diff for selector and outline)
//        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.50, editorArea);
//        right.addView(TDEPlugin.ID_TDE_SELECTOR);
//        right.addView(IPageLayout.ID_OUTLINE);

        layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
    }
}
