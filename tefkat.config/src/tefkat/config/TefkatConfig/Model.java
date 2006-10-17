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
package tefkat.config.TefkatConfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.Model#getLocationUri <em>Location Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * Returns the value of the '<em><b>Location Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location Uri</em>' attribute.
     * @see #setLocationUri(String)
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getModel_LocationUri()
     * @model required="true"
     * @generated
     */
    String getLocationUri();

    /**
     * Sets the value of the '{@link tefkat.config.TefkatConfig.Model#getLocationUri <em>Location Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location Uri</em>' attribute.
     * @see #getLocationUri()
     * @generated
     */
    void setLocationUri(String value);

} // Model
