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
 *   <li>{@link tefkat.model.PatternScope#getNamespaceDeclarations <em>Namespace Declarations</em>}</li>
 *   <li>{@link tefkat.model.PatternScope#getImportedPackages <em>Imported Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getPatternScope()
 * @model abstract="true"
 * @generated
 */
public interface PatternScope extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

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

    /**
     * Returns the value of the '<em><b>Namespace Declarations</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.NamespaceDeclaration}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace Declarations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace Declarations</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getPatternScope_NamespaceDeclarations()
     * @model type="tefkat.model.NamespaceDeclaration" containment="true"
     * @generated
     */
    EList getNamespaceDeclarations();

    /**
     * Returns the value of the '<em><b>Imported Packages</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Imported Packages</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imported Packages</em>' attribute list.
     * @see tefkat.model.TefkatPackage#getPatternScope_ImportedPackages()
     * @model type="java.lang.String"
     * @generated
     */
    EList getImportedPackages();

} // PatternScope
