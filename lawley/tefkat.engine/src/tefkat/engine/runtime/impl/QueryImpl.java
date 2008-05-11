/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import tefkat.engine.runtime.Query;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Var;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.QueryImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.QueryImpl#getParameterVar <em>Parameter Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryImpl extends PatternScopeImpl implements Query {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getTerm() <em>Term</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTerm()
     * @generated
     * @ordered
     */
    protected Term term = null;

    /**
     * The cached value of the '{@link #getParameterVar() <em>Parameter Var</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterVar()
     * @generated
     * @ordered
     */
    protected EList parameterVar = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.QUERY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Term getTerm() {
        return term;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTerm(Term newTerm, NotificationChain msgs) {
        Term oldTerm = term;
        term = newTerm;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.QUERY__TERM, oldTerm, newTerm);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTerm(Term newTerm) {
        if (newTerm != term) {
            NotificationChain msgs = null;
            if (term != null)
                msgs = ((InternalEObject)term).eInverseRemove(this, RuntimePackage.TERM__QUERY, Term.class, msgs);
            if (newTerm != null)
                msgs = ((InternalEObject)newTerm).eInverseAdd(this, RuntimePackage.TERM__QUERY, Term.class, msgs);
            msgs = basicSetTerm(newTerm, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.QUERY__TERM, newTerm, newTerm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParameterVar() {
        if (parameterVar == null) {
            parameterVar = new EObjectResolvingEList(Var.class, this, RuntimePackage.QUERY__PARAMETER_VAR);
        }
        return parameterVar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.QUERY__TERM:
                if (term != null)
                    msgs = ((InternalEObject)term).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.QUERY__TERM, null, msgs);
                return basicSetTerm((Term)otherEnd, msgs);
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
            case RuntimePackage.QUERY__TERM:
                return basicSetTerm(null, msgs);
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
            case RuntimePackage.QUERY__TERM:
                return getTerm();
            case RuntimePackage.QUERY__PARAMETER_VAR:
                return getParameterVar();
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
            case RuntimePackage.QUERY__TERM:
                setTerm((Term)newValue);
                return;
            case RuntimePackage.QUERY__PARAMETER_VAR:
                getParameterVar().clear();
                getParameterVar().addAll((Collection)newValue);
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
            case RuntimePackage.QUERY__TERM:
                setTerm((Term)null);
                return;
            case RuntimePackage.QUERY__PARAMETER_VAR:
                getParameterVar().clear();
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
            case RuntimePackage.QUERY__TERM:
                return term != null;
            case RuntimePackage.QUERY__PARAMETER_VAR:
                return parameterVar != null && !parameterVar.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //QueryImpl