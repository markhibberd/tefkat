package tefkat.engine.executiontrace.events;

import tefkat.engine.events.EventContext;
import tefkat.engine.events.EventHandler;

public class Saver implements EventHandler {
    private final ExecutionTrace trace;

    public Saver(ExecutionTrace trace) {
        this.trace = trace;
    }

    public void handle(EventContext context, Class<?> type) {
        trace.save();
    }
}
