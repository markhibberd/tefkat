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
 * A representation of the model object '<em><b>Pattern Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.PatternDefn#getPatternScope <em>Pattern Scope</em>}</li>
 *   <li>{@link tefkat.model.PatternDefn#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.model.PatternDefn#getParameterVar <em>Parameter Var</em>}</li>
 *   <li>{@link tefkat.model.PatternDefn#isSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getPatternDefn()
 * @model
 * @generated
 */
public interface PatternDefn extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Pattern Scope</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.PatternScope#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Scope</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Scope</em>' container reference.
     * @see #setPatternScope(PatternScope)
     * @see tefkat.model.TefkatPackage#getPatternDefn_PatternScope()
     * @see tefkat.model.PatternScope#getPatternDefn
     * @model opposite="patternDefn" required="true"
     * @generated
     */
    PatternScope getPatternScope();

    /**
     * Sets the value of the '{@link tefkat.model.PatternDefn#getPatternScope <em>Pattern Scope</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Scope</em>' container reference.
     * @see #getPatternScope()
     * @generated
     */
    void setPatternScope(PatternScope value);

    /**
     * Returns the value of the '<em><b>Term</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.Term#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Term</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Term</em>' containment reference.
     * @see #setTerm(Term)
     * @see tefkat.model.TefkatPackage#getPatternDefn_Term()
     * @see tefkat.model.Term#getPatternDefn
     * @model opposite="patternDefn" containment="true" required="true"
     * @generated
     */
    Term getTerm();

    /**
     * Sets the value of the '{@link tefkat.model.PatternDefn#getTerm <em>Term</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Term</em>' containment reference.
     * @see #getTerm()
     * @generated
     */
    void setTerm(Term value);

    /**
     * Returns the value of the '<em><b>Parameter Var</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.Var}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter Var</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Var</em>' reference list.
     * @see tefkat.model.TefkatPackage#getPatternDefn_ParameterVar()
     * @model type="tefkat.model.Var"
     * @generated
     */
    EList getParameterVar();

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' attribute.
     * @see #setSource(boolean)
     * @see tefkat.model.TefkatPackage#getPatternDefn_Source()
     * @model default="true"
     * @generated
     */
    boolean isSource();

    /**
     * Sets the value of the '{@link tefkat.model.PatternDefn#isSource <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' attribute.
     * @see #isSource()
     * @generated
     */
    void setSource(boolean value);

} // PatternDefn
