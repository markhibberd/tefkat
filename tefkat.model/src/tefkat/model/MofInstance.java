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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mof Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.MofInstance#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tefkat.model.MofInstance#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.model.MofInstance#isExact <em>Exact</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getMofInstance()
 * @model
 * @generated
 */
public interface MofInstance extends MofTerm{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Name</em>' containment reference.
     * @see #setTypeName(Expression)
     * @see tefkat.model.TefkatPackage#getMofInstance_TypeName()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getTypeName();

    /**
     * Sets the value of the '{@link tefkat.model.MofInstance#getTypeName <em>Type Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Name</em>' containment reference.
     * @see #getTypeName()
     * @generated
     */
    void setTypeName(Expression value);

    /**
     * Returns the value of the '<em><b>Instance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instance</em>' containment reference.
     * @see #setInstance(Expression)
     * @see tefkat.model.TefkatPackage#getMofInstance_Instance()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getInstance();

    /**
     * Sets the value of the '{@link tefkat.model.MofInstance#getInstance <em>Instance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance</em>' containment reference.
     * @see #getInstance()
     * @generated
     */
    void setInstance(Expression value);

    /**
     * Returns the value of the '<em><b>Exact</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exact</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exact</em>' attribute.
     * @see #setExact(boolean)
     * @see tefkat.model.TefkatPackage#getMofInstance_Exact()
     * @model
     * @generated
     */
    boolean isExact();

    /**
     * Sets the value of the '{@link tefkat.model.MofInstance#isExact <em>Exact</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exact</em>' attribute.
     * @see #isExact()
     * @generated
     */
    void setExact(boolean value);

} // MofInstance
