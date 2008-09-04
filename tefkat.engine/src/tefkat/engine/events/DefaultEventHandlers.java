package tefkat.engine.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultEventHandlers implements EventHandlers {
    private final List<EventHandler> handlers = new ArrayList<EventHandler>();
    private final Map<Class<?>, List<EventHandler>> registry = new HashMap<Class<?>, List<EventHandler>>();

    public void add(EventHandler handler) {
        handlers.add(handler);
    }

    public void add(Class<?> type, EventHandler handler) {
        List<EventHandler> list = get(type);
        list.add(handler);
    }

    private List<EventHandler> get(Class<?> type) {
        if (!registry.containsKey(type)) registry.put(type, new ArrayList<EventHandler>());
        return registry.get(type);
    }

    public List<EventHandler> grab(Class<?> type) {
        List<EventHandler> all = new ArrayList<EventHandler>();
        all.addAll(handlers);
        all.addAll(get(type));
        return all;
    }
}
