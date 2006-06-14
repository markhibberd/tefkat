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
 * A representation of the model object '<em><b>Pattern Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.PatternScope#getPatternDefn <em>Pattern Defn</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getPatternScope()
 * @model abstract="true"
 * @generated
 */
public interface PatternScope extends VarScope{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Returns the value of the '<em><b>Pattern Defn</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.PatternDefn}.
     * It is bidirectional and its opposite is '{@link tefkat.model.PatternDefn#getPatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Defn</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Defn</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getPatternScope_PatternDefn()
     * @see tefkat.model.PatternDefn#getPatternScope
     * @model type="tefkat.model.PatternDefn" opposite="patternScope" containment="true"
     * @generated
     */
    EList getPatternDefn();

} // PatternScope
