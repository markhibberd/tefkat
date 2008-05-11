/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mof Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.MofOrder#getLesser <em>Lesser</em>}</li>
 *   <li>{@link tefkat.engine.runtime.MofOrder#getGreater <em>Greater</em>}</li>
 *   <li>{@link tefkat.engine.runtime.MofOrder#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.engine.runtime.MofOrder#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getMofOrder()
 * @model
 * @generated
 */
public interface MofOrder extends MofTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Lesser</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lesser</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lesser</em>' containment reference.
     * @see #setLesser(Expression)
     * @see tefkat.engine.runtime.RuntimePackage#getMofOrder_Lesser()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getLesser();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.MofOrder#getLesser <em>Lesser</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lesser</em>' containment reference.
     * @see #getLesser()
     * @generated
     */
    void setLesser(Expression value);

    /**
     * Returns the value of the '<em><b>Greater</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Greater</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Greater</em>' containment reference.
     * @see #setGreater(Expression)
     * @see tefkat.engine.runtime.RuntimePackage#getMofOrder_Greater()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getGreater();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.MofOrder#getGreater <em>Greater</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Greater</em>' containment reference.
     * @see #getGreater()
     * @generated
     */
    void setGreater(Expression value);

    /**
     * Returns the value of the '<em><b>Instance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instance</em>' containment reference.
     * @see #setInstance(Expression)
     * @see tefkat.engine.runtime.RuntimePackage#getMofOrder_Instance()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getInstance();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.MofOrder#getInstance <em>Instance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance</em>' containment reference.
     * @see #getInstance()
     * @generated
     */
    void setInstance(Expression value);

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
     * @see tefkat.engine.runtime.RuntimePackage#getMofOrder_Feature()
     * @model containment="true" required="true"
     * @generated
     */
    Expression getFeature();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.MofOrder#getFeature <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature</em>' containment reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(Expression value);

} // MofOrder