/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Function;

final public class AppendFunction implements Function {
    public Object call(Binding binding, Object[] params) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]);
        }
        return sb.toString();
    }
}