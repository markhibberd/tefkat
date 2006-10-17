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
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extent</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see tefkat.model.TefkatPackage#getExtent()
 * @model abstract="true"
 * @generated
 */
public interface Extent extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model required="true" instanceRequired="true"
     * @generated
     */
    boolean contains(EObject instance);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model type="org.eclipse.emf.ecore.EObject" theClassRequired="true" isExactlyRequired="true"
     * @generated
     */
    EList getObjectsByClass(EClass theClass, boolean isExactly);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    TreeIterator getAllContents();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model objRequired="true"
     * @generated
     */
    void add(EObject obj);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model objRequired="true"
     * @generated
     */
    void remove(EObject obj);

} // Extent
