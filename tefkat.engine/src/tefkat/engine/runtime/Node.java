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
 *
 */

package tefkat.engine.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

public class Node {
    static int counter = 0;
    
    private final Binding bindings;
    private final Node parentNode;
    private final Collection<Term> goal;  // A set of tefkat.model.Terms
    
    private Collection<Term> delayed;     // A set of tefkat.model.Terms
    private Collection<NotGroundException> delayReasons;
    private Term selectedLiteral;
    private boolean isSuccess;
    private boolean isFailure;
    
    public Node(Collection<Term> goal, Binding binding, Node parent) {
        incrementCounter();
        
        binding.freeze();
        
        this.goal = goal;
        this.bindings = binding;
        this.parentNode = parent;
        isSuccess = isFailure = false;
        
        if (null != parent && null != parent.getDelayed()) {
            goal.addAll(parent.getDelayed());
        }
    }

    private static void incrementCounter() {
        counter++;
    }
    
    public Node(Collection<Term> goal, Binding binding) {
        this(goal, binding, null);
    }
    
    public void delay(final NotGroundException reason) {
        System.err.println("Delaying: " + selectedLiteral);
        if (goal.remove(selectedLiteral)) {
            if (null == delayed) {
                delayed = new ArrayList<Term>(4); // the default size of 10 is overkill
                delayReasons = new ArrayList<NotGroundException>(4);
            }
            delayed.add(selectedLiteral);
            delayReasons.add(reason);
        }
    }

    public void delay(Collection<NotGroundException> reasons) {
        if (goal.remove(selectedLiteral)) {
            if (null == delayed) {
                delayed = new ArrayList<Term>(4); // the default size of 10 is overkill
                delayReasons = new ArrayList<NotGroundException>(4);
            }
            delayed.add(selectedLiteral);
            delayReasons.addAll(reasons);
        }
    }
    
    public Collection<Term> getDelayed() {
        return delayed;
    }
    
    public Collection<NotGroundException> getDelayReasons() {
        return delayReasons;
    }
    
    public String toString() {
        if (null == selectedLiteral) {
            return "Goal Terms: " + goal;
        }
        EObject c = selectedLiteral;
        while (null != c && !(c instanceof VarScope)) {
            c = c.eContainer();
        }
        if (null == c) {
            return "Unknown Rule/Pattern/Template:\n    Term: " + selectedLiteral;
        }
        return c + ":\n    Term: " + selectedLiteral;
    }

    /**
     * Find a binding for the variable in the current context.
     * 
     * @param var   The var to lookup in this context
     * @return      The value that var is bound to in the context of this node or null
     */
    public Object lookup(Var var) {
        return getBindings().lookup(var);
    }

    public Binding getBindings() {
        return bindings;
    }

    void setSelectedLiteral(Term literal) {
        if (goal.contains(literal)) {
            selectedLiteral = literal;
        }
//        if (literal.eContainer() instanceof TRule) {
//            final String name = ((TRule) literal.eContainer()).getName();
////            if (name.startsWith("link")) {
////                System.err.println(name);
////                System.err.println("Selected: " + selectedLiteral + "\t" + bindings);
////            }
//        }
    }

    public Node getParentNode() {
        return parentNode;
    }

    void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    void setIsFailure(boolean isFailure) {
        this.isFailure = isFailure;
    }
    public Term selectedLiteral() {
        return selectedLiteral;
    }
    public Collection<Term> goal() {
        return goal;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    public boolean isFailure() {
        return isFailure;
    }
    
}
