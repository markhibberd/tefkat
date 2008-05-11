/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.FunctionExpr#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getFunctionExpr()
 * @model
 * @generated
 */
public interface FunctionExpr extends CompoundExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function</em>' attribute.
     * @see #setFunction(String)
     * @see tefkat.engine.runtime.RuntimePackage#getFunctionExpr_Function()
     * @model required="true"
     * @generated
     */
    String getFunction();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.FunctionExpr#getFunction <em>Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function</em>' attribute.
     * @see #getFunction()
     * @generated
     */
    void setFunction(String value);

} // FunctionExpr