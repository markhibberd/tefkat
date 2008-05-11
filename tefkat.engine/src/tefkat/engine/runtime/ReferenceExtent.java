/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Extent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.ReferenceExtent#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getReferenceExtent()
 * @model
 * @generated
 */
public interface ReferenceExtent extends Extent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Resources</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.resource.Resource}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' attribute list.
     * @see tefkat.engine.runtime.RuntimePackage#getReferenceExtent_Resources()
     * @model type="org.eclipse.emf.ecore.resource.Resource"
     * @generated
     */
    EList getResources();

} // ReferenceExtent