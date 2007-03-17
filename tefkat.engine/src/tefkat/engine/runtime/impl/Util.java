package tefkat.engine.runtime.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

abstract class Util {

    private Util() {
        // no instances!
        throw new UnsupportedOperationException("Do not instantiate");
    }

    /**
     * Returns an object compatible with the type of eFeature if possible.
     * 
     * @param object
     * @param eFeature
     * @return
     */
    static Object coerceType(Object object, EStructuralFeature eFeature) {
        Object result;
        if ("java.lang.String".equals(eFeature.getEType().getInstanceClassName())) {
            result = String.valueOf(object);
        } else if (object instanceof Number) {
            int typeID = eFeature.getEType().getClassifierID();
            if (typeID == EcorePackage.EINTEGER_OBJECT|| typeID == EcorePackage.EINT) {
                result = new Integer(((Number) object).intValue());
            } else if (typeID == EcorePackage.ELONG_OBJECT || typeID == EcorePackage.ELONG) {
                result = new Long(((Number) object).longValue());
            } else if (typeID == EcorePackage.ESHORT_OBJECT || typeID == EcorePackage.ESHORT) {
                result = new Short(((Number) object).shortValue());
            } else if (typeID == EcorePackage.EFLOAT_OBJECT || typeID == EcorePackage.EFLOAT) {
                result = new Float(((Number) object).floatValue());
            } else if (typeID == EcorePackage.EDOUBLE_OBJECT || typeID == EcorePackage.EDOUBLE) {
                result = new Double(((Number) object).doubleValue());
            } else if (typeID == EcorePackage.EBYTE_OBJECT || typeID == EcorePackage.EBYTE) {
                result = new Byte(((Number) object).byteValue());
            } else if (typeID == EcorePackage.EBIG_INTEGER) {
                result = new BigInteger(String.valueOf(object));
            } else if (typeID == EcorePackage.EBIG_DECIMAL) {
                result = new BigDecimal(String.valueOf(object));
            } else {
                result = object;
            }
        } else {
            result = object;
        }
        return result;
    }

    static List coerceTypes(List l, EStructuralFeature eFeature) {
        List cl = new ArrayList(l.size());
        for (int i = 0; i < l.size(); i++) {
            cl.add(i, coerceType(l.get(i), eFeature));
        }
        return cl;
    }
    
}
