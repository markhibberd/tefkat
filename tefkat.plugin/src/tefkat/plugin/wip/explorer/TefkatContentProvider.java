package tefkat.plugin.wip.explorer;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TefkatContentProvider implements ITreeContentProvider, IResourceChangeListener, IResourceDeltaVisitor {
    private static final Object[] NO_CHILDREN = new Object[0];

    public TefkatContentProvider() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    public Object[] getChildren(Object parentElement) {
        // TODO get children based upon tefkat object model
        return NO_CHILDREN;
    }

    public Object getParent(Object element) {
        // TODO if a part of tom return known parent
        return null;
    }

    public boolean hasChildren(Object element) {
        // TODO if a part of tom return known children
        return false;
    }

    public Object[] getElements(Object in) {
        return getChildren(in);
    }

    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

    public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
    }

    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();
        try {
            delta.accept(this);
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }
    public boolean visit(IResourceDelta delta) {
        // TODO update model;
        return false;
    }
}