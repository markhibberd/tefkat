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

import com.dstc.tefkat.model.PatternDefn;
import com.dstc.tefkat.model.PatternScope;
import com.dstc.tefkat.model.PatternVar;
import com.dstc.tefkat.model.TefkatPackage;
import com.dstc.tefkat.model.Term;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.PatternDefnImpl#getPatternScope <em>Pattern Scope</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.PatternDefnImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.PatternDefnImpl#getParameterVar <em>Parameter Var</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.PatternDefnImpl#isSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PatternDefnImpl extends VarScopeImpl implements PatternDefn {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

	/**
	 * The cached value of the '{@link #getTerm() <em>Term</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTerm()
	 * @generated
	 * @ordered
	 */
    protected Term term = null;

	/**
	 * The cached value of the '{@link #getParameterVar() <em>Parameter Var</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getParameterVar()
	 * @generated
	 * @ordered
	 */
    protected EList parameterVar = null;

	/**
	 * The default value of the '{@link #isSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSource()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SOURCE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSource()
	 * @generated
	 * @ordered
	 */
	protected boolean source = SOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PatternDefnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return TefkatPackage.eINSTANCE.getPatternDefn();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PatternScope getPatternScope() {
		if (eContainerFeatureID != TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE) return null;
		return (PatternScope)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPatternScope(PatternScope newPatternScope) {
		if (newPatternScope != eContainer || (eContainerFeatureID != TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE && newPatternScope != null)) {
			if (EcoreUtil.isAncestor(this, newPatternScope))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPatternScope != null)
				msgs = ((InternalEObject)newPatternScope).eInverseAdd(this, TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN, PatternScope.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPatternScope, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE, newPatternScope, newPatternScope));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Term getTerm() {
		return term;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTerm(Term newTerm, NotificationChain msgs) {
		Term oldTerm = term;
		term = newTerm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.PATTERN_DEFN__TERM, oldTerm, newTerm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTerm(Term newTerm) {
		if (newTerm != term) {
			NotificationChain msgs = null;
			if (term != null)
				msgs = ((InternalEObject)term).eInverseRemove(this, TefkatPackage.TERM__PATTERN_DEFN, Term.class, msgs);
			if (newTerm != null)
				msgs = ((InternalEObject)newTerm).eInverseAdd(this, TefkatPackage.TERM__PATTERN_DEFN, Term.class, msgs);
			msgs = basicSetTerm(newTerm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.PATTERN_DEFN__TERM, newTerm, newTerm));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getParameterVar() {
		if (parameterVar == null) {
			parameterVar = new EObjectResolvingEList(PatternVar.class, this, TefkatPackage.PATTERN_DEFN__PARAMETER_VAR);
		}
		return parameterVar;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSource(boolean newSource) {
		boolean oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.PATTERN_DEFN__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TefkatPackage.PATTERN_DEFN__VARS:
					return ((InternalEList)getVars()).basicAdd(otherEnd, msgs);
				case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE, msgs);
				case TefkatPackage.PATTERN_DEFN__TERM:
					if (term != null)
						msgs = ((InternalEObject)term).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.PATTERN_DEFN__TERM, null, msgs);
					return basicSetTerm((Term)otherEnd, msgs);
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
				case TefkatPackage.PATTERN_DEFN__VARS:
					return ((InternalEList)getVars()).basicRemove(otherEnd, msgs);
				case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
					return eBasicSetContainer(null, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE, msgs);
				case TefkatPackage.PATTERN_DEFN__TERM:
					return basicSetTerm(null, msgs);
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
				case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
					return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN, PatternScope.class, msgs);
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
			case TefkatPackage.PATTERN_DEFN__VARS:
				return getVars();
			case TefkatPackage.PATTERN_DEFN__NAME:
				return getName();
			case TefkatPackage.PATTERN_DEFN__COMMENTS:
				return getComments();
			case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
				return getPatternScope();
			case TefkatPackage.PATTERN_DEFN__TERM:
				return getTerm();
			case TefkatPackage.PATTERN_DEFN__PARAMETER_VAR:
				return getParameterVar();
			case TefkatPackage.PATTERN_DEFN__SOURCE:
				return isSource() ? Boolean.TRUE : Boolean.FALSE;
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
			case TefkatPackage.PATTERN_DEFN__VARS:
				getVars().clear();
				getVars().addAll((Collection)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__NAME:
				setName((String)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
				setPatternScope((PatternScope)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__TERM:
				setTerm((Term)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__PARAMETER_VAR:
				getParameterVar().clear();
				getParameterVar().addAll((Collection)newValue);
				return;
			case TefkatPackage.PATTERN_DEFN__SOURCE:
				setSource(((Boolean)newValue).booleanValue());
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
			case TefkatPackage.PATTERN_DEFN__VARS:
				getVars().clear();
				return;
			case TefkatPackage.PATTERN_DEFN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TefkatPackage.PATTERN_DEFN__COMMENTS:
				getComments().clear();
				return;
			case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
				setPatternScope((PatternScope)null);
				return;
			case TefkatPackage.PATTERN_DEFN__TERM:
				setTerm((Term)null);
				return;
			case TefkatPackage.PATTERN_DEFN__PARAMETER_VAR:
				getParameterVar().clear();
				return;
			case TefkatPackage.PATTERN_DEFN__SOURCE:
				setSource(SOURCE_EDEFAULT);
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
			case TefkatPackage.PATTERN_DEFN__VARS:
				return vars != null && !vars.isEmpty();
			case TefkatPackage.PATTERN_DEFN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TefkatPackage.PATTERN_DEFN__COMMENTS:
				return comments != null && !comments.isEmpty();
			case TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE:
				return getPatternScope() != null;
			case TefkatPackage.PATTERN_DEFN__TERM:
				return term != null;
			case TefkatPackage.PATTERN_DEFN__PARAMETER_VAR:
				return parameterVar != null && !parameterVar.isEmpty();
			case TefkatPackage.PATTERN_DEFN__SOURCE:
				return source != SOURCE_EDEFAULT;
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
        
        String sep = "";
        String result = "";
        for (Iterator itr = getParameterVar().iterator(); itr.hasNext(); ) {
            result  = result + sep + itr.next();
            sep = ", ";
        }

        return (source ? "PATTERN " : "TEMPLATE ") + name + "(" + result + ")";
    }

} //PatternDefnImpl
