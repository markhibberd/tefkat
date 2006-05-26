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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTargetTerm()
 * @model abstract="true"
 * @generated
 */
public interface TargetTerm extends Term {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * Returns the value of the '<em><b>TRule Tgt</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRule#getTgt <em>Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TRule Tgt</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TRule Tgt</em>' container reference.
     * @see #setTRuleTgt(TRule)
     * @see tefkat.model.TefkatPackage#getTargetTerm_TRuleTgt()
     * @see tefkat.model.TRule#getTgt
     * @model opposite="tgt"
     * @generated
     */
    TRule getTRuleTgt();

    /**
     * Sets the value of the '{@link tefkat.model.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TRule Tgt</em>' container reference.
     * @see #getTRuleTgt()
     * @generated
     */
    void setTRuleTgt(TRule value);

} // TargetTerm
