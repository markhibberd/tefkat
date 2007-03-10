/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class CastFloat implements Function {
    public Object call(Binding binding, Object[] params) {
        return Float.valueOf(String.valueOf(params[0]));
    }
}