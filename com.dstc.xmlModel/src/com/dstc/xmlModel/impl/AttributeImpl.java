/**
 * <copyright>
 * </copyright>
 *
 */
package com.dstc.xmlModel.impl;

import com.dstc.xmlModel.Attribute;
import com.dstc.xmlModel.Element;
import com.dstc.xmlModel.Namespace;
import com.dstc.xmlModel.XmlModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getQName <em>QName</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getUName <em>UName</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.AttributeImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeImpl extends EObjectImpl implements Attribute {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "(c) michael lawley";

    /**
     * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespace()
     * @generated
     * @ordered
     */
    protected Namespace namespace = null;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getQName() <em>QName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQName()
     * @generated
     * @ordered
     */
    protected static final String QNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQName() <em>QName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQName()
     * @generated
     * @ordered
     */
    protected String qName = QNAME_EDEFAULT;

    /**
     * The default value of the '{@link #getUName() <em>UName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUName()
     * @generated
     * @ordered
     */
	protected static final String UNAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = VALUE_EDEFAULT;

    /**
     * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReference()
     * @generated
     * @ordered
     */
    protected Element reference = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AttributeImpl() {
        super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return XmlModelPackage.eINSTANCE.getAttribute();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Namespace getNamespace() {
        if (namespace != null && namespace.eIsProxy()) {
            Namespace oldNamespace = namespace;
            namespace = (Namespace)eResolveProxy((InternalEObject)namespace);
            if (namespace != oldNamespace) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlModelPackage.ATTRIBUTE__NAMESPACE, oldNamespace, namespace));
            }
        }
        return namespace;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Namespace basicGetNamespace() {
        return namespace;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamespace(Namespace newNamespace) {
        Namespace oldNamespace = namespace;
        namespace = newNamespace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ATTRIBUTE__NAMESPACE, oldNamespace, namespace));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ATTRIBUTE__NAME, oldName, name));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getQName() {
        return qName;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQName(String newQName) {
        String oldQName = qName;
        qName = newQName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ATTRIBUTE__QNAME, oldQName, qName));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getUName() {
        return null == namespace ? name : namespace.getUri() + "^" + name;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getValue() {
        return value;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ATTRIBUTE__VALUE, oldValue, value));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Element getReference() {
        if (reference != null && reference.eIsProxy()) {
            Element oldReference = reference;
            reference = (Element)eResolveProxy((InternalEObject)reference);
            if (reference != oldReference) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlModelPackage.ATTRIBUTE__REFERENCE, oldReference, reference));
            }
        }
        return reference;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Element basicGetReference() {
        return reference;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReference(Element newReference) {
        Element oldReference = reference;
        reference = newReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ATTRIBUTE__REFERENCE, oldReference, reference));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlModelPackage.ATTRIBUTE__NAMESPACE:
                if (resolve) return getNamespace();
                return basicGetNamespace();
            case XmlModelPackage.ATTRIBUTE__NAME:
                return getName();
            case XmlModelPackage.ATTRIBUTE__QNAME:
                return getQName();
            case XmlModelPackage.ATTRIBUTE__UNAME:
                return getUName();
            case XmlModelPackage.ATTRIBUTE__VALUE:
                return getValue();
            case XmlModelPackage.ATTRIBUTE__REFERENCE:
                if (resolve) return getReference();
                return basicGetReference();
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
            case XmlModelPackage.ATTRIBUTE__NAMESPACE:
                setNamespace((Namespace)newValue);
                return;
            case XmlModelPackage.ATTRIBUTE__NAME:
                setName((String)newValue);
                return;
            case XmlModelPackage.ATTRIBUTE__QNAME:
                setQName((String)newValue);
                return;
            case XmlModelPackage.ATTRIBUTE__VALUE:
                setValue((String)newValue);
                return;
            case XmlModelPackage.ATTRIBUTE__REFERENCE:
                setReference((Element)newValue);
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
            case XmlModelPackage.ATTRIBUTE__NAMESPACE:
                setNamespace((Namespace)null);
                return;
            case XmlModelPackage.ATTRIBUTE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case XmlModelPackage.ATTRIBUTE__QNAME:
                setQName(QNAME_EDEFAULT);
                return;
            case XmlModelPackage.ATTRIBUTE__VALUE:
                setValue(VALUE_EDEFAULT);
                return;
            case XmlModelPackage.ATTRIBUTE__REFERENCE:
                setReference((Element)null);
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
            case XmlModelPackage.ATTRIBUTE__NAMESPACE:
                return namespace != null;
            case XmlModelPackage.ATTRIBUTE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case XmlModelPackage.ATTRIBUTE__QNAME:
                return QNAME_EDEFAULT == null ? qName != null : !QNAME_EDEFAULT.equals(qName);
            case XmlModelPackage.ATTRIBUTE__UNAME:
                return UNAME_EDEFAULT == null ? getUName() != null : !UNAME_EDEFAULT.equals(getUName());
            case XmlModelPackage.ATTRIBUTE__VALUE:
                return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
            case XmlModelPackage.ATTRIBUTE__REFERENCE:
                return reference != null;
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

        StringBuffer result = new StringBuffer("= ");   //super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", qName: ");
        result.append(qName);
        result.append(", uName: ");
        result.append(getUName());
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
	}

} //AttributeImpl
