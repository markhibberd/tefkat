/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.VarUse;
import tefkat.engine.runtime.WrappedVar;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.VarUseImpl#getVar <em>Var</em>}</li>
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
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getVar() <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVar()
     * @generated
     * @ordered
     */
    protected Var var = null;

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
        return RuntimePackage.Literals.VAR_USE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Var getVar() {
        if (var != null && var.eIsProxy()) {
            InternalEObject oldVar = (InternalEObject)var;
            var = (Var)eResolveProxy(oldVar);
            if (var != oldVar) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.VAR_USE__VAR, oldVar, var));
            }
        }
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Var basicGetVar() {
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVar(Var newVar, NotificationChain msgs) {
        Var oldVar = var;
        var = newVar;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.VAR_USE__VAR, oldVar, newVar);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVar(Var newVar) {
        if (newVar != var) {
            NotificationChain msgs = null;
            if (var != null)
                msgs = ((InternalEObject)var).eInverseRemove(this, RuntimePackage.VAR__USAGES, Var.class, msgs);
            if (newVar != null)
                msgs = ((InternalEObject)newVar).eInverseAdd(this, RuntimePackage.VAR__USAGES, Var.class, msgs);
            msgs = basicSetVar(newVar, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.VAR_USE__VAR, newVar, newVar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.VAR_USE__VAR:
                if (var != null)
                    msgs = ((InternalEObject)var).eInverseRemove(this, RuntimePackage.VAR__USAGES, Var.class, msgs);
                return basicSetVar((Var)otherEnd, msgs);
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
            case RuntimePackage.VAR_USE__VAR:
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
            case RuntimePackage.VAR_USE__VAR:
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
            case RuntimePackage.VAR_USE__VAR:
                setVar((Var)newValue);
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
            case RuntimePackage.VAR_USE__VAR:
                setVar((Var)null);
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
            case RuntimePackage.VAR_USE__VAR:
                return var != null;
        }
        return super.eIsSet(featureID);
    }

    public List eval(Context context, Binding binding) throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        Var var = getVar();
        Object value = context.lookup(var);
        if (null == value) {
            values.add(new WrappedVar(var));
        } else {
            values.add(value);
        }
        return values;
    }
    
    public String toString() {
        return String.valueOf(getVar());
    }

} //VarUseImpl