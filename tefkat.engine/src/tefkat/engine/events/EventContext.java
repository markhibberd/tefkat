package tefkat.engine.events;

public interface EventContext {
    <T> T resolve(Class<T> type);
}
