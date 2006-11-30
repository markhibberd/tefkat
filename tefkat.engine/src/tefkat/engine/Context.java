package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tefkat.model.Term;
import tefkat.model.Var;

public class Context {

    final Tree tree;
    final Node node;

    Context(Tree tree, Node node) {
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
    
    void createBranch(Binding unifier) {
        List newGoal = newGoal();
        tree.createBranch(node, unifier, newGoal);
    }
    
    void createBranch(Term term, Binding unifier) {
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
        throw new NotGroundException(message);
    }
    
    public void error(String message) throws ResolutionException {
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

    public void fail() {
        tree.failure(node);
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
        Tree result = new Tree(tree.getTransformation(), this, newRoot, tree.getContext(), tree.getTrackingExtent(), isNegation);
        if (subTree) {
            result.setLevel(tree.getLevel()-1);
        } else {
            result.setLevel(tree.getLevel());
        }
        return result;
    }
    
}
