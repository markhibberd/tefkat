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


import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.model.ContainerExtent;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Extent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.ContainerExtentImpl#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerExtentImpl extends ExtentImpl implements ContainerExtent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResource()
     * @generated
     * @ordered
     */
    protected static final Resource RESOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResource()
     * @generated
     * @ordered
     */
    protected Resource resource = RESOURCE_EDEFAULT;

    private Map exactlyCache = new WeakHashMap();
    private Map inexactlyCache = new WeakHashMap();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ContainerExtentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.CONTAINER_EXTENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean contains(EObject instance) {
        Resource res = instance.eResource();
        return null != res && res.equals(resource);
    }

    /**
     * <!-- begin-user-doc -->
     * FIXME Yikes, need to invalidate the cache if the extent is modified, but
     * in the context of a Tefkat transformation, only tracking extents will get
     * modified.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getObjectsByClass(final EClass theClass, boolean isExactly) {
        EList objects;
        if (null == theClass) {
            // null class means no type constraint -- i.e., get all instances
            objects = cacheLookup(exactlyCache, theClass);
            if (null == objects) {
                objects = new BasicEList();
                for (Iterator itr = resource.getAllContents(); itr.hasNext(); ) {
                    objects.add(itr.next());
                }
                cacheStore(exactlyCache, theClass, objects);
            }
        } else if (isExactly) {
            objects = cacheLookup(exactlyCache, theClass);
            if (null == objects) {
                objects = new BasicEList();
                for (Iterator itr = resource.getAllContents(); itr.hasNext(); ) {
                    EObject obj = (EObject) itr.next();
                    if (theClass.equals(obj.eClass())) {
                        objects.add(obj);
                    }
                }
                cacheStore(exactlyCache, theClass, objects);
            }
        } else {
            objects = cacheLookup(inexactlyCache, theClass);
            if (null == objects) {
                objects = new BasicEList();
                for (Iterator itr = resource.getAllContents(); itr.hasNext(); ) {
                    EObject obj = (EObject) itr.next();
                    if (theClass.isInstance(obj)) {
                        objects.add(obj);
                    }
                }
                cacheStore(inexactlyCache, theClass, objects);
            }
        }
        return objects;
    }

    private EList cacheLookup(Map cache, EClass key) {
        // Can't cache if underlying resource has changed
        // This is always (almost) the case for tracking extents :-(
        // hence we keep special cases in the RuleEvaluator
        if (getResource().isModified()) {
            return null;
        }
        Object result = cache.get(key);
        if (null != result) {
            result = ((WeakReference) result).get();
        }
        return (EList) result;
    }
    
    private void cacheStore(Map cache, EClass key, EList objects) {
        // Can't cache if underlying resource has changed
        cache.put(key, new WeakReference(objects));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public TreeIterator getAllContents() {
        return resource.getAllContents();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void add(EObject obj) {
        resource.getContents().add(obj);
//        System.err.println(resource.getURI() + " " + resource.getContents().size());
        final EClass eClass = obj.eClass();
        EList exact = getCache(exactlyCache, eClass);
        if (null != exact) {
//            System.err.println("hit");
            exact.add(obj);
        }
        EList inexact = getCache(inexactlyCache, eClass);
        if (null != inexact) {
            inexact.add(obj);
        }
        for (Iterator itr = eClass.getEAllSuperTypes().iterator(); itr.hasNext(); ) {
            EClass superClass = (EClass) itr.next();
            inexact = getCache(inexactlyCache, superClass);
            if (null != inexact) {
                inexact.add(obj);
            }
        }
    }
    
    EList getCache(Map cache, EClass key) {
        Object result = cache.get(key);
        if (null != result) {
            result = ((WeakReference) result).get();
        }
        if (null == result) {
            result = new BasicEList();
            cache.put(key, new WeakReference(result));
        }
        return (EList) result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void remove(EObject obj) {
        resource.getContents().remove(obj);
        System.err.println(resource.getURI() + " remove");
//        EList exact = cacheLookup(exactlyCache, obj.eClass());
//        if (null != exact) {
//            exact.remove(obj);
//        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setResource(Resource newResource) {
        Resource oldResource = resource;
        resource = newResource;
        exactlyCache.clear();
        inexactlyCache.clear();
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.CONTAINER_EXTENT__RESOURCE, oldResource, resource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.CONTAINER_EXTENT__RESOURCE:
                return getResource();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TefkatPackage.CONTAINER_EXTENT__RESOURCE:
                setResource((Resource)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case TefkatPackage.CONTAINER_EXTENT__RESOURCE:
                setResource(RESOURCE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TefkatPackage.CONTAINER_EXTENT__RESOURCE:
                return RESOURCE_EDEFAULT == null ? resource != null : !RESOURCE_EDEFAULT.equals(resource);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        return null == resource ? "null Resource" : String.valueOf(resource.getURI());
    }

} //ContainerExtentImpl
