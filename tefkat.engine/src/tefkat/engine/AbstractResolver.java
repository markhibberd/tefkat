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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import tefkat.model.*;
import tefkat.model.internal.IntMap;
import tefkat.model.internal.ModelUtils;


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
    protected void resolvePatternUse(final Tree tree, final Node node, final Collection goal,
            final PatternUse literal, final boolean isNegation) throws ResolutionException, NotGroundException {

        final PatternDefn pDefn = literal.getDefn();
        final List args = literal.getArg();

        if (null == pDefn) { // TODO fix this println hack
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

            Collection newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            tree.createBranch(node, null, newGoal);
            return;
        }

        final List pDefVars = pDefn.getParameterVar();

        // Check that the sizes of formals and actuals matches, then add the bound variables, pairwise,
        // to the initial binding to be passed to the tree resolution of the pattern.
        if (args.size() != pDefVars.size()) {
            throw new ResolutionException(node, "Invalid arity for pattern "
                    + pDefn.getName() + ": " + args.size());
        }
        
        // handle arity-zero (no args) pattern
        if (pDefVars.size() == 0) {
            if (ruleEval.INCREMENTAL) {
                incrementalResolvePatternDefn(tree, node, goal,
                        literal, pDefVars, args, pDefn, new Binding(), isNegation);
            } else {
                resolvePatternDefn(tree, node, goal,
                        literal, pDefVars, args, pDefn, new Binding(), isNegation);
            }
        } else {
//            final Binding context = tree.getContext();
            Function resolver;
            if (ruleEval.INCREMENTAL) {
                resolver = new Function2() {
                    public Object call(Object[] params) {
                        throw new Error("Internal error: this method should not be called");
                    }

                    public Object call(Binding context, Object[] params) throws ResolutionException {
                        Binding newContext = new Binding(context);

                        for (int i = 0; i < params.length; i++) {
                            newContext.add((PatternVar) pDefVars.get(i), params[i]);
                        }

                        incrementalResolvePatternDefn(tree, node,
                                goal, literal, pDefVars, args, pDefn,
                                newContext, isNegation);
                        
                        return Collections.EMPTY_LIST;
                    }
                };
            } else {
                resolver = new Function2() {
                    public Object call(Object[] params) {
                        throw new Error("Internal error: this method should not be called");
                    }

                    public Object call(Binding context, Object[] params) throws ResolutionException {
                        Binding newContext = new Binding(context);

                        for (int i = 0; i < params.length; i++) {
                            newContext.add((PatternVar) pDefVars.get(i), params[i]);
                        }

                        resolvePatternDefn(tree, node,
                                goal, literal, pDefVars, args, pDefn,
                                newContext, isNegation);

                        return Collections.EMPTY_LIST;
                    }
                };
            }
            
            exprEval.evalAll(node, node.getBindings(), args, resolver);

        }
    }

    private void incrementalResolvePatternDefn(final Tree tree,
            final Node node, Collection goal, PatternUse literal, final List pDefVars, final List args,
            PatternDefn pDefn, final Binding newContext, boolean isNegation)
            throws ResolutionException {

        final Collection newGoal = new ArrayList(goal);
        newGoal.remove(literal);

        final Map cache = ruleEval.getPatternCache(pDefn);
        Tree resultTree = (Tree) cache.get(newContext);
        
        if (null == resultTree) {
            Collection patGoal = new ArrayList();
            Term pDefTerm = pDefn.getTerm();
            patGoal.add(pDefTerm);

//            System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
            Node patternNode = new Node(patGoal, newContext);
        
            resultTree = new Tree(tree.getTransformation(), tree, node, patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
            resultTree.setLevel(tree.getLevel());
            
            ruleEval.addUnresolvedTree(resultTree);

            cache.put(newContext, resultTree);
        }

        if (!resultTree.isCompleted()) {
            // Register listener for any new solutions
            
            resultTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) throws ResolutionException {
                    Binding unifier = createOutputBinding(node, pDefVars, args, answer);

                    tree.createBranch(node, unifier, newGoal);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        tree.failure(node);
                    }
                }

                public void floundered(Tree theTree) {
                    cache.remove(newContext);
                }
                
            });
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
        try {
            ruleEval.pushPattern(pDefn);
            Tree resultTree = null;
            
            Map cache = ruleEval.getPatternCache(pDefn);
            resultTree = (Tree) cache.get(newContext);
            
            if (null == resultTree) {
                Collection patGoal = new ArrayList();
                Term pDefTerm = pDefn.getTerm();
                patGoal.add(pDefTerm);
                
//              System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
                Node patternNode = new Node(patGoal, newContext);
                
                resultTree = new Tree(tree.getTransformation(), tree, node, patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
                resultTree.setLevel(tree.getLevel());
                
                ruleEval.resolveNode(resultTree);
                
                cache.put(newContext, resultTree);
            }
            
            //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
            if (resultTree.isSuccess()) {
                final Collection newGoal = new ArrayList(goal);
                newGoal.remove(literal);

                Collection solutions = solutions(resultTree, pDefVars);
                for (Iterator itr = solutions.iterator(); itr.hasNext(); ) {
                    Binding currentSolution = (Binding) itr.next();
                    Binding unifier = createOutputBinding(node, pDefVars, args, currentSolution);

                    tree.createBranch(node, unifier, newGoal);
                }
            } else {
                tree.failure(node);
            }
        } finally {        
            ruleEval.popPattern();
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
                    System.err.println("Arg:\t" + argExpr);
                    System.err.println("actual:\t" + val);
                    System.err.println("formal:\t" + formals.get(j));
                    System.err.println("result:\t" + solution.lookup((PatternVar) formals.get(j)));
                    
                    throw new ResolutionException(node, "conflicting pattern arg and result: " + val + "\t" + solution.lookup((PatternVar) formals.get(j)));
                }
            }
        }
        return unifier;
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
            return ModelUtils.getFeature(klass, featureName);
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
        Node newRoot = new Node(condGoal, context); // cannot pass node as node-context here -- see evalNegatedGoal()
        Tree newTree = new Tree(tree.getTransformation(), tree, node, newRoot, tree.getContext(), tree.getTrackingExtent(), false);
	newTree.setLevel(tree.getLevel() - 1);

        if (ruleEval.INCREMENTAL) {
            final List newGoal = new ArrayList(goal);
            newGoal.remove(literal);
            // Add THEN Term to beginning of new goal
            newGoal.add(0, literal.getTerm().get(1));
            
            newTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) {
                    Binding sContext = new Binding(answer);
                    tree.createBranch(node, sContext, newGoal);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        // Replace THEN Term with the ELSE Term
                        newGoal.set(0, literal.getTerm().get(2));
                        tree.createBranch(node, null, newGoal);
                    }
                }

                public void floundered(Tree theTree) {
                }
                
            });

	    ruleEval.addUnresolvedTree(newTree);
        } else {
            ruleEval.resolveNode(newTree);
            
            List newGoal = new ArrayList(goal);
            newGoal.remove(literal);

            if (newTree.isSuccess()) {
                newGoal.add(0, literal.getTerm().get(1));
                for (final Iterator itr = newTree.getAnswers().iterator(); itr.hasNext(); ) {
                    Binding answer = (Binding) itr.next();
                    Binding sContext = new Binding(answer);
                    tree.createBranch(node, sContext, newGoal);
                }
            } else {
                newGoal.add(0, literal.getTerm().get(2));
                tree.createBranch(node, null, newGoal);
            }
        }
    }

}
