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
 * A representation of the model object '<em><b>Mof Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.MofOrder#getLesser <em>Lesser</em>}</li>
 *   <li>{@link tefkat.model.MofOrder#getGreater <em>Greater</em>}</li>
 *   <li>{@link tefkat.model.MofOrder#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.model.MofOrder#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getMofOrder()
 * @model
 * @generated
 */
public interface MofOrder extends MofTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Lesser</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lesser</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lesser</em>' containment reference.
     * @see #setLesser(Expression)
     * @see tefkat.model.TefkatPackage#getMofOrder_Lesser()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getLesser();

    /**
     * Sets the value of the '{@link tefkat.model.MofOrder#getLesser <em>Lesser</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lesser</em>' containment reference.
     * @see #getLesser()
     * @generated
     */
    void setLesser(Expression value);

    /**
     * Returns the value of the '<em><b>Greater</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Greater</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Greater</em>' containment reference.
     * @see #setGreater(Expression)
     * @see tefkat.model.TefkatPackage#getMofOrder_Greater()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getGreater();

    /**
     * Sets the value of the '{@link tefkat.model.MofOrder#getGreater <em>Greater</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Greater</em>' containment reference.
     * @see #getGreater()
     * @generated
     */
    void setGreater(Expression value);

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
     * @see tefkat.model.TefkatPackage#getMofOrder_Instance()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getInstance();

    /**
     * Sets the value of the '{@link tefkat.model.MofOrder#getInstance <em>Instance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance</em>' containment reference.
     * @see #getInstance()
     * @generated
     */
    void setInstance(Expression value);

    /**
     * Returns the value of the '<em><b>Feature</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature</em>' containment reference.
     * @see #setFeature(Expression)
     * @see tefkat.model.TefkatPackage#getMofOrder_Feature()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getFeature();

    /**
     * Sets the value of the '{@link tefkat.model.MofOrder#getFeature <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature</em>' containment reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(Expression value);

} // MofOrder
