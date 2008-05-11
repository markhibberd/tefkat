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
 * A representation of the model object '<em><b>Var</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.Var#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getUsages <em>Usages</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Var#getExtender <em>Extender</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getVar()
 * @model
 * @generated
 */
public interface Var extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Scope</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.VarScope#getVars <em>Vars</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scope</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scope</em>' container reference.
     * @see #setScope(VarScope)
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Scope()
     * @see tefkat.engine.runtime.VarScope#getVars
     * @model opposite="vars"
     * @generated
     */
    VarScope getScope();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Var#getScope <em>Scope</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scope</em>' container reference.
     * @see #getScope()
     * @generated
     */
    void setScope(VarScope value);

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
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.Var#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Usages</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.VarUse}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.VarUse#getVar <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Usages</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Usages()
     * @see tefkat.engine.runtime.VarUse#getVar
     * @model type="tefkat.engine.runtime.VarUse" opposite="var" changeable="false" ordered="false"
     * @generated
     */
    EList getUsages();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Superseded()
     * @see tefkat.engine.runtime.Var#getSuperseder
     * @model type="tefkat.engine.runtime.Var" opposite="superseder"
     * @generated
     */
    EList getSuperseded();

    /**
     * Returns the value of the '<em><b>Superseder</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseder</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Superseder()
     * @see tefkat.engine.runtime.Var#getSuperseded
     * @model type="tefkat.engine.runtime.Var" opposite="superseded"
     * @generated
     */
    EList getSuperseder();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Extended()
     * @see tefkat.engine.runtime.Var#getExtender
     * @model type="tefkat.engine.runtime.Var" opposite="extender"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Extender</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Var#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extender</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getVar_Extender()
     * @see tefkat.engine.runtime.Var#getExtended
     * @model type="tefkat.engine.runtime.Var" opposite="extended"
     * @generated
     */
    EList getExtender();

} // Var