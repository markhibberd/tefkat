package tefkat.engine.runtime.impl;

import java.util.Iterator;
import java.util.List;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.WrappedVar;

final public class VarExpander {
    
    interface Function {
        public void call(Context context, Binding unifier) throws ResolutionException;
    }
    
    final private Context context;
    final private List vars;
    final private Function function;

    /**
     * 
     * @param context
     * @param vars List of Vars to lookup in unifier
     * @param function
     * @throws ResolutionException 
     * @throws NotGroundException 
     */
    VarExpander(Context context, List vars, Function function, Binding unifier)
    throws NotGroundException, ResolutionException {
        this.context = context;
        this.vars = vars;
        this.function = function;
        
        expandVars(0, unifier);
    }
    
    private void expandVars(final int idx, final Binding unifier)
    throws NotGroundException, ResolutionException {
        if (idx == vars.size()) {
            // reached end of list of vars, now do the work
            function.call(context, unifier);
            return;
        }

        Var var = (Var) vars.get(idx);
        Object value = unifier.lookup(var);
        if (value instanceof WrappedVar) {
            List objs = context.expand((WrappedVar) value);
            for (final Iterator itr = objs.iterator(); itr.hasNext(); ) {
                Object obj = itr.next();
                Binding unifier2 = new Binding(unifier);
                unifier2.add(var, obj);
                expandVars(idx + 1, unifier2);
            }
        } else {
            expandVars(idx + 1, unifier);
        }
    }
}
