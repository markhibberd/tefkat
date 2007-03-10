/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.Extent;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.TrackingCallback;
import tefkat.engine.runtime.TrackingUse;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.WrappedVar;
import tefkat.model.internal.ModelUtils;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracking Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.TrackingUseImpl#getTracking <em>Tracking</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TrackingUseImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TrackingUseImpl#getTrackingName <em>Tracking Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackingUseImpl extends SimpleTermImpl implements TrackingUse {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getTracking() <em>Tracking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTracking()
     * @generated
     * @ordered
     */
    protected EClass tracking = null;

    /**
     * The cached value of the '{@link #getFeatures() <em>Features</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatures()
     * @generated
     * @ordered
     */
    protected EMap features = null;

    /**
     * The default value of the '{@link #getTrackingName() <em>Tracking Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTrackingName()
     * @generated
     * @ordered
     */
    protected static final String TRACKING_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTrackingName() <em>Tracking Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTrackingName()
     * @generated
     * @ordered
     */
    protected String trackingName = TRACKING_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TrackingUseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.TRACKING_USE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTracking() {
        if (tracking != null && tracking.eIsProxy()) {
            InternalEObject oldTracking = (InternalEObject)tracking;
            tracking = (EClass)eResolveProxy(oldTracking);
            if (tracking != oldTracking) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.TRACKING_USE__TRACKING, oldTracking, tracking));
            }
        }
        return tracking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass basicGetTracking() {
        return tracking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTracking(EClass newTracking) {
        EClass oldTracking = tracking;
        tracking = newTracking;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TRACKING_USE__TRACKING, oldTracking, tracking));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getFeatures() {
        if (features == null) {
            features = new EcoreEMap(RuntimePackage.Literals.FEATURE_VALUE_PAIR, FeatureValuePairImpl.class, this, RuntimePackage.TRACKING_USE__FEATURES);
        }
        return features;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTrackingName() {
        return trackingName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTrackingName(String newTrackingName) {
        String oldTrackingName = trackingName;
        trackingName = newTrackingName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TRACKING_USE__TRACKING_NAME, oldTrackingName, trackingName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.TRACKING_USE__FEATURES:
                return ((InternalEList)getFeatures()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.TRACKING_USE__TRACKING:
                if (resolve) return getTracking();
                return basicGetTracking();
            case RuntimePackage.TRACKING_USE__FEATURES:
                if (coreType) return getFeatures();
                else return getFeatures().map();
            case RuntimePackage.TRACKING_USE__TRACKING_NAME:
                return getTrackingName();
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
            case RuntimePackage.TRACKING_USE__TRACKING:
                setTracking((EClass)newValue);
                return;
            case RuntimePackage.TRACKING_USE__FEATURES:
                ((EStructuralFeature.Setting)getFeatures()).set(newValue);
                return;
            case RuntimePackage.TRACKING_USE__TRACKING_NAME:
                setTrackingName((String)newValue);
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
            case RuntimePackage.TRACKING_USE__TRACKING:
                setTracking((EClass)null);
                return;
            case RuntimePackage.TRACKING_USE__FEATURES:
                getFeatures().clear();
                return;
            case RuntimePackage.TRACKING_USE__TRACKING_NAME:
                setTrackingName(TRACKING_NAME_EDEFAULT);
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
            case RuntimePackage.TRACKING_USE__TRACKING:
                return tracking != null;
            case RuntimePackage.TRACKING_USE__FEATURES:
                return features != null && !features.isEmpty();
            case RuntimePackage.TRACKING_USE__TRACKING_NAME:
                return TRACKING_NAME_EDEFAULT == null ? trackingName != null : !TRACKING_NAME_EDEFAULT.equals(trackingName);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        final String name = null == getTracking() ? getTrackingName() : getTracking().getName();
        if (isTarget()) {
            result.append("LINKING ");
            result.append(name);
            result.append(" WITH ");
        } else {
            result.append(name);
            result.append(" LINKS ");
        }
        for (Iterator itr = getFeatures().iterator(); itr.hasNext(); ) {
            Object feature = itr.next();
            result.append(feature);
            if (itr.hasNext()) {
                result.append(", ");
            }
        }
        result.append(')');
        return result.toString();
    }

    public void match(Context context) throws ResolutionException, NotGroundException {

        // Get the properties of the TrackingUse
        
        final EClass trackingClass = getTracking();
        if (trackingClass.eIsProxy()) {
            // If it's still a proxy after the getTracking() call, the cross-document reference proxy has
            // not been resolved, meaning the reference was dodgy, i.e. to a non-existent class or something
            //
            context.error("Unable to locate tracking class: " + trackingClass);
        }

        List featureList = getFeatures();
        final Object[][] featureMap = new Object[featureList.size()][2];
        
        int i = 0;
        for (Iterator itr = featureList.iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            featureMap[i][0] = entry.getKey();
            featureMap[i][1] = ((Expression) entry.getValue()).eval(context);
            i++;
        }

        TrackingCallback callback = new HandleNewTrackingInstance(context, trackingClass, featureMap);

        // Get the existing instances of the tracking class.
        //
        List trackings = context.getObjectsByClass(trackingClass, false, callback);
        
        for (Iterator trackingItr = trackings.iterator(); trackingItr.hasNext(); ) {
            EObject inst = (EObject) trackingItr.next();
            
            callback.handleInstance(inst);
        }
    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {
        // TODO Auto-generated method stub
        
    }

    private static final class HandleNewTrackingInstance implements TrackingCallback {
        private final Context context;

        private final EClass class1;

        private final Object[][] map;

        HandleNewTrackingInstance(Context context, EClass class1, Object[][] map) {
            this.context = context;
            this.class1 = class1;
            this.map = map;
        }

        public String toString() {
            return "NewTrackingInstance " + context/* FIXME tree*/.toString();
        }

        public void handleInstance(EObject inst) throws ResolutionException, NotGroundException {
            if (context.isCompleted()) {
                context.error("INTERNAL ERROR: Tree completed too early: " + context);
            }
        
            // Check each feature looking for a mismatch
            List oldBindings = new ArrayList();
            oldBindings.add(new Binding());
            
            boolean isMatch = true;
            for (int i = 0; isMatch && i < map.length; i++) {
                String featureName = (String) map[i][0];
                List featureValues = (List) map[i][1];
                List newBindings = null;
                
                EStructuralFeature sFeature = context.getFeature(class1, featureName);
                Object value = inst.eGet(sFeature);
        
                if (value == null) {
                    // NOT_EQUAL, NULL
                    isMatch = false;
                } else if (sFeature.isMany()) {
                    if (((List) value).size() == 0) {
                        isMatch = false;
                    } else if (featureValues.size() == 1 && featureValues.get(0) instanceof WrappedVar) {
                        // UNIFY
                        Var var = ((WrappedVar) featureValues.get(0)).getVar();
                        newBindings = new ArrayList();
                        for (Iterator bindingItr = oldBindings.iterator(); bindingItr.hasNext(); ) {
                            Binding oldUnifier = (Binding) bindingItr.next();
                            for (Iterator valueItr = ((List) value).iterator(); valueItr.hasNext(); ) {
                                Binding unifier = new Binding(oldUnifier);
                                unifier.add(var, valueItr.next());
                                newBindings.add(unifier);
                            }
                        }
//                        ExtentUtil.highlightEdge(inst, value, ExtentUtil.FEATURE_LOOKUP);
                    } else {
                        for (Iterator valItr = ((List) value).iterator(); valItr.hasNext(); ) {
                            Object o = valItr.next();
                            if (o instanceof BindingPair) {
                                context.delay("Implementation limitiation: BindingPair in TrackingUse not yet supported.");
                            }
                        }
                        if (featureValues.removeAll((List) value)) {
                            newBindings = oldBindings;
                        } else {
                            isMatch = false;
                        }
                    }
                } else if (featureValues.size() == 1) {
                    Object featureValue = featureValues.get(0);
                    if (featureValue instanceof WrappedVar) {
                        // UNIFY
                        Var var = ((WrappedVar) featureValue).getVar();
                        newBindings = new ArrayList();
                        for (Iterator bindingItr = oldBindings.iterator(); bindingItr.hasNext(); ) {
                            Binding oldUnifier = (Binding) bindingItr.next();
                            Binding unifier = new Binding(oldUnifier);
                            unifier.add(var, value);
                            newBindings.add(unifier);
                        }
//                        ExtentUtil.highlightEdge(inst, value, ExtentUtil.FEATURE_LOOKUP);
                    } else if (featureValue instanceof BindingPair) {
                        context.delay("Implementation limitiation: BindingPair in TrackingUse not yet supported.");
                    } else if (value.equals(featureValue)) {
                        newBindings = oldBindings;
                    } else {
                        // NOT-EQUAL, non-NULL
                        isMatch = false;
                    }
                } else {
                    // NOT_EQUAL, cardinality mismatch
                    isMatch = false;
                }
                
                oldBindings = newBindings;
            }
            
            if (isMatch) {
                for (Iterator itr = oldBindings.iterator(); itr.hasNext(); ) {
                    Binding unifier = (Binding) itr.next();
                    /**
                     * Create a new branch of the tree, and continue 
                     * resolution from the newly created node.
                     */
                    context.createBranch(unifier);
                 }
            }
        }
    }

} //TrackingUseImpl

