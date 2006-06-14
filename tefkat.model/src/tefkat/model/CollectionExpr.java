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
 * A representation of the model object '<em><b>Collection Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.CollectionExpr#isUnique <em>Unique</em>}</li>
 *   <li>{@link tefkat.model.CollectionExpr#isOrdered <em>Ordered</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getCollectionExpr()
 * @model
 * @generated
 */
public interface CollectionExpr extends CompoundExpr{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Unique</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unique</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique</em>' attribute.
     * @see #setUnique(boolean)
     * @see tefkat.model.TefkatPackage#getCollectionExpr_Unique()
     * @model required="true"
     * @generated
     */
    boolean isUnique();

    /**
     * Sets the value of the '{@link tefkat.model.CollectionExpr#isUnique <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unique</em>' attribute.
     * @see #isUnique()
     * @generated
     */
    void setUnique(boolean value);

    /**
     * Returns the value of the '<em><b>Ordered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordered</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ordered</em>' attribute.
     * @see #setOrdered(boolean)
     * @see tefkat.model.TefkatPackage#getCollectionExpr_Ordered()
     * @model required="true"
     * @generated
     */
    boolean isOrdered();

    /**
     * Sets the value of the '{@link tefkat.model.CollectionExpr#isOrdered <em>Ordered</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ordered</em>' attribute.
     * @see #isOrdered()
     * @generated
     */
    void setOrdered(boolean value);

} // CollectionExpr
