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

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.CompoundTerm;
import tefkat.model.ExtentVar;
import tefkat.model.PatternDefn;
import tefkat.model.Query;
import tefkat.model.TRule;
import tefkat.model.TefkatPackage;
import tefkat.model.TrackingUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracking Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.TrackingUseImpl#getTracking <em>Tracking</em>}</li>
 *   <li>{@link tefkat.model.impl.TrackingUseImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link tefkat.model.impl.TrackingUseImpl#getTrackingName <em>Tracking Name</em>}</li>
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

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
     * The default value of the '{@link #getTrackingName() <em>Tracking Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTrackingName()
     * @generated
     * @ordered
     */
	protected static final String TRACKING_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTrackingName() <em>Tracking Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTrackingName()
     * @generated
     * @ordered
     */
	protected String trackingName = TRACKING_NAME_EDEFAULT;

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
        return TefkatPackage.Literals.TRACKING_USE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTracking() {
        if (tracking != null && tracking.eIsProxy()) {
            InternalEObject oldTracking = (InternalEObject)tracking;
            tracking = (EClass)eResolveProxy(oldTracking);
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
            features = new EcoreEMap(TefkatPackage.Literals.FEATURE_VALUE_PAIR, FeatureValuePairImpl.class, this, TefkatPackage.TRACKING_USE__FEATURES);
        }
        return features;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getTrackingName() {
        return trackingName;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTrackingName(String newTrackingName) {
        String oldTrackingName = trackingName;
        trackingName = newTrackingName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.TRACKING_USE__TRACKING_NAME, oldTrackingName, trackingName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.TRACKING_USE__FEATURES:
                return ((InternalEList)getFeatures()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.TRACKING_USE__TRACKING:
                if (resolve) return getTracking();
                return basicGetTracking();
            case TefkatPackage.TRACKING_USE__FEATURES:
                if (coreType) return getFeatures();
                else return getFeatures().map();
            case TefkatPackage.TRACKING_USE__TRACKING_NAME:
                return getTrackingName();
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
            case TefkatPackage.TRACKING_USE__TRACKING:
                setTracking((EClass)newValue);
                return;
            case TefkatPackage.TRACKING_USE__FEATURES:
                ((EStructuralFeature.Setting)getFeatures()).set(newValue);
                return;
            case TefkatPackage.TRACKING_USE__TRACKING_NAME:
                setTrackingName((String)newValue);
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
            case TefkatPackage.TRACKING_USE__TRACKING:
                setTracking((EClass)null);
                return;
            case TefkatPackage.TRACKING_USE__FEATURES:
                getFeatures().clear();
                return;
            case TefkatPackage.TRACKING_USE__TRACKING_NAME:
                setTrackingName(TRACKING_NAME_EDEFAULT);
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
            case TefkatPackage.TRACKING_USE__TRACKING:
                return tracking != null;
            case TefkatPackage.TRACKING_USE__FEATURES:
                return features != null && !features.isEmpty();
            case TefkatPackage.TRACKING_USE__TRACKING_NAME:
                return TRACKING_NAME_EDEFAULT == null ? trackingName != null : !TRACKING_NAME_EDEFAULT.equals(trackingName);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();
        
        String name;
        
        if (null == trackingName) {
            name = "unknown tracking";
        } else {
            name = getTrackingName();
        }

        return name + getFeatures();
    }
    
} //TrackingUseImpl
