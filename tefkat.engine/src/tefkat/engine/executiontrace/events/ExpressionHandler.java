package tefkat.engine.executiontrace.events;

import java.util.List;

import tefkat.engine.Context;
import tefkat.engine.events.EventContext;
import tefkat.engine.events.EventHandler;

public class ExpressionHandler implements EventHandler {
    private final ExecutionTrace trace;

    public ExpressionHandler(ExecutionTrace trace) {
        this.trace = trace;
    }

    public void handle(EventContext context, Class<?> type) {
        trace.crude("Evaluated in context: " + context.resolve(Context.class));
        trace.crude("                  to: " + context.resolve(List.class));
        // FIX MH list expression and other info here...
        trace.trace("Expression evaluation", "some random info");
    }
}
