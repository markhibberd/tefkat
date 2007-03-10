/**
 * 
 */
package tefkat.engine.runtime.functions;

import java.util.Arrays;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class SplitString implements Function {
    public Object call(Binding binding, Object[] params) {
        String string = String.valueOf(params[0]);
        String regex = String.valueOf(params[1]);
        return Arrays.asList(string.split(regex));
    }
}