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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.AbstractVar;
import tefkat.model.CompoundTerm;
import tefkat.model.ExtentVar;
import tefkat.model.NotTerm;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NotTermImpl extends CompoundTermImpl implements NotTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NotTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getNotTerm();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getNonLocalVars() {
        EList nonLocalVars = new BasicEList();
        Set notVars = new HashSet();
        Set usages = new HashSet();

        // Find the Vars and VarUses that occur in the NOT
        for (Iterator itr = eAllContents(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            if (obj instanceof VarUse) {
                notVars.add(((VarUse) obj).getVar());
                usages.add(obj);
            }
        }
        
        // Find just those Vars that are NOT only referenced in the NOT
        for (Iterator varItr = notVars.iterator(); varItr.hasNext(); ) {
            AbstractVar var = (AbstractVar) varItr.next();
            if (!usages.containsAll(var.getUsages())) {
                nonLocalVars.add(var);
            }
        }
        
        return nonLocalVars;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.NOT_TERM__PATTERN_DEFN, msgs);
                case TefkatPackage.NOT_TERM__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.NOT_TERM__QUERY, msgs);
                case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.NOT_TERM__COMPOUND_TERM, msgs);
                case TefkatPackage.NOT_TERM__TRULE_SRC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.NOT_TERM__TRULE_SRC, msgs);
                case TefkatPackage.NOT_TERM__TERM:
                    return ((InternalEList)getTerm()).basicAdd(otherEnd, msgs);
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
                case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.NOT_TERM__PATTERN_DEFN, msgs);
                case TefkatPackage.NOT_TERM__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.NOT_TERM__QUERY, msgs);
                case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.NOT_TERM__COMPOUND_TERM, msgs);
                case TefkatPackage.NOT_TERM__TRULE_SRC:
                    return eBasicSetContainer(null, TefkatPackage.NOT_TERM__TRULE_SRC, msgs);
                case TefkatPackage.NOT_TERM__TERM:
                    return ((InternalEList)getTerm()).basicRemove(otherEnd, msgs);
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
                case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                    return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.NOT_TERM__QUERY:
                    return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                    return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.NOT_TERM__TRULE_SRC:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
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
            case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.NOT_TERM__QUERY:
                return getQuery();
            case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.NOT_TERM__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.NOT_TERM__TRULE_SRC:
                return getTRuleSrc();
            case TefkatPackage.NOT_TERM__TERM:
                return getTerm();
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
            case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.NOT_TERM__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.NOT_TERM__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.NOT_TERM__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
                return;
            case TefkatPackage.NOT_TERM__TERM:
                getTerm().clear();
                getTerm().addAll((Collection)newValue);
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
            case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.NOT_TERM__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.NOT_TERM__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.NOT_TERM__TRULE_SRC:
                setTRuleSrc((TRule)null);
                return;
            case TefkatPackage.NOT_TERM__TERM:
                getTerm().clear();
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
            case TefkatPackage.NOT_TERM__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.NOT_TERM__QUERY:
                return getQuery() != null;
            case TefkatPackage.NOT_TERM__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.NOT_TERM__CONTEXT:
                return context != null;
            case TefkatPackage.NOT_TERM__TRULE_SRC:
                return getTRuleSrc() != null;
            case TefkatPackage.NOT_TERM__TERM:
                return term != null && !term.isEmpty();
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

        return "not " + getTerm();
    }

} //NotTermImpl
