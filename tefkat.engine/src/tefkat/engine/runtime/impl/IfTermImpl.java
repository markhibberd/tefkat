/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.Tree;
import tefkat.engine.runtime.TreeListener;

import tefkat.engine.runtime.IfTerm;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.TRule;
import tefkat.engine.runtime.TargetTerm;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.IfTermImpl#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfTermImpl extends CompoundTermImpl implements IfTerm {
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
    protected IfTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.IF_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule getTRuleTgt() {
        if (eContainerFeatureID != RuntimePackage.IF_TERM__TRULE_TGT) return null;
        return (TRule)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTRuleTgt(TRule newTRuleTgt, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTRuleTgt, RuntimePackage.IF_TERM__TRULE_TGT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTRuleTgt(TRule newTRuleTgt) {
        if (newTRuleTgt != eInternalContainer() || (eContainerFeatureID != RuntimePackage.IF_TERM__TRULE_TGT && newTRuleTgt != null)) {
            if (EcoreUtil.isAncestor(this, newTRuleTgt))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTRuleTgt != null)
                msgs = ((InternalEObject)newTRuleTgt).eInverseAdd(this, RuntimePackage.TRULE__TGT, TRule.class, msgs);
            msgs = basicSetTRuleTgt(newTRuleTgt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.IF_TERM__TRULE_TGT, newTRuleTgt, newTRuleTgt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getNonLocalVars() {
        if (null == nonLocalVars) {
            EList nonLocalVars = new BasicEList();
            Set conditionVars = new HashSet();
            Set usages = new HashSet();

            // Find the Vars that occur in the condition of the IF
            for (Iterator itr = ((Term) getTerm().get(0)).eAllContents(); itr.hasNext(); ) {
                Object obj = itr.next();
                
                if (obj instanceof VarUse) {
                    conditionVars.add(((VarUse) obj).getVar());
                }
            }

            // Find all VarUses that occur in the whole IF-THEN-ELSE
            for (Iterator itr = eAllContents(); itr.hasNext(); ) {
                Object obj = itr.next();
                
                if (obj instanceof VarUse) {
                    usages.add(obj);
                }
            }
            
            // Find just those condition Vars that are NOT only referenced in the IF-THEN-ELSE
            for (Iterator varItr = conditionVars.iterator(); varItr.hasNext(); ) {
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

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void match(Context context) throws ResolutionException, NotGroundException {
        evaluate(context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void ensure(Context context) throws ResolutionException, NotGroundException {
        evaluate(context);
    }
    
    private void evaluate(final Context context) throws NotGroundException, ResolutionException {
        
        // Ensure that all non-local variables are already ground
        // (WrappedVars are handled by the Expander)
        for (Iterator itr = getNonLocalVars().iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
            Object value = context.lookup(var);
            if (null == value) {
                context.delay("Non-local variable " + var + " is not bound.");
            }
        }
        
        final VarExpander.Function f = new VarExpander.Function() {
            public void call(Binding unifier) throws ResolutionException {
                evalIfGoal(context, unifier , getTerm());
            }
            
        };
        
        new VarExpander(context, getNonLocalVars(), f, context.getBindings());
    }
    
    private void evalIfGoal(final Context context, final Binding unifier, final List terms)
    throws ResolutionException {

        List condGoal = new ArrayList();
        condGoal.add(terms.get(0));
        // cannot pass node as context here or delayed terms will get pushed into the "NOT"
        // leading to possible spurious flounderings -- see also evalNegatedGoal 
        Tree newTree = context.createTree(condGoal, unifier, false, true);

        newTree.addTreeListener(new TreeListener() {

            public void solution(Binding answer) {
                // THEN
                Binding sContext = new Binding(answer);
                context.createBranch((Term) terms.get(1), sContext);
            }

            public void completed(Tree theTree) {
                if (!theTree.isSuccess()) {
                    // ELSE
                    context.createBranch((Term) terms.get(2), new Binding(unifier));
                }
            }

            public void floundered(Tree theTree) {
                // nothing to do in this case
            }

        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.IF_TERM__TRULE_TGT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTRuleTgt((TRule)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.IF_TERM__TRULE_TGT:
                return basicSetTRuleTgt(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case RuntimePackage.IF_TERM__TRULE_TGT:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.TRULE__TGT, TRule.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.IF_TERM__TRULE_TGT:
                return getTRuleTgt();
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
            case RuntimePackage.IF_TERM__TRULE_TGT:
                setTRuleTgt((TRule)newValue);
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
            case RuntimePackage.IF_TERM__TRULE_TGT:
                setTRuleTgt((TRule)null);
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
            case RuntimePackage.IF_TERM__TRULE_TGT:
                return getTRuleTgt() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == TargetTerm.class) {
            switch (derivedFeatureID) {
                case RuntimePackage.IF_TERM__TRULE_TGT: return RuntimePackage.TARGET_TERM__TRULE_TGT;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == TargetTerm.class) {
            switch (baseFeatureID) {
                case RuntimePackage.TARGET_TERM__TRULE_TGT: return RuntimePackage.IF_TERM__TRULE_TGT;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //IfTermImpl