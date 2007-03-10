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

import org.eclipse.emf.ecore.EObject;

public class Node {
    static int counter = 0;
    
    private final Binding bindings;
    private final Node parentNode;
    private final Collection goal;  // A set of tefkat.model.Terms
    
    private Collection delayed;     // A set of tefkat.model.Terms
    private Term selectedLiteral;
    private boolean isSuccess;
    private boolean isFailure;
    
    public Node(Collection goal, Binding binding, Node parent) {
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
    
    public Node(Collection goal, Binding binding) {
        this(goal, binding, null);
    }
    
    public void delay() {
        System.err.println("Delaying: " + selectedLiteral);
        if (goal.remove(selectedLiteral)) {
            if (null == delayed) {
                delayed = new ArrayList(4); // the default size of 10 is overkill
            }
            delayed.add(selectedLiteral);
        }
    }
    
    public Collection getDelayed() {
        return delayed;
    }
    
    public String toString() {
        if (null == selectedLiteral) {
            return "G: " + goal;
        }
        EObject c = selectedLiteral;
        while (null != c && !(c instanceof VarScope)) {
            c = c.eContainer();
        }
        if (null == c) {
            return "???::" + selectedLiteral;
        }
        return "S: " + c + "\n\t:: " + selectedLiteral;
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
        System.err.println("Selected: " + selectedLiteral + "\t" + bindings);
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
    public Collection goal() {
        return goal;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    public boolean isFailure() {
        return isFailure;
    }

    /**
     * Choose a literal from the goal of the given node.
     * 
     * @return A chosen literal, or null if the node's goal is empty (i.e.
     *         success)
     * @throws ResolutionException 
     */
    Term selectLiteral() throws ResolutionException {
        Term[] literals = (Term[]) goal().toArray(new Term[goal().size()]);
    
        // Simple selection rule:
        //    + select non-target, non-negation terms first
        //    + select non-target terms next
        //    + select Injections next
        //    + select target MofInstances next
        //    + select anything else (target terms) last
        //
        for (int i = 0; i < literals.length; i++) {
            if (!(literals[i].isTarget() || literals[i] instanceof NotTerm)) {
                setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }
    
        for (int i = 0; i < literals.length; i++) {
            if (!literals[i].isTarget()) {
                setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }
        
        if (null != getDelayed() && !getDelayed().isEmpty()) {
            throw new ResolutionException(this, "Flounder: All source terms delayed.");
        }
    
        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof Injection) {
                setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }
    
        for (int i = 0; i < literals.length; i++) {
            if (literals[i] instanceof MofInstance) {
                setSelectedLiteral(literals[i]);
                return literals[i];
            }
        }
    
        if (literals.length > 0) {
            setSelectedLiteral(literals[0]);
            return literals[0];
        }
    
        throw new ResolutionException(this,
                "Could not select a valid literal from goal: " + goal());
    }
}
