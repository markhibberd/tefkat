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
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Query#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.model.Query#getParameterVar <em>Parameter Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getQuery()
 * @model
 * @generated
 */
public interface Query extends PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Term</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.Term#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Term</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Term</em>' containment reference.
     * @see #setTerm(Term)
     * @see tefkat.model.TefkatPackage#getQuery_Term()
     * @see tefkat.model.Term#getQuery
     * @model opposite="query" containment="true" required="true"
     * @generated
     */
    Term getTerm();

    /**
     * Sets the value of the '{@link tefkat.model.Query#getTerm <em>Term</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Term</em>' containment reference.
     * @see #getTerm()
     * @generated
     */
    void setTerm(Term value);

    /**
     * Returns the value of the '<em><b>Parameter Var</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.PatternVar}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter Var</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Var</em>' reference list.
     * @see tefkat.model.TefkatPackage#getQuery_ParameterVar()
     * @model type="tefkat.model.PatternVar"
     * @generated
     */
    EList getParameterVar();

} // Query
