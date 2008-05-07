package tefkat.plugin.wip.objectmodel.impl;

import tefkat.plugin.wip.objectmodel.TFactory;

public abstract class TFactoryImpl<T> implements TFactory<T> {
    private final Class<T> underlier;

    public TFactoryImpl(Class<T> underlier) {
        this.underlier = underlier;
    }

    protected T cast(Object o) {
        return underlier.cast(o);
    }
}
