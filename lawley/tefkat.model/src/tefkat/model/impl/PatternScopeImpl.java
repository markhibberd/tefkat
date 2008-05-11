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
package tefkat.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.NamespaceDeclaration;
import tefkat.model.PatternDefn;
import tefkat.model.PatternScope;
import tefkat.model.TefkatPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.PatternScopeImpl#getPatternDefn <em>Pattern Defn</em>}</li>
 *   <li>{@link tefkat.model.impl.PatternScopeImpl#getNamespaceDeclarations <em>Namespace Declarations</em>}</li>
 *   <li>{@link tefkat.model.impl.PatternScopeImpl#getImportedPackages <em>Imported Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PatternScopeImpl extends VarScopeImpl implements PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * The cached value of the '{@link #getPatternDefn() <em>Pattern Defn</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternDefn()
     * @generated
     * @ordered
     */
    protected EList patternDefn = null;

    /**
     * The cached value of the '{@link #getNamespaceDeclarations() <em>Namespace Declarations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespaceDeclarations()
     * @generated
     * @ordered
     */
    protected EList namespaceDeclarations = null;

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
    protected PatternScopeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.PATTERN_SCOPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPatternDefn() {
        if (patternDefn == null) {
            patternDefn = new EObjectContainmentWithInverseEList(PatternDefn.class, this, TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN, TefkatPackage.PATTERN_DEFN__PATTERN_SCOPE);
        }
        return patternDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNamespaceDeclarations() {
        if (namespaceDeclarations == null) {
            namespaceDeclarations = new EObjectContainmentEList(NamespaceDeclaration.class, this, TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS);
        }
        return namespaceDeclarations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getImportedPackages() {
        if (importedPackages == null) {
            importedPackages = new EDataTypeUniqueEList(String.class, this, TefkatPackage.PATTERN_SCOPE__IMPORTED_PACKAGES);
        }
        return importedPackages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return ((InternalEList)getPatternDefn()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return ((InternalEList)getPatternDefn()).basicRemove(otherEnd, msgs);
            case TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS:
                return ((InternalEList)getNamespaceDeclarations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS:
                return getNamespaceDeclarations();
            case TefkatPackage.PATTERN_SCOPE__IMPORTED_PACKAGES:
                return getImportedPackages();
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
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
                getPatternDefn().addAll((Collection)newValue);
                return;
            case TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS:
                getNamespaceDeclarations().clear();
                getNamespaceDeclarations().addAll((Collection)newValue);
                return;
            case TefkatPackage.PATTERN_SCOPE__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                getImportedPackages().addAll((Collection)newValue);
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
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                getPatternDefn().clear();
                return;
            case TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS:
                getNamespaceDeclarations().clear();
                return;
            case TefkatPackage.PATTERN_SCOPE__IMPORTED_PACKAGES:
                getImportedPackages().clear();
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
            case TefkatPackage.PATTERN_SCOPE__PATTERN_DEFN:
                return patternDefn != null && !patternDefn.isEmpty();
            case TefkatPackage.PATTERN_SCOPE__NAMESPACE_DECLARATIONS:
                return namespaceDeclarations != null && !namespaceDeclarations.isEmpty();
            case TefkatPackage.PATTERN_SCOPE__IMPORTED_PACKAGES:
                return importedPackages != null && !importedPackages.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (importedPackages: ");
        result.append(importedPackages);
        result.append(')');
        return result.toString();
    }

} //PatternScopeImpl
