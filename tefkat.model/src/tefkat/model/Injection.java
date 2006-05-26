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
 * A representation of the model object '<em><b>Injection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Injection#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.Injection#getSources <em>Sources</em>}</li>
 *   <li>{@link tefkat.model.Injection#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getInjection()
 * @model
 * @generated
 */
public interface Injection extends TargetTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

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
     * @see tefkat.model.TefkatPackage#getInjection_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.model.Injection#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.Expression}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sources</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getInjection_Sources()
     * @model type="tefkat.model.Expression" containment="true"
     * @generated
     */
    EList getSources();

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(VarUse)
     * @see tefkat.model.TefkatPackage#getInjection_Target()
     * @model containment="true" required="true"
     * @generated
     */
    VarUse getTarget();

    /**
     * Sets the value of the '{@link tefkat.model.Injection#getTarget <em>Target</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(VarUse value);

} // Injection
