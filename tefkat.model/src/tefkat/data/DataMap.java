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
package tefkat.data;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.data.DataMap#getKey <em>Key</em>}</li>
 *   <li>{@link tefkat.data.DataMap#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.data.DataPackage#getDataMap()
 * @model
 * @generated
 */
public interface DataMap extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2005";

    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see tefkat.data.DataPackage#getDataMap_Key()
     * @model id="true" required="true"
     * @generated
     */
    String getKey();

    /**
     * Sets the value of the '{@link tefkat.data.DataMap#getKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
    void setKey(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' map.
     * The key is of type {@link java.lang.Object},
     * and the value is of type {@link tefkat.model.Expression},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' map.
     * @see tefkat.data.DataPackage#getDataMap_Value()
     * @model mapType="tefkat.data.DataEntry" keyType="java.lang.Object" valueType="tefkat.model.Expression"
     * @generated
     */
    EMap getValue();

} // DataMap
