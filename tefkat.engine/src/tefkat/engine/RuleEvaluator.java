/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *     Anna Gerber
 *
 *
 */

package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import tefkat.engine.TargetResolver.Injections;
import tefkat.model.Var;
import tefkat.model.Extent;
import tefkat.model.Injection;
import tefkat.model.MofInstance;
import tefkat.model.NotTerm;
import tefkat.model.PatternDefn;
import tefkat.model.TRule;
import tefkat.model.TefkatException;
import tefkat.model.TefkatFactory;
import tefkat.model.Term;
import tefkat.model.Transformation;
import tefkat.model.VarScope;
import tefkat.model.internal.ModelUtils;


public class RuleEvaluator {

    private static final boolean ONE_TREE = true;

    private final Evaluator exprEval;

    final SourceResolver srcResolver;

    final TargetResolver tgtResolver;

    private final Extent trackingExtent;

    private final Binding _context;

    private final Map evalCache;

    private final Map patternCache;

    protected Map nameMap;

    protected List listeners;

    boolean INCREMENTAL = false;

    private volatile boolean isInterrupted = false;

    private volatile boolean stepMode = false;

    private volatile int returnMode = 0;

    private volatile int step = 0;
    
    private int depth = 0;

    // Used to manage simple coarse-grain fix-point evaluation
    private TRule currentRule;

    final private Map trackingQueryMap = new HashMap();

    final private Set trackingUpdateSet = new HashSet();

    final private Set breakpoints = new HashSet();
    
    final private List unresolvedTrees = new ArrayList();
    
    final Injections injections = new Injections();


    /**
     *  
     */
    RuleEvaluator(Binding context, Extent trackingExtent,
            Map nameMap, List listeners) {

        // TODO check for null target model, either here or in the engine

        this.trackingExtent = trackingExtent;
        this.listeners = listeners;
        this._context = context;
        this.nameMap = nameMap;

        exprEval = new Evaluator(this);
        srcResolver = new SourceResolver(this);
        tgtResolver = new TargetResolver(this, null, listeners);
        evalCache = new HashMap();
        patternCache = new HashMap();
    }

//    Tree getUnresolvedTree() {
//        if (unresolvedTrees.size() > 0) {
//            return (Tree) unresolvedTrees.remove(0);
//        } else {
//            return null;
//        }
//    }
    
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

    /**
     * @return
     */
    public Evaluator getEvaluator() {
        return exprEval;
    }

    public void runTransformation(Transformation transformation, boolean force)
            throws TefkatException {
        if (INCREMENTAL) {
            runIncrementalTransformation(transformation, force);
        } else {
            runFixpointTransformation(transformation, force);
        }
    }
    
    void runFixpointTransformation(Transformation transformation, boolean force)
            throws TefkatException {

        try {
            buildMaps(transformation);
            
            fireInfo("Constructing stratification...");
            List[] strata = transformation.getStrata();
            fireInfo("... " + strata.length + " levels.");

            for (int i = 0; i < strata.length; i++) {
                fireInfo("Stratum " + i + " : " + formatStrata(strata[i]));
                fixpoint(strata[i], force);
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
            throw e;
        }
    }
    
    void runIncrementalTransformation(Transformation transformation, boolean force)
            throws TefkatException {

        try {
            buildMaps(transformation);
            
            fireInfo("Constructing stratification...");
            List[] strata = transformation.getStrata();
            fireInfo("... " + strata.length + " levels.");
            
            for (int level = 0; level < strata.length; level++) {
                fireInfo("Stratum " + level + " : " + formatStrata(strata[level]));
                
                // Currently, we use a single Tree per stratum which means
                // that it's really a forest with one root Node per TRule
                //
                Tree tree;
                if (ONE_TREE) {
                    tree = new Tree(null, null, _context, trackingExtent, false);
                    tree.setLevel(level);
                    
                    addUnresolvedTree(tree);
                }
                
                for (final Iterator itr = strata[level].iterator(); itr.hasNext(); ) {
                    Object scope = itr.next();
                    
                    if (scope instanceof TRule) {
                        TRule tRule = (TRule) scope;
                        
                        if (!tRule.isAbstract()) {
                            if (!ONE_TREE) {
                                tree = new Tree(null, null, _context, trackingExtent, false);
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
    
    private String formatStrata(List<VarScope> strata) {
        final StringBuilder sb = new StringBuilder();
        for (VarScope s: strata) {
            if (s instanceof TRule && !((TRule) s).isAbstract()) {
                sb.append(", ").append(s.getName());
            }
        }
        return sb.length() > 0 ? sb.substring(2) : "";
    }

    private void fixpoint(List tRules, boolean force) throws ResolutionException {
        trackingQueryMap.clear();
        while (tRules.size() > 0) {
            trackingUpdateSet.clear();

            for (TRule tRule = selectTRule(tRules); null != tRule; tRule = selectTRule(tRules)) {
                tRules.remove(tRule);
                if (!tRule.isAbstract()) {
                    if (force) {
                        try {
                            evaluate(tRule);
                        } catch (ResolutionException e) {
                            fireError(e);
                        }
                    } else {
                        evaluate(tRule);
                    }
                }
            }

            for (final Iterator itr = trackingUpdateSet.iterator(); itr.hasNext(); ) {
                Object key = itr.next();    // key is an updated tracking (E)Class
                Set[] scopes = (Set []) trackingQueryMap.get(key);
                if (null != scopes) {
                    fireInfo("Will re-evaluate rule(s): " + scopes[0]);
                    tRules.addAll(scopes[0]);
                    for (final Iterator ruleItr = scopes[0].iterator(); ruleItr.hasNext(); ) {
                        TRule rule = (TRule) ruleItr.next();
                        evalCache.remove(rule);
                    }
                    for (final Iterator patternItr = scopes[1].iterator(); patternItr.hasNext(); ) {
                        PatternDefn pDefn = (PatternDefn) patternItr.next();
                        if (patternCache.containsKey(pDefn)) {
                            fireInfo("\t clearing cache of " + pDefn);
                            patternCache.remove(pDefn);
                        }
                    }
                }
            }
        }
    }

    private void evaluate(TRule trule) throws ResolutionException {
        // Only evaluate rules once
        if (evalCache.containsKey(trule)) {
            fireInfo("Using cached results for " + trule.getName());
            fireEvaluateRule(trule, _context, true);
            return;
        }
        fireEvaluateRule(trule, _context, false);
        doEvaluate(trule, _context);
    }

    private void incrementalEvaluate(final TRule trule, final Tree tree)
    throws ResolutionException {
        // Only evaluate rules once
        if (evalCache.containsKey(trule)) {
            fireInfo("Using cached results for " + trule.getName());
            fireEvaluateRule(trule, _context, true);
            return;
        }
        fireEvaluateRule(trule, _context, false);

        Collection goal = trule.getGoal();
        Collection ruleContexts = generateContexts(trule, _context);

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

    final protected void pause() {
        synchronized (unresolvedTrees) {
            stepMode = true;
        }
    }
    
    final protected void step() {
        synchronized (unresolvedTrees) {
            step++;
            unresolvedTrees.notifyAll();
        }
    }
    
    final protected void stepReturn() {
        synchronized (unresolvedTrees) {
            returnMode = depth;
            resume();
        }
    }
    
    final protected void resume() {
        synchronized (unresolvedTrees) {
            stepMode = false;
            unresolvedTrees.notifyAll();
        }
    }
    
    final private void breakpoint(Term t) {
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
    
    final private void waitStep() {
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
                final TefkatListener listener = (TefkatListener) itr.next();
                listener.error(message, t);
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

//    protected void fireResourceLoaded(Resource res) {
//        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
//            try {
//                ((TefkatListener) itr.next()).resourceLoaded(res);
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//        }
//    }

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
        currentRule = rule;
        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).evaluateRule(rule, context,
                        cached);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    //    private void fireEvaluateSource(TRule rule, Binding context) {
    //        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
    //            ((TefkatListener) itr.next()).evaluateSource(rule, context);
    //        }
    //    }
    //
    //    private void fireEvaluateTarget(TRule rule, Binding context) {
    //        for (Iterator itr = listeners.iterator(); itr.hasNext();) {
    //            ((TefkatListener) itr.next()).evaluateTarget(rule, context);
    //        }
    //    }
    
    private void doEvaluate(TRule trule, Binding context)
            throws ResolutionException {
        Collection truleSolutions = new HashSet();

        Collection goal = trule.getGoal();

        //        System.out.println(trule.getName() + ": S " + getSourceTerms(trule));
        //        System.out.println(trule.getName() + ": O " +
        // getOverrideTerms(trule));
        //        System.out.println(trule.getName() + ": T " + getTargetTerms(trule));
        //        System.out.println(trule.getName() + ": B " + getBinding(bindingMap,
        // trule));

        Collection ruleContexts = generateContexts(trule, context);

        for (Iterator itr = ruleContexts.iterator(); itr.hasNext();) {
            Binding ruleContext = (Binding) itr.next();
            //            System.out.println(trule.getName() + ": C " + ruleContext);

            Node root = new Node(goal, ruleContext);
            Tree tree = new Tree(null, root, context, trackingExtent, false);

            resolveNode(tree);

            if (tree.isSuccess()) {
                HashSet vars = new HashSet(trule.getVars());
                vars.addAll(ruleContext.keys()); // Add the extent vars to the
                                                 // vars provided to solutions

                truleSolutions.addAll(tree.getAnswers());
            } else {
                
                System.err.println(tree.getUnresolvedNode());
                
                fireInfo("TRule: " + trule.getName() + " matched nothing.");
            }
        }

        // record the results for later use by extending TRules
        evalCache.put(trule, truleSolutions);
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
                        
                        Node flounder = tree.flounder(node.getDelayReasons());
                        
                        if (null != flounder) {
                            // Don't continue with this tree - it's obsolete
                            unresolvedTrees.remove(tree);
                            fireDelayTerm(flounder);
                        } else {
                            throw new ResolutionException(node,
                                    "Floundered - all terms are delayed: "
                                    + formatDelayedNoded(node));
                        }
                    }

                    fireExitTerm(node);
                } else {
                    //  Select a literal for node.
                    //
                    Term literal = selectLiteral(node);

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
                        if (literal.isTarget()) {
                            tgtResolver.doResolveNode(new Context(this, exprEval, tree, node), literal);
                        } else {
                            srcResolver.doResolveNode(new Context(this, exprEval, tree, node), literal);
                        }

                        fireExitTerm(node);

                    } catch (NotGroundException e) {
                        // fireInfo("delaying: " + literal + " : " + e);
                        if (node.selectedLiteral() != literal) {
                            throw new AssertionError(
                                "Internal Error: inconsistent state, please report this problem to the developers.");
                        }
                        node.delay(e);
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
    

    private String formatDelayedNoded(final Node node) {
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

    /**
     * Grow the tree from the given node.
     * 
     * @param node
     *            The node from which to grow the tree.
     * @param isNegation
     *            True iff the node is in a negation tree.
     * @return True iff this node or one of its (transitive) children is a
     *         success node.
     */
    protected void resolveNode(final Tree tree) throws ResolutionException {

        try {
            depth++;
            fireEnterTree(tree);

            // Iterate over all unresolved nodes until there are none left
            // (or the interrupted flag is set)
            //
            for (Node node = tree.getUnresolvedNode();
                    !isInterrupted && !tree.isCompleted() && null != node;
                    node = tree.getUnresolvedNode()) {

                //  Is the goal already a success?
                //
                if (node.goal().isEmpty()) {

//                    if (stepMode) {
//                        waitStep();
//                    }

                    fireEnterTerm(node);

                    if (null == node.getDelayed() || node.getDelayed().isEmpty()) {
                        tree.success(node);
                    } else {
                        throw new ResolutionException(node,
                                "Floundered - all terms are delayed: "
                                        + node.getDelayed() + "\t"
                                        + node.getBindings());
                    }

                    fireExitTerm(node);
                } else {
                    //  Select a literal for node.
                    //
                    Term literal = selectLiteral(node);

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
                        if (literal.isTarget()) {
                            tgtResolver.doResolveNode(new Context(this, exprEval, tree, node), literal);
                        } else {
                            srcResolver.doResolveNode(new Context(this, exprEval, tree, node), literal);
                        }

                        fireExitTerm(node);

                    } catch (NotGroundException e) {
                        // fireInfo("delaying: " + literal + " : " + e);
                        if (node.selectedLiteral() != literal) {
                            throw new AssertionError(
                                    "Internal Error: inconsistent state, please report this problem to the developers.");
                        }
                        node.delay(e);
                        tree.addUnresolvedNode(node);
                        fireDelayTerm(node);
                    }

                }
            }

       } finally {
            if (returnMode >= depth) {
                returnMode = 0;
                pause();
                // waitStep();
            }

            fireExitTree(tree);
            depth--;
        }
    }

    /**
     * Choose a literal from the goal of the given node.
     * 
     * @param node
     *            The node containing the goal from which to choose a literal.
     * @return A chosen literal, or null if the node's goal is empty (i.e.
     *         success)
     * @throws ResolutionException 
     */
    private Term selectLiteral(Node node) throws ResolutionException {
        Term[] literals = (Term[]) node.goal().toArray(new Term[node.goal().size()]);

        // Simple selection rule:
        //    + select non-target, non-negation terms first
        //    + select non-target terms next
        //    + select Injections next
        //    + select target MofInstances next
        //    + select anything else (target terms) last
        //
        for (int i = 0; i < literals.length; i++) {
            if (!(literals[i].isTarget() || literals[i] instanceof NotTerm)) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (!literals[i].isTarget()) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }
        
        if (null != node.getDelayed() && !node.getDelayed().isEmpty()) {
            throw new ResolutionException(node, "Flounder: All source terms delayed: "
                                                + formatDelayedNoded(node));
        }

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof Injection) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof MofInstance) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        if (literals.length > 0) {
            node.selectLiteral(literals[0]);
            return literals[0];
        }

        throw new ResolutionException(node,
                "Could not select a valid literal from goal: " + node.goal());
    }

    //    private String formatBinding(Binding solution) {
    //        String s = "";
    //        for (Iterator itr = solution.entrySet().iterator(); itr.hasNext();) {
    //            Map.Entry es = (Map.Entry) itr.next();
    //            Var k = (Var) es.getKey();
    //            s += "\n\t" + k.getScope().getName() + "." + k.getName() + " -> ";
    //            Object v = es.getValue();
    //            if (v instanceof Var) {
    //                Var av = (Var) v;
    //                s += av.getScope().getName() + "." + av.getName();
    //            } else if (v instanceof WrappedVar) {
    //                Var av = ((WrappedVar) v).getVar();
    //                s += "W(" + av.getScope().getName() + "." + av.getName() + ")";
    //            } else {
    //                s += v;
    //            }
    //        }
    //        return s;
    //    }

    // ================================================================================
    // Utility methods for constructing suitable goals that account for the
    // extends and supersedes associations.
    //

    /**
     * Stores a map to the transitive closure of the extends references.
     */
    private Map extRulesMap;

    /**
     * Stores a map to the (inverted) supersedes references.
     */
    private Map invertedSupMap;

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
    private void buildMaps(Transformation tr) throws ResolutionException {
        extRulesMap = new HashMap();
        invertedSupMap = new HashMap();

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

    private TRule selectTRule(List rules) {
        while (rules.size() > 0) {
            Object scope = rules.get(0);
            if (scope instanceof TRule) {
                return (TRule) scope;
            } else {
                // Now that we're doing stratification, rules may include
                // PatternDefns so we need to filter them out
                rules.remove(0);
            }
        }
        return null;
    }

    private Collection generateContexts(TRule trule, Binding context)
            throws ResolutionException {
        Collection contextSet = new HashSet();
        Binding ruleContext = new Binding(context);
        ruleContext.composeRight(getExtendsBinding(trule));
        ruleContext.composeRight(getOverrideBinding(trule));
        contextSet.add(ruleContext);

        return contextSet;
    }

    static class TestBuildMaps {
        public static void main(String[] args) throws ResolutionException {
            Transformation t = TefkatFactory.eINSTANCE.createTransformation();

            TRule r1 = TefkatFactory.eINSTANCE.createTRule();
            r1.setTransformation(t);
            r1.setName("R1");
            Var v1 = TefkatFactory.eINSTANCE.createVar();
            v1.setScope(r1);
            v1.setName("v1");
            r1.setSrc(mi(v1, "Src1"));
            r1.getTgt().add(mi(v1, "Tgt1"));

            TRule r2 = TefkatFactory.eINSTANCE.createTRule();
            r2.setTransformation(t);
            r2.setName("R2");
            Var v2 = TefkatFactory.eINSTANCE.createVar();
            v2.setScope(r2);
            v2.setName("v2");
            v2.getExtended().add(v1);
            r2.setSrc(mi(v2, "Src2"));
            r2.getTgt().add(mi(v2, "Tgt2"));
            r2.getExtended().add(r1);

            TRule r3 = TefkatFactory.eINSTANCE.createTRule();
            r3.setTransformation(t);
            r3.setName("R3");
            Var v3 = TefkatFactory.eINSTANCE.createVar();
            v3.setScope(r3);
            v3.setName("v3");
            v3.getSuperseded().add(v1);
            r3.setSrc(mi(v3, "Src3"));
            r3.getTgt().add(mi(v3, "Tgt3"));
            r3.getSuperseded().add(r1);

            TRule r4 = TefkatFactory.eINSTANCE.createTRule();
            r4.setTransformation(t);
            r4.setName("R4");
            Var v4 = TefkatFactory.eINSTANCE.createVar();
            v4.setScope(r4);
            v4.setName("v4");
            v4.getExtended().add(v3);
            r4.setSrc(mi(v4, "Src4"));
            r4.getTgt().add(mi(v4, "Tgt4"));
            r4.getExtended().add(r3);

            RuleEvaluator re = new RuleEvaluator(null, null, Collections.EMPTY_MAP, Collections.EMPTY_LIST);

            re.buildMaps(t);

//            System.out.println("s1prime: " + re.getSourceTerms(r1));
//            System.out.println("t1prime: " + re.getTargetTerms(r1));
//            System.out.println("over1  : " + re.getOverrideTerms(r1));
//            System.out.println();
//
//            System.out.println("s2prime: " + re.getSourceTerms(r2));
//            System.out.println("t2prime: " + re.getTargetTerms(r2));
//            System.out.println("over2  : " + re.getOverrideTerms(r2));
//            System.out.println();
//
//            System.out.println("s3prime: " + re.getSourceTerms(r3));
//            System.out.println("t3prime: " + re.getTargetTerms(r3));
//            System.out.println("over3  : " + re.getOverrideTerms(r3));
//            System.out.println();
//
//            System.out.println("s4prime: " + re.getSourceTerms(r4));
//            System.out.println("t4prime: " + re.getTargetTerms(r4));
//            System.out.println("over4  : " + re.getOverrideTerms(r4));
//            System.out.println();

            Binding b1 = new Binding(re.getExtendsBinding(r1));
            b1.composeRight(re.getOverrideBinding(r1));
            Binding b2 = new Binding(re.getExtendsBinding(r2));
            b2.composeRight(re.getOverrideBinding(r2));
            Binding b3 = new Binding(re.getExtendsBinding(r3));
            b3.composeRight(re.getOverrideBinding(r3));
            Binding b4 = new Binding(re.getExtendsBinding(r4));
            b4.composeRight(re.getOverrideBinding(r4));

            System.out.println("bind1  : " + b1);// + "\t" +
                                                 // re.sPrimeBinding(r1) + "\t"
                                                 // + re.overBinding(r1));
            System.out.println("bind2  : " + b2);// + "\t" +
                                                 // re.sPrimeBinding(r2) + "\t"
                                                 // + re.overBinding(r2));
            System.out.println("bind3  : " + b3);// + "\t" +
                                                 // re.sPrimeBinding(r3) + "\t"
                                                 // + re.overBinding(r3));
            System.out.println("bind4  : " + b4);// + "\t" +
                                                 // re.sPrimeBinding(r4) + "\t"
                                                 // + re.overBinding(r4));
        }

        private static MofInstance mi(Var v, String type) {
            MofInstance mi = TefkatFactory.eINSTANCE.createMofInstance();
            tefkat.model.StringConstant t = TefkatFactory.eINSTANCE
                    .createStringConstant();
            t.setRepresentation(type);
            tefkat.model.VarUse vu = TefkatFactory.eINSTANCE
                    .createVarUse();
            vu.setVar(v);
            mi.setTypeName(t);
            mi.setInstance(vu);
            return mi;
        }
    }

    /**
     * Records a TRule's interest in a given tracking class for fix-point
     * computation.
     * 
     * @param trackingClass
     * @param callback
     */
    void trackingQuery(EClass trackingClass, TrackingCallback callback) {
        if (INCREMENTAL) {
            List callbacks = (List) trackingQueryMap.get(trackingClass);
            if (null == callbacks) {
                callbacks = new ArrayList();
                trackingQueryMap.put(trackingClass, callbacks);
            }
            callbacks.add(callback);
        } else {
            Set[] scopes = (Set[]) trackingQueryMap.get(trackingClass);
            if (null == scopes) {
                scopes = new Set[] {new HashSet(), new HashSet()};
                trackingQueryMap.put(trackingClass, scopes);
            }
            scopes[0].add(currentRule);
            scopes[1].addAll(patternStack);
        }
    }

    Map tcCache = new HashMap();
    
    /**
     * Records that an instance of a given tracking class was created.
     * 
     * @param trackingClass
     * @throws NotGroundException
     * @throws ResolutionException
     */
    void trackingCreate(EClass trackingClass, EObject instance) throws ResolutionException, NotGroundException {
        if (INCREMENTAL) {
            updateTrackingCache(trackingClass, instance);
            for (Iterator itr = trackingClass.getEAllSuperTypes().iterator(); itr.hasNext(); ) {
                updateTrackingCache((EClass) itr.next(), instance);
            }
            
            List callbacks = (List) trackingQueryMap.get(trackingClass);
            if (null != callbacks) {
                for (Iterator itr = callbacks.iterator(); itr.hasNext(); ) {
                    TrackingCallback callback = (TrackingCallback) itr.next();
                    callback.handleInstance(instance);
//                    int count = srcResolver.callCount.get(TefkatPackage.Literals.TRACKING_USE);
//                    count++;
//                    srcResolver.callCount.put(TefkatPackage.Literals.TRACKING_USE, count);
                }
            }
        } else {
            trackingUpdateSet.add(trackingClass);
        }
    }

    private void updateTrackingCache(EClass trackingClass, EObject instance) {
        getTrackingCache(trackingClass).add(instance);
    }

    List getTrackingCache(EClass trackingClass) {
        List list = (List) tcCache.get(trackingClass);
        if (null == list) {
            list = new ArrayList();
            tcCache.put(trackingClass, list);
        }
        return list;
    }

    final private Map featureOrderings = new HashMap();

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
                final EStructuralFeature feature = ModelUtils.getFeature(inst.eClass(), feat);
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
    
//    private static class TestSort {
//        public static void main(String[] args) {
//            EClass inst = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass();
//            String feat = "eStructuralFeatures";
//            EStructuralFeature[] a = {org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEReference(),
//                              org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEReference(),
//                              org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAttribute(),
//                              org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAttribute(),
//                              org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAttribute(),
//            };
//            a[0].setName("zero");
//            a[1].setName("one");
//            a[2].setName("two");
//            a[3].setName("three");
//            a[4].setName("four");
//            inst.getEStructuralFeatures().addAll(java.util.Arrays.asList(a));
//            
//            RuleEvaluator re = new RuleEvaluator(null, null, Collections.EMPTY_MAP, Collections.EMPTY_LIST);
//            
//            re.addPartialOrder(inst, feat, a[4], a[3]);
//            re.addPartialOrder(inst, feat, a[3], a[2]);
//            re.addPartialOrder(inst, feat, a[2], a[1]);
//            re.addPartialOrder(inst, feat, a[1], a[0]);
//
//            re.addPartialOrder(inst, feat, a[4], "foo");    // Should cause a resolution exception below
//
//            try {
//                for (final Iterator itr = inst.getEStructuralFeatures().iterator(); itr.hasNext(); ) {
//                    System.out.println(((EStructuralFeature) itr.next()).getName());
//                }
//                re.topologicalSort();
//                System.out.println("-----------------");
//                for (final Iterator itr = inst.getEStructuralFeatures().iterator(); itr.hasNext(); ) {
//                    System.out.println(((EStructuralFeature) itr.next()).getName());
//                }
//            } catch (ResolutionException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    final private Stack patternStack = new Stack();
    
    /**
     * @param defn
     */
    void pushPattern(PatternDefn defn) {
        patternStack.push(defn);
    }
    
    /**
     * 
     */
    void popPattern() {
        patternStack.pop();
    }

    /**
     * @param term
     * @return
     */
    public Map getPatternCache(Term term) {
        Map cache = (Map) patternCache.get(term);
        if (null == cache) {
            cache = new HashMap();
            patternCache.put(term, cache);
        }
        return cache;
    }
    
}
