/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.executiontrace.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.executiontrace.model.Trace#getType <em>Type</em>}</li>
 *   <li>{@link tefkat.engine.executiontrace.model.Trace#getRefs <em>Refs</em>}</li>
 *   <li>{@link tefkat.engine.executiontrace.model.Trace#getStuff <em>Stuff</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.executiontrace.model.ExecutiontracePackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see tefkat.engine.executiontrace.model.ExecutiontracePackage#getTrace_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link tefkat.engine.executiontrace.model.Trace#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Refs</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refs</em>' reference list.
	 * @see tefkat.engine.executiontrace.model.ExecutiontracePackage#getTrace_Refs()
	 * @model required="true"
	 * @generated
	 */
	EList<EObject> getRefs();

	/**
	 * Returns the value of the '<em><b>Stuff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stuff</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stuff</em>' attribute.
	 * @see #setStuff(String)
	 * @see tefkat.engine.executiontrace.model.ExecutiontracePackage#getTrace_Stuff()
	 * @model
	 * @generated
	 */
	String getStuff();

	/**
	 * Sets the value of the '{@link tefkat.engine.executiontrace.model.Trace#getStuff <em>Stuff</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stuff</em>' attribute.
	 * @see #getStuff()
	 * @generated
	 */
	void setStuff(String value);

} // Trace
