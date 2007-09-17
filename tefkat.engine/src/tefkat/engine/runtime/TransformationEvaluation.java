
package tefkat.engine.runtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import tefkat.engine.runtime.functions.Add;
import tefkat.engine.runtime.functions.AppendFunction;
import tefkat.engine.runtime.functions.CastDouble;
import tefkat.engine.runtime.functions.CastFloat;
import tefkat.engine.runtime.functions.CastInt;
import tefkat.engine.runtime.functions.CastLong;
import tefkat.engine.runtime.functions.CollectFunction;
import tefkat.engine.runtime.functions.DataMapLookup;
import tefkat.engine.runtime.functions.Divide;
import tefkat.engine.runtime.functions.ElementAt;
import tefkat.engine.runtime.functions.IdentityFunction;
import tefkat.engine.runtime.functions.JoinStrings;
import tefkat.engine.runtime.functions.Multiply;
import tefkat.engine.runtime.functions.SplitString;
import tefkat.engine.runtime.functions.StripSuffix;
import tefkat.engine.runtime.functions.SubList;
import tefkat.engine.runtime.functions.Subtract;
import tefkat.engine.trace.BoolAny;
import tefkat.engine.trace.IntAny;
import tefkat.engine.trace.ObjectAny;
import tefkat.engine.trace.StringAny;
import tefkat.engine.trace.Trace;
import tefkat.engine.trace.TraceFactory;
import tefkat.engine.trace.TracePackage;

final public class TransformationEvaluation {

    private static final boolean ONE_TREE = true;

    private final Extent trackingExtent;

    private final Binding binding;

    private final List listeners;

    private volatile boolean isInterrupted = false;

    private volatile boolean stepMode = false;

    private volatile int returnMode = 0;

    private volatile int step = 0;
    
    private int depth = 0;


    private final List[] stratas;
    
    private final boolean force;

    private final Map nameMap = new HashMap();

    private final Map funcMap = new HashMap();

    private final Map evalCache = new HashMap();

    private final Map patternCache = new HashMap();

    final private Map trackingQueryMap = new HashMap();

    final private Map featureOrderings = new HashMap();

    final private Set breakpoints = new HashSet();
    
    final private List unresolvedTrees = new ArrayList();

    /**
     * 
     * @throws TefkatException 
     */
    public TransformationEvaluation(Transformation transformation, Binding binding, boolean force, Extent trackingExtent, List listeners) throws TefkatException {

        // TODO check for null target model, either here or in the engine

        this.trackingExtent = trackingExtent;
        this.listeners = listeners;
        this.binding = binding;
        this.force = force;
        
        initFunctionMap();
        
        buildNameMap(transformation, nameMap);
        buildMaps(transformation);

        fireInfo("Constructing stratification...");
        stratas = transformation.getStrata();
        fireInfo("... " + stratas.length + " levels.");

    }

    private void initFunctionMap() {
        addFunction("identity", new IdentityFunction());
        addFunction("collect", new CollectFunction());
        addFunction("append", new AppendFunction());
        addFunction("elementAt", new ElementAt());
        addFunction("subList", new SubList());
        addFunction("join", new JoinStrings());
        addFunction("split", new SplitString());
        addFunction("stripSuffix", new StripSuffix());
        addFunction("int", new CastInt());
        addFunction("long", new CastLong());
        addFunction("float", new CastFloat());
        addFunction("double", new CastDouble());
        addFunction("+", new Add());
        addFunction("-", new Subtract());
        addFunction("*", new Multiply());
        addFunction("/", new Divide());
        
//        addFunction("funmap", new MapFeature());
        
        // FIXME rename this function to dataMap or something (see tefkat.g)
        addFunction("map", new DataMapLookup());
    }
    
    void addUnresolvedTree(Tree tree) {
        // insert the tree at the start of the list
        // This means we perform a depth-first traversal which will
        // make debugging more intuitive.
        unresolvedTrees.add(0, tree);
        fireTreeAdded(tree);
    }
    
    void removeUnresolvedTree(Tree tree) {
        unresolvedTrees.remove(tree);
        fireTreeRemoved(tree);
    }
    
    protected void addBreakpoint(Term t) {
        breakpoints.add(t);
    }
    
    protected void removeBreakpoint(Term t) {
        breakpoints.remove(t);
    }

    protected boolean getInterrupted() {
        return isInterrupted;
    }

    protected void setInterrupted(boolean state) {
        isInterrupted = state;
    }

    public void run() throws TefkatException {
        try {

            for (int level = 0; level < stratas.length; level++) {
                fireInfo("Stratum " + level + " : " + stratas[level]);

                // Currently, we use a single Tree per stratum which means
                // that it's really a forest with one root Node per TRule
                //
                Tree tree;
                if (ONE_TREE) {
                    tree = new Tree(null, null, binding, trackingExtent, false);
                    tree.setLevel(level);

                    addUnresolvedTree(tree);
                }

                for (final Iterator itr = stratas[level].iterator(); itr.hasNext(); ) {
                    Object scope = itr.next();

                    if (scope instanceof TRule) {
                        TRule tRule = (TRule) scope;

                        if (!tRule.isAbstract()) {
                            if (!ONE_TREE) {
                                tree = new Tree(null, null, binding, trackingExtent, false);
                                tree.setLevel(level);

                                addUnresolvedTree(tree);
                            }
                            incrementalEvaluate(tRule, tree);
                        }
                    }
                }

                while (unresolvedTrees.size() > 0) {
                    try {
                        resolve();
                        //                      System.err.println("completing trees...");
                        int minLevel = Integer.MAX_VALUE;
                        List done = new ArrayList();
                        for (int j = 0; j < unresolvedTrees.size(); j++) {
                            Tree cTree = (Tree) unresolvedTrees.get(j);
                            if (cTree.getLevel() < minLevel) {
                                done.clear();
                                done.add(cTree);
                                minLevel = cTree.getLevel();
                            } else if (cTree.getLevel() == minLevel) {
                                done.add(cTree);
                            }
                        }

                        if (done.size() == 0) {
                            // I don't think we should ever reach here...
                            throw new TefkatException("Internal Error.  Please file a bug report.");
                        } else {
                            //                          System.err.println("Min level: " + minLevel);
                            for (Iterator itr = done.iterator(); itr.hasNext(); ) {
                                Tree cTree = (Tree) itr.next();
                                //                              System.err.println(cTree + " " + cTree.isNegation() + "\t" + cTree.getLevel());
                                removeUnresolvedTree(cTree);
                                cTree.completed();
                            }
                        }
                        //                      System.err.println(" #trees = " + unresolvedTrees.size());
                    } catch (ResolutionException e) {
                        if (force) {
                            fireError(e);
                        } else {
                            throw e;
                        }
                    }
                }

            }

            topologicalSort();
        } catch (ResolutionException e) {
            fireError(e);
            if (stepMode) {
                breakpoint(e.getNode().selectedLiteral());
            }
            throw e;
        } catch (RuntimeException e) {
            fireError(e);
            if (stepMode) {
                // FIXME - this stuff doesn't work as intended
                pause();
                waitStep();
            }
            throw new ResolutionException(null, "Internal error", e);
        }
    }
    
    private void incrementalEvaluate(final TRule trule, final Tree tree)
    throws ResolutionException {
        // Only evaluate rules once
        if (evalCache.containsKey(trule)) {
            fireInfo("Using cached results for " + trule.getName());
            fireEvaluateRule(trule, binding, true);
            return;
        }
        fireEvaluateRule(trule, binding, false);

        Collection goal = trule.getGoal();
        Collection ruleContexts = generateContexts(trule, binding);

        // FIXME - no rule caching any more
        final Collection truleSolutions = new HashSet();
        
        for (Iterator itr = ruleContexts.iterator(); itr.hasNext();) {
            final Binding ruleContext = (Binding) itr.next();

            tree.createBranch(null, ruleContext, goal);
            
            tree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) throws ResolutionException {
                    // nothing to do in this case
                }

                public void completed(Tree theTree) {
                    if (theTree.isSuccess()) {
                        fireInfo("TRule: " + trule.getName() + " completed.");
                    } else {
                        fireWarning("TRule: " + trule.getName() + " matched nothing.");
                    }
                }

                public void floundered(Tree theTree) {
                    // floundering of top-level tree is handled in the
                    // resolve/resolveNode loop
                }
            
            });
        }

        // record the results for later use by extending TRules
        evalCache.put(trule, truleSolutions);
    }

    protected void pause() {
        synchronized (unresolvedTrees) {
            stepMode = true;
        }
    }
    
    protected void step() {
        synchronized (unresolvedTrees) {
            step++;
            unresolvedTrees.notifyAll();
        }
    }
    
    protected void stepReturn() {
        synchronized (unresolvedTrees) {
            returnMode = depth;
            resume();
        }
    }
    
    protected void resume() {
        synchronized (unresolvedTrees) {
            stepMode = false;
            unresolvedTrees.notifyAll();
        }
    }
    
    private void breakpoint(Term t) {
        synchronized (unresolvedTrees) {
            pause();
            fireBreakpoint(t);
            try {
                unresolvedTrees.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void waitStep() {
        synchronized (unresolvedTrees) {
            while (stepMode && step < 1) {
                try {
                    fireSuspend();
                    if (step < 1) {
                        unresolvedTrees.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (stepMode && step > 0) {
                step--;
            }
            fireResume();
        }
    }

    protected void fireBreakpoint(Term t) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).breakpoint(t);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireSuspend() {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).suspended();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireResume() {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).resumed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireInfo(String message) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).info(message);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireWarning(Throwable t) {
        String message = t.getMessage();
        if (null == message) {
            message = String.valueOf(t);
        }
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).warning(message);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireWarning(String message) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).warning(message);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireError(Throwable t) {
        String message = t.getMessage();
        if (null == message) {
            message = String.valueOf(t);
        }
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).error(message, t);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireError(String message) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).error(message, null);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireResourceLoaded(Resource res) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).resourceLoaded(res);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireTreeAdded(Tree tree) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                Object listener = itr.next();
                if (listener instanceof TefkatListener2) {
                    ((TefkatListener2) listener).treeAdded(tree);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireTreeRemoved(Tree tree) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                Object listener = itr.next();
                if (listener instanceof TefkatListener2) {
                    ((TefkatListener2) listener).treeRemoved(tree);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireEnterTree(Tree tree) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).enterTree(tree);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireExitTree(Tree tree) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).exitTree(tree);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireEnterTerm(Node node) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).enterTerm(node);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireExitTerm(Node node) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).exitTerm(node);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void fireDelayTerm(Node node) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).delayTerm(node);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireEvaluateRule(TRule rule, Binding context, boolean cached) {
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).evaluateRule(rule, context, cached);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Iterate until no trees with unresolved nodes.
     * This should be called from within a loop over the unresolvedTrees
     * that completes all unresolvedTrees that belong to the minimum strata.
     * 
     * @throws ResolutionException
     */
    void resolve() throws ResolutionException {
        
        while (!isInterrupted && unresolvedTrees.size() > 0) {
            Tree tree = null;
            Node node = null;
            for (Iterator itr = unresolvedTrees.iterator(); null == node && itr.hasNext(); ) {
                tree = (Tree) itr.next();
                node = tree.getUnresolvedNode();
            }
            if (null == node) {
                // No Trees with unresolved nodes
                break;
            }
            
            try {
                depth++;
                fireEnterTree(tree);

                //  Is the goal already a success?
                //
                if (node.goal().isEmpty()) {

                    fireEnterTerm(node);

                    if (null == node.getDelayed() || node.getDelayed().isEmpty()) {
                        tree.success(node);
                    } else {
                        // If this is a subtree (created for IFs or PATTERNs/TEMPLATEs)
                        // propagate delay -- only top-level Trees (for TRules) should flounder
                        
                        Node flounder = tree.flounder();
                        
                        if (null != flounder) {
                            // Don't continue with this tree - it's obsolete
                            unresolvedTrees.remove(tree);
                            fireDelayTerm(flounder);
                        } else {
                            throw new ResolutionException(node,
                                    "Floundered - all terms are delayed: "
                                    + node.getDelayed() + "\t"
                                    + node.getBindings());
                        }
                    }

                    fireExitTerm(node);
                } else {
                    //  Select a literal for node.
                    //
                    Term literal = node.selectLiteral();

                    try {
                        fireEnterTerm(node);

//                        if (breakpoints.contains(literal)) {
//                            breakpoint(literal);
//                        } else
                        if (stepMode) {
                            waitStep();
                        }

                        // Grow the tree according to the type of literal using the
                        // appropriate resolver.
                        //
                        final Context context = new Context(this, tree, node);
                        if (literal.isTarget()) {
                            ((TargetTerm) literal).ensure(context);
                        } else {
                            ((SourceTerm) literal).match(context);
                        }

                        fireExitTerm(node);

                    } catch (NotGroundException e) {
                        // fireInfo("delaying: " + literal + " : " + e);
                        if (node.selectedLiteral() != literal) {
                            throw new AssertionError(
                                "Internal Error: inconsistent state, please report this problem to the developers.");
                        }
e.printStackTrace();
                        node.delay();
                        tree.addUnresolvedNode(node);
                        fireDelayTerm(node);
                    }
                }

            } finally {
                if (returnMode >= depth) {
                    returnMode = 0;
                    pause();
                    // waitStep();
                }

                if (tree.isCompleted()) {
                    removeUnresolvedTree(tree);
                }

                fireExitTree(tree);
                depth--;
            }
        }
    }


    // ================================================================================
    // Utility methods for constructing suitable goals that account for the
    // extends and supersedes associations.
    //

    /**
     * Stores a map to the (inverted) supersedes references.
     */
    private final Map invertedSupMap = new HashMap();

    private final Map extendsBindingMap = new HashMap();

    /**
     * Construct variable bindings required for extends relationships. Depends
     * on buildExtBindings(), buildSupBindings() and itself.
     * 
     * @param rule
     * @return
     * @throws ResolutionException
     */
    private Binding getExtendsBinding(TRule rule) throws ResolutionException {
        Binding binding = (Binding) extendsBindingMap.get(rule);
        if (null == binding) {
            binding = new Binding();

            buildExtBindings(rule.getVars(), binding);
            buildSupBindings(rule.getVars(), binding);

            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                binding.composeRight(getExtendsBinding(extRule));
            }

            for (Iterator itr = rule.getSuperseded().iterator(); itr.hasNext();) {
                TRule supRule = (TRule) itr.next();
                binding.composeRight(getExtendsBinding(supRule));
            }

            extendsBindingMap.put(rule, binding);
        }
        return binding;
    }

    private final Map overrideBindingMap = new HashMap();

    /**
     * Construct variable bindings required for superseding relationships.
     * Depends on invertedSupMap, sPrimeBinding() and itself.
     * 
     * @param rule
     * @return
     * @throws ResolutionException
     */
    private Binding getOverrideBinding(TRule rule) throws ResolutionException {
        Binding binding = (Binding) overrideBindingMap.get(rule);
        if (null == binding) {
            binding = new Binding();

            List supersedingRules = getList(invertedSupMap, rule);
            for (Iterator itr = supersedingRules.iterator(); itr.hasNext();) {
                TRule supersedingRule = (TRule) itr.next();

                binding.composeRight(getExtendsBinding(supersedingRule));
            }

            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                binding.composeRight(getOverrideBinding(extRule));
            }

            overrideBindingMap.put(rule, binding);
        }

        return binding;
    }

    /**
     * Populate the supMap and extMap fields with the (inverted) supersedes
     * references and the transitive closure of the extends references.
     * 
     * @param tr
     * @throws ResolutionException
     */
    private void buildMaps(Transformation tr) {
         // Stores a map to the transitive closure of the extends references.
        final Map extRulesMap = new HashMap();

        for (Iterator itr = tr.getTRule().iterator(); itr.hasNext();) {
            TRule rule = (TRule) itr.next();

            // Compute the TC of the rules that 'rule' extends
            List ext = getList(extRulesMap, rule);
            buildExtList(rule, ext);

            // For each rule that this rule supersedes, bulid the inverted
            // association
            for (Iterator supItr = rule.getSuperseded().iterator(); supItr.hasNext();) {
                TRule supRule = (TRule) supItr.next();

                List sup = getList(invertedSupMap, supRule);
                sup.add(rule);
            }
        }
    }

    private void buildExtBindings(List vars, Binding binding)
            throws ResolutionException {
        for (Iterator itr = vars.iterator(); itr.hasNext();) {
            Var var = (Var) itr.next();
            for (Iterator extItr = var.getExtended().iterator(); extItr.hasNext();) {
                Var extVar = (Var) extItr.next();
                linkVars(binding, var, extVar);
            }
        }
    }

    private void buildSupBindings(List vars, Binding binding)
            throws ResolutionException {
        for (Iterator itr = vars.iterator(); itr.hasNext();) {
            Var var = (Var) itr.next();
            for (Iterator supItr = var.getSuperseded().iterator(); supItr.hasNext();) {
                Var supVar = (Var) supItr.next();
                linkVars(binding, var, supVar);
            }
        }
    }

    private Object lookup(Binding binding, Var var) {
        Object obj = binding.lookup(var);
        if (null == obj) {
            return new WrappedVar(var);
        }
        return obj;
    }

    private void linkVars(Binding binding, Var lhVar, Var rhVar)
            throws ResolutionException {
        Object lhObj = lookup(binding, lhVar);
        Object rhObj = lookup(binding, rhVar);
        if (lhObj instanceof WrappedVar) {
            Var var = ((WrappedVar) lhObj).getVar();
            binding.add(var, rhObj);
        } else {
            binding.add((Var) lhObj, rhObj);
        }
    }

    private List getList(Map map, TRule rule) {
        List list = (List) map.get(rule);
        if (null == list) {
            list = new ArrayList();
            map.put(rule, list);
        }
        return list;
    }

    //    private Binding getBinding(Map map, TRule rule) {
    //        Binding binding = (Binding) map.get(rule);
    //        if (null == binding) {
    //            binding = new Binding();
    //            map.put(rule, binding);
    //        }
    //        return binding;
    //    }

    private void buildExtList(TRule rule, List rules) {
        rules.addAll(rule.getExtended());
        for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
            TRule extRule = (TRule) itr.next();
            buildExtList(extRule, rules);
        }
    }

    //
    //
    // ================================================================================

    private Collection generateContexts(TRule trule, Binding context)
            throws ResolutionException {
        Collection contextSet = new HashSet();
        Binding ruleContext = new Binding(context);
        ruleContext.composeRight(getExtendsBinding(trule));
        ruleContext.composeRight(getOverrideBinding(trule));
        contextSet.add(ruleContext);

        return contextSet;
    }

    /**
     * Records a TRule's interest in a given tracking class for fix-point
     * computation.
     * 
     * @param trackingClass
     * @param callback
     */
    void trackingQuery(EClass trackingClass, TrackingCallback callback) {
        List callbacks = (List) trackingQueryMap.get(trackingClass);
        if (null == callbacks) {
            callbacks = new ArrayList();
            trackingQueryMap.put(trackingClass, callbacks);
        }
        callbacks.add(callback);
    }

    /**
     * Records that an instance of a given tracking class was created.
     * 
     * @param trackingClass
     * @throws NotGroundException
     * @throws ResolutionException
     */
    void trackingCreate(EClass trackingClass, EObject instance) throws ResolutionException, NotGroundException {
        // FIXME do this for all superclasses as well!
        //
        List callbacks = (List) trackingQueryMap.get(trackingClass);
        if (null != callbacks) {
            for (Iterator itr = callbacks.iterator(); itr.hasNext(); ) {
                TrackingCallback callback = (TrackingCallback) itr.next();
                callback.handleInstance(instance);
            }
        }
    }

    void addPartialOrder(Object inst, Object feat, Object lesser, Object greater) {
        Map instanceOrderings = (Map) featureOrderings.get(feat);
        if (null == instanceOrderings) {
            instanceOrderings = new HashMap();
            featureOrderings.put(feat, instanceOrderings);
        }
        PartialOrder partialOrder = (PartialOrder) instanceOrderings.get(inst);
        if (null == partialOrder) {
            partialOrder = new PartialOrder(feat + " of " + inst);
            instanceOrderings.put(inst, partialOrder);
        }
        partialOrder.lessThan(lesser, greater);
    }

    private void topologicalSort() throws ResolutionException {
        if (featureOrderings.size() > 0) {
            fireInfo("Resolving ordering constraints.");
        }
        for (final Iterator fItr = featureOrderings.entrySet().iterator(); fItr.hasNext(); ) {
            final Map.Entry fEntry = (Map.Entry) fItr.next();
            final String feat = (String) fEntry.getKey();
            final Map featureOrderings = (Map) fEntry.getValue();
            
            for (final Iterator iItr = featureOrderings.entrySet().iterator(); iItr.hasNext(); ) {
                final Map.Entry iEntry = (Map.Entry) iItr.next();
                final EObject inst = (EObject) iEntry.getKey();
                final PartialOrder partialOrder = (PartialOrder) iEntry.getValue();
                final EStructuralFeature feature = inst.eClass().getEStructuralFeature(feat);
                final Object val = inst.eGet(feature);
                
                if (!(val instanceof List)) {
                    throw new ResolutionException(null, "The feature " + feat + " of "
                            + inst + " did not return an ordered collection.");
                }

                partialOrder.sort((List) val);
            }
        }
    }

    static class PartialOrder {
        final private String context;
        
        final private Map instanceOrderings = new HashMap();
        final private Map instanceCounters = new HashMap();

        PartialOrder(String context) {
            this.context = context;
        }
        
        void lessThan(Object lesser, Object greater) {
//            System.out.println(lesser + " < " + greater);
            if (null == instanceOrderings.get(greater)) {
                instanceOrderings.put(greater, new HashSet());
                instanceCounters.put(greater, new Counter());
            }
            Set adjacentNodes = (Set) instanceOrderings.get(lesser);
            if (null == adjacentNodes) {
                adjacentNodes = new HashSet();
                instanceOrderings.put(lesser, adjacentNodes);
                instanceCounters.put(lesser, new Counter());
            }
            if (!adjacentNodes.contains(greater)) {
                ((Counter) instanceCounters.get(greater)).increment();
                adjacentNodes.add(greater);
            }
        }
        
        void lessThanEqual(Object lesser, Object greater) {
//            System.out.println(lesser + " <= " + greater);
            if (null == instanceOrderings.get(greater)) {
                instanceOrderings.put(greater, new HashSet());
                instanceCounters.put(greater, new Counter());
            }
            Set adjacentNodes = (Set) instanceOrderings.get(lesser);
            if (null == adjacentNodes) {
                adjacentNodes = new HashSet();
                instanceOrderings.put(lesser, adjacentNodes);
                instanceCounters.put(lesser, new Counter());
            }
            adjacentNodes.add(greater);
        }
        
        void sort(List vals) throws ResolutionException {
            List no_pred = new ArrayList();

            // Identify all nodes with no predecessors
            for (final Iterator itr = instanceOrderings.keySet().iterator(); itr.hasNext(); ) {
                Object node = itr.next();
                if (((Counter) instanceCounters.get(node)).isZero()) {
                    no_pred.add(node);
                }
            }

            // As long as there are nodes with no predecessors, output and "delete"
            // them
            for (int i = 0; !no_pred.isEmpty(); i++) {
                Object lesser = no_pred.remove(0);

                // ((org.eclipse.emf.common.util.EList) vals).move(i, lesser);
                int idx = vals.lastIndexOf(lesser);
                if (idx >= 0) {
                    vals.remove(idx);
                } else {
                    throw new ResolutionException(null, "Cannot order non-existent member of " + context + " : " + lesser);
                }
                try {
                    vals.add(i, lesser);
                } catch (ArrayStoreException e) {
                    throw new ResolutionException(null, "Cannot store " + lesser + " in " + context, e);
                }

                Collection adjacentNodes = (Collection) instanceOrderings.get(lesser);
                if (null == adjacentNodes) {
                    continue;
                }
                for (Iterator neighbors = adjacentNodes.iterator(); neighbors.hasNext(); ) {
                    Object greater = neighbors.next();
                    Counter counter = (Counter) instanceCounters.get(greater);
                    if (null == counter) {
                        no_pred.add(greater);
                    } else {
                        counter.decrement();
                        if (counter.isZero()) {
                            no_pred.add(greater);
                        }
                    }
                }
            }

            // Are there nodes remaining? If so, it was a cyclic graph.
            List cycle = new ArrayList();
            for (final Iterator itr = instanceCounters.entrySet().iterator(); itr.hasNext();) {
                final Map.Entry entry = (Map.Entry) itr.next();
                final Counter value = (Counter) entry.getValue();
                if (!value.isZero()) {
                    final Object key = entry.getKey();
                    cycle.add(key);
                }
            }
            if (!cycle.isEmpty()) {
                throw new ResolutionException(null, "Found a cyclic partial order in "
                        + context + ": " + cycle);
            }
        }

        private static class Counter {
            int val = 0;
            
            Counter() {
                // no init required
            }

            void increment() {
                val++;
            }

            void decrement() {
                val--;
            }

            boolean isZero() {
                return 0 == val;
            }
        }
    }

    private static void importResource(Set resources, EPackage.Registry registry, ResourceSet resourceSet, String uriStr) throws ResolutionException {
        // Avoid explicitly loading resources corresponding to packages that
        // have already been loaded (or dynamically created!)
        //
        try {
            if (registry.containsKey(uriStr)) {
                resources.add(registry.getEPackage(uriStr).eResource());
            } else {
                // gracefully handle the case where an XSD has no targetNamespace
                // (there can be only one :-)
                EPackage nullPkg = registry.getEPackage(null);
                if (null != nullPkg && uriStr.equals(nullPkg.getNsURI())) {
                    resources.add(nullPkg.eResource());
                } else {
                    resources.add(getResource(resourceSet, uriStr));
                }
            }
        } catch (IOException e) {
            throw new ResolutionException(null, "Could not import model: " + uriStr, e);
        }
    }
    
    private static void buildNameMap(Transformation t, Map nameMap) throws ResolutionException {
        Map importedNamespaces = new HashMap();
        importedNamespaces.put(null, new HashSet());

        final ResourceSet resourceSet = t.eResource().getResourceSet();
        EPackage.Registry registry = resourceSet.getPackageRegistry();
        
        for (final Iterator itr = t.getNamespaceDeclarations().iterator(); itr.hasNext(); ) {
            NamespaceDeclaration nsd = (NamespaceDeclaration) itr.next();
            String name = nsd.getPrefix();
            String uriStr = nsd.getURI();
            Set resources = (Set) importedNamespaces.get(name);
            if (null == resources) {
                resources = new HashSet();
                importedNamespaces.put(name, resources);
            }
            importResource(resources, registry, resourceSet, uriStr);
        }
        Set resources = (Set) importedNamespaces.get(null);
        resources.add(t.eResource());
        for (final Iterator itr = t.getImportedPackages().iterator(); itr.hasNext(); ) {
            String uriStr = (String) itr.next();
            importResource(resources, registry, resourceSet, uriStr);
        }
        
        for (final Iterator itr = importedNamespaces.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Entry) itr.next();
            String name = (String) entry.getKey();
            resources = findAllResources((Collection) entry.getValue());
            buildNameMaps(resources, nameMap, name);
        }
    }

    // Attempting to use a separate thread to allow hung resource loads to be interrupted
    private static Resource getResource(final ResourceSet resourceSet, final String location) throws IOException {
        URI uri = URI.createURI(location);
        return resourceSet.getResource(uri, true);

//        final Resource[] res = { null };
//        final Exception[] error = { null };
//        Thread thread = new Thread() {
//            public void run() {
//                try {
//                    URI uri = URI.createURI(location);
//                    res[0] = resourceSet.getResource(uri, true);
//                } catch (Exception e) {
//                    error[0] = e;
//                } finally {
//                    threads.remove(this);
//                }
//            }
//        };
//        threads.add(thread);
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            res[0] = null;
//            error[0] = e;
//        }
//        if (null == res[0] || null != error[0]) {
//            IOException e = new IOException("Failed to load Resource from " + location);
//            if (null != error[0]) {
//                e.initCause(error[0]);
//            }
//            throw e;
//        } else if (null != res[0]) {
//            List errors = res[0].getErrors();
//            if (errors.size() > 0) {
//                throw new IOException("Parse errors: " + errors);
//            }
//        }
//        return res[0];
    }

    /**
     * Given a list of Resources, transitively load all resources that contain referenced stuff.
     * 
     * @param resources
     * @return
     */
    static private final Set findAllResources(Collection resources) {
        Set result = new HashSet(resources);

        while (resources.size() > 0) {
            Set newResources = new HashSet();
            for (Iterator itr = resources.iterator(); itr.hasNext(); ) {
                Resource res = (Resource) itr.next();
                Map refs = EcoreUtil.ExternalCrossReferencer.find(res);
                for (Iterator refItr = refs.keySet().iterator(); refItr.hasNext(); ) {
                    EObject o = (EObject) refItr.next();
                    Resource newRes = o.eResource();
                    // ignore things we've already seen
                    if (newRes != null && !result.contains(newRes)) {
                        newResources.add(newRes);
//                        fireResourceLoaded(newRes);
                    }
                }
            }
//            newResources.removeAll(result); // ignore things we've already seen
            result.addAll(newResources);    // remember everything for result
            resources = newResources;       // get ready to iterate over the new things
        }

        return result;
    }
    
    private static Map buildNameMaps(Collection resources, Map nameMap, String namespace) {
        XSDEcoreBuilder xsdEcoreBuilder = null;
        for (Iterator itr = resources.iterator(); itr.hasNext();) {
            Resource res = (Resource) itr.next();
            // Do a tree iteration over the Resource, pruning on every
            // non-EPackage since only EPackages can (transitively) contain
            // EClassifiers
            TreeIterator treeItr = res.getAllContents();
            xsdEcoreBuilder = buildNameMaps(treeItr, nameMap, namespace, res.getResourceSet(), xsdEcoreBuilder);
        }
        if (null != xsdEcoreBuilder) {
            for (Iterator itr = xsdEcoreBuilder.getTargetNamespaceToEPackageMap().values().iterator(); itr.hasNext();) {
                EPackage pkg = (EPackage) itr.next();
                xsdEcoreBuilder = buildNameMaps(pkg.eAllContents(), nameMap, namespace, null, xsdEcoreBuilder);
            }
        }
        
        return nameMap;
    }
    
    private static XSDEcoreBuilder buildNameMaps(TreeIterator treeItr, Map nameMap, String namespace, final ResourceSet resourceSet, XSDEcoreBuilder xsdEcoreBuilder) {
        while (treeItr.hasNext()) {
            EObject obj = (EObject) treeItr.next();
            if (obj instanceof EClassifier) {
                EClassifier eClassifier = (EClassifier) obj;
                
                String fqName = getFullyQualifiedName(eClassifier);
                addToMap(nameMap, fqName, eClassifier);
                
                String name = eClassifier.getName();
                addToMap(nameMap, name, eClassifier);
                
                if (null != namespace) {
                    addToMap(nameMap, '^' + namespace + fqName, eClassifier);
                    addToMap(nameMap, namespace + '^' + name, eClassifier);
                }
                
//              if (obj instanceof EEnum) {
//              EEnum eEnum = (EEnum) obj;
//              for (Iterator eItr = eEnum.getELiterals().iterator(); eItr.hasNext(); ) {
//              EEnumLiteral eLiteral = (EEnumLiteral) eItr.next();
//              nameMap.put(fqName + "::" + eLiteral.getName(), eLiteral);
//              }
//              }
            } else if (obj instanceof XSDSchema) {
                // Attempt to handle ecore models dynamically generated from xml schema 
                if (null == xsdEcoreBuilder) {
                    xsdEcoreBuilder = new XSDEcoreBuilder();
                }
                try {
                    xsdEcoreBuilder.generate((XSDSchema) obj);
                } catch (ClassCastException e) {
                    // FIXME ignore this for the moment -- it's patched in EMF's CVS HEAD
                    // see bugzilla -- https://bugs.eclipse.org/bugs/show_bug.cgi?id=136267
                    System.err.println(obj);
                    //e.printStackTrace();
                }
            }
            
            if (!(obj instanceof EPackage)) {
                treeItr.prune();
            }
        }
        return xsdEcoreBuilder;
    }
    
    private static void addToMap(Map nameMap, String name, EClassifier eClassifier) {
        if (nameMap.containsKey(name)) {
            if (!eClassifier.equals(nameMap.get(name))) {
                // Record a name-clash by storing a List of the clashing things
                Object clashObj = nameMap.get(name);
                if (clashObj instanceof List) {
                    ((List) clashObj).add(eClassifier);
                } else {
                    List allNames = new ArrayList();
                    allNames.add((EClassifier) clashObj);
                    allNames.add(eClassifier);
                    nameMap.put(name, allNames);
                }
            }
        } else {
            nameMap.put(name, eClassifier);
        }
    }

    public final static String getFullyQualifiedName(EClassifier eClassifier) {
        return getFullyQualifiedName(eClassifier.getEPackage()) + "::" + eClassifier.getName();
    }
    
    final static protected String getFullyQualifiedName(EPackage ePackage) {
        String name = "";
        while (null != ePackage) {
            name = "::" + ePackage.getName() + name;
            ePackage = ePackage.getESuperPackage();
        }
        return name;
    }
    
    /**
     * Lookup the supplied name map (containing both unqualifed and fully-qualified names) for the corresponding EClass.
     * Returns null if it is not found.
     * 
     * @param name
     * @return
     */
    EClassifier findClassifierByName(String name) {
        if (!nameMap .containsKey(name)) {
            return null;
        }
        Object obj = nameMap.get(name);
        if (obj instanceof List) {
            throw new RuntimeException("Ambiguous name: " + name + " resolves to " + obj);
        }
        return (EClassifier) obj;
    }

    /**
     * @param term
     * @return
     */
    Map getPatternCache(Term term) {
        Map cache = (Map) patternCache.get(term);
        if (null == cache) {
            cache = new HashMap();
            patternCache.put(term, cache);
        }
        return cache;
    }
    
    // Injections
    
    final private Map injections = new HashMap();
    final private Map traces = new HashMap();
    
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
//        ExtentUtil.highlightNode(obj, ExtentUtil.OBJECT_LOOKUP);
//        ExtentUtil.highlightNode(trace, ExtentUtil.OBJECT_LOOKUP);
        
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
                any.getValue().add(key);
                sources.add(any);
                // In case a target object is used as an injection parameter
                if (key instanceof DynamicObject) {
                    DynamicObject dynObj = (DynamicObject) key;
                    dynObj.addMultiReferenceFrom(any, TracePackage.eINSTANCE.getObjectAny_Value());
                }
            } else if (key instanceof String) {
                StringAny any = TraceFactory.eINSTANCE.createStringAny();
                any.setValue((String) key);
                sources.add(any);
            } else if (key instanceof Integer) {
                IntAny any = TraceFactory.eINSTANCE.createIntAny();
                any.setValue(((Integer) key).intValue());
                sources.add(any);
            } else if (key instanceof Boolean) {
                BoolAny any = TraceFactory.eINSTANCE.createBoolAny();
                any.setValue(((Boolean) key).booleanValue());
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


    final void addFunction(String name, Function function) {
        if (funcMap.containsKey(name)) {
            throw new IllegalArgumentException("A Function with named " + name + " is already registered.");
        }
        funcMap.put(name, function);
    }

    final Function getFunction(String name) {
        return (Function) funcMap.get(name);
    }

}
