/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.WrappedVar;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.Injection;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Injection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.InjectionImpl#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.InjectionImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.InjectionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InjectionImpl extends TargetTermImpl implements Injection {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSources() <em>Sources</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSources()
     * @generated
     * @ordered
     */
    protected EList sources = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected VarUse target = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InjectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.INJECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.INJECTION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSources() {
        if (sources == null) {
            sources = new EObjectContainmentEList(Expression.class, this, RuntimePackage.INJECTION__SOURCES);
        }
        return sources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarUse getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(VarUse newTarget, NotificationChain msgs) {
        VarUse oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.INJECTION__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(VarUse newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.INJECTION__TARGET, null, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.INJECTION__TARGET, null, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.INJECTION__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.INJECTION__SOURCES:
                return ((InternalEList)getSources()).basicRemove(otherEnd, msgs);
            case RuntimePackage.INJECTION__TARGET:
                return basicSetTarget(null, msgs);
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
            case RuntimePackage.INJECTION__NAME:
                return getName();
            case RuntimePackage.INJECTION__SOURCES:
                return getSources();
            case RuntimePackage.INJECTION__TARGET:
                return getTarget();
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
            case RuntimePackage.INJECTION__NAME:
                setName((String)newValue);
                return;
            case RuntimePackage.INJECTION__SOURCES:
                getSources().clear();
                getSources().addAll((Collection)newValue);
                return;
            case RuntimePackage.INJECTION__TARGET:
                setTarget((VarUse)newValue);
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
            case RuntimePackage.INJECTION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case RuntimePackage.INJECTION__SOURCES:
                getSources().clear();
                return;
            case RuntimePackage.INJECTION__TARGET:
                setTarget((VarUse)null);
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
            case RuntimePackage.INJECTION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case RuntimePackage.INJECTION__SOURCES:
                return sources != null && !sources.isEmpty();
            case RuntimePackage.INJECTION__TARGET:
                return target != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

    public void ensure(Context context) throws ResolutionException, NotGroundException {
        List keySet = new ArrayList();

        List sources = getSources();
        for (Iterator itr = sources.iterator(); itr.hasNext(); ) {
            Expression expr = (Expression) itr.next();
//          System.out.println("Eval: " + expr);
            List vals = expr.eval(context);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) vals.get(0);
                Collection instances = context.expand(wVar);
                List pairs = new ArrayList(instances.size());
                for (Iterator instItr = instances.iterator(); instItr.hasNext(); ) {
                    Object o = instItr.next();
                    BindingPair bp = new BindingPair(o);
                    bp.add(wVar.getVar(), o);
                    pairs.add(bp);
                }
                keySet.add(pairs);
//              ruleEval.fireInfo(expr + DELAYING_MESSAGE);
//              context.delay(expr + NOT_BOUND_MESSAGE);
            } else {
                keySet.add(vals);
            }
        }

        // do expr eval before we create things in case we need to delay
        Object targetVal = getTarget().eval(context).get(0);    // Can only be one thing

        keySet = buildCrossProduct(keySet);
        for (Iterator itr = keySet.iterator(); itr.hasNext(); ) {
            List keys = (List) itr.next();
            keys.add(0, getName());

            EObject targetObject = context.lookup(keys, getTRuleTgt());

            Binding unifier = new Binding();
            for (Iterator keyItr = keys.iterator(); keyItr.hasNext(); ) {
                Object k = keyItr.next();
                if (k instanceof BindingPair) {
                    unifier.composeRight((BindingPair) k);
                }
            }

            if (targetVal instanceof WrappedVar) {
                unifier.add(((WrappedVar) targetVal).getVar(), targetObject);
            } else if (!targetVal.equals(targetObject)) {
                context.error("Incompatible values for variable: " + getTarget().getVar());
            }

            context.createBranch(unifier);
        }

    }
    
    /**
     * 
     * @param values a List of Lists
     * @return
     */
    private static List buildCrossProduct(List values) {
        if (values.size() == 0) {
            List result = new ArrayList(1);
            result.add(new ArrayList());
            return result;
        }
        
        Collection vals = (Collection) values.remove(0);
        if (values.size() == 0) {
            List result = new ArrayList(vals.size());
            for (Iterator itr = vals.iterator(); itr.hasNext(); ) {
                List l = new ArrayList();
                l.add(itr.next());
                result.add(l);
            }
            return result;
        }
        
        List xp = buildCrossProduct(values);
        List result = new ArrayList();
        for (Iterator itr = xp.iterator(); itr.hasNext(); ) {
            Collection l = (Collection) itr.next();
            for (Iterator itr2 = vals.iterator(); itr2.hasNext(); ) {
                Object o2 = itr2.next();
                List l2 = new ArrayList(l);
                l2.add(0, o2);
                result.add(l2);
            }
        }
        return result;
    }

} //InjectionImpl