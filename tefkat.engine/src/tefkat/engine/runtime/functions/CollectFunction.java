/**
 * 
 */
package tefkat.engine.runtime.functions;

import java.util.Arrays;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

final public class CollectFunction implements Function {
    public Object call(Context ctxt, Binding binding, Object[] params) {
        return Arrays.asList(params);
    }
}