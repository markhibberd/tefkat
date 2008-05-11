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
package tefkat.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Var</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Var#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.model.Var#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.Var#getUsages <em>Usages</em>}</li>
 *   <li>{@link tefkat.model.Var#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.model.Var#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.model.Var#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.Var#getExtender <em>Extender</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getVar()
 * @model
 * @generated
 */
public interface Var extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * Returns the value of the '<em><b>Scope</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.VarScope#getVars <em>Vars</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scope</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scope</em>' container reference.
     * @see #setScope(VarScope)
     * @see tefkat.model.TefkatPackage#getVar_Scope()
     * @see tefkat.model.VarScope#getVars
     * @model opposite="vars"
     * @generated
     */
    VarScope getScope();

    /**
     * Sets the value of the '{@link tefkat.model.Var#getScope <em>Scope</em>}' container reference.
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
     * @see tefkat.model.TefkatPackage#getVar_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.model.Var#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Usages</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.VarUse}.
     * It is bidirectional and its opposite is '{@link tefkat.model.VarUse#getVar <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Usages</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Usages</em>' reference list.
     * @see tefkat.model.TefkatPackage#getVar_Usages()
     * @see tefkat.model.VarUse#getVar
     * @model type="tefkat.model.VarUse" opposite="var" changeable="false" ordered="false"
     * @generated
     */
    EList getUsages();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.model.Var#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseded</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.model.TefkatPackage#getVar_Superseded()
     * @see tefkat.model.Var#getSuperseder
     * @model type="tefkat.model.Var" opposite="superseder"
     * @generated
     */
    EList getSuperseded();

    /**
     * Returns the value of the '<em><b>Superseder</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.model.Var#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseder</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseder</em>' reference list.
     * @see tefkat.model.TefkatPackage#getVar_Superseder()
     * @see tefkat.model.Var#getSuperseded
     * @model type="tefkat.model.Var" opposite="superseded"
     * @generated
     */
    EList getSuperseder();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.model.Var#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extended</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.model.TefkatPackage#getVar_Extended()
     * @see tefkat.model.Var#getExtender
     * @model type="tefkat.model.Var" opposite="extender"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Extender</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.Var}.
     * It is bidirectional and its opposite is '{@link tefkat.model.Var#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extender</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extender</em>' reference list.
     * @see tefkat.model.TefkatPackage#getVar_Extender()
     * @see tefkat.model.Var#getExtended
     * @model type="tefkat.model.Var" opposite="extended"
     * @generated
     */
    EList getExtender();

} // Var
