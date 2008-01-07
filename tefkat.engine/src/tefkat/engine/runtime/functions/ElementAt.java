/**
 * 
 */
package tefkat.engine.runtime.functions;

import java.util.List;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

final public class ElementAt implements Function {
    public Object call(Context ctxt, Binding binding, Object[] params) {
        List list = (List) params[0];
        Number index = (Number) params[1];
        return list.get(index.intValue());
    }
}