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

package tefkat.engine;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import tefkat.model.*;

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
        counter++;
        
        binding.freeze();
        
        this.goal = goal;
        this.bindings = binding;
        this.parentNode = parent;
        isSuccess = isFailure = false;
        
        if (null != parent && null != parent.getDelayed()) {
            goal.addAll(parent.getDelayed());
        }
    }
    
    public Node(Collection goal, Binding binding) {
        this(goal, binding, null);
    }
    
    public void delay() {
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

    void selectLiteral(Term literal) {
        if (goal.contains(literal)) {
            selectedLiteral = literal;
        }
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
}
