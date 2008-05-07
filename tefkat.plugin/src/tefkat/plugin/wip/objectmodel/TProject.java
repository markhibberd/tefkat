package tefkat.plugin.wip.objectmodel;

import org.eclipse.core.resources.IResource;

import tefkat.plugin.wip.objectmodel.impl.TFactoryImpl;
import tefkat.plugin.wip.objectmodel.impl.TProjectImpl;

public interface TProject extends TElement {
    TFactory<TProject> factory = new TFactoryImpl<TProject>(TProject.class) {
        public TProject nu(IResource resource, Object... args) { return new TProjectImpl(resource); }
    };

    void setObjectModel(TefkatObjectModel tefkatObjectModel);

    void add(TConfig config);


}
