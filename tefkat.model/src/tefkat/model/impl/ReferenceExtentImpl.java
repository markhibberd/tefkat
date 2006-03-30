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
 */
package tefkat.model.impl;


import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import tefkat.model.ReferenceExtent;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Extent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.ReferenceExtentImpl#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceExtentImpl extends ExtentImpl implements ReferenceExtent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * The cached value of the '{@link #getResources() <em>Resources</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected EList resources = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReferenceExtentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getReferenceExtent();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean contains(EObject instance) {
        Resource res = instance.eResource();
        return null != res && getResources().contains(res);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getObjectsByClass(EClass theClass, boolean isExactly) {
        EList objects = getAllObjectsByClass(theClass, isExactly);
        return objects;
    }

    /**
     * @param theClass
     * @param isExactly
     * @return
     */
    private EList getAllObjectsByClass(EClass theClass, boolean isExactly) {
        EList objects = new BasicEList();
        for (final Iterator resItr = getResources().iterator(); resItr.hasNext(); ) {
            Resource resource = (Resource) resItr.next();
            Iterator itr = resource.getAllContents();
            while (itr.hasNext()) {
                EObject obj = (EObject) itr.next();
                EClass eClass = obj.eClass();
                if (eClass.equals(theClass)) {
                    objects.add(obj);
                } else if (!isExactly) {
                    Iterator itr2 = eClass.getEAllSuperTypes().iterator();
                    while (itr2.hasNext()) {
                        EClass subClass = (EClass) itr2.next();
                        if (theClass.equals(subClass)) {
                            objects.add(obj);
                            break;
                        }
                    }
                }
            }
        }
        return objects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public TreeIterator getAllContents() {
        return new TreeIterator() {
            Iterator itr = getResources().iterator();
            TreeIterator resItr = nextIterator();

            private TreeIterator nextIterator() {
                return itr.hasNext() ? ((Resource) itr.next()).getAllContents() : null;
            }
            
            public void prune() {
                resItr.prune();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                if (!resItr.hasNext()) {
                    if (itr.hasNext()) {
                        resItr = nextIterator();
                        return hasNext();
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }

            public Object next() {
                if (!resItr.hasNext()) {
                    if (itr.hasNext()) {
                        resItr = nextIterator();
                        return next();
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    return resItr.next();
                }
            }
            
        };

    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void add(EObject obj) {
        Resource resource = (Resource) getResources().get(0);
        if (null == resource) {
            throw new IllegalStateException("No encapsulated Resources");
        }
        resource.getContents().add(obj);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void remove(EObject obj) {
        Resource resource = (Resource) getResources().get(0);
        if (null == resource) {
            throw new IllegalStateException("No encapsulated Resources");
        }
        resource.getContents().remove(obj);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getResources() {
        if (resources == null) {
            resources = new EDataTypeUniqueEList(Resource.class, this, TefkatPackage.REFERENCE_EXTENT__RESOURCES);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.REFERENCE_EXTENT__RESOURCES:
                return getResources();
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
            case TefkatPackage.REFERENCE_EXTENT__RESOURCES:
                getResources().clear();
                getResources().addAll((Collection)newValue);
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
            case TefkatPackage.REFERENCE_EXTENT__RESOURCES:
                getResources().clear();
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
            case TefkatPackage.REFERENCE_EXTENT__RESOURCES:
                return resources != null && !resources.isEmpty();
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
        result.append(" (resources: ");
        result.append(resources);
        result.append(')');
        return result.toString();
    }

} //ReferenceExtentImpl
