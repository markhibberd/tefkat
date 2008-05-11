/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.EnumConstant;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Constant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EnumConstantImpl extends CompoundExprImpl implements EnumConstant {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumConstantImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.ENUM_CONSTANT;
    }

    public List eval(Context context, Binding binding) throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        List args = getArg();
        Expression enumExpr = (Expression) args.get(0);
        Expression literalExpr = (Expression) args.get(1);
        List enumObjs = enumExpr.eval(context);
        List literalObjs = literalExpr.eval(context);

        for (final Iterator eItr = enumObjs.iterator(); eItr.hasNext(); ) {
            Object eObj = eItr.next();
            EEnum enumeration = null;
            if (eObj instanceof String) {
                eObj = context.findClassifierByName((String) eObj);
            }
            if (eObj instanceof EEnum) {
                enumeration = (EEnum) eObj;
            }
            if (null != enumeration) {
                for (final Iterator lItr = literalObjs.iterator(); lItr.hasNext(); ) {
                    Object lObj = lItr.next();
                    if (lObj instanceof String) {
                        EEnumLiteral eLit = enumeration.getEEnumLiteral((String) lObj);
                        if (null != eLit) {
                            values.add(eLit.getInstance());
                        }
                    }
                }
            }
        }
        
        return values;
    }

} //EnumConstantImpl