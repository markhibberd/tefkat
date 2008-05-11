/**
 * 
 */
package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

final public class Add implements Function {

    public Object call(Context ctxt, Binding binding, Object[] params) {
        Number lhs = (Number) params[0];
        Number rhs = (Number) params[1];
        if (lhs instanceof Float || rhs instanceof Float || lhs instanceof Double || rhs instanceof Double) {
            double lval = lhs.doubleValue();
            double rval = rhs.doubleValue();
            double result = lval + rval;
            if (result < Float.MAX_VALUE && result > Float.MIN_VALUE) {
                return new Float(result);
            } else {
                return new Double(result);
            }
        } else {
            long lval = lhs.longValue();
            long rval = rhs.longValue();
            long result = lval + rval;
            if (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE) {
                return new Integer((int) result);
            } else {
                return new Long(result);
            }
        }
    }
    
}