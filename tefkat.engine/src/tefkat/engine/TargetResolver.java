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
 *
 *
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

import tefkat.engine.trace.IntAny;
import tefkat.engine.trace.ObjectAny;
import tefkat.engine.trace.StringAny;
import tefkat.engine.trace.Trace;
import tefkat.engine.trace.TraceFactory;
import tefkat.engine.trace.TracePackage;
import tefkat.engine.trace.impl.TracePackageImpl;
import tefkat.model.*;
import tefkat.model.internal.Util;


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
 *      Collection solutions = r.solutions(t, vars);
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
    
    private final Injections injections = new Injections();

    /**
     *  Create a new TargetResolver for goal using rules in transformation, and solve.
     *  @param goal   The goal to solve.
     *  @param transformation The rule database to use.
     */
    TargetResolver(RuleEvaluator evaluator, ResourceSet tgtModels, List listeners) {
    	super(evaluator);
    }

    protected void doResolveNode(Tree tree, Node node, Collection goal,
            Term literal, boolean isNegation) throws ResolutionException, NotGroundException {
        if (literal instanceof Injection) {
            resolveInjection(tree, node, goal, (Injection) literal);
        } else {
            super.doResolveNode(tree, node, goal, literal, isNegation);
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
            Tree tree,
            Node node,
            Collection goal,
            Injection literal) throws ResolutionException, NotGroundException {
        List keySet = new ArrayList();
        
        List sources = literal.getSources();
        for (Iterator itr = sources.iterator(); itr.hasNext(); ) {
            Expression expr = (Expression) itr.next();
//            System.out.println("Eval: " + expr);
            List vals = exprEval.eval(node, expr);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) vals.get(0);
                Collection instances = exprEval.expand(wVar, expr);
                List pairs = new ArrayList(instances.size());
                for (Iterator instItr = instances.iterator(); instItr.hasNext(); ) {
                    Object o = instItr.next();
                    BindingPair bp = new BindingPair(o);
                    bp.add(wVar.getVar(), o);
                    pairs.add(bp);
                }
                keySet.add(pairs);
//                ruleEval.fireInfo(expr + DELAYING_MESSAGE);
//                throw new NotGroundException(expr + NOT_BOUND_MESSAGE);
            } else {
                keySet.add(vals);
            }
        }

        // do expr eval before we create things in case we need to delay
        VarUse targetVarUse = literal.getTarget();
        Object targetVal = exprEval.eval(node, targetVarUse).get(0);    // Can only be one thing
        
        keySet = buildCrossProduct(keySet);
        for (Iterator itr = keySet.iterator(); itr.hasNext(); ) {
            List keys = (List) itr.next();
            keys.add(0, literal.getName());
        
            EObject targetObject = injections.lookup(tree.getTrackingExtent(), keys, literal.getTRuleTgt());
        
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
                throw new ResolutionException(node, "Incompatible values for variable: " + targetVarUse.getVar());
            }
        
            Collection newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            tree.createBranch(node, unifier, newGoal);
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
        Tree tree,
        Node node,
        Collection goal,
        Term literal)
        throws ResolutionException, NotGroundException {

        long t1 = System.currentTimeMillis();
        
        // Get the properties of the TrackingUse
        TrackingUse term = (TrackingUse) literal;
        
        EClass trackingClass = term.getTracking();
        if (trackingClass.eIsProxy()) {
            // If it's still a proxy after the getTracking() call, the cross-document reference proxy has
            // not been resolved, meaning the reference was dodgy, i.e. to a non-existent class or something
            //
            throw new ResolutionException(node, "Unable to locate tracking class: " + trackingClass);
        }

        Map featureVal = new HashMap();
        List featureList = term.getFeatures();
        
        for (Iterator itr = featureList.iterator(); itr.hasNext(); ) {
            Map.Entry keyEntry = (Map.Entry) itr.next();
            String name = (String) keyEntry.getKey();
            Expression expr = (Expression) keyEntry.getValue();
            List vals = exprEval.eval(node, expr);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                // ruleEval.fireInfo(expr + DELAYING_MESSAGE);
                throw new NotGroundException(expr + NOT_BOUND_MESSAGE);
            }
            EStructuralFeature feature = getFeature(node, trackingClass, name);
            featureVal.put(feature, coerceTypes(vals, feature));
//            System.err.println("**** " + keyEntry.getKey() + " = " + vals);
        }
        
        long t2 = System.currentTimeMillis();

        Extent trackingExtent = tree.getTrackingExtent();

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
            createTrackingInstance(node, trackingClass, trackingExtent, featureVal);
            counter++;
        }

        elapsed[0] += t2-t1;
        elapsed[1] += t3-t2;
        elapsed[2] += t4-t3;
        
        Collection newGoal = new ArrayList(goal);
        newGoal.remove(literal);
        tree.createBranch(node, null, newGoal);
    }

    private void createTrackingInstance(Node node, EClass trackingClass, Extent trackingExtent, Map featureVal) throws ResolutionException, NotGroundException {
        EObject trackingInstance;
        // instantiate the class using the factory

        // find the package
//        System.out.println("==== Creating a tracking: " + trackingClass.getName());
        // TODO fixme - should probably use DynamicObjects for tracking instances
        EPackage trackingPackage = trackingClass.getEPackage();
        if (null == trackingPackage) {
            throw new ResolutionException(node, "Malformed class: " + Util.getFullyQualifiedName(trackingClass) + " not contained in a package.");
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
                    throw new ResolutionException(node, "Too many values (" + featureValues + ") for feature " + trackingClass + "." + feature.getName());
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
        Tree tree,
        Node node,
        Collection goal,
        Term literal)
        throws ResolutionException, NotGroundException {

        MofInstance term = (MofInstance) literal;
        Expression instanceExpr = term.getInstance();
        Collection instances = exprEval.eval(node, instanceExpr);

        // deal with type
        List results = exprEval.eval(node, term.getTypeName());
        if (results.size() != 1) {
            throw new ResolutionException(node, "Expected only a single type name, got: " + results);
        }
        Object typeObj = results.get(0);
        
        Binding unifier = new Binding();

        for (Iterator itr = instances.iterator(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            // System.err.println(obj);   // TODO delete
            if (obj instanceof WrappedVar) {
                // ruleEval.fireInfo(obj + DELAYING_MESSAGE);
                throw new NotGroundException(obj + NOT_BOUND_MESSAGE);
            } else {
                EObject eObj = (EObject) obj;
                
                if (typeObj instanceof WrappedVar) {
                    unifier.add(((WrappedVar) typeObj).getVar(), eObj.eClass().getName());
                } else if (typeObj instanceof String) {
		    // _ is the universal type -> it always matches
		    if (!"_".equals(typeObj)) {
			Map nameMap = getNameMap();
			String typeName = (String) typeObj;
			EClassifier eClassifier = Util.findClassifierByName(nameMap, typeName);
                        if (null == eClassifier) {
                            for (final Iterator nItr = nameMap.keySet().iterator(); nItr.hasNext(); ) {
                                Object entry =  nItr.next();
                                System.out.println(entry);
                            }
                            throw new ResolutionException(node, "Expected an EClass called: " + typeName + ", but found nothing");
			} else if (!(eClassifier instanceof EClass)) {
			    throw new ResolutionException(node, "Expected an EClass called: " + typeName + ", but found an EDataType or EEnum");
			}
			EClass subCls = (EClass) eClassifier;
                        
			if (null == subCls) {
			    throw new ResolutionException(node, "Cannot find class named: " + typeName);
			}

			boolean result = conformToType(eObj, subCls);
			if (!result) {
			    throw new ResolutionException(node, "Type mismatch, " + typeName + " not compatible with " + eObj.eClass());
			}
		    }
                } else if (typeObj instanceof EClass) {
                    EClass subCls = (EClass) typeObj;
                    boolean result = conformToType(eObj, subCls);
                    if (!result) {
                        throw new ResolutionException(node, "Type mismatch, " + subCls + " not compatible with " + eObj.eClass());
                    }
                } else {
                    throw new ResolutionException(node, "Invalid Expression type for MofInstance.typeName: " + typeObj);
                }
                
                if (term.isExact() && eObj instanceof DynamicObject) {
                    if (eObj.eResource() != null) {
                        eObj.eResource().getContents().remove(eObj);
//                      System.err.println("  ...removed: " + eObj.hashCode());
                    }
                    eObj = ((DynamicObject) eObj).getStaticInstance();
                }
                
                // deal with extent
                Extent extentObj = (Extent) node.lookup(term.getExtent());
                if (null != extentObj) {
                    // Extents are optional, but dangling objects may be created that
                    // should cause errors when the Resource is saved, but only if there
                    // is a reference to the object, but no containment reference.
                    Resource res = eObj.eResource();
                    if (null == res) {
                        extentObj.add(eObj);
                    } else if (!extentObj.contains(eObj)) {
                        throw new ResolutionException(node, "Object (" + eObj + ") cannot exist in multiple resources, " + res + " and " + extentObj);
                    }
                }
            }
        }
        
        Collection newGoal = new ArrayList(goal);
        newGoal.remove(term);
        tree.createBranch(node, unifier, newGoal);

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

    protected void resolveCondition(Tree tree, Node node, Collection goal,
            Condition literal) throws ResolutionException, NotGroundException {

        Condition term = (Condition) literal;

        String relation = term.getRelation();

        if (relation.equals("=")) {
            handleBindingCondition(tree, node, goal, term);
        } else if (relation.equals("boolean")) {
            handleBooleanCondition(tree, node, goal, term);
        } else {
            throw new ResolutionException(node, "Target condition containing "
                    + relation + " is Not Yet Implemented");
        }
    }

    /**
     * @param tree
     * @param node
     * @param goal
     * @param term
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private void handleBooleanCondition(Tree tree, Node node, Collection goal, Condition term) throws ResolutionException, NotGroundException {
        List args = term.getArg();
        Collection vals = exprEval.eval(node, (Expression) args.get(0));
        for (Iterator itr = vals.iterator(); itr.hasNext(); ) {
            Object val = itr.next();
            if (Boolean.TRUE.equals(val)) {
                // do nothing
            } else if (Boolean.FALSE.equals(val)) {
                throw new ResolutionException(node, "Stopping on target-side FALSE.");
            } else if (val instanceof WrappedVar) {
                throw new NotGroundException("Unbound Var, " + val + ", not allowed in Condition.");
            } else {
                throw new ResolutionException(node, "Condition did not reference a boolean valued Expression.");
            }
        }

        // This is outside the loop since there's no point in creating
        // multiple branches for the same (new) goal and empty Binding.
        Collection newGoal = new ArrayList(goal);
        newGoal.remove(term);
        tree.createBranch(node, null, newGoal);
    }

    /**
     * @param tree
     * @param node
     * @param goal
     * @param term
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private void handleBindingCondition(Tree tree, Node node, Collection goal, Condition term) throws ResolutionException, NotGroundException {
        List args = term.getArg();

        // The following possibilities exist:
        //   LHS & RHS unbound
        //   LHS unbound & RHS bound
        //   LHS bound & RHS unbound
        //   LHS & RHS bound
        
        // For now, implement the following:
        //   <expr>.y = <valExpr>;
        Expression valExpr = (Expression) args.get(1);
        List vals = exprEval.eval(node, valExpr);
        
        if (args.get(0) instanceof FeatureExpr) {
            Binding unifier = null;

            FeatureExpr featExpr = (FeatureExpr) args.get(0);
            Collection featureNames = exprEval.eval(node, featExpr.getFeature());
            Collection objs = exprEval.eval(node, (Expression) featExpr.getArg().get(0));
            
            for (Iterator fItr = featureNames.iterator(); fItr.hasNext(); ) {
                Object fObj = fItr.next();
                if (fObj instanceof WrappedVar) {
                    AbstractVar var = ((WrappedVar) fObj).getVar();
                    throw new NotGroundException(
                        "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featExpr.getFeature());
                } else if (fObj instanceof EStructuralFeature) {
                    // TODO FIXME this is a HACK
                    fObj = ((EStructuralFeature) fObj).getName();
                } else if (!(fObj instanceof String)) {
                    throw new ResolutionException(node, "The Feature Expression " + featExpr + " must evaluate to a feature name of type String, not " + fObj.getClass());
                }
                String featureName = (String) fObj;
                
                for (Iterator itr = objs.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                
                    if (obj instanceof WrappedVar) {
                        AbstractVar var = ((WrappedVar) obj).getVar();
                        throw new NotGroundException(
                            "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featureName);
                    } else if (!(obj instanceof EObject)) {
                        throw new ResolutionException(
                            node,
                            "Target object is not an EObject (i.e., not a valid model instance)" + featExpr.getArg().get(0));
                    }
                
                    EObject instance = (EObject) obj;

                    EStructuralFeature eFeature = getFeature(node, instance.eClass(), featureName);
                    
                    if (eFeature.isMany()) {
                        // Adding multiple feature values: featureName = vals
                        if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                            Object newVal = vals.get(0);
                            // ruleEval.fireInfo(newVal + DELAYING_MESSAGE);
                            throw new NotGroundException(newVal + NOT_BOUND_MESSAGE);
                        }
                        // values are always added for multi-valued features
                        List featureValues = (List) instance.eGet(eFeature);
                        List newVals = coerceTypes(vals, eFeature);
                        try {
                            // Insert at beginning so that we have a chance of preserving
                            // the order from the source model (the Node-tree traversal 
                            // would otherwise naturally invert the order).
                            
                            if (eFeature.isUnique()) {
                                // This is normally done by the EMF code, but not in the
                                // case where the eFeature is backed by a FeatureMap.
                                // Hence, we do it ourselves
                                newVals.removeAll(featureValues);
                            }
                            
                            featureValues.addAll(0, newVals);
                            
                            for (Iterator newValsItr = newVals.iterator(); newValsItr.hasNext(); ) {
                                Object newVal = newValsItr.next();
                                if (newVal instanceof DynamicObject) {
                                    ((DynamicObject) newVal).addMultiReferenceFrom(instance, eFeature);
                                }
                            }
                        } catch (ArrayStoreException e) {
                            throw new ResolutionException(
                                node,
                                "Couldn't add values to feature (type mismatch?): " + Util.getFullyQualifiedName(eFeature) + " <- " + newVals, e);
                        }
                    } else if (vals.size() > 1) {
                        throw new ResolutionException(node, "Too many values for, " + Util.getFullyQualifiedName(eFeature));
                    } else if (vals.size() == 1) {
                        Object newVal = coerceType(vals.get(0), eFeature);
                        if (instance.eIsSet(eFeature)) {
                            Object curVal = instance.eGet(eFeature);
                            if (newVal instanceof WrappedVar) {
                                ruleEval.fireInfo(newVal + " was not bound in source term!  Attempting to bind and continue.");
                                unifier = new Binding();
                                unifier.add(((WrappedVar) newVal).getVar(), curVal);
                            } else if (!curVal.equals(newVal)) {
                                throw new ResolutionException(
                                    node,
                                    "Conflicting value: " + newVal + " found for feature, " + Util.getFullyQualifiedName(eFeature) + ", which is already set to: " + curVal);
                            }
                        } else {
                            //System.err.println("Setting " + eFeature + " to " + newVal);
//                                System.err.println("O **** " + instance);
//                                System.err.println(instance.eClass());
//                                System.err.println("F **** " + eFeature);
//                                System.err.println(eFeature.getEType());
//                                System.err.println("V **** " + newVal);
//                                System.err.println(newVal.getClass());
                            if (newVal instanceof WrappedVar) {
                                // ruleEval.fireInfo(newVal + DELAYING_MESSAGE);
                                throw new NotGroundException(newVal + NOT_BOUND_MESSAGE);
                            }
                            instance.eSet(eFeature, newVal);
                            if (newVal instanceof DynamicObject) {
                                ((DynamicObject) newVal).addReferenceFrom(instance, eFeature);
                            }
                        }
                    } else {
                        if (eFeature.getLowerBound() > 0) {
                            throw new ResolutionException(
                                node,
                                "No value for " + Util.getFullyQualifiedName(eFeature) + " but lower bound is " + eFeature.getLowerBound());
                        }
                        ruleEval.fireWarning("No value for " + Util.getFullyQualifiedName(eFeature));
                    }
                    
                    Collection newGoal = new ArrayList(goal);
                    newGoal.remove(term);
                    tree.createBranch(node, unifier, newGoal);
                }
            }
        } else {
            throw new ResolutionException(
                node,
                "Non FeatureExpr LHS, " + args.get(0) + ", Not Yet Implemented");
        }
    }

    /**
     * Returns an object compatible with the type of eFeature if possible.
     * 
     * @param object
     * @param eFeature
     * @return
     */
    private Object coerceType(Object object, EStructuralFeature eFeature) {
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
    
    private List coerceTypes(List l, EStructuralFeature eFeature) {
        List cl = new ArrayList(l.size());
        for (int i = 0; i < l.size(); i++) {
            cl.add(i, coerceType(l.get(i), eFeature));
        }
        return cl;
    }

    protected void resolveNotTerm(
        Tree tree,
        Node node,
        Collection goal,
        Term literal)
        throws ResolutionException {
            throw new ResolutionException(node, "NotTerm is Not Yet Implemented");
        //      /**
        //       *  Create a new subtree attached to this node, and mark
        //       *  that tree as a negative tree.
        //       */
        //      List terms = ((NotTerm) literal).getTerm();
        //      
        //      if (null == terms || terms.size() != 1) {
        //          throw new ResolutionException(node, "Malformed NotTerm - must contain exactly one term.");
        //      }
        //      Term newTerm = (Term) terms.get(0);
        //      Collection newGoal;
        //      if (newTerm instanceof AndTerm) {
        //          newGoal = ((AndTerm) newTerm).getTerm();
        //      } else {
        //          newGoal = new ArrayList(1);
        //          newGoal.add(newTerm);
        //      }
        //
        //      Tree newTree = new Tree(newGoal, node.getAllBindings());
        //      node.setNegationTree(newTree);
        //
        //      /**
        //       *  Any success nodes in the negation tree?
        //       */
        //      boolean success = resolveNode(newTree, newTree.root(), true);
        //      if (newTree.isSuccess()) {
        //          tree.failure(node);
        //          return false;
        //      } else {
        //          /**
        //           *  Negation tree finitely failed, regard as true.
        //           */
        //          newGoal = new ArrayList(goal);
        //          newGoal.remove(literal);
        //          createBranch(node, null, newGoal);
        //      }
//        return true;
    }

    protected void resolveOrTerm(
        Tree tree,
        Node node,
        Collection goal,
        Term literal)
        throws ResolutionException {
        /**
         * Throw a ResolutionException - OrTerms on the target side give us nasty nondeterminism
         */
        throw new ResolutionException(node, "OrTerm not supported in target term");
        //      /**
        //       *  Create a node for each disjunct, distributing them into
        //       *  the remaining conjuncts of the goal.
        //       */
        //      Collection terms = ((OrTerm) literal).getTerm();
        //      if (null == terms || terms.isEmpty()) {
        //          throw new ResolutionException(node, "Malformed (empty) OrTerm");
        //      }
        //
        //      Iterator itr = terms.iterator();
        //      Collection newGoal;
        //
        //      while (itr.hasNext()) {
        //          newGoal = new ArrayList(goal);
        //          newGoal.remove(literal);
        //          newGoal.add(itr.next());
        //          createBranch(node, null, newGoal);
        //      }
    }

    protected void resolveMofOrder(Tree tree, Node node, Collection goal, MofOrder term) throws ResolutionException, NotGroundException {
        List featVals = exprEval.eval(node, term.getFeature());
        List instVals = exprEval.eval(node, term.getInstance());
        List lesserVals = exprEval.eval(node, term.getLesser());
        List greaterVals = exprEval.eval(node, term.getGreater());
        
        for (final Iterator fItr = featVals.iterator(); fItr.hasNext(); ) {
            Object feat = fItr.next();
            Binding fUnifier = null;
            if (feat instanceof WrappedVar) {
                AbstractVar var = ((WrappedVar) feat).getVar();
                throw new NotGroundException("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
            } else if (feat instanceof BindingPair) {
                fUnifier = (BindingPair) feat;
                feat = ((BindingPair) feat).getValue();
            }
            for (final Iterator iItr = instVals.iterator(); iItr.hasNext(); ) {
                Object inst = iItr.next();
                Binding iUnifier = fUnifier;
                if (inst instanceof WrappedVar) {
                    AbstractVar var = ((WrappedVar) inst).getVar();
                    throw new NotGroundException("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
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
                        AbstractVar var = ((WrappedVar) lesser).getVar();
                        throw new NotGroundException("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
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
                            AbstractVar var = ((WrappedVar) greater).getVar();
                            throw new NotGroundException("Unsupported mode (unbound '" + var.getName() + "') for MofOrder: " + term);
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

                        Collection newGoal = new ArrayList(goal);
                        newGoal.remove(term);
                        tree.createBranch(node, gUnifier, newGoal);
                    }
                }
            }
        }
    }

    /**
     *  Choose a literal from the goal of the given node.
     *  @param node  The node containing the goal from which to choose a 
     *               literal.
     *  @return A chosen literal, or null if the node's goal is empty 
     *          (i.e. success)
     */
    protected  Term selectLiteral(Node node) {
        Term t = doSelectLiteral(node);
        return t;
    }

    protected  Term doSelectLiteral(Node node) {
        Term[] literals = (Term[]) node.goal().toArray(new Term[0]);
        
        /**
         * Expand:
         *   Injections, then
         *   bound MofInstances, then
         *   TrackingUses, then
         *   the rest
         */

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof Injection) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }
        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof MofInstance) {
                MofInstance term = (MofInstance) literals[i];
                Expression theVar = term.getInstance();
                /**
                 * Check that the Var is bound
                 */
                if (theVar instanceof VarUse && null != node.lookup(((VarUse) theVar).getVar())) {
                    node.selectLiteral(literals[i]);
                    return literals[i];
                }
            }
        }
        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof TrackingUse) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        /**
         *  Otherwise delegate to generic selection rule.
         */
        return super.selectLiteral(node);
    }

    
    static class Injections {
        
        private Map injections = new HashMap();
        private Map traces = new HashMap();
        
        EObject lookup(Extent extent, List keys, TRule rule) {
            EObject obj = lookup(injections, keys, 0);
            Trace trace;
            if (null == obj) {
                obj = new DynamicObject();
                store(extent, keys, obj);
                trace = createTrace(extent, keys, obj);
                traces.put(obj, trace);
            } else {
            	trace = (Trace) traces.get(obj);
            }
            if (rule != null) {
                trace.getRules().add(rule);
            }
            ExtentUtil.highlightNode(obj, ExtentUtil.OBJECT_LOOKUP);
            ExtentUtil.highlightNode(trace, ExtentUtil.OBJECT_LOOKUP);
            
            return obj;
        }
        
        /**
         * @param extent
         * @param keys
         * @param obj
         * @throws Error
         */
        private Trace createTrace(Extent extent, List keys, EObject obj) throws Error {
            Trace trace = TraceFactory.eINSTANCE.createTrace();
            trace.setTarget(obj);
            if (obj instanceof DynamicObject) {
                DynamicObject dynObj = (DynamicObject) obj;
                dynObj.addReferenceFrom(trace, TracePackage.eINSTANCE.getTrace_Target());
            }
            
            extent.add(trace);

            List sources = trace.getSources();
            for (int i = 0; i < keys.size(); i++) {
                Object key = keys.get(i);
                if (key instanceof BindingPair) {
                    key = ((BindingPair) key).getValue();
                }
                
                if (key instanceof EObject) {
                    ObjectAny any = TraceFactory.eINSTANCE.createObjectAny();
                    any.getRef().add(key);
                    sources.add(any);
                } else if (key instanceof String) {
                    StringAny any = TraceFactory.eINSTANCE.createStringAny();
                    any.setString((String) key);
                    sources.add(any);
                } else if (key instanceof Integer) {
                    IntAny any = TraceFactory.eINSTANCE.createIntAny();
                    any.setInt(((Integer) key).intValue());
                    sources.add(any);
                } else {
                    throw new Error("Internal Error: trace support for " + key.getClass() + " not yet implemented.");
                }
            }
            return trace;
        }

        private void store(Extent extent, List keys, EObject value) {
            store(extent, injections, keys, value, 0);
        }
        
        private EObject lookup(Map map, List keys, int idx) {
            Object key = keys.get(idx);
            Object keyVal = map.get(key);
            
            if (null == keyVal) {
                return null;
            } else if ((idx + 1)  < keys.size()) {
                return lookup((Map) keyVal, keys, idx + 1);
            } else {
                return (EObject) keyVal;
            }
        }
        
        private void store(Extent extent, Map map, List keys, EObject value, int idx) {
            Object key = keys.get(idx);
            
            if ((idx + 1) < keys.size()) {
                Map subMap = (Map) map.get(key);
                if (null == subMap) {
                    subMap = new HashMap();
                    map.put(key, subMap);
                }
                store(extent, subMap, keys, value, idx + 1);
            } else {
                map.put(key, value);
            }
        }
    }
    
}
