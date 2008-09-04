package tde.ui.views.workbench;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tde.TDEPlugin;
import tde.ui.views.selector.SelectionViewerSelection;
import tde.ui.views.workbench.selectionmodel.EObjectNode;
import tde.ui.views.workbench.selectionmodel.RootNode;
import tefkat.plugin.TefkatPlugin;

public class TDEWorkbench extends ViewPart implements ISelectionListener {
    private EObject selected;
    private TreeViewer selectionViewer;
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());

        GridLayout grid = new GridLayout(2, false);
        Group questions = new Group(parent, SWT.BORDER);
        questions.setText("Debugging Questions");
        Group determinism = new Group(parent, SWT.BORDER);
        determinism.setText("Show Determinism");
        Group branches = new Group(parent, SWT.BORDER);
        branches.setText("Branch Highlighting");
        Group creation = new Group(parent, SWT.BORDER);
        creation.setText("Slice By Object");
        Group selection = new Group(parent, SWT.BORDER);
        selection.setText("Object Selection");

        questions.setLayout(new FormLayout());
        branches.setLayout(grid);
        creation.setLayout(grid);
        determinism.setLayout(grid);
        selection.setLayout(new FormLayout());

        ListViewer questionList = new ListViewer(questions, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        // TODO questions
//        questionList.add(new String[] {"Why are there no objects where _?"});
//        questionList.add(new String[] {"Why so many objects where _?"});
//        questionList.add(new String[] {"Who is responsible for objects where _?"});
//        questionList.add(new String[] {"What target objects were realised for source objects where _?"});
//        questionList.add(new String[] {"What target objects where _ did not realise source objects where _?"});


        final Button enableDeterminism = new Button(determinism, SWT.CHECK);
        Label enableDeterminismLabel = new Label(determinism, SWT.NONE);
        enableDeterminismLabel.setText("View determinism.");

        final Button enableBranches = new Button(branches, SWT.CHECK);
        Label enableBranchesLabel = new Label(branches, SWT.NONE);
        enableBranchesLabel.setText("Enable branch colouring.");


        final Button enableCreation = new Button(creation, SWT.CHECK);
        Label enableCreationLabel = new Label(creation, SWT.NONE);
        enableCreationLabel.setText("Enable creation colouring.");

        final Button enableFold = new Button(creation, SWT.PUSH);
        enableFold.setText("Fold on selection");
        GridData gridSpan = new GridData();
        gridSpan.grabExcessHorizontalSpace = true;
        gridSpan.horizontalSpan = 2;
        enableFold.setLayoutData(gridSpan);

        selectionViewer = new TreeViewer(selection);
        selectionViewer.setContentProvider(new SelectionContentProvider());
        selectionViewer.setLabelProvider(new SelectionLabelProvider());

        FormData qData = new FormData();
        qData.top = new FormAttachment(0, 5);
        qData.bottom = new FormAttachment(100, -5);
        qData.left = new FormAttachment(0, 5);
        qData.right = new FormAttachment(50, 0);
        questions.setLayoutData(qData);

        FormData dData = new FormData();
        dData.top = new FormAttachment(0, 5);
        dData.bottom = new FormAttachment(33, 0);
        dData.left = new FormAttachment(questions);
        dData.right = new FormAttachment(75, 0);
        determinism.setLayoutData(dData);

        FormData bData = new FormData();
        bData.top = new FormAttachment(determinism, 5);
        bData.bottom = new FormAttachment(66, 0);
        bData.left = new FormAttachment(questions, 5);
        bData.right = new FormAttachment(75, 0);
        branches.setLayoutData(bData);

        FormData cData = new FormData();
        cData.top = new FormAttachment(branches, 5);
        cData.bottom = new FormAttachment(100, -5);
        cData.left = new FormAttachment(questions, 5);
        cData.right = new FormAttachment(75, 0);
        creation.setLayoutData(cData);

        FormData sData = new FormData();
        sData.top = new FormAttachment(0, 5);
        sData.bottom = new FormAttachment(100, -5);
        sData.left = new FormAttachment(branches, 5);
        sData.right = new FormAttachment(100, -5);
        selection.setLayoutData(sData);

        FormData complete = new FormData();
        complete.top = new FormAttachment(0, 5);
        complete.bottom = new FormAttachment(100, -5);
        complete.left = new FormAttachment(0, 5);
        complete.right = new FormAttachment(100, 0);
        questionList.getControl().setLayoutData(complete);
        selectionViewer.getControl().setLayoutData(complete);

        enableBranches.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
//                AnnotatingStatsListener.setEnabled(enableBranches.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.AnnotatingStatsListener");
                setEnabled(clazz, enableBranches.getSelection());
            }

            public void widgetSelected(SelectionEvent e) {
//              AnnotatingStatsListener.setEnabled(enableBranches.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.AnnotatingStatsListener");
                setEnabled(clazz, enableBranches.getSelection());
                if (enableBranches.getSelection()) {
                	enableDeterminism.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.DeterminismThingo");
                    setEnabled(clazz, enableDeterminism.getSelection());
                    enableCreation.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.ObjectCreationListener");
                    setEnabled(clazz, enableCreation.getSelection());
                }
            }
        });

        enableCreation.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
//              ObjectCreationListener.setEnabled(enableCreation.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.ObjectCreationListener");
                setEnabled(clazz, enableCreation.getSelection());
            }
            public void widgetSelected(SelectionEvent e) {
//                ObjectCreationListener.setEnabled(enableCreation.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.ObjectCreationListener");
                setEnabled(clazz, enableCreation.getSelection());

                if (enableCreation.getSelection()) {
                	enableDeterminism.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.DeterminismThingo");
                    setEnabled(clazz, enableDeterminism.getSelection());
                    enableBranches.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.AnnotatingStatsListener");
                    setEnabled(clazz, enableBranches.getSelection());
                }
            }
        });

        enableDeterminism.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
//              ObjectCreationListener.setEnabled(enableCreation.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.DeterminismThingo");
                setEnabled(clazz, enableDeterminism.getSelection());
            }
            public void widgetSelected(SelectionEvent e) {
//                ObjectCreationListener.setEnabled(enableCreation.getSelection());
                Class<?> clazz = c4n("tefkat.plugin.stats.DeterminismThingo");
                setEnabled(clazz, enableDeterminism.getSelection());

                if (enableDeterminism.getSelection()) {
                    enableBranches.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.AnnotatingStatsListener");
                    setEnabled(clazz, enableBranches.getSelection());
                    enableCreation.setSelection(false);
                    clazz = c4n("tefkat.plugin.stats.ObjectCreationListener");
                    setEnabled(clazz, enableCreation.getSelection());
                }
            }
        });
        getViewSite().getPage().addSelectionListener(TDEPlugin.ID_TDE_SELECTOR, this);
    }

    public void setFocus() {

    }

    private static void setEnabled(Class<?> clazz, boolean selection) {
        try {
            Method m = clazz.getMethod("setEnabled", boolean.class);
            m.invoke(null, selection);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    private static void setInterested(Class<?> clazz, EObject e) {
        try {
            Method m = clazz.getMethod("setInterested", EObject.class);
            m.invoke(null, e);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    private static Class<?> c4n(String name) {
        try {
            ClassLoader cl = TefkatPlugin.class.getClassLoader();
            return Class.forName(name, true, cl);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    public void selectionChanged(IWorkbenchPart part, ISelection generic) {
        if (generic instanceof SelectionViewerSelection) {
            SelectionViewerSelection selection = (SelectionViewerSelection) generic;
            selected = selection.selected();

            RootNode root = new RootNode();
            root.set(new EObjectNode(root, selected));
            selectionViewer.setInput(root);

            Class<?> clazz = c4n("tefkat.plugin.stats.ObjectCreationListener");
            setInterested(clazz, selected);
        }
    }
}
