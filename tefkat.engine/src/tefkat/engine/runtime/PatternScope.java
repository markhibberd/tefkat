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
 * A representation of the model object '<em><b>Pattern Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.PatternScope#getPatternDefn <em>Pattern Defn</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getPatternScope()
 * @model abstract="true"
 * @generated
 */
public interface PatternScope extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Pattern Defn</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.PatternDefn}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.PatternDefn#getPatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Defn</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getPatternScope_PatternDefn()
     * @see tefkat.engine.runtime.PatternDefn#getPatternScope
     * @model type="tefkat.engine.runtime.PatternDefn" opposite="patternScope" containment="true"
     * @generated
     */
    EList getPatternDefn();

} // PatternScope