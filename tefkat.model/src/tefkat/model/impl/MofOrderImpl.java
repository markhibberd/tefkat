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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.model.Expression;
import tefkat.model.MofOrder;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mof Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.MofOrderImpl#getLesser <em>Lesser</em>}</li>
 *   <li>{@link tefkat.model.impl.MofOrderImpl#getGreater <em>Greater</em>}</li>
 *   <li>{@link tefkat.model.impl.MofOrderImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.model.impl.MofOrderImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MofOrderImpl extends MofTermImpl implements MofOrder {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached value of the '{@link #getLesser() <em>Lesser</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLesser()
     * @generated
     * @ordered
     */
    protected Expression lesser = null;

    /**
     * The cached value of the '{@link #getGreater() <em>Greater</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGreater()
     * @generated
     * @ordered
     */
    protected Expression greater = null;

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
     * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeature()
     * @generated
     * @ordered
     */
    protected Expression feature = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MofOrderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.MOF_ORDER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getLesser() {
        return lesser;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLesser(Expression newLesser, NotificationChain msgs) {
        Expression oldLesser = lesser;
        lesser = newLesser;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__LESSER, oldLesser, newLesser);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLesser(Expression newLesser) {
        if (newLesser != lesser) {
            NotificationChain msgs = null;
            if (lesser != null)
                msgs = ((InternalEObject)lesser).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__LESSER, null, msgs);
            if (newLesser != null)
                msgs = ((InternalEObject)newLesser).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__LESSER, null, msgs);
            msgs = basicSetLesser(newLesser, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__LESSER, newLesser, newLesser));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getGreater() {
        return greater;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGreater(Expression newGreater, NotificationChain msgs) {
        Expression oldGreater = greater;
        greater = newGreater;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__GREATER, oldGreater, newGreater);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGreater(Expression newGreater) {
        if (newGreater != greater) {
            NotificationChain msgs = null;
            if (greater != null)
                msgs = ((InternalEObject)greater).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__GREATER, null, msgs);
            if (newGreater != null)
                msgs = ((InternalEObject)newGreater).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__GREATER, null, msgs);
            msgs = basicSetGreater(newGreater, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__GREATER, newGreater, newGreater));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__INSTANCE, oldInstance, newInstance);
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
                msgs = ((InternalEObject)instance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__INSTANCE, null, msgs);
            if (newInstance != null)
                msgs = ((InternalEObject)newInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__INSTANCE, null, msgs);
            msgs = basicSetInstance(newInstance, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__INSTANCE, newInstance, newInstance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFeature(Expression newFeature, NotificationChain msgs) {
        Expression oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__FEATURE, oldFeature, newFeature);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeature(Expression newFeature) {
        if (newFeature != feature) {
            NotificationChain msgs = null;
            if (feature != null)
                msgs = ((InternalEObject)feature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__FEATURE, null, msgs);
            if (newFeature != null)
                msgs = ((InternalEObject)newFeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.MOF_ORDER__FEATURE, null, msgs);
            msgs = basicSetFeature(newFeature, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.MOF_ORDER__FEATURE, newFeature, newFeature));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.MOF_ORDER__LESSER:
                return basicSetLesser(null, msgs);
            case TefkatPackage.MOF_ORDER__GREATER:
                return basicSetGreater(null, msgs);
            case TefkatPackage.MOF_ORDER__INSTANCE:
                return basicSetInstance(null, msgs);
            case TefkatPackage.MOF_ORDER__FEATURE:
                return basicSetFeature(null, msgs);
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
            case TefkatPackage.MOF_ORDER__LESSER:
                return getLesser();
            case TefkatPackage.MOF_ORDER__GREATER:
                return getGreater();
            case TefkatPackage.MOF_ORDER__INSTANCE:
                return getInstance();
            case TefkatPackage.MOF_ORDER__FEATURE:
                return getFeature();
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
            case TefkatPackage.MOF_ORDER__LESSER:
                setLesser((Expression)newValue);
                return;
            case TefkatPackage.MOF_ORDER__GREATER:
                setGreater((Expression)newValue);
                return;
            case TefkatPackage.MOF_ORDER__INSTANCE:
                setInstance((Expression)newValue);
                return;
            case TefkatPackage.MOF_ORDER__FEATURE:
                setFeature((Expression)newValue);
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
            case TefkatPackage.MOF_ORDER__LESSER:
                setLesser((Expression)null);
                return;
            case TefkatPackage.MOF_ORDER__GREATER:
                setGreater((Expression)null);
                return;
            case TefkatPackage.MOF_ORDER__INSTANCE:
                setInstance((Expression)null);
                return;
            case TefkatPackage.MOF_ORDER__FEATURE:
                setFeature((Expression)null);
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
            case TefkatPackage.MOF_ORDER__LESSER:
                return lesser != null;
            case TefkatPackage.MOF_ORDER__GREATER:
                return greater != null;
            case TefkatPackage.MOF_ORDER__INSTANCE:
                return instance != null;
            case TefkatPackage.MOF_ORDER__FEATURE:
                return feature != null;
        }
        return super.eIsSet(featureID);
    }

} //MofOrderImpl
