package tefkat.engine.executiontrace.events;

import org.eclipse.emf.ecore.resource.ResourceSet;

import tefkat.engine.Evaluator;
import tefkat.engine.RuleEvaluator;
import tefkat.engine.events.EventBootstrapper;
import tefkat.engine.events.EventRegistry;
import tefkat.model.Query;
import tefkat.model.Transformation;

public class Bootstrap implements EventBootstrapper {
    // FIX MH System property?
    private final boolean enabled = true;

    public void bootstrap(EventRegistry events, ResourceSet resources) {
        if (!enabled) return;
        ExecutionTrace trace = new ExecutionTrace(resources);
        register(events, trace);
    }

    private void register(EventRegistry events, ExecutionTrace trace) {
        events.register(Evaluator.class, new ExpressionHandler(trace));
        events.register(RuleEvaluator.class, new TermHandler(trace));
        events.register(Transformation.class, new Saver(trace));
        events.register(Query.class, new Saver(trace));
    }
}
