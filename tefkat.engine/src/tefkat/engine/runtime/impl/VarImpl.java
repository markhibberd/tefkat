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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.VarScope;
import tefkat.engine.runtime.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getName <em>Name</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getUsages <em>Usages</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getSuperseder <em>Superseder</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.VarImpl#getExtender <em>Extender</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VarImpl extends EObjectImpl implements Var {
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
     * The cached value of the '{@link #getUsages() <em>Usages</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsages()
     * @generated
     * @ordered
     */
    protected EList usages = null;

    /**
     * The cached value of the '{@link #getSuperseded() <em>Superseded</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseded()
     * @generated
     * @ordered
     */
    protected EList superseded = null;

    /**
     * The cached value of the '{@link #getSuperseder() <em>Superseder</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseder()
     * @generated
     * @ordered
     */
    protected EList superseder = null;

    /**
     * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtended()
     * @generated
     * @ordered
     */
    protected EList extended = null;

    /**
     * The cached value of the '{@link #getExtender() <em>Extender</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtender()
     * @generated
     * @ordered
     */
    protected EList extender = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VarImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.VAR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarScope getScope() {
        if (eContainerFeatureID != RuntimePackage.VAR__SCOPE) return null;
        return (VarScope)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScope(VarScope newScope, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newScope, RuntimePackage.VAR__SCOPE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScope(VarScope newScope) {
        if (newScope != eInternalContainer() || (eContainerFeatureID != RuntimePackage.VAR__SCOPE && newScope != null)) {
            if (EcoreUtil.isAncestor(this, newScope))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newScope != null)
                msgs = ((InternalEObject)newScope).eInverseAdd(this, RuntimePackage.VAR_SCOPE__VARS, VarScope.class, msgs);
            msgs = basicSetScope(newScope, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.VAR__SCOPE, newScope, newScope));
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
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.VAR__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getUsages() {
        if (usages == null) {
            usages = new EObjectWithInverseResolvingEList(VarUse.class, this, RuntimePackage.VAR__USAGES, RuntimePackage.VAR_USE__VAR);
        }
        return usages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseded() {
        if (superseded == null) {
            superseded = new EObjectWithInverseResolvingEList.ManyInverse(Var.class, this, RuntimePackage.VAR__SUPERSEDED, RuntimePackage.VAR__SUPERSEDER);
        }
        return superseded;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseder() {
        if (superseder == null) {
            superseder = new EObjectWithInverseResolvingEList.ManyInverse(Var.class, this, RuntimePackage.VAR__SUPERSEDER, RuntimePackage.VAR__SUPERSEDED);
        }
        return superseder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtended() {
        if (extended == null) {
            extended = new EObjectWithInverseResolvingEList.ManyInverse(Var.class, this, RuntimePackage.VAR__EXTENDED, RuntimePackage.VAR__EXTENDER);
        }
        return extended;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtender() {
        if (extender == null) {
            extender = new EObjectWithInverseResolvingEList.ManyInverse(Var.class, this, RuntimePackage.VAR__EXTENDER, RuntimePackage.VAR__EXTENDED);
        }
        return extender;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.VAR__SCOPE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetScope((VarScope)otherEnd, msgs);
            case RuntimePackage.VAR__USAGES:
                return ((InternalEList)getUsages()).basicAdd(otherEnd, msgs);
            case RuntimePackage.VAR__SUPERSEDED:
                return ((InternalEList)getSuperseded()).basicAdd(otherEnd, msgs);
            case RuntimePackage.VAR__SUPERSEDER:
                return ((InternalEList)getSuperseder()).basicAdd(otherEnd, msgs);
            case RuntimePackage.VAR__EXTENDED:
                return ((InternalEList)getExtended()).basicAdd(otherEnd, msgs);
            case RuntimePackage.VAR__EXTENDER:
                return ((InternalEList)getExtender()).basicAdd(otherEnd, msgs);
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
            case RuntimePackage.VAR__SCOPE:
                return basicSetScope(null, msgs);
            case RuntimePackage.VAR__USAGES:
                return ((InternalEList)getUsages()).basicRemove(otherEnd, msgs);
            case RuntimePackage.VAR__SUPERSEDED:
                return ((InternalEList)getSuperseded()).basicRemove(otherEnd, msgs);
            case RuntimePackage.VAR__SUPERSEDER:
                return ((InternalEList)getSuperseder()).basicRemove(otherEnd, msgs);
            case RuntimePackage.VAR__EXTENDED:
                return ((InternalEList)getExtended()).basicRemove(otherEnd, msgs);
            case RuntimePackage.VAR__EXTENDER:
                return ((InternalEList)getExtender()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.VAR__SCOPE:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.VAR_SCOPE__VARS, VarScope.class, msgs);
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
            case RuntimePackage.VAR__SCOPE:
                return getScope();
            case RuntimePackage.VAR__NAME:
                return getName();
            case RuntimePackage.VAR__USAGES:
                return getUsages();
            case RuntimePackage.VAR__SUPERSEDED:
                return getSuperseded();
            case RuntimePackage.VAR__SUPERSEDER:
                return getSuperseder();
            case RuntimePackage.VAR__EXTENDED:
                return getExtended();
            case RuntimePackage.VAR__EXTENDER:
                return getExtender();
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
            case RuntimePackage.VAR__SCOPE:
                setScope((VarScope)newValue);
                return;
            case RuntimePackage.VAR__NAME:
                setName((String)newValue);
                return;
            case RuntimePackage.VAR__SUPERSEDED:
                getSuperseded().clear();
                getSuperseded().addAll((Collection)newValue);
                return;
            case RuntimePackage.VAR__SUPERSEDER:
                getSuperseder().clear();
                getSuperseder().addAll((Collection)newValue);
                return;
            case RuntimePackage.VAR__EXTENDED:
                getExtended().clear();
                getExtended().addAll((Collection)newValue);
                return;
            case RuntimePackage.VAR__EXTENDER:
                getExtender().clear();
                getExtender().addAll((Collection)newValue);
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
            case RuntimePackage.VAR__SCOPE:
                setScope((VarScope)null);
                return;
            case RuntimePackage.VAR__NAME:
                setName(NAME_EDEFAULT);
                return;
            case RuntimePackage.VAR__SUPERSEDED:
                getSuperseded().clear();
                return;
            case RuntimePackage.VAR__SUPERSEDER:
                getSuperseder().clear();
                return;
            case RuntimePackage.VAR__EXTENDED:
                getExtended().clear();
                return;
            case RuntimePackage.VAR__EXTENDER:
                getExtender().clear();
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
            case RuntimePackage.VAR__SCOPE:
                return getScope() != null;
            case RuntimePackage.VAR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case RuntimePackage.VAR__USAGES:
                return usages != null && !usages.isEmpty();
            case RuntimePackage.VAR__SUPERSEDED:
                return superseded != null && !superseded.isEmpty();
            case RuntimePackage.VAR__SUPERSEDER:
                return superseder != null && !superseder.isEmpty();
            case RuntimePackage.VAR__EXTENDED:
                return extended != null && !extended.isEmpty();
            case RuntimePackage.VAR__EXTENDER:
                return extender != null && !extender.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        return null == getName() ? ("_" + hashCode()) : getName();
    }

} //VarImpl