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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.model.Transformation#getTRule <em>TRule</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * Returns the value of the '<em><b>TRule</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.TRule}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TRule#getTransformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TRule</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TRule</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getTransformation_TRule()
     * @see tefkat.model.TRule#getTransformation
     * @model type="tefkat.model.TRule" opposite="transformation" containment="true"
     * @generated
     */
    EList getTRule();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.model.ListArray" exceptions="tefkat.model.TefkatException"
     * @generated
     */
    List[] getStrata() throws TefkatException;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model supersederRequired="true" supersededRequired="true"
     * @generated
     */
    void addSupersedes(TRule superseder, TRule superseded);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model supersederRequired="true" supersededRequired="true"
     * @generated
     */
    void removeSupersedes(TRule superseder, TRule superseded);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model dataType="tefkat.model.Collection" required="true" supersededRequired="true"
     * @generated
     */
    Collection getSupersedingRules(TRule superseded);

} // Transformation
