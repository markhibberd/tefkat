/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import tefkat.engine.runtime.ReferenceExtent;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Extent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.ReferenceExtentImpl#getResources <em>Resources</em>}</li>
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
    public static final String copyright = "Copyright michael lawley 2004";

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
        return RuntimePackage.Literals.REFERENCE_EXTENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getResources() {
        if (resources == null) {
            resources = new EDataTypeUniqueEList(Resource.class, this, RuntimePackage.REFERENCE_EXTENT__RESOURCES);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.REFERENCE_EXTENT__RESOURCES:
                return getResources();
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
            case RuntimePackage.REFERENCE_EXTENT__RESOURCES:
                getResources().clear();
                getResources().addAll((Collection)newValue);
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
            case RuntimePackage.REFERENCE_EXTENT__RESOURCES:
                getResources().clear();
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
            case RuntimePackage.REFERENCE_EXTENT__RESOURCES:
                return resources != null && !resources.isEmpty();
        }
        return super.eIsSet(featureID);
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

    public void add(EObject obj) {
        Resource resource = (Resource) getResources().get(0);
        if (null == resource) {
            throw new IllegalStateException("No encapsulated Resources");
        }
        resource.getContents().add(obj);
    }

    public boolean contains(EObject instance) {
        Resource res = instance.eResource();
        return null != res && getResources().contains(res);
    }

    private final TreeIterator ALL_CONTENTS_ITERATOR = new TreeIterator() {
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

    
    public TreeIterator getAllContents() {
        return ALL_CONTENTS_ITERATOR;
    }

    public EList getObjectsByClass(EClass theClass, boolean isExactly) {
        EList objects = getAllObjectsByClass(theClass, isExactly);
        return objects;
    }

    /**
     * @param theClass
     * @param isExactly
     * @return
     */
    private EList getAllObjectsByClass(final EClass theClass, final boolean isExactly) {
        EList objects = new BasicEList();
        for (final Iterator resItr = getResources().iterator(); resItr.hasNext(); ) {
            Resource resource = (Resource) resItr.next();
            Iterator itr = resource.getAllContents();
            while (itr.hasNext()) {
                EObject obj = (EObject) itr.next();
                if (null == theClass) {
                    // null class means no type constraint -- i.e., get all instances
                    objects.add(obj);
                } else {
                    EClass eClass = obj.eClass();
                    if ( eClass.equals(theClass) ||
                         (!isExactly && theClass.isSuperTypeOf(eClass)) ) {
                        objects.add(obj);
                    }
                }
            }
        }
        return objects;
    }

    public void remove(EObject obj) {
        for (int i = getResources().size() - 1; i >= 0; i--) {
            Resource resource = (Resource) getResources().get(i);
            resource.getContents().remove(obj);
        }
    }

} //ReferenceExtentImpl