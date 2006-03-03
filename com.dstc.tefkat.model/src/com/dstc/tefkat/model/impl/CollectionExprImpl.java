/**
 * <copyright>
 * </copyright>
 *
 * $Id: CollectionExprImpl.java,v 1.2 2005/05/16 01:52:27 lawley Exp $
 */
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CollectionExpr;
import com.dstc.tefkat.model.CompoundExpr;
import com.dstc.tefkat.model.Expression;
import com.dstc.tefkat.model.TefkatFactory;
import com.dstc.tefkat.model.TefkatPackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.CollectionExprImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.CollectionExprImpl#isOrdered <em>Ordered</em>}</li>
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
    public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

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
        return TefkatPackage.eINSTANCE.getCollectionExpr();
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
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.COLLECTION_EXPR__UNIQUE, oldUnique, unique));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.COLLECTION_EXPR__ORDERED, oldOrdered, ordered));
    }

    /**
     * <!-- begin-user-doc -->
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        CollectionExpr copy = TefkatFactory.eINSTANCE.createCollectionExpr();
        copy.setOrdered(isOrdered());
        copy.setUnique(isUnique());
        List args = copy.getArg();
        for (Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            args.add(((Expression) itr.next()).copy());
        }
        return copy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.COLLECTION_EXPR__EXPR:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.COLLECTION_EXPR__EXPR, msgs);
                case TefkatPackage.COLLECTION_EXPR__ARG:
                    return ((InternalEList)getArg()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.COLLECTION_EXPR__EXPR:
                    return eBasicSetContainer(null, TefkatPackage.COLLECTION_EXPR__EXPR, msgs);
                case TefkatPackage.COLLECTION_EXPR__ARG:
                    return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
        if (eContainerFeatureID >= 0) {
            switch (eContainerFeatureID) {
                case TefkatPackage.COLLECTION_EXPR__EXPR:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.COMPOUND_EXPR__ARG, CompoundExpr.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.COLLECTION_EXPR__EXPR:
                return getExpr();
            case TefkatPackage.COLLECTION_EXPR__ARG:
                return getArg();
            case TefkatPackage.COLLECTION_EXPR__UNIQUE:
                return isUnique() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatPackage.COLLECTION_EXPR__ORDERED:
                return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.COLLECTION_EXPR__EXPR:
                setExpr((CompoundExpr)newValue);
                return;
            case TefkatPackage.COLLECTION_EXPR__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
                return;
            case TefkatPackage.COLLECTION_EXPR__UNIQUE:
                setUnique(((Boolean)newValue).booleanValue());
                return;
            case TefkatPackage.COLLECTION_EXPR__ORDERED:
                setOrdered(((Boolean)newValue).booleanValue());
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.COLLECTION_EXPR__EXPR:
                setExpr((CompoundExpr)null);
                return;
            case TefkatPackage.COLLECTION_EXPR__ARG:
                getArg().clear();
                return;
            case TefkatPackage.COLLECTION_EXPR__UNIQUE:
                setUnique(UNIQUE_EDEFAULT);
                return;
            case TefkatPackage.COLLECTION_EXPR__ORDERED:
                setOrdered(ORDERED_EDEFAULT);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.COLLECTION_EXPR__EXPR:
                return getExpr() != null;
            case TefkatPackage.COLLECTION_EXPR__ARG:
                return arg != null && !arg.isEmpty();
            case TefkatPackage.COLLECTION_EXPR__UNIQUE:
                return unique != UNIQUE_EDEFAULT;
            case TefkatPackage.COLLECTION_EXPR__ORDERED:
                return ordered != ORDERED_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
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

} //CollectionExprImpl
