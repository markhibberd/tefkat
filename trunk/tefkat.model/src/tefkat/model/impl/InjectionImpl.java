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


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.CompoundTerm;
import tefkat.model.Expression;
import tefkat.model.ExtentVar;
import tefkat.model.Injection;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Injection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.InjectionImpl#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.impl.InjectionImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link tefkat.model.impl.InjectionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InjectionImpl extends TargetTermImpl implements Injection {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

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
	 * The cached value of the '{@link #getSources() <em>Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSources()
	 * @generated
	 * @ordered
	 */
    protected EList sources = null;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
    protected VarUse target = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected InjectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return TefkatPackage.eINSTANCE.getInjection();
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
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.INJECTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSources() {
		if (sources == null) {
			sources = new EObjectContainmentEList(Expression.class, this, TefkatPackage.INJECTION__SOURCES);
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public VarUse getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTarget(VarUse newTarget, NotificationChain msgs) {
		VarUse oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.INJECTION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTarget(VarUse newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.INJECTION__TARGET, null, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.INJECTION__TARGET, null, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.INJECTION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TefkatPackage.INJECTION__PATTERN_DEFN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.INJECTION__PATTERN_DEFN, msgs);
				case TefkatPackage.INJECTION__QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.INJECTION__QUERY, msgs);
				case TefkatPackage.INJECTION__COMPOUND_TERM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.INJECTION__COMPOUND_TERM, msgs);
				case TefkatPackage.INJECTION__TRULE_TGT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.INJECTION__TRULE_TGT, msgs);
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
				case TefkatPackage.INJECTION__PATTERN_DEFN:
					return eBasicSetContainer(null, TefkatPackage.INJECTION__PATTERN_DEFN, msgs);
				case TefkatPackage.INJECTION__QUERY:
					return eBasicSetContainer(null, TefkatPackage.INJECTION__QUERY, msgs);
				case TefkatPackage.INJECTION__COMPOUND_TERM:
					return eBasicSetContainer(null, TefkatPackage.INJECTION__COMPOUND_TERM, msgs);
				case TefkatPackage.INJECTION__TRULE_TGT:
					return eBasicSetContainer(null, TefkatPackage.INJECTION__TRULE_TGT, msgs);
				case TefkatPackage.INJECTION__SOURCES:
					return ((InternalEList)getSources()).basicRemove(otherEnd, msgs);
				case TefkatPackage.INJECTION__TARGET:
					return basicSetTarget(null, msgs);
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
				case TefkatPackage.INJECTION__PATTERN_DEFN:
					return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
				case TefkatPackage.INJECTION__QUERY:
					return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
				case TefkatPackage.INJECTION__COMPOUND_TERM:
					return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
				case TefkatPackage.INJECTION__TRULE_TGT:
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
			case TefkatPackage.INJECTION__PATTERN_DEFN:
				return getPatternDefn();
			case TefkatPackage.INJECTION__QUERY:
				return getQuery();
			case TefkatPackage.INJECTION__COMPOUND_TERM:
				return getCompoundTerm();
			case TefkatPackage.INJECTION__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case TefkatPackage.INJECTION__TRULE_TGT:
				return getTRuleTgt();
			case TefkatPackage.INJECTION__NAME:
				return getName();
			case TefkatPackage.INJECTION__SOURCES:
				return getSources();
			case TefkatPackage.INJECTION__TARGET:
				return getTarget();
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
			case TefkatPackage.INJECTION__PATTERN_DEFN:
				setPatternDefn((PatternDefn)newValue);
				return;
			case TefkatPackage.INJECTION__QUERY:
				setQuery((Query)newValue);
				return;
			case TefkatPackage.INJECTION__COMPOUND_TERM:
				setCompoundTerm((CompoundTerm)newValue);
				return;
			case TefkatPackage.INJECTION__CONTEXT:
				setContext((ExtentVar)newValue);
				return;
			case TefkatPackage.INJECTION__TRULE_TGT:
				setTRuleTgt((TRule)newValue);
				return;
			case TefkatPackage.INJECTION__NAME:
				setName((String)newValue);
				return;
			case TefkatPackage.INJECTION__SOURCES:
				getSources().clear();
				getSources().addAll((Collection)newValue);
				return;
			case TefkatPackage.INJECTION__TARGET:
				setTarget((VarUse)newValue);
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
			case TefkatPackage.INJECTION__PATTERN_DEFN:
				setPatternDefn((PatternDefn)null);
				return;
			case TefkatPackage.INJECTION__QUERY:
				setQuery((Query)null);
				return;
			case TefkatPackage.INJECTION__COMPOUND_TERM:
				setCompoundTerm((CompoundTerm)null);
				return;
			case TefkatPackage.INJECTION__CONTEXT:
				setContext((ExtentVar)null);
				return;
			case TefkatPackage.INJECTION__TRULE_TGT:
				setTRuleTgt((TRule)null);
				return;
			case TefkatPackage.INJECTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TefkatPackage.INJECTION__SOURCES:
				getSources().clear();
				return;
			case TefkatPackage.INJECTION__TARGET:
				setTarget((VarUse)null);
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
			case TefkatPackage.INJECTION__PATTERN_DEFN:
				return getPatternDefn() != null;
			case TefkatPackage.INJECTION__QUERY:
				return getQuery() != null;
			case TefkatPackage.INJECTION__COMPOUND_TERM:
				return getCompoundTerm() != null;
			case TefkatPackage.INJECTION__CONTEXT:
				return context != null;
			case TefkatPackage.INJECTION__TRULE_TGT:
				return getTRuleTgt() != null;
			case TefkatPackage.INJECTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TefkatPackage.INJECTION__SOURCES:
				return sources != null && !sources.isEmpty();
			case TefkatPackage.INJECTION__TARGET:
				return target != null;
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

        return "'" + name + "'" + getSources() + " -> " + target;
    }

} //InjectionImpl
