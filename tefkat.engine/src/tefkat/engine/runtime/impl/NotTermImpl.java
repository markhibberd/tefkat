/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.Tree;
import tefkat.engine.runtime.TreeListener;
import tefkat.engine.runtime.NotTerm;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NotTermImpl extends CompoundTermImpl implements NotTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    private EList nonLocalVars = null;
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NotTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.NOT_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getNonLocalVars() {
        if (null == nonLocalVars) {
            nonLocalVars = new BasicEList();

            Set notVars = new HashSet();
            Set usages = new HashSet();

            // Find the Vars and VarUses that occur in the NOT
            for (Iterator itr = eAllContents(); itr.hasNext(); ) {
                Object obj = itr.next();

                if (obj instanceof VarUse) {
                    notVars.add(((VarUse) obj).getVar());
                    usages.add(obj);
                }
            }

            // Find just those Vars that are NOT only referenced in the NOT
            for (Iterator varItr = notVars.iterator(); varItr.hasNext(); ) {
                Var var = (Var) varItr.next();

                if (var.getScope() instanceof PatternDefn && ((PatternDefn) var.getScope()).getParameterVar().contains(var)) {
                    // Pattern parameters are non-local vars
                    nonLocalVars.add(var);
                } else if (!usages.containsAll(var.getUsages())) {
                    nonLocalVars.add(var);
                }
            }
        }
        
        return nonLocalVars;
    }

    public void match(final Context context) throws ResolutionException, NotGroundException {
        // Ensure that all non-local variables are already ground
        // (WrappedVars are handled by the Expander)
        for (final Iterator itr = getNonLocalVars().iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
            Object value = context.lookup(var);
            if (null == value) {
                context.delay("Non-local variable " + var + " is not bound.");
            }
        }
        
        final VarExpander.Function f = new VarExpander.Function() {
            public void call(Binding unifier) throws ResolutionException {
                evalNegatedGoal(context, unifier, new ArrayList(getTerm()));
            }
            
        };
        
        new VarExpander(context, getNonLocalVars(), f, context.getBindings());

    }

    /**
     * Coupling to RuleEvakluator needs to be broken.
     * Probably the  best approach is to make Tree "active" - evaluation should be a
     * behaviour of Tree rather than just using Tree as a passive data structure.
     * 
     * @param context
     * @param unifier
     * @param negGoal
     * @return
     * @throws ResolutionException
     */
    private void evalNegatedGoal(final Context context, final Binding unifier, final Collection negGoal)
    throws ResolutionException {
        // cannot pass node as context here or delayed terms will get pushed into the "NOT"
        // leading to possible spurious flounderings -- see also resolveIfTerm 
        final Tree newTree = context.createTree(negGoal, unifier, true, true);

        newTree.addTreeListener(new TreeListener() {

            public void solution(Binding answer) {
            }

            public void completed(Tree theTree) {
                if (theTree.isSuccess()) {
                    newTree.removeTreeListener(this);
                    context.fail();
                } else {
                    // Negation tree finitely failed, regard as true.
                    //
                    context.createBranch(new Binding(unifier));
                }
            }

            public void floundered(Tree theTree) {
            }

        });
        
    }

} //NotTermImpl