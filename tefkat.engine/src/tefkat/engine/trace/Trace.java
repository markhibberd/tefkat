/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 * $Id$
 */
package tefkat.engine.trace;

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
 *   <li>{@link tefkat.engine.trace.Trace#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.trace.Trace#getSources <em>Sources</em>}</li>
 *   <li>{@link tefkat.engine.trace.Trace#getTarget <em>Target</em>}</li>
 *   <li>{@link tefkat.engine.trace.Trace#getRules <em>Rules</em>}</li>
 *   <li>{@link tefkat.engine.trace.Trace#getExtra <em>Extra</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.trace.TracePackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
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
     * @see tefkat.engine.trace.TracePackage#getTrace_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.engine.trace.Trace#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.trace.Any}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sources</em>' containment reference list.
     * @see tefkat.engine.trace.TracePackage#getTrace_Sources()
     * @model containment="true"
     * @generated
     */
    EList<Any> getSources();

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(EObject)
     * @see tefkat.engine.trace.TracePackage#getTrace_Target()
     * @model required="true"
     * @generated
     */
    EObject getTarget();

    /**
     * Sets the value of the '{@link tefkat.engine.trace.Trace#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(EObject value);

    /**
     * Returns the value of the '<em><b>Rules</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rules</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rules</em>' reference list.
     * @see tefkat.engine.trace.TracePackage#getTrace_Rules()
     * @model required="true"
     * @generated
     */
    EList<EObject> getRules();

    /**
     * Returns the value of the '<em><b>Extra</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extra</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extra</em>' containment reference list.
     * @see tefkat.engine.trace.TracePackage#getTrace_Extra()
     * @model containment="true"
     * @generated
     */
    EList<EObject> getExtra();

} // Trace
