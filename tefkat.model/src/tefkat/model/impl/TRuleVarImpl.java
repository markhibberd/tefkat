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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.TRuleVar;
import tefkat.model.TefkatPackage;
import tefkat.model.VarScope;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TRule Var</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.TRuleVarImpl#getExtender <em>Extender</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleVarImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleVarImpl#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleVarImpl#getSuperseded <em>Superseded</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TRuleVarImpl extends AbstractVarImpl implements TRuleVar {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached value of the '{@link #getExtender() <em>Extender</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtender()
     * @generated
     * @ordered
     */
    protected EList extender = null;

    /**
     * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtended()
     * @generated
     * @ordered
     */
    protected EList extended = null;

    /**
     * The cached value of the '{@link #getSuperseder() <em>Superseder</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseder()
     * @generated
     * @ordered
     */
    protected EList superseder = null;

    /**
     * The cached value of the '{@link #getSuperseded() <em>Superseded</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseded()
     * @generated
     * @ordered
     */
    protected EList superseded = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TRuleVarImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTRuleVar();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtender() {
        if (extender == null) {
            extender = new EObjectWithInverseResolvingEList.ManyInverse(TRuleVar.class, this, TefkatPackage.TRULE_VAR__EXTENDER, TefkatPackage.TRULE_VAR__EXTENDED);
        }
        return extender;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtended() {
        if (extended == null) {
            extended = new EObjectWithInverseResolvingEList.ManyInverse(TRuleVar.class, this, TefkatPackage.TRULE_VAR__EXTENDED, TefkatPackage.TRULE_VAR__EXTENDER);
        }
        return extended;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseder() {
        if (superseder == null) {
            superseder = new EObjectWithInverseResolvingEList.ManyInverse(TRuleVar.class, this, TefkatPackage.TRULE_VAR__SUPERSEDER, TefkatPackage.TRULE_VAR__SUPERSEDED);
        }
        return superseder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseded() {
        if (superseded == null) {
            superseded = new EObjectWithInverseResolvingEList.ManyInverse(TRuleVar.class, this, TefkatPackage.TRULE_VAR__SUPERSEDED, TefkatPackage.TRULE_VAR__SUPERSEDER);
        }
        return superseded;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRULE_VAR__SCOPE:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRULE_VAR__SCOPE, msgs);
                case TefkatPackage.TRULE_VAR__USAGES:
                    return ((InternalEList)getUsages()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__EXTENDER:
                    return ((InternalEList)getExtender()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__EXTENDED:
                    return ((InternalEList)getExtended()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__SUPERSEDER:
                    return ((InternalEList)getSuperseder()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__SUPERSEDED:
                    return ((InternalEList)getSuperseded()).basicAdd(otherEnd, msgs);
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
                case TefkatPackage.TRULE_VAR__SCOPE:
                    return eBasicSetContainer(null, TefkatPackage.TRULE_VAR__SCOPE, msgs);
                case TefkatPackage.TRULE_VAR__USAGES:
                    return ((InternalEList)getUsages()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__EXTENDER:
                    return ((InternalEList)getExtender()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__EXTENDED:
                    return ((InternalEList)getExtended()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__SUPERSEDER:
                    return ((InternalEList)getSuperseder()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRULE_VAR__SUPERSEDED:
                    return ((InternalEList)getSuperseded()).basicRemove(otherEnd, msgs);
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
                case TefkatPackage.TRULE_VAR__SCOPE:
                    return eContainer.eInverseRemove(this, TefkatPackage.VAR_SCOPE__VARS, VarScope.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRULE_VAR__SCOPE:
                return getScope();
            case TefkatPackage.TRULE_VAR__NAME:
                return getName();
            case TefkatPackage.TRULE_VAR__USAGES:
                return getUsages();
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return getExtender();
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return getExtended();
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return getSuperseder();
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return getSuperseded();
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
            case TefkatPackage.TRULE_VAR__SCOPE:
                setScope((VarScope)newValue);
                return;
            case TefkatPackage.TRULE_VAR__NAME:
                setName((String)newValue);
                return;
            case TefkatPackage.TRULE_VAR__EXTENDER:
                getExtender().clear();
                getExtender().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE_VAR__EXTENDED:
                getExtended().clear();
                getExtended().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                getSuperseder().clear();
                getSuperseder().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                getSuperseded().clear();
                getSuperseded().addAll((Collection)newValue);
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
            case TefkatPackage.TRULE_VAR__SCOPE:
                setScope((VarScope)null);
                return;
            case TefkatPackage.TRULE_VAR__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TefkatPackage.TRULE_VAR__EXTENDER:
                getExtender().clear();
                return;
            case TefkatPackage.TRULE_VAR__EXTENDED:
                getExtended().clear();
                return;
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                getSuperseder().clear();
                return;
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                getSuperseded().clear();
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
            case TefkatPackage.TRULE_VAR__SCOPE:
                return getScope() != null;
            case TefkatPackage.TRULE_VAR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.TRULE_VAR__USAGES:
                return usages != null && !usages.isEmpty();
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return extender != null && !extender.isEmpty();
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return extended != null && !extended.isEmpty();
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return superseder != null && !superseder.isEmpty();
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return superseded != null && !superseded.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //TRuleVarImpl
