package tefkat.engine.events;

import tefkat.model.PatternScope;


public class DefaultEventWriter implements EventWriter {
    private final EventHandlers handlers;
    private final PatternScope scope;

    public DefaultEventWriter(PatternScope scope, EventHandlers handlers) {
        this.handlers = handlers;
        this.scope = scope;
    }

    public void write(Class<?> type, EventBinding... bindings) {
        EventContext context = new BoundEventContext(scope, bindings);
        for (EventHandler handler : handlers.grab(type)) safe(type, context, handler);
    }

    private void safe(Class<?> type, EventContext context, EventHandler handler) {
        try {
            handler.handle(context, type);
        } catch (Throwable t) {
            // FIX MH debugging.
            System.err.println("Error in handler(" + handler.getClass() + "): " + t.getMessage());
            t.printStackTrace();
        }
    }
}
