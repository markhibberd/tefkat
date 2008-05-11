/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.Transformation#getTRule <em>TRule</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Transformation#getImportedPackages <em>Imported Packages</em>}</li>
 *   <li>{@link tefkat.engine.runtime.Transformation#getNamespaceDeclarations <em>Namespace Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends PatternScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>TRule</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.TRule}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.TRule#getTransformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>TRule</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getTransformation_TRule()
     * @see tefkat.engine.runtime.TRule#getTransformation
     * @model type="tefkat.engine.runtime.TRule" opposite="transformation" containment="true"
     * @generated
     */
    EList getTRule();

    /**
     * Returns the value of the '<em><b>Imported Packages</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imported Packages</em>' attribute list.
     * @see tefkat.engine.runtime.RuntimePackage#getTransformation_ImportedPackages()
     * @model type="java.lang.String"
     * @generated
     */
    EList getImportedPackages();

    /**
     * Returns the value of the '<em><b>Namespace Declarations</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.NamespaceDeclaration}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace Declarations</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getTransformation_NamespaceDeclarations()
     * @model type="tefkat.engine.runtime.NamespaceDeclaration" containment="true"
     * @generated
     */
    EList getNamespaceDeclarations();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.engine.runtime.ListArray" exceptions="tefkat.engine.runtime.TefkatException"
     * @generated
     */
    List[] getStrata() throws TefkatException;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model supersederRequired="true" supersededRequired="true"
     * @generated
     */
    void addSupersedes(TRule superseder, TRule superseded);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model supersederRequired="true" supersededRequired="true"
     * @generated
     */
    void removeSupersedes(TRule superseder, TRule superseded);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model dataType="tefkat.engine.runtime.Collection" required="true" supersededRequired="true"
     * @generated
     */
    Collection getSupersedingRules(TRule superseded);

} // Transformation