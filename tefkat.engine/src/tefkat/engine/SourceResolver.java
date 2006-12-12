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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import tefkat.model.*;
import tefkat.model.internal.ModelUtils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *  SourceResolver performs SLDNF resolution on a provided goal, creating an SLDNF resolution tree.
 *  The success nodes of this tree represent different
 *  variable bindings that make the provided goal true.
 *
 *  Example:
 *      SourceResolver r = new SourceResolver();
 *      Tree t = r.resolve(goal, binding);
 *      Collection solutions = r.solutions(t, vars);
 *
 *  @author David Hearnden, Aug 2003
 *  @author michael lawley, Aug 2003 -- modified for QVT model
 */
class SourceResolver extends AbstractResolver {

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
            return context.tree.toString();
        }

        public void handleInstance(EObject inst) throws ResolutionException, NotGroundException {
            if (context.tree.isCompleted()) {
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
                
                EStructuralFeature sFeature = getFeature(context, class1, featureName);
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
                        ExtentUtil.highlightEdge(inst, value, ExtentUtil.FEATURE_LOOKUP);
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
                        ExtentUtil.highlightEdge(inst, value, ExtentUtil.FEATURE_LOOKUP);
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

    SourceResolver(RuleEvaluator evaluator) {
    	super(evaluator);
    }

    /**
     * Find instances of the referenced tracking class with matching feature values.
     * This may involve variable binding.
     * 
     * Tracking queries are strictly match-only, so we don't need to concern
     * ourselves with tracking instance creation. So, the
     * basic algorithm is to scan all instances of the nominated tracking
     * class and filter out any instance that doesn't bind with the supplied
     * feature expressions.
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

        // Get the properties of the TrackingUse
        
        final EClass trackingClass = literal.getTracking();
        if (trackingClass.eIsProxy()) {
            // If it's still a proxy after the getTracking() call, the cross-document reference proxy has
            // not been resolved, meaning the reference was dodgy, i.e. to a non-existent class or something
            //
            context.error("Unable to locate tracking class: " + trackingClass);
        }

        List featureList = literal.getFeatures();
        final Object[][] featureMap = new Object[featureList.size()][2];
        
        int i = 0;
        for (Iterator itr = featureList.iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            featureMap[i][0] = entry.getKey();
            featureMap[i][1] = exprEval.eval(context, (Expression) entry.getValue());
            i++;
        }

        TrackingCallback callback = new HandleNewTrackingInstance(context, trackingClass, featureMap);

        // Get the existing instances of the tracking class.
        //
        Extent trackingExtent = context.tree.getTrackingExtent();
        List trackings = trackingExtent.getObjectsByClass(trackingClass, false);
        ExtentUtil.highlightNodes(trackings, ExtentUtil.CLASS_LOOKUP);
        
        // record that this rule has queried the tracking class
        ruleEval.trackingQuery(trackingClass, callback);
        
        for (Iterator trackingItr = trackings.iterator(); trackingItr.hasNext(); ) {
            EObject inst = (EObject) trackingItr.next();
            
            callback.handleInstance(inst);
        }
    }

    protected boolean resolveMofInstance(
        final Context context,
        final MofInstance literal)
    throws ResolutionException, NotGroundException {
        /**
         * Find all instances of the specified class in the (context) extent
         */
        Var extentVar = literal.getExtent();

        List results = exprEval.eval(context, literal.getTypeName());
        if (results.size() != 1) {
            context.error("Expected only a single type name, got: " + results);
        }
        Object typeObj = results.get(0);
        EClassifier theClass = null;
        String className;
        if (typeObj instanceof EClassifier) {
            theClass = (EClassifier) typeObj;
            className = ModelUtils.getFullyQualifiedName(theClass);
        } else if (typeObj instanceof WrappedVar) {
            context.error("Unsupported mode (unbound typeName) for MofInstance: " + literal);
            // satisfy the compiler (it doesn't know the previous line always throws an exception)
            className = null;
        } else {
            className = String.valueOf(typeObj);
            if (!"_".equals(className)) {
                theClass = ModelUtils.findClassifierByName(getNameMap(), className);
                if (null == theClass) {
                    ruleEval.fireWarning("Could not find class named: " + className);
                    return false;
                }
            }
        }
        
        Expression instanceExpr = literal.getInstance();

        // Our "package instance" wrapper around an EMOF ExtentUtil (EMF Resource)
        Extent extent = (null == extentVar ? null : (Extent) context.lookup(extentVar));

        Collection instances = exprEval.eval(context, instanceExpr);

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
                } else if (!(theClass instanceof EClass)) {
                    ruleEval.fireWarning("Could not find class named: " + className);
                } else if (wVar.setType((EClass) theClass, literal.isExact())) {
                    Binding unifier = new Binding();
                    unifier.add(wVar.getVar(), wVar);
                    context.createBranch(unifier);
                }
            } else {
                // handle the ??+ mode
                Binding unifier = null;
                if (instance instanceof BindingPair) {
                    unifier = (Binding) instance;
                    instance = ((BindingPair) instance).getValue();
                }
                if (instance instanceof EObject && (null == extent || extent.contains((EObject) instance))) {
                    ExtentUtil.highlightNode(instance, ExtentUtil.OBJECT_LOOKUP);
                    boolean isOfType =
                        null == theClass ||
                        (literal.isExact() ? theClass.equals(((EObject) instance).eClass()) : theClass.isInstance(instance));
                    if (isOfType) {
                        success = true;
                    
                        /**
                         * Create a new branch of the tree, and continue 
                         * resolution from the newly created node.
                         */
                        context.createBranch(unifier);
                    }
                }
            }
        }

        if (!success) {
            context.fail();
        }
        return success;
    }

    final private static String[] relOpArray = {
        "<", "<=", ">", ">=", "!="
    };
    final private static List relOpList = Arrays.asList(relOpArray);
   
    protected void resolveCondition(
        final Context context,
        final Condition term)
    throws ResolutionException, NotGroundException {
        boolean result = false;

        String relation = term.getRelation();
        List args = term.getArg();

        if ("=".equals(relation)) {
            List vals1 = exprEval.eval(context, (Expression) args.get(0));
            List vals2 = exprEval.eval(context, (Expression) args.get(1));
            
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
            Collection vals1 = exprEval.eval(context, (Expression) args.get(0));
            Collection vals2 = exprEval.eval(context, (Expression) args.get(1));
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
            Collection vals = exprEval.eval(context, (Expression) args.get(0));
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

    protected void resolveNotTerm(
        final Context context,
        final NotTerm literal)
    throws ResolutionException, NotGroundException {
        
        // Ensure that all non-local variables are already ground
        // (WrappedVars are handled by the Expander)
        for (final Iterator itr = literal.getNonLocalVars().iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
            Object value = context.lookup(var);
            if (null == value) {
                context.delay("Non-local variable " + var + " is not bound.");
            }
        }
        
        final Function f = new Function() {
            public Object call(Object[] params) throws ResolutionException {
                Binding unifier = (Binding) params[0];
                evalNegatedGoal(context, unifier, new ArrayList(literal.getTerm()));
                return null;
            }
            
        };
        
        new VarExpander(literal.getNonLocalVars(), f, context.node.getBindings());

    }

    /**
     * @param context
     * @param unifier
     * @param negGoal
     * @return
     * @throws ResolutionException
     */
    private void evalNegatedGoal(final Context context, final Binding unifier, final Collection negGoal)
    throws ResolutionException {
        // cannot pass node as context here or delayed terms will get pushed into the "NOT"
        // leading to possible spurious flounderings -- see also resolveIfTerm 
        final Tree newTree = context.createTree(negGoal, unifier, true, true);

        if (ruleEval.INCREMENTAL) {
            newTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) {
                }

                public void completed(Tree theTree) {
                    if (theTree.isSuccess()) {
                    	newTree.removeTreeListener(this);
                    	context.fail();
                    } else {
                    	// Negation tree finitely failed, regard as true.
                    	//
                        context.createBranch(new Binding(unifier));
                    }
                }

                public void floundered(Tree theTree) {
                }
                
            });
        } else {
            // Any success nodes in the negation tree?
            //
            ruleEval.resolveNode(newTree);
            if (newTree.isSuccess()) {
                context.fail();
            } else {
                // Negation tree finitely failed, regard as true.
                //
                context.createBranch(new Binding(unifier));
            }
        }
        
    }

    protected void resolveOrTerm(
        final Context context,
        final OrTerm literal)
        throws ResolutionException {
        /**
         *  Create a node for each disjunct, distributing them into
         *  the remaining conjuncts of the goal.
         */
        Collection terms = literal.getTerm();
        if (null == terms || terms.isEmpty()) {
            context.error("Malformed (empty) OrTerm");
        }

        for (Iterator itr = terms.iterator(); itr.hasNext(); ) {
            context.createBranch((Term) itr.next());
        }
    }

    protected Term selectLiteral(final Node node) {
        Term t = doSelectLiteral(node);
        return t;
    }

    protected Term doSelectLiteral(final Node node) {
        return super.selectLiteral(node);
    }
    
    protected void resolveMofOrder(final Context context, final MofOrder term)
    throws ResolutionException, NotGroundException {
        List instances = exprEval.eval(context, term.getInstance());
        List features = exprEval.eval(context, term.getFeature());
        List lesserObjects = exprEval.eval(context, term.getLesser());
        List greaterObjects = exprEval.eval(context, term.getGreater());
        
        for (Iterator iItr = instances.iterator(); iItr.hasNext(); ) {
            Object instance = iItr.next();
            
            if (instance instanceof WrappedVar) {
                context.delay("Unsupported mode (unbound '" + term.getInstance() + "') for MofOrder: " + term);
            }
            
            for (Iterator fItr = features.iterator(); fItr.hasNext(); ) {
                Object featureRef = fItr.next();

                if (featureRef instanceof WrappedVar) {
                    context.delay("Unsupported mode (unbound '" + term.getFeature() + "') for MofOrder: " + term);
                } else if (featureRef instanceof EStructuralFeature) {
                    featureRef = ((EStructuralFeature) featureRef).getName();
                }
                
                Object values = exprEval.fetchFeature((String) featureRef, instance);
                if (!(values instanceof List)) {
                    context.error("The feature " + featureRef + " of " + instance + " did not return an ordered collection.");
                }
                List valueList = (List) values;
                
                for (Iterator lItr = lesserObjects.iterator(); lItr.hasNext(); ) {
                    Object lesser = lItr.next();
                    
                    if (lesser instanceof WrappedVar) {
                        for (int i = 0; i < valueList.size(); i++) {
                            processGreaterObjects(context, greaterObjects, valueList, i);
                        }
                    } else {
                        int index = valueList.indexOf(lesser);
                        processGreaterObjects(context, greaterObjects, valueList, index);
                    }
                }
            }
        }
    }

    private void processGreaterObjects(final Context context,
            final List greaterObjects, final List valueList, final int lindex)
    throws ResolutionException {
        for (Iterator gItr = greaterObjects.iterator(); gItr.hasNext(); ) {
            Object greater = gItr.next();
        
            if (greater instanceof WrappedVar) {
                for (int i = lindex + 1; i < valueList.size(); i++) {
                    Object val = greaterObjects.get(i);
                    
                    Binding unifier = new Binding();
                    unifier.add(((WrappedVar) greater).getVar(), val);

                    context.createBranch(unifier);
                }
            } else if (lindex < valueList.indexOf(greater)) {
                context.createBranch();
            }
        }
    }
}
