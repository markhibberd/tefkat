/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Condition;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.DynamicObject;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.FeatureExpr;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.WrappedVar;
import tefkat.model.internal.ModelUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.ConditionImpl#getArg <em>Arg</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.ConditionImpl#getRelation <em>Relation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionImpl extends SimpleTermImpl implements Condition {
    
    final private static String[] relOpArray = {
        "<", "<=", ">", ">=", "!="
    };
    final private static List relOpList = Arrays.asList(relOpArray);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getArg() <em>Arg</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArg()
     * @generated
     * @ordered
     */
    protected EList arg = null;

    /**
     * The default value of the '{@link #getRelation() <em>Relation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelation()
     * @generated
     * @ordered
     */
    protected static final String RELATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRelation() <em>Relation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelation()
     * @generated
     * @ordered
     */
    protected String relation = RELATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getArg() {
        if (arg == null) {
            arg = new EObjectContainmentEList(Expression.class, this, RuntimePackage.CONDITION__ARG);
        }
        return arg;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRelation() {
        return relation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelation(String newRelation) {
        String oldRelation = relation;
        relation = newRelation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.CONDITION__RELATION, oldRelation, relation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.CONDITION__ARG:
                return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.CONDITION__ARG:
                return getArg();
            case RuntimePackage.CONDITION__RELATION:
                return getRelation();
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
            case RuntimePackage.CONDITION__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
                return;
            case RuntimePackage.CONDITION__RELATION:
                setRelation((String)newValue);
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
            case RuntimePackage.CONDITION__ARG:
                getArg().clear();
                return;
            case RuntimePackage.CONDITION__RELATION:
                setRelation(RELATION_EDEFAULT);
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
            case RuntimePackage.CONDITION__ARG:
                return arg != null && !arg.isEmpty();
            case RuntimePackage.CONDITION__RELATION:
                return RELATION_EDEFAULT == null ? relation != null : !RELATION_EDEFAULT.equals(relation);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        final List args = getArg();
        switch (args.size()) {
        case 0:
            return getRelation();
        case 1:
            return getRelation() + " " + args.get(0);
        case 2:
            return args.get(0) + " " + getRelation() + " " + args.get(1);
        default:
            return getRelation() + " " + args;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void match(Context context) throws ResolutionException, NotGroundException {
        boolean result = false;

        String relation = getRelation();
        List args = getArg();

        if ("=".equals(relation)) {
            Collection vals1 = ((Expression) args.get(0)).eval(context);
            Collection vals2 = ((Expression) args.get(1)).eval(context);
            
            // TODO need to check handling of all the possible cases
            // Eg X = Y, X = 3, 3 = X, 2 = 3, 3 = 3,
            // (Z = 1, X = Z), (Z = 1, 2 = Z), (X = Y, Z = X), etc
            for (final Iterator itr1 = vals1.iterator(); itr1.hasNext(); ) {
                Object val1 = itr1.next();
                
                for (final Iterator itr2 = vals2.iterator(); itr2.hasNext(); ) {
                    Object val2 = itr2.next();
                    
                    Binding unifier = Binding.createBinding(val1, val2);

                    if (null != unifier) {
                        result = true;
                        context.createBranch(unifier);
                    }
                }
            }
        } else if (relOpList.contains(relation)) {
            Collection vals1 = ((Expression) args.get(0)).eval(context);
            Collection vals2 = ((Expression) args.get(1)).eval(context);
            for (Iterator itr1 = vals1.iterator(); itr1.hasNext(); ) {
                Object val1 = itr1.next();
                // System.err.println("** " + val1);
                
                for (Iterator itr2 = vals2.iterator(); itr2.hasNext(); ) {
                    Object val2 = itr2.next();
                    // System.err.println("**** " + val2);

                    if (val1 instanceof WrappedVar) {
                        context.delay("Unbound Var, " + val1 + ", not allowed in Condition.");
                    } else if (val2 instanceof WrappedVar) {
                        context.delay("Unbound Var, " + val2 + ", not allowed in Condition.");
                    } else {
                        Binding unifier = new Binding();
                        if (val1 instanceof BindingPair) {
                            unifier.composeRight((BindingPair) val1);
                            val1 = ((BindingPair) val1).getValue();
                        }
                        if (val2 instanceof BindingPair) {
                            unifier.composeRight((BindingPair) val2);
                            val2 = ((BindingPair) val2).getValue();
                        }
                        if (compare(context, relation, val1, val2)) {
                            result = true;
                            context.createBranch(unifier);
//                        } else {
                            // no match - result unchanged
                        }
                    }
                }
            }
        } else if (relation.equals("boolean")) {
            Collection vals = ((Expression) args.get(0)).eval(context);
            List bindings = new ArrayList();
            for (Iterator itr = vals.iterator(); itr.hasNext(); ) {
                Object val = itr.next();
                if (val instanceof BindingPair) {
                    Object bVal = ((BindingPair) val).getValue();
                    if (Boolean.TRUE.equals(bVal)) {
                        result = true;
                        bindings.add(val);
                    } else if (Boolean.FALSE.equals(bVal)) {
                        // do nothing
                    } else if (bVal instanceof WrappedVar) {
                        context.delay("Unbound Var, " + bVal + ", not allowed in Condition.");
                    } else {
                        context.error("Condition did not reference a boolean valued Expression.");
                    }
                }
                if (Boolean.TRUE.equals(val)) {
                    result = true;
                } else if (Boolean.FALSE.equals(val)) {
                    // do nothing
                } else if (val instanceof WrappedVar) {
                    context.delay("Unbound Var, " + val + ", not allowed in Condition.");
                } else {
                    context.error("Condition did not reference a boolean valued Expression.");
                }
            }

            // This is outside the loop since there's no point in creating
            // multiple branches for the same (new) goal and empty Binding.
            if (result) {
                if (bindings.size() > 0) {
                    for (Iterator itr = bindings.iterator(); itr.hasNext(); ) {
                        context.createBranch((Binding) itr.next());
                    }
                } else {
                    context.createBranch();
                }
            }
        } else {
            context.error("Unknown relation '" + relation + "' in Condition");
        }
        
        if (!result) {
            context.fail();
        }
    }
    
    private boolean compare(Context context, String relation, Object val1, Object val2)
        throws ResolutionException {

        long cmp;
        
        if (val1 instanceof Number && val2 instanceof Number) {
            if (val1 instanceof Float || val1 instanceof Double ||
                val2 instanceof Float || val2 instanceof Double) {
                double dval1 = ((Number) val1).doubleValue();
                double dval2 = ((Number) val2).doubleValue();
                cmp = (long) (dval1 - dval2);
            } else if (val1 instanceof BigInteger || val1 instanceof BigDecimal ||
                       val2 instanceof BigInteger || val2 instanceof BigDecimal) {
                try {
                    cmp = ((Comparable) val1).compareTo(val2);
                } catch (ClassCastException e) {
                    context.error(val1 + " and " + val2 + " are not comparable.", e);
                    cmp = 0;    // notreached
                }
            } else {
                long lval1 = ((Number) val1).longValue();
                long lval2 = ((Number) val2).longValue();
                cmp = lval1 - lval2;
            }
        } else if (val1 instanceof Comparable) {
            try {
                cmp = ((Comparable) val1).compareTo(val2);
            } catch (ClassCastException e) {
                context.error(val1 + " and " + val2 + " are not comparable.", e);
                cmp = 0;    // notreached
            }
        } else if ("!=".equals(relation)) {
            return !val1.equals(val2);
        } else {
            context.error(val1 + " and " + val2 + " are not comparable.");
            cmp = 0;    // notreached
        }

        if (cmp < 0 && relation.charAt(0) == '<') { // "<".equals(relation) || "<=".equals(relation)
            return true;
        } else if (cmp > 0 && relation.charAt(0) == '>') {  // ">".equals(relation) || ">=".equals(relation)
            return true;
        } else if (cmp == 0 && ("<=".equals(relation) || ">=".equals(relation))) {
            return true;
        } else if (cmp != 0 && "!=".equals(relation)) {
            return true;
        } else {
            return false;
        }
    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {
        String relation = getRelation();

        if (relation.equals("=")) {
            handleBindingCondition(context);
        } else if (relation.equals("boolean")) {
            handleBooleanCondition(context);
        } else {
            context.error("Target condition containing "
                    + relation + " is Not Yet Implemented");
        }
    }

    private void handleBindingCondition(final Context context)
    throws ResolutionException, NotGroundException {
        List args = getArg();

        // The following possibilities exist:
        //   LHS & RHS unbound
        //   LHS unbound & RHS bound
        //   LHS bound & RHS unbound
        //   LHS & RHS bound
        
        // For now, implement the following:
        //   <expr>.y = <valExpr>;
        Expression valExpr = (Expression) args.get(1);
        List vals = valExpr.eval(context);
        
        if (args.get(0) instanceof FeatureExpr) {
            Binding unifier = null;

            FeatureExpr featExpr = (FeatureExpr) args.get(0);
            Collection featureNames = featExpr.getFeature().eval(context);
            Collection objs = ((Expression) featExpr.getArg().get(0)).eval(context);
            
            for (Iterator fItr = featureNames.iterator(); fItr.hasNext(); ) {
                Object fObj = fItr.next();
                EStructuralFeature featureObj = null;
                String featureName = null;

                if (fObj instanceof WrappedVar) {
                    Var var = ((WrappedVar) fObj).getVar();
                    context.delay(
                        "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featExpr.getFeature());
                } else if (fObj instanceof EStructuralFeature) {
                    featureObj = (EStructuralFeature) fObj;
                } else if (!(fObj instanceof String)) {
                    context.error("The Feature Expression " + featExpr + " must evaluate to a feature name of type String, not " + fObj.getClass());
                } else {
                    featureName = (String) fObj;
                }
                
                for (Iterator itr = objs.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                
                    if (obj instanceof WrappedVar) {
                        Var var = ((WrappedVar) obj).getVar();
                        context.delay(
                            "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featureName);
                    } else if (!(obj instanceof EObject)) {
                        context.error("Target object is not an EObject (i.e., not a valid model instance)" + featExpr.getArg().get(0));
                    }
                
                    EObject instance = (EObject) obj;

                    EStructuralFeature theFeature;
                    if (null == featureObj) {
                        theFeature = context.getFeature(instance.eClass(), featureName);
                    } else {
                        EClass objClass = instance.eClass();
                        EClass featureClass = featureObj.getEContainingClass();
                        if (objClass.equals(featureClass) || objClass.getEAllSuperTypes().contains(featureClass)) {
                            theFeature = featureObj;
                        } else {
                            context.error("The target feature " + featureObj + " does not belong to the object " + instance);
                            theFeature = null;  // notreached
                        }
                    }
                    
                    if (theFeature.isMany()) {
                        // Adding multiple feature values: featureName = vals
                        if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                            Object newVal = vals.get(0);
                            // ruleEval.fireInfo(newVal + DELAYING_MESSAGE);
                            context.delay(newVal + NOT_BOUND_MESSAGE);
                        }
                        // values are always added for multi-valued features
                        List featureValues = (List) instance.eGet(theFeature);
                        List newVals = Util.coerceTypes(vals, theFeature);
                        try {
                            // Insert at beginning so that we have a chance of preserving
                            // the order from the source model (the Node-tree traversal 
                            // would otherwise naturally invert the order).
                            
                            if (theFeature.isUnique()) {
                                // This is normally done by the EMF code, but not in the
                                // case where the theFeature is backed by a FeatureMap.
                                // Hence, we do it ourselves
                                newVals.removeAll(featureValues);
                            }
                            
                            featureValues.addAll(0, newVals);
                            
                            for (Iterator newValsItr = newVals.iterator(); newValsItr.hasNext(); ) {
                                Object newVal = newValsItr.next();
                                if (newVal instanceof DynamicObject) {
                                    ((DynamicObject) newVal).addMultiReferenceFrom(instance, theFeature);
                                }
                            }
                        } catch (ArrayStoreException e) {
                            context.error("Couldn't add values to feature (type mismatch?): " + ModelUtils.getFullyQualifiedName(theFeature) + " <- " + newVals, e);
                        }
                    } else if (vals.size() > 1) {
                        context.error("Too many values for, " + ModelUtils.getFullyQualifiedName(theFeature));
                    } else if (vals.size() == 1) {
                        Object newVal = Util.coerceType(vals.get(0), theFeature);
                        if (instance.eIsSet(theFeature)) {
                            Object curVal = instance.eGet(theFeature);
                            if (newVal instanceof WrappedVar) {
                                context.info(newVal + NOT_BOUND_MESSAGE + "  Attempting to bind and continue.");
                                unifier = new Binding();
                                unifier.add(((WrappedVar) newVal).getVar(), curVal);
                            } else if (!curVal.equals(newVal)) {
                                context.error("Conflicting value: " + newVal + " found for feature, " + ModelUtils.getFullyQualifiedName(theFeature) + ", which is already set to: " + curVal);
                            }
                        } else {
                            //System.err.println("Setting " + theFeature + " to " + newVal);
//                                System.err.println("O **** " + instance);
//                                System.err.println(instance.eClass());
//                                System.err.println("F **** " + theFeature);
//                                System.err.println(theFeature.getEType());
//                                System.err.println("V **** " + newVal);
//                                System.err.println(newVal.getClass());
                            if (newVal instanceof WrappedVar) {
                                // ruleEval.fireInfo(newVal + DELAYING_MESSAGE);
                                context.delay(newVal + NOT_BOUND_MESSAGE);
                            }
                            instance.eSet(theFeature, newVal);
                            if (newVal instanceof DynamicObject) {
                                ((DynamicObject) newVal).addReferenceFrom(instance, theFeature);
                            }
                        }
                    } else {
                        if (theFeature.getLowerBound() > 0) {
                            context.error("No value for " + ModelUtils.getFullyQualifiedName(theFeature) + " but lower bound is " + theFeature.getLowerBound());
                        }
                        context.warn("No value for " + ModelUtils.getFullyQualifiedName(theFeature));
                    }
                    
                    context.createBranch(unifier);
                }
            }
        } else {
            context.error("Non FeatureExpr LHS, " + args.get(0) + ", Not Yet Implemented");
        }
    }

    /**
     * @param context
     * @param term
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private void handleBooleanCondition(final Context context)
    throws ResolutionException, NotGroundException {
        List args = getArg();
        Collection vals = ((Expression) args.get(0)).eval(context);
        for (Iterator itr = vals.iterator(); itr.hasNext(); ) {
            Object val = itr.next();
            if (Boolean.TRUE.equals(val)) {
                // do nothing
            } else if (Boolean.FALSE.equals(val)) {
                context.error("Stopping on target-side FALSE.");
            } else if (val instanceof WrappedVar) {
                context.delay("Unbound Var, " + val + ", not allowed in Condition.");
            } else {
                context.error("Condition did not reference a boolean valued Expression.");
            }
        }

        // This is outside the loop since there's no point in creating
        // multiple branches for the same (new) goal and empty Binding.
        context.createBranch();
    }

} //ConditionImpl
