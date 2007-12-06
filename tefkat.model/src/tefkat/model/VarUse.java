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
 * A representation of the model object '<em><b>Var Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.VarUse#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getVarUse()
 * @model
 * @generated
 */
public interface VarUse extends Expression {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * Returns the value of the '<em><b>Var</b></em>' reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.Var#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var</em>' reference.
     * @see #setVar(Var)
     * @see tefkat.model.TefkatPackage#getVarUse_Var()
     * @see tefkat.model.Var#getUsages
     * @model opposite="usages" required="true"
     * @generated
     */
    Var getVar();

    /**
     * Sets the value of the '{@link tefkat.model.VarUse#getVar <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var</em>' reference.
     * @see #getVar()
     * @generated
     */
    void setVar(Var value);

} // VarUse
