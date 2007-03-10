/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getTargetTerm()
 * @model abstract="true"
 * @generated
 */
public interface TargetTerm extends Term {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>TRule Tgt</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.TRule#getTgt <em>Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TRule Tgt</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TRule Tgt</em>' container reference.
     * @see #setTRuleTgt(TRule)
     * @see tefkat.engine.runtime.RuntimePackage#getTargetTerm_TRuleTgt()
     * @see tefkat.engine.runtime.TRule#getTgt
     * @model opposite="tgt"
     * @generated
     */
    TRule getTRuleTgt();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TRule Tgt</em>' container reference.
     * @see #getTRuleTgt()
     * @generated
     */
    void setTRuleTgt(TRule value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model exceptions="tefkat.engine.runtime.ResolutionException tefkat.engine.runtime.NotGroundException" contextDataType="tefkat.engine.runtime.Context"
     * @generated
     */
    void ensure(Context context) throws ResolutionException, NotGroundException;

} // TargetTerm