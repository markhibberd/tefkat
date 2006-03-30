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

import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.model.CompoundTerm;
import tefkat.model.ExtentVar;
import tefkat.model.IfTerm;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TargetTerm;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.TargetTermImpl#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TargetTermImpl extends TermImpl implements TargetTerm {
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
	protected TargetTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTargetTerm();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TRule getTRuleTgt() {
        if (eContainerFeatureID != TefkatPackage.TARGET_TERM__TRULE_TGT) return null;
        return (TRule)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTRuleTgt(TRule newTRuleTgt) {
        if (newTRuleTgt != eContainer || (eContainerFeatureID != TefkatPackage.TARGET_TERM__TRULE_TGT && newTRuleTgt != null)) {
            if (EcoreUtil.isAncestor(this, newTRuleTgt))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTRuleTgt != null)
                msgs = ((InternalEObject)newTRuleTgt).eInverseAdd(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newTRuleTgt, TefkatPackage.TARGET_TERM__TRULE_TGT, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TARGET_TERM__TRULE_TGT, newTRuleTgt, newTRuleTgt));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TARGET_TERM__PATTERN_DEFN, msgs);
                case TefkatPackage.TARGET_TERM__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TARGET_TERM__QUERY, msgs);
                case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TARGET_TERM__COMPOUND_TERM, msgs);
                case TefkatPackage.TARGET_TERM__TRULE_TGT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TARGET_TERM__TRULE_TGT, msgs);
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
                case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.TARGET_TERM__PATTERN_DEFN, msgs);
                case TefkatPackage.TARGET_TERM__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.TARGET_TERM__QUERY, msgs);
                case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.TARGET_TERM__COMPOUND_TERM, msgs);
                case TefkatPackage.TARGET_TERM__TRULE_TGT:
                    return eBasicSetContainer(null, TefkatPackage.TARGET_TERM__TRULE_TGT, msgs);
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
                case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                    return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.TARGET_TERM__QUERY:
                    return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                    return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.TARGET_TERM__TRULE_TGT:
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
            case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.TARGET_TERM__QUERY:
                return getQuery();
            case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.TARGET_TERM__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.TARGET_TERM__TRULE_TGT:
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
            case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.TARGET_TERM__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.TARGET_TERM__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.TARGET_TERM__TRULE_TGT:
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
            case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.TARGET_TERM__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.TARGET_TERM__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.TARGET_TERM__TRULE_TGT:
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
            case TefkatPackage.TARGET_TERM__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.TARGET_TERM__QUERY:
                return getQuery() != null;
            case TefkatPackage.TARGET_TERM__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.TARGET_TERM__CONTEXT:
                return context != null;
            case TefkatPackage.TARGET_TERM__TRULE_TGT:
                return getTRuleTgt() != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * A TargetTerm is in a "target" context if:
     * <ul>
     * <li> it's owned by a TRule via its "tgt" reference, or </li>
     * <li> it's owned by a PatternDefn for which "isSource" is false, or </li>
     * <li> it's not the condition of an IfTerm, and
     * it's owned by a CompoundTerm that is a "target" term.</li>
     * </ul>
     * 
     * Otherwise its either owned by a TRule via its "src" reference or by a
     * PatternDefn for which "isSource" is true or by a Query so it must be a
     * "source" term.
     * 
     * @generated NOT
     */
    public boolean isTarget() {
        CompoundTerm parent = getCompoundTerm();
        return (this.getTRuleTgt() != null
                || (this.getPatternDefn() != null && !this.getPatternDefn().isSource())
                || (parent != null
                        && !(parent instanceof IfTerm &&
                                ((IfTerm) parent).getTerm().get(0).equals(this))
                                && parent.isTarget()));
    }

} //TargetTermImpl
