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
import tefkat.engine.runtime.SourceTerm;
import tefkat.engine.runtime.TRule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.SourceTermImpl#getTRuleSrc <em>TRule Src</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SourceTermImpl extends TermImpl implements SourceTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";
    
    static final String NOT_BOUND_MESSAGE = " was not bound in source term!";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SourceTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.SOURCE_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule getTRuleSrc() {
        if (eContainerFeatureID != RuntimePackage.SOURCE_TERM__TRULE_SRC) return null;
        return (TRule)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTRuleSrc(TRule newTRuleSrc, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTRuleSrc, RuntimePackage.SOURCE_TERM__TRULE_SRC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTRuleSrc(TRule newTRuleSrc) {
        if (newTRuleSrc != eInternalContainer() || (eContainerFeatureID != RuntimePackage.SOURCE_TERM__TRULE_SRC && newTRuleSrc != null)) {
            if (EcoreUtil.isAncestor(this, newTRuleSrc))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTRuleSrc != null)
                msgs = ((InternalEObject)newTRuleSrc).eInverseAdd(this, RuntimePackage.TRULE__SRC, TRule.class, msgs);
            msgs = basicSetTRuleSrc(newTRuleSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.SOURCE_TERM__TRULE_SRC, newTRuleSrc, newTRuleSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract void match(Context context) throws ResolutionException, NotGroundException;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTRuleSrc((TRule)otherEnd, msgs);
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                return basicSetTRuleSrc(null, msgs);
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.TRULE__SRC, TRule.class, msgs);
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                return getTRuleSrc();
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                setTRuleSrc((TRule)newValue);
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                setTRuleSrc((TRule)null);
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
            case RuntimePackage.SOURCE_TERM__TRULE_SRC:
                return getTRuleSrc() != null;
        }
        return super.eIsSet(featureID);
    }

} //SourceTermImpl