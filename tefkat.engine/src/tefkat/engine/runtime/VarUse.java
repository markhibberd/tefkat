/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.VarUse#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getVarUse()
 * @model
 * @generated
 */
public interface VarUse extends Expression {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Var</b></em>' reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var</em>' reference.
     * @see #setVar(Var)
     * @see tefkat.engine.runtime.RuntimePackage#getVarUse_Var()
     * @see tefkat.engine.runtime.Var#getUsages
     * @model opposite="usages" required="true"
     * @generated
     */
    Var getVar();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.VarUse#getVar <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var</em>' reference.
     * @see #getVar()
     * @generated
     */
    void setVar(Var value);

} // VarUse