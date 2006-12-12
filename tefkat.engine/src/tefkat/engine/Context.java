package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Var;

final class Context {

    final private RuleEvaluator ruleEval;
    final private Evaluator exprEval;
    final Tree tree;
    final Node node;

    Context(RuleEvaluator ruleEval, Evaluator exprEval, Tree tree, Node node) {
        this.ruleEval = ruleEval;
        this.exprEval = exprEval;
        this.tree = tree;
        this.node = node;
    }
    
    void createBranch() {
        List newGoal = newGoal();
        tree.createBranch(node, null, newGoal);
    }
    
    void createBranch(Term term) {
        createBranch(term, null);
    }
    
    void createBranch(Binding unifier) {
        List newGoal = newGoal();
        tree.createBranch(node, unifier, newGoal);
    }
    
    void createBranch(Term term, Binding unifier) {
        List newGoal = newGoal();
        newGoal.add(term);
        tree.createBranch(node, unifier, newGoal);
    }
    
    void createBranch(Collection terms) {
        List newGoal = newGoal();
        newGoal.addAll(terms);
        tree.createBranch(node, null, newGoal);
    }
    
    void delay(String message) throws NotGroundException {
        throw new NotGroundException(message);
    }
    
    void error(String message) throws ResolutionException {
        throw new ResolutionException(node, message);
    }
    
    void error(String message, Exception e) throws ResolutionException {
        throw new ResolutionException(node, message, e);
    }

    private List newGoal() {
        List newGoal = new ArrayList(node.goal());
        newGoal.remove(node.selectedLiteral());
        return newGoal;
    }

    void fail() {
        node.setIsFailure(true);
    }

    /**
     * Find a binding for the variable in the current context.
     * 
     * @param var   The var to lookup in this context
     * @return      The value that var is bound to in the context of this node or null
     */
    Object lookup(Var var) {
        return node.lookup(var);
    }
    
    Tree createTree(Collection goal, Binding unifier, boolean isNegation, boolean subTree) {
        return createTree(new Node(goal, unifier), isNegation, subTree);
    }

    Tree createTree(Node newRoot, boolean isNegation, boolean subTree) {
        Tree result = new Tree(this, newRoot, tree.getContext(), tree.getTrackingExtent(), isNegation);
        if (subTree) {
            result.setLevel(tree.getLevel()-1);
        } else {
            result.setLevel(tree.getLevel());
        }
        
        if (ruleEval.INCREMENTAL) {
            ruleEval.addUnresolvedTree(result);
        }
        
        return result;
    }

    Binding getBindings() {
        return node.getBindings();
    }

    List expand(WrappedVar var) throws NotGroundException {
        return exprEval.expand(var);
    }

    EObject lookup(List keys, TRule rule) {
        return ruleEval.injections.lookup(tree.getTrackingExtent(), keys, rule);
    }

    void warn(String string) {
        ruleEval.fireWarning(string);
    }

    Map getNameMap() {
        return ruleEval.nameMap;
    }

    Object fetchFeature(String featureName, Object obj) {
        return exprEval.fetchFeature(featureName, obj);
    }

    void addPartialOrder(Object inst, Object feat, Object lesser, Object greater) {
        ruleEval.addPartialOrder(inst, feat, lesser, greater);
    }

    void fireInfo(String mesg) {
        ruleEval.fireInfo(mesg);
    }
    
    Tree getResultTree(final Collection goal, final Binding unifier) {
        final Map cache = ruleEval.getPatternCache(goal);
        final Binding parameterContext;
        if (null == unifier) {
            parameterContext = tree.getContext();
        } else {
            parameterContext = unifier;
            parameterContext.composeRight(tree.getContext());
        }
        Tree resultTree = (Tree) cache.get(parameterContext);
        
        if (null == resultTree) {
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

}
