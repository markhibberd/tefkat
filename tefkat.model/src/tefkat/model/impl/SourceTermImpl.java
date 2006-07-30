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
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.SourceTerm;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.SourceTermImpl#getTRuleSrc <em>TRule Src</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SourceTermImpl extends TermImpl implements SourceTerm {
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
    protected SourceTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.SOURCE_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule getTRuleSrc() {
        if (eContainerFeatureID != TefkatPackage.SOURCE_TERM__TRULE_SRC) return null;
        return (TRule)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTRuleSrc(TRule newTRuleSrc, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTRuleSrc, TefkatPackage.SOURCE_TERM__TRULE_SRC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTRuleSrc(TRule newTRuleSrc) {
        if (newTRuleSrc != eInternalContainer() || (eContainerFeatureID != TefkatPackage.SOURCE_TERM__TRULE_SRC && newTRuleSrc != null)) {
            if (EcoreUtil.isAncestor(this, newTRuleSrc))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTRuleSrc != null)
                msgs = ((InternalEObject)newTRuleSrc).eInverseAdd(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
            msgs = basicSetTRuleSrc(newTRuleSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.SOURCE_TERM__TRULE_SRC, newTRuleSrc, newTRuleSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTRuleSrc((TRule)otherEnd, msgs);
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                return basicSetTRuleSrc(null, msgs);
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                return eInternalContainer().eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                return getTRuleSrc();
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                setTRuleSrc((TRule)null);
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
            case TefkatPackage.SOURCE_TERM__TRULE_SRC:
                return getTRuleSrc() != null;
        }
        return super.eIsSet(featureID);
    }

} //SourceTermImpl
