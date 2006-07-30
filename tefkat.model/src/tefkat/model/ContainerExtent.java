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

import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Extent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.ContainerExtent#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getContainerExtent()
 * @model
 * @generated
 */
public interface ContainerExtent extends Extent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Resource</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource</em>' attribute.
     * @see #setResource(Resource)
     * @see tefkat.model.TefkatPackage#getContainerExtent_Resource()
     * @model required="true"
     * @generated
     */
    Resource getResource();

    /**
     * Sets the value of the '{@link tefkat.model.ContainerExtent#getResource <em>Resource</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource</em>' attribute.
     * @see #getResource()
     * @generated
     */
    void setResource(Resource value);

} // ContainerExtent
