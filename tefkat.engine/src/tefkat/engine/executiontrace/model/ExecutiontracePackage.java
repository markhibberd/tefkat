/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.executiontrace.model;

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
 * @see tefkat.engine.executiontrace.model.ExecutiontraceFactory
 * @model kind="package"
 * @generated
 */
public interface ExecutiontracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "executiontrace";

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
	String eNS_PREFIX = "executiontrace";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExecutiontracePackage eINSTANCE = tefkat.engine.executiontrace.model.impl.ExecutiontracePackageImpl.init();

	/**
	 * The meta object id for the '{@link tefkat.engine.executiontrace.model.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tefkat.engine.executiontrace.model.impl.TraceImpl
	 * @see tefkat.engine.executiontrace.model.impl.ExecutiontracePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__REFS = 1;

	/**
	 * The feature id for the '<em><b>Stuff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__STUFF = 2;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link tefkat.engine.executiontrace.model.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see tefkat.engine.executiontrace.model.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the attribute '{@link tefkat.engine.executiontrace.model.Trace#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see tefkat.engine.executiontrace.model.Trace#getType()
	 * @see #getTrace()
	 * @generated
	 */
	EAttribute getTrace_Type();

	/**
	 * Returns the meta object for the reference list '{@link tefkat.engine.executiontrace.model.Trace#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refs</em>'.
	 * @see tefkat.engine.executiontrace.model.Trace#getRefs()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Refs();

	/**
	 * Returns the meta object for the attribute '{@link tefkat.engine.executiontrace.model.Trace#getStuff <em>Stuff</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stuff</em>'.
	 * @see tefkat.engine.executiontrace.model.Trace#getStuff()
	 * @see #getTrace()
	 * @generated
	 */
	EAttribute getTrace_Stuff();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExecutiontraceFactory getExecutiontraceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link tefkat.engine.executiontrace.model.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tefkat.engine.executiontrace.model.impl.TraceImpl
		 * @see tefkat.engine.executiontrace.model.impl.ExecutiontracePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE__TYPE = eINSTANCE.getTrace_Type();

		/**
		 * The meta object literal for the '<em><b>Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__REFS = eINSTANCE.getTrace_Refs();

		/**
		 * The meta object literal for the '<em><b>Stuff</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE__STUFF = eINSTANCE.getTrace_Stuff();

	}

} //ExecutiontracePackage
