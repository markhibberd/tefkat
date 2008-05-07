package tde.ui.views.selector;

import static tde.ui.utils.Toolbaro.createToolbar;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tde.TDEPlugin;

public class TDESelector extends ViewPart {
    private SelectionViewer viewer;
    private Selectortron selectortron;
    private EObject current;

    private Label label;
    private Text text;

    public void createPartControl(Composite parent) {
        addSelectionListener();

        createActions();

        parent.setLayout(twoCoulmGrid());

        label = new Label(parent, SWT.NORMAL);
        label.setText("eClass: ");

        text = new Text(parent, SWT.NORMAL);
        text.setLayoutData(fillHorizontal());

        viewer = new SelectionViewer(parent);
        viewer.setStatusLineManager(getStatusLineManager());
        viewer.getControl().setLayoutData(fillVertical());

        getSite().setSelectionProvider(viewer);
    }

    public void dispose() {
        super.dispose();
        ISelectionService selector = getSite().getWorkbenchWindow().getSelectionService();
        if (selectortron != null) selector.removeSelectionListener(selectortron);
    }

    public void setFocus() {
        if (viewer != null) viewer.getControl().setFocus();
    }

    private GridLayout twoCoulmGrid() {
        GridLayout layout = new GridLayout(2, false);
        layout.numColumns = 2;
        return layout;
    }

    private GridData fillHorizontal() {
        GridData fillh = new GridData(GridData.FILL_HORIZONTAL);
        return fillh;
    }

    private GridData fillVertical() {
        GridData fillhv = new GridData(GridData.FILL_BOTH);
        fillhv.horizontalSpan = 2;
        return fillhv;
    }

    private IStatusLineManager getStatusLineManager() {
        return getViewSite().getActionBars().getStatusLineManager();
    }

    private void createActions() {
        IAction grab = new Action() { public void run() { grab(); } };
        grab.setImageDescriptor(TDEPlugin.getImageDescriptor("icons/link_obj.gif"));

        IAction refresh = new Action() { public void run() { viewer.reload(); } };
        refresh.setImageDescriptor(TDEPlugin.getImageDescriptor("icons/refresh_tab.gif"));

        IAction clear = new Action() { public void run() { clear(); } };
        clear.setImageDescriptor(TDEPlugin.getImageDescriptor("icons/delete_obj.gif"));

        IAction save = new Action() { public void run() { save(); } };
        save.setImageDescriptor(TDEPlugin.getImageDescriptor("icons/step_done.gif"));

        createToolbar(getViewSite(), grab, save, refresh, clear);
    }

    private void save() {
        viewer.select();

    }
    private void grab() {
        System.err.println("selected: " + current);
        if (current == null) {
            Shell shell = new Shell();
            MessageDialog.openInformation(shell, "Tefkat Debug Environment Plug-in", "Nothing selected, can not link object.");
            return;
        }
        populateGrabbedObject();
    }

    private void populateGrabbedObject() {
        EClass eclass = current.eClass();
        text.setText(eclass.getName());
        viewer.setInput(current);
    }

    private void addSelectionListener() {
        ISelectionService selector = getSite().getWorkbenchWindow().getSelectionService();
        selectortron = new Selectortron();
        selector.addSelectionListener(selectortron); // FIXME should be a post selection listener,
    }

    private void clear() {
        viewer.clear();
        text.setText("");
    }
    private class Selectortron implements ISelectionListener {
        public void selectionChanged(IWorkbenchPart part, ISelection s) {
            if (!(s instanceof IStructuredSelection)) {
                return;
            }
            IStructuredSelection selection = (IStructuredSelection) s;
            if (selection.size() != 1) {
                current = null;
                return; // TODO currently only handle single select
            }
            Object o = selection.getFirstElement();
            if (!(o instanceof EObject)) {
                current = null;
                return;
            }
            current = (EObject) o;
        }
    }
}
