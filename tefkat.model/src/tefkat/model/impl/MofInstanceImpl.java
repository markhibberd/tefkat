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
        return TefkatPackage.eINSTANCE.getMofInstance();
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_INSTANCE__PATTERN_DEFN, msgs);
                case TefkatPackage.MOF_INSTANCE__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_INSTANCE__QUERY, msgs);
                case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_INSTANCE__COMPOUND_TERM, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_INSTANCE__TRULE_SRC, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.MOF_INSTANCE__TRULE_TGT, msgs);
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
                case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.MOF_INSTANCE__PATTERN_DEFN, msgs);
                case TefkatPackage.MOF_INSTANCE__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.MOF_INSTANCE__QUERY, msgs);
                case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.MOF_INSTANCE__COMPOUND_TERM, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                    return eBasicSetContainer(null, TefkatPackage.MOF_INSTANCE__TRULE_SRC, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                    return eBasicSetContainer(null, TefkatPackage.MOF_INSTANCE__TRULE_TGT, msgs);
                case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                    return basicSetTypeName(null, msgs);
                case TefkatPackage.MOF_INSTANCE__INSTANCE:
                    return basicSetInstance(null, msgs);
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
                case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                    return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.MOF_INSTANCE__QUERY:
                    return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                    return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
                case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
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
            case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.MOF_INSTANCE__QUERY:
                return getQuery();
            case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.MOF_INSTANCE__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                return getTRuleSrc();
            case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                return getTRuleTgt();
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                return getTypeName();
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                return getInstance();
            case TefkatPackage.MOF_INSTANCE__EXACT:
                return isExact() ? Boolean.TRUE : Boolean.FALSE;
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
            case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
                return;
            case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
                return;
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
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.MOF_INSTANCE__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.MOF_INSTANCE__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                setTRuleSrc((TRule)null);
                return;
            case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                setTRuleTgt((TRule)null);
                return;
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
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.MOF_INSTANCE__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.MOF_INSTANCE__QUERY:
                return getQuery() != null;
            case TefkatPackage.MOF_INSTANCE__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.MOF_INSTANCE__CONTEXT:
                return context != null;
            case TefkatPackage.MOF_INSTANCE__TRULE_SRC:
                return getTRuleSrc() != null;
            case TefkatPackage.MOF_INSTANCE__TRULE_TGT:
                return getTRuleTgt() != null;
            case TefkatPackage.MOF_INSTANCE__TYPE_NAME:
                return typeName != null;
            case TefkatPackage.MOF_INSTANCE__INSTANCE:
                return instance != null;
            case TefkatPackage.MOF_INSTANCE__EXACT:
                return exact != EXACT_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
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
