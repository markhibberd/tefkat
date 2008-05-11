/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.RealConstant;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Real Constant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class RealConstantImpl extends SimpleExprImpl implements RealConstant {
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
    protected RealConstantImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.REAL_CONSTANT;
    }

    public List eval(Context context, Binding binding) throws ResolutionException, NotGroundException {
        return Arrays.asList(new Double[] {Double.valueOf(getRepresentation())});
    }

} //RealConstantImpl