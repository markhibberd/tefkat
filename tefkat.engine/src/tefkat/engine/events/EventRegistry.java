package tefkat.engine.events;

public interface EventRegistry {
    void register(EventHandler handler);
    void register(Class<?> type, EventHandler handler);
}
