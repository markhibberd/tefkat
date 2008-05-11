/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.Term#getPatternDefn <em>Pattern Defn</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Term#getQuery <em>Query</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Term#getCompoundTerm <em>Compound Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Term#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getTerm()
 * @model abstract="true"
 * @generated
 */
public interface Term extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Pattern Defn</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.PatternDefn#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Defn</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Defn</em>' container reference.
     * @see #setPatternDefn(PatternDefn)
     * @see tefkat.engine.runtime.RuntimePackage#getTerm_PatternDefn()
     * @see tefkat.engine.runtime.PatternDefn#getTerm
     * @model opposite="term"
     * @generated
     */
    PatternDefn getPatternDefn();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Term#getPatternDefn <em>Pattern Defn</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Defn</em>' container reference.
     * @see #getPatternDefn()
     * @generated
     */
    void setPatternDefn(PatternDefn value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Query#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' container reference.
     * @see #setQuery(Query)
     * @see tefkat.engine.runtime.RuntimePackage#getTerm_Query()
     * @see tefkat.engine.runtime.Query#getTerm
     * @model opposite="term"
     * @generated
     */
    Query getQuery();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Term#getQuery <em>Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' container reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery(Query value);

    /**
     * Returns the value of the '<em><b>Compound Term</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.CompoundTerm#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compound Term</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Compound Term</em>' container reference.
     * @see #setCompoundTerm(CompoundTerm)
     * @see tefkat.engine.runtime.RuntimePackage#getTerm_CompoundTerm()
     * @see tefkat.engine.runtime.CompoundTerm#getTerm
     * @model opposite="term"
     * @generated
     */
    CompoundTerm getCompoundTerm();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Term#getCompoundTerm <em>Compound Term</em>}' container reference.
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
     * @see #setContext(Var)
     * @see tefkat.engine.runtime.RuntimePackage#getTerm_Context()
     * @model
     * @generated
     */
    Var getContext();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Term#getContext <em>Context</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context</em>' reference.
     * @see #getContext()
     * @generated
     */
    void setContext(Var value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    Var getExtent();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    boolean isTarget();

} // Term