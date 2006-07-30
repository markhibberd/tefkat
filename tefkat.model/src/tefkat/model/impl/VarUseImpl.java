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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.model.AbstractVar;
import tefkat.model.CompoundExpr;
import tefkat.model.Expression;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.VarUseImpl#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VarUseImpl extends ExpressionImpl implements VarUse {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached value of the '{@link #getVar() <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVar()
     * @generated
     * @ordered
     */
    protected AbstractVar var = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VarUseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.VAR_USE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractVar getVar() {
        if (var != null && var.eIsProxy()) {
            InternalEObject oldVar = (InternalEObject)var;
            var = (AbstractVar)eResolveProxy(oldVar);
            if (var != oldVar) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TefkatPackage.VAR_USE__VAR, oldVar, var));
            }
        }
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractVar basicGetVar() {
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVar(AbstractVar newVar, NotificationChain msgs) {
        AbstractVar oldVar = var;
        var = newVar;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.VAR_USE__VAR, oldVar, newVar);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVar(AbstractVar newVar) {
        if (newVar != var) {
            NotificationChain msgs = null;
            if (var != null)
                msgs = ((InternalEObject)var).eInverseRemove(this, TefkatPackage.ABSTRACT_VAR__USAGES, AbstractVar.class, msgs);
            if (newVar != null)
                msgs = ((InternalEObject)newVar).eInverseAdd(this, TefkatPackage.ABSTRACT_VAR__USAGES, AbstractVar.class, msgs);
            msgs = basicSetVar(newVar, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.VAR_USE__VAR, newVar, newVar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.VAR_USE__VAR:
                if (var != null)
                    msgs = ((InternalEObject)var).eInverseRemove(this, TefkatPackage.ABSTRACT_VAR__USAGES, AbstractVar.class, msgs);
                return basicSetVar((AbstractVar)otherEnd, msgs);
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
            case TefkatPackage.VAR_USE__VAR:
                return basicSetVar(null, msgs);
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
            case TefkatPackage.VAR_USE__VAR:
                if (resolve) return getVar();
                return basicGetVar();
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
            case TefkatPackage.VAR_USE__VAR:
                setVar((AbstractVar)newValue);
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
            case TefkatPackage.VAR_USE__VAR:
                setVar((AbstractVar)null);
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
            case TefkatPackage.VAR_USE__VAR:
                return var != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        VarUse copy = TefkatFactory.eINSTANCE.createVarUse();
        copy.setVar(getVar());
        return copy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();
        
        return String.valueOf(var);
    }

} //VarUseImpl
