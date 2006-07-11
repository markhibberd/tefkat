package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import tefkat.model.PatternVar;

abstract class EngineUtils {

    private EngineUtils() {
        throw new Error("This class should not be instantiated");
    }

    /**
     * Create a Binding for each combination of values in the List of Lists ls.
     * 
     * @param context
     * @param actualsList
     * @param formals
     * @throws ResolutionException
     */
    protected static Collection createBindings(Binding context, List actualsList, List formals)
    throws ResolutionException {
        Collection bindings = null;
        if (actualsList.size() > 0) {
            bindings = new ArrayList();
            createBindings(context, actualsList, formals, bindings, 0, new Object[actualsList.size()]);
        }
        return bindings;
    }

    private static void createBindings(Binding context, List actualsList,
            List formals, Collection bindings, int i, Object[] params)
            throws ResolutionException {
        Collection actuals = (Collection) actualsList.get(i);
        if (i < actualsList.size() - 1) {
            for (Iterator itr = actuals.iterator(); itr.hasNext();) {
                params[i] = itr.next();
                createBindings(context, actualsList, formals, bindings, i + 1, params);
            }
        } else {
            for (Iterator itr = actuals.iterator(); itr.hasNext();) {
                params[i] = itr.next();
    
                Binding newContext = new Binding(context);
                boolean debugFlag = false;
                for (int j = 0; j < params.length; j++) {
                    // only values are passed in, not references (i.e., Vars)
                    // since we get WrappedVars for unbound inputs as well as for
                    // vars bound to other vars
                    // FIXME - the above is bogus (I think) at least need to
                    // propagate var bindings when calculating the actualsList
                    // to handle, for example, p(X, X) and also p(X.foo, X.bar)
                    // -- the latter involves BindingPairs
                    if (params[j] instanceof BindingPair) {
                        BindingPair bp = (BindingPair) params[j];
                        System.err.println("BP: " + bp);
                        System.err.println("BP: " + formals.get(j) + " = " + bp.getValue());
                        newContext.composeRight(bp);
                        newContext.add((PatternVar) formals.get(j), bp.getValue());
                        debugFlag = true;
                    } else
                    if (true || !(params[j] instanceof WrappedVar)) {
                        newContext.add((PatternVar) formals.get(j), params[j]);
                    }
                }
    
                if (debugFlag) {
                    System.err.println("=================");
                    System.err.println(context);
                    System.err.println("-----------------");
                    System.err.println(newContext);
                }
                bindings.add(newContext);
            }
        }
    }
        
}
