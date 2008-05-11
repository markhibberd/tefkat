/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TRule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.TRule#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.engine.runtime.TRule#getSrc <em>Src</em>}</li>
 *   <li>{@link tefkat.engine.runtime.TRule#getTgt <em>Tgt</em>}</li>
 *   <li>{@link tefkat.engine.runtime.TRule#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.engine.runtime.TRule#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.engine.runtime.TRule#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.engine.runtime.RuntimePackage#getTRule()
 * @model
 * @generated
 */
public interface TRule extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * Returns the value of the '<em><b>Transformation</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.Transformation#getTRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation</em>' container reference.
     * @see #setTransformation(Transformation)
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Transformation()
     * @see tefkat.engine.runtime.Transformation#getTRule
     * @model opposite="tRule" required="true"
     * @generated
     */
    Transformation getTransformation();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.TRule#getTransformation <em>Transformation</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transformation</em>' container reference.
     * @see #getTransformation()
     * @generated
     */
    void setTransformation(Transformation value);

    /**
     * Returns the value of the '<em><b>Src</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.SourceTerm#getTRuleSrc <em>TRule Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Src</em>' containment reference.
     * @see #setSrc(SourceTerm)
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Src()
     * @see tefkat.engine.runtime.SourceTerm#getTRuleSrc
     * @model opposite="tRuleSrc" containment="true"
     * @generated
     */
    SourceTerm getSrc();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.TRule#getSrc <em>Src</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Src</em>' containment reference.
     * @see #getSrc()
     * @generated
     */
    void setSrc(SourceTerm value);

    /**
     * Returns the value of the '<em><b>Tgt</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.engine.runtime.TargetTerm}.
     * It is bidirectional and its opposite is '{@link tefkat.engine.runtime.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tgt</em>' containment reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Tgt()
     * @see tefkat.engine.runtime.TargetTerm#getTRuleTgt
     * @model type="tefkat.engine.runtime.TargetTerm" opposite="tRuleTgt" containment="true"
     * @generated
     */
    EList getTgt();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.TRule}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Extended()
     * @model type="tefkat.engine.runtime.TRule"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.engine.runtime.TRule}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Superseded()
     * @model type="tefkat.engine.runtime.TRule"
     * @generated
     */
    EList getSuperseded();

    /**
     * Returns the value of the '<em><b>Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Abstract</em>' attribute.
     * @see #setAbstract(boolean)
     * @see tefkat.engine.runtime.RuntimePackage#getTRule_Abstract()
     * @model
     * @generated
     */
    boolean isAbstract();

    /**
     * Sets the value of the '{@link tefkat.engine.runtime.TRule#isAbstract <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract</em>' attribute.
     * @see #isAbstract()
     * @generated
     */
    void setAbstract(boolean value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.engine.runtime.Collection"
     * @generated
     */
    Collection getGoal();

} // TRule