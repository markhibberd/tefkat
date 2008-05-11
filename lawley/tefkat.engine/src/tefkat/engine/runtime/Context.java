package tefkat.engine.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;

public class Context {

    private static final String NULL_TYPE = "null";

    final private TransformationEvaluation evaluation;

    final Tree tree;
    final Node node;

    final static private Map methodCache = new HashMap();

    Context(TransformationEvaluation evaluation, /*RuleEvaluator ruleEval, Evaluator exprEval,*/ Tree tree, Node node) {
        this.evaluation = evaluation;
//        this.ruleEval = ruleEval;
//        this.exprEval = exprEval;
        this.tree = tree;
        this.node = node;
    }
    
    public void createBranch() {
        List newGoal = newGoal();
        tree.createBranch(node, null, newGoal);
    }
    
    public void createBranch(Term term) {
        createBranch(term, null);
    }
    
    public void createBranch(Binding unifier) {
        List newGoal = newGoal();
        tree.createBranch(node, unifier, newGoal);
    }
    
    public void createBranch(Term term, Binding unifier) {
        List newGoal = newGoal();
        newGoal.add(term);
        tree.createBranch(node, unifier, newGoal);
    }
    
    public void createBranch(Collection terms) {
        List newGoal = newGoal();
        newGoal.addAll(terms);
        tree.createBranch(node, null, newGoal);
    }
    
    public void delay(String message) throws NotGroundException {
        throw new NotGroundException(node, message);
    }
    
    public void error(String message) throws ResolutionException {
        throw new ResolutionException(node, message);
    }
    
    public void error(String message, Exception e) throws ResolutionException {
        throw new ResolutionException(node, message, e);
    }

    private List newGoal() {
        List newGoal = new ArrayList(node.goal());
        newGoal.remove(node.selectedLiteral());
        return newGoal;
    }

    public void fail() {
        node.setIsFailure(true);
    }

    /**
     * Find a binding for the variable in the current context.
     * 
     * @param var   The var to lookup in this context
     * @return      The value that var is bound to in the context of this node or null
     */
    public Object lookup(Var var) {
        return node.lookup(var);
    }
    
    public Tree createTree(Collection goal, Binding unifier, boolean isNegation, boolean subTree) {
        return createTree(new Node(goal, unifier), isNegation, subTree);
    }

    Tree createTree(Node newRoot, boolean isNegation, boolean subTree) {
        Tree result = new Tree(this, newRoot, tree.getContext(), tree.getTrackingExtent(), isNegation);
        if (subTree) {
            result.setLevel(tree.getLevel()-1);
        } else {
            result.setLevel(tree.getLevel());
        }
        
        evaluation.addUnresolvedTree(result);
        
        return result;
    }

    public Binding getBindings() {
        return node.getBindings();
    }

    public List expand(WrappedVar wVar) throws NotGroundException {
        if (null == wVar.getExtent()) {
            delay("Unsupported mode: unbound extent for " + wVar);
        }
//        Var var = wVar.getVar();
//        EClassifier type = wVar.getType();
//        System.out.println("Expanding " + wVar);   // TODO delete
//        System.out.println("\t" + wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact()));
        return wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact());
    }

    public EObject lookup(List keys, TRule rule) {
        return evaluation.injections.lookup(tree.getTrackingExtent(), keys, rule);
    }

    public void info(String string) {
        evaluation.fireInfo(string);
    }

    public void warn(String string) {
        evaluation.fireWarning(string);
    }

    public void warn(Throwable throwable) {
        evaluation.fireWarning(throwable);
    }

    public Object fetchFeature(String featureName, Object obj) throws ResolutionException {
        Object valuesObject = null;

        if (obj instanceof DynamicObject) {
            throw new ResolutionException(node, "Illegal attempt to retrieve feature value from target object instance: " + obj);
        }
        
        if (obj instanceof EObject) {
            EObject instance = (EObject) obj;
            // If instance is a DynamicObject or it's containing eResource is a target Extent
            // then we're querying a target object which is an error (until we update the stratification
            // as outlined by David Hearnden)

            EStructuralFeature eFeature = instance.eClass().getEStructuralFeature(featureName);
            if (null != eFeature) {
                valuesObject = instance.eGet(eFeature);

                if (valuesObject != null || instance.eIsSet(eFeature) || !eFeature.isRequired()) {
//                  FIXME                        ExtentUtil.highlightEdge(instance, valuesObject, ExtentUtil.FEATURE_LOOKUP);
                } else {
                    warn(Context.getFullyQualifiedName(eFeature) + " is not set and no default value");
                }
                return valuesObject;    // This was a valid feature - don't want to fall through
            }
        }
        // EFeature not found, so try other ways to get a value for featureName
        if (obj instanceof FeatureMap.Entry) {
            FeatureMap.Entry entry = (FeatureMap.Entry) obj;
            EStructuralFeature eFeature = entry.getEStructuralFeature();
            if (eFeature.getName().equals(featureName)) {
                valuesObject = entry.getValue();
                return valuesObject;    // This was a valid feature - don't want to fall through
            }
        }

        String methName = "get" + featureName.substring(0, 1).toUpperCase() + featureName.substring(1, featureName.length());
        try {
            try {
                valuesObject = obj.getClass().getMethod(methName, null).invoke(obj, null);
            } catch (NoSuchMethodException e) {
                if (null == valuesObject) {
                    valuesObject = obj.getClass().getField(featureName).get(obj);
                }
            }
        } catch (Exception e) {
            warn("Could not find a source of values for '" + featureName + "' in '" + obj + "'");
        }

        return valuesObject;
    }

    public void addPartialOrder(Object inst, Object feat, Object lesser, Object greater) {
        evaluation.addPartialOrder(inst, feat, lesser, greater);
    }
    
    public Tree getResultTree(final Term term, final Binding unifier) {
        final Map cache = evaluation.getPatternCache(term);
        final Binding parameterContext;
        if (null == unifier) {
            parameterContext = tree.getContext();
        } else {
            parameterContext = unifier;
            parameterContext.composeRight(tree.getContext());
        }
        Tree resultTree = (Tree) cache.get(parameterContext);
        
        if (null == resultTree) {
            final Collection goal = new ArrayList();
            goal.add(term);
            
//            Collection patGoal = new ArrayList();
//            Term pDefTerm = pDefn.getTerm();
//            patGoal.add(pDefTerm);

//            System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
            Node patternNode = new Node(goal, parameterContext);
        
            // Maybe Tree (via context) should construct the new tree?
            resultTree = createTree(patternNode, false, false);

            cache.put(parameterContext, resultTree);
        }
        
        if (!resultTree.isCompleted()) {
            // Register listener for floundering (and remove from cache)
            
            resultTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) {
                }

                public void completed(Tree theTree) {
                    theTree.removeTreeListener(this);
                }

                public void floundered(Tree theTree) {
                    cache.remove(parameterContext);
                }
                
            });
        }

        return resultTree;
    }
    
    /** 
     * 
     * @param trackingClass
     * @param isExactly
     * @param callback Used to notify of subsequently created instances, can be null
     * @return
     */
    public List getObjectsByClass(EClass trackingClass, boolean isExactly, TrackingCallback callback) {
        // if necessary, record that this rule has queried the tracking class
        if (null != callback) {
            evaluation.trackingQuery(trackingClass, callback);
        }

//      ExtentUtil.highlightNodes(trackings, ExtentUtil.CLASS_LOOKUP);

        return tree.getTrackingExtent().getObjectsByClass(trackingClass, isExactly);
    }
    
    public void addTrackingInstance(EClass trackingClass, EObject trackingInstance) throws ResolutionException, NotGroundException {
        tree.getTrackingExtent().add(trackingInstance);
        evaluation.trackingCreate(trackingClass, trackingInstance);
    }

    public EStructuralFeature getFeature(EClass klass, String featureName) throws ResolutionException {
        EStructuralFeature feature = klass.getEStructuralFeature(featureName);
        
        if (null == feature) {
            String featureNames = null;
            List allFeatures = klass.getEAllStructuralFeatures();
            for (Iterator itr = allFeatures.iterator(); itr.hasNext();) {
                EStructuralFeature esf = (EStructuralFeature) itr.next();
                if (null == featureNames) {
                    featureNames = esf.getName();
                } else {
                    featureNames = featureNames + ", " + esf.getName();
                }
            }
            error("Cannot find feature '"
                    + featureName + "' for '" + klass.getName()
                    + ".  Valid features are: " + featureNames);
        }
        return feature;
    }

    public boolean isCompleted() {
        return tree.isCompleted();
    }

    public Function getFunction(String name) {
        return evaluation.getFunction(name);
    }
    
    /**
     * Lookup the supplied name map (containing both unqualifed and fully-qualified names) for the corresponding EClass.
     * Returns null if it is not found.
     * 
     * @param name
     * @return
     */
    public final EClassifier findClassifierByName(String name) {
        return evaluation.findClassifierByName(name);
    }
    
    public final static String getFullyQualifiedName(Class klass) {
        return klass.getName();
    }
    
    public final static String getFullyQualifiedName(EClassifier eClassifier) {
        return getFullyQualifiedName(eClassifier.getEPackage()) + "::" + eClassifier.getName();
    }
    
    final static String getFullyQualifiedName(EPackage ePackage) {
        String name = "";
        while (null != ePackage) {
            name = "::" + ePackage.getName() + name;
            ePackage = ePackage.getESuperPackage();
        }
        return name;
    }
    
    public final static String getFullyQualifiedName(EStructuralFeature eFeature) {
        return getFullyQualifiedName(eFeature.getEContainingClass()) + "." + eFeature.getName();
    }
    
    private static Map getMethodCache(Object instance, String name) {
        Map namesToTypes = (Map) methodCache.get(instance.getClass());
        Map typesToMethods;
        if (null != namesToTypes) {
            typesToMethods = (Map) namesToTypes.get(name);
            if (null == typesToMethods) {
                typesToMethods = new HashMap();
                namesToTypes.put(name, typesToMethods);
            }
        } else {
            namesToTypes = new HashMap();
            typesToMethods = new HashMap();
            methodCache.put(instance.getClass(), namesToTypes);
            namesToTypes.put(name, typesToMethods);
        }
        return typesToMethods;
    }
    
    public static Method resolveMethod(Object instance, String name, Object[] params) {
        Map methodCache = getMethodCache(instance, name);
        Method method = null;
        
        // Deal with zero-arity method first
        if (null == params || params.length == 0) {
            Class cls = instance.getClass();
            method = (Method) methodCache.get(NULL_TYPE);
            if (null == method) {
                try {
                    Method[] ms = cls.getMethods();
                    for (int i = 0; null == method && i < ms.length; i++) {
                        if (name.equals(ms[i].getName()) && ms[i].getParameterTypes().length == 0) {
                            method = ms[i];
                        }
                    }
                } catch (SecurityException e) {
                }
            }
            if (null != method) {
                // Cache the result
                methodCache.put(NULL_TYPE, method);
            }
            return method;
        }
        
        Class[] rawTypes = new Class[params.length];
        boolean hasBoxedTypes = false;
        for (int j = 0; j < params.length; j++) {
            Class type = params[j].getClass();
            rawTypes[j] = type;
            if (Number.class.isAssignableFrom(type)) {
                hasBoxedTypes = true;
            }
        }
        
        method = (Method) methodCache.get(Arrays.asList(rawTypes));
        
        if (null == method) {
            Class[] unboxedTypes = null;
            if (hasBoxedTypes) {
                unboxedTypes = new Class[params.length];
                for (int j = 0; j < params.length; j++) {
                    Class type = params[j].getClass();
                    rawTypes[j] = type;
                    if (Number.class.isAssignableFrom(type)) {
                        try {
                            Field typeField = type.getField("TYPE");
                            unboxedTypes[j] = (Class) typeField.get(type);
                        } catch (SecurityException e) {
                        } catch (NoSuchFieldException e) {
                        } catch (IllegalArgumentException e) {
                        } catch (IllegalAccessException e) {
                        }
                    } else {
                        unboxedTypes[j] = type;
                    }
                }
            }
            method = resolveMethod(instance, name, rawTypes, unboxedTypes);
            
            if (null != method) {
                // Cache the result
                methodCache.put(Arrays.asList(rawTypes), method);
            }
        }

        return method;
    }
    
    /**
     * Beware, this will find the first method (they are in an arbitrary order) that matches subject to
     * auto-unboxing...eg, the choice between foo(int) and foo(Integer) is arbitrary
     * 
     * @param instance
     * @param name
     * @param types
     * @param unboxedTypes may be null, but otherwise the same length as types
     * @return
     */
    static private Method resolveMethod(Object instance, String name, Class[] types, Class[] unboxedTypes) {
        Method method = null;
        Class cls = instance.getClass();
        
        // FIXME: This will fail for private Classes implementing public Interfaces
        Method[] ms = cls.getMethods();
        for (int i = 0; null == method && i < ms.length; i++) {
            if (name.equals(ms[i].getName())) {
                Class[] parameterTypes = ms[i].getParameterTypes();
                if (parameterTypes.length != types.length) {
                    continue;
                }
                method = ms[i];
                for (int j = 0; j < parameterTypes.length; j++) {
                    if (!parameterTypes[j].isAssignableFrom(types[j]) &&
                        (null == unboxedTypes || !parameterTypes[j].isAssignableFrom(unboxedTypes[j]))) {
                        method = null;
                        break;
                    }
                }
            }
        }

        return method;
    }

    /**
     * Choose a literal from the goal of the given node.
     * 
     * @return A chosen literal, or null if the node's goal is empty (i.e.
     *         success)
     * @throws ResolutionException 
     * @throws NotGroundException 
     */
    public Term selectLiteral() throws ResolutionException, NotGroundException {
        Term[] literals = node.goal().toArray(new Term[node.goal().size()]);

        // Simple selection rule:
        //    + select non-target, non-negation terms first
        //    + select non-target terms next
        //    + select Injections next
        //    + select target MofInstances next
        //    + select anything else (target terms) last
        //
        for (int i = 0; i < literals.length; i++) {
            if (!(literals[i].isTarget() || literals[i] instanceof NotTerm)) {
                node.setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (!literals[i].isTarget()) {
                node.setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }

        if (!groundWrappedVars()) {
            return null;
        }

        if (null != node.getDelayed() && !node.getDelayed().isEmpty()) {
            throw new ResolutionException(node, "Flounder: All source terms delayed: " + formatDelayedNode());
        }

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof Injection) {
                node.setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof MofInstance) {
                node.setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }

        if (literals.length > 0) {
            node.setSelectedLiteral(literals[0]);
            return literals[0];
        }

        throw new ResolutionException(node,
                "Could not select a valid literal from goal: " + node.goal());
    }

    /**
     * Looks for a WrappedVar in the Node's Binding and creates new nodes with bindings to found instances.
     * If there are no instances to bind to then node fails.
     * 
     * @param tree
     * @param node
     * @return true if no WrappedVars were found
     * @throws NotGroundException
     */
    boolean groundWrappedVars() throws NotGroundException {
        boolean done = true;
    
    //      fireInfo("grounding...");
    
        final Binding binding = getBindings();
    
        for (final Iterator itr = binding.entrySet().iterator(); done && itr.hasNext(); ) {
            final Map.Entry entry = (Map.Entry) itr.next();
            final Var var = (Var) entry.getKey();
            final Object v = entry.getValue();
            if (v instanceof WrappedVar) {
                done = false;
    
                final List l = expand((WrappedVar) v);
    
    //              fireInfo(var + " <- #instances: " + l.size());
                if (l.size() == 0) {
                    fail();
                } else {
                    for (final Iterator itr2 = l.iterator(); itr2.hasNext(); ) {
                        final Object o = itr2.next();
                        Binding unifier = new Binding();
                        unifier.add(var, o);
    //                      fireInfo("    " + var + " = " + o);
                        createBranch(unifier);
                    }
                }
            }
        }
        return done;
    }
    
    private String formatDelayedNode() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append("[\n");
        Collection<NotGroundException> reasons = node.getDelayReasons();
        for (final Iterator<NotGroundException> itr = reasons.iterator(); itr.hasNext(); ) {
            final NotGroundException reason = itr.next();
            final Node reasonNode = reason.getNode();
            Term term = reasonNode.selectedLiteral();
            EObject c = term.eContainer();
            while (null != c && !(c instanceof VarScope)) {
                c = c.eContainer();
            }
            sb.append("  ").append(reason.getMessage()).append(" in ").append(c).append("\n");
        }
        sb.append("]");
        
//        sb.append("[\n");
//        Collection<Term> terms = node.getDelayed();
//        for (final Iterator<Term> itr = terms.iterator(); itr.hasNext(); ) {
//            final Term term = itr.next();
//            sb.append("  ").append(term).append("\n");
//        }
//        sb.append("]");
//        
//        sb.append(node.getBindings());
        
        return sb.toString();
    }
    
}
