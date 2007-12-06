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
package tefkat.model;

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
 *   <li>{@link tefkat.model.TRule#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.model.TRule#getSrc <em>Src</em>}</li>
 *   <li>{@link tefkat.model.TRule#getTgt <em>Tgt</em>}</li>
 *   <li>{@link tefkat.model.TRule#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.model.TRule#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.model.TRule#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @see tefkat.model.TefkatPackage#getTRule()
 * @model
 * @generated
 */
public interface TRule extends VarScope {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * Returns the value of the '<em><b>Transformation</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.Transformation#getTRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation</em>' container reference.
     * @see #setTransformation(Transformation)
     * @see tefkat.model.TefkatPackage#getTRule_Transformation()
     * @see tefkat.model.Transformation#getTRule
     * @model opposite="tRule" required="true"
     * @generated
     */
    Transformation getTransformation();

    /**
     * Sets the value of the '{@link tefkat.model.TRule#getTransformation <em>Transformation</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transformation</em>' container reference.
     * @see #getTransformation()
     * @generated
     */
    void setTransformation(Transformation value);

    /**
     * Returns the value of the '<em><b>Src</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link tefkat.model.SourceTerm#getTRuleSrc <em>TRule Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Src</em>' containment reference.
     * @see #setSrc(SourceTerm)
     * @see tefkat.model.TefkatPackage#getTRule_Src()
     * @see tefkat.model.SourceTerm#getTRuleSrc
     * @model opposite="tRuleSrc" containment="true"
     * @generated
     */
    SourceTerm getSrc();

    /**
     * Sets the value of the '{@link tefkat.model.TRule#getSrc <em>Src</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Src</em>' containment reference.
     * @see #getSrc()
     * @generated
     */
    void setSrc(SourceTerm value);

    /**
     * Returns the value of the '<em><b>Tgt</b></em>' containment reference list.
     * The list contents are of type {@link tefkat.model.TargetTerm}.
     * It is bidirectional and its opposite is '{@link tefkat.model.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tgt</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tgt</em>' containment reference list.
     * @see tefkat.model.TefkatPackage#getTRule_Tgt()
     * @see tefkat.model.TargetTerm#getTRuleTgt
     * @model type="tefkat.model.TargetTerm" opposite="tRuleTgt" containment="true"
     * @generated
     */
    EList getTgt();

    /**
     * Returns the value of the '<em><b>Extended</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extended</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRule_Extended()
     * @model type="tefkat.model.TRule"
     * @generated
     */
    EList getExtended();

    /**
     * Returns the value of the '<em><b>Superseded</b></em>' reference list.
     * The list contents are of type {@link tefkat.model.TRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Superseded</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Superseded</em>' reference list.
     * @see tefkat.model.TefkatPackage#getTRule_Superseded()
     * @model type="tefkat.model.TRule"
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
     * @see tefkat.model.TefkatPackage#getTRule_Abstract()
     * @model
     * @generated
     */
    boolean isAbstract();

    /**
     * Sets the value of the '{@link tefkat.model.TRule#isAbstract <em>Abstract</em>}' attribute.
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
     * @model kind="operation" dataType="tefkat.model.Collection" required="true"
     * @generated
     */
    Collection getGoal();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.model.Collection" required="true"
     * @generated
     */
    Collection getSourceTerms();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.model.Collection" required="true"
     * @generated
     */
    Collection getOverrideTerms();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="tefkat.model.Collection" required="true"
     * @generated
     */
    Collection getTargetTerms();

} // TRule
