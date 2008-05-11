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
 */
package tefkat.model.impl;


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

import tefkat.model.CompoundExpr;
import tefkat.model.Expression;
import tefkat.model.FeatureExpr;
import tefkat.model.StringConstant;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.FeatureExprImpl#isOperation <em>Operation</em>}</li>
 *   <li>{@link tefkat.model.impl.FeatureExprImpl#isCollect <em>Collect</em>}</li>
 *   <li>{@link tefkat.model.impl.FeatureExprImpl#getFeature <em>Feature</em>}</li>
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

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
        return TefkatPackage.Literals.FEATURE_EXPR;
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
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                return basicSetFeature(null, msgs);
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
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                return isOperation() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                return isCollect() ? Boolean.TRUE : Boolean.FALSE;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                return getFeature();
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
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
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
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TefkatPackage.FEATURE_EXPR__OPERATION:
                return operation != OPERATION_EDEFAULT;
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                return collect != COLLECT_EDEFAULT;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
                return feature != null;
        }
        return super.eIsSet(featureID);
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
