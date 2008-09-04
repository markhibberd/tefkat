package tefkat.engine.events;

public interface EventHandler {
    void handle(EventContext context, Class<?> type);
}
