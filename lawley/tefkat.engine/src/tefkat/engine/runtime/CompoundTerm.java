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
 * A representation of the model object '<em><b>Compound Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.CompoundTerm#getTerm <em>Term</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getCompoundTerm()
 * @model abstract="true"
 * @generated
 */
public interface CompoundTerm extends SourceTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Term</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Term}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Term#getCompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Term</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getCompoundTerm_Term()
     * @see tefkat.engine.runtime.Term#getCompoundTerm
     * @model type="tefkat.engine.runtime.Term" opposite="compoundTerm" containment="true"
     * @generated
     */
    EList getTerm();

} // CompoundTerm