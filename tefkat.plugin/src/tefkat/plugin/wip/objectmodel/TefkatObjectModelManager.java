package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;

import tefkat.plugin.TefkatPlugin;
import tefkat.plugin.wip.objectmodel.impl.TefkatObjectModelImpl;

public class TefkatObjectModelManager {
    private static final TefkatObjectModelManager SINGLE = new TefkatObjectModelManager();
    private static final TefkatObjectModelBuilder builder = new TefkatObjectModelBuilder();

    private final TefkatObjectModel model = new TefkatObjectModelImpl();

    public static TefkatObjectModelManager instance() {
        return SINGLE;
    }

    public TefkatObjectModel getModel() {
        return this.model;
    }

    public void init() {
        final IWorkspace workspace = TefkatPlugin.getWorkspace();
        workspace.addResourceChangeListener(new Changes(), IResourceChangeEvent.POST_CHANGE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.PRE_CLOSE);
        builder.build(model, workspace.getRoot());
    }

    private static class Changes implements IResourceChangeListener {
        public void resourceChanged(IResourceChangeEvent event) {
            // TODO implement me
//            System.err.println("== resource change listener ==");
//            System.err.println(event.getSource());
//            System.err.println(event.getDelta());
//            System.err.println(event.getResource());
//            System.err.println(event.getType());
//            System.err.println(event.getBuildKind());
//            System.err.println("== resource change listener ==");
        }
    }
}
