/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 * $Id$
 */
package tefkat.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.PatternDefn;
import tefkat.model.PatternScope;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.PatternScopeImpl#getPatternDefn <em>Pattern Defn</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PatternScopeImpl extends VarScopeImpl implements PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached value of the '{@link #getPatternDefn() <em>Pattern Defn</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternDefn()
     * @generated
     * @ordered
     */
    protected EList patternDefn = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PatternScopeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getPatternScope();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPatternDefn() {
        if (patternDefn == null) {
            patternDefn = new EObjectContainmentWithInverseEList(PatternDefn.class, this, TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE);
        }
        return patternDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.PATTERN_SCOPE__VARS:
                    return ((InternalEList)getVars()).basicAdd(otherEnd, msgs);
                case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicAdd(otherEnd, msgs);
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
                case TefkatPackage.PATTERN_SCOPE__VARS:
                    return ((InternalEList)getVars()).basicRemove(otherEnd, msgs);
                case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicRemove(otherEnd, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.PATTERN_SCOPE__VARS:
                return getVars();
            case TefkatPackage.PATTERN_SCOPE__NAME:
                return getName();
            case TefkatPackage.PATTERN_SCOPE__COMMENTS:
                return getComments();
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return getPatternDefn();
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
            case TefkatPackage.PATTERN_SCOPE__VARS:
                getVars().clear();
                getVars().addAll((Collection)newValue);
                return;
            case TefkatPackage.PATTERN_SCOPE__NAME:
                setName((String)newValue);
                return;
            case TefkatPackage.PATTERN_SCOPE__COMMENTS:
                getComments().clear();
                getComments().addAll((Collection)newValue);
                return;
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
                getPatternDefn().addAll((Collection)newValue);
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
            case TefkatPackage.PATTERN_SCOPE__VARS:
                getVars().clear();
                return;
            case TefkatPackage.PATTERN_SCOPE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TefkatPackage.PATTERN_SCOPE__COMMENTS:
                getComments().clear();
                return;
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
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
            case TefkatPackage.PATTERN_SCOPE__VARS:
                return vars != null && !vars.isEmpty();
            case TefkatPackage.PATTERN_SCOPE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.PATTERN_SCOPE__COMMENTS:
                return comments != null && !comments.isEmpty();
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return patternDefn != null && !patternDefn.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //PatternScopeImpl
