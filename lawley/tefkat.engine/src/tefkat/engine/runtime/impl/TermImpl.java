/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.engine.runtime.CompoundTerm;
import tefkat.engine.runtime.IfTerm;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.Query;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.TargetTerm;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Var;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.TermImpl#getPatternDefn <em>Pattern Defn</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TermImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TermImpl#getCompoundTerm <em>Compound Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TermImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TermImpl extends EObjectImpl implements Term {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContext()
     * @generated
     * @ordered
     */
    protected Var context = null;

    int isTarget;
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternDefn getPatternDefn() {
        if (eContainerFeatureID != RuntimePackage.TERM__PATTERN_DEFN) return null;
        return (PatternDefn)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPatternDefn(PatternDefn newPatternDefn, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPatternDefn, RuntimePackage.TERM__PATTERN_DEFN, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPatternDefn(PatternDefn newPatternDefn) {
        if (newPatternDefn != eInternalContainer() || (eContainerFeatureID != RuntimePackage.TERM__PATTERN_DEFN && newPatternDefn != null)) {
            if (EcoreUtil.isAncestor(this, newPatternDefn))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPatternDefn != null)
                msgs = ((InternalEObject)newPatternDefn).eInverseAdd(this, RuntimePackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
            msgs = basicSetPatternDefn(newPatternDefn, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TERM__PATTERN_DEFN, newPatternDefn, newPatternDefn));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Query getQuery() {
        if (eContainerFeatureID != RuntimePackage.TERM__QUERY) return null;
        return (Query)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQuery(Query newQuery, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuery, RuntimePackage.TERM__QUERY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuery(Query newQuery) {
        if (newQuery != eInternalContainer() || (eContainerFeatureID != RuntimePackage.TERM__QUERY && newQuery != null)) {
            if (EcoreUtil.isAncestor(this, newQuery))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuery != null)
                msgs = ((InternalEObject)newQuery).eInverseAdd(this, RuntimePackage.QUERY__TERM, Query.class, msgs);
            msgs = basicSetQuery(newQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TERM__QUERY, newQuery, newQuery));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CompoundTerm getCompoundTerm() {
        if (eContainerFeatureID != RuntimePackage.TERM__COMPOUND_TERM) return null;
        return (CompoundTerm)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompoundTerm(CompoundTerm newCompoundTerm, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCompoundTerm, RuntimePackage.TERM__COMPOUND_TERM, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompoundTerm(CompoundTerm newCompoundTerm) {
        if (newCompoundTerm != eInternalContainer() || (eContainerFeatureID != RuntimePackage.TERM__COMPOUND_TERM && newCompoundTerm != null)) {
            if (EcoreUtil.isAncestor(this, newCompoundTerm))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCompoundTerm != null)
                msgs = ((InternalEObject)newCompoundTerm).eInverseAdd(this, RuntimePackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
            msgs = basicSetCompoundTerm(newCompoundTerm, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TERM__COMPOUND_TERM, newCompoundTerm, newCompoundTerm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Var getContext() {
        if (context != null && context.eIsProxy()) {
            InternalEObject oldContext = (InternalEObject)context;
            context = (Var)eResolveProxy(oldContext);
            if (context != oldContext) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.TERM__CONTEXT, oldContext, context));
            }
        }
        return context;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Var basicGetContext() {
        return context;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContext(Var newContext) {
        Var oldContext = context;
        context = newContext;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TERM__CONTEXT, oldContext, context));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Var getExtent() {
        Var extent = getContext();
        if (null == extent && getCompoundTerm() != null) {
            extent = getCompoundTerm().getExtent();
        }
        return extent;
    }

    /**
     * <!-- begin-user-doc -->
     * A term is in a "target" context if (1) it's a subsclass of TargetTerm and:
     * <ul>
     * <li> (2) it's owned by a TRule via its "tgt" reference, or </li>
     * <li> (3) it's owned by a PatternDefn for which "isSource" is false, or </li>
     * <li> (4) it's not the condition of an IfTerm, and
     *      (5) it's owned by a CompoundTerm that is a "target" term.</li>
     * </ul>
     * 
     * Otherwise its either owned by a TRule via its "src" reference or by a
     * PatternDefn for which "isSource" is true or by a Query so it must be a
     * "source" term.
     * 
     * Note that the implementation is here, rather than in TargetTermImpl due
     * to the wonderful nature of multiple inheritance -- most of the relevant
     * ...TermImpls actually extend SourceTermImpl, and not TargetTermImpl.
     * 
     * Beware, the result of calling isTarget is cached - if you change a Term's
     * containment the result is <b>NOT</b> recomputed.
     * 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isTarget() {
        if (0 != isTarget) {
            return isTarget > 0;
        }
        // (1)
        if (this instanceof TargetTerm) {
            // (2)
            if (((TargetTerm) this).getTRuleTgt() != null ||
                    // (3)
                    (this.getPatternDefn() != null && !this.getPatternDefn().isSource())) {
                isTarget = 1;
                return true;
            }
            CompoundTerm parent = getCompoundTerm();
            if (parent != null &&
                    // (4)
                    !(parent instanceof IfTerm &&
                            ((IfTerm) parent).getTerm().get(0).equals(this)) &&
                  // (5)
                  parent.isTarget()) {
                isTarget = 1;
                return true;
            }
        }
        isTarget = -1;
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.TERM__PATTERN_DEFN:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPatternDefn((PatternDefn)otherEnd, msgs);
            case RuntimePackage.TERM__QUERY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuery((Query)otherEnd, msgs);
            case RuntimePackage.TERM__COMPOUND_TERM:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCompoundTerm((CompoundTerm)otherEnd, msgs);
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                return basicSetPatternDefn(null, msgs);
            case RuntimePackage.TERM__QUERY:
                return basicSetQuery(null, msgs);
            case RuntimePackage.TERM__COMPOUND_TERM:
                return basicSetCompoundTerm(null, msgs);
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.PATTERN_DEFN__TERM, PatternDefn.class, msgs);
            case RuntimePackage.TERM__QUERY:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.QUERY__TERM, Query.class, msgs);
            case RuntimePackage.TERM__COMPOUND_TERM:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.COMPOUND_TERM__TERM, CompoundTerm.class, msgs);
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                return getPatternDefn();
            case RuntimePackage.TERM__QUERY:
                return getQuery();
            case RuntimePackage.TERM__COMPOUND_TERM:
                return getCompoundTerm();
            case RuntimePackage.TERM__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)newValue);
                return;
            case RuntimePackage.TERM__QUERY:
                setQuery((Query)newValue);
                return;
            case RuntimePackage.TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)newValue);
                return;
            case RuntimePackage.TERM__CONTEXT:
                setContext((Var)newValue);
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                setPatternDefn((PatternDefn)null);
                return;
            case RuntimePackage.TERM__QUERY:
                setQuery((Query)null);
                return;
            case RuntimePackage.TERM__COMPOUND_TERM:
                setCompoundTerm((CompoundTerm)null);
                return;
            case RuntimePackage.TERM__CONTEXT:
                setContext((Var)null);
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
            case RuntimePackage.TERM__PATTERN_DEFN:
                return getPatternDefn() != null;
            case RuntimePackage.TERM__QUERY:
                return getQuery() != null;
            case RuntimePackage.TERM__COMPOUND_TERM:
                return getCompoundTerm() != null;
            case RuntimePackage.TERM__CONTEXT:
                return context != null;
        }
        return super.eIsSet(featureID);
    }

} //TermImpl