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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tefkat.engine.trace.TraceFactory
 * @model kind="package"
 * @generated
 */
public interface TracePackage extends EPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2004";

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "trace";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///com/dstc/tefkat/TefkatTrace.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "trace";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TracePackage eINSTANCE = tefkat.engine.trace.impl.TracePackageImpl.init();

    /**
     * The meta object id for the '{@link tefkat.engine.trace.impl.TraceImpl <em>Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.trace.impl.TraceImpl
     * @see tefkat.engine.trace.impl.TracePackageImpl#getTrace()
     * @generated
     */
    int TRACE = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACE__NAME = 0;

    /**
     * The feature id for the '<em><b>Sources</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACE__SOURCES = 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACE__TARGET = 2;

    /**
     * The feature id for the '<em><b>Rules</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACE__RULES = 3;

    /**
     * The number of structural features of the the '<em>Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link tefkat.engine.trace.impl.AnyImpl <em>Any</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.trace.impl.AnyImpl
     * @see tefkat.engine.trace.impl.TracePackageImpl#getAny()
     * @generated
     */
    int ANY = 1;

    /**
     * The number of structural features of the the '<em>Any</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANY_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link tefkat.engine.trace.impl.IntAnyImpl <em>Int Any</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.trace.impl.IntAnyImpl
     * @see tefkat.engine.trace.impl.TracePackageImpl#getIntAny()
     * @generated
     */
    int INT_ANY = 2;

    /**
     * The feature id for the '<em><b>Int</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANY__INT = ANY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Int Any</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANY_FEATURE_COUNT = ANY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link tefkat.engine.trace.impl.StringAnyImpl <em>String Any</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.trace.impl.StringAnyImpl
     * @see tefkat.engine.trace.impl.TracePackageImpl#getStringAny()
     * @generated
     */
    int STRING_ANY = 3;

    /**
     * The feature id for the '<em><b>String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANY__STRING = ANY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>String Any</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANY_FEATURE_COUNT = ANY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link tefkat.engine.trace.impl.ObjectAnyImpl <em>Object Any</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.trace.impl.ObjectAnyImpl
     * @see tefkat.engine.trace.impl.TracePackageImpl#getObjectAny()
     * @generated
     */
    int OBJECT_ANY = 4;

    /**
     * The feature id for the '<em><b>Ref</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_ANY__REF = ANY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Object Any</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_ANY_FEATURE_COUNT = ANY_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link tefkat.engine.trace.Trace <em>Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Trace</em>'.
     * @see tefkat.engine.trace.Trace
     * @generated
     */
    EClass getTrace();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.trace.Trace#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.engine.trace.Trace#getName()
     * @see #getTrace()
     * @generated
     */
    EAttribute getTrace_Name();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.trace.Trace#getSources <em>Sources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sources</em>'.
     * @see tefkat.engine.trace.Trace#getSources()
     * @see #getTrace()
     * @generated
     */
    EReference getTrace_Sources();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.trace.Trace#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see tefkat.engine.trace.Trace#getTarget()
     * @see #getTrace()
     * @generated
     */
    EReference getTrace_Target();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.trace.Trace#getRules <em>Rules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Rules</em>'.
     * @see tefkat.engine.trace.Trace#getRules()
     * @see #getTrace()
     * @generated
     */
    EReference getTrace_Rules();

    /**
     * Returns the meta object for class '{@link tefkat.engine.trace.Any <em>Any</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Any</em>'.
     * @see tefkat.engine.trace.Any
     * @generated
     */
    EClass getAny();

    /**
     * Returns the meta object for class '{@link tefkat.engine.trace.IntAny <em>Int Any</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Any</em>'.
     * @see tefkat.engine.trace.IntAny
     * @generated
     */
    EClass getIntAny();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.trace.IntAny#getInt <em>Int</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Int</em>'.
     * @see tefkat.engine.trace.IntAny#getInt()
     * @see #getIntAny()
     * @generated
     */
    EAttribute getIntAny_Int();

    /**
     * Returns the meta object for class '{@link tefkat.engine.trace.StringAny <em>String Any</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Any</em>'.
     * @see tefkat.engine.trace.StringAny
     * @generated
     */
    EClass getStringAny();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.trace.StringAny#getString <em>String</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String</em>'.
     * @see tefkat.engine.trace.StringAny#getString()
     * @see #getStringAny()
     * @generated
     */
    EAttribute getStringAny_String();

    /**
     * Returns the meta object for class '{@link tefkat.engine.trace.ObjectAny <em>Object Any</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Any</em>'.
     * @see tefkat.engine.trace.ObjectAny
     * @generated
     */
    EClass getObjectAny();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.trace.ObjectAny#getRef <em>Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Ref</em>'.
     * @see tefkat.engine.trace.ObjectAny#getRef()
     * @see #getObjectAny()
     * @generated
     */
    EReference getObjectAny_Ref();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TraceFactory getTraceFactory();

} //TracePackage
