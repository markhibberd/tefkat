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
import java.util.Arrays;
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
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.model.AbstractVar;
import tefkat.model.CompoundTerm;
import tefkat.model.Extent;
import tefkat.model.IfTerm;
import tefkat.model.Injection;
import tefkat.model.MofInstance;
import tefkat.model.NotTerm;
import tefkat.model.PatternDefn;
import tefkat.model.PatternUse;
import tefkat.model.TRule;
import tefkat.model.TRuleVar;
import tefkat.model.TargetTerm;
import tefkat.model.TefkatFactory;
import tefkat.model.Term;
import tefkat.model.TrackingUse;
import tefkat.model.Transformation;
import tefkat.model.VarScope;
import tefkat.model.internal.Util;
import tefkat.model.util.TefkatSwitch;


public class RuleEvaluator {

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

    private transient boolean isInterrupted = false;

    private transient boolean stepMode = false;

    private transient int returnMode = 0;

    private transient int step = 0;
    
    private int depth = 0;

    // Used to manage simple coarse-grain fix-point evaluation
    private TRule currentRule;

    private Map trackingQueryMap = new HashMap();

    private Set trackingUpdateSet = new HashSet();

    private Set breakpoints = new HashSet();
    
    private final List unresolvedTrees = new ArrayList();

    /**
     *  
     */
    public RuleEvaluator(Binding context, Extent trackingExtent,
            Collection allResources, List listeners) {

        // TODO check for null target model, either here or in the engine

        this.trackingExtent = trackingExtent;
        this.listeners = listeners;
        this._context = context;

        // Get all the transitively referenced Resources
        allResources = findAllResources(allResources);
        nameMap = Util.buildNameMaps(allResources);

        exprEval = new Evaluator(this);
        srcResolver = new SourceResolver(this, listeners);
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
            throws ResolutionException {
        if (INCREMENTAL) {
            runIncrementalTransformation(transformation, force);
        } else {
            runFixpointTransformation(transformation, force);
        }
    }
    
    public void runFixpointTransformation(Transformation transformation, boolean force)
            throws ResolutionException {

        try {
            buildMaps(transformation);
            
            List[] strata = stratify(transformation.getTRule(), transformation.getPatternDefn());

            for (int i = 0; i < strata.length; i++) {
                fireInfo("Stratum " + i + " : " + strata[i]);
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
    
    public void runIncrementalTransformation(Transformation transformation, boolean force)
            throws ResolutionException {

        try {
            buildMaps(transformation);
            
            List[] strata = stratify(transformation.getTRule(), transformation.getPatternDefn());

            for (int level = 0; level < strata.length; level++) {
                fireInfo("Stratum " + level + " : " + strata[level]);

                // Currently, we use a single Tree per stratum which means
                // that it's really a forest with one root Node per TRule
                //
		Tree tree = new Tree(transformation, null, _context, trackingExtent, false);
		tree.setLevel(level);

		addUnresolvedTree(tree);

                for (final Iterator itr = strata[level].iterator(); itr.hasNext(); ) {
                    Object scope = itr.next();

                    if (scope instanceof TRule) {
                        TRule tRule = (TRule) scope;

                        if (!tRule.isAbstract()) {
			    incrementalEvaluate(tRule, tree);
                        }
                    }
                }

                while (unresolvedTrees.size() > 0) {
                    try {
                        resolve();
//			System.err.println("completing trees...");
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
                            // TODO check this out
                            Tree cTree = (Tree) unresolvedTrees.remove(0);
                            System.err.print(cTree + "\t" + cTree.getLevel() + " - FORCED");
                            cTree.completed();
                        } else {
//			    System.err.println("Min level: " + minLevel);
			    for (Iterator itr = done.iterator(); itr.hasNext(); ) {
				Tree cTree = (Tree) itr.next();
//				System.err.println(cTree + " " + cTree.isNegation() + "\t" + cTree.getLevel());
				unresolvedTrees.remove(cTree);
				cTree.completed();
			    }
			}
//                        System.err.println(" #trees = " + unresolvedTrees.size());
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

    // FIXME handle inheritance of tracking classes
    private List[] stratify(List rules, List patterns) throws ResolutionException {
        fireInfo("Constructing stratification...");
        
        final IntMap levelMapping = new IntMap(2 * (rules.size() + patterns.size()) + 1);

        final Stratifier stratifier = new Stratifier();
        
        for (final Iterator ruleItr = rules.iterator(); ruleItr.hasNext(); ) {
            TRule rule = (TRule) ruleItr.next();
            Collection goal = getGoal(rule);
            
            levelMapping.put(rule, 0);
            
            for (final Iterator termItr = goal.iterator(); termItr.hasNext(); ) {
                Term term = (Term) termItr.next();
                stratifier.check(rule, term);
            }
        }
        
        for (final Iterator patternItr = patterns.iterator(); patternItr.hasNext(); ) {
            PatternDefn pattern = (PatternDefn) patternItr.next();

            levelMapping.put(pattern, 0);
            
            stratifier.check(pattern, pattern.getTerm());
        }
        
        List less = new ArrayList();
        List lesseq = new ArrayList();

//        fireInfo("    finding dependencies...");
        
        // FIXME - handle subtyping and stratification
        
        for (final Iterator itr = stratifier.readers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                lesseq.add(new Object[] {tc, scope});
//                System.out.println(tc + " <= " + scope);
            }
        }

        for (final Iterator itr = stratifier.writers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                lesseq.add(new Object[] {scope, tc});
//                System.out.println(scope + " <= " + tc);
            }
        }

        for (final Iterator itr = stratifier.neg_readers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                less.add(new Object[] {tc, scope});
//                System.out.println(tc + " < " + scope);
            }
        }

        for (final Iterator itr = stratifier.neg_writers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                less.add(new Object[] {tc, scope});
//                System.out.println(tc + " < " + scope);
            }
        }

//        fireInfo("    populating strata...");

        boolean done = false;
        
        int maxLevel = 0;
        for (boolean acyclic = true; !done && acyclic && maxLevel <= levelMapping.size(); ) {
            done = true;
            acyclic = false;
            for (final Iterator lItr = less.iterator(); lItr.hasNext(); ) {
                Object[] pair = (Object[]) lItr.next();
                int lidx = levelMapping.get(pair[0]);
                int gidx = levelMapping.get(pair[1]);
                if (lidx >= gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " >= " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + (lidx + 1));
                    levelMapping.put(pair[1], lidx + 1);
                    if (lidx >= maxLevel) {
                        maxLevel = lidx + 1;
                    }
//                    levelMapping.put(pair[1], maxLevel);
                    done = false;
                }
                acyclic |= (0 == lidx);
            }
            
            for (final Iterator lItr = lesseq.iterator(); lItr.hasNext(); ) {
                Object[] pair = (Object[]) lItr.next();
                int lidx = levelMapping.get(pair[0]);
                int gidx = levelMapping.get(pair[1]);
                if (lidx > gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " > " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + lidx);
                    levelMapping.put(pair[1], lidx);
                    if (lidx > maxLevel) {
                        maxLevel = lidx;
                    }
//                    levelMapping.put(pair[1], maxLevel);
                    done = false;
                }
                acyclic |= (0 == lidx);
            }
        }
        
        if (!done) {
            String s = orderString(levelMapping, less, lesseq);
            throw new ResolutionException(null, "Rules are not stratifiable: max level = " + maxLevel + s);
        }
        
        List[] strata = new List[maxLevel + 1];
        for (int level = 0; level < strata.length; level++) {
            strata[level] = new ArrayList();
        }
        
        for (final Iterator itr = rules.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.get(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }
        
        for (final Iterator itr = patterns.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.get(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }

        fireInfo("... " + strata.length + " levels.");
        
        return strata;
    }
    
    private String orderString(final IntMap levelMapping, List less, List lesseq) {
        StringBuffer sb = new StringBuffer();
        for (final Iterator lItr = less.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.get(pair[0]);
            int gidx = levelMapping.get(pair[1]);
            if (true || lidx >= gidx) {
                sb.append(", ");
                sb.append(getName(pair[0]));
                sb.append(" < ");
                sb.append(getName(pair[1]));
            }
        }
        for (final Iterator lItr = lesseq.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.get(pair[0]);
            int gidx = levelMapping.get(pair[1]);
            if (true || lidx > gidx) {
                sb.append(", ");
                sb.append(getName(pair[0]));
                sb.append(" <= ");
                sb.append(getName(pair[1]));
            }
        }
        return sb.toString();
    }
    
    private String getName(Object obj) {
        if (obj instanceof EClass) {
            return ((EClass) obj).getName();
        } else if (obj instanceof VarScope) {
            return ((VarScope) obj).getName();
        } else {
            return String.valueOf(obj);
        }
    }

    static class Stratifier extends TefkatSwitch {
        final Map readers = new HashMap();
        final Map writers = new HashMap();
        final Map neg_readers = new HashMap();
        final Map neg_writers = new HashMap();
        
        boolean negated = false;
        VarScope scope;
        
        public void check(VarScope scope, Term term) {
            this.negated = false;
            this.scope = scope;
            
            check(term);
        }
        
        /* (non-Javadoc)
         * @see tefkat.model.util.TefkatSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
         */
//        public Object doSwitch(EObject theEObject) {
//            System.out.println(theEObject);
//            return super.doSwitch(theEObject);
//        }

        private void store(Map map, Object key) {
            if (!map.containsKey(key)) {
                map.put(key, new HashSet());
            }
            Set set = (Set) map.get(key);
            set.add(scope);
        }
        
        private Object check(Term term, boolean newNegated) {
            boolean oldNegated = negated;
            negated = newNegated;
            Object result = check(term);
            negated = oldNegated;
            return result;
        }

        private Object check(Term term) {
            return doSwitch(term);
        }

        /* (non-Javadoc)
         * @see tefkat.model.util.TefkatSwitch#caseNotTerm(tefkat.model.NotTerm)
         */
        public Object caseNotTerm(NotTerm object) {
            return check((Term) object.getTerm().get(0), true);
        }
        
        /* (non-Javadoc)
         * @see tefkat.model.util.TefkatSwitch#caseIfTerm(tefkat.model.IfTerm)
         */
        public Object caseIfTerm(IfTerm object) {
            check((Term) object.getTerm().get(1));
            check((Term) object.getTerm().get(2));
            return check((Term) object.getTerm().get(0), true);
        }
        
        /**
         * Return the Set of dependency items to add the current scope (TRule or PatternDefn) to.
         * 
         * @see tefkat.model.util.TefkatSwitch#caseTrackingUse(tefkat.model.TrackingUse)
         */
        public Object caseTrackingUse(TrackingUse object) {
            EClass key = object.getTracking();
            List keys = null;
            Map map;
            if (negated) {
                if (isTarget(object)) {
                    map = neg_writers;
                    // Affects all subclasses as well
                } else {
                    map = neg_readers;
                    // Affected by all superclasses as well
                    keys = key.getEAllSuperTypes();
                }
            } else {
                if (isTarget(object)) {
                    map = writers;
                    // Affects all subclasses as well
                } else {
                    map = readers;
                    // Affected by all superclasses as well
                    keys = key.getEAllSuperTypes();
                }
            }
            store(map, key);
            if (null != keys) {
                for (Iterator itr = keys.iterator(); itr.hasNext(); ) {
                    store(map, itr.next());
                }
            }
            return this;
        }
        
        /* (non-Javadoc)
         * @see tefkat.model.util.TefkatSwitch#casePatternUse(tefkat.model.PatternUse)
         */
        public Object casePatternUse(PatternUse object) {
            PatternDefn key = object.getDefn();
            if (null == key) {
                // special case for "println"
                return null;
            }
            Map map;
            if (negated) {
                if (isTarget(object)) {
                    map = neg_writers;
                } else {
                    map = neg_readers;
                }
            } else {
                if (isTarget(object)) {
                    map = writers;
                } else {
                    map = readers;
                }
            }
            store(map, key);
            return this;
        }
        
        /* (non-Javadoc)
         * @see tefkat.model.util.TefkatSwitch#caseCompoundTerm(tefkat.model.CompoundTerm)
         */
        public Object caseCompoundTerm(CompoundTerm object) {
            List terms = object.getTerm();
            for (final Iterator termItr = terms.iterator(); termItr.hasNext(); ) {
                Term term = (Term) termItr.next();
                check(term);
            }
            return null;
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

        Collection goal = getGoal(trule);
        Collection ruleContexts = generateContexts(trule, _context);

	// FIXME - no rule caching any more
        final Collection truleSolutions = new HashSet();
        
        for (Iterator itr = ruleContexts.iterator(); itr.hasNext();) {
            final Binding ruleContext = (Binding) itr.next();

	    tree.createBranch(null, ruleContext, goal);
            
            tree.addTreeListener(new TreeListener() {

                public void solution(Node node) throws ResolutionException {
                }

                public void completed(Tree tree) {
                    if (tree.isSuccess()) {
                        fireInfo("TRule: " + trule.getName() + " completed.");
                    } else {
                        fireInfo("TRule: " + trule.getName() + " matched nothing.");
                    }
                }

            
            });
        }

        // record the results for later use by extending TRules
        evalCache.put(trule, truleSolutions);
    }

    synchronized final protected void pause() {
        stepMode = true;
    }

    synchronized final protected void step() {
        step++;
        notify();
    }

    synchronized final protected void stepReturn() {
        returnMode = depth;
        resume();
    }

    synchronized final protected void resume() {
        stepMode = false;
        notify();
    }

    synchronized final private void breakpoint(Term t) {
        pause();
        fireBreakpoint(t);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized final private void waitStep() {
        while (stepMode && step < 1) {
            try {
                fireSuspend();
                if (step < 1) {
                    wait();
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

    private Collection getGoal(TRule trule) throws ResolutionException {
        Collection goal = new ArrayList();

        goal.addAll(getSourceTerms(trule));
        goal.addAll(getOverrideTerms(trule));
        goal.addAll(getTargetTerms(trule));
        
        return goal;
    }
    
    private void doEvaluate(TRule trule, Binding context)
            throws ResolutionException {
        Collection truleSolutions = new HashSet();

        Collection goal = getGoal(trule);

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
            Tree tree = new Tree(trule.getTransformation(), root, context, trackingExtent, false);

            resolveNode(tree);

            if (tree.isSuccess()) {
                HashSet vars = new HashSet(trule.getVars());
                vars.addAll(ruleContext.keys()); // Add the extent vars to the
                                                 // vars provided to solutions

                truleSolutions.addAll(tgtResolver.solutions(tree, vars));
            } else {
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
                Collection goal = node.goal();
                if (goal.isEmpty()) {

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
                    if (literal == null) {
                        throw new ResolutionException(node,
                                "Could not select a valid literal from goal: " + goal);
                    }

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
                        if (isTarget(literal)) {
                            tgtResolver.doResolveNode(tree, node, goal, literal, false);
                        } else {
                            srcResolver.doResolveNode(tree, node, goal, literal, false);
                        }

                        fireExitTerm(node);

                    } catch (NotGroundException e) {
                        // fireInfo("delaying: " + literal + " : " + e);
                        if (node.selectedLiteral() != literal) {
                            throw new AssertionError(
                                "Internal Error: inconsistent state, please report this problem to the developers.");
                        }
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
		    unresolvedTrees.remove(tree);
		}

                fireExitTree(tree);
                depth--;
            }
        }
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
    protected void resolveNode(Tree tree) throws ResolutionException {

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
                Collection goal = node.goal();
                if (goal.isEmpty()) {

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
                    if (literal == null) {
                        throw new ResolutionException(node,
                                "Could not select a valid literal from goal: " + goal);
                    }

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
                        if (isTarget(literal)) {
                            tgtResolver.doResolveNode(tree, node, goal, literal, false);
                        } else {
                            srcResolver.doResolveNode(tree, node, goal, literal, false);
                        }

                        fireExitTerm(node);

                    } catch (NotGroundException e) {
                        // fireInfo("delaying: " + literal + " : " + e);
                        if (node.selectedLiteral() != literal) {
                            throw new AssertionError(
                                    "Internal Error: inconsistent state, please report this problem to the developers.");
                        }
                        node.delay();
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
     */
    private Term selectLiteral(Node node) {
        Term[] literals = (Term[]) node.goal().toArray(new Term[0]);

        // Simple selection rule:
        //    + select non-target, non-negation terms first
        //    + select non-target terms next
        //    + select Injections next
        //    + select target MofInstances next
        //    + select anything else (target terms) last
        //
        for (int i = 0; i < literals.length; i++) {
            if (!(isTarget(literals[i]) || literals[i] instanceof NotTerm || literals[i] instanceof OverrideTerm)) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (!isTarget(literals[i])) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
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

        return null;
    }

    /**
     * A term is a "target" term if it's of the correct type and:
     * <ul>
     * <li> it's owned by a TRule via its "tgt" reference, or </li>
     * <li> it's owned by a PatternDefn for which "isSource" is false, or </li>
     * <li> it's not the condition of an IfTerm, and
     * it's owned by a CompoundTerm that is a "target" term.</li>
     * </ul>
     * 
     * Otherwise its either owned by a TRule via its "src" reference or by a
     * PatternDefn for which "isSource" is true or by a Query so it must be a
     * "source" term.
     * 
     * @param term
     * @return
     */
    private static boolean isTarget(Term term) {
        CompoundTerm parent = term.getCompoundTerm();
        return (term instanceof TargetTerm)
                && (((TargetTerm) term).getTRuleTgt() != null
                        || (term.getPatternDefn() != null && !term.getPatternDefn().isSource())
                        || (parent != null
                                && !(parent instanceof IfTerm &&
                                        ((IfTerm) parent).getTerm().get(0).equals(term))
                                && isTarget(parent)));
    }

    //    private String formatBinding(Binding solution) {
    //        String s = "";
    //        for (Iterator itr = solution.entrySet().iterator(); itr.hasNext();) {
    //            Map.Entry es = (Map.Entry) itr.next();
    //            AbstractVar k = (AbstractVar) es.getKey();
    //            s += "\n\t" + k.getScope().getName() + "." + k.getName() + " -> ";
    //            Object v = es.getValue();
    //            if (v instanceof AbstractVar) {
    //                AbstractVar av = (AbstractVar) v;
    //                s += av.getScope().getName() + "." + av.getName();
    //            } else if (v instanceof WrappedVar) {
    //                AbstractVar av = ((WrappedVar) v).getVar();
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

    private final Map sourceTermMap = new HashMap();

    /**
     * Returns all the source Terms in the transitive closure of the extends and
     * supersedes relationship of the TRule.
     * 
     * @param rule
     * @return
     */
    private List getSourceTerms(TRule rule) throws ResolutionException {
        List sources = (List) sourceTermMap.get(rule);
        if (null == sources) {
            sources = new ArrayList();

            Term srcTerm = rule.getSrc();
            if (null != srcTerm) {
                sources.add(srcTerm);
            }

            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                sources.addAll(getSourceTerms(extRule));
            }

            for (Iterator itr = rule.getSuperseded().iterator(); itr.hasNext();) {
                TRule supRule = (TRule) itr.next();
                sources.addAll(getSourceTerms(supRule));
            }

            sourceTermMap.put(rule, sources);
        }
        return sources;
    }

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

    private final Map targetTermMap = new HashMap();

    /**
     * Returns all the target Terms in the transitive closure of the extends
     * relationship of the TRule.
     * 
     * @param rule
     * @return
     */
    private List getTargetTerms(TRule rule) {
        List targets = (List) targetTermMap.get(rule);
        if (null == targets) {
            targets = new ArrayList();

            targets.addAll(rule.getTgt());

            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                targets.addAll(getTargetTerms(extRule));
            }

            targetTermMap.put(rule, targets);
        }
        return targets;
    }

    private final Map overrideTermMap = new HashMap();

    private List getOverrideTerms(TRule rule) throws ResolutionException {
        List overs = (List) overrideTermMap.get(rule);
        if (null == overs) {
            overs = new ArrayList();

            List supersedingRules = getList(invertedSupMap, rule);
            for (Iterator itr = supersedingRules.iterator(); itr.hasNext();) {
                TRule supersedingRule = (TRule) itr.next();

                OverrideTerm override = new OverrideTerm();
                override.getNegatedTerms().addAll(
                        getSourceTerms(supersedingRule));
                overs.add(override);
            }

            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                overs.addAll(getOverrideTerms(extRule));
            }
        }

        return overs;
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
            TRuleVar var = (TRuleVar) itr.next();
            for (Iterator extItr = var.getExtended().iterator(); extItr.hasNext();) {
                TRuleVar extVar = (TRuleVar) extItr.next();
                linkVars(binding, var, extVar);
            }
        }
    }

    private void buildSupBindings(List vars, Binding binding)
            throws ResolutionException {
        for (Iterator itr = vars.iterator(); itr.hasNext();) {
            TRuleVar var = (TRuleVar) itr.next();
            for (Iterator supItr = var.getSuperseded().iterator(); supItr.hasNext();) {
                TRuleVar supVar = (TRuleVar) supItr.next();
                linkVars(binding, var, supVar);
            }
        }
    }

    private Object lookup(Binding binding, TRuleVar var) {
        Object obj = binding.lookup(var);
        if (null == obj) {
            return new WrappedVar(var);
        }
        return obj;
    }

    private void linkVars(Binding binding, TRuleVar lhVar, TRuleVar rhVar)
            throws ResolutionException {
        Object lhObj = lookup(binding, lhVar);
        Object rhObj = lookup(binding, rhVar);
        if (lhObj instanceof WrappedVar) {
            AbstractVar var = ((WrappedVar) lhObj).getVar();
            binding.add(var, rhObj);
        } else {
            binding.add((TRuleVar) lhObj, rhObj);
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

    static class TestBulidMaps {
        public static void main(String[] args) throws ResolutionException {
            Transformation t = TefkatFactory.eINSTANCE.createTransformation();

            TRule r1 = TefkatFactory.eINSTANCE.createTRule();
            r1.setTransformation(t);
            r1.setName("R1");
            AbstractVar v1 = TefkatFactory.eINSTANCE.createTRuleVar();
            v1.setScope(r1);
            v1.setName("v1");
            r1.setSrc(mi(v1, "Src1"));
            r1.getTgt().add(mi(v1, "Tgt1"));

            TRule r2 = TefkatFactory.eINSTANCE.createTRule();
            r2.setTransformation(t);
            r2.setName("R2");
            TRuleVar v2 = TefkatFactory.eINSTANCE.createTRuleVar();
            v2.setScope(r2);
            v2.setName("v2");
            v2.getExtended().add(v1);
            r2.setSrc(mi(v2, "Src2"));
            r2.getTgt().add(mi(v2, "Tgt2"));
            r2.getExtended().add(r1);

            TRule r3 = TefkatFactory.eINSTANCE.createTRule();
            r3.setTransformation(t);
            r3.setName("R3");
            TRuleVar v3 = TefkatFactory.eINSTANCE.createTRuleVar();
            v3.setScope(r3);
            v3.setName("v3");
            v3.getSuperseded().add(v1);
            r3.setSrc(mi(v3, "Src3"));
            r3.getTgt().add(mi(v3, "Tgt3"));
            r3.getSuperseded().add(r1);

            TRule r4 = TefkatFactory.eINSTANCE.createTRule();
            r4.setTransformation(t);
            r4.setName("R4");
            TRuleVar v4 = TefkatFactory.eINSTANCE.createTRuleVar();
            v4.setScope(r4);
            v4.setName("v4");
            v4.getExtended().add(v3);
            r4.setSrc(mi(v4, "Src4"));
            r4.getTgt().add(mi(v4, "Tgt4"));
            r4.getExtended().add(r3);

            RuleEvaluator re = new RuleEvaluator(null, null, new ArrayList(),
                    new ArrayList());

            re.buildMaps(t);

            System.out.println("s1prime: " + re.getSourceTerms(r1));
            System.out.println("t1prime: " + re.getTargetTerms(r1));
            System.out.println("over1  : " + re.getOverrideTerms(r1));
            System.out.println();

            System.out.println("s2prime: " + re.getSourceTerms(r2));
            System.out.println("t2prime: " + re.getTargetTerms(r2));
            System.out.println("over2  : " + re.getOverrideTerms(r2));
            System.out.println();

            System.out.println("s3prime: " + re.getSourceTerms(r3));
            System.out.println("t3prime: " + re.getTargetTerms(r3));
            System.out.println("over3  : " + re.getOverrideTerms(r3));
            System.out.println();

            System.out.println("s4prime: " + re.getSourceTerms(r4));
            System.out.println("t4prime: " + re.getTargetTerms(r4));
            System.out.println("over4  : " + re.getOverrideTerms(r4));
            System.out.println();

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

        private static MofInstance mi(AbstractVar v, String type) {
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

    /**
     * Records that an instance of a given tracking class was created.
     * 
     * @param trackingClass
     * @throws NotGroundException
     * @throws ResolutionException
     */
    void trackingCreate(EClass trackingClass, EObject instance) throws ResolutionException, NotGroundException {
        if (INCREMENTAL) {
	    // FIXME do this for all superclasses as well!
	    //
            List callbacks = (List) trackingQueryMap.get(trackingClass);
            if (null != callbacks) {
                for (Iterator itr = callbacks.iterator(); itr.hasNext(); ) {
                    TrackingCallback callback = (TrackingCallback) itr.next();
                    callback.handleInstance(instance);
                }
            }
        } else {
            trackingUpdateSet.add(trackingClass);
        }
    }

    private Map featureOrderings = new HashMap();

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
                final EStructuralFeature feature = Util.getFeature(inst.eClass(), feat);
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
        private String context;
        
        private Map instanceOrderings = new HashMap();
        private Map instanceCounters = new HashMap();

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
    
    /**
     * Given a list of Resources, transitively load all resources that contain referenced stuff.
     * 
     * @param resources
     * @return
     */
    public final Set findAllResources(Collection resources) {
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

    private static class TestSort {
        public static void main(String[] args) {
            EClass inst = EcoreFactory.eINSTANCE.createEClass();
            String feat = "eStructuralFeatures";
            EStructuralFeature[] a = {EcoreFactory.eINSTANCE.createEReference(),
                              EcoreFactory.eINSTANCE.createEReference(),
                              EcoreFactory.eINSTANCE.createEAttribute(),
                              EcoreFactory.eINSTANCE.createEAttribute(),
                              EcoreFactory.eINSTANCE.createEAttribute(),
            };
            a[0].setName("zero");
            a[1].setName("one");
            a[2].setName("two");
            a[3].setName("three");
            a[4].setName("four");
            inst.getEStructuralFeatures().addAll(Arrays.asList(a));
            
            RuleEvaluator re = new RuleEvaluator(null, null, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
            
            re.addPartialOrder(inst, feat, a[4], a[3]);
            re.addPartialOrder(inst, feat, a[3], a[2]);
            re.addPartialOrder(inst, feat, a[2], a[1]);
            re.addPartialOrder(inst, feat, a[1], a[0]);

            re.addPartialOrder(inst, feat, a[4], "foo");    // Should cause a resolution exception below

            try {
                for (final Iterator itr = inst.getEStructuralFeatures().iterator(); itr.hasNext(); ) {
                    System.out.println(((EStructuralFeature) itr.next()).getName());
                }
                re.topologicalSort();
                System.out.println("-----------------");
                for (final Iterator itr = inst.getEStructuralFeatures().iterator(); itr.hasNext(); ) {
                    System.out.println(((EStructuralFeature) itr.next()).getName());
                }
            } catch (ResolutionException e) {
                e.printStackTrace();
            }
        }
    }

    private Stack patternStack = new Stack();
    
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
     * @param defn
     * @return
     */
    public Map getPatternCache(PatternDefn defn) {
        Map cache = (Map) patternCache.get(defn);
        if (null == cache) {
            cache = new HashMap();
            patternCache.put(defn, cache);
        }
        return cache;
    }
    
}