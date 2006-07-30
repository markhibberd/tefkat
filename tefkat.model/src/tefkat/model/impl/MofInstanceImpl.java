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

import tefkat.model.CompoundTerm;
import tefkat.model.Expression;
import tefkat.model.ExtentVar;
import tefkat.model.MofInstance;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mof Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.MofInstanceImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tefkat.model.impl.MofInstanceImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.model.impl.MofInstanceImpl#isExact <em>Exact</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MofInstanceImpl extends MofTermImpl implements MofInstance {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeName()
     * @generated
     * @ordered
     */
    protected Expression typeName = null;

    /**
     * The cached value of the '{@link #getInstance() <em>Instance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstance()
     * @generated
     * @ordered
     */
    protected Expression instance = null;

    /**
     * The default value of the '{@link #isExact() <em>Exact</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExact()
     * @generated
     * @ordered
     */
    protected static final boolean EXACT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isExact() <em>Exact</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExact()
     * @generated
     * @ordered
     */
    protected boolean exact = EXACT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MofInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.MOF_INSTANCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getTypeName() {
        return typeName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTypeName(Expression newTypeName, NotificationChain msgs) {
        Expression oldTypeName = typeName;
        typeName = newTypeName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_INSTANCE__TYPE_NAME, oldTypeName, newTypeName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeName(Expression newTypeName) {
        if (newTypeName != typeName) {
            NotificationChain msgs = null;
            if (typeName != null)
                msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_INSTANCE__TYPE_NAME, null, msgs);
            if (newTypeName != null)
                msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_INSTANCE__TYPE_NAME, null, msgs);
            msgs = basicSetTypeName(newTypeName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_INSTANCE__TYPE_NAME, newTypeName, newTypeName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getInstance() {
        return instance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInstance(Expression newInstance, NotificationChain msgs) {
        Expression oldInstance = instance;
        instance = newInstance;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_INSTANCE__INSTANCE, oldInstance, newInstance);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInstance(Expression newInstance) {
        if (newInstance != instance) {
            NotificationChain msgs = null;
            if (instance != null)
                msgs = ((InternalEObject)instance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_INSTANCE__INSTANCE, null, msgs);
            if (newInstance != null)
                msgs = ((InternalEObject)newInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_INSTANCE__INSTANCE, null, msgs);
            msgs = basicSetInstance(newInstance, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_INSTANCE__INSTANCE, newInstance, newInstance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isExact() {
        return exact;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExact(boolean newExact) {
        boolean oldExact = exact;
        exact = newExact;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_INSTANCE__EXACT, oldExact, exact));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                return basicSetTypeName(null, msgs);
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                return basicSetInstance(null, msgs);
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
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                return getTypeName();
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                return getInstance();
            case TefkatPackage.MOF_INSTANCE__EXACT:
                return isExact() ? Boolean.TRUE : Boolean.FALSE;
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
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                setTypeName((Expression)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                setInstance((Expression)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__EXACT:
                setExact(((Boolean)newValue).booleanValue());
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
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                setTypeName((Expression)null);
                return;
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                setInstance((Expression)null);
                return;
            case TefkatPackage.MOF_INSTANCE__EXACT:
                setExact(EXACT_EDEFAULT);
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
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                return typeName != null;
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                return instance != null;
            case TefkatPackage.MOF_INSTANCE__EXACT:
                return exact != EXACT_EDEFAULT;
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

        return instance + (exact ? " :#" : " : ") + typeName + (null == context ? "" : "@" + context);
    }

} //MofInstanceImpl
