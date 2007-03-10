/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pattern Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.PatternDefn#getPatternScope <em>Pattern Scope</em>}</li>
 *   <li>{@link tefkat.engine.runtime.PatternDefn#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.PatternDefn#getParameterVar <em>Parameter Var</em>}</li>
 *   <li>{@link tefkat.engine.runtime.PatternDefn#isSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getPatternDefn()
 * @model
 * @generated
 */
public interface PatternDefn extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Pattern Scope</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.PatternScope#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Scope</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Scope</em>' container reference.
     * @see #setPatternScope(PatternScope)
     * @see tefkat.engine.runtime.RuntimePackage#getPatternDefn_PatternScope()
     * @see tefkat.engine.runtime.PatternScope#getPatternDefn
     * @model opposite="patternDefn" required="true"
     * @generated
     */
    PatternScope getPatternScope();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.PatternDefn#getPatternScope <em>Pattern Scope</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Scope</em>' container reference.
     * @see #getPatternScope()
     * @generated
     */
    void setPatternScope(PatternScope value);

    /**
     * Returns the value of the '<em><b>Term</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Term#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Term</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Term</em>' containment reference.
     * @see #setTerm(Term)
     * @see tefkat.engine.runtime.RuntimePackage#getPatternDefn_Term()
     * @see tefkat.engine.runtime.Term#getPatternDefn
     * @model opposite="patternDefn" containment="true" required="true"
     * @generated
     */
    Term getTerm();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.PatternDefn#getTerm <em>Term</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Term</em>' containment reference.
     * @see #getTerm()
     * @generated
     */
    void setTerm(Term value);

    /**
     * Returns the value of the '<em><b>Parameter Var</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Var</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getPatternDefn_ParameterVar()
     * @model type="tefkat.engine.runtime.Var"
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
     * @see tefkat.engine.runtime.RuntimePackage#getPatternDefn_Source()
     * @model default="true"
     * @generated
     */
    boolean isSource();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.PatternDefn#isSource <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' attribute.
     * @see #isSource()
     * @generated
     */
    void setSource(boolean value);

} // PatternDefn