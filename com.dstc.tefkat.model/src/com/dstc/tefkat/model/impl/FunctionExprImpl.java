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
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.CompoundExpr;
import com.dstc.tefkat.model.Expression;
import com.dstc.tefkat.model.FunctionExpr;
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
 * An implementation of the model object '<em><b>Function Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.FunctionExprImpl#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionExprImpl extends CompoundExprImpl implements FunctionExpr {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

	/**
	 * The default value of the '{@link #getFunction() <em>Function</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
    protected static final String FUNCTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
    protected String function = FUNCTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected FunctionExprImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return TefkatPackage.eINSTANCE.getFunctionExpr();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFunction(String newFunction) {
		String oldFunction = function;
		function = newFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TefkatPackage.FUNCTION_EXPR__FUNCTION, oldFunction, function));
	}

    /**
     * <!-- begin-user-doc -->
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        FunctionExpr copy = TefkatFactory.eINSTANCE.createFunctionExpr();
        copy.setFunction(getFunction());
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
				case TefkatPackage.FUNCTION_EXPR__EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, TefkatPackage.FUNCTION_EXPR__EXPR, msgs);
				case TefkatPackage.FUNCTION_EXPR__ARG:
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
				case TefkatPackage.FUNCTION_EXPR__EXPR:
					return eBasicSetContainer(null, TefkatPackage.FUNCTION_EXPR__EXPR, msgs);
				case TefkatPackage.FUNCTION_EXPR__ARG:
					return ((InternalEList)getArg()).basicRemove(otherEnd, msgs);
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
				case TefkatPackage.FUNCTION_EXPR__EXPR:
					return eContainer.eInverseRemove(this, TefkatPackage.COMPOUND_EXPR__ARG, CompoundExpr.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TefkatPackage.FUNCTION_EXPR__EXPR:
				return getExpr();
			case TefkatPackage.FUNCTION_EXPR__ARG:
				return getArg();
			case TefkatPackage.FUNCTION_EXPR__FUNCTION:
				return getFunction();
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
			case TefkatPackage.FUNCTION_EXPR__EXPR:
				setExpr((CompoundExpr)newValue);
				return;
			case TefkatPackage.FUNCTION_EXPR__ARG:
				getArg().clear();
				getArg().addAll((Collection)newValue);
				return;
			case TefkatPackage.FUNCTION_EXPR__FUNCTION:
				setFunction((String)newValue);
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
			case TefkatPackage.FUNCTION_EXPR__EXPR:
				setExpr((CompoundExpr)null);
				return;
			case TefkatPackage.FUNCTION_EXPR__ARG:
				getArg().clear();
				return;
			case TefkatPackage.FUNCTION_EXPR__FUNCTION:
				setFunction(FUNCTION_EDEFAULT);
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
			case TefkatPackage.FUNCTION_EXPR__EXPR:
				return getExpr() != null;
			case TefkatPackage.FUNCTION_EXPR__ARG:
				return arg != null && !arg.isEmpty();
			case TefkatPackage.FUNCTION_EXPR__FUNCTION:
				return FUNCTION_EDEFAULT == null ? function != null : !FUNCTION_EDEFAULT.equals(function);
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
        
        String result = "";
        for (Iterator itr = getArg().iterator(); itr.hasNext(); ) {
            result  = result + ", " + itr.next();
        }

        return "apply(" + function + result + ")";
    }

} //FunctionExprImpl
