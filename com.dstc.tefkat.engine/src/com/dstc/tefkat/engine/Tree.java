package com.dstc.tefkat.engine;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.dstc.tefkat.model.Extent;
import com.dstc.tefkat.model.Transformation;

public class Tree {

    static int counter = 0;
    
    private final Extent trackingExtent;
    
    private final Binding context;

    private final Transformation transformation;

    private final Collection successes = new ArrayList();
    
    private final List unresolvedNodes = new ArrayList();
    
    private final Set listeners = new HashSet();

    private final boolean isNegation;
    
    private boolean completed;
    
    private int level = Integer.MAX_VALUE;

    public Tree(Transformation transformation, Node node, Binding context, Extent trackingExtent, boolean isNegation) {
        counter++;
        
        this.transformation = transformation;
        this.context = context;
        this.trackingExtent = trackingExtent;
        this.isNegation = isNegation;
        
	if (null != node) {
	    unresolvedNodes.add(node);
	}
    }

    void addTreeListener(TreeListener listener) {
        listeners.add(listener);
    }

    void removeTreeListener(TreeListener listener) {
        listeners.remove(listener);
    }
    
    void setLevel(int level) {
        this.level = level;
    }

    int getLevel() {
        return level;
    }

    Extent getTrackingExtent() {
        return trackingExtent;
    }

    Binding getContext() {
        return context;
    }

    Node getUnresolvedNode() {
        if (unresolvedNodes.size() > 0) {
            return (Node) unresolvedNodes.remove(0);
        } else {
            return null;
        }
    }
    
    public List getUnresolvedNodes() {
        return unresolvedNodes;
    }
    
    void addUnresolvedNode(Node node) {
        // insert the node at the start of the list
        // This means we perform a depth-first traversal which will
        // make debugging easier.
        unresolvedNodes.add(0, node);
    }

    /**
     *  Create a branch from parent.  goal and
     *  unifier are associated with the new Node.  The selected literal in
     *  parent is replaced with the body of rule to create the child's goal,
     *  and this is then substituted under unifier.
     *  @param parent   The parent node for the new edge.
     *  @param unifier  The unifier of the parent's selected literal.
     */
    void createBranch(Node parent, Binding unifier, Collection childGoal) {
	if (null != parent) {
	    if (null == unifier) {
		unifier = parent.getBindings(); // Inherit bindings from parent
	    } else {
		unifier.composeLeft(parent.getBindings());
	    }
	}
        Node child = new Node(childGoal, unifier, parent);
        addUnresolvedNode(child);
    }
    
    public void completed() {
        completed = true;
        
        for (final Iterator itr = listeners.iterator(); itr.hasNext(); ) {
            TreeListener listener = (TreeListener) itr.next();
            listener.completed(this);
        }
    }

    public void success(Node node) throws ResolutionException {
        node.setIsSuccess(true);
        successes.add(node);
        
        for (final Iterator itr = listeners.iterator(); itr.hasNext(); ) {
            TreeListener listener = (TreeListener) itr.next();
            listener.solution(node);
        }
        
        if (isNegation) {
            completed();
        }
    }

    public void failure(Node node) {
        node.setIsFailure(true);
    }

    public Collection successes() {
        return successes;
    }

    public boolean isSuccess() {
        return (successes.size() > 0);
    }
    
    public boolean isCompleted() {
        return completed;
    }

    boolean isNegation() {
        return isNegation;
    }
    
    public Transformation getTransformation() {
        return transformation;
    }

}

