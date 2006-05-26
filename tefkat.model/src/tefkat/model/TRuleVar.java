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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TRule Var</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.TRuleVar#getExtender <em>Extender</em>}</li>
 *   <li>{@link tefkat.model.TRuleVar#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.TRuleVar#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.model.TRuleVar#getSuperseded <em>Superseded</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTRuleVar()
 * @model
 * @generated
 */
public interface TRuleVar extends AbstractVar {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Extender</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRuleVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRuleVar#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extender</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extender</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRuleVar_Extender()
     * @see tefkat.model.TRuleVar#getExtended
     * @model type="tefkat.model.TRuleVar" opposite="extended"
     * @generated
     */
    EList getExtender();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRuleVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRuleVar#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extended</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRuleVar_Extended()
     * @see tefkat.model.TRuleVar#getExtender
     * @model type="tefkat.model.TRuleVar" opposite="extender"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Superseder</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRuleVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRuleVar#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseder</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseder</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRuleVar_Superseder()
     * @see tefkat.model.TRuleVar#getSuperseded
     * @model type="tefkat.model.TRuleVar" opposite="superseded"
     * @generated
     */
    EList getSuperseder();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRuleVar}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRuleVar#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseded</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRuleVar_Superseded()
     * @see tefkat.model.TRuleVar#getSuperseder
     * @model type="tefkat.model.TRuleVar" opposite="superseder"
     * @generated
     */
    EList getSuperseded();

} // TRuleVar
