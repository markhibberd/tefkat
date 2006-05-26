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
package tefkat.model;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tracking Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.TrackingUse#getTracking <em>Tracking</em>}</li>
 *   <li>{@link tefkat.model.TrackingUse#getFeatures <em>Features</em>}</li>
 *   <li>{@link tefkat.model.TrackingUse#getTrackingName <em>Tracking Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTrackingUse()
 * @model
 * @generated
 */
public interface TrackingUse extends SimpleTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Tracking</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tracking</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tracking</em>' reference.
     * @see #setTracking(EClass)
     * @see tefkat.model.TefkatPackage#getTrackingUse_Tracking()
     * @model transient="true"
     * @generated
     */
    EClass getTracking();

    /**
     * Sets the value of the '{@link tefkat.model.TrackingUse#getTracking <em>Tracking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tracking</em>' reference.
     * @see #getTracking()
     * @generated
     */
    void setTracking(EClass value);

    /**
     * Returns the value of the '<em><b>Features</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link tefkat.model.Expression},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Features</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Features</em>' map.
     * @see tefkat.model.TefkatPackage#getTrackingUse_Features()
     * @model mapType="tefkat.model.FeatureValuePair" keyType="java.lang.String" valueType="tefkat.model.Expression"
     * @generated
     */
    EMap getFeatures();

    /**
     * Returns the value of the '<em><b>Tracking Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tracking Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tracking Name</em>' attribute.
     * @see #setTrackingName(String)
     * @see tefkat.model.TefkatPackage#getTrackingUse_TrackingName()
     * @model required="true"
     * @generated
     */
    String getTrackingName();

    /**
     * Sets the value of the '{@link tefkat.model.TrackingUse#getTrackingName <em>Tracking Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tracking Name</em>' attribute.
     * @see #getTrackingName()
     * @generated
     */
    void setTrackingName(String value);

} // TrackingUse
