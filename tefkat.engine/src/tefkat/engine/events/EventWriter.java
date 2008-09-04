package tefkat.engine.events;

public interface EventWriter {
    void write(Class<?> type, EventBinding... bindings);
}
