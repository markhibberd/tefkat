package tefkat.engine.runtime.functions;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;

public class Sum implements Function {

    public Object call(Context context, Binding unifier, Object[] params) {
        Number[] collection = (Number[]) params[0];
        boolean integral = true;
        for (int i = 0; i < collection.length && integral; i++) {
            integral |= collection[i] instanceof Double;
        }
        if (integral) {
            long result = 0;
            for (int i = 0; i < collection.length && integral; i++) {
                result += collection[i].longValue();
            }
            return result;
        } else {
            double result = 0;
            for (int i = 0; i < collection.length && integral; i++) {
                result += collection[i].doubleValue();
            }
            return result;
        }
    }

}
