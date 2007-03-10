/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class IdentityFunction implements Function {
    public Object call(Binding binding, Object[] params) {
        return params[0];
    }
}