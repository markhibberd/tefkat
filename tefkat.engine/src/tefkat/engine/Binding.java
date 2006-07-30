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

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import tefkat.model.AbstractVar;
import tefkat.model.VarUse;


/**
 *
 *  Represents a mapping from Variables to Objects.
 *  Supports the following operations between unifiers:
 *
 *      u.composeLeft(t)     u <- u o t
 *      u.composeRight(t)    u <- t o u
 *
 *  Constraints maintained by this class:
 *
 *  keySet().intersect(values).isEmpty()
 *
 *  @author David Hearnden, 2003
 *  @author Michael Lawley, 2003-2005
 */
public class Binding {
    static int counter = 0;

    private boolean frozen = false;
    final private Map varToTerm;    // Each entry is a AbstractVar -> Object
                                    // Note, Object may be a WrappedVar...

    /**
     *  Constructs an empty unifier.
     */
    public Binding() {
        this(null);
    }

    /**
     *  Constructs a duplicate unifier.
     *  @param unifier  The unifier to duplicate.
     */
    public Binding(Binding unifier) {
        counter++;
        if (null == unifier) {
            varToTerm = new HashMap();
        } else {
            varToTerm = new HashMap(unifier.varToTerm);
        }
    }
    
    
    /**
     * Removes all bindings to leave an empty unifier.
     */
    public void clear() {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        varToTerm.clear();
    }
    
    public int size() {
        return varToTerm.size();
    }

    /**
     *  Puts a binding from var to val into this unifier.  This should only be
     *  done if var is not in the range of the unifier.  If this is not the 
     *  case, then add(var, val) should be used.
     *
     *  Precondition:  
     *      !(varToTerm.values().contains(var))
     *      !(val.variables().contains(var))        (occurs check)
     *  @param var   The variable to bind.
     *  @param val  The value to bind the variable to.
     */
    public void put(AbstractVar var, Object val) {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        if (null == val) {
            throw new BindingError("Attempting to bind " + var + " to null");
        }
        varToTerm.put(var, val);
    }
    

    /**
     *  Adds the binding from the var referenced by varUse to val into this unifier @see #add(AbstractVar, Object).
     *
     *  Precondition:  
     *      !(varToTerm.keySet().contains(var))
     *      !(val.variables().contains(var))        (occurs check)
     *  @param varUse   The reference to the variable to bind.
     *  @param val  The value to bind the variable to.
     */
    public void add(VarUse varUse, Object val) throws ResolutionException {
        add(varUse.getVar(), val);
    }

    /**
     *  Adds the binding from var to val into this unifier, first substituting
     *  away any instances of var in the range of the unifier.  If var is not in
     *  the range of the unifier, then put(var, val) should be used for 
     *  efficiency.
     *
     *  Precondition:  
     *      !(varToTerm.keySet().contains(var))
     *      !(val.variables().contains(var))        (occurs check)
     *  @param var  The variable to bind.
     *  @param val  The value to bind the variable to.
     */
    public void add(AbstractVar var, Object val) throws ResolutionException {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        if (null == val) {
            throw new BindingError("Attempting to bind " + var + " to null");
        }

        for (Iterator i = varToTerm.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry vt = (Map.Entry) i.next();
            Object obj = vt.getValue();
            if (obj instanceof WrappedVar) {
                obj = ((WrappedVar) obj).getVar();
            }
            if (var.equals(obj)) {
                vt.setValue(val);
            }
        }
        varToTerm.put(var, val);
    }

    
    /**
     *  Retrieves the binding for var if it exists, otherwise returns null.
     *  @param var  The variable to query.
     *  @return  The value of the variable in this unifier.
     */
    public Object lookup(AbstractVar var) {
        Object obj = varToTerm.get(var);
        if (obj instanceof DynamicObject && ((DynamicObject) obj).hasStaticInstance()) {
            obj = ((DynamicObject) obj).getStaticInstance();
            varToTerm.put(var, obj);
        }
        return obj;
    }


    private Object substitute(Object obj, Binding u) {
        Object val = obj;

        if (obj instanceof WrappedVar) {
            val = u.lookup(((WrappedVar) obj).getVar());
            if (null == val) {
                val = obj;
            }
        }
        return val;
    }

    /**
     *  Applies unifier u to all the terms in this unifier.
     *
     *  Precondition:
     *      u.values().intersect(keySet()).isEmpty()
     */
    public void apply(Binding u) {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        if (null == u) {
            return;      // application of the empty binding does nothing
        }
        
        for (Iterator i = varToTerm.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry vt = (Map.Entry) i.next();
            Object o = substitute(vt.getValue(), u);
            vt.setValue(o);
        }
    }


    /**
     *  Combines this unifier with u with the result being the composition
     *  of the two unifiers, with this unifier being on the right.  This has
     *  the effect of applying u to all the terms in this, followed by the 
     *  addition (putting) of u's bindings into this unifier.
     *
     *    composeRight(u)      this <- u o this
     *    where             
     *        (u o this)(V)  ==  u(this(V))
     *
     *  Precondition:  keySet().intersect(u.keySet()).isEmpty()
     *
     *  @param u  The unifier to compose with.
     */
    public void composeRight(Binding u) {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        if (null == u) {
            return;      // composition of the empty binding does nothing
        }
        apply(u);
        varToTerm.putAll(u.varToTerm);
    }


    /**
     * Combines this unifier with u with the result being the composition
     * of the two unifiers, with this unifier being on the left.  This has
     * the effect of applying this unifier's bindings to all the terms in u,
     * followed by the addition (putting) of u's bindings into this unifier.
     *
     *    composeLeft(u)      this <- this o u
     *    where             
     *        (this o u)(V)  ==  this(u(V))
     *
     * Precondition:  keySet().intersect(u.keySet()).isEmpty()
     * 
     * We have weakened the precondition to allow vars to be self-bound or
     * to be bound to the same thing multiple times (see resolvePatternUse)
     * 
     * Note, we also account for DynamicObject/static instance equivalence
     *
     * @param u  The unifier to compose with.
     */
    public void composeLeft(Binding u) {
        if (frozen) {
            throw new BindingError("Cannot modify a frozen Binding");
        }
        if (null == u) {
            return;      // composition of the empty binding does nothing
        }
        Iterator i = u.varToTerm.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry vt = (Map.Entry) i.next();
            AbstractVar uKey = (AbstractVar) vt.getKey();
            Object uVal = vt.getValue();
            // check precondition
            if (true &&
                    keys().contains(uKey) &&
                    // uKey != uVal &&   // Vars can be bound to themselves
                    !uVal.equals(lookup(uKey)) // Can be bound to the same thing
                    && !(uVal instanceof WrappedVar)
            ) {
//                System.err.println(this);
//                System.err.println(u);
                if (uVal instanceof DynamicObject) {
                    DynamicObject dyn = (DynamicObject) uVal;
                    if (dyn.getStaticInstance().equals(varToTerm.get(uKey))) {
                        break;
                    }
                }
                throw new BindingError("INTERNAL ERROR: Binding precondition violated for " + uKey + " =\n\t" + uVal + "\n\t" + varToTerm.get(uKey));
            }
            Object o = substitute(uVal, this);
            varToTerm.put(vt.getKey(), o);
        }
    }


    /**
     *  @return An iterator that iterates through the variables in this unifier.
     */
    public Collection keys() {
        return varToTerm.keySet();
    }
    
    public Set entrySet() {
        return varToTerm.entrySet();
    }

    /**
     *  unifier ::= "{" (binding (", " binding)* "}"
     *  binding ::= <var> " -> " <term> 
     *  @return A string representation of the unifier.
     */
    public String toString() {
        String s = null;

        for (Iterator i = varToTerm.keySet().iterator(); i.hasNext(); ) {
            AbstractVar var = (AbstractVar) i.next();
            Object subs = lookup(var);
            
            if (null == subs) {
                throw new BindingError("NULL binding for " + var);
            } else if (subs.getClass().equals(EObjectImpl.class)) {
                subs = subs.hashCode() + ":" + ((EObject) subs).eClass().getName();
            } else if (subs instanceof WrappedVar) {
                subs = "W(" + subs + ")";
            }

            s = (s == null ? "" : s + ", ")
                + formatVar(var)
                + " -> " + subs;
        }
        return "{" + (s == null ? "" : s) + "}";
    }
    
    private String formatVar(AbstractVar var) {
        return var.getScope().getName() + "::"
            + (null == var.getName() ? "_V" + var.hashCode() : var.getName());
    }

    public boolean equals(Object obj) {
        if (obj instanceof Binding) {
            Binding b = (Binding) obj;
            return entrySet().equals(b.entrySet());
        }
        return false;
    }
    
    public int hashCode() {
        return entrySet().hashCode() + 42;
    }
    
    public static class BindingError extends Error {

        private static final long serialVersionUID = 6575697896092447618L;

        public BindingError(String message) {
            super(message);
        }
    }

    /**
     * You can not update the binding after calling freeze
     */
    public void freeze() {
        frozen = true;
    }
    

    public static Binding createBinding(Object val1, Object val2) throws ResolutionException {
        Binding unifier = null;
        if (val1 instanceof BindingPair) {
            unifier = new Binding();
            unifier.composeLeft((BindingPair) val1);
            val1 = ((BindingPair) val1).getValue();
        }
        if (val2 instanceof BindingPair) {
            if (null == unifier) {
                unifier = new Binding();
            }
            unifier.composeLeft((BindingPair) val2);
            val2 = ((BindingPair) val2).getValue();
        }
    
        if (val1 instanceof WrappedVar) {
            unifier = bindWrappedVar(unifier, (WrappedVar) val1, val2);
        } else if (val2 instanceof WrappedVar) {
            unifier = bindWrappedVar(unifier, (WrappedVar) val2, val1);
        } else if (val1 instanceof Number && val2 instanceof Number) {
            if (val1 instanceof Float || val1 instanceof Double ||
                val2 instanceof Float || val2 instanceof Double) {
                double v1 = ((Number) val1).doubleValue();
                double v2 = ((Number) val2).doubleValue();
                if (v1 == v2) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                } else {
                    unifier = null;
                }
            } else {
                long l1 = ((Number) val1).longValue();
                long l2 = ((Number) val2).longValue();
                if (l1 == l2) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                } else {
                    unifier = null;
                }
            }
        } else {
            // bloody EMF - a "generated" EEnumLiteral is not "equal" to a
            // dynamic EEnumLiteral or something like that
            // - you need to get the EEnumerator for comparisons...
            if (val1 instanceof EEnumLiteral) {
                val1 = ((EEnumLiteral) val1).getInstance();
            }
            if (val2 instanceof EEnumLiteral) {
                val2 = ((EEnumLiteral) val2).getInstance();
            }
            if (val1.equals(val2)) {
                if (null == unifier) {
                    unifier = new Binding();
                }
            } else {
                unifier = null;
            }
        }
        return unifier;
    }

    static private Binding bindWrappedVar(Binding unifier, WrappedVar wVar, Object val) throws ResolutionException {
        AbstractVar var = wVar.getVar();
        EClass eClass = wVar.getType();
        if (null != eClass) {
            if (val instanceof WrappedVar) {
                WrappedVar wVar2 = (WrappedVar) val;
                AbstractVar var2 = wVar2.getVar();
                EClass eClass2 = wVar2.getType();
                boolean isExact2 = wVar2.isExact();
                
                if (null != eClass2) {
                    if (wVar.setType(eClass2, isExact2)) {
                        if (null == unifier) {
                            unifier = new Binding();
                        }
                        unifier.add(var, wVar);
                        unifier.add(var2, wVar);
                    } else {    // type mismatch
                        unifier = null;
                    }
                } else {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(var, wVar);
                    unifier.add(var2, wVar);
                }
            } else if (val instanceof EObject) {
                if (eClass.isSuperTypeOf(((EObject) val).eClass())) {
                    if (null == unifier) {
                        unifier = new Binding();
                    }
                    unifier.add(var, val);
                } else {
                    unifier = null;
                }
            } else {
                Class cls = eClass.getInstanceClass();
                if (null != cls) {
                    if (cls.isAssignableFrom(val.getClass())) {
                        if (null == unifier) {
                            unifier = new Binding();
                        }
                        unifier.add(var, val);
                    } else {
                        unifier = null;
                    }
                } else {
                    unifier = null;
                }
            }
        } else {
            if (null == unifier) {
                unifier = new Binding();
            }
            unifier.add(var, val);
        }
        
        return unifier;
    }

}
