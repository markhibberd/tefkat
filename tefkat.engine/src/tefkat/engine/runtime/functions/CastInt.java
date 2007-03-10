/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class CastInt implements Function {
    public Object call(Binding binding, Object[] params) {
        return Integer.decode(String.valueOf(params[0]));
    }
}