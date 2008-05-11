package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.ResolutionException;

public final class MinCardinality implements Function {

    public Object call(Context ctxt, Binding unifier, Object[] params) throws ResolutionException {
        if (params.length != 4) {
            throw new ResolutionException(null, "min_cardinality expected 4 args, got " + params.length);
        }
        ctxt.error("NOT IMPLEMENTED: Assert " + params[0] + "." + params[1] + " = " + params[2] + " >= " + params[3]);
        return Boolean.TRUE;
    }
    
}
