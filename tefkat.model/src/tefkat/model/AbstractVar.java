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
 *   <li>{@link tefkat.model.AbstractVar#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getUsages <em>Usages</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.AbstractVar#getExtender <em>Extender</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getAbstractVar()
 * @model
 * @generated
 */
public interface AbstractVar extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

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
     * @see tefkat.model.TefkatPackage#getAbstractVar_Scope()
     * @see tefkat.model.VarScope#getVars
     * @model opposite="vars"
     * @generated
     */
    VarScope getScope();

    /**
     * Sets the value of the '{@link tefkat.model.AbstractVar#getScope <em>Scope</em>}' container reference.
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
     * @see tefkat.model.TefkatPackage#getAbstractVar_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link tefkat.model.AbstractVar#getName <em>Name</em>}' attribute.
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
     * @see tefkat.model.TefkatPackage#getAbstractVar_Usages()
     * @see tefkat.model.VarUse#getVar
     * @model type="tefkat.model.VarUse" opposite="var" changeable="false" ordered="false"
     * @generated
     */
    EList getUsages();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.AbstractVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.AbstractVar#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseded</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.model.TefkatPackage#getAbstractVar_Superseded()
     * @see tefkat.model.AbstractVar#getSuperseder
     * @model type="tefkat.model.AbstractVar" opposite="superseder"
     * @generated
     */
    EList getSuperseded();

    /**
     * Returns the value of the '<em><b>Superseder</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.AbstractVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.AbstractVar#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseder</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseder</em>' reference list.
     * @see tefkat.model.TefkatPackage#getAbstractVar_Superseder()
     * @see tefkat.model.AbstractVar#getSuperseded
     * @model type="tefkat.model.AbstractVar" opposite="superseded"
     * @generated
     */
    EList getSuperseder();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.AbstractVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.AbstractVar#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extended</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.model.TefkatPackage#getAbstractVar_Extended()
     * @see tefkat.model.AbstractVar#getExtender
     * @model type="tefkat.model.AbstractVar" opposite="extender"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Extender</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.AbstractVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.AbstractVar#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extender</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extender</em>' reference list.
     * @see tefkat.model.TefkatPackage#getAbstractVar_Extender()
     * @see tefkat.model.AbstractVar#getExtended
     * @model type="tefkat.model.AbstractVar" opposite="extended"
     * @generated
     */
    EList getExtender();

} // AbstractVar
