/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.SimpleExpr#getRepresentation <em>Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getSimpleExpr()
 * @model abstract="true"
 * @generated
 */
public interface SimpleExpr extends Expression {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Representation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Representation</em>' attribute.
     * @see #setRepresentation(String)
     * @see tefkat.engine.runtime.RuntimePackage#getSimpleExpr_Representation()
     * @model required="true"
     * @generated
     */
    String getRepresentation();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.SimpleExpr#getRepresentation <em>Representation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Representation</em>' attribute.
     * @see #getRepresentation()
     * @generated
     */
    void setRepresentation(String value);

} // SimpleExpr