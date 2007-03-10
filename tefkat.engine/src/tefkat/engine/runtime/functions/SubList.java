/**
 * 
 */
package tefkat.engine.runtime.functions;

import java.util.List;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class SubList implements Function {
    public Object call(Binding binding, Object[] params) {
        List list = (List) params[0];
        Number start = (Number) params[1];
        if (params.length > 2) {
            Number end = (Number) params[2];
            return list.subList(start.intValue(), end.intValue());
        } else {
            return list.subList(start.intValue(), list.size());
        }
    }
}