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
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.Query#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Query#getParameterVar <em>Parameter Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getQuery()
 * @model
 * @generated
 */
public interface Query extends PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Term</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Term#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Term</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Term</em>' containment reference.
     * @see #setTerm(Term)
     * @see tefkat.engine.runtime.RuntimePackage#getQuery_Term()
     * @see tefkat.engine.runtime.Term#getQuery
     * @model opposite="query" containment="true" required="true"
     * @generated
     */
    Term getTerm();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Query#getTerm <em>Term</em>}' containment reference.
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
     * @see tefkat.engine.runtime.RuntimePackage#getQuery_ParameterVar()
     * @model type="tefkat.engine.runtime.Var"
     * @generated
     */
    EList getParameterVar();

} // Query