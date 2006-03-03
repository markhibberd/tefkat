/**
 * <copyright>
 * </copyright>
 *
 * $Id: ElementImpl.java,v 1.3 2005/05/11 06:23:47 lawley Exp $
 */
package com.dstc.xmlModel.impl;

import com.dstc.xmlModel.Attribute;
import com.dstc.xmlModel.Content;
import com.dstc.xmlModel.Element;
import com.dstc.xmlModel.Namespace;
import com.dstc.xmlModel.XmlModelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getNamespaces <em>Namespaces</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getQName <em>QName</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getUName <em>UName</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.dstc.xmlModel.impl.ElementImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImpl extends ContentImpl implements Element {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "(c) DSTC Pty Ltd";

    /**
     * The cached value of the '{@link #getNamespaces() <em>Namespaces</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespaces()
     * @generated
     * @ordered
     */
    protected EList namespaces = null;

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
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected EList attributes = null;

    /**
     * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContents()
     * @generated
     * @ordered
     */
	protected EList contents = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ElementImpl() {
        super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return XmlModelPackage.eINSTANCE.getElement();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNamespaces() {
        if (namespaces == null) {
            namespaces = new EObjectContainmentEList(Namespace.class, this, XmlModelPackage.ELEMENT__NAMESPACES);
        }
        return namespaces;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlModelPackage.ELEMENT__NAMESPACE, oldNamespace, namespace));
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
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ELEMENT__NAMESPACE, oldNamespace, namespace));
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
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ELEMENT__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, XmlModelPackage.ELEMENT__QNAME, oldQName, qName));
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
    public EList getAttributes() {
        if (attributes == null) {
            attributes = new EObjectContainmentEList(Attribute.class, this, XmlModelPackage.ELEMENT__ATTRIBUTES);
        }
        return attributes;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getContents() {
        if (contents == null) {
            contents = new EObjectContainmentWithInverseEList(Content.class, this, XmlModelPackage.ELEMENT__CONTENTS, XmlModelPackage.CONTENT__PARENT);
        }
        return contents;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case XmlModelPackage.ELEMENT__PARENT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, XmlModelPackage.ELEMENT__PARENT, msgs);
                case XmlModelPackage.ELEMENT__CONTENTS:
                    return ((InternalEList)getContents()).basicAdd(otherEnd, msgs);
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
                case XmlModelPackage.ELEMENT__PARENT:
                    return eBasicSetContainer(null, XmlModelPackage.ELEMENT__PARENT, msgs);
                case XmlModelPackage.ELEMENT__NAMESPACES:
                    return ((InternalEList)getNamespaces()).basicRemove(otherEnd, msgs);
                case XmlModelPackage.ELEMENT__ATTRIBUTES:
                    return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
                case XmlModelPackage.ELEMENT__CONTENTS:
                    return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
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
                case XmlModelPackage.ELEMENT__PARENT:
                    return ((InternalEObject)eContainer).eInverseRemove(this, XmlModelPackage.ELEMENT__CONTENTS, Element.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlModelPackage.ELEMENT__PARENT:
                return getParent();
            case XmlModelPackage.ELEMENT__NAMESPACES:
                return getNamespaces();
            case XmlModelPackage.ELEMENT__NAMESPACE:
                if (resolve) return getNamespace();
                return basicGetNamespace();
            case XmlModelPackage.ELEMENT__NAME:
                return getName();
            case XmlModelPackage.ELEMENT__QNAME:
                return getQName();
            case XmlModelPackage.ELEMENT__UNAME:
                return getUName();
            case XmlModelPackage.ELEMENT__ATTRIBUTES:
                return getAttributes();
            case XmlModelPackage.ELEMENT__CONTENTS:
                return getContents();
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
            case XmlModelPackage.ELEMENT__PARENT:
                setParent((Element)newValue);
                return;
            case XmlModelPackage.ELEMENT__NAMESPACES:
                getNamespaces().clear();
                getNamespaces().addAll((Collection)newValue);
                return;
            case XmlModelPackage.ELEMENT__NAMESPACE:
                setNamespace((Namespace)newValue);
                return;
            case XmlModelPackage.ELEMENT__NAME:
                setName((String)newValue);
                return;
            case XmlModelPackage.ELEMENT__QNAME:
                setQName((String)newValue);
                return;
            case XmlModelPackage.ELEMENT__ATTRIBUTES:
                getAttributes().clear();
                getAttributes().addAll((Collection)newValue);
                return;
            case XmlModelPackage.ELEMENT__CONTENTS:
                getContents().clear();
                getContents().addAll((Collection)newValue);
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
            case XmlModelPackage.ELEMENT__PARENT:
                setParent((Element)null);
                return;
            case XmlModelPackage.ELEMENT__NAMESPACES:
                getNamespaces().clear();
                return;
            case XmlModelPackage.ELEMENT__NAMESPACE:
                setNamespace((Namespace)null);
                return;
            case XmlModelPackage.ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case XmlModelPackage.ELEMENT__QNAME:
                setQName(QNAME_EDEFAULT);
                return;
            case XmlModelPackage.ELEMENT__ATTRIBUTES:
                getAttributes().clear();
                return;
            case XmlModelPackage.ELEMENT__CONTENTS:
                getContents().clear();
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
            case XmlModelPackage.ELEMENT__PARENT:
                return getParent() != null;
            case XmlModelPackage.ELEMENT__NAMESPACES:
                return namespaces != null && !namespaces.isEmpty();
            case XmlModelPackage.ELEMENT__NAMESPACE:
                return namespace != null;
            case XmlModelPackage.ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case XmlModelPackage.ELEMENT__QNAME:
                return QNAME_EDEFAULT == null ? qName != null : !QNAME_EDEFAULT.equals(qName);
            case XmlModelPackage.ELEMENT__UNAME:
                return UNAME_EDEFAULT == null ? getUName() != null : !UNAME_EDEFAULT.equals(getUName());
            case XmlModelPackage.ELEMENT__ATTRIBUTES:
                return attributes != null && !attributes.isEmpty();
            case XmlModelPackage.ELEMENT__CONTENTS:
                return contents != null && !contents.isEmpty();
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

        StringBuffer result = new StringBuffer("<> ");  // super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", qName: ");
        result.append(qName);
        result.append(", uName: ");
        result.append(getUName());
        result.append(')');
        return result.toString();
	}

} //ElementImpl
