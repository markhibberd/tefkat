/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.FunctionExpr;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.FunctionExprImpl#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionExprImpl extends CompoundExprImpl implements FunctionExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The default value of the '{@link #getFunction() <em>Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunction()
     * @generated
     * @ordered
     */
    protected static final String FUNCTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFunction() <em>Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunction()
     * @generated
     * @ordered
     */
    protected String function = FUNCTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FunctionExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.FUNCTION_EXPR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFunction() {
        return function;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunction(String newFunction) {
        String oldFunction = function;
        function = newFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.FUNCTION_EXPR__FUNCTION, oldFunction, function));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.FUNCTION_EXPR__FUNCTION:
                return getFunction();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RuntimePackage.FUNCTION_EXPR__FUNCTION:
                setFunction((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case RuntimePackage.FUNCTION_EXPR__FUNCTION:
                setFunction(FUNCTION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RuntimePackage.FUNCTION_EXPR__FUNCTION:
                return FUNCTION_EDEFAULT == null ? function != null : !FUNCTION_EDEFAULT.equals(function);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        final String functor = getFunction();
        StringBuffer result = new StringBuffer();
        result.append(functor);
        result.append('(');
        for (final Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            result.append(itr.next());
            if (itr.hasNext()) {
                result.append(", ");
            }
        }
        result.append(')');
        return result.toString();
    }

    public List eval(final Context context, final Binding binding) throws ResolutionException, NotGroundException {
        List values;
        String op = getFunction();
        List args = getArg();
        Function f = context.getFunction(op);
        try {
            if ("funmap".equals(op)) {
                f = new Function() {
                    public Object call(Binding binding, Object[] params) throws ResolutionException {
                        final Collection list = (Collection) params[0];
                        final String feature = (String) params[1];
                        final List result = new ArrayList();
                        
                        for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
                            Object obj = itr.next();
                            result.add(context.fetchFeature(feature, obj));
                        }
                    
                        return result;
                    }
                };
            }

            if (null != f) {
                final boolean collect = "collect".equals(op);
                if (collect || args.size() > 0) {
                    ExprExpander expander = new ExprExpander(context, f, binding, args, collect);
                    values = expander.getResults();
                } else {
                    values = new ArrayList();
                    values.add(f.call(binding, args.toArray()));
                }
            } else {
                throw new ResolutionException(null, "Unknown function: " + op);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ResolutionException(null, "Badly typed parameter(s) to function: " + op, e);
        } catch (RuntimeException e) {
            throw new ResolutionException(null, "Function evaluation failed: " + op, e);
        }
        return values;
    }

} //FunctionExprImpl
