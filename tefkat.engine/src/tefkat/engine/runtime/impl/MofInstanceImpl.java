/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.DynamicObject;
import tefkat.engine.runtime.Extent;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.WrappedVar;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.MofInstance;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.model.internal.ModelUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mof Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.MofInstanceImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.MofInstanceImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.MofInstanceImpl#isExact <em>Exact</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MofInstanceImpl extends MofTermImpl implements MofInstance {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    private static final String NOT_BOUND_MESSAGE = " was not bound in source term!";

    /**
     * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeName()
     * @generated
     * @ordered
     */
    protected Expression typeName = null;

    /**
     * The cached value of the '{@link #getInstance() <em>Instance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstance()
     * @generated
     * @ordered
     */
    protected Expression instance = null;

    /**
     * The default value of the '{@link #isExact() <em>Exact</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExact()
     * @generated
     * @ordered
     */
    protected static final boolean EXACT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isExact() <em>Exact</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExact()
     * @generated
     * @ordered
     */
    protected boolean exact = EXACT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MofInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.MOF_INSTANCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getTypeName() {
        return typeName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTypeName(Expression newTypeName, NotificationChain msgs) {
        Expression oldTypeName = typeName;
        typeName = newTypeName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_INSTANCE__TYPE_NAME, oldTypeName, newTypeName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeName(Expression newTypeName) {
        if (newTypeName != typeName) {
            NotificationChain msgs = null;
            if (typeName != null)
                msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_INSTANCE__TYPE_NAME, null, msgs);
            if (newTypeName != null)
                msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_INSTANCE__TYPE_NAME, null, msgs);
            msgs = basicSetTypeName(newTypeName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_INSTANCE__TYPE_NAME, newTypeName, newTypeName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getInstance() {
        return instance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInstance(Expression newInstance, NotificationChain msgs) {
        Expression oldInstance = instance;
        instance = newInstance;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_INSTANCE__INSTANCE, oldInstance, newInstance);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInstance(Expression newInstance) {
        if (newInstance != instance) {
            NotificationChain msgs = null;
            if (instance != null)
                msgs = ((InternalEObject)instance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_INSTANCE__INSTANCE, null, msgs);
            if (newInstance != null)
                msgs = ((InternalEObject)newInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_INSTANCE__INSTANCE, null, msgs);
            msgs = basicSetInstance(newInstance, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_INSTANCE__INSTANCE, newInstance, newInstance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isExact() {
        return exact;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExact(boolean newExact) {
        boolean oldExact = exact;
        exact = newExact;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_INSTANCE__EXACT, oldExact, exact));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.MOF_INSTANCE__TYPE_NAME:
                return basicSetTypeName(null, msgs);
            case RuntimePackage.MOF_INSTANCE__INSTANCE:
                return basicSetInstance(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.MOF_INSTANCE__TYPE_NAME:
                return getTypeName();
            case RuntimePackage.MOF_INSTANCE__INSTANCE:
                return getInstance();
            case RuntimePackage.MOF_INSTANCE__EXACT:
                return isExact() ? Boolean.TRUE : Boolean.FALSE;
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
            case RuntimePackage.MOF_INSTANCE__TYPE_NAME:
                setTypeName((Expression)newValue);
                return;
            case RuntimePackage.MOF_INSTANCE__INSTANCE:
                setInstance((Expression)newValue);
                return;
            case RuntimePackage.MOF_INSTANCE__EXACT:
                setExact(((Boolean)newValue).booleanValue());
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
            case RuntimePackage.MOF_INSTANCE__TYPE_NAME:
                setTypeName((Expression)null);
                return;
            case RuntimePackage.MOF_INSTANCE__INSTANCE:
                setInstance((Expression)null);
                return;
            case RuntimePackage.MOF_INSTANCE__EXACT:
                setExact(EXACT_EDEFAULT);
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
            case RuntimePackage.MOF_INSTANCE__TYPE_NAME:
                return typeName != null;
            case RuntimePackage.MOF_INSTANCE__INSTANCE:
                return instance != null;
            case RuntimePackage.MOF_INSTANCE__EXACT:
                return exact != EXACT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        return getTypeName() + " " + getInstance() + " " + getExtent();
    }

    public void match(Context context) throws ResolutionException, NotGroundException {
        /**
         * Find all instances of the specified class in the (context) extent
         */
        Var extentVar = getExtent();

        Collection results = getTypeName().eval(context);
        if (results.size() != 1) {
            context.error("Expected only a single type name, got: " + results);
        }
        Object typeObj = results.toArray()[0];
        EClassifier theClass = null;
        String className;
        if (typeObj instanceof EClassifier) {
            theClass = (EClassifier) typeObj;
            className = ModelUtils.getFullyQualifiedName(theClass);
        } else if (typeObj instanceof WrappedVar) {
            context.error("Unsupported mode (unbound typeName) for MofInstance: " + this);
            // satisfy the compiler (it doesn't know the previous line always throws an exception)
            className = null;
        } else {
            className = String.valueOf(typeObj);
            if (!"_".equals(className)) {
                theClass = context.findClassifierByName(className);
                if (null == theClass) {
                    context.warn("Could not find class named: " + className);
                    return;
                }
            }
        }
        
        // Our "package instance" wrapper around an EMOF ExtentUtil (EMF Resource)
        // FIXME why don't we call "eval" on Vars?
        Extent extent = (null == extentVar ? null : (Extent) context.lookup(extentVar));

        Collection instances = getInstance().eval(context);

        boolean success = false;
        
        for (Iterator itr = instances.iterator(); itr.hasNext(); ) {
            Object instance = itr.next();
            
            if (instance instanceof WrappedVar) {
                // handle the ??- mode
                //
                // does lazy expansion of the tree.
                // This will cause relatively untested code (and broken?) to be used in
                // other parts of Abstract/Source/TargetResolver and Evaluator
                // FIXME make this stuff work
                // FIXME I think it works, but some Unit tests would make me more comfortable
                WrappedVar wVar = (WrappedVar) instance;
                wVar.setExtent(extent);
                if ("_".equals(className)) {
                    // Any type will do, isExact in this context is meaningless
                    // hence, no need to call setType on the WrappedVar
                    Binding unifier = new Binding();
                    unifier.add(wVar.getVar(), wVar);
                    context.createBranch(unifier);
                    success = true;
                } else if (!(theClass instanceof EClass)) {
                    context.warn("Could not find class named: " + className);
                } else if (wVar.setType((EClass) theClass, isExact())) {
                    Binding unifier = new Binding();
                    unifier.add(wVar.getVar(), wVar);
                    context.createBranch(unifier);
                    success = true;
                }
            } else {
                // handle the ??+ mode
                Binding unifier = null;
                if (instance instanceof BindingPair) {
                    unifier = (Binding) instance;
                    instance = ((BindingPair) instance).getValue();
                }
                if (instance instanceof EObject && (null == extent || extent.contains((EObject) instance))) {
// FIXME                    ExtentUtil.highlightNode(instance, ExtentUtil.OBJECT_LOOKUP);
                    boolean isOfType =
                        null == theClass ||
                        (isExact() ? theClass.equals(((EObject) instance).eClass()) : theClass.isInstance(instance));
                    if (isOfType) {
                        /**
                         * Create a new branch of the tree, and continue 
                         * resolution from the newly created node.
                         */
                        context.createBranch(unifier);
                        success = true;
                    }
                }
            }
        }

        if (!success) {
            context.fail();
        }
    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {

        Collection instances = getInstance().eval(context);

        // deal with type
        List results = getTypeName().eval(context);
        if (results.size() != 1) {
            context.error("Expected only a single type name, got: " + results);
        }
        Object typeObj = results.get(0);
        
        Binding unifier = new Binding();

        for (Iterator itr = instances.iterator(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            // System.err.println(obj);   // TODO delete
            if (obj instanceof WrappedVar) {
                // ruleEval.fireInfo(obj + DELAYING_MESSAGE);
                context.delay(obj + NOT_BOUND_MESSAGE);
            } else {
                EObject eObj = (EObject) obj;
                
                if (typeObj instanceof WrappedVar) {
                    unifier.add(((WrappedVar) typeObj).getVar(), eObj.eClass().getName());
                } else if (typeObj instanceof String) {
                    // _ is the universal type -> it always matches
                    if (!"_".equals(typeObj)) {
                        String typeName = (String) typeObj;
                        EClassifier eClassifier = context.findClassifierByName(typeName);
                        if (null == eClassifier) {
//                          for (final Iterator nItr = nameMap.keySet().iterator(); nItr.hasNext(); ) {
//                              Object entry =  nItr.next();
//                              System.out.println(entry);
//                          }
                            context.error("Expected an EClass called: " + typeName + ", but found nothing");
                        } else if (!(eClassifier instanceof EClass)) {
                            context.error("Expected an EClass called: " + typeName + ", but found an EDataType or EEnum");
                        }
                        EClass subCls = (EClass) eClassifier;
                        
                        boolean result = conformToType(eObj, subCls);
                        if (!result) {
                            context.error("Type mismatch, " + typeName + " not compatible with " + eObj.eClass());
                        }
                    }
                } else if (typeObj instanceof EClass) {
                    EClass subCls = (EClass) typeObj;
                    boolean result = conformToType(eObj, subCls);
                    if (!result) {
                        context.error("Type mismatch, " + subCls + " not compatible with " + eObj.eClass());
                    }
                } else {
                    context.error("Invalid Expression type for MofInstance.typeName: " + typeObj);
                }
                
                if (isExact() && eObj instanceof DynamicObject) {
                    if (eObj.eResource() != null) {
                        eObj.eResource().getContents().remove(eObj);
//                      System.err.println("  ...removed: " + eObj.hashCode());
                    }
                    eObj = ((DynamicObject) eObj).getStaticInstance();
                }
                
                // deal with extent
                Extent extentObj = (Extent) context.lookup(getExtent());
                if (null != extentObj) {
                    // Extents are optional, but dangling objects may be created that
                    // should cause errors when the Resource is saved, but only if there
                    // is a reference to the object, but no containment reference.
                    Resource res = eObj.eResource();
                    if (null == res) {
                        extentObj.add(eObj);
                    } else if (!extentObj.contains(eObj)) {
                        context.error("Object (" + eObj + ") cannot exist in multiple resources, " + res + " and " + extentObj);
                    }
                }
            }
        }
        
        context.createBranch(unifier);
    }

    /**
     * @param eObj
     * @param subCls
     * @return
     */
    private static boolean conformToType(EObject eObj, EClass subCls) {
        boolean result = true;
        EClass cls = eObj.eClass();
        if (eObj instanceof DynamicObject) {
            if (cls.equals(EcorePackage.eINSTANCE.getEObject())) {
                // EObject is a supertype of all types
                ((DynamicObject) eObj).narrow(subCls);
            } else if (subCls.isSuperTypeOf(cls)) {
                // never widen the type of the object (this catches the
                // equals case also, so must come before next test)
            } else if (cls.isSuperTypeOf(subCls)) {
                // narrow the type of the previously created object
                ((DynamicObject) eObj).narrow(subCls);
            } else {
                result = false;
            }
        } else {
            if (subCls.isSuperTypeOf(cls)) {
                // never widen the type of the object (this catches the
                // equals case also, so must come before next test)
//            } else if (cls.isSuperTypeOf(subCls)) {
//                throw new RuntimeException("Can't narrow the type of a non-dynamic instance");
            } else {
                result = false;
            }
        }
        return result;
    }

} //MofInstanceImpl