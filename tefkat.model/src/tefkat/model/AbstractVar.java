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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Var</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.AbstractVar#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getAbstractVar()
 * @model abstract="true"
 * @generated
 */
public interface AbstractVar extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Scope</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.VarScope#getVars <em>Vars</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scope</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scope</em>' container reference.
     * @see #setScope(VarScope)
     * @see tefkat.model.TefkatPackage#getAbstractVar_Scope()
     * @see tefkat.model.VarScope#getVars
     * @model opposite="vars"
     * @generated
     */
    VarScope getScope();

    /**
     * Sets the value of the '{@link tefkat.model.AbstractVar#getScope <em>Scope</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scope</em>' container reference.
     * @see #getScope()
     * @generated
     */
    void setScope(VarScope value);

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
     * @see tefkat.model.TefkatPackage#getAbstractVar_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.model.AbstractVar#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // AbstractVar
