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
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Expression#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends EObject{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.CompoundExpr#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expr</em>' container reference.
     * @see #setExpr(CompoundExpr)
     * @see tefkat.model.TefkatPackage#getExpression_Expr()
     * @see tefkat.model.CompoundExpr#getArg
     * @model opposite="arg"
     * @generated
     */
    CompoundExpr getExpr();

    /**
     * Sets the value of the '{@link tefkat.model.Expression#getExpr <em>Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expr</em>' container reference.
     * @see #getExpr()
     * @generated
     */
    void setExpr(CompoundExpr value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    Expression copy();

} // Expression
