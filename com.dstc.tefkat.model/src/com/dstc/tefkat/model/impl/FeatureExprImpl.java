/**
 * <copyright>
 * </copyright>
 *
 * $Id: FeatureExprImpl.java,v 1.6 2005/05/16 01:52:27 lawley Exp $
 */
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CompoundExpr;
import com.dstc.tefkat.model.Expression;
import com.dstc.tefkat.model.FeatureExpr;
import com.dstc.tefkat.model.StringConstant;
import com.dstc.tefkat.model.TefkatFactory;
import com.dstc.tefkat.model.TefkatPackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.FeatureExprImpl#isOperation <em>Operation</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.FeatureExprImpl#isCollect <em>Collect</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.FeatureExprImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureExprImpl extends CompoundExprImpl implements FeatureExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

    /**
     * The default value of the '{@link #isOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOperation()
     * @generated
     * @ordered
     */
    protected static final boolean OPERATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOperation()
     * @generated
     * @ordered
     */
    protected boolean operation = OPERATION_EDEFAULT;

    /**
     * The default value of the '{@link #isCollect() <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollect()
     * @generated
     * @ordered
     */
    protected static final boolean COLLECT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCollect() <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollect()
     * @generated
     * @ordered
     */
    protected boolean collect = COLLECT_EDEFAULT;

    /**
     * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeature()
     * @generated
     * @ordered
     */
	protected Expression feature = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FeatureExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getFeatureExpr();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOperation() {
        return operation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperation(boolean newOperation) {
        boolean oldOperation = operation;
        operation = newOperation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.FEATURE_EXPR__OPERATION, oldOperation, operation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCollect() {
        return collect;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCollect(boolean newCollect) {
        boolean oldCollect = collect;
        collect = newCollect;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.FEATURE_EXPR__COLLECT, oldCollect, collect));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFeature(Expression newFeature, NotificationChain msgs) {
        Expression oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TefkatPackage.FEATURE_EXPR__FEATURE, oldFeature, newFeature);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeature(Expression newFeature) {
        if (newFeature != feature) {
            NotificationChain msgs = null;
            if (feature != null)
                msgs = ((InternalEObject)feature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.FEATURE_EXPR__FEATURE, null, msgs);
            if (newFeature != null)
                msgs = ((InternalEObject)newFeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TefkatPackage.FEATURE_EXPR__FEATURE, null, msgs);
            msgs = basicSetFeature(newFeature, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.FEATURE_EXPR__FEATURE, newFeature, newFeature));
    }

    /**
     * <!-- begin-user-doc -->
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        FeatureExpr copy = TefkatFactory.eINSTANCE.createFeatureExpr();
        copy.setCollect(isCollect());
        copy.setOperation(isOperation());
        copy.setFeature(getFeature().copy());
        List args = copy.getArg();
        for (Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            args.add(((Expression) itr.next()).copy());
        }
        return copy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.FEATURE_EXPR__EXPR:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TefkatPackage.FEATURE_EXPR__EXPR, msgs);
                case TefkatPackage.FEATURE_EXPR__ARG:
                    return ((InternalEList)getArg()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.FEATURE_EXPR__EXPR:
                    return eBasicSetContainer(null, TefkatPackage.FEATURE_EXPR__EXPR, msgs);
                case TefkatPackage.FEATURE_EXPR__ARG:
                    return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
                case TefkatPackage.FEATURE_EXPR__FEATURE:
                    return basicSetFeature(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
        if (eContainerFeatureID >= 0) {
            switch (eContainerFeatureID) {
                case TefkatPackage.FEATURE_EXPR__EXPR:
                    return ((InternalEObject)eContainer).eInverseRemove(this, TefkatPackage.COMPOUND_EXPR__ARG, CompoundExpr.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.FEATURE_EXPR__EXPR:
                return getExpr();
            case TefkatPackage.FEATURE_EXPR__ARG:
                return getArg();
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                return isOperation() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                return isCollect() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                return getFeature();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.FEATURE_EXPR__EXPR:
                setExpr((CompoundExpr)newValue);
                return;
            case TefkatPackage.FEATURE_EXPR__ARG:
                getArg().clear();
                getArg().addAll((Collection)newValue);
                return;
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                setOperation(((Boolean)newValue).booleanValue());
                return;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                setCollect(((Boolean)newValue).booleanValue());
                return;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                setFeature((Expression)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.FEATURE_EXPR__EXPR:
                setExpr((CompoundExpr)null);
                return;
            case TefkatPackage.FEATURE_EXPR__ARG:
                getArg().clear();
                return;
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                setOperation(OPERATION_EDEFAULT);
                return;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                setCollect(COLLECT_EDEFAULT);
                return;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                setFeature((Expression)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.FEATURE_EXPR__EXPR:
                return getExpr() != null;
            case TefkatPackage.FEATURE_EXPR__ARG:
                return arg != null && !arg.isEmpty();
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                return operation != OPERATION_EDEFAULT;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                return collect != COLLECT_EDEFAULT;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                return feature != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();
        
        List args = getArg();
        String featureName = String.valueOf(getFeature());
        if (!(getFeature() instanceof StringConstant)) {
            featureName = "$" + featureName;
        }
        String result;
        
        if (args.size() == 1) {
            result = args.get(0) + "." + featureName + (operation ? "()" : "") + (collect ? "{}" : "");
        } else if (args.size() > 1 && operation) {
            result = args.get(0) + "." + featureName + "(" + args.get(1);
            for (int i = 2; i < args.size(); i++) {
                result += "," + args.get(i);
            }
            result += (collect ? "){}" : ")");
        } else {
            result = super.toString();
        }

        return result;
    }

} //FeatureExprImpl
