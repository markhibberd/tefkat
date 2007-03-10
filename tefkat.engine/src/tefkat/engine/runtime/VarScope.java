/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.VarScope#getVars <em>Vars</em>}</li>
 *   <li>{@link tefkat.engine.runtime.VarScope#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.VarScope#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getVarScope()
 * @model
 * @generated
 */
public interface VarScope extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Vars</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getScope <em>Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vars</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVarScope_Vars()
     * @see tefkat.engine.runtime.Var#getScope
     * @model type="tefkat.engine.runtime.Var" opposite="scope" containment="true"
     * @generated
     */
    EList getVars();

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
     * @see tefkat.engine.runtime.RuntimePackage#getVarScope_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.VarScope#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Comments</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comments</em>' attribute list.
     * @see tefkat.engine.runtime.RuntimePackage#getVarScope_Comments()
     * @model type="java.lang.String" unique="false"
     * @generated
     */
    EList getComments();

} // VarScope