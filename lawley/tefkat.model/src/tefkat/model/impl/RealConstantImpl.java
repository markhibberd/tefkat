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


import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import tefkat.model.CompoundExpr;
import tefkat.model.Expression;
import tefkat.model.RealConstant;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Real Constant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class RealConstantImpl extends SimpleExprImpl implements RealConstant {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RealConstantImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * Create a (deep) copy of this instance.
     * Contained EObjects are also copied while referenced EObjects are not.
     * <!-- end-user-doc -->
     */
    public Expression copy() {
        RealConstant copy = TefkatFactory.eINSTANCE.createRealConstant();
        copy.setRepresentation(getRepresentation());
        return copy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.REAL_CONSTANT;
    }

} //RealConstantImpl
