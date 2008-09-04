package tefkat.engine.events;

import java.util.List;

public interface EventHandlers {
    void add(EventHandler handler);
    void add(Class<?> type, EventHandler handler);
    List<EventHandler> grab(Class<?> type);
}
