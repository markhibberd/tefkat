/**
 * <copyright>
 * </copyright>
 *
 * $Id: EnumConstantImpl.java,v 1.3 2005/05/19 05:51:41 lawley Exp $
 */
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CompoundExpr;
import com.dstc.tefkat.model.EnumConstant;
import com.dstc.tefkat.model.Expression;
import com.dstc.tefkat.model.TefkatFactory;
import com.dstc.tefkat.model.TefkatPackage;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

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
    public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

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
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        EnumConstant copy = TefkatFactory.eINSTANCE.createEnumConstant();
        EList argCopy = copy.getArg();
        for (final Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            Expression expr = (Expression) itr.next();
            argCopy.add(expr.copy());
        }
        return copy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getEnumConstant();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.ENUM_CONSTANT__EXPR:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.ENUM_CONSTANT__EXPR, msgs);
                case TefkatPackage.ENUM_CONSTANT__ARG:
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
                case TefkatPackage.ENUM_CONSTANT__EXPR:
                    return eBasicSetContainer(null, TefkatPackage.ENUM_CONSTANT__EXPR, msgs);
                case TefkatPackage.ENUM_CONSTANT__ARG:
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
                case TefkatPackage.ENUM_CONSTANT__EXPR:
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
            case TefkatPackage.ENUM_CONSTANT__EXPR:
                return getExpr();
            case TefkatPackage.ENUM_CONSTANT__ARG:
                return getArg();
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
            case TefkatPackage.ENUM_CONSTANT__EXPR:
                setExpr((CompoundExpr)newValue);
                return;
            case TefkatPackage.ENUM_CONSTANT__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
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
            case TefkatPackage.ENUM_CONSTANT__EXPR:
                setExpr((CompoundExpr)null);
                return;
            case TefkatPackage.ENUM_CONSTANT__ARG:
                getArg().clear();
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
            case TefkatPackage.ENUM_CONSTANT__EXPR:
                return getExpr() != null;
            case TefkatPackage.ENUM_CONSTANT__ARG:
                return arg != null && !arg.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //EnumConstantImpl
