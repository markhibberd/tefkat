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
package tefkat.config.TefkatConfig.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.config.TefkatConfig.TefkatConfigPackage;
import tefkat.config.TefkatConfig.TransformationTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.impl.ConfigurationImpl#getTransformationTasks <em>Transformation Tasks</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.ConfigurationImpl#getPackageClasses <em>Package Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigurationImpl extends EObjectImpl implements Configuration {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * The cached value of the '{@link #getTransformationTasks() <em>Transformation Tasks</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransformationTasks()
     * @generated
     * @ordered
     */
    protected EList transformationTasks = null;

    /**
     * The cached value of the '{@link #getPackageClasses() <em>Package Classes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackageClasses()
     * @generated
     * @ordered
     */
    protected EList packageClasses = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConfigurationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatConfigPackage.eINSTANCE.getConfiguration();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTransformationTasks() {
        if (transformationTasks == null) {
            transformationTasks = new EObjectContainmentEList(TransformationTask.class, this, TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS);
        }
        return transformationTasks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPackageClasses() {
        if (packageClasses == null) {
            packageClasses = new EDataTypeUniqueEList(String.class, this, TefkatConfigPackage.CONFIGURATION__PACKAGE_CLASSES);
        }
        return packageClasses;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS:
                    return ((InternalEList)getTransformationTasks()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS:
                return getTransformationTasks();
            case TefkatConfigPackage.CONFIGURATION__PACKAGE_CLASSES:
                return getPackageClasses();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS:
                getTransformationTasks().clear();
                getTransformationTasks().addAll((Collection)newValue);
                return;
            case TefkatConfigPackage.CONFIGURATION__PACKAGE_CLASSES:
                getPackageClasses().clear();
                getPackageClasses().addAll((Collection)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS:
                getTransformationTasks().clear();
                return;
            case TefkatConfigPackage.CONFIGURATION__PACKAGE_CLASSES:
                getPackageClasses().clear();
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatConfigPackage.CONFIGURATION__TRANSFORMATION_TASKS:
                return transformationTasks != null && !transformationTasks.isEmpty();
            case TefkatConfigPackage.CONFIGURATION__PACKAGE_CLASSES:
                return packageClasses != null && !packageClasses.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (PackageClasses: ");
        result.append(packageClasses);
        result.append(')');
        return result.toString();
    }

} //ConfigurationImpl
