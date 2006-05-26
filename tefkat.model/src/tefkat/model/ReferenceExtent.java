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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Extent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.ReferenceExtent#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getReferenceExtent()
 * @model
 * @generated
 */
public interface ReferenceExtent extends Extent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Resources</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.resource.Resource}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' attribute list.
     * @see tefkat.model.TefkatPackage#getReferenceExtent_Resources()
     * @model type="org.eclipse.emf.ecore.resource.Resource"
     * @generated
     */
    EList getResources();

} // ReferenceExtent
