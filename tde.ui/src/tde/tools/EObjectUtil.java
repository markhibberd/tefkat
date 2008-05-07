package tde.tools;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EObjectUtil {
    public static String buildToString(EObject e) {
        EClass eclass = e.eClass();
        String className = eclass.getName();
        StringBuilder attribs = new StringBuilder();

        for (EStructuralFeature f : eclass.getEStructuralFeatures()) {
            attribs.append(f.getName());
            attribs.append("=");
            Object param = e.eGet(f);
            if (param instanceof EObject) {
                attribs.append(((EObject) param).eClass().getName());
            } else {
                attribs.append(param);
            }
            attribs.append(",");
        }
        if (eclass.getFeatureCount() > 0) attribs.setLength(attribs.length() - 1);

        return className + "("+ attribs + ")";

    }
}
