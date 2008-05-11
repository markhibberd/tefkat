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
 * A representation of the model object '<em><b>Compound Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.CompoundExpr#getArg <em>Arg</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getCompoundExpr()
 * @model abstract="true"
 * @generated
 */
public interface CompoundExpr extends Expression {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Arg</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.Expression}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Expression#getExpr <em>Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Arg</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getCompoundExpr_Arg()
     * @see tefkat.engine.runtime.Expression#getExpr
     * @model type="tefkat.engine.runtime.Expression" opposite="expr" containment="true"
     * @generated
     */
    EList getArg();

} // CompoundExpr