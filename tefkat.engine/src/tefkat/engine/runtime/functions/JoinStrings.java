/**
 * 
 */
package tefkat.engine.runtime.functions;

import java.util.Collection;
import java.util.Iterator;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

final public class JoinStrings implements Function {
    /**
     * concatenate a list of Strings interspersed with a separator
     * @params[0]   separator
     * @params[1..n]    the list of strings
     */
    public Object call(Context ctxt, Binding binding, Object[] params) {
        String separator = String.valueOf(params[0]);
        StringBuffer b = new StringBuffer();
        if (params.length == 2 && params[1] instanceof Collection) {
            for (Iterator itr = ((Collection) params[1]).iterator(); itr.hasNext(); ) {
                b.append(itr.next());
                if (itr.hasNext()) {
                    b.append(separator);
                }
            }
        } else if (params.length > 1) {
            b.append(params[1]);
            for (int i = 2; i < params.length; i++) {
                b.append(separator).append(params[i]);
            }
        }
        return b.toString();
    }
}