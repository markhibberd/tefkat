/*
 * Created on 13/11/2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.dstc.tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.dstc.tefkat.model.*;
import com.dstc.tefkat.model.internal.Util;

/**
 * @author steel, lawley
 * 
 */
abstract class AbstractResolver {

    final IntMap elapsedTime = new IntMap();
    final IntMap callCount = new IntMap();
    
    protected RuleEvaluator ruleEval;

    protected Evaluator exprEval;

    /**
     * 
     */
    protected AbstractResolver(RuleEvaluator evaluator) {
        ruleEval = evaluator;
        exprEval = evaluator.getEvaluator();
    }

    protected Map getNameMap() {
        return ruleEval.nameMap;
    }
    
    /**
     *  Collect the unifiers that describe the solutions to the goal.
     *  If the goal was not successful, throws a RuntimeExpcetion.
     *  @see #getSucceeded
     *  @return A collection of unifiers, each unifier containing only the set
     *          or variables in the goal, and mapping each variable to a term.
     */
    Collection solutions(Tree tree, Collection goalVars) {
        if (!tree.isSuccess()) {
            throw new IllegalStateException(
                    "Resolution of the goal failed - there are no solutions");
        }
        
        Collection unifiers = new ArrayList();
      
        for (Iterator itr = tree.successes().iterator(); itr.hasNext(); ) {
            Node node = (Node) itr.next();
            unifiers.add(node.getBindings());
        }

//        Binding init = new Binding();
//
//        Iterator i = goalVars.iterator();
//        while (i.hasNext()) {
//            AbstractVar v = (AbstractVar) i.next();
//            init.put(v, v);
//        }
//
//        Node rootNode = tree.root();
//        init.apply(rootNode.binding());
//        solutions(rootNode, init, unifiers);
        return unifiers;
    }

    /**
     * @param tree
     * @param node
     * @param goal
     * @param isNegation
     * @return
     * @throws ResolutionException
     */
    protected void doResolveNode(Tree tree, Node node, Collection goal,
            Term literal, boolean isNegation) throws ResolutionException, NotGroundException {

        EClass idc = literal.eClass();
        int id = idc.getClassifierID();
        long start = System.currentTimeMillis();

        //  Grow the tree according to the type of literal.
        //  A predicate is searched for in the rule database.
        //  A conjunction is flattened into the enclosing goal conjunction.
        //  A negation spawns a new tree
        //  A disjunction has its disjunctions distributed into subtrees
        //
        if (literal instanceof MofInstance) {
            resolveMofInstance(tree, node, goal, (MofInstance) literal);
        } else if (literal instanceof TrackingUse) {
            resolveTrackingUse(tree, node, goal, (TrackingUse) literal);
        } else if (literal instanceof PatternUse) {
            resolvePatternUse(tree, node, goal, (PatternUse) literal, isNegation);
        } else if (literal instanceof NotTerm) {
            resolveNotTerm(tree, node, goal, (NotTerm) literal);
        } else if (literal instanceof OverrideTerm) {
            resolveOverrideTerm(tree, node, goal, (OverrideTerm) literal);
        } else if (literal instanceof OrTerm) {
            resolveOrTerm(tree, node, goal, (OrTerm) literal);
        } else if (literal instanceof AndTerm) {
            resolveAndTerm(tree, node, goal, (AndTerm) literal);
        } else if (literal instanceof Condition) {
            resolveCondition(tree, node, goal, (Condition) literal);
        } else if (literal instanceof IfTerm) {
            resolveIfTerm(tree, node, goal, (IfTerm) literal);
        } else if (literal instanceof MofOrder) {
            resolveMofOrder(tree, node, goal, (MofOrder) literal);
        } else {
            throw new ResolutionException(node, "Unrecognised term type: " + literal);
        }
        
        long end = System.currentTimeMillis();
        int total = elapsedTime.get(idc);
        total += (end - start);
        elapsedTime.put(idc, total);
        int count = callCount.get(idc);
        count++;
        callCount.put(idc, count);
    }

    /**
     * @param tree
     * @param node
     * @param goal
     * @param order
     */
    protected abstract void resolveMofOrder(Tree tree, Node node, Collection goal, MofOrder term)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     */
    protected abstract void resolveCondition(Tree tree, Node node, Collection goal, Condition literal)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     */
    protected abstract void resolveTrackingUse(Tree tree, Node node,
            Collection goal, Term literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     * @return
     */
    protected abstract boolean resolveMofInstance(Tree tree, Node node,
            Collection goal, Term literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     */
    protected void resolveOrTerm(Tree tree, Node node, Collection goal,
            Term literal) throws ResolutionException {
        // Overridden in subtypes
    }

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     * @return
     */
    protected void resolveNotTerm(Tree tree, Node node, Collection goal,
            Term literal) throws ResolutionException {
        // Overridden in subtypes
    }


    protected void resolveOverrideTerm(Tree tree, Node node, Collection goal,
        OverrideTerm literal) throws ResolutionException {
        // Overridden in subtypes
    }
    
    /**
     * Resolve the PatternDefn passing in a set of bindings for the vars.
     * Need to do this on a new tree so that a fresh set of variables is used
     * in case there is recursion. 
     *
     * @param tree
     * @param node
     * @param goal
     * @param literal
     * @param isNegation
     * @return
     * 
     * TODO Cache PatternUse calls to trap infinite recursion.
     */
    protected void resolvePatternUse(Tree tree, Node node, Collection goal,
            Term literal, boolean isNegation) throws ResolutionException, NotGroundException {

        PatternUse term = (PatternUse) literal;
        PatternDefn pDefn = term.getDefn();
        List args = term.getArg();

        if (null == pDefn) { // TODO fix this println hack
        //            System.err.println("PAT: println " + ((VarUse)args.get(0)).getVar().getName());
        //            System.err.println(node.getAllBindings());
            String mesg = "";
            for (int i = 0; i < args.size(); i++) {
                String str;
                try {
                    str = String.valueOf(exprEval.eval(node, (Expression) args.get(i)));
                } catch (NotGroundException e) {
                    str = "(" + args.get(i) + " not sufficiently ground)";
                }
                mesg += (i > 0 ? " " : "") + str;
            }
            ruleEval.fireInfo(mesg);
//            System.out.println(mesg);

            Collection newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            tree.createBranch(node, null, newGoal);
            return;
        }

        //        System.err.println("PAT: " + pDefn.getName());	// TODO delete
        List pDefVars = pDefn.getParameterVar();

        // Check that the sizes of formals and actuals matches, then add the bound variables, pairwise,
        // to the initial binding to be passed to the tree resolution of the pattern.
        if (args.size() != pDefVars.size()) {
            throw new ResolutionException(node, "Invalid arity for pattern "
                    + pDefn.getName() + ": " + args.size());
        }
        
        // Eval all the input Expressions
        List actuals = new ArrayList();
        for (Iterator itr = args.iterator(); itr.hasNext(); ) {
            Expression actualExpr = (Expression) itr.next();
            actuals.add(exprEval.eval(node, actualExpr));
        }

        Collection bindings = new ArrayList();
        createBindings(tree.getContext(), actuals, pDefVars, bindings);

        ruleEval.pushPattern(pDefn);

        if (bindings.size() > 0) {
            for (Iterator itr = bindings.iterator(); itr.hasNext();) {
                if (ruleEval.INCREMENTAL) {
                    incrementalResolvePatternDefn(tree, node,
                            goal, literal, pDefVars, args, pDefn,
                            (Binding) itr.next(), isNegation);
                } else {
                    resolvePatternDefn(tree, node,
                            goal, literal, pDefVars, args, pDefn,
                            (Binding) itr.next(), isNegation);
                }
            }
        } else if (pDefVars.size() == 0) {
            // Only call with empty bindings if it's a arity-zero pattern
            if (ruleEval.INCREMENTAL) {
                incrementalResolvePatternDefn(tree, node, goal,
                        literal, pDefVars, args, pDefn, new Binding(), isNegation);
            } else {
                resolvePatternDefn(tree, node, goal,
                        literal, pDefVars, args, pDefn, new Binding(), isNegation);
            }
        } else {
            tree.failure(node);
        }

        ruleEval.popPattern();
    }

    private void incrementalResolvePatternDefn(final Tree tree,
            final Node node, Collection goal, Term literal, final List pDefVars, final List args,
            PatternDefn pDefn, Binding newContext, boolean isNegation)
            throws ResolutionException {
        Tree resultTree = null;

        Map cache = ruleEval.getPatternCache(pDefn);
        resultTree = (Tree) cache.get(newContext);

        final Collection newGoal = new ArrayList(goal);
        newGoal.remove(literal);
        
        if (null == resultTree) {
            Collection patGoal = new ArrayList();
            Term pDefTerm = pDefn.getTerm();
            patGoal.add(pDefTerm);

//            System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
            Node patternNode = new Node(patGoal, newContext);
        
            resultTree = new Tree(tree.getTransformation(), patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
	    resultTree.setLevel(tree.getLevel());
            
            ruleEval.addUnresolvedTree(resultTree);

            cache.put(newContext, resultTree);
        }

        if (!resultTree.isCompleted()) {
            // Register listener for any new solutions
            
            resultTree.addTreeListener(new TreeListener() {

                public void solution(Node sNode) throws ResolutionException {
                    Binding unifier = new Binding();
                    Binding currentSolution = sNode.getBindings();
                    // System.err.println("  s " + currentSolution); // TODO delete
                    for (int j = 0; j < args.size(); j++) {
                        Expression argExpr = (Expression) args.get(j);
                        if (argExpr instanceof VarUse) {
                            Object val = node.lookup(((VarUse) argExpr).getVar());
                            if (null == val) {
                                // We only add a new binding if argExpr is not already
                                // bound, otherwise we end up with multiple identical bindings
                                // for the same Var.
                                unifier.add(((VarUse) argExpr).getVar(),
                                        currentSolution.lookup((PatternVar) pDefVars.get(j)));
                            } else if (val instanceof WrappedVar) {
                                unifier.add(((WrappedVar) val).getVar(),
                                        currentSolution.lookup((PatternVar) pDefVars.get(j)));
                            }
                        }
                    }

                    // System.err.println("  u " + unifier); // TODO delete
                    tree.createBranch(node, unifier, newGoal);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        tree.failure(node);
                    }
                }
                
            });
        }
        
        //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
        if (resultTree.isSuccess()) {
            // Process any existing solutions
            
            Collection solutions = solutions(resultTree, pDefVars);
            for (Iterator itr = solutions.iterator(); itr.hasNext();) {
                Binding unifier = new Binding();
                Binding currentSolution = (Binding) itr.next();
                // System.err.println("  s " + currentSolution); // TODO delete
                for (int j = 0; j < args.size(); j++) {
                    Expression argExpr = (Expression) args.get(j);
                    if (argExpr instanceof VarUse) {
                        Object val = node.lookup(((VarUse) argExpr).getVar());
                        if (null == val) {
                            // We only add a new binding if argExpr is not already
                            // bound, otherwise we end up with multiple identical bindings
                            // for the same Var.
                            unifier.add(((VarUse) argExpr).getVar(),
                                    currentSolution.lookup((PatternVar) pDefVars.get(j)));
                        } else if (val instanceof WrappedVar) {
                            unifier.add(((WrappedVar) val).getVar(),
                                    currentSolution.lookup((PatternVar) pDefVars.get(j)));
                        }
                    }
                }

                // System.err.println("  u " + unifier); // TODO delete
                tree.createBranch(node, unifier, newGoal);
            }
        }
    }
    
    private void resolvePatternDefn(final Tree tree,
            final Node node, Collection goal, Term literal, final List pDefVars, final List args,
            PatternDefn pDefn, Binding newContext, boolean isNegation)
            throws ResolutionException {
        Tree resultTree = null;

        Map cache = ruleEval.getPatternCache(pDefn);
        resultTree = (Tree) cache.get(newContext);
        
        if (null == resultTree) {
            Collection patGoal = new ArrayList();
            Term pDefTerm = pDefn.getTerm();
            patGoal.add(pDefTerm);

//            System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
            Node patternNode = new Node(patGoal, newContext);
        
            resultTree = new Tree(tree.getTransformation(), patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
	    resultTree.setLevel(tree.getLevel());

            ruleEval.resolveNode(resultTree);

            cache.put(newContext, resultTree);
            
        }
        
        //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
        if (resultTree.isSuccess()) {
            Collection solutions = solutions(resultTree, pDefVars);
            for (Iterator itr = solutions.iterator(); itr.hasNext();) {
                Binding unifier = new Binding();
                Binding currentSolution = (Binding) itr.next();
                // System.err.println("  s " + currentSolution); // TODO delete
                for (int j = 0; j < args.size(); j++) {
                    Expression argExpr = (Expression) args.get(j);
                    if (argExpr instanceof VarUse) {
                        Object val = node.lookup(((VarUse) argExpr).getVar());
                        if (null == val) {
                            // We only add a new binding if argExpr is not already
                            // bound, otherwise we end up with multiple identical bindings
                            // for the same Var.
                            unifier.add(((VarUse) argExpr).getVar(),
                                    currentSolution.lookup((PatternVar) pDefVars.get(j)));
                        } else if (val instanceof WrappedVar) {
                            unifier.add(((WrappedVar) val).getVar(),
                                    currentSolution.lookup((PatternVar) pDefVars.get(j)));
                        }
                    }
                }

                // System.err.println("  u " + unifier); // TODO delete
                Collection newGoal = new ArrayList(goal);
                newGoal.remove(literal);
                tree.createBranch(node, unifier, newGoal);
            }
        } else {
            tree.failure(node);
        }
    }

    /**
     * Create a Binding for each combination of values in the List of Lists ls.
     * 
     * @param context
     * @param actualsList
     * @param formals
     * @param bindings
     * @throws ResolutionException
     */
    private void createBindings(Binding context, List actualsList,
            List formals, Collection bindings) throws ResolutionException {
        if (actualsList.size() > 0) {
            createBindings(context, actualsList, formals, bindings, 0,
                    new Object[actualsList.size()]);
        }
    }

    private void createBindings(Binding context, List actualsList,
            List formals, Collection bindings, int i, Object[] params)
            throws ResolutionException {
        Collection actuals = (Collection) actualsList.get(i);
        if (i < actualsList.size() - 1) {
            for (Iterator itr = actuals.iterator(); itr.hasNext();) {
                params[i] = itr.next();
                createBindings(context, actualsList, formals, bindings, i + 1, params);
            }
        } else {
            for (Iterator itr = actuals.iterator(); itr.hasNext();) {
                params[i] = itr.next();

                Binding newContext = new Binding(context);
                for (int j = 0; j < params.length; j++) {
                    // only values are passed in, not references (i.e., Vars)
                    // since we get WrappedVars for unbound inputs as well as for
                    // vars bound to other vars
                    if (!(params[j] instanceof WrappedVar)) {
                        newContext.add((PatternVar) formals.get(j), params[j]);
                    }
                }

                bindings.add(newContext);
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
    protected Term selectLiteral(Node node) {
        Term[] literals = (Term[]) node.goal().toArray(new Term[0]);

        /**
         *  Simple selection rule:
         *    + select MofInstances first, then anything else
         */
        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof MofInstance) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof CompoundTerm) {
                node.selectLiteral(literals[i]);
                return literals[i];
            }
        }

        // Delay selection of "println"
        for (int i = 0; i < literals.length; i++) {
            if ((literals[i] instanceof PatternUse)) {
                if (((PatternUse) literals[i]).getDefn() == null) {
                    continue;
                }
            }
            node.selectLiteral(literals[i]);
            return literals[i];
        }

        if (literals.length > 0) {
            node.selectLiteral(literals[0]);
            return literals[0];
        }

        return null;
    }

    protected void resolveAndTerm(Tree tree, Node node, Collection goal,
            Term literal) throws ResolutionException {
        /**
         *  Add the conjuncts of this literal into the goal.
         */
        Collection terms = ((AndTerm) literal).getTerm();
        if (null == terms) {
            throw new ResolutionException(node,
                    "Malformed (null) AndTerm contents");
        }
        List newGoal = new ArrayList(goal);
        newGoal.remove(literal);
        newGoal.addAll(0, terms);
        tree.createBranch(node, null, newGoal);
    }
    
    static protected EStructuralFeature getFeature(Node node, EClass klass, String featureName)
        throws ResolutionException {
        try {
            return Util.getFeature(klass, featureName);
        } catch (RuntimeException e) {
            throw new ResolutionException(node, e.getMessage());
        }
    }

    Binding bind(Object val1, Object val2) throws ResolutionException {
        Binding unifier = null;
        if (val1 instanceof BindingPair) {
            unifier = new Binding();
            unifier.composeLeft((BindingPair) val1);
            val1 = ((BindingPair) val1).getValue();
        }
        if (val2 instanceof BindingPair) {
            if (null == unifier) {
                unifier = new Binding();
            }
            unifier.composeLeft((BindingPair) val2);
            val2 = ((BindingPair) val2).getValue();
        }
    
        if (val1 instanceof WrappedVar) {
            unifier = bindWrappedVar(unifier, (WrappedVar) val1, val2);
        } else if (val2 instanceof WrappedVar) {
            unifier = bindWrappedVar(unifier, (WrappedVar) val2, val1);
	} else if (val1 instanceof Number && val2 instanceof Number) {
	    if (val1 instanceof Float || val1 instanceof Double ||
	        val2 instanceof Float || val2 instanceof Double) {
	        double v1 = ((Number) val1).doubleValue();
		double v2 = ((Number) val2).doubleValue();
		if (v1 == v2) {
		    if (null == unifier) {
			unifier = new Binding();
		    }
		} else {
		    unifier = null;
		}
	    } else {
		long l1 = ((Number) val1).longValue();
		long l2 = ((Number) val2).longValue();
		if (l1 == l2) {
		    if (null == unifier) {
			unifier = new Binding();
		    }
		} else {
		    unifier = null;
		}
	    }
        } else {
            // bloody EMF - a "generated" EEnumLiteral is not "equal" to a
            // dynamic EEnumLiteral or something like that
            // - you need to get the EEnumerator for comparisons...
            if (val1 instanceof EEnumLiteral) {
                val1 = ((EEnumLiteral) val1).getInstance();
            }
            if (val2 instanceof EEnumLiteral) {
                val2 = ((EEnumLiteral) val2).getInstance();
            }
            if (val1.equals(val2)) {
                if (null == unifier) {
                    unifier = new Binding();
                }
            } else {
                unifier = null;
            }
        }
        return unifier;
    }

    private Binding bindWrappedVar(Binding unifier, WrappedVar wVar, Object val) throws ResolutionException {
        AbstractVar var = wVar.getVar();
        EClass eClass = wVar.getType();
        if (null != eClass) {
            if (val instanceof WrappedVar) {
                EClass eClass2 = ((WrappedVar) val).getType();
                if (null == eClass2) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(var, val);
                } else if (eClass.isSuperTypeOf(eClass2)) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(var, val);
                } else if (eClass2.isSuperTypeOf(eClass)) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(((WrappedVar) val).getVar(), wVar);
                } else {    // type mismatch
                    unifier = null;
                }
            } else if (val instanceof EObject) {
                if (eClass.isSuperTypeOf(((EObject) val).eClass())) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(var, val);
                } else {
                    unifier = null;
                }
            } else {
                Class cls = eClass.getInstanceClass();
                if (null != cls) {
                    if (cls.isAssignableFrom(val.getClass())) {
                        if (null == unifier) {
                            unifier = new Binding();
                        }
                        unifier.add(var, val);
                    } else {
                        unifier = null;
                    }
                } else {
                    unifier = null;
                }
            }
        } else {
            if (null == unifier) {
                unifier = new Binding();
            }
            unifier.add(var, val);
        }
        
        return unifier;
    }

    protected void resolveIfTerm(final Tree tree, final Node node, Collection goal, final IfTerm literal)
    throws ResolutionException, NotGroundException {
        Binding context = new Binding(node.getBindings());
        List condGoal = new ArrayList();
        condGoal.add(literal.getTerm().get(0));
        Node newRoot = new Node(condGoal, context); // cannot pass node as parent here -- see evalNegatedGoal()
        Tree newTree = new Tree(tree.getTransformation(), newRoot, tree.getContext(), tree.getTrackingExtent(), false);
	newTree.setLevel(tree.getLevel() - 1);

        if (ruleEval.INCREMENTAL) {
            final List newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            
            newTree.addTreeListener(new TreeListener() {

                public void solution(Node sNode) {
                    Binding sContext = new Binding(sNode.getBindings());
                    newGoal.add(0, literal.getTerm().get(1));
                    tree.createBranch(node, sContext, newGoal);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        newGoal.add(0, literal.getTerm().get(2));
                        tree.createBranch(node, null, newGoal);
                    }
                }
                
            });
	    ruleEval.addUnresolvedTree(newTree);
        } else {
            ruleEval.resolveNode(newTree);
            
            if (newTree.isSuccess()) {
                for (final Iterator itr = newTree.successes().iterator(); itr.hasNext(); ) {
                    Node sNode = (Node) itr.next();
                    Binding sContext = new Binding(sNode.getBindings());
                    List newGoal = new ArrayList(goal);
                    newGoal.remove(literal);
                    newGoal.add(0, literal.getTerm().get(1));
                    tree.createBranch(node, sContext, newGoal);
                }
            } else {
                List newGoal = new ArrayList(goal);
                newGoal.remove(literal);
                newGoal.add(0, literal.getTerm().get(2));
                tree.createBranch(node, null, newGoal);
            }
        }
    }

}
