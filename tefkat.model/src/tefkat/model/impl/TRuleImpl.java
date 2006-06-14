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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.NotTerm;
import tefkat.model.SourceTerm;
import tefkat.model.TRule;
import tefkat.model.TargetTerm;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;
import tefkat.model.Term;
import tefkat.model.Transformation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TRule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.TRuleImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleImpl#getTgt <em>Tgt</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleImpl#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.model.impl.TRuleImpl#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TRuleImpl extends VarScopeImpl implements TRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";
    
    /**
     * The cached value of the '{@link #getSrc() <em>Src</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected SourceTerm src = null;
    
    /**
     * The cached value of the '{@link #getTgt() <em>Tgt</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTgt()
     * @generated
     * @ordered
     */
    protected EList tgt = null;
    
    /**
     * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtended()
     * @generated
     * @ordered
     */
    protected EList extended = null;
    
    /**
     * The cached value of the '{@link #getSuperseded() <em>Superseded</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseded()
     * @generated
     * @ordered
     */
    protected EList superseded = null;
    
    /**
     * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected static final boolean ABSTRACT_EDEFAULT = false;
    
    /**
     * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected boolean abstract_ = ABSTRACT_EDEFAULT;

    /**
     * Cached collection of all the source Terms in the transitive closure
     * of the extends and supersedes relationship of the TRule.
     */
    private Collection sourceTerms = null;

    /**
     * Cached collection of all the override Terms in the transitive closure
     * of the extends and supersedes relationship of the TRule.
     */
    private Collection overrideTerms = null;

    /**
     * Cached collection of all the target Terms in the transitive closure
     * of the extends relationship of the TRule.
     */
    private Collection targetTerms = null;
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected TRuleImpl() {
        super();
        eAdapters().add(new AdapterImpl() {

            public void notifyChanged(Notification msg) {
                switch (msg.getFeatureID(TRule.class)) {
                case TefkatPackage.TRULE__SUPERSEDED:
                    sourceTerms = null;
                    overrideTerms = null;
                    
                    Transformation t = getTransformation();
                    switch(msg.getEventType()) {
                    case Notification.ADD:
                        t.addSupersedes(TRuleImpl.this, (TRule) msg.getNewValue());
                        break;
                    case Notification.ADD_MANY:
                        for (Iterator itr = ((Collection) msg.getNewValue()).iterator(); itr.hasNext(); ) {
                            TRule superseded = (TRule) itr.next();
                            t.addSupersedes(TRuleImpl.this, superseded);
                        }
                        break;
                    case Notification.REMOVE:
                        t.removeSupersedes(TRuleImpl.this, (TRule) msg.getNewValue());
                        break;
                    case Notification.REMOVE_MANY:
                        for (Iterator itr = ((Collection) msg.getNewValue()).iterator(); itr.hasNext(); ) {
                            TRule superseded = (TRule) itr.next();
                            t.removeSupersedes(TRuleImpl.this, superseded);
                        }
                        break;
                    }
                    break;
                case TefkatPackage.TRULE__EXTENDED:
                    sourceTerms = null;
                    overrideTerms = null;
                    targetTerms = null;
                    break;
                }
            }
            
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTRule();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transformation getTransformation() {
        if (eContainerFeatureID != TefkatPackage.TRULE__TRANSFORMATION) return null;
        return (Transformation)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransformation(Transformation newTransformation) {
        if (newTransformation != eContainer || (eContainerFeatureID != TefkatPackage.TRULE__TRANSFORMATION && newTransformation != null)) {
            if (EcoreUtil.isAncestor(this, newTransformation))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTransformation != null)
                msgs = ((InternalEObject)newTransformation).eInverseAdd(this, TefkatPackage.TRANSFORMATION__TRULE, Transformation.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newTransformation, TefkatPackage.TRULE__TRANSFORMATION, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TRULE__TRANSFORMATION, newTransformation, newTransformation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTerm getSrc() {
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSrc(SourceTerm newSrc, NotificationChain msgs) {
        SourceTerm oldSrc = src;
        src = newSrc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.TRULE__SRC, oldSrc, newSrc);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSrc(SourceTerm newSrc) {
        if (newSrc != src) {
            NotificationChain msgs = null;
            if (src != null)
                msgs = ((InternalEObject)src).eInverseRemove(this, TefkatPackage.SOURCE_TERM__TRULE_SRC, SourceTerm.class, msgs);
            if (newSrc != null)
                msgs = ((InternalEObject)newSrc).eInverseAdd(this, TefkatPackage.SOURCE_TERM__TRULE_SRC, SourceTerm.class, msgs);
            msgs = basicSetSrc(newSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TRULE__SRC, newSrc, newSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTgt() {
        if (tgt == null) {
            tgt = new EObjectContainmentWithInverseEList(TargetTerm.class, this, TefkatPackage.TRULE__TGT, TefkatPackage.TARGET_TERM__TRULE_TGT);
        }
        return tgt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtended() {
        if (extended == null) {
            extended = new EObjectResolvingEList(TRule.class, this, TefkatPackage.TRULE__EXTENDED);
        }
        return extended;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseded() {
        if (superseded == null) {
            superseded = new EObjectResolvingEList(TRule.class, this, TefkatPackage.TRULE__SUPERSEDED);
        }
        return superseded;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAbstract() {
        return abstract_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAbstract(boolean newAbstract) {
        boolean oldAbstract = abstract_;
        abstract_ = newAbstract;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TRULE__ABSTRACT, oldAbstract, abstract_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Collection getGoal() {
        Collection goal = new ArrayList();
        
        goal.addAll(getSourceTerms());
        goal.addAll(getOverrideTerms());
        goal.addAll(getTargetTerms());
        
        return goal;
    }
    
    /**
     * Returns all the source Terms in the transitive closure of the extends and
     * supersedes relationship of the TRule.
     * 
     * @return
     */
    public Collection getSourceTerms() {
        if (null == sourceTerms) {
            sourceTerms = new ArrayList();

            Term srcTerm = getSrc();
            if (null != srcTerm) {
                sourceTerms.add(srcTerm);
            }

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                sourceTerms.addAll(extRule.getSourceTerms());
            }

            for (Iterator itr = getSuperseded().iterator(); itr.hasNext();) {
                TRule supRule = (TRule) itr.next();
                sourceTerms.addAll(supRule.getSourceTerms());
            }
        }
        return sourceTerms;
    }

    /**
     * Returns all the overriding Terms in the transitive closure of the
     * extends and supersedes relationship of the TRule.
     * 
     * @return
     */
    public Collection getOverrideTerms() {
        if (null == overrideTerms) {
            overrideTerms = new ArrayList();

            Collection supersedingRules = getTransformation().getSupersedingRules(this);
            for (Iterator itr = supersedingRules.iterator(); itr.hasNext();) {
                TRule supersedingRule = (TRule) itr.next();

                NotTerm override = TefkatFactory.eINSTANCE.createNotTerm();
                override.getTerm().addAll(supersedingRule.getSourceTerms());
                overrideTerms.add(override);
            }

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                overrideTerms.addAll(extRule.getOverrideTerms());
            }
        }

        return overrideTerms;
    }

    /**
     * Returns all the target Terms in the transitive closure of the extends
     * relationship of the TRule.
     * 
     * @param rule
     * @return
     */
    public Collection getTargetTerms() {
        if (null == targetTerms) {
            targetTerms = new ArrayList();

            targetTerms.addAll(getTgt());

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                targetTerms.addAll(extRule.getTargetTerms());
            }
        }
        return targetTerms;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRULE__VARS:
                    return ((InternalEList)getVars()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRULE__TRANSFORMATION:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRULE__TRANSFORMATION, msgs);
                case TefkatPackage.TRULE__SRC:
                    if (src != null)
                        msgs = ((InternalEObject)src).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.TRULE__SRC, null, msgs);
                    return basicSetSrc((SourceTerm)otherEnd, msgs);
                case TefkatPackage.TRULE__TGT:
                    return ((InternalEList)getTgt()).basicAdd(otherEnd, msgs);
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
                case TefkatPackage.TRULE__VARS:
                    return ((InternalEList)getVars()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRULE__TRANSFORMATION:
                    return eBasicSetContainer(null, TefkatPackage.TRULE__TRANSFORMATION, msgs);
                case TefkatPackage.TRULE__SRC:
                    return basicSetSrc(null, msgs);
                case TefkatPackage.TRULE__TGT:
                    return ((InternalEList)getTgt()).basicRemove(otherEnd, msgs);
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
                case TefkatPackage.TRULE__TRANSFORMATION:
                    return eContainer.eInverseRemove(this, TefkatPackage.TRANSFORMATION__TRULE, Transformation.class, msgs);
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
            case TefkatPackage.TRULE__VARS:
                return getVars();
            case TefkatPackage.TRULE__NAME:
                return getName();
            case TefkatPackage.TRULE__COMMENTS:
                return getComments();
            case TefkatPackage.TRULE__TRANSFORMATION:
                return getTransformation();
            case TefkatPackage.TRULE__SRC:
                return getSrc();
            case TefkatPackage.TRULE__TGT:
                return getTgt();
            case TefkatPackage.TRULE__EXTENDED:
                return getExtended();
            case TefkatPackage.TRULE__SUPERSEDED:
                return getSuperseded();
            case TefkatPackage.TRULE__ABSTRACT:
                return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
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
            case TefkatPackage.TRULE__VARS:
                getVars().clear();
                getVars().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE__NAME:
                setName((String)newValue);
                return;
            case TefkatPackage.TRULE__COMMENTS:
                getComments().clear();
                getComments().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE__TRANSFORMATION:
                setTransformation((Transformation)newValue);
                return;
            case TefkatPackage.TRULE__SRC:
                setSrc((SourceTerm)newValue);
                return;
            case TefkatPackage.TRULE__TGT:
                getTgt().clear();
                getTgt().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE__EXTENDED:
                getExtended().clear();
                getExtended().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE__SUPERSEDED:
                getSuperseded().clear();
                getSuperseded().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRULE__ABSTRACT:
                setAbstract(((Boolean)newValue).booleanValue());
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
            case TefkatPackage.TRULE__VARS:
                getVars().clear();
                return;
            case TefkatPackage.TRULE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TefkatPackage.TRULE__COMMENTS:
                getComments().clear();
                return;
            case TefkatPackage.TRULE__TRANSFORMATION:
                setTransformation((Transformation)null);
                return;
            case TefkatPackage.TRULE__SRC:
                setSrc((SourceTerm)null);
                return;
            case TefkatPackage.TRULE__TGT:
                getTgt().clear();
                return;
            case TefkatPackage.TRULE__EXTENDED:
                getExtended().clear();
                return;
            case TefkatPackage.TRULE__SUPERSEDED:
                getSuperseded().clear();
                return;
            case TefkatPackage.TRULE__ABSTRACT:
                setAbstract(ABSTRACT_EDEFAULT);
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
            case TefkatPackage.TRULE__VARS:
                return vars != null && !vars.isEmpty();
            case TefkatPackage.TRULE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.TRULE__COMMENTS:
                return comments != null && !comments.isEmpty();
            case TefkatPackage.TRULE__TRANSFORMATION:
                return getTransformation() != null;
            case TefkatPackage.TRULE__SRC:
                return src != null;
            case TefkatPackage.TRULE__TGT:
                return tgt != null && !tgt.isEmpty();
            case TefkatPackage.TRULE__EXTENDED:
                return extended != null && !extended.isEmpty();
            case TefkatPackage.TRULE__SUPERSEDED:
                return superseded != null && !superseded.isEmpty();
            case TefkatPackage.TRULE__ABSTRACT:
                return abstract_ != ABSTRACT_EDEFAULT;
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
        
        return (abstract_ ? "ABSTRACT " : "") + "RULE " + name;
    }
    
} //TRuleImpl
