/**
 * <copyright>
 * </copyright>
 *
 * $Id: TrackingUseImpl.java,v 1.4 2005/05/16 01:52:26 lawley Exp $
 */
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CompoundTerm;
import com.dstc.tefkat.model.ExtentVar;
import com.dstc.tefkat.model.PatternDefn;
import com.dstc.tefkat.model.Query;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.TefkatPackage;
import com.dstc.tefkat.model.TrackingUse;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracking Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.TrackingUseImpl#getTracking <em>Tracking</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.TrackingUseImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackingUseImpl extends SimpleTermImpl implements TrackingUse {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

    /**
     * The cached value of the '{@link #getTracking() <em>Tracking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTracking()
     * @generated
     * @ordered
     */
    protected EClass tracking = null;

    /**
     * The cached value of the '{@link #getFeatures() <em>Features</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatures()
     * @generated
     * @ordered
     */
    protected EMap features = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TrackingUseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTrackingUse();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTracking() {
        if (tracking != null && tracking.eIsProxy()) {
            EClass oldTracking = tracking;
            tracking = (EClass)eResolveProxy((InternalEObject)tracking);
            if (tracking != oldTracking) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TefkatPackage.TRACKING_USE__TRACKING, oldTracking, tracking));
            }
        }
        return tracking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass basicGetTracking() {
        return tracking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTracking(EClass newTracking) {
        EClass oldTracking = tracking;
        tracking = newTracking;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TRACKING_USE__TRACKING, oldTracking, tracking));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getFeatures() {
        if (features == null) {
            features = new EcoreEMap(TefkatPackage.eINSTANCE.getFeatureValuePair(), FeatureValuePairImpl.class, this, TefkatPackage.TRACKING_USE__FEATURES);
        }
        return features;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRACKING_USE__PATTERN_DEFN, msgs);
                case TefkatPackage.TRACKING_USE__QUERY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRACKING_USE__QUERY, msgs);
                case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRACKING_USE__COMPOUND_TERM, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_SRC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRACKING_USE__TRULE_SRC, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_TGT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.TRACKING_USE__TRULE_TGT, msgs);
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
                case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                    return eBasicSetContainer(null, TefkatPackage.TRACKING_USE__PATTERN_DEFN, msgs);
                case TefkatPackage.TRACKING_USE__QUERY:
                    return eBasicSetContainer(null, TefkatPackage.TRACKING_USE__QUERY, msgs);
                case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                    return eBasicSetContainer(null, TefkatPackage.TRACKING_USE__COMPOUND_TERM, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_SRC:
                    return eBasicSetContainer(null, TefkatPackage.TRACKING_USE__TRULE_SRC, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_TGT:
                    return eBasicSetContainer(null, TefkatPackage.TRACKING_USE__TRULE_TGT, msgs);
                case TefkatPackage.TRACKING_USE__FEATURES:
                    return ((InternalEList)getFeatures()).basicRemove(otherEnd, msgs);
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
                case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
                case TefkatPackage.TRACKING_USE__QUERY:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.QUERY__TERM, Query.class, msgs);
                case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_SRC:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.TRULE__SRC, TRule.class, msgs);
                case TefkatPackage.TRACKING_USE__TRULE_TGT:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.TRULE__TGT, TRule.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.TRACKING_USE__QUERY:
                return getQuery();
            case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                return getCompoundTerm();
            case TefkatPackage.TRACKING_USE__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case TefkatPackage.TRACKING_USE__TRULE_SRC:
                return getTRuleSrc();
            case TefkatPackage.TRACKING_USE__TRULE_TGT:
                return getTRuleTgt();
            case TefkatPackage.TRACKING_USE__TRACKING:
                if (resolve) return getTracking();
                return basicGetTracking();
            case TefkatPackage.TRACKING_USE__FEATURES:
                return getFeatures();
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
            case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case TefkatPackage.TRACKING_USE__QUERY:
                setQuery((Query)newValue);
                return;
            case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case TefkatPackage.TRACKING_USE__CONTEXT:
                setContext((ExtentVar)newValue);
                return;
            case TefkatPackage.TRACKING_USE__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
                return;
            case TefkatPackage.TRACKING_USE__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
                return;
            case TefkatPackage.TRACKING_USE__TRACKING:
                setTracking((EClass)newValue);
                return;
            case TefkatPackage.TRACKING_USE__FEATURES:
                getFeatures().clear();
                getFeatures().addAll((Collection)newValue);
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
            case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case TefkatPackage.TRACKING_USE__QUERY:
                setQuery((Query)null);
                return;
            case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case TefkatPackage.TRACKING_USE__CONTEXT:
                setContext((ExtentVar)null);
                return;
            case TefkatPackage.TRACKING_USE__TRULE_SRC:
                setTRuleSrc((TRule)null);
                return;
            case TefkatPackage.TRACKING_USE__TRULE_TGT:
                setTRuleTgt((TRule)null);
                return;
            case TefkatPackage.TRACKING_USE__TRACKING:
                setTracking((EClass)null);
                return;
            case TefkatPackage.TRACKING_USE__FEATURES:
                getFeatures().clear();
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
            case TefkatPackage.TRACKING_USE__PATTERN_DEFN:
                return getPatternDefn() != null;
            case TefkatPackage.TRACKING_USE__QUERY:
                return getQuery() != null;
            case TefkatPackage.TRACKING_USE__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case TefkatPackage.TRACKING_USE__CONTEXT:
                return context != null;
            case TefkatPackage.TRACKING_USE__TRULE_SRC:
                return getTRuleSrc() != null;
            case TefkatPackage.TRACKING_USE__TRULE_TGT:
                return getTRuleTgt() != null;
            case TefkatPackage.TRACKING_USE__TRACKING:
                return tracking != null;
            case TefkatPackage.TRACKING_USE__FEATURES:
                return features != null && !features.isEmpty();
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
        
        String name;
        
        if (null == tracking) {
            name = "unknown tracking";
        } else {
            name = getTracking().getName();
        }

        return name + getFeatures();
    }
    
} //TrackingUseImpl
