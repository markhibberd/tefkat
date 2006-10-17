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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tefkat.model.TefkatFactory
 * @model kind="package"
 * @generated
 */
public interface TefkatPackage extends EPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "model";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///TefkatModel-2.5.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "tefkat";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TefkatPackage eINSTANCE = tefkat.model.impl.TefkatPackageImpl.init();

    /**
     * The meta object id for the '{@link tefkat.model.impl.ExtentImpl <em>Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.ExtentImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getExtent()
     * @generated
     */
    int EXTENT = 0;

    /**
     * The number of structural features of the '<em>Extent</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link tefkat.model.impl.ContainerExtentImpl <em>Container Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.ContainerExtentImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getContainerExtent()
     * @generated
     */
    int CONTAINER_EXTENT = 1;

    /**
     * The feature id for the '<em><b>Resource</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_EXTENT__RESOURCE = EXTENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Container Extent</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_EXTENT_FEATURE_COUNT = EXTENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link tefkat.model.impl.ReferenceExtentImpl <em>Reference Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.ReferenceExtentImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getReferenceExtent()
     * @generated
     */
    int REFERENCE_EXTENT = 2;

    /**
     * The feature id for the '<em><b>Resources</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_EXTENT__RESOURCES = EXTENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Reference Extent</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_EXTENT_FEATURE_COUNT = EXTENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link tefkat.model.impl.VarImpl <em>Var</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.VarImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getVar()
     * @generated
     */
    int VAR = 3;

    /**
     * The feature id for the '<em><b>Scope</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__SCOPE = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__NAME = 1;

    /**
     * The feature id for the '<em><b>Usages</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__USAGES = 2;

    /**
     * The feature id for the '<em><b>Superseded</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__SUPERSEDED = 3;

    /**
     * The feature id for the '<em><b>Superseder</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__SUPERSEDER = 4;

    /**
     * The feature id for the '<em><b>Extended</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__EXTENDED = 5;

    /**
     * The feature id for the '<em><b>Extender</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR__EXTENDER = 6;

    /**
     * The number of structural features of the '<em>Var</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link tefkat.model.impl.VarScopeImpl <em>Var Scope</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.VarScopeImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getVarScope()
     * @generated
     */
    int VAR_SCOPE = 4;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_SCOPE__VARS = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_SCOPE__NAME = 1;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_SCOPE__COMMENTS = 2;

    /**
     * The number of structural features of the '<em>Var Scope</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_SCOPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link tefkat.model.impl.PatternScopeImpl <em>Pattern Scope</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.PatternScopeImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getPatternScope()
     * @generated
     */
    int PATTERN_SCOPE = 5;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_SCOPE__VARS = VAR_SCOPE__VARS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_SCOPE__NAME = VAR_SCOPE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_SCOPE__COMMENTS = VAR_SCOPE__COMMENTS;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_SCOPE__PATTERN_DEFN = VAR_SCOPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Pattern Scope</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_SCOPE_FEATURE_COUNT = VAR_SCOPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link tefkat.model.impl.TRuleImpl <em>TRule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TRuleImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getTRule()
     * @generated
     */
    int TRULE = 6;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__VARS = VAR_SCOPE__VARS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__NAME = VAR_SCOPE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__COMMENTS = VAR_SCOPE__COMMENTS;

    /**
     * The feature id for the '<em><b>Transformation</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__TRANSFORMATION = VAR_SCOPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Src</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__SRC = VAR_SCOPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tgt</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__TGT = VAR_SCOPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Extended</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__EXTENDED = VAR_SCOPE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Superseded</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__SUPERSEDED = VAR_SCOPE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE__ABSTRACT = VAR_SCOPE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>TRule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRULE_FEATURE_COUNT = VAR_SCOPE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link tefkat.model.impl.TransformationImpl <em>Transformation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TransformationImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getTransformation()
     * @generated
     */
    int TRANSFORMATION = 7;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__VARS = PATTERN_SCOPE__VARS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__NAME = PATTERN_SCOPE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__COMMENTS = PATTERN_SCOPE__COMMENTS;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__PATTERN_DEFN = PATTERN_SCOPE__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>TRule</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__TRULE = PATTERN_SCOPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Imported Packages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__IMPORTED_PACKAGES = PATTERN_SCOPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Namespace Declarations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION__NAMESPACE_DECLARATIONS = PATTERN_SCOPE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Transformation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSFORMATION_FEATURE_COUNT = PATTERN_SCOPE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link tefkat.model.impl.PatternDefnImpl <em>Pattern Defn</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.PatternDefnImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getPatternDefn()
     * @generated
     */
    int PATTERN_DEFN = 9;

    /**
     * The meta object id for the '{@link tefkat.model.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.QueryImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getQuery()
     * @generated
     */
    int QUERY = 10;

    /**
     * The meta object id for the '{@link tefkat.model.impl.TermImpl <em>Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getTerm()
     * @generated
     */
    int TERM = 11;

    /**
     * The meta object id for the '{@link tefkat.model.impl.SourceTermImpl <em>Source Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.SourceTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getSourceTerm()
     * @generated
     */
    int SOURCE_TERM = 12;

    /**
     * The meta object id for the '{@link tefkat.model.impl.TargetTermImpl <em>Target Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TargetTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getTargetTerm()
     * @generated
     */
    int TARGET_TERM = 13;

    /**
     * The meta object id for the '{@link tefkat.model.impl.CompoundTermImpl <em>Compound Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.CompoundTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getCompoundTerm()
     * @generated
     */
    int COMPOUND_TERM = 14;

    /**
     * The meta object id for the '{@link tefkat.model.impl.SimpleTermImpl <em>Simple Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.SimpleTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getSimpleTerm()
     * @generated
     */
    int SIMPLE_TERM = 15;

    /**
     * The meta object id for the '{@link tefkat.model.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.ExpressionImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getExpression()
     * @generated
     */
    int EXPRESSION = 16;

    /**
     * The meta object id for the '{@link tefkat.model.impl.InstanceRefImpl <em>Instance Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.InstanceRefImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getInstanceRef()
     * @generated
     */
    int INSTANCE_REF = 17;

    /**
     * The meta object id for the '{@link tefkat.model.impl.AndTermImpl <em>And Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.AndTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getAndTerm()
     * @generated
     */
    int AND_TERM = 18;

    /**
     * The meta object id for the '{@link tefkat.model.impl.OrTermImpl <em>Or Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.OrTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getOrTerm()
     * @generated
     */
    int OR_TERM = 19;

    /**
     * The meta object id for the '{@link tefkat.model.impl.NotTermImpl <em>Not Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.NotTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getNotTerm()
     * @generated
     */
    int NOT_TERM = 20;

    /**
     * The meta object id for the '{@link tefkat.model.impl.IfTermImpl <em>If Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.IfTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getIfTerm()
     * @generated
     */
    int IF_TERM = 21;

    /**
     * The meta object id for the '{@link tefkat.model.impl.TrackingUseImpl <em>Tracking Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TrackingUseImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getTrackingUse()
     * @generated
     */
    int TRACKING_USE = 22;

    /**
     * The meta object id for the '{@link tefkat.model.impl.FeatureValuePairImpl <em>Feature Value Pair</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.FeatureValuePairImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getFeatureValuePair()
     * @generated
     */
    int FEATURE_VALUE_PAIR = 23;

    /**
     * The meta object id for the '{@link tefkat.model.impl.PatternUseImpl <em>Pattern Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.PatternUseImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getPatternUse()
     * @generated
     */
    int PATTERN_USE = 24;

    /**
     * The meta object id for the '{@link tefkat.model.impl.MofTermImpl <em>Mof Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.MofTermImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getMofTerm()
     * @generated
     */
    int MOF_TERM = 25;

    /**
     * The meta object id for the '{@link tefkat.model.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.ConditionImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getCondition()
     * @generated
     */
    int CONDITION = 26;

    /**
     * The meta object id for the '{@link tefkat.model.impl.MofInstanceImpl <em>Mof Instance</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.MofInstanceImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getMofInstance()
     * @generated
     */
    int MOF_INSTANCE = 27;

    /**
     * The meta object id for the '{@link tefkat.model.impl.MofOrderImpl <em>Mof Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.MofOrderImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getMofOrder()
     * @generated
     */
    int MOF_ORDER = 28;

    /**
     * The meta object id for the '{@link tefkat.model.impl.CompoundExprImpl <em>Compound Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.CompoundExprImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getCompoundExpr()
     * @generated
     */
    int COMPOUND_EXPR = 29;

    /**
     * The meta object id for the '{@link tefkat.model.impl.SimpleExprImpl <em>Simple Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.SimpleExprImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getSimpleExpr()
     * @generated
     */
    int SIMPLE_EXPR = 30;

    /**
     * The meta object id for the '{@link tefkat.model.impl.VarUseImpl <em>Var Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.VarUseImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getVarUse()
     * @generated
     */
    int VAR_USE = 31;

    /**
     * The meta object id for the '{@link tefkat.model.impl.CollectionExprImpl <em>Collection Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.CollectionExprImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getCollectionExpr()
     * @generated
     */
    int COLLECTION_EXPR = 32;

    /**
     * The meta object id for the '{@link tefkat.model.impl.FunctionExprImpl <em>Function Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.FunctionExprImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getFunctionExpr()
     * @generated
     */
    int FUNCTION_EXPR = 33;

    /**
     * The meta object id for the '{@link tefkat.model.impl.FeatureExprImpl <em>Feature Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.FeatureExprImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getFeatureExpr()
     * @generated
     */
    int FEATURE_EXPR = 34;

    /**
     * The meta object id for the '{@link tefkat.model.impl.StringConstantImpl <em>String Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.StringConstantImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getStringConstant()
     * @generated
     */
    int STRING_CONSTANT = 35;

    /**
     * The meta object id for the '{@link tefkat.model.impl.IntConstantImpl <em>Int Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.IntConstantImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getIntConstant()
     * @generated
     */
    int INT_CONSTANT = 36;

    /**
     * The meta object id for the '{@link tefkat.model.impl.RealConstantImpl <em>Real Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.RealConstantImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getRealConstant()
     * @generated
     */
    int REAL_CONSTANT = 37;

    /**
     * The meta object id for the '{@link tefkat.model.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.BooleanConstantImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getBooleanConstant()
     * @generated
     */
    int BOOLEAN_CONSTANT = 38;

    /**
     * The meta object id for the '{@link tefkat.model.impl.EnumConstantImpl <em>Enum Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.EnumConstantImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getEnumConstant()
     * @generated
     */
    int ENUM_CONSTANT = 39;

    /**
     * The meta object id for the '{@link tefkat.model.impl.InjectionImpl <em>Injection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.InjectionImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getInjection()
     * @generated
     */
    int INJECTION = 40;

    /**
     * The meta object id for the '{@link tefkat.model.impl.NamespaceDeclarationImpl <em>Namespace Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.NamespaceDeclarationImpl
     * @see tefkat.model.impl.TefkatPackageImpl#getNamespaceDeclaration()
     * @generated
     */
    int NAMESPACE_DECLARATION = 8;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMESPACE_DECLARATION__PREFIX = 0;

    /**
     * The feature id for the '<em><b>URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMESPACE_DECLARATION__URI = 1;

    /**
     * The number of structural features of the '<em>Namespace Declaration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMESPACE_DECLARATION_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__VARS = VAR_SCOPE__VARS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__NAME = VAR_SCOPE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__COMMENTS = VAR_SCOPE__COMMENTS;

    /**
     * The feature id for the '<em><b>Pattern Scope</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__PATTERN_SCOPE = VAR_SCOPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__TERM = VAR_SCOPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parameter Var</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__PARAMETER_VAR = VAR_SCOPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN__SOURCE = VAR_SCOPE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Pattern Defn</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_DEFN_FEATURE_COUNT = VAR_SCOPE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Vars</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__VARS = PATTERN_SCOPE__VARS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__NAME = PATTERN_SCOPE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__COMMENTS = PATTERN_SCOPE__COMMENTS;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__PATTERN_DEFN = PATTERN_SCOPE__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__TERM = PATTERN_SCOPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Parameter Var</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__PARAMETER_VAR = PATTERN_SCOPE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_FEATURE_COUNT = PATTERN_SCOPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TERM__PATTERN_DEFN = 0;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TERM__QUERY = 1;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TERM__COMPOUND_TERM = 2;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TERM__CONTEXT = 3;

    /**
     * The number of structural features of the '<em>Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TERM_FEATURE_COUNT = 4;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM__PATTERN_DEFN = TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM__QUERY = TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM__COMPOUND_TERM = TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM__CONTEXT = TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM__TRULE_SRC = TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Source Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TERM_FEATURE_COUNT = TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM__PATTERN_DEFN = TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM__QUERY = TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM__COMPOUND_TERM = TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM__CONTEXT = TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM__TRULE_TGT = TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Target Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_TERM_FEATURE_COUNT = TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__PATTERN_DEFN = SOURCE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__QUERY = SOURCE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__COMPOUND_TERM = SOURCE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__CONTEXT = SOURCE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__TRULE_SRC = SOURCE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM__TERM = SOURCE_TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Compound Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_TERM_FEATURE_COUNT = SOURCE_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__PATTERN_DEFN = SOURCE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__QUERY = SOURCE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__COMPOUND_TERM = SOURCE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__CONTEXT = SOURCE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__TRULE_SRC = SOURCE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM__TRULE_TGT = SOURCE_TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Simple Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TERM_FEATURE_COUNT = SOURCE_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__EXPR = 0;

    /**
     * The number of structural features of the '<em>Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANCE_REF__EXPR = EXPRESSION__EXPR;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANCE_REF__OBJECT = EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Instance Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANCE_REF_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__PATTERN_DEFN = COMPOUND_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__QUERY = COMPOUND_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__COMPOUND_TERM = COMPOUND_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__CONTEXT = COMPOUND_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__TRULE_SRC = COMPOUND_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__TERM = COMPOUND_TERM__TERM;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM__TRULE_TGT = COMPOUND_TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>And Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_TERM_FEATURE_COUNT = COMPOUND_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__PATTERN_DEFN = COMPOUND_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__QUERY = COMPOUND_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__COMPOUND_TERM = COMPOUND_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__CONTEXT = COMPOUND_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__TRULE_SRC = COMPOUND_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM__TERM = COMPOUND_TERM__TERM;

    /**
     * The number of structural features of the '<em>Or Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_TERM_FEATURE_COUNT = COMPOUND_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__PATTERN_DEFN = COMPOUND_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__QUERY = COMPOUND_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__COMPOUND_TERM = COMPOUND_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__CONTEXT = COMPOUND_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__TRULE_SRC = COMPOUND_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM__TERM = COMPOUND_TERM__TERM;

    /**
     * The number of structural features of the '<em>Not Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOT_TERM_FEATURE_COUNT = COMPOUND_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__PATTERN_DEFN = COMPOUND_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__QUERY = COMPOUND_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__COMPOUND_TERM = COMPOUND_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__CONTEXT = COMPOUND_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__TRULE_SRC = COMPOUND_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>Term</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__TERM = COMPOUND_TERM__TERM;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM__TRULE_TGT = COMPOUND_TERM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>If Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IF_TERM_FEATURE_COUNT = COMPOUND_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__PATTERN_DEFN = SIMPLE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__QUERY = SIMPLE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__COMPOUND_TERM = SIMPLE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__CONTEXT = SIMPLE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__TRULE_SRC = SIMPLE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__TRULE_TGT = SIMPLE_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Tracking</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__TRACKING = SIMPLE_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Features</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__FEATURES = SIMPLE_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tracking Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE__TRACKING_NAME = SIMPLE_TERM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Tracking Use</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACKING_USE_FEATURE_COUNT = SIMPLE_TERM_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_VALUE_PAIR__VALUE = 0;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_VALUE_PAIR__KEY = 1;

    /**
     * The number of structural features of the '<em>Feature Value Pair</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_VALUE_PAIR_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__PATTERN_DEFN = SIMPLE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__QUERY = SIMPLE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__COMPOUND_TERM = SIMPLE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__CONTEXT = SIMPLE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__TRULE_SRC = SIMPLE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__TRULE_TGT = SIMPLE_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Defn</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__DEFN = SIMPLE_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE__ARG = SIMPLE_TERM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Pattern Use</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATTERN_USE_FEATURE_COUNT = SIMPLE_TERM_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__PATTERN_DEFN = SIMPLE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__QUERY = SIMPLE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__COMPOUND_TERM = SIMPLE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__CONTEXT = SIMPLE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__TRULE_SRC = SIMPLE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM__TRULE_TGT = SIMPLE_TERM__TRULE_TGT;

    /**
     * The number of structural features of the '<em>Mof Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_TERM_FEATURE_COUNT = SIMPLE_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__PATTERN_DEFN = SIMPLE_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__QUERY = SIMPLE_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__COMPOUND_TERM = SIMPLE_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__CONTEXT = SIMPLE_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__TRULE_SRC = SIMPLE_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__TRULE_TGT = SIMPLE_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__ARG = SIMPLE_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Relation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__RELATION = SIMPLE_TERM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION_FEATURE_COUNT = SIMPLE_TERM_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__PATTERN_DEFN = MOF_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__QUERY = MOF_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__COMPOUND_TERM = MOF_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__CONTEXT = MOF_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__TRULE_SRC = MOF_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__TRULE_TGT = MOF_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__TYPE_NAME = MOF_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Instance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__INSTANCE = MOF_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Exact</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE__EXACT = MOF_TERM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Mof Instance</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_INSTANCE_FEATURE_COUNT = MOF_TERM_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__PATTERN_DEFN = MOF_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__QUERY = MOF_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__COMPOUND_TERM = MOF_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__CONTEXT = MOF_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Src</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__TRULE_SRC = MOF_TERM__TRULE_SRC;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__TRULE_TGT = MOF_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Lesser</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__LESSER = MOF_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Greater</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__GREATER = MOF_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Instance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__INSTANCE = MOF_TERM_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Feature</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER__FEATURE = MOF_TERM_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Mof Order</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MOF_ORDER_FEATURE_COUNT = MOF_TERM_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_EXPR__EXPR = EXPRESSION__EXPR;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_EXPR__ARG = EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Compound Expr</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_EXPR__EXPR = EXPRESSION__EXPR;

    /**
     * The feature id for the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_EXPR__REPRESENTATION = EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Simple Expr</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_USE__EXPR = EXPRESSION__EXPR;

    /**
     * The feature id for the '<em><b>Var</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_USE__VAR = EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Var Use</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VAR_USE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLLECTION_EXPR__EXPR = COMPOUND_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLLECTION_EXPR__ARG = COMPOUND_EXPR__ARG;

    /**
     * The feature id for the '<em><b>Unique</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLLECTION_EXPR__UNIQUE = COMPOUND_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Ordered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLLECTION_EXPR__ORDERED = COMPOUND_EXPR_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Collection Expr</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLLECTION_EXPR_FEATURE_COUNT = COMPOUND_EXPR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FUNCTION_EXPR__EXPR = COMPOUND_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FUNCTION_EXPR__ARG = COMPOUND_EXPR__ARG;

    /**
     * The feature id for the '<em><b>Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FUNCTION_EXPR__FUNCTION = COMPOUND_EXPR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Function Expr</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FUNCTION_EXPR_FEATURE_COUNT = COMPOUND_EXPR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR__EXPR = COMPOUND_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR__ARG = COMPOUND_EXPR__ARG;

    /**
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR__OPERATION = COMPOUND_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Collect</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR__COLLECT = COMPOUND_EXPR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Feature</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR__FEATURE = COMPOUND_EXPR_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Feature Expr</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FEATURE_EXPR_FEATURE_COUNT = COMPOUND_EXPR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_CONSTANT__EXPR = SIMPLE_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_CONSTANT__REPRESENTATION = SIMPLE_EXPR__REPRESENTATION;

    /**
     * The number of structural features of the '<em>String Constant</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_CONSTANT_FEATURE_COUNT = SIMPLE_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_CONSTANT__EXPR = SIMPLE_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_CONSTANT__REPRESENTATION = SIMPLE_EXPR__REPRESENTATION;

    /**
     * The number of structural features of the '<em>Int Constant</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_CONSTANT_FEATURE_COUNT = SIMPLE_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REAL_CONSTANT__EXPR = SIMPLE_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REAL_CONSTANT__REPRESENTATION = SIMPLE_EXPR__REPRESENTATION;

    /**
     * The number of structural features of the '<em>Real Constant</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REAL_CONSTANT_FEATURE_COUNT = SIMPLE_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_CONSTANT__EXPR = SIMPLE_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Representation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_CONSTANT__REPRESENTATION = SIMPLE_EXPR__REPRESENTATION;

    /**
     * The number of structural features of the '<em>Boolean Constant</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_CONSTANT_FEATURE_COUNT = SIMPLE_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CONSTANT__EXPR = COMPOUND_EXPR__EXPR;

    /**
     * The feature id for the '<em><b>Arg</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CONSTANT__ARG = COMPOUND_EXPR__ARG;

    /**
     * The number of structural features of the '<em>Enum Constant</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CONSTANT_FEATURE_COUNT = COMPOUND_EXPR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern Defn</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__PATTERN_DEFN = TARGET_TERM__PATTERN_DEFN;

    /**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__QUERY = TARGET_TERM__QUERY;

    /**
     * The feature id for the '<em><b>Compound Term</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__COMPOUND_TERM = TARGET_TERM__COMPOUND_TERM;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__CONTEXT = TARGET_TERM__CONTEXT;

    /**
     * The feature id for the '<em><b>TRule Tgt</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__TRULE_TGT = TARGET_TERM__TRULE_TGT;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__NAME = TARGET_TERM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sources</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__SOURCES = TARGET_TERM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION__TARGET = TARGET_TERM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Injection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INJECTION_FEATURE_COUNT = TARGET_TERM_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '<em>Collection</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Collection
     * @see tefkat.model.impl.TefkatPackageImpl#getCollection()
     * @generated
     */
    int COLLECTION = 41;

    /**
     * The meta object id for the '<em>List</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see tefkat.model.impl.TefkatPackageImpl#getList()
     * @generated
     */
    int LIST = 42;

    /**
     * The meta object id for the '<em>List Array</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.impl.TefkatPackageImpl#getListArray()
     * @generated
     */
    int LIST_ARRAY = 43;

    /**
     * The meta object id for the '<em>Exception</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.model.TefkatException
     * @see tefkat.model.impl.TefkatPackageImpl#getTefkatException()
     * @generated
     */
    int TEFKAT_EXCEPTION = 44;


    /**
     * Returns the meta object for class '{@link tefkat.model.Extent <em>Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Extent</em>'.
     * @see tefkat.model.Extent
     * @generated
     */
    EClass getExtent();

    /**
     * Returns the meta object for class '{@link tefkat.model.ContainerExtent <em>Container Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Container Extent</em>'.
     * @see tefkat.model.ContainerExtent
     * @generated
     */
    EClass getContainerExtent();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.ContainerExtent#getResource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resource</em>'.
     * @see tefkat.model.ContainerExtent#getResource()
     * @see #getContainerExtent()
     * @generated
     */
    EAttribute getContainerExtent_Resource();

    /**
     * Returns the meta object for class '{@link tefkat.model.ReferenceExtent <em>Reference Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reference Extent</em>'.
     * @see tefkat.model.ReferenceExtent
     * @generated
     */
    EClass getReferenceExtent();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.model.ReferenceExtent#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Resources</em>'.
     * @see tefkat.model.ReferenceExtent#getResources()
     * @see #getReferenceExtent()
     * @generated
     */
    EAttribute getReferenceExtent_Resources();

    /**
     * Returns the meta object for class '{@link tefkat.model.Var <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var</em>'.
     * @see tefkat.model.Var
     * @generated
     */
    EClass getVar();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.Var#getScope <em>Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Scope</em>'.
     * @see tefkat.model.Var#getScope()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Scope();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.Var#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.model.Var#getName()
     * @see #getVar()
     * @generated
     */
    EAttribute getVar_Name();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Var#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Usages</em>'.
     * @see tefkat.model.Var#getUsages()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Usages();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Var#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseded</em>'.
     * @see tefkat.model.Var#getSuperseded()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Superseded();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Var#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseder</em>'.
     * @see tefkat.model.Var#getSuperseder()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Superseder();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Var#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extended</em>'.
     * @see tefkat.model.Var#getExtended()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Extended();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Var#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extender</em>'.
     * @see tefkat.model.Var#getExtender()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Extender();

    /**
     * Returns the meta object for class '{@link tefkat.model.VarScope <em>Var Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var Scope</em>'.
     * @see tefkat.model.VarScope
     * @generated
     */
    EClass getVarScope();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.VarScope#getVars <em>Vars</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Vars</em>'.
     * @see tefkat.model.VarScope#getVars()
     * @see #getVarScope()
     * @generated
     */
    EReference getVarScope_Vars();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.VarScope#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.model.VarScope#getName()
     * @see #getVarScope()
     * @generated
     */
    EAttribute getVarScope_Name();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.model.VarScope#getComments <em>Comments</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Comments</em>'.
     * @see tefkat.model.VarScope#getComments()
     * @see #getVarScope()
     * @generated
     */
    EAttribute getVarScope_Comments();

    /**
     * Returns the meta object for class '{@link tefkat.model.PatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Scope</em>'.
     * @see tefkat.model.PatternScope
     * @generated
     */
    EClass getPatternScope();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.PatternScope#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pattern Defn</em>'.
     * @see tefkat.model.PatternScope#getPatternDefn()
     * @see #getPatternScope()
     * @generated
     */
    EReference getPatternScope_PatternDefn();

    /**
     * Returns the meta object for class '{@link tefkat.model.TRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>TRule</em>'.
     * @see tefkat.model.TRule
     * @generated
     */
    EClass getTRule();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.TRule#getTransformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Transformation</em>'.
     * @see tefkat.model.TRule#getTransformation()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Transformation();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.TRule#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Src</em>'.
     * @see tefkat.model.TRule#getSrc()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Src();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.TRule#getTgt <em>Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Tgt</em>'.
     * @see tefkat.model.TRule#getTgt()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Tgt();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.TRule#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extended</em>'.
     * @see tefkat.model.TRule#getExtended()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Extended();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.TRule#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseded</em>'.
     * @see tefkat.model.TRule#getSuperseded()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Superseded();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.TRule#isAbstract <em>Abstract</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Abstract</em>'.
     * @see tefkat.model.TRule#isAbstract()
     * @see #getTRule()
     * @generated
     */
    EAttribute getTRule_Abstract();

    /**
     * Returns the meta object for class '{@link tefkat.model.Transformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transformation</em>'.
     * @see tefkat.model.Transformation
     * @generated
     */
    EClass getTransformation();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.Transformation#getTRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>TRule</em>'.
     * @see tefkat.model.Transformation#getTRule()
     * @see #getTransformation()
     * @generated
     */
    EReference getTransformation_TRule();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.model.Transformation#getImportedPackages <em>Imported Packages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Imported Packages</em>'.
     * @see tefkat.model.Transformation#getImportedPackages()
     * @see #getTransformation()
     * @generated
     */
    EAttribute getTransformation_ImportedPackages();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.Transformation#getNamespaceDeclarations <em>Namespace Declarations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Namespace Declarations</em>'.
     * @see tefkat.model.Transformation#getNamespaceDeclarations()
     * @see #getTransformation()
     * @generated
     */
    EReference getTransformation_NamespaceDeclarations();

    /**
     * Returns the meta object for class '{@link tefkat.model.PatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Defn</em>'.
     * @see tefkat.model.PatternDefn
     * @generated
     */
    EClass getPatternDefn();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.PatternDefn#getPatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Pattern Scope</em>'.
     * @see tefkat.model.PatternDefn#getPatternScope()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_PatternScope();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.PatternDefn#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Term</em>'.
     * @see tefkat.model.PatternDefn#getTerm()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_Term();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.PatternDefn#getParameterVar <em>Parameter Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Parameter Var</em>'.
     * @see tefkat.model.PatternDefn#getParameterVar()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_ParameterVar();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.PatternDefn#isSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see tefkat.model.PatternDefn#isSource()
     * @see #getPatternDefn()
     * @generated
     */
    EAttribute getPatternDefn_Source();

    /**
     * Returns the meta object for class '{@link tefkat.model.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query</em>'.
     * @see tefkat.model.Query
     * @generated
     */
    EClass getQuery();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.Query#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Term</em>'.
     * @see tefkat.model.Query#getTerm()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_Term();

    /**
     * Returns the meta object for the reference list '{@link tefkat.model.Query#getParameterVar <em>Parameter Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Parameter Var</em>'.
     * @see tefkat.model.Query#getParameterVar()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_ParameterVar();

    /**
     * Returns the meta object for class '{@link tefkat.model.Term <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Term</em>'.
     * @see tefkat.model.Term
     * @generated
     */
    EClass getTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.Term#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Pattern Defn</em>'.
     * @see tefkat.model.Term#getPatternDefn()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_PatternDefn();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.Term#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query</em>'.
     * @see tefkat.model.Term#getQuery()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_Query();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.Term#getCompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Compound Term</em>'.
     * @see tefkat.model.Term#getCompoundTerm()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_CompoundTerm();

    /**
     * Returns the meta object for the reference '{@link tefkat.model.Term#getContext <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Context</em>'.
     * @see tefkat.model.Term#getContext()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_Context();

    /**
     * Returns the meta object for class '{@link tefkat.model.SourceTerm <em>Source Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Term</em>'.
     * @see tefkat.model.SourceTerm
     * @generated
     */
    EClass getSourceTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.SourceTerm#getTRuleSrc <em>TRule Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>TRule Src</em>'.
     * @see tefkat.model.SourceTerm#getTRuleSrc()
     * @see #getSourceTerm()
     * @generated
     */
    EReference getSourceTerm_TRuleSrc();

    /**
     * Returns the meta object for class '{@link tefkat.model.TargetTerm <em>Target Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target Term</em>'.
     * @see tefkat.model.TargetTerm
     * @generated
     */
    EClass getTargetTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>TRule Tgt</em>'.
     * @see tefkat.model.TargetTerm#getTRuleTgt()
     * @see #getTargetTerm()
     * @generated
     */
    EReference getTargetTerm_TRuleTgt();

    /**
     * Returns the meta object for class '{@link tefkat.model.CompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound Term</em>'.
     * @see tefkat.model.CompoundTerm
     * @generated
     */
    EClass getCompoundTerm();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.CompoundTerm#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Term</em>'.
     * @see tefkat.model.CompoundTerm#getTerm()
     * @see #getCompoundTerm()
     * @generated
     */
    EReference getCompoundTerm_Term();

    /**
     * Returns the meta object for class '{@link tefkat.model.SimpleTerm <em>Simple Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Term</em>'.
     * @see tefkat.model.SimpleTerm
     * @generated
     */
    EClass getSimpleTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression</em>'.
     * @see tefkat.model.Expression
     * @generated
     */
    EClass getExpression();

    /**
     * Returns the meta object for the container reference '{@link tefkat.model.Expression#getExpr <em>Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Expr</em>'.
     * @see tefkat.model.Expression#getExpr()
     * @see #getExpression()
     * @generated
     */
    EReference getExpression_Expr();

    /**
     * Returns the meta object for class '{@link tefkat.model.InstanceRef <em>Instance Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance Ref</em>'.
     * @see tefkat.model.InstanceRef
     * @generated
     */
    EClass getInstanceRef();

    /**
     * Returns the meta object for the reference '{@link tefkat.model.InstanceRef#getObject <em>Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Object</em>'.
     * @see tefkat.model.InstanceRef#getObject()
     * @see #getInstanceRef()
     * @generated
     */
    EReference getInstanceRef_Object();

    /**
     * Returns the meta object for class '{@link tefkat.model.AndTerm <em>And Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>And Term</em>'.
     * @see tefkat.model.AndTerm
     * @generated
     */
    EClass getAndTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.OrTerm <em>Or Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Or Term</em>'.
     * @see tefkat.model.OrTerm
     * @generated
     */
    EClass getOrTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.NotTerm <em>Not Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Not Term</em>'.
     * @see tefkat.model.NotTerm
     * @generated
     */
    EClass getNotTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.IfTerm <em>If Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>If Term</em>'.
     * @see tefkat.model.IfTerm
     * @generated
     */
    EClass getIfTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.TrackingUse <em>Tracking Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tracking Use</em>'.
     * @see tefkat.model.TrackingUse
     * @generated
     */
    EClass getTrackingUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.model.TrackingUse#getTracking <em>Tracking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Tracking</em>'.
     * @see tefkat.model.TrackingUse#getTracking()
     * @see #getTrackingUse()
     * @generated
     */
    EReference getTrackingUse_Tracking();

    /**
     * Returns the meta object for the map '{@link tefkat.model.TrackingUse#getFeatures <em>Features</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Features</em>'.
     * @see tefkat.model.TrackingUse#getFeatures()
     * @see #getTrackingUse()
     * @generated
     */
    EReference getTrackingUse_Features();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.TrackingUse#getTrackingName <em>Tracking Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tracking Name</em>'.
     * @see tefkat.model.TrackingUse#getTrackingName()
     * @see #getTrackingUse()
     * @generated
     */
    EAttribute getTrackingUse_TrackingName();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Feature Value Pair</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Value Pair</em>'.
     * @see java.util.Map.Entry
     * @model features="value key" 
     *        valueType="tefkat.model.Expression" valueContainment="true"
     *        keyType="java.lang.String"
     * @generated
     */
    EClass getFeatureValuePair();

    /**
     * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getFeatureValuePair()
     * @generated
     */
    EReference getFeatureValuePair_Value();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getFeatureValuePair()
     * @generated
     */
    EAttribute getFeatureValuePair_Key();

    /**
     * Returns the meta object for class '{@link tefkat.model.PatternUse <em>Pattern Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Use</em>'.
     * @see tefkat.model.PatternUse
     * @generated
     */
    EClass getPatternUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.model.PatternUse#getDefn <em>Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Defn</em>'.
     * @see tefkat.model.PatternUse#getDefn()
     * @see #getPatternUse()
     * @generated
     */
    EReference getPatternUse_Defn();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.PatternUse#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.model.PatternUse#getArg()
     * @see #getPatternUse()
     * @generated
     */
    EReference getPatternUse_Arg();

    /**
     * Returns the meta object for class '{@link tefkat.model.MofTerm <em>Mof Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Term</em>'.
     * @see tefkat.model.MofTerm
     * @generated
     */
    EClass getMofTerm();

    /**
     * Returns the meta object for class '{@link tefkat.model.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Condition</em>'.
     * @see tefkat.model.Condition
     * @generated
     */
    EClass getCondition();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.Condition#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.model.Condition#getArg()
     * @see #getCondition()
     * @generated
     */
    EReference getCondition_Arg();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.Condition#getRelation <em>Relation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relation</em>'.
     * @see tefkat.model.Condition#getRelation()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Relation();

    /**
     * Returns the meta object for class '{@link tefkat.model.MofInstance <em>Mof Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Instance</em>'.
     * @see tefkat.model.MofInstance
     * @generated
     */
    EClass getMofInstance();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofInstance#getTypeName <em>Type Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Type Name</em>'.
     * @see tefkat.model.MofInstance#getTypeName()
     * @see #getMofInstance()
     * @generated
     */
    EReference getMofInstance_TypeName();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofInstance#getInstance <em>Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Instance</em>'.
     * @see tefkat.model.MofInstance#getInstance()
     * @see #getMofInstance()
     * @generated
     */
    EReference getMofInstance_Instance();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.MofInstance#isExact <em>Exact</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exact</em>'.
     * @see tefkat.model.MofInstance#isExact()
     * @see #getMofInstance()
     * @generated
     */
    EAttribute getMofInstance_Exact();

    /**
     * Returns the meta object for class '{@link tefkat.model.MofOrder <em>Mof Order</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Order</em>'.
     * @see tefkat.model.MofOrder
     * @generated
     */
    EClass getMofOrder();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofOrder#getLesser <em>Lesser</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Lesser</em>'.
     * @see tefkat.model.MofOrder#getLesser()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Lesser();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofOrder#getGreater <em>Greater</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Greater</em>'.
     * @see tefkat.model.MofOrder#getGreater()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Greater();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofOrder#getInstance <em>Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Instance</em>'.
     * @see tefkat.model.MofOrder#getInstance()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Instance();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.MofOrder#getFeature <em>Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Feature</em>'.
     * @see tefkat.model.MofOrder#getFeature()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Feature();

    /**
     * Returns the meta object for class '{@link tefkat.model.CompoundExpr <em>Compound Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound Expr</em>'.
     * @see tefkat.model.CompoundExpr
     * @generated
     */
    EClass getCompoundExpr();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.CompoundExpr#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.model.CompoundExpr#getArg()
     * @see #getCompoundExpr()
     * @generated
     */
    EReference getCompoundExpr_Arg();

    /**
     * Returns the meta object for class '{@link tefkat.model.SimpleExpr <em>Simple Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Expr</em>'.
     * @see tefkat.model.SimpleExpr
     * @generated
     */
    EClass getSimpleExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.SimpleExpr#getRepresentation <em>Representation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Representation</em>'.
     * @see tefkat.model.SimpleExpr#getRepresentation()
     * @see #getSimpleExpr()
     * @generated
     */
    EAttribute getSimpleExpr_Representation();

    /**
     * Returns the meta object for class '{@link tefkat.model.VarUse <em>Var Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var Use</em>'.
     * @see tefkat.model.VarUse
     * @generated
     */
    EClass getVarUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.model.VarUse#getVar <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Var</em>'.
     * @see tefkat.model.VarUse#getVar()
     * @see #getVarUse()
     * @generated
     */
    EReference getVarUse_Var();

    /**
     * Returns the meta object for class '{@link tefkat.model.CollectionExpr <em>Collection Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Collection Expr</em>'.
     * @see tefkat.model.CollectionExpr
     * @generated
     */
    EClass getCollectionExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.CollectionExpr#isUnique <em>Unique</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unique</em>'.
     * @see tefkat.model.CollectionExpr#isUnique()
     * @see #getCollectionExpr()
     * @generated
     */
    EAttribute getCollectionExpr_Unique();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.CollectionExpr#isOrdered <em>Ordered</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ordered</em>'.
     * @see tefkat.model.CollectionExpr#isOrdered()
     * @see #getCollectionExpr()
     * @generated
     */
    EAttribute getCollectionExpr_Ordered();

    /**
     * Returns the meta object for class '{@link tefkat.model.FunctionExpr <em>Function Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Expr</em>'.
     * @see tefkat.model.FunctionExpr
     * @generated
     */
    EClass getFunctionExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.FunctionExpr#getFunction <em>Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Function</em>'.
     * @see tefkat.model.FunctionExpr#getFunction()
     * @see #getFunctionExpr()
     * @generated
     */
    EAttribute getFunctionExpr_Function();

    /**
     * Returns the meta object for class '{@link tefkat.model.FeatureExpr <em>Feature Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Expr</em>'.
     * @see tefkat.model.FeatureExpr
     * @generated
     */
    EClass getFeatureExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.FeatureExpr#isOperation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation</em>'.
     * @see tefkat.model.FeatureExpr#isOperation()
     * @see #getFeatureExpr()
     * @generated
     */
    EAttribute getFeatureExpr_Operation();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.FeatureExpr#isCollect <em>Collect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Collect</em>'.
     * @see tefkat.model.FeatureExpr#isCollect()
     * @see #getFeatureExpr()
     * @generated
     */
    EAttribute getFeatureExpr_Collect();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.FeatureExpr#getFeature <em>Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Feature</em>'.
     * @see tefkat.model.FeatureExpr#getFeature()
     * @see #getFeatureExpr()
     * @generated
     */
    EReference getFeatureExpr_Feature();

    /**
     * Returns the meta object for class '{@link tefkat.model.StringConstant <em>String Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Constant</em>'.
     * @see tefkat.model.StringConstant
     * @generated
     */
    EClass getStringConstant();

    /**
     * Returns the meta object for class '{@link tefkat.model.IntConstant <em>Int Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Constant</em>'.
     * @see tefkat.model.IntConstant
     * @generated
     */
    EClass getIntConstant();

    /**
     * Returns the meta object for class '{@link tefkat.model.RealConstant <em>Real Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Real Constant</em>'.
     * @see tefkat.model.RealConstant
     * @generated
     */
    EClass getRealConstant();

    /**
     * Returns the meta object for class '{@link tefkat.model.BooleanConstant <em>Boolean Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean Constant</em>'.
     * @see tefkat.model.BooleanConstant
     * @generated
     */
    EClass getBooleanConstant();

    /**
     * Returns the meta object for class '{@link tefkat.model.EnumConstant <em>Enum Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Constant</em>'.
     * @see tefkat.model.EnumConstant
     * @generated
     */
    EClass getEnumConstant();

    /**
     * Returns the meta object for class '{@link tefkat.model.Injection <em>Injection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Injection</em>'.
     * @see tefkat.model.Injection
     * @generated
     */
    EClass getInjection();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.Injection#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.model.Injection#getName()
     * @see #getInjection()
     * @generated
     */
    EAttribute getInjection_Name();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.model.Injection#getSources <em>Sources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sources</em>'.
     * @see tefkat.model.Injection#getSources()
     * @see #getInjection()
     * @generated
     */
    EReference getInjection_Sources();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.model.Injection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see tefkat.model.Injection#getTarget()
     * @see #getInjection()
     * @generated
     */
    EReference getInjection_Target();

    /**
     * Returns the meta object for class '{@link tefkat.model.NamespaceDeclaration <em>Namespace Declaration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Namespace Declaration</em>'.
     * @see tefkat.model.NamespaceDeclaration
     * @generated
     */
    EClass getNamespaceDeclaration();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.NamespaceDeclaration#getPrefix <em>Prefix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see tefkat.model.NamespaceDeclaration#getPrefix()
     * @see #getNamespaceDeclaration()
     * @generated
     */
    EAttribute getNamespaceDeclaration_Prefix();

    /**
     * Returns the meta object for the attribute '{@link tefkat.model.NamespaceDeclaration#getURI <em>URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>URI</em>'.
     * @see tefkat.model.NamespaceDeclaration#getURI()
     * @see #getNamespaceDeclaration()
     * @generated
     */
    EAttribute getNamespaceDeclaration_URI();

    /**
     * Returns the meta object for data type '{@link java.util.Collection <em>Collection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Collection</em>'.
     * @see java.util.Collection
     * @model instanceClass="java.util.Collection"
     * @generated
     */
    EDataType getCollection();

    /**
     * Returns the meta object for data type '{@link java.util.List <em>List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>List</em>'.
     * @see java.util.List
     * @model instanceClass="java.util.List"
     * @generated
     */
    EDataType getList();

    /**
     * Returns the meta object for data type '<em>List Array</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>List Array</em>'.
     * @model instanceClass="java.util.List[]"
     * @generated
     */
    EDataType getListArray();

    /**
     * Returns the meta object for data type '{@link tefkat.model.TefkatException <em>Exception</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Exception</em>'.
     * @see tefkat.model.TefkatException
     * @model instanceClass="tefkat.model.TefkatException"
     * @generated
     */
    EDataType getTefkatException();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TefkatFactory getTefkatFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals  {
        /**
         * The meta object literal for the '{@link tefkat.model.impl.ExtentImpl <em>Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.ExtentImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getExtent()
         * @generated
         */
        EClass EXTENT = eINSTANCE.getExtent();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.ContainerExtentImpl <em>Container Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.ContainerExtentImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getContainerExtent()
         * @generated
         */
        EClass CONTAINER_EXTENT = eINSTANCE.getContainerExtent();

        /**
         * The meta object literal for the '<em><b>Resource</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTAINER_EXTENT__RESOURCE = eINSTANCE.getContainerExtent_Resource();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.ReferenceExtentImpl <em>Reference Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.ReferenceExtentImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getReferenceExtent()
         * @generated
         */
        EClass REFERENCE_EXTENT = eINSTANCE.getReferenceExtent();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute REFERENCE_EXTENT__RESOURCES = eINSTANCE.getReferenceExtent_Resources();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.VarImpl <em>Var</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.VarImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getVar()
         * @generated
         */
        EClass VAR = eINSTANCE.getVar();

        /**
         * The meta object literal for the '<em><b>Scope</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__SCOPE = eINSTANCE.getVar_Scope();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VAR__NAME = eINSTANCE.getVar_Name();

        /**
         * The meta object literal for the '<em><b>Usages</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__USAGES = eINSTANCE.getVar_Usages();

        /**
         * The meta object literal for the '<em><b>Superseded</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__SUPERSEDED = eINSTANCE.getVar_Superseded();

        /**
         * The meta object literal for the '<em><b>Superseder</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__SUPERSEDER = eINSTANCE.getVar_Superseder();

        /**
         * The meta object literal for the '<em><b>Extended</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__EXTENDED = eINSTANCE.getVar_Extended();

        /**
         * The meta object literal for the '<em><b>Extender</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR__EXTENDER = eINSTANCE.getVar_Extender();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.VarScopeImpl <em>Var Scope</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.VarScopeImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getVarScope()
         * @generated
         */
        EClass VAR_SCOPE = eINSTANCE.getVarScope();

        /**
         * The meta object literal for the '<em><b>Vars</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR_SCOPE__VARS = eINSTANCE.getVarScope_Vars();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VAR_SCOPE__NAME = eINSTANCE.getVarScope_Name();

        /**
         * The meta object literal for the '<em><b>Comments</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VAR_SCOPE__COMMENTS = eINSTANCE.getVarScope_Comments();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.PatternScopeImpl <em>Pattern Scope</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.PatternScopeImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getPatternScope()
         * @generated
         */
        EClass PATTERN_SCOPE = eINSTANCE.getPatternScope();

        /**
         * The meta object literal for the '<em><b>Pattern Defn</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_SCOPE__PATTERN_DEFN = eINSTANCE.getPatternScope_PatternDefn();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.TRuleImpl <em>TRule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TRuleImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getTRule()
         * @generated
         */
        EClass TRULE = eINSTANCE.getTRule();

        /**
         * The meta object literal for the '<em><b>Transformation</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRULE__TRANSFORMATION = eINSTANCE.getTRule_Transformation();

        /**
         * The meta object literal for the '<em><b>Src</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRULE__SRC = eINSTANCE.getTRule_Src();

        /**
         * The meta object literal for the '<em><b>Tgt</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRULE__TGT = eINSTANCE.getTRule_Tgt();

        /**
         * The meta object literal for the '<em><b>Extended</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRULE__EXTENDED = eINSTANCE.getTRule_Extended();

        /**
         * The meta object literal for the '<em><b>Superseded</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRULE__SUPERSEDED = eINSTANCE.getTRule_Superseded();

        /**
         * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TRULE__ABSTRACT = eINSTANCE.getTRule_Abstract();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.TransformationImpl <em>Transformation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TransformationImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getTransformation()
         * @generated
         */
        EClass TRANSFORMATION = eINSTANCE.getTransformation();

        /**
         * The meta object literal for the '<em><b>TRule</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSFORMATION__TRULE = eINSTANCE.getTransformation_TRule();

        /**
         * The meta object literal for the '<em><b>Imported Packages</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TRANSFORMATION__IMPORTED_PACKAGES = eINSTANCE.getTransformation_ImportedPackages();

        /**
         * The meta object literal for the '<em><b>Namespace Declarations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSFORMATION__NAMESPACE_DECLARATIONS = eINSTANCE.getTransformation_NamespaceDeclarations();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.PatternDefnImpl <em>Pattern Defn</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.PatternDefnImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getPatternDefn()
         * @generated
         */
        EClass PATTERN_DEFN = eINSTANCE.getPatternDefn();

        /**
         * The meta object literal for the '<em><b>Pattern Scope</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_DEFN__PATTERN_SCOPE = eINSTANCE.getPatternDefn_PatternScope();

        /**
         * The meta object literal for the '<em><b>Term</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_DEFN__TERM = eINSTANCE.getPatternDefn_Term();

        /**
         * The meta object literal for the '<em><b>Parameter Var</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_DEFN__PARAMETER_VAR = eINSTANCE.getPatternDefn_ParameterVar();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PATTERN_DEFN__SOURCE = eINSTANCE.getPatternDefn_Source();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.QueryImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getQuery()
         * @generated
         */
        EClass QUERY = eINSTANCE.getQuery();

        /**
         * The meta object literal for the '<em><b>Term</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY__TERM = eINSTANCE.getQuery_Term();

        /**
         * The meta object literal for the '<em><b>Parameter Var</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY__PARAMETER_VAR = eINSTANCE.getQuery_ParameterVar();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.TermImpl <em>Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getTerm()
         * @generated
         */
        EClass TERM = eINSTANCE.getTerm();

        /**
         * The meta object literal for the '<em><b>Pattern Defn</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TERM__PATTERN_DEFN = eINSTANCE.getTerm_PatternDefn();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TERM__QUERY = eINSTANCE.getTerm_Query();

        /**
         * The meta object literal for the '<em><b>Compound Term</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TERM__COMPOUND_TERM = eINSTANCE.getTerm_CompoundTerm();

        /**
         * The meta object literal for the '<em><b>Context</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TERM__CONTEXT = eINSTANCE.getTerm_Context();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.SourceTermImpl <em>Source Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.SourceTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getSourceTerm()
         * @generated
         */
        EClass SOURCE_TERM = eINSTANCE.getSourceTerm();

        /**
         * The meta object literal for the '<em><b>TRule Src</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE_TERM__TRULE_SRC = eINSTANCE.getSourceTerm_TRuleSrc();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.TargetTermImpl <em>Target Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TargetTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getTargetTerm()
         * @generated
         */
        EClass TARGET_TERM = eINSTANCE.getTargetTerm();

        /**
         * The meta object literal for the '<em><b>TRule Tgt</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TARGET_TERM__TRULE_TGT = eINSTANCE.getTargetTerm_TRuleTgt();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.CompoundTermImpl <em>Compound Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.CompoundTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getCompoundTerm()
         * @generated
         */
        EClass COMPOUND_TERM = eINSTANCE.getCompoundTerm();

        /**
         * The meta object literal for the '<em><b>Term</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOUND_TERM__TERM = eINSTANCE.getCompoundTerm_Term();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.SimpleTermImpl <em>Simple Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.SimpleTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getSimpleTerm()
         * @generated
         */
        EClass SIMPLE_TERM = eINSTANCE.getSimpleTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.ExpressionImpl <em>Expression</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.ExpressionImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getExpression()
         * @generated
         */
        EClass EXPRESSION = eINSTANCE.getExpression();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXPRESSION__EXPR = eINSTANCE.getExpression_Expr();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.InstanceRefImpl <em>Instance Ref</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.InstanceRefImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getInstanceRef()
         * @generated
         */
        EClass INSTANCE_REF = eINSTANCE.getInstanceRef();

        /**
         * The meta object literal for the '<em><b>Object</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INSTANCE_REF__OBJECT = eINSTANCE.getInstanceRef_Object();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.AndTermImpl <em>And Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.AndTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getAndTerm()
         * @generated
         */
        EClass AND_TERM = eINSTANCE.getAndTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.OrTermImpl <em>Or Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.OrTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getOrTerm()
         * @generated
         */
        EClass OR_TERM = eINSTANCE.getOrTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.NotTermImpl <em>Not Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.NotTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getNotTerm()
         * @generated
         */
        EClass NOT_TERM = eINSTANCE.getNotTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.IfTermImpl <em>If Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.IfTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getIfTerm()
         * @generated
         */
        EClass IF_TERM = eINSTANCE.getIfTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.TrackingUseImpl <em>Tracking Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TrackingUseImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getTrackingUse()
         * @generated
         */
        EClass TRACKING_USE = eINSTANCE.getTrackingUse();

        /**
         * The meta object literal for the '<em><b>Tracking</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRACKING_USE__TRACKING = eINSTANCE.getTrackingUse_Tracking();

        /**
         * The meta object literal for the '<em><b>Features</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRACKING_USE__FEATURES = eINSTANCE.getTrackingUse_Features();

        /**
         * The meta object literal for the '<em><b>Tracking Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TRACKING_USE__TRACKING_NAME = eINSTANCE.getTrackingUse_TrackingName();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.FeatureValuePairImpl <em>Feature Value Pair</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.FeatureValuePairImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getFeatureValuePair()
         * @generated
         */
        EClass FEATURE_VALUE_PAIR = eINSTANCE.getFeatureValuePair();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FEATURE_VALUE_PAIR__VALUE = eINSTANCE.getFeatureValuePair_Value();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FEATURE_VALUE_PAIR__KEY = eINSTANCE.getFeatureValuePair_Key();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.PatternUseImpl <em>Pattern Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.PatternUseImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getPatternUse()
         * @generated
         */
        EClass PATTERN_USE = eINSTANCE.getPatternUse();

        /**
         * The meta object literal for the '<em><b>Defn</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_USE__DEFN = eINSTANCE.getPatternUse_Defn();

        /**
         * The meta object literal for the '<em><b>Arg</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PATTERN_USE__ARG = eINSTANCE.getPatternUse_Arg();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.MofTermImpl <em>Mof Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.MofTermImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getMofTerm()
         * @generated
         */
        EClass MOF_TERM = eINSTANCE.getMofTerm();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.ConditionImpl <em>Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.ConditionImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getCondition()
         * @generated
         */
        EClass CONDITION = eINSTANCE.getCondition();

        /**
         * The meta object literal for the '<em><b>Arg</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONDITION__ARG = eINSTANCE.getCondition_Arg();

        /**
         * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONDITION__RELATION = eINSTANCE.getCondition_Relation();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.MofInstanceImpl <em>Mof Instance</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.MofInstanceImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getMofInstance()
         * @generated
         */
        EClass MOF_INSTANCE = eINSTANCE.getMofInstance();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_INSTANCE__TYPE_NAME = eINSTANCE.getMofInstance_TypeName();

        /**
         * The meta object literal for the '<em><b>Instance</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_INSTANCE__INSTANCE = eINSTANCE.getMofInstance_Instance();

        /**
         * The meta object literal for the '<em><b>Exact</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MOF_INSTANCE__EXACT = eINSTANCE.getMofInstance_Exact();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.MofOrderImpl <em>Mof Order</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.MofOrderImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getMofOrder()
         * @generated
         */
        EClass MOF_ORDER = eINSTANCE.getMofOrder();

        /**
         * The meta object literal for the '<em><b>Lesser</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_ORDER__LESSER = eINSTANCE.getMofOrder_Lesser();

        /**
         * The meta object literal for the '<em><b>Greater</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_ORDER__GREATER = eINSTANCE.getMofOrder_Greater();

        /**
         * The meta object literal for the '<em><b>Instance</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_ORDER__INSTANCE = eINSTANCE.getMofOrder_Instance();

        /**
         * The meta object literal for the '<em><b>Feature</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MOF_ORDER__FEATURE = eINSTANCE.getMofOrder_Feature();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.CompoundExprImpl <em>Compound Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.CompoundExprImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getCompoundExpr()
         * @generated
         */
        EClass COMPOUND_EXPR = eINSTANCE.getCompoundExpr();

        /**
         * The meta object literal for the '<em><b>Arg</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOUND_EXPR__ARG = eINSTANCE.getCompoundExpr_Arg();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.SimpleExprImpl <em>Simple Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.SimpleExprImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getSimpleExpr()
         * @generated
         */
        EClass SIMPLE_EXPR = eINSTANCE.getSimpleExpr();

        /**
         * The meta object literal for the '<em><b>Representation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_EXPR__REPRESENTATION = eINSTANCE.getSimpleExpr_Representation();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.VarUseImpl <em>Var Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.VarUseImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getVarUse()
         * @generated
         */
        EClass VAR_USE = eINSTANCE.getVarUse();

        /**
         * The meta object literal for the '<em><b>Var</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VAR_USE__VAR = eINSTANCE.getVarUse_Var();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.CollectionExprImpl <em>Collection Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.CollectionExprImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getCollectionExpr()
         * @generated
         */
        EClass COLLECTION_EXPR = eINSTANCE.getCollectionExpr();

        /**
         * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COLLECTION_EXPR__UNIQUE = eINSTANCE.getCollectionExpr_Unique();

        /**
         * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COLLECTION_EXPR__ORDERED = eINSTANCE.getCollectionExpr_Ordered();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.FunctionExprImpl <em>Function Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.FunctionExprImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getFunctionExpr()
         * @generated
         */
        EClass FUNCTION_EXPR = eINSTANCE.getFunctionExpr();

        /**
         * The meta object literal for the '<em><b>Function</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FUNCTION_EXPR__FUNCTION = eINSTANCE.getFunctionExpr_Function();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.FeatureExprImpl <em>Feature Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.FeatureExprImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getFeatureExpr()
         * @generated
         */
        EClass FEATURE_EXPR = eINSTANCE.getFeatureExpr();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FEATURE_EXPR__OPERATION = eINSTANCE.getFeatureExpr_Operation();

        /**
         * The meta object literal for the '<em><b>Collect</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FEATURE_EXPR__COLLECT = eINSTANCE.getFeatureExpr_Collect();

        /**
         * The meta object literal for the '<em><b>Feature</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FEATURE_EXPR__FEATURE = eINSTANCE.getFeatureExpr_Feature();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.StringConstantImpl <em>String Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.StringConstantImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getStringConstant()
         * @generated
         */
        EClass STRING_CONSTANT = eINSTANCE.getStringConstant();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.IntConstantImpl <em>Int Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.IntConstantImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getIntConstant()
         * @generated
         */
        EClass INT_CONSTANT = eINSTANCE.getIntConstant();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.RealConstantImpl <em>Real Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.RealConstantImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getRealConstant()
         * @generated
         */
        EClass REAL_CONSTANT = eINSTANCE.getRealConstant();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.BooleanConstantImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getBooleanConstant()
         * @generated
         */
        EClass BOOLEAN_CONSTANT = eINSTANCE.getBooleanConstant();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.EnumConstantImpl <em>Enum Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.EnumConstantImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getEnumConstant()
         * @generated
         */
        EClass ENUM_CONSTANT = eINSTANCE.getEnumConstant();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.InjectionImpl <em>Injection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.InjectionImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getInjection()
         * @generated
         */
        EClass INJECTION = eINSTANCE.getInjection();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INJECTION__NAME = eINSTANCE.getInjection_Name();

        /**
         * The meta object literal for the '<em><b>Sources</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INJECTION__SOURCES = eINSTANCE.getInjection_Sources();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INJECTION__TARGET = eINSTANCE.getInjection_Target();

        /**
         * The meta object literal for the '{@link tefkat.model.impl.NamespaceDeclarationImpl <em>Namespace Declaration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.NamespaceDeclarationImpl
         * @see tefkat.model.impl.TefkatPackageImpl#getNamespaceDeclaration()
         * @generated
         */
        EClass NAMESPACE_DECLARATION = eINSTANCE.getNamespaceDeclaration();

        /**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMESPACE_DECLARATION__PREFIX = eINSTANCE.getNamespaceDeclaration_Prefix();

        /**
         * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMESPACE_DECLARATION__URI = eINSTANCE.getNamespaceDeclaration_URI();

        /**
         * The meta object literal for the '<em>Collection</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.Collection
         * @see tefkat.model.impl.TefkatPackageImpl#getCollection()
         * @generated
         */
        EDataType COLLECTION = eINSTANCE.getCollection();

        /**
         * The meta object literal for the '<em>List</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.List
         * @see tefkat.model.impl.TefkatPackageImpl#getList()
         * @generated
         */
        EDataType LIST = eINSTANCE.getList();

        /**
         * The meta object literal for the '<em>List Array</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.impl.TefkatPackageImpl#getListArray()
         * @generated
         */
        EDataType LIST_ARRAY = eINSTANCE.getListArray();

        /**
         * The meta object literal for the '<em>Exception</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.model.TefkatException
         * @see tefkat.model.impl.TefkatPackageImpl#getTefkatException()
         * @generated
         */
        EDataType TEFKAT_EXCEPTION = eINSTANCE.getTefkatException();

    }

} //TefkatPackage
