package tefkat.engine;

public interface Function2
    extends Function {
    public Object call(Binding context, Object[] params) throws ResolutionException;
}
