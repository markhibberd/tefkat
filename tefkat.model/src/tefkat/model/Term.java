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
 * A representation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Term#getPatternDefn <em>Pattern Defn</em>}</li>
 *   <li>{@link tefkat.model.Term#getQuery <em>Query</em>}</li>
 *   <li>{@link tefkat.model.Term#getCompoundTerm <em>Compound Term</em>}</li>
 *   <li>{@link tefkat.model.Term#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTerm()
 * @model abstract="true"
 * @generated
 */
public interface Term extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Pattern Defn</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.PatternDefn#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Defn</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Defn</em>' container reference.
     * @see #setPatternDefn(PatternDefn)
     * @see tefkat.model.TefkatPackage#getTerm_PatternDefn()
     * @see tefkat.model.PatternDefn#getTerm
     * @model opposite="term"
     * @generated
     */
    PatternDefn getPatternDefn();

    /**
     * Sets the value of the '{@link tefkat.model.Term#getPatternDefn <em>Pattern Defn</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Defn</em>' container reference.
     * @see #getPatternDefn()
     * @generated
     */
    void setPatternDefn(PatternDefn value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.Query#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' container reference.
     * @see #setQuery(Query)
     * @see tefkat.model.TefkatPackage#getTerm_Query()
     * @see tefkat.model.Query#getTerm
     * @model opposite="term"
     * @generated
     */
    Query getQuery();

    /**
     * Sets the value of the '{@link tefkat.model.Term#getQuery <em>Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' container reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery(Query value);

    /**
     * Returns the value of the '<em><b>Compound Term</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.CompoundTerm#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compound Term</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Compound Term</em>' container reference.
     * @see #setCompoundTerm(CompoundTerm)
     * @see tefkat.model.TefkatPackage#getTerm_CompoundTerm()
     * @see tefkat.model.CompoundTerm#getTerm
     * @model opposite="term"
     * @generated
     */
    CompoundTerm getCompoundTerm();

    /**
     * Sets the value of the '{@link tefkat.model.Term#getCompoundTerm <em>Compound Term</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Compound Term</em>' container reference.
     * @see #getCompoundTerm()
     * @generated
     */
    void setCompoundTerm(CompoundTerm value);

    /**
     * Returns the value of the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Context</em>' reference.
     * @see #setContext(ExtentVar)
     * @see tefkat.model.TefkatPackage#getTerm_Context()
     * @model
     * @generated
     */
    ExtentVar getContext();

    /**
     * Sets the value of the '{@link tefkat.model.Term#getContext <em>Context</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context</em>' reference.
     * @see #getContext()
     * @generated
     */
    void setContext(ExtentVar value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    ExtentVar getExtent();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    boolean isTarget();

} // Term
