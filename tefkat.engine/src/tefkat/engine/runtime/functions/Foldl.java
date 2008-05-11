package tefkat.engine.runtime.functions;

import java.util.Collection;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;

public class Foldl implements Function {

    public Object call(Context context, Binding unifier, Object[] params) throws ResolutionException, NotGroundException {
        final String function = (String) params[0];
        Object result = params[1];
        final Collection collection = (Collection) params[2];
        
        final Function func = context.getFunction(function);
        for (Object param: collection) {
            Object[] args = {result, param};
            result = func.call(context, unifier, args);
        }
        return result;
    }

}
