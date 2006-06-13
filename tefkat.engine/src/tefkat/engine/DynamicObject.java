/*
 * Copyright (c) 2004- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 *
 */

package tefkat.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * @author lawley
 *
 */
final public class DynamicObject extends EObjectImpl {
    static int counter = 0;
    
    private EObject staticObj = null;

    public DynamicObject() {
        this(EcorePackage.eINSTANCE.getEObject());
    }
    
    public DynamicObject(EClass eClass) {
        counter++;
        eSetClass(eClass);
    }
    
    public boolean hasStaticInstance() {
        return null != staticObj;
    }
    
    public EObject getStaticInstance() {
        if (null == staticObj) {
            EClass eClass = eClass();
            staticObj = eClass.getEPackage().getEFactoryInstance().create(eClass);
            
//            System.out.println("New static instance: " + staticObj.hashCode() + " of type " + eClass.getName());

            // Copy over all property values
            EPropertiesHolder props = eProperties();
            if (props.getEClass() != null && props.hasSettings()) {
                List features = eClass.getEAllStructuralFeatures();
                for (Iterator itr = features.iterator(); itr.hasNext();) {
                    EStructuralFeature feature = (EStructuralFeature) itr.next();

                    staticObj.eSet(feature, eGet(feature));
//                    System.out.println("    F: " + feature.getName());
                }
            }

            // Update all external references
            for (Iterator itr = actions.iterator(); itr.hasNext();) {
                Action action = (Action) itr.next();
                action.doIt(staticObj);
            }
            
            // Update containing resource
//            List contents = eResource().getContents();
//            contents.remove(this);
//            contents.add(staticObj);
        }
        
        return staticObj;
    }

    /**
     * Converts this Object to a dynamic object with a new type that is a subtype of the current type, preserving any existing property values.
     * 
     * @param newEClass the new type for this Object
     * @throws IllegalArgumentException if newEClass is not a subtype of this Object's current type
     * @see org.eclipse.emf.ecore.InternalEObject#eSetClass(org.eclipse.emf.ecore.EClass)
     */
    public void narrow(EClass newEClass) throws IllegalArgumentException {
        if (!eClass().equals(EcorePackage.eINSTANCE.getEObject()) && !eClass().isSuperTypeOf(newEClass)) {
            throw new IllegalArgumentException(
                "The class "
                    + newEClass.getName()
                    + " is not a subtype of the Object's current type "
                    + eClass().getName());
        }
        EPropertiesHolder props = eProperties();
        if (props.getEClass() != null && props.hasSettings()) {
            int staticSize = eStaticFeatureCount();
            int oldSize =
                eClass().getEAllStructuralFeatures().size() - staticSize;
            if (oldSize > 0) {
                int newSize =
                    newEClass.getEAllStructuralFeatures().size() - staticSize;
                if (newSize > oldSize) {
                    List oldValues = new ArrayList(oldSize);
                    for (int i = 0; i < oldSize; i++) {
                        oldValues.add(props.dynamicGet(i));
                    }
                    props.allocateSettings(newSize);
                    for (int i = 0; i < oldSize; i++) {
                        props.dynamicSet(i, oldValues.get(i));
                    }
                }
            }
        }
        eSetClass(newEClass);
    }
    
    List actions = new ArrayList();
    
    protected void addReferenceFrom(final EObject instance, final EStructuralFeature feature) {
        actions.add(new AddSingleValuedReferenceAction(instance, feature));
    }
    
    protected void addMultiReferenceFrom(final EObject instance, final EStructuralFeature feature) {
        actions.add(new AddMultiValuedReferenceAction(feature, this, instance));
    }

    public String toString() {
        return "_" + hashCode() + ":" + eClass().getName();
    }
    
    static interface Action {
        public void doIt(EObject obj);
    }
    
    private static final class AddMultiValuedReferenceAction implements Action {
        private final EStructuralFeature feature;

        private final DynamicObject dynObj;

        private final EObject instance;

        private AddMultiValuedReferenceAction(EStructuralFeature feature, DynamicObject dynObj, EObject instance) {
            super();
            this.feature = feature;
            this.dynObj = dynObj;
            this.instance = instance;
        }

        public void doIt(EObject obj) {
            EObject referringInstance =
                (instance instanceof DynamicObject && ((DynamicObject) instance).hasStaticInstance())
                ? ((DynamicObject) instance).getStaticInstance()
                        : instance;
                
                List featureValues = (List) referringInstance.eGet(feature);
                int index = featureValues.indexOf(dynObj);
                featureValues.set(index, obj);
                //                System.out.println("    M: " + feature.getName());
        }
    }

    private static final class AddSingleValuedReferenceAction implements Action {
        private final EObject instance;
        
        private final EStructuralFeature feature;
        
        private AddSingleValuedReferenceAction(EObject instance, EStructuralFeature feature) {
            super();
            this.instance = instance;
            this.feature = feature;
        }
        
        public void doIt(EObject obj) {
            EObject referringInstance =
                (instance instanceof DynamicObject && ((DynamicObject) instance).hasStaticInstance())
                ? ((DynamicObject) instance).getStaticInstance()
                        : instance;
                
                referringInstance.eSet(feature, obj);
                //                System.out.println("    R: " + feature.getName());
        }
    }

}
