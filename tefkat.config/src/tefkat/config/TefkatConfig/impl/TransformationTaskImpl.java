/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 * $Id$
 */
package tefkat.config.TefkatConfig.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.config.TefkatConfig.AbstractModel;
import tefkat.config.TefkatConfig.ExecutionMode;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TefkatConfigPackage;
import tefkat.config.TefkatConfig.TransformationTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getSourceModels <em>Source Models</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getTargetModels <em>Target Models</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getTrace <em>Trace</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link tefkat.config.TefkatConfig.impl.TransformationTaskImpl#getUriMap <em>Uri Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationTaskImpl extends EObjectImpl implements TransformationTask {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransformation()
     * @generated
     * @ordered
     */
    protected Model transformation = null;

    /**
     * The cached value of the '{@link #getSourceModels() <em>Source Models</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceModels()
     * @generated
     * @ordered
     */
    protected EList sourceModels = null;

    /**
     * The cached value of the '{@link #getTargetModels() <em>Target Models</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetModels()
     * @generated
     * @ordered
     */
    protected EList targetModels = null;

    /**
     * The cached value of the '{@link #getTrace() <em>Trace</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTrace()
     * @generated
     * @ordered
     */
    protected Model trace = null;

    /**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected boolean enabled = ENABLED_EDEFAULT;

    /**
     * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected static final ExecutionMode MODE_EDEFAULT = ExecutionMode.REPLACE_LITERAL;

    /**
     * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected ExecutionMode mode = MODE_EDEFAULT;

    /**
     * This is true if the Mode attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean modeESet = false;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EMap properties = null;

    /**
     * The cached value of the '{@link #getUriMap() <em>Uri Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUriMap()
     * @generated
     * @ordered
     */
    protected EMap uriMap = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TransformationTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatConfigPackage.Literals.TRANSFORMATION_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Model getTransformation() {
        return transformation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTransformation(Model newTransformation, NotificationChain msgs) {
        Model oldTransformation = transformation;
        transformation = newTransformation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION, oldTransformation, newTransformation);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransformation(Model newTransformation) {
        if (newTransformation != transformation) {
            NotificationChain msgs = null;
            if (transformation != null)
                msgs = ((InternalEObject)transformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION, null, msgs);
            if (newTransformation != null)
                msgs = ((InternalEObject)newTransformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION, null, msgs);
            msgs = basicSetTransformation(newTransformation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION, newTransformation, newTransformation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSourceModels() {
        if (sourceModels == null) {
            sourceModels = new EObjectContainmentEList(Model.class, this, TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS);
        }
        return sourceModels;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTargetModels() {
        if (targetModels == null) {
            targetModels = new EObjectContainmentEList(Model.class, this, TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS);
        }
        return targetModels;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Model getTrace() {
        return trace;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTrace(Model newTrace, NotificationChain msgs) {
        Model oldTrace = trace;
        trace = newTrace;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__TRACE, oldTrace, newTrace);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTrace(Model newTrace) {
        if (newTrace != trace) {
            NotificationChain msgs = null;
            if (trace != null)
                msgs = ((InternalEObject)trace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatConfigPackage.TRANSFORMATION_TASK__TRACE, null, msgs);
            if (newTrace != null)
                msgs = ((InternalEObject)newTrace).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatConfigPackage.TRANSFORMATION_TASK__TRACE, null, msgs);
            msgs = basicSetTrace(newTrace, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__TRACE, newTrace, newTrace));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED, oldEnabled, enabled));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionMode getMode() {
        return mode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMode(ExecutionMode newMode) {
        ExecutionMode oldMode = mode;
        mode = newMode == null ? MODE_EDEFAULT : newMode;
        boolean oldModeESet = modeESet;
        modeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatConfigPackage.TRANSFORMATION_TASK__MODE, oldMode, mode, !oldModeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMode() {
        ExecutionMode oldMode = mode;
        boolean oldModeESet = modeESet;
        mode = MODE_EDEFAULT;
        modeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, TefkatConfigPackage.TRANSFORMATION_TASK__MODE, oldMode, MODE_EDEFAULT, oldModeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMode() {
        return modeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getProperties() {
        if (properties == null) {
            properties = new EcoreEMap(TefkatConfigPackage.Literals.PROPERTY, PropertyImpl.class, this, TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap getUriMap() {
        if (uriMap == null) {
            uriMap = new EcoreEMap(TefkatConfigPackage.Literals.URI_MAP_ENTRY, URIMapEntryImpl.class, this, TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP);
        }
        return uriMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
                return basicSetTransformation(null, msgs);
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
                return ((InternalEList)getSourceModels()).basicRemove(otherEnd, msgs);
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
                return ((InternalEList)getTargetModels()).basicRemove(otherEnd, msgs);
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
                return basicSetTrace(null, msgs);
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
                return ((InternalEList)getProperties()).basicRemove(otherEnd, msgs);
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
                return ((InternalEList)getUriMap()).basicRemove(otherEnd, msgs);
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
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
                return getTransformation();
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
                return getSourceModels();
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
                return getTargetModels();
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
                return getTrace();
            case TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED:
                return isEnabled() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatConfigPackage.TRANSFORMATION_TASK__MODE:
                return getMode();
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
                if (coreType) return getProperties();
                else return getProperties().map();
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
                if (coreType) return getUriMap();
                else return getUriMap().map();
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
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
                setTransformation((Model)newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
                getSourceModels().clear();
                getSourceModels().addAll((Collection)newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
                getTargetModels().clear();
                getTargetModels().addAll((Collection)newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
                setTrace((Model)newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED:
                setEnabled(((Boolean)newValue).booleanValue());
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__MODE:
                setMode((ExecutionMode)newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
                ((EStructuralFeature.Setting)getProperties()).set(newValue);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
                ((EStructuralFeature.Setting)getUriMap()).set(newValue);
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
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
                setTransformation((Model)null);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
                getSourceModels().clear();
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
                getTargetModels().clear();
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
                setTrace((Model)null);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED:
                setEnabled(ENABLED_EDEFAULT);
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__MODE:
                unsetMode();
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
                getProperties().clear();
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
                getUriMap().clear();
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
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
                return transformation != null;
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
                return sourceModels != null && !sourceModels.isEmpty();
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
                return targetModels != null && !targetModels.isEmpty();
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
                return trace != null;
            case TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED:
                return enabled != ENABLED_EDEFAULT;
            case TefkatConfigPackage.TRANSFORMATION_TASK__MODE:
                return isSetMode();
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
                return properties != null && !properties.isEmpty();
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
                return uriMap != null && !uriMap.isEmpty();
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
        result.append(" (enabled: ");
        result.append(enabled);
        result.append(", mode: ");
        if (modeESet) result.append(mode); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //TransformationTaskImpl
