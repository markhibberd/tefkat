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
package tefkat.config.TefkatConfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.Configuration#getTransformationTasks <em>Transformation Tasks</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.Configuration#getPackageClasses <em>Package Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * Returns the value of the '<em><b>Transformation Tasks</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.config.TefkatConfig.TransformationTask}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation Tasks</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation Tasks</em>' containment reference list.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getConfiguration_TransformationTasks()
     * @model type="tefkat.config.TefkatConfig.TransformationTask" containment="true"
     * @generated
     */
    EList getTransformationTasks();

    /**
     * Returns the value of the '<em><b>Package Classes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Package Classes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Package Classes</em>' attribute list.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getConfiguration_PackageClasses()
     * @model type="java.lang.String"
     * @generated
     */
    EList getPackageClasses();

} // Configuration
