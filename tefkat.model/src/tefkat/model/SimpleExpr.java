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
 * A representation of the model object '<em><b>Simple Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.SimpleExpr#getRepresentation <em>Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getSimpleExpr()
 * @model abstract="true"
 * @generated
 */
public interface SimpleExpr extends Expression {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Representation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Representation</em>' attribute.
     * @see #setRepresentation(String)
     * @see tefkat.model.TefkatPackage#getSimpleExpr_Representation()
     * @model required="true"
     * @generated
     */
    String getRepresentation();

    /**
     * Sets the value of the '{@link tefkat.model.SimpleExpr#getRepresentation <em>Representation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Representation</em>' attribute.
     * @see #getRepresentation()
     * @generated
     */
    void setRepresentation(String value);

} // SimpleExpr
