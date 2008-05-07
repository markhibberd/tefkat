package tde.popup.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class TDEQuestions implements IObjectActionDelegate {
    private ISelection selection;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) { }

    public void run(IAction action) {
        if (!(selection instanceof StructuredSelection)) return;
        StructuredSelection ss = (StructuredSelection) selection;

        Object o = ss.getFirstElement();
        if (!(o instanceof EObject)) return;



//        try {
//        Shell shell = new Shell();
//        MessageDialog.openInformation(
//            shell,
//            "Tefkat Debug Environment Plug-in",
//            "Selected: " + EObjectUtil.buildToString((EObject) o));
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
    }
    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }
}
