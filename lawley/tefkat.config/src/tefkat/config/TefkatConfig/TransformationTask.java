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
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getSourceModels <em>Source Models</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getTargetModels <em>Target Models</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getTrace <em>Trace</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getMode <em>Mode</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getProperties <em>Properties</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.TransformationTask#getUriMap <em>Uri Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask()
 * @model
 * @generated
 */
public interface TransformationTask extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation</em>' containment reference.
     * @see #setTransformation(Model)
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_Transformation()
     * @model containment="true" required="true"
     * @generated
     */
    Model getTransformation();

    /**
     * Sets the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#getTransformation <em>Transformation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transformation</em>' containment reference.
     * @see #getTransformation()
     * @generated
     */
    void setTransformation(Model value);

    /**
     * Returns the value of the '<em><b>Source Models</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.config.TefkatConfig.Model}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Models</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Models</em>' containment reference list.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_SourceModels()
     * @model type="tefkat.config.TefkatConfig.Model" containment="true"
     * @generated
     */
    EList getSourceModels();

    /**
     * Returns the value of the '<em><b>Target Models</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.config.TefkatConfig.Model}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Models</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Models</em>' containment reference list.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_TargetModels()
     * @model type="tefkat.config.TefkatConfig.Model" containment="true"
     * @generated
     */
    EList getTargetModels();

    /**
     * Returns the value of the '<em><b>Trace</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Trace</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Trace</em>' containment reference.
     * @see #setTrace(Model)
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_Trace()
     * @model containment="true"
     * @generated
     */
    Model getTrace();

    /**
     * Sets the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#getTrace <em>Trace</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Trace</em>' containment reference.
     * @see #getTrace()
     * @generated
     */
    void setTrace(Model value);

    /**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #setEnabled(boolean)
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_Enabled()
     * @model
     * @generated
     */
    boolean isEnabled();

    /**
     * Sets the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isEnabled()
     * @generated
     */
    void setEnabled(boolean value);

    /**
     * Returns the value of the '<em><b>Mode</b></em>' attribute.
     * The literals are from the enumeration {@link tefkat.config.TefkatConfig.ExecutionMode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mode</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mode</em>' attribute.
     * @see tefkat.config.TefkatConfig.ExecutionMode
     * @see #isSetMode()
     * @see #unsetMode()
     * @see #setMode(ExecutionMode)
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_Mode()
     * @model unsettable="true" required="true"
     * @generated
     */
    ExecutionMode getMode();

    /**
     * Sets the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#getMode <em>Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mode</em>' attribute.
     * @see tefkat.config.TefkatConfig.ExecutionMode
     * @see #isSetMode()
     * @see #unsetMode()
     * @see #getMode()
     * @generated
     */
    void setMode(ExecutionMode value);

    /**
     * Unsets the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#getMode <em>Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMode()
     * @see #getMode()
     * @see #setMode(ExecutionMode)
     * @generated
     */
    void unsetMode();

    /**
     * Returns whether the value of the '{@link tefkat.config.TefkatConfig.TransformationTask#getMode <em>Mode</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Mode</em>' attribute is set.
     * @see #unsetMode()
     * @see #getMode()
     * @see #setMode(ExecutionMode)
     * @generated
     */
    boolean isSetMode();

    /**
     * Returns the value of the '<em><b>Properties</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' map.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_Properties()
     * @model mapType="tefkat.config.TefkatConfig.Property" keyType="java.lang.String" valueType="java.lang.String"
     * @generated
     */
    EMap getProperties();

    /**
     * Returns the value of the '<em><b>Uri Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri Map</em>' map.
     * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getTransformationTask_UriMap()
     * @model mapType="tefkat.config.TefkatConfig.URIMapEntry" keyType="java.lang.String" valueType="java.lang.String"
     * @generated
     */
    EMap getUriMap();

} // TransformationTask
