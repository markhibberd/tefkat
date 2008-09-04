package tefkat.engine.events;

public class EventBinding {
    public final Class<?> type;
    public final Object ref;

    EventBinding(Class<?> type, Object ref) {
        this.type = type;
        this.ref = ref;
    }
}
