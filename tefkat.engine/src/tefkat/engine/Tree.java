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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tefkat.model.Extent;


public class Tree {

    static int counter = 0;

    private final Extent trackingExtent;

    private final Binding context;

    private final Context parentContext;

    private final Collection answers = new ArrayList();

    private final List<Node> unresolvedNodes = new ArrayList<Node>();

    private final Set listeners = new HashSet();

    private final boolean isNegation;

    private boolean completed;

    private int level = Integer.MAX_VALUE;



    public Tree(Context parentContext, Node node, Binding context, Extent trackingExtent, boolean isNegation) {
        increment();

        this.parentContext = parentContext;
        this.context = context;
        this.trackingExtent = trackingExtent;
        this.isNegation = isNegation;

        if (null != node) {
            addUnresolvedNode(node);
        }
    }

    private static void increment() {
        counter++;
    }

    public void addTreeListener(TreeListener listener) {
        listeners.add(listener);
    }

    public void removeTreeListener(TreeListener listener) {
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

    // FIXME MH: this shouldn't really need to be done, when I get around to it
    //           the info should be exposed in a more controlled manner through
    //           the callback interface.
    public List<Node> getUnresolvedNodes() {
        return this.unresolvedNodes;
    }

    Node getUnresolvedNode() {
        if (unresolvedNodes.size() > 0) {
            return (Node) unresolvedNodes.remove(0);
        } else {
            return null;
        }
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
    // FIXME MH: when does this method get called for parent == null
    void createBranch(Node parent, Binding unifier, Collection childGoal) {
        Node child;
        if (null != parent) {
            if (null == unifier) {
                unifier = parent.getBindings(); // Inherit bindings from parent
            } else {
                unifier.composeLeft(parent.getBindings());
            }
            child = parent.createChild(childGoal, unifier);
        } else {
            child = new Node(childGoal, unifier);
        }
        addUnresolvedNode(child);
    }

    public void completed() {
        completed = true;

        // Need to avoid problems if the TreeListener tries to remove itself
        Object[] array = listeners.toArray();
        for (int i = array.length - 1; i >= 0; i--) {
            TreeListener listener = (TreeListener) array[i];
            listener.completed(this);
        }
    }

    Node flounder(Collection reasons) {
        // Tell any listeners so they can clean up
        floundered();

        if (null != parentContext) {
            // Delay the node that originally created this tree
            parentContext.node.delay(reasons);
//            // grab the currently delayed terms
//            final Collection delayed = parentContext.node.getDelayed();
//            // traverse this Tree to find non-failed leaf Nodes and compute the output binding
//            // for each output binding, unifier, create a branch in the parent Tree
//            parentContext.tree.createBranch(parentContext.node, unifier, parentContext.node.goal());
            parentContext.tree.addUnresolvedNode(parentContext.node);
            return parentContext.node;
        }
        return null;
    }

    private void floundered() {
        for (final Iterator itr = listeners.iterator(); itr.hasNext(); ) {
            TreeListener listener = (TreeListener) itr.next();
            listener.floundered(this);
        }
    }

    public void success(Node node) throws ResolutionException {
        node.setIsSuccess(true);

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

    public Collection getAnswers() {
        return Collections.unmodifiableCollection(answers);
    }

    public boolean isSuccess() {
        return (answers.size() > 0);
    }

    public boolean isCompleted() {
        return completed;
    }

    boolean isNegation() {
        return isNegation;
    }

}

