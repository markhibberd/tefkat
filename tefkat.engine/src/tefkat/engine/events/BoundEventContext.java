package tefkat.engine.events;

import java.util.HashMap;
import java.util.Map;

import tefkat.model.PatternScope;

public class BoundEventContext implements EventContext {
    private final Map<Class<?>, Object> bindings = new HashMap<Class<?>, Object>();

    public BoundEventContext(PatternScope scope, EventBinding[] in) {
        bindings.put(PatternScope.class, scope);
        for (EventBinding binding : in) {
            bindings.put(binding.type, binding.ref);
        }
    }

    public <T> T resolve(Class<T> type) {
        Object object = bindings.get(type);
        return type.cast(object);
    }
}
