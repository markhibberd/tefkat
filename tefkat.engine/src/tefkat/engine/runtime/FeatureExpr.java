/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.FeatureExpr#isOperation <em>Operation</em>}</li>
 *   <li>{@link tefkat.engine.runtime.FeatureExpr#isCollect <em>Collect</em>}</li>
 *   <li>{@link tefkat.engine.runtime.FeatureExpr#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getFeatureExpr()
 * @model
 * @generated
 */
public interface FeatureExpr extends CompoundExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' attribute.
     * @see #setOperation(boolean)
     * @see tefkat.engine.runtime.RuntimePackage#getFeatureExpr_Operation()
     * @model required="true"
     * @generated
     */
    boolean isOperation();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.FeatureExpr#isOperation <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' attribute.
     * @see #isOperation()
     * @generated
     */
    void setOperation(boolean value);

    /**
     * Returns the value of the '<em><b>Collect</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Collect</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Collect</em>' attribute.
     * @see #setCollect(boolean)
     * @see tefkat.engine.runtime.RuntimePackage#getFeatureExpr_Collect()
     * @model required="true"
     * @generated
     */
    boolean isCollect();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.FeatureExpr#isCollect <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collect</em>' attribute.
     * @see #isCollect()
     * @generated
     */
    void setCollect(boolean value);

    /**
     * Returns the value of the '<em><b>Feature</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature</em>' containment reference.
     * @see #setFeature(Expression)
     * @see tefkat.engine.runtime.RuntimePackage#getFeatureExpr_Feature()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getFeature();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.FeatureExpr#getFeature <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature</em>' containment reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(Expression value);

} // FeatureExpr