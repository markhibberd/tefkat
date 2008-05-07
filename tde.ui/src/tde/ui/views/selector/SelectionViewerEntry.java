package tde.ui.views.selector;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import tde.ui.utils.Disposer;

/**
 * Naive viewer entry implementation for object selection model.
 */
//FIXME this is a nightmare, but i don't have time to look at for now
public class SelectionViewerEntry {
    private ICellEditorListener cellEditorListener = new SelectionViewerEntryEditorListener();
    private String name;
    private String value;
    private CellEditor editor;
    private Object featureValue;
    private EObject eobject;
    private SelectionViewer viewer;
    private List<SelectionViewerEntry> children = null;
    private TreeItem treeItem;

    public SelectionViewerEntry(SelectionViewer viewer, EObject e, EStructuralFeature f) {
        this.viewer = viewer;
        name = f.getName();
        Object featureValue = e.eGet(f);
        if (featureValue instanceof EObject) {
            eobject = (EObject) featureValue;
            value = eobject.eClass().getName();
        } else {
            value = featureValue != null ? featureValue.toString() : "";
        }
    }

    public static List<SelectionViewerEntry> getChildren(SelectionViewer viewer, EObject e) {
        EClass eclass = e.eClass();
        List<SelectionViewerEntry> result = new ArrayList<SelectionViewerEntry>();
        for (EStructuralFeature f : eclass.getEStructuralFeatures()) {
            result.add(new SelectionViewerEntry(viewer, e, f));
        }
        return result;
    }

    public String getName() { return name; }
    public String getValue() { return value; }
    public EObject getEObject() { return eobject; }
    public Object getFeatureValue() { return featureValue; }
    public String getDescription() { return name + " [" + featureValue + "]"; }

    public boolean hasChildEntries() { return eobject != null; }

    public void buildChildren() {
        if (!hasChildEntries()) return;
        children = getChildren(viewer, eobject);
    }

    public List<SelectionViewerEntry> getChildren() {
        if (children == null) buildChildren();
        return children;
    }

    public CellEditor getEditor(Composite parent) {
        if (editor == null) editor = newCellEditor(parent, newCellValidator());
        editor.setValue(value);
        return editor;
    }

    public void dispose() {
        Disposer.dispose(editor, treeItem);
        if (children != null) {
            for (SelectionViewerEntry child : children) Disposer.dispose(child);
        }
    }

    public void apply() {
        if (editor == null) return;
        if (!editor.isValueValid()) return;
        // FIXME may not always be a string one more editors are added
        value = (String) editor.getValue();
        populate(treeItem);
    }

    public CellEditor newCellEditor(Composite parent, ICellEditorValidator validator) {
        // TODO add support for more sophisticated cell editors (i.e. drop downs, wizards)
        CellEditor editor = new TextCellEditor(parent);
        if (validator != null) editor.setValidator(validator);
        editor.addListener(cellEditorListener); // FIXME this is leaking
        return editor;
    }

    public ICellEditorValidator newCellValidator() {
        // TODO add cell validators - either that or just zap the user everytime they get something wrong
        return null;
    }

    private class SelectionViewerEntryEditorListener implements ICellEditorListener {
        public void editorValueChanged(boolean oldValidState, boolean newValidState) {
            viewer.setError(newValidState ? null : editor.getErrorMessage());
        }

        public void cancelEditor() {
            viewer.setError(null);
        }

        public void applyEditorValue() {
            SelectionViewerEntry.this.apply();
        }
    }

    public void createTreeItem(Tree tree) {
        cleanup();
        treeItem = new TreeItem(tree, SWT.NONE);
        populate(treeItem);
    }

    public void createTreeItem(TreeItem parent) {
        treeItem = new TreeItem(parent, SWT.NONE);
        populate(treeItem);
    }

    private void cleanup() {
        if (treeItem != null && !treeItem.isDisposed()) treeItem.dispose();
    }

    private void populate(TreeItem ti) {
        if (ti == null) return;
        ti.setData(this);
        ti.setText(0, getName());
        ti.setText(1, getValue());
        if (hasChildEntries()) {
            new TreeItem(ti, SWT.NULL);
        }
    }
}
