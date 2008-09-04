package tefkat.engine.events;

import org.eclipse.emf.ecore.resource.ResourceSet;

import tefkat.model.PatternScope;

public interface EventLifecycle {
    EventWriter nu(PatternScope scope, ResourceSet resources, EventBootstrapper... bootstrappers);
}
