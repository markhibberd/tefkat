/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.WrappedVar;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.MofOrder;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mof Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.MofOrderImpl#getLesser <em>Lesser</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.MofOrderImpl#getGreater <em>Greater</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.MofOrderImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.MofOrderImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MofOrderImpl extends MofTermImpl implements MofOrder {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getLesser() <em>Lesser</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLesser()
     * @generated
     * @ordered
     */
    protected Expression lesser = null;

    /**
     * The cached value of the '{@link #getGreater() <em>Greater</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGreater()
     * @generated
     * @ordered
     */
    protected Expression greater = null;

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
     * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeature()
     * @generated
     * @ordered
     */
    protected Expression feature = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MofOrderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.MOF_ORDER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getLesser() {
        return lesser;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLesser(Expression newLesser, NotificationChain msgs) {
        Expression oldLesser = lesser;
        lesser = newLesser;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__LESSER, oldLesser, newLesser);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLesser(Expression newLesser) {
        if (newLesser != lesser) {
            NotificationChain msgs = null;
            if (lesser != null)
                msgs = ((InternalEObject)lesser).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__LESSER, null, msgs);
            if (newLesser != null)
                msgs = ((InternalEObject)newLesser).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__LESSER, null, msgs);
            msgs = basicSetLesser(newLesser, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__LESSER, newLesser, newLesser));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getGreater() {
        return greater;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGreater(Expression newGreater, NotificationChain msgs) {
        Expression oldGreater = greater;
        greater = newGreater;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__GREATER, oldGreater, newGreater);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGreater(Expression newGreater) {
        if (newGreater != greater) {
            NotificationChain msgs = null;
            if (greater != null)
                msgs = ((InternalEObject)greater).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__GREATER, null, msgs);
            if (newGreater != null)
                msgs = ((InternalEObject)newGreater).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__GREATER, null, msgs);
            msgs = basicSetGreater(newGreater, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__GREATER, newGreater, newGreater));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__INSTANCE, oldInstance, newInstance);
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
                msgs = ((InternalEObject)instance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__INSTANCE, null, msgs);
            if (newInstance != null)
                msgs = ((InternalEObject)newInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__INSTANCE, null, msgs);
            msgs = basicSetInstance(newInstance, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__INSTANCE, newInstance, newInstance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFeature(Expression newFeature, NotificationChain msgs) {
        Expression oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__FEATURE, oldFeature, newFeature);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeature(Expression newFeature) {
        if (newFeature != feature) {
            NotificationChain msgs = null;
            if (feature != null)
                msgs = ((InternalEObject)feature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__FEATURE, null, msgs);
            if (newFeature != null)
                msgs = ((InternalEObject)newFeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.MOF_ORDER__FEATURE, null, msgs);
            msgs = basicSetFeature(newFeature, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MOF_ORDER__FEATURE, newFeature, newFeature));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.MOF_ORDER__LESSER:
                return basicSetLesser(null, msgs);
            case RuntimePackage.MOF_ORDER__GREATER:
                return basicSetGreater(null, msgs);
            case RuntimePackage.MOF_ORDER__INSTANCE:
                return basicSetInstance(null, msgs);
            case RuntimePackage.MOF_ORDER__FEATURE:
                return basicSetFeature(null, msgs);
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
            case RuntimePackage.MOF_ORDER__LESSER:
                return getLesser();
            case RuntimePackage.MOF_ORDER__GREATER:
                return getGreater();
            case RuntimePackage.MOF_ORDER__INSTANCE:
                return getInstance();
            case RuntimePackage.MOF_ORDER__FEATURE:
                return getFeature();
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
            case RuntimePackage.MOF_ORDER__LESSER:
                setLesser((Expression)newValue);
                return;
            case RuntimePackage.MOF_ORDER__GREATER:
                setGreater((Expression)newValue);
                return;
            case RuntimePackage.MOF_ORDER__INSTANCE:
                setInstance((Expression)newValue);
                return;
            case RuntimePackage.MOF_ORDER__FEATURE:
                setFeature((Expression)newValue);
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
            case RuntimePackage.MOF_ORDER__LESSER:
                setLesser((Expression)null);
                return;
            case RuntimePackage.MOF_ORDER__GREATER:
                setGreater((Expression)null);
                return;
            case RuntimePackage.MOF_ORDER__INSTANCE:
                setInstance((Expression)null);
                return;
            case RuntimePackage.MOF_ORDER__FEATURE:
                setFeature((Expression)null);
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
            case RuntimePackage.MOF_ORDER__LESSER:
                return lesser != null;
            case RuntimePackage.MOF_ORDER__GREATER:
                return greater != null;
            case RuntimePackage.MOF_ORDER__INSTANCE:
                return instance != null;
            case RuntimePackage.MOF_ORDER__FEATURE:
                return feature != null;
        }
        return super.eIsSet(featureID);
    }

    public void match(Context context) throws ResolutionException, NotGroundException {
        List instances = getInstance().eval(context);
        List features = getFeature().eval(context);
        List lesserObjects = getLesser().eval(context);
        List greaterObjects = getGreater().eval(context);
        
        for (Iterator iItr = instances.iterator(); iItr.hasNext(); ) {
            Object instance = iItr.next();
            
            if (instance instanceof WrappedVar) {
                context.delay("Unsupported mode (unbound '" + getInstance() + "') for MofOrder: " + this);
            }
            
            for (Iterator fItr = features.iterator(); fItr.hasNext(); ) {
                Object featureRef = fItr.next();

                if (featureRef instanceof WrappedVar) {
                    context.delay("Unsupported mode (unbound '" + getFeature() + "') for MofOrder: " + this);
                } else if (featureRef instanceof EStructuralFeature) {
                    featureRef = ((EStructuralFeature) featureRef).getName();
                }
                
                Object values = context.fetchFeature((String) featureRef, instance);
                if (!(values instanceof List)) {
                    context.error("The feature " + featureRef + " of " + instance + " did not return an ordered collection.");
                }
                List valueList = (List) values;
                
                for (Iterator lItr = lesserObjects.iterator(); lItr.hasNext(); ) {
                    Object lesser = lItr.next();
                    
                    if (lesser instanceof WrappedVar) {
                        for (int i = 0; i < valueList.size(); i++) {
                            Binding unifier = Binding.bindWrappedVar(null, (WrappedVar) lesser, valueList.get(i));
                            processGreaterObjects(context, unifier, greaterObjects, valueList, i);
                        }
                    } else {
                        int index = valueList.indexOf(lesser);
                        processGreaterObjects(context, null, greaterObjects, valueList, index);
                    }
                }
            }
        }
    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {
        List featVals = getFeature().eval(context);
        List instVals = getInstance().eval(context);
        List lesserVals = getLesser().eval(context);
        List greaterVals = getGreater().eval(context);
        
        for (final Iterator fItr = featVals.iterator(); fItr.hasNext(); ) {
            Object feat = fItr.next();
            Binding fUnifier = null;
            if (feat instanceof WrappedVar) {
                Var var = ((WrappedVar) feat).getVar();
                context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + this);
            } else if (feat instanceof BindingPair) {
                fUnifier = (BindingPair) feat;
                feat = ((BindingPair) feat).getValue();
            }
            for (final Iterator iItr = instVals.iterator(); iItr.hasNext(); ) {
                Object inst = iItr.next();
                Binding iUnifier = fUnifier;
                if (inst instanceof WrappedVar) {
                    Var var = ((WrappedVar) inst).getVar();
                    context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + this);
                } else if (inst instanceof BindingPair) {
                    if (null == iUnifier) {
                        iUnifier = (BindingPair) inst;
                    } else {
                        iUnifier = new Binding(fUnifier);
                        iUnifier.composeRight((BindingPair) inst);
                    }
                    inst = ((BindingPair) inst).getValue();
                }
                for (final Iterator lItr = lesserVals.iterator(); lItr.hasNext(); ) {
                    Object lesser = lItr.next();
                    Binding lUnifier = iUnifier;
                    if (lesser instanceof WrappedVar) {
                        Var var = ((WrappedVar) lesser).getVar();
                        context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + this);
                    } else if (lesser instanceof BindingPair) {
                        if (null == lUnifier) {
                            lUnifier = (BindingPair) lesser;
                        } else {
                            lUnifier = new Binding(iUnifier);
                            lUnifier.composeRight((BindingPair) lesser);
                        }
                        lesser = ((BindingPair) lesser).getValue();
                    }
                    for (final Iterator gItr = greaterVals.iterator(); gItr.hasNext(); ) {
                        Object greater = gItr.next();
                        Binding gUnifier = lUnifier;
                        if (greater instanceof WrappedVar) {
                            Var var = ((WrappedVar) greater).getVar();
                            context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + this);
                        } else if (greater instanceof BindingPair) {
                            if (null == gUnifier) {
                                gUnifier = (BindingPair) greater;
                            } else {
                                gUnifier = new Binding(lUnifier);
                                gUnifier.composeRight((BindingPair) greater);
                            }
                            greater = ((BindingPair) greater).getValue();
                        }

                        context.addPartialOrder(inst, feat, lesser, greater);

                        context.createBranch(gUnifier);
                    }
                }
            }
        }
    }

    /**
     * 
     * @param context
     * @param greaterObjects
     * @param valueList all values of feature (ordered)
     * @param lindex index of lesser object in valueList
     * @throws ResolutionException
     */
    private static void processGreaterObjects(final Context context, final Binding unifier,
            final List greaterObjects, final List valueList, final int lindex)
    throws ResolutionException {
        for (Iterator gItr = greaterObjects.iterator(); gItr.hasNext(); ) {
            Object greater = gItr.next();
        
            if (greater instanceof WrappedVar) {
                final WrappedVar wrappedVar = (WrappedVar) greater;
                // greater was unbound, so bind to all greater instances from valueList
                
                for (int i = lindex + 1; i < valueList.size(); i++) {
                    Object val = valueList.get(i);
                    
                    Binding unifier2 = Binding.bindWrappedVar(new Binding(unifier), wrappedVar, val);
                    if (null != unifier2) {
                        context.createBranch(unifier2);
                    }
                }
            } else if (lindex < valueList.indexOf(greater)) {
                context.createBranch(unifier);
            }
        }
    }

} //MofOrderImpl