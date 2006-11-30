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
 * A representation of the model object '<em><b>Source Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.SourceTerm#getTRuleSrc <em>TRule Src</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getSourceTerm()
 * @model abstract="true"
 * @generated
 */
public interface SourceTerm extends Term {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>TRule Src</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRule#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TRule Src</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TRule Src</em>' container reference.
     * @see #setTRuleSrc(TRule)
     * @see tefkat.model.TefkatPackage#getSourceTerm_TRuleSrc()
     * @see tefkat.model.TRule#getSrc
     * @model opposite="src"
     * @generated
     */
    TRule getTRuleSrc();

    /**
     * Sets the value of the '{@link tefkat.model.SourceTerm#getTRuleSrc <em>TRule Src</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TRule Src</em>' container reference.
     * @see #getTRuleSrc()
     * @generated
     */
    void setTRuleSrc(TRule value);

} // SourceTerm
