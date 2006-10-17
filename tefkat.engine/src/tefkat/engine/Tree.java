/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *     David Hearnden
 *
 *
 */

package tefkat.engine;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tefkat.model.Extent;
import tefkat.model.Transformation;


public class Tree {

    static int counter = 0;
    
    private final Extent trackingExtent;
    
    private final Binding context;

    private final Transformation transformation;

    private final Tree parentTree;

    private final Node parentNode;

    private final Collection answers = new ArrayList();
    
    private final List unresolvedNodes = new ArrayList();
    
    private final Set listeners = new HashSet();

    private final boolean isNegation;
    
    private boolean completed;
    
    private int level = Integer.MAX_VALUE;

    public Tree(Transformation transformation, Tree parentTree, Node parentNode, Node node, Binding context, Extent trackingExtent, boolean isNegation) {
        counter++;
        
        this.transformation = transformation;
        this.parentTree = parentTree;
        this.parentNode = parentNode;
        this.context = context;
        this.trackingExtent = trackingExtent;
        this.isNegation = isNegation;
        
        if (null != node) {
            unresolvedNodes.add(node);
        }
    }
    
    Tree getParentTree() {
        return parentTree;
    }
    
    Node getParentNode() {
        return parentNode;
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
    
    public void floundered() {
        for (final Iterator itr = listeners.iterator(); itr.hasNext(); ) {
            TreeListener listener = (TreeListener) itr.next();
            listener.floundered(this);
        }
    }

    public void success(Node node) throws ResolutionException {
        node.setIsSuccess(true);
        successes.add(node);
        
        Binding answer = node.getBindings();
        if (!answers.contains(answer)) {
            answers.add(answer);

            for (final Iterator itr = listeners.iterator(); itr.hasNext(); ) {
                TreeListener listener = (TreeListener) itr.next();
                listener.solution(answer);
            }
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

