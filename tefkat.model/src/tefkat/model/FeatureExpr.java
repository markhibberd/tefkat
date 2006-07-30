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
 * A representation of the model object '<em><b>Feature Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.FeatureExpr#isOperation <em>Operation</em>}</li>
 *   <li>{@link tefkat.model.FeatureExpr#isCollect <em>Collect</em>}</li>
 *   <li>{@link tefkat.model.FeatureExpr#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getFeatureExpr()
 * @model
 * @generated
 */
public interface FeatureExpr extends CompoundExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' attribute.
     * @see #setOperation(boolean)
     * @see tefkat.model.TefkatPackage#getFeatureExpr_Operation()
     * @model required="true"
     * @generated
     */
    boolean isOperation();

    /**
     * Sets the value of the '{@link tefkat.model.FeatureExpr#isOperation <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' attribute.
     * @see #isOperation()
     * @generated
     */
    void setOperation(boolean value);

    /**
     * Returns the value of the '<em><b>Collect</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Collect</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Collect</em>' attribute.
     * @see #setCollect(boolean)
     * @see tefkat.model.TefkatPackage#getFeatureExpr_Collect()
     * @model required="true"
     * @generated
     */
    boolean isCollect();

    /**
     * Sets the value of the '{@link tefkat.model.FeatureExpr#isCollect <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collect</em>' attribute.
     * @see #isCollect()
     * @generated
     */
    void setCollect(boolean value);

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
     * @see tefkat.model.TefkatPackage#getFeatureExpr_Feature()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getFeature();

    /**
     * Sets the value of the '{@link tefkat.model.FeatureExpr#getFeature <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature</em>' containment reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(Expression value);

} // FeatureExpr
