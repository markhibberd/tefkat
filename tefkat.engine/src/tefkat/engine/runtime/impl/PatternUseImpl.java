/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Tree;
import tefkat.engine.runtime.TreeListener;
import tefkat.engine.runtime.WrappedVar;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.PatternUse;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.PatternUseImpl#getDefn <em>Defn</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.PatternUseImpl#getArg <em>Arg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PatternUseImpl extends SimpleTermImpl implements PatternUse {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getDefn() <em>Defn</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefn()
     * @generated
     * @ordered
     */
    protected PatternDefn defn = null;

    /**
     * The cached value of the '{@link #getArg() <em>Arg</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArg()
     * @generated
     * @ordered
     */
    protected EList arg = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PatternUseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.PATTERN_USE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternDefn getDefn() {
        if (defn != null && defn.eIsProxy()) {
            InternalEObject oldDefn = (InternalEObject)defn;
            defn = (PatternDefn)eResolveProxy(oldDefn);
            if (defn != oldDefn) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.PATTERN_USE__DEFN, oldDefn, defn));
            }
        }
        return defn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternDefn basicGetDefn() {
        return defn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefn(PatternDefn newDefn) {
        PatternDefn oldDefn = defn;
        defn = newDefn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PATTERN_USE__DEFN, oldDefn, defn));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getArg() {
        if (arg == null) {
            arg = new EObjectContainmentEList(Expression.class, this, RuntimePackage.PATTERN_USE__ARG);
        }
        return arg;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.PATTERN_USE__ARG:
                return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.PATTERN_USE__DEFN:
                if (resolve) return getDefn();
                return basicGetDefn();
            case RuntimePackage.PATTERN_USE__ARG:
                return getArg();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RuntimePackage.PATTERN_USE__DEFN:
                setDefn((PatternDefn)newValue);
                return;
            case RuntimePackage.PATTERN_USE__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case RuntimePackage.PATTERN_USE__DEFN:
                setDefn((PatternDefn)null);
                return;
            case RuntimePackage.PATTERN_USE__ARG:
                getArg().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RuntimePackage.PATTERN_USE__DEFN:
                return defn != null;
            case RuntimePackage.PATTERN_USE__ARG:
                return arg != null && !arg.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    public void match(final Context context) throws ResolutionException, NotGroundException {

        final PatternDefn pDefn = getDefn();
        final List args = getArg();

        if (null == pDefn) { // TODO fix this println hack
            String mesg = "";
            for (int i = 0; i < args.size(); i++) {
                String str;
                try {
                    List values = ((Expression) args.get(i)).eval(context);
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
            context.info(mesg);

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
            incrementalResolvePatternDefn(context,
                    pDefVars, args, null, null);
        } else {
//            final Binding context = tree.getContext();
            Function resolver;
            resolver = new PatternCall(pDefVars) {
                void invoke(Binding callContext, Binding parameterContext) throws ResolutionException {
                    incrementalResolvePatternDefn(context,
                            pDefVars, args,
                            callContext, parameterContext);
                }
            };
            
            new ExprExpander(context, resolver, context.getBindings(), args, false);

        }

    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {
    }

    private void incrementalResolvePatternDefn(final Context context,
            final List pDefVars, final List args,
            final Binding callContext, final Binding parameterContext)
    throws ResolutionException {

        Tree resultTree = context.getResultTree(getDefn().getTerm(), parameterContext);

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
        
        //System.err.println("\t" + resultTree.isSuccess());      // TODO delete
        if (resultTree.isSuccess()) {
            // Process any existing solutions
            
            for (Iterator itr = resultTree.getAnswers().iterator(); itr.hasNext();) {
                Binding answer = (Binding) itr.next();
                Binding unifier = createOutputBinding(context, callContext, pDefVars, args, answer);

                context.createBranch(unifier);
            }
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
    
    public String toString() {
        final String name = null == getDefn() ? "println" : getDefn().getName();
        StringBuffer result = new StringBuffer(name);
        result.append('(');
        for (Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            Expression expr = (Expression) itr.next();
            result.append(expr);
            if (itr.hasNext()) {
                result.append(", ");
            }
        }
        result.append(')');
        return result.toString();
    }

} //PatternUseImpl

abstract class PatternCall
implements Function {
    private final List vars;

    PatternCall(List vars) {
        this.vars = vars;
    }

    /**
     * @param unifier a set of variable bindings that needs to be propagated to the answer Binding
     * @param actuals actual values or WrappedVaes for the pDefVars for a call
     */
    public Object call(Binding unifier, Object[] actuals) throws ResolutionException {
        Binding parameterUnifier = new Binding();
        Binding callUnifier = new Binding(unifier);

//      System.err.println(pDefn.getName());
//      System.err.println("    C: " + context);
//      System.err.println("    P: " + Arrays.asList(actuals));

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
                varValue = callUnifier.lookup(var);
                if (null == varValue) {
                    WrappedVar wvar = new WrappedVar(pVar);
                    wvar.setExtent(wrappedActual.getExtent());
                    wvar.setType(wrappedActual.getType(), wrappedActual.isExact());

                    callUnifier.add(var, wvar);
                    varValue = wvar;
                }
            } else {
                varValue = actuals[i];
            }
            if (null != varValue) {
                parameterUnifier.add(pVar, varValue);
            }
//          System.err.println("    " + i + " " + pVar + " = " + varValue);
        }
//      System.err.println("    V: " + varContext);

        invoke(callUnifier, parameterUnifier);

        return Collections.EMPTY_LIST;
    }

    abstract void invoke(Binding callUnifier, Binding parameterUnifier) throws ResolutionException;
    
}
