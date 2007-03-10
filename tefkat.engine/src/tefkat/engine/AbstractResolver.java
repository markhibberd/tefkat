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
    
    class VarExpander {
        final private Context context;
        final private List vars;
        final private Function function;

        VarExpander(Context context, List vars, Function function, Binding unifier)
        throws NotGroundException, ResolutionException {
            this.context = context;
            this.vars = vars;
            this.function = function;

            expandVars(0, unifier);
        }
        
        private void expandVars(final int idx, final Binding unifier)
        throws NotGroundException, ResolutionException {
            if (idx == vars.size()) {
                // reached end of list of vars, now do the work
                final Object[] args = {unifier};
                function.call(context, args);
                return;
            }

            Var var = (Var) vars.get(idx);
            Object value = unifier.lookup(var);
            if (value instanceof WrappedVar) {
                List objs = exprEval.expand((WrappedVar) value);
                for (final Iterator itr = objs.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                    Binding unifier2 = new Binding(unifier);
                    unifier2.add(var, obj);
                    expandVars(idx + 1, unifier2);
                }
            } else {
                expandVars(idx + 1, unifier);
            }
        }
    }

    private abstract class PatternCall
        implements Function2 {
        private final List vars;

        PatternCall(List vars) {
            this.vars = vars;
        }

        public Object call(Context context, Object[] params) {
            throw new UnsupportedOperationException("Internal error: this method should not be called");
        }

        /**
         * @param binding a set of variable bindings that needs to be propagated to the answer Binding
         * @param actuals actual values or WrappedVaes for the pDefVars for a call
         */
        public Object call(Context context, Binding binding, Object[] actuals) throws ResolutionException {
            Binding parameterContext = new Binding();
            Binding callContext = new Binding(binding);
            
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
    
    final protected RuleEvaluator ruleEval;

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
     * @param tree
     * @param node
     * @param goal
     * @return
     * @throws ResolutionException
     */
    protected void doResolveNode(final Context context, final Term literal)
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
            resolveMofInstance(context, (MofInstance) literal);
            break;
            
        case TefkatPackage.TRACKING_USE:
            resolveTrackingUse(context, (TrackingUse) literal);
            break;
            
        case TefkatPackage.PATTERN_USE:
            resolvePatternUse(context, (PatternUse) literal);
            break;
            
        case TefkatPackage.NOT_TERM:
            resolveNotTerm(context, (NotTerm) literal);
            break;
            
        case TefkatPackage.OR_TERM:
            resolveOrTerm(context, (OrTerm) literal);
            break;
            
        case TefkatPackage.AND_TERM:
            resolveAndTerm(context, (AndTerm) literal);
            break;
            
        case TefkatPackage.CONDITION:
            resolveCondition(context, (Condition) literal);
            break;
            
        case TefkatPackage.IF_TERM:
            resolveIfTerm(context, (IfTerm) literal);
            break;
            
        case TefkatPackage.MOF_ORDER:
            resolveMofOrder(context, (MofOrder) literal);
            break;
            
        default:
            context.error("Unrecognised term type: " + literal);
        }
        
        final long end = System.currentTimeMillis();
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
    protected abstract void resolveMofOrder(Context context, MofOrder term)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveCondition(Context context, Condition literal)
    throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveTrackingUse(Context context,
            TrackingUse literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     * @return
     */
    protected abstract boolean resolveMofInstance(Context context,
            MofInstance literal) throws ResolutionException, NotGroundException;

    /**
     * @param tree
     * @param node
     * @param literal
     */
    protected abstract void resolveOrTerm(Context context,
            OrTerm literal) throws ResolutionException ;

    /**
     * @param tree
     * @param node
     * @param literal
     * @return
     */
    protected abstract void resolveNotTerm(Context context,
            NotTerm literal) throws ResolutionException, NotGroundException;
    
    /**
     * Resolve the PatternDefn passing in a set of bindings for the vars.
     * Need to do this on a new tree so that a fresh set of variables is used
     * in case there is recursion. 
     *
     * @param tree
     * @param node
     * @param literal
     * @return
     * 
     * TODO Cache PatternUse calls to trap infinite recursion.
     */
    protected void resolvePatternUse(final Context context, final PatternUse literal)
    throws ResolutionException, NotGroundException {

        final PatternDefn pDefn = literal.getDefn();
        final List args = literal.getArg();

        if (null == pDefn) { // TODO fix this println hack
            String mesg = "";
            for (int i = 0; i < args.size(); i++) {
                String str;
                try {
                    List values = exprEval.eval(context, (Expression) args.get(i));
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

            context.createBranch();
            return;
        }

        final List pDefVars = pDefn.getParameterVar();

        // Check that the sizes of formals and actuals matches, then add the bound variables, pairwise,
        // to the initial binding to be passed to the tree resolution of the pattern.
        if (args.size() != pDefVars.size()) {
            context.error("Invalid arity for pattern "
                    + pDefn.getName() + ": " + args.size());
        }
        
        // handle arity-zero (no args) pattern
        if (pDefVars.size() == 0) {
            if (ruleEval.INCREMENTAL) {
                incrementalResolvePatternDefn(context,
                        literal, pDefVars, args, pDefn, null, context.tree.getContext());
            } else {
                resolvePatternDefn(context,
                        literal, pDefVars, args, pDefn, null, context.tree.getContext());
            }
        } else {
//            final Binding context = tree.getContext();
            Function resolver;
            if (ruleEval.INCREMENTAL) {
                resolver = new PatternCall(pDefVars) {
                    void invoke(Binding callContext, Binding parameterContext) throws ResolutionException {
                        parameterContext.composeRight(context.tree.getContext());
                        incrementalResolvePatternDefn(context,
                                literal, pDefVars, args, pDefn,
                                callContext, parameterContext);
                    }
                };
            } else {
                resolver = new PatternCall(pDefVars) {
                    void invoke(Binding callContext, Binding parameterContext) throws ResolutionException {
                        parameterContext.composeRight(context.tree.getContext());
                        resolvePatternDefn(context,
                                literal, pDefVars, args, pDefn,
                                callContext, parameterContext);
                    }
                };
            }
            
            exprEval.evalAll(context, context.node.getBindings(), args, resolver);

        }
    }

    private void incrementalResolvePatternDefn(final Context context, final PatternUse literal,
            final List pDefVars, final List args, final PatternDefn pDefn,
            final Binding callContext, final Binding parameterContext)
    throws ResolutionException {

        final Collection goal = new ArrayList();
        goal.add(pDefn.getTerm());
        
        Tree resultTree = context.getResultTree(goal, parameterContext);

        if (!resultTree.isCompleted()) {
            // Register listener for any new solutions
            
            resultTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) throws ResolutionException {
                    Binding unifier = createOutputBinding(context, callContext, pDefVars, args, answer);

                    context.createBranch(unifier);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        context.fail();
                    }
                }

                public void floundered(Tree theTree) {
                }
                
            });
        }
        
        if (resultTree.isSuccess()) {
            // Process any existing solutions
            
            for (Iterator itr = resultTree.getAnswers().iterator(); itr.hasNext();) {
                Binding answer = (Binding) itr.next();
                Binding unifier = createOutputBinding(context, callContext, pDefVars, args, answer);

                context.createBranch(unifier);
            }
        }
    }
    
    private void resolvePatternDefn(final Context context, final PatternUse literal,
            final List pDefVars, final List args, final PatternDefn pDefn,
            final Binding callContext, final Binding parameterContext)
    throws ResolutionException {
        try {
            ruleEval.pushPattern(pDefn);

            final Collection goal = new ArrayList();
            goal.add(pDefn.getTerm());
            
            Tree resultTree = context.getResultTree(goal, parameterContext);
            
            if (resultTree.isSuccess()) {
                for (Iterator itr = resultTree.getAnswers().iterator(); itr.hasNext(); ) {
                    Binding currentSolution = (Binding) itr.next();
                    Binding unifier = createOutputBinding(context, callContext, pDefVars, args, currentSolution);

                    context.createBranch(unifier);
                }
            } else {
                context.fail();
            }
        } finally {        
            ruleEval.popPattern();
        }
    }

    static private Binding createOutputBinding(final Context context, final Binding callContext, final List formals, final List actuals, Binding solution) throws ResolutionException {
        Binding unifier = new Binding(callContext);
        Map linkMap = new HashMap();
        // System.err.println("  s " + currentSolution); // TODO delete
        for (int j = 0; j < actuals.size(); j++) {
            Expression argExpr = (Expression) actuals.get(j);
            if (argExpr instanceof VarUse) {
                final Var var = ((VarUse) argExpr).getVar();
                final Object val = context.lookup(var);
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
                    
                    context.error("conflicting pattern arg and result: " + val + "\t" + outVal);
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

    protected void resolveAndTerm(final Context context, final AndTerm literal)
    throws ResolutionException {
        /**
         *  Add the conjuncts of this literal into the goal.
         */
        Collection terms = literal.getTerm();
        if (null == terms) {
            context.error("Malformed (null) AndTerm contents");
        }
        context.createBranch(terms);
    }
    
    static protected EStructuralFeature getFeature(Context context, EClass klass, String featureName)
        throws ResolutionException {
        try {
            return ModelUtils.getFeature(klass, featureName);
        } catch (RuntimeException e) {
            context.error(e.getMessage(), e);
            return null;        // notreached
        }
    }

    protected void resolveIfTerm(Context context, final IfTerm literal)
    throws ResolutionException, NotGroundException {
        
        // Ensure that all non-local variables are already ground
        // (WrappedVars are handled by the Expander)
        for (Iterator itr = literal.getNonLocalVars().iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
            Object value = context.lookup(var);
            if (null == value) {
                context.delay("Non-local variable " + var + " is not bound.");
            }
        }
        
        final Function f = new Function() {
            public Object call(Context context, Object[] params) throws ResolutionException {
                Binding unifier = (Binding) params[0];
                evalIfGoal(context, unifier , literal.getTerm());
                return null;
            }
            
        };
        
        new VarExpander(context, literal.getNonLocalVars(), f, context.getBindings());
    }
    
    private void evalIfGoal(final Context context, final Binding unifier, final List terms)
    throws ResolutionException {

        List condGoal = new ArrayList();
        condGoal.add(terms.get(0));
        // cannot pass node as context here or delayed terms will get pushed into the "NOT"
        // leading to possible spurious flounderings -- see also evalNegatedGoal 
        Tree newTree = context.createTree(condGoal, unifier, false, true);

        if (ruleEval.INCREMENTAL) {
            newTree.addTreeListener(new TreeListener() {

                public void solution(Binding answer) {
                    // THEN
                    Binding sContext = new Binding(answer);
                    context.createBranch((Term) terms.get(1), sContext);
                }

                public void completed(Tree theTree) {
                    if (!theTree.isSuccess()) {
                        // ELSE
                        context.createBranch((Term) terms.get(2), new Binding(unifier));
                    }
                }

                public void floundered(Tree theTree) {
                    // nothing to do in this case
                }
                
            });
        } else {
            ruleEval.resolveNode(newTree);
            
            if (newTree.isSuccess()) {
                // THEN
                for (final Iterator itr = newTree.getAnswers().iterator(); itr.hasNext(); ) {
                    Binding answer = (Binding) itr.next();
                    Binding sContext = new Binding(answer);
                    context.createBranch((Term) terms.get(1), sContext);
                }
            } else {
                // ELSE
                context.createBranch((Term) terms.get(2), new Binding(unifier));
            }
        }
    }

}
