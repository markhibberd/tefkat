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
import tefkat.model.Condition;
import tefkat.model.Expression;
import tefkat.model.ExtentVar;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.ConditionImpl#getArg <em>Arg</em>}</li>
 *   <li>{@link tefkat.model.impl.ConditionImpl#getRelation <em>Relation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionImpl extends SimpleTermImpl implements Condition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * The cached value of the '{@link #getArg() <em>Arg</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArg()
     * @generated
     * @ordered
     */
    protected EList arg = null;

    /**
     * The default value of the '{@link #getRelation() <em>Relation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelation()
     * @generated
     * @ordered
     */
    protected static final String RELATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRelation() <em>Relation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelation()
     * @generated
     * @ordered
     */
    protected String relation = RELATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getCondition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getArg() {
        if (arg == null) {
            arg = new EObjectContainmentEList(Expression.class, this, TefkatPackage.CONDITION__ARG);
        }
        return arg;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRelation() {
        return relation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelation(String newRelation) {
        String oldRelation = relation;
        relation = newRelation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.CONDITION__RELATION, oldRelation, relation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.CONDITION__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.CONDITION__PATTERN_DEFN, msgs);
                case TefkatPackage.CONDITION__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.CONDITION__QUERY, msgs);
                case TefkatPackage.CONDITION__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.CONDITION__COMPOUND_TERM, msgs);
                case TefkatPackage.CONDITION__TRULE_SRC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.CONDITION__TRULE_SRC, msgs);
                case TefkatPackage.CONDITION__TRULE_TGT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.CONDITION__TRULE_TGT, msgs);
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
                case TefkatPackage.CONDITION__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.CONDITION__PATTERN_DEFN, msgs);
                case TefkatPackage.CONDITION__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.CONDITION__QUERY, msgs);
                case TefkatPackage.CONDITION__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.CONDITION__COMPOUND_TERM, msgs);
                case TefkatPackage.CONDITION__TRULE_SRC:
                    return eBasicSetContainer(null, TefkatPackage.CONDITION__TRULE_SRC, msgs);
                case TefkatPackage.CONDITION__TRULE_TGT:
                    return eBasicSetContainer(null, TefkatPackage.CONDITION__TRULE_TGT, msgs);
                case TefkatPackage.CONDITION__ARG:
                    return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
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
                case TefkatPackage.CONDITION__PATTERN_DEFN:
                    return eContainer.eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.CONDITION__QUERY:
                    return eContainer.eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.CONDITION__COMPOUND_TERM:
                    return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.CONDITION__TRULE_SRC:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
                case TefkatPackage.CONDITION__TRULE_TGT:
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
            case TefkatPackage.CONDITION__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.CONDITION__QUERY:
                return getQuery();
            case TefkatPackage.CONDITION__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.CONDITION__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.CONDITION__TRULE_SRC:
                return getTRuleSrc();
            case TefkatPackage.CONDITION__TRULE_TGT:
                return getTRuleTgt();
            case TefkatPackage.CONDITION__ARG:
                return getArg();
            case TefkatPackage.CONDITION__RELATION:
                return getRelation();
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
            case TefkatPackage.CONDITION__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.CONDITION__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.CONDITION__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.CONDITION__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.CONDITION__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
                return;
            case TefkatPackage.CONDITION__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
                return;
            case TefkatPackage.CONDITION__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
                return;
            case TefkatPackage.CONDITION__RELATION:
                setRelation((String)newValue);
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
            case TefkatPackage.CONDITION__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.CONDITION__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.CONDITION__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.CONDITION__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.CONDITION__TRULE_SRC:
                setTRuleSrc((TRule)null);
                return;
            case TefkatPackage.CONDITION__TRULE_TGT:
                setTRuleTgt((TRule)null);
                return;
            case TefkatPackage.CONDITION__ARG:
                getArg().clear();
                return;
            case TefkatPackage.CONDITION__RELATION:
                setRelation(RELATION_EDEFAULT);
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
            case TefkatPackage.CONDITION__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.CONDITION__QUERY:
                return getQuery() != null;
            case TefkatPackage.CONDITION__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.CONDITION__CONTEXT:
                return context != null;
            case TefkatPackage.CONDITION__TRULE_SRC:
                return getTRuleSrc() != null;
            case TefkatPackage.CONDITION__TRULE_TGT:
                return getTRuleTgt() != null;
            case TefkatPackage.CONDITION__ARG:
                return arg != null && !arg.isEmpty();
            case TefkatPackage.CONDITION__RELATION:
                return RELATION_EDEFAULT == null ? relation != null : !RELATION_EDEFAULT.equals(relation);
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
        
        String result;
        
        if (getArg().size() == 1) {
            result = relation + "(" + arg.get(0) + ")";
        } else if (getArg().size() > 1) {
            result = arg.get(0) + relation + arg.get(1);
        } else {
            result = super.toString();
        }

        return result;
    }

} //ConditionImpl