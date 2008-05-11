/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

final public class IdentityFunction implements Function {
    public Object call(Context ctxt, Binding binding, Object[] params) {
        return params[0];
    }
}