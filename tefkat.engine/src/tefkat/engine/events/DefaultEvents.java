package tefkat.engine.events;

import org.eclipse.emf.ecore.resource.ResourceSet;

import tefkat.model.PatternScope;


public class DefaultEvents implements Events {
    private EventHandlers handlers = new DefaultEventHandlers();

    public synchronized void register(EventHandler handler) {
        handlers.add(handler);
    }

    public synchronized void register(Class<?> type, EventHandler handler) {
        handlers.add(type, handler);
    }

    public synchronized EventWriter nu(PatternScope scope, ResourceSet resources, EventBootstrapper... bootstrappers) {
        for (EventBootstrapper bootstrapper : bootstrappers) bootstrapper.bootstrap(this, resources);
        return new DefaultEventWriter(scope, handlers);
    }
}
