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
package tefkat.config.TefkatConfig.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TefkatConfigPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.impl.ModelImpl#getLocationUri <em>Location Uri</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.ModelImpl#getVarGroup <em>Var Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends EObjectImpl implements Model {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * The default value of the '{@link #getLocationUri() <em>Location Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocationUri()
     * @generated
     * @ordered
     */
    protected static final String LOCATION_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLocationUri() <em>Location Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocationUri()
     * @generated
     * @ordered
     */
    protected String locationUri = LOCATION_URI_EDEFAULT;

    /**
     * The default value of the '{@link #getVarGroup() <em>Var Group</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVarGroup()
     * @generated
     * @ordered
     */
    protected static final String VAR_GROUP_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVarGroup() <em>Var Group</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVarGroup()
     * @generated
     * @ordered
     */
    protected String varGroup = VAR_GROUP_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatConfigPackage.Literals.MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLocationUri() {
        return locationUri;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocationUri(String newLocationUri) {
        String oldLocationUri = locationUri;
        locationUri = newLocationUri;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.MODEL__LOCATION_URI, oldLocationUri, locationUri));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVarGroup() {
        return varGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVarGroup(String newVarGroup) {
        String oldVarGroup = varGroup;
        varGroup = newVarGroup;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.MODEL__VAR_GROUP, oldVarGroup, varGroup));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatConfigPackage.MODEL__LOCATION_URI:
                return getLocationUri();
            case TefkatConfigPackage.MODEL__VAR_GROUP:
                return getVarGroup();
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
            case TefkatConfigPackage.MODEL__LOCATION_URI:
                setLocationUri((String)newValue);
                return;
            case TefkatConfigPackage.MODEL__VAR_GROUP:
                setVarGroup((String)newValue);
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
            case TefkatConfigPackage.MODEL__LOCATION_URI:
                setLocationUri(LOCATION_URI_EDEFAULT);
                return;
            case TefkatConfigPackage.MODEL__VAR_GROUP:
                setVarGroup(VAR_GROUP_EDEFAULT);
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
            case TefkatConfigPackage.MODEL__LOCATION_URI:
                return LOCATION_URI_EDEFAULT == null ? locationUri != null : !LOCATION_URI_EDEFAULT.equals(locationUri);
            case TefkatConfigPackage.MODEL__VAR_GROUP:
                return VAR_GROUP_EDEFAULT == null ? varGroup != null : !VAR_GROUP_EDEFAULT.equals(varGroup);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (locationUri: ");
        result.append(locationUri);
        result.append(", varGroup: ");
        result.append(varGroup);
        result.append(')');
        return result.toString();
    }

} //ModelImpl
