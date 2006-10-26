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
import java.util.HashMap;
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

    private abstract class PatternCall
        implements Function2 {
        private final List vars;

        PatternCall(List vars) {
            this.vars = vars;
        }

        public Object call(Object[] params) {
            throw new Error("Internal error: this method should not be called");
        }

        /**
         * @param context a set of variable bindings that needs to be propagated to the answer Binding
         * @param actuals actual values or WrappedVaes for the pDefVars for a call
         */
        public Object call(Binding context, Object[] actuals) throws ResolutionException {
            Binding parameterContext = new Binding();
            Binding callContext = new Binding(context);
            
//            System.err.println(pDefn.getName());
//            System.err.println("    C: " + context);
//            System.err.println("    P: " + Arrays.asList(actuals));
            
            // Example calls to p(X,Y,Z) that we must handle:
            //   1. Foo A AND p(A, A, A)       -> {}, [A/Foo, A/Foo, A/Foo]
            //   2. Foo A AND p(A, A.a, A.b)   -> {A -> 1:Foo}, [1:Foo, 2, 3]
            //
            // 1. varContext = {A -> W(X/Foo)}
            //    parameterContext = {X -> W(X/Foo), Y -> W(X/Foo), Z -> W(X/Foo)}
            //
            // 2. varContext = {A -> 1:Foo}
            //    parameterContext = {X -> 1:Foo, Y -> 2, Z -> 3}
            //
            for (int i = 0; i < actuals.length; i++) {
                Var pVar = (Var) vars.get(i);
                Object varValue;
                if (actuals[i] instanceof WrappedVar) {
                    WrappedVar wrappedActual = (WrappedVar) actuals[i];
                    Var var = wrappedActual.getVar();
                    varValue = callContext.lookup(var);
                    if (null == varValue) {
                        WrappedVar wvar = new WrappedVar(pVar);
                        wvar.setExtent(wrappedActual.getExtent());
                        wvar.setType(wrappedActual.getType(), wrappedActual.isExact());
                        
                        callContext.add(var, wvar);
                        varValue = wvar;
                    }
                } else {
                    varValue = actuals[i];
                }
                if (null != varValue) {
                    parameterContext.add(pVar, varValue);
                }
//                System.err.println("    " + i + " " + pVar + " = " + varValue);
            }
//            System.err.println("    V: " + varContext);
            
            invoke(callContext, parameterContext);
            
            return Collections.EMPTY_LIST;
        }
        
        abstract void invoke(Binding callContext, Binding parameterContext) throws ResolutionException;
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
//            Var v = (Var) i.next();
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
    protected void doResolveNode(final Tree tree, final Node node, final Term literal, final boolean isNegation)
    throws ResolutionException, NotGroundException {

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
            resolveMofInstance(tree, node, (MofInstance) literal);
            break;
            
        case TefkatPackage.TRACKING_USE:
            resolveTrackingUse(tree, node, (TrackingUse) literal);
            break;
            
        case TefkatPackage.PATTERN_USE:
            resolvePatternUse(tree, node, (PatternUse) literal, isNegation);
            break;
            
        case TefkatPackage.NOT_TERM:
            resolveNotTerm(tree, node, (NotTerm) literal);
            break;
            
        case TefkatPackage.OR_TERM:
            resolveOrTerm(tree, node, (OrTerm) literal);
            break;
            
        case TefkatPackage.AND_TERM:
            resolveAndTerm(tree, node, (AndTerm) literal);
            break;
            
        case TefkatPackage.CONDITION:
            resolveCondition(tree, node, (Condition) literal);
            break;
            
        case TefkatPackage.IF_TERM:
            resolveIfTerm(tree, node, (IfTerm) literal);
            break;
            
        case TefkatPackage.MOF_ORDER:
            resolveMofOrder(tree, node, (MofOrder) literal);
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
     * @param order
     */
    protected abstract void resolveMofOrder(Tree tree, Node node, MofOrder term)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveCondition(Tree tree, Node node, Condition literal)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveTrackingUse(Tree tree, Node node,
            TrackingUse literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     * @return
     */
    protected abstract boolean resolveMofInstance(Tree tree, Node node,
            MofInstance literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveOrTerm(Tree tree, Node node,
            OrTerm literal) throws ResolutionException ;

    /**
     * @param tree
     * @param node
     * @param literal
     * @return
     */
    protected abstract void resolveNotTerm(Tree tree, Node node,
            NotTerm literal) throws ResolutionException, NotGroundException;
    
    /**
     * Resolve the PatternDefn passing in a set of bindings for the vars.
     * Need to do this on a new tree so that a fresh set of variables is used
     * in case there is recursion. 
     *
     * @param tree
     * @param node
     * @param literal
     * @param isNegation
     * @return
     * 
     * TODO Cache PatternUse calls to trap infinite recursion.
     */
    protected void resolvePatternUse(final Tree tree, final Node node, final PatternUse literal, final boolean isNegation)
    throws ResolutionException, NotGroundException {

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

            Collection newGoal = new ArrayList(node.goal());
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
                incrementalResolvePatternDefn(tree, node,
                        literal, pDefVars, args, pDefn, null, tree.getContext(), isNegation);
            } else {
                resolvePatternDefn(tree, node,
                        literal, pDefVars, args, pDefn, null, tree.getContext(), isNegation);
            }
        } else {
//            final Binding context = tree.getContext();
            Function resolver;
            if (ruleEval.INCREMENTAL) {
                resolver = new PatternCall(pDefVars) {
                    void invoke(Binding callContext, Binding parameterContext) throws ResolutionException {
                        parameterContext.composeRight(tree.getContext());
                        incrementalResolvePatternDefn(tree, node,
                                literal, pDefVars, args, pDefn,
                                callContext, parameterContext, isNegation);
                    }
                };
            } else {
                resolver = new PatternCall(pDefVars) {
                    void invoke(Binding callContext, Binding parameterContext) throws ResolutionException {
                        parameterContext.composeRight(tree.getContext());
                        resolvePatternDefn(tree, node,
                                literal, pDefVars, args, pDefn,
                                callContext, parameterContext, isNegation);
                    }
                };
            }
            
            exprEval.evalAll(node, node.getBindings(), args, resolver);

        }
    }

    private void incrementalResolvePatternDefn(final Tree tree, final Node node, final PatternUse literal,
            final List pDefVars, final List args, final PatternDefn pDefn,
            final Binding callContext, final Binding parameterContext, final boolean isNegation)
    throws ResolutionException {

        final Collection newGoal = new ArrayList(node.goal());
        newGoal.remove(literal);

        final Map cache = ruleEval.getPatternCache(pDefn);
        Tree resultTree = (Tree) cache.get(parameterContext);
        
        if (null == resultTree) {
            Collection patGoal = new ArrayList();
            Term pDefTerm = pDefn.getTerm();
            patGoal.add(pDefTerm);

//            System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
            Node patternNode = new Node(patGoal, parameterContext);
        
            resultTree = new Tree(tree.getTransformation(), tree, node, patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
            resultTree.setLevel(tree.getLevel());
            
            ruleEval.addUnresolvedTree(resultTree);

            cache.put(parameterContext, resultTree);
        }

        if (!resultTree.isCompleted()) {
            // Register listener for any new solutions
            
            resultTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) throws ResolutionException {
                    Binding unifier = createOutputBinding(node, callContext, pDefVars, args, answer);

                    tree.createBranch(node, unifier, newGoal);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        tree.failure(node);
                    }
                }

                public void floundered(Tree theTree) {
                    cache.remove(parameterContext);
                }
                
            });
        }
        
        //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
        if (resultTree.isSuccess()) {
            // Process any existing solutions
            
            Collection solutions = solutions(resultTree, pDefVars);
            for (Iterator itr = solutions.iterator(); itr.hasNext();) {
                Binding answer = (Binding) itr.next();
                Binding unifier = createOutputBinding(node, callContext, pDefVars, args, answer);

                tree.createBranch(node, unifier, newGoal);
            }
        }
    }
    
    private void resolvePatternDefn(final Tree tree, final Node node, final PatternUse literal,
            final List pDefVars, final List args, final PatternDefn pDefn,
            final Binding callContext, final Binding parameterContext, final boolean isNegation)
    throws ResolutionException {
        try {
            ruleEval.pushPattern(pDefn);
            Tree resultTree = null;
            
            Map cache = ruleEval.getPatternCache(pDefn);
            resultTree = (Tree) cache.get(parameterContext);
            
            if (null == resultTree) {
                Collection patGoal = new ArrayList();
                Term pDefTerm = pDefn.getTerm();
                patGoal.add(pDefTerm);
                
//              System.err.println("resolving " + patGoal + "\n  " + newContext);      // TODO delete
                Node patternNode = new Node(patGoal, parameterContext);
                
                resultTree = new Tree(tree.getTransformation(), tree, node, patternNode, tree.getContext(), tree.getTrackingExtent(), isNegation);
                resultTree.setLevel(tree.getLevel());
                
                ruleEval.resolveNode(resultTree);
                
                cache.put(parameterContext, resultTree);
            }
            
            //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
            if (resultTree.isSuccess()) {
                final Collection newGoal = new ArrayList(node.goal());
                newGoal.remove(literal);

                Collection solutions = solutions(resultTree, pDefVars);
                for (Iterator itr = solutions.iterator(); itr.hasNext(); ) {
                    Binding currentSolution = (Binding) itr.next();
                    Binding unifier = createOutputBinding(node, callContext, pDefVars, args, currentSolution);

                    tree.createBranch(node, unifier, newGoal);
                }
            } else {
                tree.failure(node);
            }
        } finally {        
            ruleEval.popPattern();
        }
    }

    static private Binding createOutputBinding(final Node node, final Binding context, final List formals, final List actuals, Binding solution) throws ResolutionException {
        Binding unifier = new Binding(context);
        Map linkMap = new HashMap();
        // System.err.println("  s " + currentSolution); // TODO delete
        for (int j = 0; j < actuals.size(); j++) {
            Expression argExpr = (Expression) actuals.get(j);
            if (argExpr instanceof VarUse) {
                final Var var = ((VarUse) argExpr).getVar();
                final Object val = node.lookup(var);
                Object outVal = solution.lookup((Var) formals.get(j));

                // Handle case where unbound input params are unified
                // but not grounded within the pattern.
                // For example: p(A, B) call to
                //    PATTERN p(X, Y) WHERE X = Y;
                // should bind A to B, but not to X or Y.
		//
                if (outVal instanceof WrappedVar) {
                    if (linkMap.containsKey(outVal)) {
                        outVal = linkMap.get(outVal);
                    } else {
                        WrappedVar wVar = new WrappedVar(var);
                        wVar.setExtent(((WrappedVar) outVal).getExtent());
                        wVar.setType(((WrappedVar) outVal).getType(),
                                     ((WrappedVar) outVal).isExact());
                        linkMap.put(outVal, wVar);
                        outVal = wVar;
                    }
                }

                if (null == val) {
                    // We only add a new binding if argExpr is not already
                    // bound, otherwise we end up with multiple identical bindings
                    // for the same Var.

                    unifier.add(var, outVal);
                } else if (val instanceof WrappedVar) {
                    Binding.bindWrappedVar(unifier, (WrappedVar) val, outVal);
                } else if (!val.equals(outVal)) {
//                    System.err.println("Arg:\t" + argExpr);
//                    System.err.println("actual:\t" + val);
//                    System.err.println("formal:\t" + formals.get(j));
//                    System.err.println("result:\t" + outVal);
                    
                    throw new ResolutionException(node, "conflicting pattern arg and result: " + val + "\t" + outVal);
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
    protected Term selectLiteral(final Node node) {
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
            if ((literals[i] instanceof PatternUse) &&
                    null == ((PatternUse) literals[i]).getDefn()) {
                continue;
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

    protected void resolveAndTerm(final Tree tree, final Node node, final AndTerm literal)
    throws ResolutionException {
        /**
         *  Add the conjuncts of this literal into the goal.
         */
        Collection terms = literal.getTerm();
        if (null == terms) {
            throw new ResolutionException(node,
                    "Malformed (null) AndTerm contents");
        }
        List newGoal = new ArrayList(node.goal());
        newGoal.remove(literal);
        newGoal.addAll(0, terms);
        tree.createBranch(node, null, newGoal);
    }
    
    static protected EStructuralFeature getFeature(Node node, EClass klass, String featureName)
        throws ResolutionException {
        try {
            return ModelUtils.getFeature(klass, featureName);
        } catch (RuntimeException e) {
            throw new ResolutionException(node, e.getMessage(), e);
        }
    }

    protected void resolveIfTerm(final Tree tree, final Node node, final IfTerm literal)
    throws ResolutionException, NotGroundException {
        
        // Ensure that all non-local variables are already bound
        for (Iterator itr = literal.getNonLocalVars().iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
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
            final List newGoal = new ArrayList(node.goal());
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
                    // nothing to do in this case
                }
                
            });

            ruleEval.addUnresolvedTree(newTree);
        } else {
            ruleEval.resolveNode(newTree);
            
            List newGoal = new ArrayList(node.goal());
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
