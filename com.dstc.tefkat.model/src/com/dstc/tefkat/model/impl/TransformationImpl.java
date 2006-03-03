/**
 * <copyright>
 * </copyright>
 *
 * $Id: TransformationImpl.java,v 1.5 2005/06/07 07:02:19 lawley Exp $
 */
package com.dstc.tefkat.model.impl;

import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.TefkatPackage;
import com.dstc.tefkat.model.Transformation;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.dstc.tefkat.model.impl.TransformationImpl#getTRule <em>TRule</em>}</li>
 *   <li>{@link com.dstc.tefkat.model.impl.TransformationImpl#getImportedPackages <em>Imported Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationImpl extends PatternScopeImpl implements Transformation {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

    /**
     * The cached value of the '{@link #getTRule() <em>TRule</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTRule()
     * @generated
     * @ordered
     */
    protected EList tRule = null;

    /**
     * The cached value of the '{@link #getImportedPackages() <em>Imported Packages</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImportedPackages()
     * @generated
     * @ordered
     */
    protected EList importedPackages = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TransformationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTransformation();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTRule() {
        if (tRule == null) {
            tRule = new EObjectContainmentWithInverseEList(TRule.class, this, TefkatPackage.TRANSFORMATION__TRULE, TefkatPackage.TRULE__TRANSFORMATION);
        }
        return tRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getImportedPackages() {
        if (importedPackages == null) {
            importedPackages = new EDataTypeUniqueEList(String.class, this, TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES);
        }
        return importedPackages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRANSFORMATION__VARS:
                    return ((InternalEList)getVars()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__TRULE:
                    return ((InternalEList)getTRule()).basicAdd(otherEnd, msgs);
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
                case TefkatPackage.TRANSFORMATION__VARS:
                    return ((InternalEList)getVars()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__TRULE:
                    return ((InternalEList)getTRule()).basicRemove(otherEnd, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRANSFORMATION__VARS:
                return getVars();
            case TefkatPackage.TRANSFORMATION__NAME:
                return getName();
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                return getComments();
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.TRANSFORMATION__TRULE:
                return getTRule();
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return getImportedPackages();
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
            case TefkatPackage.TRANSFORMATION__VARS:
                getVars().clear();
                getVars().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__NAME:
                setName((String)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                getComments().clear();
                getComments().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                getPatternDefn().clear();
                getPatternDefn().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                getTRule().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                getImportedPackages().addAll((Collection)newValue);
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
            case TefkatPackage.TRANSFORMATION__VARS:
                getVars().clear();
                return;
            case TefkatPackage.TRANSFORMATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                getComments().clear();
                return;
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                getPatternDefn().clear();
                return;
            case TefkatPackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                return;
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
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
            case TefkatPackage.TRANSFORMATION__VARS:
                return vars != null && !vars.isEmpty();
            case TefkatPackage.TRANSFORMATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                return comments != null && !comments.isEmpty();
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                return patternDefn != null && !patternDefn.isEmpty();
            case TefkatPackage.TRANSFORMATION__TRULE:
                return tRule != null && !tRule.isEmpty();
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return importedPackages != null && !importedPackages.isEmpty();
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
        
        String str = "";
        String sep = "";
        for (Iterator itr = getVars().iterator(); itr.hasNext(); ) {
            str = str + sep + itr.next();
            sep = ", ";
        }

        return name + "(" + str + ")";
    }

} //TransformationImpl
