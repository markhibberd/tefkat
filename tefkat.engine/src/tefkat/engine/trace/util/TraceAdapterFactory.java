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
package tefkat.engine.trace.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tefkat.engine.trace.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tefkat.engine.trace.TracePackage
 * @generated
 */
public class TraceAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TracePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TraceAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TracePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch the delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TraceSwitch<Adapter> modelSwitch =
        new TraceSwitch<Adapter>() {
            @Override
            public Adapter caseTrace(Trace object) {
                return createTraceAdapter();
            }
            @Override
            public Adapter caseAny(Any object) {
                return createAnyAdapter();
            }
            @Override
            public Adapter caseBoolAny(BoolAny object) {
                return createBoolAnyAdapter();
            }
            @Override
            public Adapter caseIntAny(IntAny object) {
                return createIntAnyAdapter();
            }
            @Override
            public Adapter caseDecimalAny(DecimalAny object) {
                return createDecimalAnyAdapter();
            }
            @Override
            public Adapter caseStringAny(StringAny object) {
                return createStringAnyAdapter();
            }
            @Override
            public Adapter caseObjectAny(ObjectAny object) {
                return createObjectAnyAdapter();
            }
            @Override
            public Adapter caseNameValuePair(NameValuePair object) {
                return createNameValuePairAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.Trace <em>Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.Trace
     * @generated
     */
    public Adapter createTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.Any <em>Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.Any
     * @generated
     */
    public Adapter createAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.IntAny <em>Int Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.IntAny
     * @generated
     */
    public Adapter createIntAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.DecimalAny <em>Decimal Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.DecimalAny
     * @generated
     */
    public Adapter createDecimalAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.StringAny <em>String Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.StringAny
     * @generated
     */
    public Adapter createStringAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.ObjectAny <em>Object Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.ObjectAny
     * @generated
     */
    public Adapter createObjectAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.NameValuePair <em>Name Value Pair</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.NameValuePair
     * @generated
     */
    public Adapter createNameValuePairAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.trace.BoolAny <em>Bool Any</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.trace.BoolAny
     * @generated
     */
    public Adapter createBoolAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //TraceAdapterFactory
