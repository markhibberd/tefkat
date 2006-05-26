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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.VarScope#getVars <em>Vars</em>}</li>
 *   <li>{@link tefkat.model.VarScope#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.VarScope#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getVarScope()
 * @model
 * @generated
 */
public interface VarScope extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Vars</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.AbstractVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.AbstractVar#getScope <em>Scope</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vars</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vars</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getVarScope_Vars()
     * @see tefkat.model.AbstractVar#getScope
     * @model type="tefkat.model.AbstractVar" opposite="scope" containment="true"
     * @generated
     */
    EList getVars();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see tefkat.model.TefkatPackage#getVarScope_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.model.VarScope#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Comments</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comments</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comments</em>' attribute list.
     * @see tefkat.model.TefkatPackage#getVarScope_Comments()
     * @model type="java.lang.String" unique="false"
     * @generated
     */
    EList getComments();

} // VarScope
