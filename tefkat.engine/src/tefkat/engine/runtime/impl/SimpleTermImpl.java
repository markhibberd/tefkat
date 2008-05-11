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

import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.SimpleTerm;
import tefkat.engine.runtime.TRule;
import tefkat.engine.runtime.TargetTerm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.SimpleTermImpl#getTRuleTgt <em>TRule Tgt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SimpleTermImpl extends SourceTermImpl implements SimpleTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimpleTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.SIMPLE_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule getTRuleTgt() {
        if (eContainerFeatureID != RuntimePackage.SIMPLE_TERM__TRULE_TGT) return null;
        return (TRule)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTRuleTgt(TRule newTRuleTgt, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTRuleTgt, RuntimePackage.SIMPLE_TERM__TRULE_TGT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTRuleTgt(TRule newTRuleTgt) {
        if (newTRuleTgt != eInternalContainer() || (eContainerFeatureID != RuntimePackage.SIMPLE_TERM__TRULE_TGT && newTRuleTgt != null)) {
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
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.SIMPLE_TERM__TRULE_TGT, newTRuleTgt, newTRuleTgt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract void ensure(Context context) throws ResolutionException, NotGroundException;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
            case RuntimePackage.SIMPLE_TERM__TRULE_TGT:
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
                case RuntimePackage.SIMPLE_TERM__TRULE_TGT: return RuntimePackage.TARGET_TERM__TRULE_TGT;
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
                case RuntimePackage.TARGET_TERM__TRULE_TGT: return RuntimePackage.SIMPLE_TERM__TRULE_TGT;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //SimpleTermImpl