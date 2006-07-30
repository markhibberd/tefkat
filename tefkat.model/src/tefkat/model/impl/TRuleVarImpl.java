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
        return TefkatPackage.Literals.TRULE_VAR;
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return ((InternalEList)getExtender()).basicAdd(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return ((InternalEList)getExtended()).basicAdd(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return ((InternalEList)getSuperseder()).basicAdd(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return ((InternalEList)getSuperseded()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return ((InternalEList)getExtender()).basicRemove(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return ((InternalEList)getExtended()).basicRemove(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return ((InternalEList)getSuperseder()).basicRemove(otherEnd, msgs);
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return ((InternalEList)getSuperseded()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return getExtender();
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return getExtended();
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return getSuperseder();
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return getSuperseded();
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
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
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
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TefkatPackage.TRULE_VAR__EXTENDER:
                return extender != null && !extender.isEmpty();
            case TefkatPackage.TRULE_VAR__EXTENDED:
                return extended != null && !extended.isEmpty();
            case TefkatPackage.TRULE_VAR__SUPERSEDER:
                return superseder != null && !superseder.isEmpty();
            case TefkatPackage.TRULE_VAR__SUPERSEDED:
                return superseded != null && !superseded.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //TRuleVarImpl
