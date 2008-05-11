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
import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.PatternScope;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Var;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.PatternDefnImpl#getPatternScope <em>Pattern Scope</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.PatternDefnImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.PatternDefnImpl#getParameterVar <em>Parameter Var</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.PatternDefnImpl#isSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PatternDefnImpl extends VarScopeImpl implements PatternDefn {
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
     * The default value of the '{@link #isSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSource()
     * @generated
     * @ordered
     */
    protected static final boolean SOURCE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSource()
     * @generated
     * @ordered
     */
    protected boolean source = SOURCE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PatternDefnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.PATTERN_DEFN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternScope getPatternScope() {
        if (eContainerFeatureID != RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE) return null;
        return (PatternScope)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPatternScope(PatternScope newPatternScope, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPatternScope, RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPatternScope(PatternScope newPatternScope) {
        if (newPatternScope != eInternalContainer() || (eContainerFeatureID != RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE && newPatternScope != null)) {
            if (EcoreUtil.isAncestor(this, newPatternScope))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPatternScope != null)
                msgs = ((InternalEObject)newPatternScope).eInverseAdd(this, RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN, PatternScope.class, msgs);
            msgs = basicSetPatternScope(newPatternScope, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE, newPatternScope, newPatternScope));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.PATTERN_DEFN__TERM, oldTerm, newTerm);
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
                msgs = ((InternalEObject)term).eInverseRemove(this, RuntimePackage.TERM__PATTERN_DEFN, Term.class, msgs);
            if (newTerm != null)
                msgs = ((InternalEObject)newTerm).eInverseAdd(this, RuntimePackage.TERM__PATTERN_DEFN, Term.class, msgs);
            msgs = basicSetTerm(newTerm, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PATTERN_DEFN__TERM, newTerm, newTerm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParameterVar() {
        if (parameterVar == null) {
            parameterVar = new EObjectResolvingEList(Var.class, this, RuntimePackage.PATTERN_DEFN__PARAMETER_VAR);
        }
        return parameterVar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(boolean newSource) {
        boolean oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PATTERN_DEFN__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPatternScope((PatternScope)otherEnd, msgs);
            case RuntimePackage.PATTERN_DEFN__TERM:
                if (term != null)
                    msgs = ((InternalEObject)term).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.PATTERN_DEFN__TERM, null, msgs);
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                return basicSetPatternScope(null, msgs);
            case RuntimePackage.PATTERN_DEFN__TERM:
                return basicSetTerm(null, msgs);
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN, PatternScope.class, msgs);
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                return getPatternScope();
            case RuntimePackage.PATTERN_DEFN__TERM:
                return getTerm();
            case RuntimePackage.PATTERN_DEFN__PARAMETER_VAR:
                return getParameterVar();
            case RuntimePackage.PATTERN_DEFN__SOURCE:
                return isSource() ? Boolean.TRUE : Boolean.FALSE;
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                setPatternScope((PatternScope)newValue);
                return;
            case RuntimePackage.PATTERN_DEFN__TERM:
                setTerm((Term)newValue);
                return;
            case RuntimePackage.PATTERN_DEFN__PARAMETER_VAR:
                getParameterVar().clear();
                getParameterVar().addAll((Collection)newValue);
                return;
            case RuntimePackage.PATTERN_DEFN__SOURCE:
                setSource(((Boolean)newValue).booleanValue());
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                setPatternScope((PatternScope)null);
                return;
            case RuntimePackage.PATTERN_DEFN__TERM:
                setTerm((Term)null);
                return;
            case RuntimePackage.PATTERN_DEFN__PARAMETER_VAR:
                getParameterVar().clear();
                return;
            case RuntimePackage.PATTERN_DEFN__SOURCE:
                setSource(SOURCE_EDEFAULT);
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
            case RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE:
                return getPatternScope() != null;
            case RuntimePackage.PATTERN_DEFN__TERM:
                return term != null;
            case RuntimePackage.PATTERN_DEFN__PARAMETER_VAR:
                return parameterVar != null && !parameterVar.isEmpty();
            case RuntimePackage.PATTERN_DEFN__SOURCE:
                return source != SOURCE_EDEFAULT;
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
        result.append(" (source: ");
        result.append(source);
        result.append(')');
        return result.toString();
    }

} //PatternDefnImpl