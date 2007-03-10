/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class StripSuffix implements Function {
    public Object call(Binding binding, Object[] params) {
        String str = (String) params[0];
        String suffix = (String) params[1];
        String result = str;
        if (str.endsWith(suffix)) {
            result = str.substring(0, str.length() - suffix.length());
        }
        return result;
    }
}