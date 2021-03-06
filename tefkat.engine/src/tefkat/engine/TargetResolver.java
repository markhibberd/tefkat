/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *     David Hearnden
 */

package tefkat.engine;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import tefkat.engine.trace.impl.TracePackageImpl;
import tefkat.model.*;
import tefkat.model.internal.ModelUtils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  TargetResolver performs SLDNF resolution on a provided goal, creating an SLDNF resolution tree.
 *  The success nodes of this tree represent different
 *  variable bindings that make the provided goal true.
 *
 *  Example:
 *      TargetResolver r = new TargetResolver();
 *      Tree t = r.resolve(goal, binding);
 *      Collection solutions = t.getAnswers();
 *
 *  @author David Hearnden, Aug 2003
 *  @author michael lawley, Aug 2003 -- modified for QVT model
 */
class TargetResolver extends AbstractResolver {

    private static final String NOT_BOUND_MESSAGE = " was not bound in source term!";
//    private static final String DELAYING_MESSAGE = " was not bound in source term!  Attempting to delay and continue.";
    static {
        // Ensure EMF runtime knows about the Trace metamodel
        TracePackageImpl.init();
    }

    /**
     *  Create a new TargetResolver for goal using rules in transformation, and solve.
     *  @param goal   The goal to solve.
     *  @param transformation The rule database to use.
     */
    TargetResolver(RuleEvaluator evaluator, ResourceSet tgtModels, List listeners) {
    	super(evaluator);
    }

    protected void doResolveNode(final Context context, final Term literal)
    throws ResolutionException, NotGroundException {
        if (literal instanceof Injection) {
            resolveInjection(context, (Injection) literal);
        } else {
            super.doResolveNode(context, literal);
        }
    }
    
    /**
     * 
     * @param values a List of Lists
     * @return
     */
    private static List buildCrossProduct(List values) {
        if (values.size() == 0) {
            List result = new ArrayList(1);
            result.add(new ArrayList());
            return result;
        }
        
        Collection vals = (Collection) values.remove(0);
        if (values.size() == 0) {
            List result = new ArrayList(vals.size());
            for (Iterator itr = vals.iterator(); itr.hasNext(); ) {
                List l = new ArrayList();
                l.add(itr.next());
                result.add(l);
            }
            return result;
        }
        
        List xp = buildCrossProduct(values);
        List result = new ArrayList();
        for (Iterator itr = xp.iterator(); itr.hasNext(); ) {
            Collection l = (Collection) itr.next();
            for (Iterator itr2 = vals.iterator(); itr2.hasNext(); ) {
                Object o2 = itr2.next();
                List l2 = new ArrayList(l);
                l2.add(0, o2);
                result.add(l2);
            }
        }
        return result;
    }
    
    protected boolean resolveInjection(
            final Context context,
            final Injection literal)
    throws ResolutionException, NotGroundException {
        List keySet = new ArrayList();
        
        List sources = literal.getSources();
        for (Iterator itr = sources.iterator(); itr.hasNext(); ) {
            Expression expr = (Expression) itr.next();
//            System.out.println("Eval: " + expr);
            List vals = exprEval.eval(context, expr);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) vals.get(0);
                Collection instances = exprEval.expand(context, wVar);
                List pairs = new ArrayList(instances.size());
                for (Iterator instItr = instances.iterator(); instItr.hasNext(); ) {
                    Object o = instItr.next();

                    BindingPair bp = new BindingPair(o);
                    bp.add(wVar.getVar(), o);
                    pairs.add(bp);
                }
                keySet.add(pairs);
//                ruleEval.fireInfo(expr + DELAYING_MESSAGE);
//                context.delay(expr + NOT_BOUND_MESSAGE);
            } else {
                keySet.add(vals);
            }
        }

        // do expr eval before we create things in case we need to delay
        VarUse targetVarUse = literal.getTarget();
        Object targetVal = exprEval.eval(context, targetVarUse).get(0);    // Can only be one thing
        
        keySet = buildCrossProduct(keySet);
        for (Iterator itr = keySet.iterator(); itr.hasNext(); ) {
            List keys = (List) itr.next();
            keys.add(0, literal.getName());
        
            EObject targetObject = context.lookup(keys, literal.getTRuleTgt());
        
            Binding unifier = new Binding();
            for (Iterator keyItr = keys.iterator(); keyItr.hasNext(); ) {
                Object k = keyItr.next();
                if (k instanceof BindingPair) {
                    unifier.composeRight((BindingPair) k);
                }
            }

            if (targetVal instanceof WrappedVar) {
                unifier.add(((WrappedVar) targetVal).getVar(), targetObject);
            } else if (!targetVal.equals(targetObject)) {
                context.error("Incompatible values for variable: " + targetVarUse.getVar());
            }
        
            context.createBranch(unifier);
        }
        
        return true;
    }
    
    int counter = 0;
    int[] elapsed = {0, 0, 0, 0};
    
    /**
     * Looks up a tracking class instance based on the supplied type and feature values,
     * creating a new instance if nothing suitable is found.
     * <p>
     * The basic method is as follows:
     * <ol>
     * <li> For each feature of the TrackingUse, evaluate its Expression and store the set of results
     * <li> Find all instances of the tracking class
     * <li> For each KeyPart of each Key of each instance:
     * <ol>
     * <li> get the value of the keyPart
     * <li> compare with the values of the TrackingUse
     * <li> if different, try next Key
     * </ol>
     * <li> If all KeyParts match, then instance is a match
     * </ol>
     * 
     * @param tree
     * @param node
     * @param goal
     * @param literal
     */
    protected void resolveTrackingUse(
            final Context context,
            final TrackingUse literal)
    throws ResolutionException, NotGroundException {

        long t1 = System.currentTimeMillis();
        
        // Get the properties of the TrackingUse
        
        EClass trackingClass = literal.getTracking();
        if (trackingClass.eIsProxy()) {
            // If it's still a proxy after the getTracking() call, the cross-document reference proxy has
            // not been resolved, meaning the reference was dodgy, i.e. to a non-existent class or something
            //
            context.error("Unable to locate tracking class: " + trackingClass);
        }

        Map featureVal = new HashMap();
        List featureList = literal.getFeatures();
        
        for (Iterator itr = featureList.iterator(); itr.hasNext(); ) {
            Map.Entry keyEntry = (Map.Entry) itr.next();
            String name = (String) keyEntry.getKey();
            Expression expr = (Expression) keyEntry.getValue();
            List vals = exprEval.eval(context, expr);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
//                 ruleEval.fireInfo(expr + " delayed in LINKING");
                context.delay(expr + NOT_BOUND_MESSAGE);
            }
            EStructuralFeature feature = getFeature(context, trackingClass, name);
            featureVal.put(feature, coerceTypes(vals, feature));
//            System.err.println("**** " + keyEntry.getKey() + " = " + vals);
        }
        
        long t2 = System.currentTimeMillis();

        Extent trackingExtent = context.tree.getTrackingExtent();

        List trackings = trackingExtent.getObjectsByClass(trackingClass, false);
        ExtentUtil.highlightNodes(trackings, ExtentUtil.CLASS_LOOKUP);
        
        long t3 = System.currentTimeMillis();
        
        boolean isMatch = false;
        for (final Iterator itr = trackings.iterator(); !isMatch && itr.hasNext(); ) {
            EObject inst = (EObject) itr.next();
            isMatch = true;
            for (final Iterator fvItr = featureVal.entrySet().iterator(); isMatch && fvItr.hasNext(); ) {
                Map.Entry entry = (Map.Entry) fvItr.next();
                EStructuralFeature feature = (EStructuralFeature) entry.getKey();
                List featureValues = (List) entry.getValue();
                Object value = inst.eGet(feature);
                if (feature.isMany()) {
                    if (!featureValues.equals(value)) {
                        isMatch = false;
                    }
                } else {
                    if (featureValues.size() != 1 || !featureValues.get(0).equals(value)) {
                        isMatch = false;
                    }
                }
            }
        }
        
        long t4 = System.currentTimeMillis();

        if (!isMatch) {
            createTrackingInstance(context, trackingClass, trackingExtent, featureVal);
            counter++;
        }

        elapsed[0] += t2-t1;
        elapsed[1] += t3-t2;
        elapsed[2] += t4-t3;
        
        context.createBranch();
    }

    private void createTrackingInstance(Context context, EClass trackingClass, Extent trackingExtent, Map featureVal) throws ResolutionException, NotGroundException {
        EObject trackingInstance;
        // instantiate the class using the factory

        // find the package
//        System.out.println("==== Creating a tracking: " + trackingClass.getName());
        // TODO fixme - should probably use DynamicObjects for tracking instances
        EPackage trackingPackage = trackingClass.getEPackage();
        if (null == trackingPackage) {
            context.error("Malformed class: " + ModelUtils.getFullyQualifiedName(trackingClass) + " not contained in a package.");
        }
        EFactory trackingFactory = trackingPackage.getEFactoryInstance();
        trackingInstance = trackingFactory.create(trackingClass);
        trackingExtent.add(trackingInstance);
            
        // fill in the values of the fields
        for (Iterator featureValIter = featureVal.entrySet().iterator(); featureValIter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) featureValIter.next();
            
            EStructuralFeature feature = (EStructuralFeature) entry.getKey();
            List featureValues = (List) entry.getValue();
            
            if (feature.isMany()) {
//                System.out.println("==== Adding tracking features: " + featureName + " = " + featureValues);
                List vals = (List) trackingInstance.eGet(feature);
//                System.out.println("==== " + vals);
                vals.addAll(featureValues);
//                System.out.println("==== " + trackingInstance.eGet(feature));
                for (Iterator itr = featureValues.iterator(); itr.hasNext(); ) {
                    Object val = itr.next();
                    if (val instanceof DynamicObject) {
                        DynamicObject dynVal = (DynamicObject) val;
                        dynVal.addMultiReferenceFrom(trackingInstance, feature);
                    }
                }
            } else {
                // If the feature isn't multi-valued, then there better be only
                // one possible value for it.
                if (featureValues.size() > 1) {
                    context.error("Too many values (" + featureValues + ") for feature " + trackingClass + "." + feature.getName());
                }
                Object val = featureValues.get(0);
                // System.out.println("  Setting tracking feature: " + feature + " = " + val);
                trackingInstance.eSet(feature, val);
                if (val instanceof DynamicObject) {
                    DynamicObject dynVal = (DynamicObject) val;
                    dynVal.addReferenceFrom(trackingInstance, feature);
                }
            }
        }
        
        long start = System.currentTimeMillis();
        
        // tracking instance created - now tell others about it
        ruleEval.trackingCreate(trackingClass, trackingInstance);
        
        long end = System.currentTimeMillis();

        EClass idc = TefkatPackage.eINSTANCE.getTrackingUse();
        int total = elapsedTime.get(idc);
        total -= (end - start);
        elapsedTime.put(idc, total);
    }

    protected boolean resolveMofInstance(
            final Context context,
            final MofInstance literal)
    throws ResolutionException, NotGroundException {

        Expression instanceExpr = literal.getInstance();
        Collection instances = exprEval.eval(context, instanceExpr);

        // deal with type
        List results = exprEval.eval(context, literal.getTypeName());
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
                boolean makeExact = literal.isExact();
                
                if (typeObj instanceof WrappedVar) {
                    unifier.add(((WrappedVar) typeObj).getVar(), eObj.eClass().getName());
                } else if (typeObj instanceof String) {
		    // _ is the universal type -> it always matches
		    if ("_".equals(typeObj)) {
                        // It's not possible to make an instance exactly no type
                        // (although we _might_ consider the possibilty that one
                        // would like the possiblity to freeze the current type)
                        makeExact = false;
                    } else {
			Map nameMap = getNameMap();
			String typeName = (String) typeObj;
			EClassifier eClassifier = ModelUtils.findClassifierByName(nameMap, typeName);
			if (null == eClassifier) {
//			    for (final Iterator nItr = nameMap.keySet().iterator(); nItr.hasNext(); ) {
//			        Object entry =  nItr.next();
//			        System.out.println(entry);
//			    }
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
                
                if (makeExact && eObj instanceof DynamicObject) {
                    if (eObj.eResource() != null) {
                        eObj.eResource().getContents().remove(eObj);
//                      System.err.println("  ...removed: " + eObj.hashCode());
                    }
                    eObj = ((DynamicObject) eObj).getStaticInstance();
                }
                
                // deal with extent
                Extent extentObj = (Extent) context.lookup(literal.getExtent());
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
        
        return true;
    }

    /**
     * @param eObj
     * @param subCls
     * @return
     */
    private boolean conformToType(EObject eObj, EClass subCls) {
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

    protected void resolveCondition(final Context context, final Condition literal)
    throws ResolutionException, NotGroundException {

        Condition term = (Condition) literal;

        String relation = term.getRelation();

        if (relation.equals("=")) {
            handleBindingCondition(context, term);
        } else if (relation.equals("boolean")) {
            handleBooleanCondition(context, term);
        } else {
            context.error("Target condition containing "
                    + relation + " is Not Yet Implemented");
        }
    }

    /**
     * @param context
     * @param term
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private void handleBooleanCondition(final Context context, final Condition term)
    throws ResolutionException, NotGroundException {
        List args = term.getArg();
        Collection vals = exprEval.eval(context, (Expression) args.get(0));
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

    /**
     * @param context
     * @param term
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private void handleBindingCondition(final Context context, final Condition term)
    throws ResolutionException, NotGroundException {
        List args = term.getArg();

        // The following possibilities exist:
        //   LHS & RHS unbound
        //   LHS unbound & RHS bound
        //   LHS bound & RHS unbound
        //   LHS & RHS bound
        
        // For now, implement the following:
        //   <expr>.y = <valExpr>;
        Expression valExpr = (Expression) args.get(1);
        List vals = exprEval.eval(context, valExpr);
        
        if (args.get(0) instanceof FeatureExpr) {
            Binding unifier = null;

            FeatureExpr featExpr = (FeatureExpr) args.get(0);
            Collection featureNames = exprEval.eval(context, featExpr.getFeature());
            Collection objs = exprEval.eval(context, (Expression) featExpr.getArg().get(0));
            
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
			theFeature = getFeature(context, instance.eClass(), featureName);
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
                        List newVals = coerceTypes(vals, theFeature);
                        try {
                            // Insert at beginning so that we have a chance of preserving
                            // the order from the source model (the Node-tree traversal 
                            // would otherwise naturally invert the order).

                            if (featExpr.isCollect()) {
                                for (final Iterator nvItr = newVals.iterator(); nvItr.hasNext(); ) {
                                    final List valList = (List) nvItr.next();

                                    if (theFeature.isUnique()) {
                                        // This is normally done by the EMF code, but not in the
                                        // case where the theFeature is backed by a FeatureMap.
                                        // Hence, we do it ourselves
                                        valList.removeAll(featureValues);
                                    }
                                    featureValues.addAll(0, valList);
                                    recordMultiReference(instance, theFeature, valList);
                                }
                            } else {
                                if (theFeature.isUnique()) {
                                    // This is normally done by the EMF code, but not in the
                                    // case where the theFeature is backed by a FeatureMap.
                                    // Hence, we do it ourselves
                                    newVals.removeAll(featureValues);
                                }
                                featureValues.addAll(0, newVals);
                                recordMultiReference(instance, theFeature, newVals);
                            }
                            
                        } catch (ArrayStoreException e) {
                            context.error("Couldn't add values to feature (type mismatch?): " + ModelUtils.getFullyQualifiedName(theFeature) + " <- " + newVals, e);
                        }
                    } else if (vals.size() > 1) {
                        context.error("Too many values for, " + ModelUtils.getFullyQualifiedName(theFeature));
                    } else if (vals.size() == 1) {
                        Object newVal = coerceType(vals.get(0), theFeature);
                        if (instance.eIsSet(theFeature)) {
                            Object curVal = instance.eGet(theFeature);
                            if (newVal instanceof WrappedVar) {
                                ruleEval.fireInfo(newVal + " was not bound in source term!  Attempting to bind and continue.");
                                unifier = new Binding();
                                unifier.add(((WrappedVar) newVal).getVar(), curVal);
                            } else if (!curVal.equals(newVal)) {
                                context.error("Conflicting value: " + newVal + " found for feature, " + ModelUtils.getFullyQualifiedName(theFeature) + ", which is already set to: " + curVal);
                            }
                        } else {
//                            System.err.println("Setting " + theFeature.getName() + " to " + newVal);
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
                        ruleEval.fireWarning("No value for " + ModelUtils.getFullyQualifiedName(theFeature));
                    }
                    
                    context.createBranch(unifier);
                }
            }
        } else {
            context.error("Non FeatureExpr LHS, " + args.get(0) + ", Not Yet Implemented");
        }
    }

    private void recordMultiReference(EObject instance, EStructuralFeature theFeature, List newVals) {
        for (Iterator newValsItr = newVals.iterator(); newValsItr.hasNext(); ) {
            Object newVal = newValsItr.next();
            if (newVal instanceof DynamicObject) {
                ((DynamicObject) newVal).addMultiReferenceFrom(instance, theFeature);
            }
        }
    }

    /**
     * Returns an object compatible with the type of eFeature if possible.
     * 
     * @param object
     * @param eFeature
     * @return
     */
    static private Object coerceType(Object object, EStructuralFeature eFeature) {
        Object result;
        if ("java.lang.String".equals(eFeature.getEType().getInstanceClassName())) {
            result = String.valueOf(object);
        } else if (object instanceof Number) {
            int typeID = eFeature.getEType().getClassifierID();
            if (typeID == EcorePackage.EINTEGER_OBJECT|| typeID == EcorePackage.EINT) {
                result = new Integer(((Number) object).intValue());
            } else if (typeID == EcorePackage.ELONG_OBJECT || typeID == EcorePackage.ELONG) {
                result = new Long(((Number) object).longValue());
            } else if (typeID == EcorePackage.ESHORT_OBJECT || typeID == EcorePackage.ESHORT) {
                result = new Short(((Number) object).shortValue());
            } else if (typeID == EcorePackage.EFLOAT_OBJECT || typeID == EcorePackage.EFLOAT) {
                result = new Float(((Number) object).floatValue());
            } else if (typeID == EcorePackage.EDOUBLE_OBJECT || typeID == EcorePackage.EDOUBLE) {
                result = new Double(((Number) object).doubleValue());
            } else if (typeID == EcorePackage.EBYTE_OBJECT || typeID == EcorePackage.EBYTE) {
                result = new Byte(((Number) object).byteValue());
            } else if (typeID == EcorePackage.EBIG_INTEGER) {
                result = new BigInteger(String.valueOf(object));
            } else if (typeID == EcorePackage.EBIG_DECIMAL) {
                result = new BigDecimal(String.valueOf(object));
            } else {
                result = object;
            }
        } else {
            result = object;
        }
        return result;
    }
    
    static private List coerceTypes(List l, EStructuralFeature eFeature) {
        List cl = new ArrayList(l.size());
        for (int i = 0; i < l.size(); i++) {
            cl.add(i, coerceType(l.get(i), eFeature));
        }
        return cl;
    }

    /**
     * @throws ResolutionException NotTerms on the target side are not supported
     */
    protected void resolveNotTerm(
            final Context context,
            final NotTerm literal)
    throws ResolutionException {
        context.error("NotTerm not supported in target term");
    }
    
    /**
     * @throws ResolutionException OrTerms on the target side are not supported (since they lead to nondeterminism)
     */
    protected void resolveOrTerm(
            final Context context,
            final OrTerm literal)
    throws ResolutionException {
        context.error("OrTerm not supported in target term");
    }

    protected void resolveMofOrder(final Context context, final MofOrder term)
    throws ResolutionException, NotGroundException {
        List featVals = exprEval.eval(context, term.getFeature());
        List instVals = exprEval.eval(context, term.getInstance());
        List lesserVals = exprEval.eval(context, term.getLesser());
        List greaterVals = exprEval.eval(context, term.getGreater());
        
        for (final Iterator fItr = featVals.iterator(); fItr.hasNext(); ) {
            Object feat = fItr.next();
            Binding fUnifier = null;
            if (feat instanceof WrappedVar) {
                Var var = ((WrappedVar) feat).getVar();
                context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
            } else if (feat instanceof BindingPair) {
                fUnifier = (BindingPair) feat;
                feat = ((BindingPair) feat).getValue();
            }
            for (final Iterator iItr = instVals.iterator(); iItr.hasNext(); ) {
                Object inst = iItr.next();
                Binding iUnifier = fUnifier;
                if (inst instanceof WrappedVar) {
                    Var var = ((WrappedVar) inst).getVar();
                    context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
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
                        context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
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
                            context.delay("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
                        } else if (greater instanceof BindingPair) {
                            if (null == gUnifier) {
                                gUnifier = (BindingPair) greater;
                            } else {
                                gUnifier = new Binding(lUnifier);
                                gUnifier.composeRight((BindingPair) greater);
                            }
                            greater = ((BindingPair) greater).getValue();
                        }

                        ruleEval.addPartialOrder(inst, feat, lesser, greater);

                        context.createBranch(gUnifier);
                    }
                }
            }
        }
    }
    
}
