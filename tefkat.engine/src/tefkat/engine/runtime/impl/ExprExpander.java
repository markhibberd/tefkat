package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.Function;
//import tefkat.engine.runtime.Function;
//import tefkat.engine.runtime.Function2;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;

final class ExprExpander {

    final private List results = new ArrayList();
    
    final private Context context;
    final private Function f;
    final private List actuals;
    final private Object[] params;
    final private boolean collect;

    /**
     * 
     * @param function Function to call for each set of actual parameter values
     * @param unifier outer Binding context for the calls
     * @param actuals List of Expressions to evaluate to obtain the parameter values
     * @param collect true means preserve nesting of result values
     * @throws NotGroundException
     * @throws ResolutionException
     */
    ExprExpander(Context context, Function function, Binding unifier, List actuals, boolean collect)
    throws NotGroundException, ResolutionException {
        this.context = context;
        this.f = function;
        this.actuals = actuals;
        this.collect = collect;

        params = new Object[actuals.size()];

        expandParams(unifier, 0);
    }

    private void expandParams(Binding binding, int i)
    throws NotGroundException, ResolutionException {
        if (i == params.length) {
            Object result = f.call(binding, params);
            if (null != result) {
                if (!collect && result instanceof Collection) { 
                    results.addAll((Collection) result);
                } else {
                    results.add(result);
                }
            } else {
                context.warn("Function call returned null: " + f + "(" + params + ")");
            }
        } else {
            List values = ((Expression) actuals.get(i)).eval(context, binding);
            for (final Iterator itr = values.iterator(); itr.hasNext(); ) {
                Object obj = itr.next();
                Binding newUnifier;
                if (obj instanceof BindingPair) {
                    newUnifier = new Binding(binding);
                    newUnifier.composeRight((BindingPair) obj);
                    obj = ((BindingPair) obj).getValue();

                    System.err.println(params[i] + " = " + obj);
                    System.err.println(binding);
                    System.err.println(newUnifier);
                } else {
                    newUnifier = binding;
                }
                params[i] = obj;
                expandParams(newUnifier, i+1);
            }
        }
    }

    List getResults() {
        return results;
    }
}
