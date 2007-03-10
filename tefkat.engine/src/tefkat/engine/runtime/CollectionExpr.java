/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.CollectionExpr#isUnique <em>Unique</em>}</li>
 *   <li>{@link tefkat.engine.runtime.CollectionExpr#isOrdered <em>Ordered</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getCollectionExpr()
 * @model
 * @generated
 */
public interface CollectionExpr extends CompoundExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Unique</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unique</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique</em>' attribute.
     * @see #setUnique(boolean)
     * @see tefkat.engine.runtime.RuntimePackage#getCollectionExpr_Unique()
     * @model required="true"
     * @generated
     */
    boolean isUnique();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.CollectionExpr#isUnique <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unique</em>' attribute.
     * @see #isUnique()
     * @generated
     */
    void setUnique(boolean value);

    /**
     * Returns the value of the '<em><b>Ordered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordered</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ordered</em>' attribute.
     * @see #setOrdered(boolean)
     * @see tefkat.engine.runtime.RuntimePackage#getCollectionExpr_Ordered()
     * @model required="true"
     * @generated
     */
    boolean isOrdered();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.CollectionExpr#isOrdered <em>Ordered</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ordered</em>' attribute.
     * @see #isOrdered()
     * @generated
     */
    void setOrdered(boolean value);

} // CollectionExpr