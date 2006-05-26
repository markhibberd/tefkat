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
 * A representation of the model object '<em><b>Pattern Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.PatternUse#getDefn <em>Defn</em>}</li>
 *   <li>{@link tefkat.model.PatternUse#getArg <em>Arg</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getPatternUse()
 * @model
 * @generated
 */
public interface PatternUse extends SimpleTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>Defn</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Defn</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Defn</em>' reference.
     * @see #setDefn(PatternDefn)
     * @see tefkat.model.TefkatPackage#getPatternUse_Defn()
     * @model required="true"
     * @generated
     */
    PatternDefn getDefn();

    /**
     * Sets the value of the '{@link tefkat.model.PatternUse#getDefn <em>Defn</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Defn</em>' reference.
     * @see #getDefn()
     * @generated
     */
    void setDefn(PatternDefn value);

    /**
     * Returns the value of the '<em><b>Arg</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.Expression}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arg</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Arg</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getPatternUse_Arg()
     * @model type="tefkat.model.Expression" containment="true"
     * @generated
     */
    EList getArg();

} // PatternUse
