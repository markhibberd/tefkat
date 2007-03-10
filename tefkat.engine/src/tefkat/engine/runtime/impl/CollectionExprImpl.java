/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.CollectionExpr;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.CollectionExprImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.CollectionExprImpl#isOrdered <em>Ordered</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionExprImpl extends CompoundExprImpl implements CollectionExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isUnique()
     * @generated
     * @ordered
     */
    protected static final boolean UNIQUE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isUnique()
     * @generated
     * @ordered
     */
    protected boolean unique = UNIQUE_EDEFAULT;

    /**
     * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOrdered()
     * @generated
     * @ordered
     */
    protected static final boolean ORDERED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOrdered()
     * @generated
     * @ordered
     */
    protected boolean ordered = ORDERED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CollectionExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.COLLECTION_EXPR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnique(boolean newUnique) {
        boolean oldUnique = unique;
        unique = newUnique;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.COLLECTION_EXPR__UNIQUE, oldUnique, unique));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOrdered(boolean newOrdered) {
        boolean oldOrdered = ordered;
        ordered = newOrdered;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.COLLECTION_EXPR__ORDERED, oldOrdered, ordered));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.COLLECTION_EXPR__UNIQUE:
                return isUnique() ? Boolean.TRUE : Boolean.FALSE;
            case RuntimePackage.COLLECTION_EXPR__ORDERED:
                return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
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
            case RuntimePackage.COLLECTION_EXPR__UNIQUE:
                setUnique(((Boolean)newValue).booleanValue());
                return;
            case RuntimePackage.COLLECTION_EXPR__ORDERED:
                setOrdered(((Boolean)newValue).booleanValue());
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
            case RuntimePackage.COLLECTION_EXPR__UNIQUE:
                setUnique(UNIQUE_EDEFAULT);
                return;
            case RuntimePackage.COLLECTION_EXPR__ORDERED:
                setOrdered(ORDERED_EDEFAULT);
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
            case RuntimePackage.COLLECTION_EXPR__UNIQUE:
                return unique != UNIQUE_EDEFAULT;
            case RuntimePackage.COLLECTION_EXPR__ORDERED:
                return ordered != ORDERED_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (unique: ");
        result.append(unique);
        result.append(", ordered: ");
        result.append(ordered);
        result.append(')');
        return result.toString();
    }

    final static Function COLLECT_FUNCTION = new Function() {
        public Object call(Binding unifier, Object[] params) {
            return Arrays.asList(params);
        }
    };


    public List eval(Context context, Binding binding) throws ResolutionException, NotGroundException {
        // FIXME - Yikes, the semantiocs of these is complicated
        //
        // Should X = [1, Y.foo] where Y.foo returns the collection [2, 3]
        // bind X to 1, 2, and 3 or to [1, 2, 3] or to [1, 2] and [1, 3] ?
        //
        // What about X = [1, Y.foo{}]?
        //
        // For the moment, we'll make X = [expr_1, ..., expr_k] equivalent
        // to X = union(expr_1, ..., expr_k) i.e., for the example above,
        // X would bind to 1, then 2, then 3.
        //
        
        ExprExpander expander = new ExprExpander(context, COLLECT_FUNCTION, binding, getArg(), true);
        List results = expander.getResults();
        
        return results;
    }

} //CollectionExprImpl