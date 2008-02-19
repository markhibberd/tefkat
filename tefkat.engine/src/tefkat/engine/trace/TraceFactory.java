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
package tefkat.engine.trace;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tefkat.engine.trace.TracePackage
 * @generated
 */
public interface TraceFactory extends EFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TraceFactory eINSTANCE = tefkat.engine.trace.impl.TraceFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Trace</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Trace</em>'.
     * @generated
     */
    Trace createTrace();

    /**
     * Returns a new object of class '<em>Int Any</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Int Any</em>'.
     * @generated
     */
    IntAny createIntAny();

    /**
     * Returns a new object of class '<em>Decimal Any</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Decimal Any</em>'.
     * @generated
     */
    DecimalAny createDecimalAny();

    /**
     * Returns a new object of class '<em>String Any</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>String Any</em>'.
     * @generated
     */
    StringAny createStringAny();

    /**
     * Returns a new object of class '<em>Object Any</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Object Any</em>'.
     * @generated
     */
    ObjectAny createObjectAny();

    /**
     * Returns a new object of class '<em>Name Value Pair</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Name Value Pair</em>'.
     * @generated
     */
    NameValuePair createNameValuePair();

    /**
     * Returns a new object of class '<em>Bool Any</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Bool Any</em>'.
     * @generated
     */
    BoolAny createBoolAny();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TracePackage getTracePackage();

} //TraceFactory
