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
 * A representation of the model object '<em><b>Injection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.Injection#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Injection#getSources <em>Sources</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Injection#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getInjection()
 * @model
 * @generated
 */
public interface Injection extends TargetTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see tefkat.engine.runtime.RuntimePackage#getInjection_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Injection#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Expression}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sources</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getInjection_Sources()
     * @model type="tefkat.engine.runtime.Expression" containment="true"
     * @generated
     */
    EList getSources();

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(VarUse)
     * @see tefkat.engine.runtime.RuntimePackage#getInjection_Target()
     * @model containment="true" required="true"
     * @generated
     */
    VarUse getTarget();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Injection#getTarget <em>Target</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(VarUse value);

} // Injection