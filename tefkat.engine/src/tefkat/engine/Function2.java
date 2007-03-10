package tefkat.engine;

public interface Function2
    extends Function {
    public Object call(Context context, Binding binding, Object[] params) throws ResolutionException;
}
