/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.WrappedVar;
import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;

final public class AppendFunction implements Function {
    public Object call(Context ctxt, Binding binding, Object[] params) throws NotGroundException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof WrappedVar) {
                ctxt.delay("Cannot append unbound variable: " + params[i]);
            }
            sb.append(params[i]);
        }
        return sb.toString();
    }
}