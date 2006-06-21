/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 * Created on 13/11/2003
 *
 *
 */

package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import tefkat.model.*;
import tefkat.model.internal.IntMap;
import tefkat.model.internal.Util;


/**
 * @author steel, lawley
 * 
 */
abstract class AbstractResolver {

    private static final class IfConditionResultListener implements TreeListener {
        private final IfTerm literal;

        private final Node node;

        private final List goal;

        private final Tree tree;

        private IfConditionResultListener(IfTerm literal, Node node, List goal, Tree tree) {
            super();
            this.literal = literal;
            this.node = node;
            this.goal = goal;
            this.tree = tree;
        }

        public void solution(Binding answer) {
            Binding sContext = new Binding(answer);
            goal.add(0, literal.getTerm().get(1));
            tree.createBranch(node, sContext, goal);
        }

        public void completed(Tree theTree) {
            if (!theTree.isSuccess()) {
                goal.add(0, literal.getTerm().get(2));
                tree.createBranch(node, null, goal);
            }
        }
    }

    private static final class NewPatternSolutionsListener implements TreeListener {
        private final Collection goal;

        private final Node node;

        private final Tree tree;

        private final List vars;

        private final List args;

        private NewPatternSolutionsListener(Collection goal, Node node, Tree tree, List vars, List args) {
            super();
            this.goal = goal;
            this.node = node;
            this.tree = tree;
            this.vars = vars;
            this.args = args;
        }

        public void solution(Binding answer) throws ResolutionException {
            Binding unifier = createOutputBinding(node, vars, args, answer);
        
            tree.createBranch(node, unifier, goal);
        }

        public void completed(Tree theTree) {
            if (!theTree.isSuccess()) {
                tree.failure(node);
            }
        }
    }

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
     *  
     *  @return A collection of unifiers, each unifier containing only the set
     *          or variables in the goal, and mapping each variable to a term.
     */
    Collection solutions(Tree tree, Collection goalVars) {
        if (!tree.isSuccess()) {
            throw new IllegalStateException(
                    "Resolution of the goal failed - there are no solutions");
        }
        
        Collection unifiers = new ArrayList();
      
        for (Iterator itr = tree.getAnswers().iterator(); itr.hasNext(); ) {
            Binding answer = (Binding) itr.next();
            unifiers.add(answer);
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
        switch (id) {
        case TefkatPackage.MOF_INSTANCE:
            resolveMofInstance(tree, node, goal, (MofInstance) literal);
            break;
            
        case TefkatPackage.TRACKING_USE:
            resolveTrackingUse(tree, node, goal, (TrackingUse) literal);
            break;
            
        case TefkatPackage.PATTERN_USE:
            resolvePatternUse(tree, node, goal, (PatternUse) literal, isNegation);
            break;
            
        case TefkatPackage.NOT_TERM:
            resolveNotTerm(tree, node, goal, (NotTerm) literal);
            break;
            
        case TefkatPackage.OR_TERM:
            resolveOrTerm(tree, node, goal, (OrTerm) literal);
            break;
            
        case TefkatPackage.AND_TERM:
            resolveAndTerm(tree, node, goal, (AndTerm) literal);
            break;
            
        case TefkatPackage.CONDITION:
            resolveCondition(tree, node, goal, (Condition) literal);
            break;
            
        case TefkatPackage.IF_TERM:
            resolveIfTerm(tree, node, goal, (IfTerm) literal);
            break;
            
        case TefkatPackage.MOF_ORDER:
            resolveMofOrder(tree, node, goal, (MofOrder) literal);
            break;
            
        default:
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
            Collection goal, TrackingUse literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     * @return
     */
    protected abstract boolean resolveMofInstance(Tree tree, Node node,
            Collection goal, MofInstance literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     */
    protected abstract void resolveOrTerm(Tree tree, Node node, Collection goal,
            OrTerm literal) throws ResolutionException ;

    /**
     * @param tree
     * @param node
     * @param goal
     * @param literal
     * @return
     */
    protected abstract void resolveNotTerm(Tree tree, Node node, Collection goal,
            NotTerm literal) throws ResolutionException, NotGroundException;
    
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
            PatternUse literal, boolean isNegation) throws ResolutionException, NotGroundException {

        PatternDefn pDefn = literal.getDefn();
        List args = literal.getArg();

        if (null == pDefn) { // TODO fix this println hack
        //            System.err.println("PAT: println " + ((VarUse)args.get(0)).getVar().getName());
        //            System.err.println(node.getAllBindings());
            String mesg = "";
            for (int i = 0; i < args.size(); i++) {
                String str;
                try {
                    List values = exprEval.eval(node, (Expression) args.get(i));
                    str = "[";
                    for (Iterator itr = values.iterator(); itr.hasNext(); ) {
                        Object o = itr.next();
                        str += (o instanceof BindingPair ? ((BindingPair) o).getValue() : o);
                        if (itr.hasNext()) {
                            str += ", ";
                        }
                    }
                    str += "]";
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
        
        // FIXME handle BindingPairs
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
            final Node node, Collection goal, PatternUse literal, final List pDefVars, final List args,
            PatternDefn pDefn, Binding newContext, boolean isNegation)
            throws ResolutionException {

        final Collection newGoal = new ArrayList(goal);
        newGoal.remove(literal);

        Map cache = ruleEval.getPatternCache(pDefn);
        Tree resultTree = (Tree) cache.get(newContext);
        
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
            
            resultTree.addTreeListener(new NewPatternSolutionsListener(newGoal, node, tree, pDefVars, args));
        }
        
        //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
        if (resultTree.isSuccess()) {
            // Process any existing solutions
            
            Collection solutions = solutions(resultTree, pDefVars);
            for (Iterator itr = solutions.iterator(); itr.hasNext();) {
                Binding answer = (Binding) itr.next();
                Binding unifier = createOutputBinding(node, pDefVars, args, answer);

                tree.createBranch(node, unifier, newGoal);
            }
        }
    }
    
    private void resolvePatternDefn(final Tree tree,
            final Node node, Collection goal, PatternUse literal, final List pDefVars, final List args,
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
            for (Iterator itr = solutions.iterator(); itr.hasNext(); ) {
                Binding currentSolution = (Binding) itr.next();
                Binding unifier = createOutputBinding(node, pDefVars, args, currentSolution);

                // System.err.println("  u " + unifier); // TODO delete
                Collection newGoal = new ArrayList(goal);
                newGoal.remove(literal);
                tree.createBranch(node, unifier, newGoal);
            }
        } else {
            tree.failure(node);
        }
    }

    static private Binding createOutputBinding(final Node node, final List formals, final List actuals, Binding solution) throws ResolutionException {
        Binding unifier = new Binding();
        // System.err.println("  s " + currentSolution); // TODO delete
        for (int j = 0; j < actuals.size(); j++) {
            Expression argExpr = (Expression) actuals.get(j);
            if (argExpr instanceof VarUse) {
                Object val = node.lookup(((VarUse) argExpr).getVar());
                if (null == val) {
                    // We only add a new binding if argExpr is not already
                    // bound, otherwise we end up with multiple identical bindings
                    // for the same Var.
                    unifier.add(((VarUse) argExpr).getVar(),
                            solution.lookup((PatternVar) formals.get(j)));
                } else if (val instanceof WrappedVar) {
                    unifier.add(((WrappedVar) val).getVar(),
                            solution.lookup((PatternVar) formals.get(j)));
                } else if (!val.equals(solution.lookup((PatternVar) formals.get(j)))) {
                    throw new ResolutionException(node, "conflicting pattern arg and result: " + val + "\t" + solution.lookup((PatternVar) formals.get(j)));
                }
            }
        }
        return unifier;
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
                    // FIXME - the above is bogus (I think) at least need to
                    // propagate var bindings when calculating the actualsList
                    // to handle, for example, p(X, X) and also p(X.foo, X.bar)
                    // -- the latter involves BindingPairs
                    if (true || !(params[j] instanceof WrappedVar)) {
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
        Term[] literals = (Term[]) node.goal().toArray(new Term[node.goal().size()]);

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
            AndTerm literal) throws ResolutionException {
        /**
         *  Add the conjuncts of this literal into the goal.
         */
        Collection terms = literal.getTerm();
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

    protected void resolveIfTerm(final Tree tree, final Node node, Collection goal, final IfTerm literal)
    throws ResolutionException, NotGroundException {
        
        // Ensure that all non-local variables are already bound
        for (Iterator itr = literal.getNonLocalVars().iterator(); itr.hasNext(); ) {
            AbstractVar var = (AbstractVar) itr.next();
            if (null == node.lookup(var)) {
                throw new NotGroundException("Non-local variable " + var + " is not bound.");
            }
        }
        
        Binding context = new Binding(node.getBindings());
        List condGoal = new ArrayList();
        condGoal.add(literal.getTerm().get(0));
        Node newRoot = new Node(condGoal, context); // cannot pass node as parent here -- see evalNegatedGoal()
        Tree newTree = new Tree(tree.getTransformation(), newRoot, tree.getContext(), tree.getTrackingExtent(), false);
	newTree.setLevel(tree.getLevel() - 1);

        if (ruleEval.INCREMENTAL) {
            final List newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            
            newTree.addTreeListener(new IfConditionResultListener(literal, node, newGoal, tree));
	    ruleEval.addUnresolvedTree(newTree);
        } else {
            ruleEval.resolveNode(newTree);
            
            if (newTree.isSuccess()) {
                for (final Iterator itr = newTree.getAnswers().iterator(); itr.hasNext(); ) {
                    Binding answer = (Binding) itr.next();
                    Binding sContext = new Binding(answer);
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
