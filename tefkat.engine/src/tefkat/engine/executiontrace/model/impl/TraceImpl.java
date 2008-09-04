/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.executiontrace.model.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import tefkat.engine.executiontrace.model.ExecutiontracePackage;
import tefkat.engine.executiontrace.model.Trace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.executiontrace.model.impl.TraceImpl#getType <em>Type</em>}</li>
 *   <li>{@link tefkat.engine.executiontrace.model.impl.TraceImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link tefkat.engine.executiontrace.model.impl.TraceImpl#getStuff <em>Stuff</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceImpl extends EObjectImpl implements Trace {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> refs;

	/**
	 * The default value of the '{@link #getStuff() <em>Stuff</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStuff()
	 * @generated
	 * @ordered
	 */
	protected static final String STUFF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStuff() <em>Stuff</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStuff()
	 * @generated
	 * @ordered
	 */
	protected String stuff = STUFF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutiontracePackage.Literals.TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutiontracePackage.TRACE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getRefs() {
		if (refs == null) {
			refs = new EObjectResolvingEList<EObject>(EObject.class, this, ExecutiontracePackage.TRACE__REFS);
		}
		return refs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStuff() {
		return stuff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStuff(String newStuff) {
		String oldStuff = stuff;
		stuff = newStuff;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutiontracePackage.TRACE__STUFF, oldStuff, stuff));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutiontracePackage.TRACE__TYPE:
				return getType();
			case ExecutiontracePackage.TRACE__REFS:
				return getRefs();
			case ExecutiontracePackage.TRACE__STUFF:
				return getStuff();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExecutiontracePackage.TRACE__TYPE:
				setType((String)newValue);
				return;
			case ExecutiontracePackage.TRACE__REFS:
				getRefs().clear();
				getRefs().addAll((Collection<? extends EObject>)newValue);
				return;
			case ExecutiontracePackage.TRACE__STUFF:
				setStuff((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExecutiontracePackage.TRACE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ExecutiontracePackage.TRACE__REFS:
				getRefs().clear();
				return;
			case ExecutiontracePackage.TRACE__STUFF:
				setStuff(STUFF_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExecutiontracePackage.TRACE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ExecutiontracePackage.TRACE__REFS:
				return refs != null && !refs.isEmpty();
			case ExecutiontracePackage.TRACE__STUFF:
				return STUFF_EDEFAULT == null ? stuff != null : !STUFF_EDEFAULT.equals(stuff);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(", stuff: ");
		result.append(stuff);
		result.append(')');
		return result.toString();
	}

} //TraceImpl
