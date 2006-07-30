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
 */
package tefkat.model.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.AbstractVar;
import tefkat.model.TefkatPackage;
import tefkat.model.VarScope;

import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Var</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.AbstractVarImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.model.impl.AbstractVarImpl#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.impl.AbstractVarImpl#getUsages <em>Usages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractVarImpl extends EObjectImpl implements AbstractVar {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getUsages() <em>Usages</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsages()
     * @generated
     * @ordered
     */
    protected EList usages = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractVarImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.ABSTRACT_VAR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarScope getScope() {
        if (eContainerFeatureID != TefkatPackage.ABSTRACT_VAR__SCOPE) return null;
        return (VarScope)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScope(VarScope newScope, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newScope, TefkatPackage.ABSTRACT_VAR__SCOPE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScope(VarScope newScope) {
        if (newScope != eInternalContainer() || (eContainerFeatureID != TefkatPackage.ABSTRACT_VAR__SCOPE && newScope != null)) {
            if (EcoreUtil.isAncestor(this, newScope))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newScope != null)
                msgs = ((InternalEObject)newScope).eInverseAdd(this, TefkatPackage.VAR_SCOPE__VARS, VarScope.class, msgs);
            msgs = basicSetScope(newScope, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.ABSTRACT_VAR__SCOPE, newScope, newScope));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.ABSTRACT_VAR__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getUsages() {
        if (usages == null) {
            usages = new EObjectWithInverseResolvingEList(VarUse.class, this, TefkatPackage.ABSTRACT_VAR__USAGES, TefkatPackage.VAR_USE__VAR);
        }
        return usages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetScope((VarScope)otherEnd, msgs);
            case TefkatPackage.ABSTRACT_VAR__USAGES:
                return ((InternalEList)getUsages()).basicAdd(otherEnd, msgs);
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
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                return basicSetScope(null, msgs);
            case TefkatPackage.ABSTRACT_VAR__USAGES:
                return ((InternalEList)getUsages()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                return eInternalContainer().eInverseRemove(this, TefkatPackage.VAR_SCOPE__VARS, VarScope.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                return getScope();
            case TefkatPackage.ABSTRACT_VAR__NAME:
                return getName();
            case TefkatPackage.ABSTRACT_VAR__USAGES:
                return getUsages();
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
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                setScope((VarScope)newValue);
                return;
            case TefkatPackage.ABSTRACT_VAR__NAME:
                setName((String)newValue);
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
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                setScope((VarScope)null);
                return;
            case TefkatPackage.ABSTRACT_VAR__NAME:
                setName(NAME_EDEFAULT);
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
            case TefkatPackage.ABSTRACT_VAR__SCOPE:
                return getScope() != null;
            case TefkatPackage.ABSTRACT_VAR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.ABSTRACT_VAR__USAGES:
                return usages != null && !usages.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        return getScope().getName() + "::" + name;
    }

} //AbstractVarImpl
