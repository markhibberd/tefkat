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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tefkat.model.TefkatPackage
 * @generated
 */
public interface TefkatFactory extends EFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TefkatFactory eINSTANCE = new tefkat.model.impl.TefkatFactoryImpl();

    /**
     * Returns a new object of class '<em>Container Extent</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Container Extent</em>'.
     * @generated
     */
    ContainerExtent createContainerExtent();

    /**
     * Returns a new object of class '<em>Reference Extent</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Reference Extent</em>'.
     * @generated
     */
    ReferenceExtent createReferenceExtent();

    /**
     * Returns a new object of class '<em>Pattern Var</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Var</em>'.
     * @generated
     */
    PatternVar createPatternVar();

    /**
     * Returns a new object of class '<em>TRule Var</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TRule Var</em>'.
     * @generated
     */
    TRuleVar createTRuleVar();

    /**
     * Returns a new object of class '<em>Extent Var</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extent Var</em>'.
     * @generated
     */
    ExtentVar createExtentVar();

    /**
     * Returns a new object of class '<em>Var Scope</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Var Scope</em>'.
     * @generated
     */
    VarScope createVarScope();

    /**
     * Returns a new object of class '<em>TRule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TRule</em>'.
     * @generated
     */
    TRule createTRule();

    /**
     * Returns a new object of class '<em>Transformation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Transformation</em>'.
     * @generated
     */
    Transformation createTransformation();

    /**
     * Returns a new object of class '<em>Pattern Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Defn</em>'.
     * @generated
     */
    PatternDefn createPatternDefn();

    /**
     * Returns a new object of class '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Query</em>'.
     * @generated
     */
    Query createQuery();

    /**
     * Returns a new object of class '<em>Instance Ref</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Instance Ref</em>'.
     * @generated
     */
    InstanceRef createInstanceRef();

    /**
     * Returns a new object of class '<em>And Term</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>And Term</em>'.
     * @generated
     */
    AndTerm createAndTerm();

    /**
     * Returns a new object of class '<em>Or Term</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Or Term</em>'.
     * @generated
     */
    OrTerm createOrTerm();

    /**
     * Returns a new object of class '<em>Not Term</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Not Term</em>'.
     * @generated
     */
    NotTerm createNotTerm();

    /**
     * Returns a new object of class '<em>If Term</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>If Term</em>'.
     * @generated
     */
    IfTerm createIfTerm();

    /**
     * Returns a new object of class '<em>Tracking Use</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tracking Use</em>'.
     * @generated
     */
    TrackingUse createTrackingUse();

    /**
     * Returns a new object of class '<em>Pattern Use</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Use</em>'.
     * @generated
     */
    PatternUse createPatternUse();

    /**
     * Returns a new object of class '<em>Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Condition</em>'.
     * @generated
     */
    Condition createCondition();

    /**
     * Returns a new object of class '<em>Mof Instance</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Mof Instance</em>'.
     * @generated
     */
    MofInstance createMofInstance();

    /**
     * Returns a new object of class '<em>Mof Order</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Mof Order</em>'.
     * @generated
     */
    MofOrder createMofOrder();

    /**
     * Returns a new object of class '<em>Var Use</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Var Use</em>'.
     * @generated
     */
    VarUse createVarUse();

    /**
     * Returns a new object of class '<em>Collection Expr</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Collection Expr</em>'.
     * @generated
     */
    CollectionExpr createCollectionExpr();

    /**
     * Returns a new object of class '<em>Function Expr</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Expr</em>'.
     * @generated
     */
    FunctionExpr createFunctionExpr();

    /**
     * Returns a new object of class '<em>Feature Expr</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Feature Expr</em>'.
     * @generated
     */
    FeatureExpr createFeatureExpr();

    /**
     * Returns a new object of class '<em>String Constant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>String Constant</em>'.
     * @generated
     */
    StringConstant createStringConstant();

    /**
     * Returns a new object of class '<em>Int Constant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Int Constant</em>'.
     * @generated
     */
    IntConstant createIntConstant();

    /**
     * Returns a new object of class '<em>Real Constant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Real Constant</em>'.
     * @generated
     */
    RealConstant createRealConstant();

    /**
     * Returns a new object of class '<em>Boolean Constant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Boolean Constant</em>'.
     * @generated
     */
    BooleanConstant createBooleanConstant();

    /**
     * Returns a new object of class '<em>Enum Constant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Enum Constant</em>'.
     * @generated
     */
    EnumConstant createEnumConstant();

    /**
     * Returns a new object of class '<em>Injection</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Injection</em>'.
     * @generated
     */
    Injection createInjection();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TefkatPackage getTefkatPackage();

} //TefkatFactory
