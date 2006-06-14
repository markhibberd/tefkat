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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.model.CompoundTerm;
import tefkat.model.Expression;
import tefkat.model.ExtentVar;
import tefkat.model.MofOrder;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
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
        return TefkatPackage.eINSTANCE.getMofOrder();
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_ORDER__PATTERN_DEFN, msgs);
                case TefkatPackage.MOF_ORDER__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_ORDER__QUERY, msgs);
                case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_ORDER__COMPOUND_TERM, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_SRC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_ORDER__TRULE_SRC, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_TGT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_ORDER__TRULE_TGT, msgs);
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
                case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.MOF_ORDER__PATTERN_DEFN, msgs);
                case TefkatPackage.MOF_ORDER__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.MOF_ORDER__QUERY, msgs);
                case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.MOF_ORDER__COMPOUND_TERM, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_SRC:
                    return eBasicSetContainer(null, TefkatPackage.MOF_ORDER__TRULE_SRC, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_TGT:
                    return eBasicSetContainer(null, TefkatPackage.MOF_ORDER__TRULE_TGT, msgs);
                case TefkatPackage.MOF_ORDER__LESSER:
                    return basicSetLesser(null, msgs);
                case TefkatPackage.MOF_ORDER__GREATER:
                    return basicSetGreater(null, msgs);
                case TefkatPackage.MOF_ORDER__INSTANCE:
                    return basicSetInstance(null, msgs);
                case TefkatPackage.MOF_ORDER__FEATURE:
                    return basicSetFeature(null, msgs);
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
                case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                    return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.MOF_ORDER__QUERY:
                    return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                    return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_SRC:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
                case TefkatPackage.MOF_ORDER__TRULE_TGT:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
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
            case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.MOF_ORDER__QUERY:
                return getQuery();
            case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.MOF_ORDER__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.MOF_ORDER__TRULE_SRC:
                return getTRuleSrc();
            case TefkatPackage.MOF_ORDER__TRULE_TGT:
                return getTRuleTgt();
            case TefkatPackage.MOF_ORDER__LESSER:
                return getLesser();
            case TefkatPackage.MOF_ORDER__GREATER:
                return getGreater();
            case TefkatPackage.MOF_ORDER__INSTANCE:
                return getInstance();
            case TefkatPackage.MOF_ORDER__FEATURE:
                return getFeature();
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
            case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.MOF_ORDER__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.MOF_ORDER__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.MOF_ORDER__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
                return;
            case TefkatPackage.MOF_ORDER__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
                return;
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
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.MOF_ORDER__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.MOF_ORDER__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.MOF_ORDER__TRULE_SRC:
                setTRuleSrc((TRule)null);
                return;
            case TefkatPackage.MOF_ORDER__TRULE_TGT:
                setTRuleTgt((TRule)null);
                return;
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
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.MOF_ORDER__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.MOF_ORDER__QUERY:
                return getQuery() != null;
            case TefkatPackage.MOF_ORDER__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.MOF_ORDER__CONTEXT:
                return context != null;
            case TefkatPackage.MOF_ORDER__TRULE_SRC:
                return getTRuleSrc() != null;
            case TefkatPackage.MOF_ORDER__TRULE_TGT:
                return getTRuleTgt() != null;
            case TefkatPackage.MOF_ORDER__LESSER:
                return lesser != null;
            case TefkatPackage.MOF_ORDER__GREATER:
                return greater != null;
            case TefkatPackage.MOF_ORDER__INSTANCE:
                return instance != null;
            case TefkatPackage.MOF_ORDER__FEATURE:
                return feature != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //MofOrderImpl
