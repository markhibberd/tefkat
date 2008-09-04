package tefkat.engine.events;

import org.eclipse.emf.ecore.resource.ResourceSet;

public interface EventBootstrapper {

    void bootstrap(EventRegistry defaultEvents, ResourceSet resources);

}
