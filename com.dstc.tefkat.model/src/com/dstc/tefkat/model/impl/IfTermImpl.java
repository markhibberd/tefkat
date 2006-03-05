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
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CompoundTerm;
import com.dstc.tefkat.model.ExtentVar;
import com.dstc.tefkat.model.IfTerm;
import com.dstc.tefkat.model.PatternDefn;
import com.dstc.tefkat.model.Query;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.TargetTerm;
import com.dstc.tefkat.model.TefkatPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.IfTermImpl#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfTermImpl extends CompoundTermImpl implements IfTerm {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IfTermImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return TefkatPackage.eINSTANCE.getIfTerm();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TRule getTRuleTgt() {
		if (eContainerFeatureID != TefkatPackage.IF_TERM__TRULE_TGT) return null;
		return (TRule)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTRuleTgt(TRule newTRuleTgt) {
		if (newTRuleTgt != eContainer || (eContainerFeatureID != TefkatPackage.IF_TERM__TRULE_TGT && newTRuleTgt != null)) {
			if (EcoreUtil.isAncestor(this, newTRuleTgt))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTRuleTgt != null)
				msgs = ((InternalEObject)newTRuleTgt).eInverseAdd(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTRuleTgt, TefkatPackage.IF_TERM__TRULE_TGT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.IF_TERM__TRULE_TGT, newTRuleTgt, newTRuleTgt));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TefkatPackage.IF_TERM__PATTERN_DEFN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.IF_TERM__PATTERN_DEFN, msgs);
				case TefkatPackage.IF_TERM__QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.IF_TERM__QUERY, msgs);
				case TefkatPackage.IF_TERM__COMPOUND_TERM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.IF_TERM__COMPOUND_TERM, msgs);
				case TefkatPackage.IF_TERM__TRULE_SRC:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.IF_TERM__TRULE_SRC, msgs);
				case TefkatPackage.IF_TERM__TERM:
					return ((InternalEList)getTerm()).basicAdd(otherEnd, msgs);
				case TefkatPackage.IF_TERM__TRULE_TGT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.IF_TERM__TRULE_TGT, msgs);
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
				case TefkatPackage.IF_TERM__PATTERN_DEFN:
					return eBasicSetContainer(null, TefkatPackage.IF_TERM__PATTERN_DEFN, msgs);
				case TefkatPackage.IF_TERM__QUERY:
					return eBasicSetContainer(null, TefkatPackage.IF_TERM__QUERY, msgs);
				case TefkatPackage.IF_TERM__COMPOUND_TERM:
					return eBasicSetContainer(null, TefkatPackage.IF_TERM__COMPOUND_TERM, msgs);
				case TefkatPackage.IF_TERM__TRULE_SRC:
					return eBasicSetContainer(null, TefkatPackage.IF_TERM__TRULE_SRC, msgs);
				case TefkatPackage.IF_TERM__TERM:
					return ((InternalEList)getTerm()).basicRemove(otherEnd, msgs);
				case TefkatPackage.IF_TERM__TRULE_TGT:
					return eBasicSetContainer(null, TefkatPackage.IF_TERM__TRULE_TGT, msgs);
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
				case TefkatPackage.IF_TERM__PATTERN_DEFN:
					return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
				case TefkatPackage.IF_TERM__QUERY:
					return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
				case TefkatPackage.IF_TERM__COMPOUND_TERM:
					return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
				case TefkatPackage.IF_TERM__TRULE_SRC:
					return eContainer.eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
				case TefkatPackage.IF_TERM__TRULE_TGT:
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
			case TefkatPackage.IF_TERM__PATTERN_DEFN:
				return getPatternDefn();
			case TefkatPackage.IF_TERM__QUERY:
				return getQuery();
			case TefkatPackage.IF_TERM__COMPOUND_TERM:
				return getCompoundTerm();
			case TefkatPackage.IF_TERM__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case TefkatPackage.IF_TERM__TRULE_SRC:
				return getTRuleSrc();
			case TefkatPackage.IF_TERM__TERM:
				return getTerm();
			case TefkatPackage.IF_TERM__TRULE_TGT:
				return getTRuleTgt();
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
			case TefkatPackage.IF_TERM__PATTERN_DEFN:
				setPatternDefn((PatternDefn)newValue);
				return;
			case TefkatPackage.IF_TERM__QUERY:
				setQuery((Query)newValue);
				return;
			case TefkatPackage.IF_TERM__COMPOUND_TERM:
				setCompoundTerm((CompoundTerm)newValue);
				return;
			case TefkatPackage.IF_TERM__CONTEXT:
				setContext((ExtentVar)newValue);
				return;
			case TefkatPackage.IF_TERM__TRULE_SRC:
				setTRuleSrc((TRule)newValue);
				return;
			case TefkatPackage.IF_TERM__TERM:
				getTerm().clear();
				getTerm().addAll((Collection)newValue);
				return;
			case TefkatPackage.IF_TERM__TRULE_TGT:
				setTRuleTgt((TRule)newValue);
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
			case TefkatPackage.IF_TERM__PATTERN_DEFN:
				setPatternDefn((PatternDefn)null);
				return;
			case TefkatPackage.IF_TERM__QUERY:
				setQuery((Query)null);
				return;
			case TefkatPackage.IF_TERM__COMPOUND_TERM:
				setCompoundTerm((CompoundTerm)null);
				return;
			case TefkatPackage.IF_TERM__CONTEXT:
				setContext((ExtentVar)null);
				return;
			case TefkatPackage.IF_TERM__TRULE_SRC:
				setTRuleSrc((TRule)null);
				return;
			case TefkatPackage.IF_TERM__TERM:
				getTerm().clear();
				return;
			case TefkatPackage.IF_TERM__TRULE_TGT:
				setTRuleTgt((TRule)null);
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
			case TefkatPackage.IF_TERM__PATTERN_DEFN:
				return getPatternDefn() != null;
			case TefkatPackage.IF_TERM__QUERY:
				return getQuery() != null;
			case TefkatPackage.IF_TERM__COMPOUND_TERM:
				return getCompoundTerm() != null;
			case TefkatPackage.IF_TERM__CONTEXT:
				return context != null;
			case TefkatPackage.IF_TERM__TRULE_SRC:
				return getTRuleSrc() != null;
			case TefkatPackage.IF_TERM__TERM:
				return term != null && !term.isEmpty();
			case TefkatPackage.IF_TERM__TRULE_TGT:
				return getTRuleTgt() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == TargetTerm.class) {
			switch (derivedFeatureID) {
				case TefkatPackage.IF_TERM__TRULE_TGT: return TefkatPackage.TARGET_TERM__TRULE_TGT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == TargetTerm.class) {
			switch (baseFeatureID) {
				case TefkatPackage.TARGET_TERM__TRULE_TGT: return TefkatPackage.IF_TERM__TRULE_TGT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();
        String condS = term.size() > 0 ? String.valueOf(term.get(0)) : "null";
        String thenS = term.size() > 1 ? String.valueOf(term.get(1)) : "null";
        String elseS = term.size() > 2 ? String.valueOf(term.get(2)) : "null";

        return "IF " + condS + " THEN " + thenS + " ELSE " + elseS + " ENDIF";
    }

} //IfTermImpl
