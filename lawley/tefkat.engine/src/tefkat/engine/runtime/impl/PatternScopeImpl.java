/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.PatternScope;
import tefkat.engine.runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.PatternScopeImpl#getPatternDefn <em>Pattern Defn</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PatternScopeImpl extends VarScopeImpl implements PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getPatternDefn() <em>Pattern Defn</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternDefn()
     * @generated
     * @ordered
     */
    protected EList patternDefn = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PatternScopeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.PATTERN_SCOPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPatternDefn() {
        if (patternDefn == null) {
            patternDefn = new EObjectContainmentWithInverseEList(PatternDefn.class, this, RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN, RuntimePackage.PATTERN_DEFN__PATTERN_SCOPE);
        }
        return patternDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                return ((InternalEList)getPatternDefn()).basicAdd(otherEnd, msgs);
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
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                return ((InternalEList)getPatternDefn()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                return getPatternDefn();
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
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
                getPatternDefn().addAll((Collection)newValue);
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
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
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
            case RuntimePackage.PATTERN_SCOPE__PATTERN_DEFN:
                return patternDefn != null && !patternDefn.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //PatternScopeImpl