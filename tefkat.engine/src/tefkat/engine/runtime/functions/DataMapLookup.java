package tefkat.engine.runtime.functions;

import java.util.List;

import tefkat.data.DataMap;
import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.SimpleExpr;

final public class DataMapLookup implements Function {
    
    public Object call(Binding binding, Object[] params) throws ResolutionException {
        DataMap dataMap = (DataMap) params[0];
        String key = String.valueOf(params[1]);
        Object result = dataMap.getValue().get(key);
        if (result instanceof SimpleExpr) {
            try {
                // only valid Expressions are those that don't need a Context
                List vals = ((SimpleExpr) result).eval(null, binding);
                if (vals.size() == 1) {
                    result = vals.get(0);
                } else {
                    result = vals;
                }
            } catch (ResolutionException e) {
                throw new ResolutionException(null, "Map expression '" + result + "' evaluation failed", e);
            } catch (NotGroundException e) {
                throw new ResolutionException(null, "Map expression '" + result + "' should not contain variable(s)", e);
            }
        }
        return result;
    }

    public Object call(Context context, Object[] params) throws ResolutionException {
        throw new UnsupportedOperationException("Wrong method called. Should have been call(Context, Binding, Object[])");
    }

}
