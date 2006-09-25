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


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.model.AbstractVar;
import tefkat.model.IfTerm;
import tefkat.model.TRule;
import tefkat.model.TargetTerm;
import tefkat.model.TefkatPackage;
import tefkat.model.Term;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.IfTermImpl#getTRuleTgt <em>TRule Tgt</em>}</li>
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

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
        return TefkatPackage.Literals.IF_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule getTRuleTgt() {
        if (eContainerFeatureID != TefkatPackage.IF_TERM__TRULE_TGT) return null;
        return (TRule)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTRuleTgt(TRule newTRuleTgt, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTRuleTgt, TefkatPackage.IF_TERM__TRULE_TGT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTRuleTgt(TRule newTRuleTgt) {
        if (newTRuleTgt != eInternalContainer() || (eContainerFeatureID != TefkatPackage.IF_TERM__TRULE_TGT && newTRuleTgt != null)) {
            if (EcoreUtil.isAncestor(this, newTRuleTgt))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTRuleTgt != null)
                msgs = ((InternalEObject)newTRuleTgt).eInverseAdd(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
            msgs = basicSetTRuleTgt(newTRuleTgt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.IF_TERM__TRULE_TGT, newTRuleTgt, newTRuleTgt));
    }

    /**
     * <!-- begin-user-doc -->
     * Returns a list of AbstractVars that are referenced (by VarUse) in the <em>condition</em> of this IfTerm but not anywhere else (except the <em>then</em> or <em>else</em> terms.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getNonLocalVars() {
        EList nonLocalVars = new BasicEList();
        Set conditionVars = new HashSet();
        Set usages = new HashSet();

        // Find the Vars that occur in the condition of the IF
        for (Iterator itr = ((Term) getTerm().get(0)).eAllContents(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            if (obj instanceof VarUse) {
                conditionVars.add(((VarUse) obj).getVar());
            }
        }

        // Find all VarUses that occur in the whole IF-THEN-ELSE
        for (Iterator itr = eAllContents(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            if (obj instanceof VarUse) {
                usages.add(obj);
            }
        }
        
        // Find just those condition Vars that are NOT only referenced in the IF-THEN-ELSE
        for (Iterator varItr = conditionVars.iterator(); varItr.hasNext(); ) {
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.IF_TERM__TRULE_TGT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTRuleTgt((TRule)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.IF_TERM__TRULE_TGT:
                return basicSetTRuleTgt(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case TefkatPackage.IF_TERM__TRULE_TGT:
                return eInternalContainer().eInverseRemove(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.IF_TERM__TRULE_TGT:
                return getTRuleTgt();
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
            case TefkatPackage.IF_TERM__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
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
            case TefkatPackage.IF_TERM__TRULE_TGT:
                setTRuleTgt((TRule)null);
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
            case TefkatPackage.IF_TERM__TRULE_TGT:
                return getTRuleTgt() != null;
        }
        return super.eIsSet(featureID);
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
