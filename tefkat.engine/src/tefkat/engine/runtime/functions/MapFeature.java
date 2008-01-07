package tefkat.engine.runtime.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.ResolutionException;

/**
 * Takes a collection and a feature and returns a collection of the results
 * of fetching the feature from each of the input objects.
 * 
 * @author michaellawley
 */
public class MapFeature implements Function {

    public Object call(Context context, Binding unifier, Object[] params) throws ResolutionException {
        final Collection list = (Collection) params[0];
        final String feature = (String) params[1];
        final List items = new ArrayList();

        for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
            Object obj = itr.next();
            items.add(context.fetchFeature(feature, obj));
        }

        final ArrayList result = new ArrayList();
        result.add(items);
        return result;
    }

}
