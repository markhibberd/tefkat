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
import tefkat.model.FunctionExpr;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.FunctionExprImpl#getFunction <em>Function</em>}</li>
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

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
        return TefkatPackage.Literals.FUNCTION_EXPR;
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
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.FUNCTION_EXPR__FUNCTION:
                return getFunction();
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
            case TefkatPackage.FUNCTION_EXPR__FUNCTION:
                setFunction((String)newValue);
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
            case TefkatPackage.FUNCTION_EXPR__FUNCTION:
                setFunction(FUNCTION_EDEFAULT);
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
            case TefkatPackage.FUNCTION_EXPR__FUNCTION:
                return FUNCTION_EDEFAULT == null ? function != null : !FUNCTION_EDEFAULT.equals(function);
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
