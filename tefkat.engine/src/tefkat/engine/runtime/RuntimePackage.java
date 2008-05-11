/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime;

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
 * @see tefkat.engine.runtime.RuntimeFactory
 * @model kind="package"
 * @generated
 */
public interface RuntimePackage extends EPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright michael lawley 2004";

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "runtime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///TefkatRuntime-1.0.ecore";

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
    RuntimePackage eINSTANCE = tefkat.engine.runtime.impl.RuntimePackageImpl.init();

    /**
     * The meta object id for the '{@link tefkat.engine.runtime.impl.ExtentImpl <em>Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.ExtentImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getExtent()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.ContainerExtentImpl <em>Container Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.ContainerExtentImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getContainerExtent()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.ReferenceExtentImpl <em>Reference Extent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.ReferenceExtentImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getReferenceExtent()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.VarImpl <em>Var</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.VarImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVar()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.VarScopeImpl <em>Var Scope</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.VarScopeImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVarScope()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.PatternScopeImpl <em>Pattern Scope</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.PatternScopeImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternScope()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.TRuleImpl <em>TRule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.TRuleImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTRule()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.TransformationImpl <em>Transformation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.TransformationImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTransformation()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.NamespaceDeclarationImpl <em>Namespace Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.NamespaceDeclarationImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNamespaceDeclaration()
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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.PatternDefnImpl <em>Pattern Defn</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.PatternDefnImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternDefn()
     * @generated
     */
    int PATTERN_DEFN = 9;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.QueryImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getQuery()
     * @generated
     */
    int QUERY = 10;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.TermImpl <em>Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.TermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTerm()
     * @generated
     */
    int TERM = 11;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.SourceTermImpl <em>Source Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.SourceTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSourceTerm()
     * @generated
     */
    int SOURCE_TERM = 12;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.TargetTermImpl <em>Target Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.TargetTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTargetTerm()
     * @generated
     */
    int TARGET_TERM = 13;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.CompoundTermImpl <em>Compound Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.CompoundTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCompoundTerm()
     * @generated
     */
    int COMPOUND_TERM = 14;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.SimpleTermImpl <em>Simple Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.SimpleTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSimpleTerm()
     * @generated
     */
    int SIMPLE_TERM = 15;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.ExpressionImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getExpression()
     * @generated
     */
    int EXPRESSION = 16;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.InstanceRefImpl <em>Instance Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.InstanceRefImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getInstanceRef()
     * @generated
     */
    int INSTANCE_REF = 17;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.AndTermImpl <em>And Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.AndTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getAndTerm()
     * @generated
     */
    int AND_TERM = 18;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.OrTermImpl <em>Or Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.OrTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getOrTerm()
     * @generated
     */
    int OR_TERM = 19;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.NotTermImpl <em>Not Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.NotTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNotTerm()
     * @generated
     */
    int NOT_TERM = 20;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.IfTermImpl <em>If Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.IfTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getIfTerm()
     * @generated
     */
    int IF_TERM = 21;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.TrackingUseImpl <em>Tracking Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.TrackingUseImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTrackingUse()
     * @generated
     */
    int TRACKING_USE = 22;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.FeatureValuePairImpl <em>Feature Value Pair</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.FeatureValuePairImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFeatureValuePair()
     * @generated
     */
    int FEATURE_VALUE_PAIR = 23;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.PatternUseImpl <em>Pattern Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.PatternUseImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternUse()
     * @generated
     */
    int PATTERN_USE = 24;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.MofTermImpl <em>Mof Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.MofTermImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofTerm()
     * @generated
     */
    int MOF_TERM = 25;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.ConditionImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCondition()
     * @generated
     */
    int CONDITION = 26;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.MofInstanceImpl <em>Mof Instance</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.MofInstanceImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofInstance()
     * @generated
     */
    int MOF_INSTANCE = 27;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.MofOrderImpl <em>Mof Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.MofOrderImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofOrder()
     * @generated
     */
    int MOF_ORDER = 28;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.CompoundExprImpl <em>Compound Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.CompoundExprImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCompoundExpr()
     * @generated
     */
    int COMPOUND_EXPR = 29;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.SimpleExprImpl <em>Simple Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.SimpleExprImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSimpleExpr()
     * @generated
     */
    int SIMPLE_EXPR = 30;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.VarUseImpl <em>Var Use</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.VarUseImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVarUse()
     * @generated
     */
    int VAR_USE = 31;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.CollectionExprImpl <em>Collection Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.CollectionExprImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCollectionExpr()
     * @generated
     */
    int COLLECTION_EXPR = 32;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.FunctionExprImpl <em>Function Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.FunctionExprImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFunctionExpr()
     * @generated
     */
    int FUNCTION_EXPR = 33;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.FeatureExprImpl <em>Feature Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.FeatureExprImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFeatureExpr()
     * @generated
     */
    int FEATURE_EXPR = 34;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.StringConstantImpl <em>String Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.StringConstantImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getStringConstant()
     * @generated
     */
    int STRING_CONSTANT = 35;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.IntConstantImpl <em>Int Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.IntConstantImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getIntConstant()
     * @generated
     */
    int INT_CONSTANT = 36;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.RealConstantImpl <em>Real Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.RealConstantImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getRealConstant()
     * @generated
     */
    int REAL_CONSTANT = 37;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.BooleanConstantImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getBooleanConstant()
     * @generated
     */
    int BOOLEAN_CONSTANT = 38;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.EnumConstantImpl <em>Enum Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.EnumConstantImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getEnumConstant()
     * @generated
     */
    int ENUM_CONSTANT = 39;

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
     * The meta object id for the '{@link tefkat.engine.runtime.impl.InjectionImpl <em>Injection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.InjectionImpl
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getInjection()
     * @generated
     */
    int INJECTION = 40;

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
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCollection()
     * @generated
     */
    int COLLECTION = 41;

    /**
     * The meta object id for the '<em>List</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getList()
     * @generated
     */
    int LIST = 42;

    /**
     * The meta object id for the '<em>List Array</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getListArray()
     * @generated
     */
    int LIST_ARRAY = 43;

    /**
     * The meta object id for the '<em>Context</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.Context
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getContext()
     * @generated
     */
    int CONTEXT = 44;

    /**
     * The meta object id for the '<em>Tefkat Exception</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.TefkatException
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTefkatException()
     * @generated
     */
    int TEFKAT_EXCEPTION = 45;


    /**
     * The meta object id for the '<em>Binding</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.Binding
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getBinding()
     * @generated
     */
    int BINDING = 46;

    /**
     * The meta object id for the '<em>Resolution Exception</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.ResolutionException
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getResolutionException()
     * @generated
     */
    int RESOLUTION_EXCEPTION = 47;

    /**
     * The meta object id for the '<em>Not Ground Exception</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tefkat.engine.runtime.NotGroundException
     * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNotGroundException()
     * @generated
     */
    int NOT_GROUND_EXCEPTION = 48;


    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Extent <em>Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Extent</em>'.
     * @see tefkat.engine.runtime.Extent
     * @generated
     */
    EClass getExtent();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.ContainerExtent <em>Container Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Container Extent</em>'.
     * @see tefkat.engine.runtime.ContainerExtent
     * @generated
     */
    EClass getContainerExtent();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.ContainerExtent#getResource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resource</em>'.
     * @see tefkat.engine.runtime.ContainerExtent#getResource()
     * @see #getContainerExtent()
     * @generated
     */
    EAttribute getContainerExtent_Resource();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.ReferenceExtent <em>Reference Extent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reference Extent</em>'.
     * @see tefkat.engine.runtime.ReferenceExtent
     * @generated
     */
    EClass getReferenceExtent();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.engine.runtime.ReferenceExtent#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Resources</em>'.
     * @see tefkat.engine.runtime.ReferenceExtent#getResources()
     * @see #getReferenceExtent()
     * @generated
     */
    EAttribute getReferenceExtent_Resources();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Var <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var</em>'.
     * @see tefkat.engine.runtime.Var
     * @generated
     */
    EClass getVar();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.Var#getScope <em>Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Scope</em>'.
     * @see tefkat.engine.runtime.Var#getScope()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Scope();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.Var#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.engine.runtime.Var#getName()
     * @see #getVar()
     * @generated
     */
    EAttribute getVar_Name();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Var#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Usages</em>'.
     * @see tefkat.engine.runtime.Var#getUsages()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Usages();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Var#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseded</em>'.
     * @see tefkat.engine.runtime.Var#getSuperseded()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Superseded();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Var#getSuperseder <em>Superseder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseder</em>'.
     * @see tefkat.engine.runtime.Var#getSuperseder()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Superseder();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Var#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extended</em>'.
     * @see tefkat.engine.runtime.Var#getExtended()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Extended();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Var#getExtender <em>Extender</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extender</em>'.
     * @see tefkat.engine.runtime.Var#getExtender()
     * @see #getVar()
     * @generated
     */
    EReference getVar_Extender();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.VarScope <em>Var Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var Scope</em>'.
     * @see tefkat.engine.runtime.VarScope
     * @generated
     */
    EClass getVarScope();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.VarScope#getVars <em>Vars</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Vars</em>'.
     * @see tefkat.engine.runtime.VarScope#getVars()
     * @see #getVarScope()
     * @generated
     */
    EReference getVarScope_Vars();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.VarScope#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.engine.runtime.VarScope#getName()
     * @see #getVarScope()
     * @generated
     */
    EAttribute getVarScope_Name();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.engine.runtime.VarScope#getComments <em>Comments</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Comments</em>'.
     * @see tefkat.engine.runtime.VarScope#getComments()
     * @see #getVarScope()
     * @generated
     */
    EAttribute getVarScope_Comments();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.PatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Scope</em>'.
     * @see tefkat.engine.runtime.PatternScope
     * @generated
     */
    EClass getPatternScope();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.PatternScope#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pattern Defn</em>'.
     * @see tefkat.engine.runtime.PatternScope#getPatternDefn()
     * @see #getPatternScope()
     * @generated
     */
    EReference getPatternScope_PatternDefn();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.TRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>TRule</em>'.
     * @see tefkat.engine.runtime.TRule
     * @generated
     */
    EClass getTRule();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.TRule#getTransformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Transformation</em>'.
     * @see tefkat.engine.runtime.TRule#getTransformation()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Transformation();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.TRule#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Src</em>'.
     * @see tefkat.engine.runtime.TRule#getSrc()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Src();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.TRule#getTgt <em>Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Tgt</em>'.
     * @see tefkat.engine.runtime.TRule#getTgt()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Tgt();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.TRule#getExtended <em>Extended</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Extended</em>'.
     * @see tefkat.engine.runtime.TRule#getExtended()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Extended();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.TRule#getSuperseded <em>Superseded</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Superseded</em>'.
     * @see tefkat.engine.runtime.TRule#getSuperseded()
     * @see #getTRule()
     * @generated
     */
    EReference getTRule_Superseded();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.TRule#isAbstract <em>Abstract</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Abstract</em>'.
     * @see tefkat.engine.runtime.TRule#isAbstract()
     * @see #getTRule()
     * @generated
     */
    EAttribute getTRule_Abstract();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Transformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transformation</em>'.
     * @see tefkat.engine.runtime.Transformation
     * @generated
     */
    EClass getTransformation();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.Transformation#getTRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>TRule</em>'.
     * @see tefkat.engine.runtime.Transformation#getTRule()
     * @see #getTransformation()
     * @generated
     */
    EReference getTransformation_TRule();

    /**
     * Returns the meta object for the attribute list '{@link tefkat.engine.runtime.Transformation#getImportedPackages <em>Imported Packages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Imported Packages</em>'.
     * @see tefkat.engine.runtime.Transformation#getImportedPackages()
     * @see #getTransformation()
     * @generated
     */
    EAttribute getTransformation_ImportedPackages();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.Transformation#getNamespaceDeclarations <em>Namespace Declarations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Namespace Declarations</em>'.
     * @see tefkat.engine.runtime.Transformation#getNamespaceDeclarations()
     * @see #getTransformation()
     * @generated
     */
    EReference getTransformation_NamespaceDeclarations();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.NamespaceDeclaration <em>Namespace Declaration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Namespace Declaration</em>'.
     * @see tefkat.engine.runtime.NamespaceDeclaration
     * @generated
     */
    EClass getNamespaceDeclaration();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.NamespaceDeclaration#getPrefix <em>Prefix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see tefkat.engine.runtime.NamespaceDeclaration#getPrefix()
     * @see #getNamespaceDeclaration()
     * @generated
     */
    EAttribute getNamespaceDeclaration_Prefix();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.NamespaceDeclaration#getURI <em>URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>URI</em>'.
     * @see tefkat.engine.runtime.NamespaceDeclaration#getURI()
     * @see #getNamespaceDeclaration()
     * @generated
     */
    EAttribute getNamespaceDeclaration_URI();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.PatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Defn</em>'.
     * @see tefkat.engine.runtime.PatternDefn
     * @generated
     */
    EClass getPatternDefn();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.PatternDefn#getPatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Pattern Scope</em>'.
     * @see tefkat.engine.runtime.PatternDefn#getPatternScope()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_PatternScope();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.PatternDefn#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Term</em>'.
     * @see tefkat.engine.runtime.PatternDefn#getTerm()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_Term();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.PatternDefn#getParameterVar <em>Parameter Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Parameter Var</em>'.
     * @see tefkat.engine.runtime.PatternDefn#getParameterVar()
     * @see #getPatternDefn()
     * @generated
     */
    EReference getPatternDefn_ParameterVar();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.PatternDefn#isSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see tefkat.engine.runtime.PatternDefn#isSource()
     * @see #getPatternDefn()
     * @generated
     */
    EAttribute getPatternDefn_Source();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query</em>'.
     * @see tefkat.engine.runtime.Query
     * @generated
     */
    EClass getQuery();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.Query#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Term</em>'.
     * @see tefkat.engine.runtime.Query#getTerm()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_Term();

    /**
     * Returns the meta object for the reference list '{@link tefkat.engine.runtime.Query#getParameterVar <em>Parameter Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Parameter Var</em>'.
     * @see tefkat.engine.runtime.Query#getParameterVar()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_ParameterVar();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Term <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Term</em>'.
     * @see tefkat.engine.runtime.Term
     * @generated
     */
    EClass getTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.Term#getPatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Pattern Defn</em>'.
     * @see tefkat.engine.runtime.Term#getPatternDefn()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_PatternDefn();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.Term#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query</em>'.
     * @see tefkat.engine.runtime.Term#getQuery()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_Query();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.Term#getCompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Compound Term</em>'.
     * @see tefkat.engine.runtime.Term#getCompoundTerm()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_CompoundTerm();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.runtime.Term#getContext <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Context</em>'.
     * @see tefkat.engine.runtime.Term#getContext()
     * @see #getTerm()
     * @generated
     */
    EReference getTerm_Context();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.SourceTerm <em>Source Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Term</em>'.
     * @see tefkat.engine.runtime.SourceTerm
     * @generated
     */
    EClass getSourceTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.SourceTerm#getTRuleSrc <em>TRule Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>TRule Src</em>'.
     * @see tefkat.engine.runtime.SourceTerm#getTRuleSrc()
     * @see #getSourceTerm()
     * @generated
     */
    EReference getSourceTerm_TRuleSrc();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.TargetTerm <em>Target Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target Term</em>'.
     * @see tefkat.engine.runtime.TargetTerm
     * @generated
     */
    EClass getTargetTerm();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.TargetTerm#getTRuleTgt <em>TRule Tgt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>TRule Tgt</em>'.
     * @see tefkat.engine.runtime.TargetTerm#getTRuleTgt()
     * @see #getTargetTerm()
     * @generated
     */
    EReference getTargetTerm_TRuleTgt();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.CompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound Term</em>'.
     * @see tefkat.engine.runtime.CompoundTerm
     * @generated
     */
    EClass getCompoundTerm();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.CompoundTerm#getTerm <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Term</em>'.
     * @see tefkat.engine.runtime.CompoundTerm#getTerm()
     * @see #getCompoundTerm()
     * @generated
     */
    EReference getCompoundTerm_Term();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.SimpleTerm <em>Simple Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Term</em>'.
     * @see tefkat.engine.runtime.SimpleTerm
     * @generated
     */
    EClass getSimpleTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression</em>'.
     * @see tefkat.engine.runtime.Expression
     * @generated
     */
    EClass getExpression();

    /**
     * Returns the meta object for the container reference '{@link tefkat.engine.runtime.Expression#getExpr <em>Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Expr</em>'.
     * @see tefkat.engine.runtime.Expression#getExpr()
     * @see #getExpression()
     * @generated
     */
    EReference getExpression_Expr();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.InstanceRef <em>Instance Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance Ref</em>'.
     * @see tefkat.engine.runtime.InstanceRef
     * @generated
     */
    EClass getInstanceRef();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.runtime.InstanceRef#getObject <em>Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Object</em>'.
     * @see tefkat.engine.runtime.InstanceRef#getObject()
     * @see #getInstanceRef()
     * @generated
     */
    EReference getInstanceRef_Object();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.AndTerm <em>And Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>And Term</em>'.
     * @see tefkat.engine.runtime.AndTerm
     * @generated
     */
    EClass getAndTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.OrTerm <em>Or Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Or Term</em>'.
     * @see tefkat.engine.runtime.OrTerm
     * @generated
     */
    EClass getOrTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.NotTerm <em>Not Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Not Term</em>'.
     * @see tefkat.engine.runtime.NotTerm
     * @generated
     */
    EClass getNotTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.IfTerm <em>If Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>If Term</em>'.
     * @see tefkat.engine.runtime.IfTerm
     * @generated
     */
    EClass getIfTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.TrackingUse <em>Tracking Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tracking Use</em>'.
     * @see tefkat.engine.runtime.TrackingUse
     * @generated
     */
    EClass getTrackingUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.runtime.TrackingUse#getTracking <em>Tracking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Tracking</em>'.
     * @see tefkat.engine.runtime.TrackingUse#getTracking()
     * @see #getTrackingUse()
     * @generated
     */
    EReference getTrackingUse_Tracking();

    /**
     * Returns the meta object for the map '{@link tefkat.engine.runtime.TrackingUse#getFeatures <em>Features</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Features</em>'.
     * @see tefkat.engine.runtime.TrackingUse#getFeatures()
     * @see #getTrackingUse()
     * @generated
     */
    EReference getTrackingUse_Features();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.TrackingUse#getTrackingName <em>Tracking Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tracking Name</em>'.
     * @see tefkat.engine.runtime.TrackingUse#getTrackingName()
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
     *        valueType="tefkat.engine.runtime.Expression" valueContainment="true"
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
     * Returns the meta object for class '{@link tefkat.engine.runtime.PatternUse <em>Pattern Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Use</em>'.
     * @see tefkat.engine.runtime.PatternUse
     * @generated
     */
    EClass getPatternUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.runtime.PatternUse#getDefn <em>Defn</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Defn</em>'.
     * @see tefkat.engine.runtime.PatternUse#getDefn()
     * @see #getPatternUse()
     * @generated
     */
    EReference getPatternUse_Defn();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.PatternUse#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.engine.runtime.PatternUse#getArg()
     * @see #getPatternUse()
     * @generated
     */
    EReference getPatternUse_Arg();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.MofTerm <em>Mof Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Term</em>'.
     * @see tefkat.engine.runtime.MofTerm
     * @generated
     */
    EClass getMofTerm();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Condition</em>'.
     * @see tefkat.engine.runtime.Condition
     * @generated
     */
    EClass getCondition();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.Condition#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.engine.runtime.Condition#getArg()
     * @see #getCondition()
     * @generated
     */
    EReference getCondition_Arg();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.Condition#getRelation <em>Relation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relation</em>'.
     * @see tefkat.engine.runtime.Condition#getRelation()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Relation();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.MofInstance <em>Mof Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Instance</em>'.
     * @see tefkat.engine.runtime.MofInstance
     * @generated
     */
    EClass getMofInstance();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofInstance#getTypeName <em>Type Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Type Name</em>'.
     * @see tefkat.engine.runtime.MofInstance#getTypeName()
     * @see #getMofInstance()
     * @generated
     */
    EReference getMofInstance_TypeName();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofInstance#getInstance <em>Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Instance</em>'.
     * @see tefkat.engine.runtime.MofInstance#getInstance()
     * @see #getMofInstance()
     * @generated
     */
    EReference getMofInstance_Instance();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.MofInstance#isExact <em>Exact</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exact</em>'.
     * @see tefkat.engine.runtime.MofInstance#isExact()
     * @see #getMofInstance()
     * @generated
     */
    EAttribute getMofInstance_Exact();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.MofOrder <em>Mof Order</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mof Order</em>'.
     * @see tefkat.engine.runtime.MofOrder
     * @generated
     */
    EClass getMofOrder();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofOrder#getLesser <em>Lesser</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Lesser</em>'.
     * @see tefkat.engine.runtime.MofOrder#getLesser()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Lesser();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofOrder#getGreater <em>Greater</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Greater</em>'.
     * @see tefkat.engine.runtime.MofOrder#getGreater()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Greater();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofOrder#getInstance <em>Instance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Instance</em>'.
     * @see tefkat.engine.runtime.MofOrder#getInstance()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Instance();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.MofOrder#getFeature <em>Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Feature</em>'.
     * @see tefkat.engine.runtime.MofOrder#getFeature()
     * @see #getMofOrder()
     * @generated
     */
    EReference getMofOrder_Feature();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.CompoundExpr <em>Compound Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound Expr</em>'.
     * @see tefkat.engine.runtime.CompoundExpr
     * @generated
     */
    EClass getCompoundExpr();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.CompoundExpr#getArg <em>Arg</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arg</em>'.
     * @see tefkat.engine.runtime.CompoundExpr#getArg()
     * @see #getCompoundExpr()
     * @generated
     */
    EReference getCompoundExpr_Arg();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.SimpleExpr <em>Simple Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Expr</em>'.
     * @see tefkat.engine.runtime.SimpleExpr
     * @generated
     */
    EClass getSimpleExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.SimpleExpr#getRepresentation <em>Representation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Representation</em>'.
     * @see tefkat.engine.runtime.SimpleExpr#getRepresentation()
     * @see #getSimpleExpr()
     * @generated
     */
    EAttribute getSimpleExpr_Representation();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.VarUse <em>Var Use</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Var Use</em>'.
     * @see tefkat.engine.runtime.VarUse
     * @generated
     */
    EClass getVarUse();

    /**
     * Returns the meta object for the reference '{@link tefkat.engine.runtime.VarUse#getVar <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Var</em>'.
     * @see tefkat.engine.runtime.VarUse#getVar()
     * @see #getVarUse()
     * @generated
     */
    EReference getVarUse_Var();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.CollectionExpr <em>Collection Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Collection Expr</em>'.
     * @see tefkat.engine.runtime.CollectionExpr
     * @generated
     */
    EClass getCollectionExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.CollectionExpr#isUnique <em>Unique</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unique</em>'.
     * @see tefkat.engine.runtime.CollectionExpr#isUnique()
     * @see #getCollectionExpr()
     * @generated
     */
    EAttribute getCollectionExpr_Unique();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.CollectionExpr#isOrdered <em>Ordered</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ordered</em>'.
     * @see tefkat.engine.runtime.CollectionExpr#isOrdered()
     * @see #getCollectionExpr()
     * @generated
     */
    EAttribute getCollectionExpr_Ordered();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.FunctionExpr <em>Function Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Expr</em>'.
     * @see tefkat.engine.runtime.FunctionExpr
     * @generated
     */
    EClass getFunctionExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.FunctionExpr#getFunction <em>Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Function</em>'.
     * @see tefkat.engine.runtime.FunctionExpr#getFunction()
     * @see #getFunctionExpr()
     * @generated
     */
    EAttribute getFunctionExpr_Function();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.FeatureExpr <em>Feature Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Expr</em>'.
     * @see tefkat.engine.runtime.FeatureExpr
     * @generated
     */
    EClass getFeatureExpr();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.FeatureExpr#isOperation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation</em>'.
     * @see tefkat.engine.runtime.FeatureExpr#isOperation()
     * @see #getFeatureExpr()
     * @generated
     */
    EAttribute getFeatureExpr_Operation();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.FeatureExpr#isCollect <em>Collect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Collect</em>'.
     * @see tefkat.engine.runtime.FeatureExpr#isCollect()
     * @see #getFeatureExpr()
     * @generated
     */
    EAttribute getFeatureExpr_Collect();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.FeatureExpr#getFeature <em>Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Feature</em>'.
     * @see tefkat.engine.runtime.FeatureExpr#getFeature()
     * @see #getFeatureExpr()
     * @generated
     */
    EReference getFeatureExpr_Feature();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.StringConstant <em>String Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Constant</em>'.
     * @see tefkat.engine.runtime.StringConstant
     * @generated
     */
    EClass getStringConstant();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.IntConstant <em>Int Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Constant</em>'.
     * @see tefkat.engine.runtime.IntConstant
     * @generated
     */
    EClass getIntConstant();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.RealConstant <em>Real Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Real Constant</em>'.
     * @see tefkat.engine.runtime.RealConstant
     * @generated
     */
    EClass getRealConstant();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.BooleanConstant <em>Boolean Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean Constant</em>'.
     * @see tefkat.engine.runtime.BooleanConstant
     * @generated
     */
    EClass getBooleanConstant();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.EnumConstant <em>Enum Constant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Constant</em>'.
     * @see tefkat.engine.runtime.EnumConstant
     * @generated
     */
    EClass getEnumConstant();

    /**
     * Returns the meta object for class '{@link tefkat.engine.runtime.Injection <em>Injection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Injection</em>'.
     * @see tefkat.engine.runtime.Injection
     * @generated
     */
    EClass getInjection();

    /**
     * Returns the meta object for the attribute '{@link tefkat.engine.runtime.Injection#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see tefkat.engine.runtime.Injection#getName()
     * @see #getInjection()
     * @generated
     */
    EAttribute getInjection_Name();

    /**
     * Returns the meta object for the containment reference list '{@link tefkat.engine.runtime.Injection#getSources <em>Sources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sources</em>'.
     * @see tefkat.engine.runtime.Injection#getSources()
     * @see #getInjection()
     * @generated
     */
    EReference getInjection_Sources();

    /**
     * Returns the meta object for the containment reference '{@link tefkat.engine.runtime.Injection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see tefkat.engine.runtime.Injection#getTarget()
     * @see #getInjection()
     * @generated
     */
    EReference getInjection_Target();

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
     * Returns the meta object for data type '{@link tefkat.engine.runtime.Context <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Context</em>'.
     * @see tefkat.engine.runtime.Context
     * @model instanceClass="tefkat.engine.runtime.Context"
     * @generated
     */
    EDataType getContext();

    /**
     * Returns the meta object for data type '{@link tefkat.engine.runtime.TefkatException <em>Tefkat Exception</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Tefkat Exception</em>'.
     * @see tefkat.engine.runtime.TefkatException
     * @model instanceClass="tefkat.engine.runtime.TefkatException"
     * @generated
     */
    EDataType getTefkatException();

    /**
     * Returns the meta object for data type '{@link tefkat.engine.runtime.Binding <em>Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Binding</em>'.
     * @see tefkat.engine.runtime.Binding
     * @model instanceClass="tefkat.engine.runtime.Binding"
     * @generated
     */
    EDataType getBinding();

    /**
     * Returns the meta object for data type '{@link tefkat.engine.runtime.ResolutionException <em>Resolution Exception</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Resolution Exception</em>'.
     * @see tefkat.engine.runtime.ResolutionException
     * @model instanceClass="tefkat.engine.runtime.ResolutionException"
     * @generated
     */
    EDataType getResolutionException();

    /**
     * Returns the meta object for data type '{@link tefkat.engine.runtime.NotGroundException <em>Not Ground Exception</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Not Ground Exception</em>'.
     * @see tefkat.engine.runtime.NotGroundException
     * @model instanceClass="tefkat.engine.runtime.NotGroundException"
     * @generated
     */
    EDataType getNotGroundException();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RuntimeFactory getRuntimeFactory();

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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.ExtentImpl <em>Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.ExtentImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getExtent()
         * @generated
         */
        EClass EXTENT = eINSTANCE.getExtent();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.ContainerExtentImpl <em>Container Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.ContainerExtentImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getContainerExtent()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.ReferenceExtentImpl <em>Reference Extent</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.ReferenceExtentImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getReferenceExtent()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.VarImpl <em>Var</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.VarImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVar()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.VarScopeImpl <em>Var Scope</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.VarScopeImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVarScope()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.PatternScopeImpl <em>Pattern Scope</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.PatternScopeImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternScope()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.TRuleImpl <em>TRule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.TRuleImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTRule()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.TransformationImpl <em>Transformation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.TransformationImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTransformation()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.NamespaceDeclarationImpl <em>Namespace Declaration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.NamespaceDeclarationImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNamespaceDeclaration()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.PatternDefnImpl <em>Pattern Defn</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.PatternDefnImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternDefn()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.QueryImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getQuery()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.TermImpl <em>Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.TermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTerm()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.SourceTermImpl <em>Source Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.SourceTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSourceTerm()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.TargetTermImpl <em>Target Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.TargetTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTargetTerm()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.CompoundTermImpl <em>Compound Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.CompoundTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCompoundTerm()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.SimpleTermImpl <em>Simple Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.SimpleTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSimpleTerm()
         * @generated
         */
        EClass SIMPLE_TERM = eINSTANCE.getSimpleTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.ExpressionImpl <em>Expression</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.ExpressionImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getExpression()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.InstanceRefImpl <em>Instance Ref</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.InstanceRefImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getInstanceRef()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.AndTermImpl <em>And Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.AndTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getAndTerm()
         * @generated
         */
        EClass AND_TERM = eINSTANCE.getAndTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.OrTermImpl <em>Or Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.OrTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getOrTerm()
         * @generated
         */
        EClass OR_TERM = eINSTANCE.getOrTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.NotTermImpl <em>Not Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.NotTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNotTerm()
         * @generated
         */
        EClass NOT_TERM = eINSTANCE.getNotTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.IfTermImpl <em>If Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.IfTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getIfTerm()
         * @generated
         */
        EClass IF_TERM = eINSTANCE.getIfTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.TrackingUseImpl <em>Tracking Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.TrackingUseImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTrackingUse()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.FeatureValuePairImpl <em>Feature Value Pair</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.FeatureValuePairImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFeatureValuePair()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.PatternUseImpl <em>Pattern Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.PatternUseImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getPatternUse()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.MofTermImpl <em>Mof Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.MofTermImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofTerm()
         * @generated
         */
        EClass MOF_TERM = eINSTANCE.getMofTerm();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.ConditionImpl <em>Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.ConditionImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCondition()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.MofInstanceImpl <em>Mof Instance</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.MofInstanceImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofInstance()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.MofOrderImpl <em>Mof Order</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.MofOrderImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getMofOrder()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.CompoundExprImpl <em>Compound Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.CompoundExprImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCompoundExpr()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.SimpleExprImpl <em>Simple Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.SimpleExprImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getSimpleExpr()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.VarUseImpl <em>Var Use</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.VarUseImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getVarUse()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.CollectionExprImpl <em>Collection Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.CollectionExprImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCollectionExpr()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.FunctionExprImpl <em>Function Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.FunctionExprImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFunctionExpr()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.FeatureExprImpl <em>Feature Expr</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.FeatureExprImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getFeatureExpr()
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
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.StringConstantImpl <em>String Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.StringConstantImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getStringConstant()
         * @generated
         */
        EClass STRING_CONSTANT = eINSTANCE.getStringConstant();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.IntConstantImpl <em>Int Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.IntConstantImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getIntConstant()
         * @generated
         */
        EClass INT_CONSTANT = eINSTANCE.getIntConstant();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.RealConstantImpl <em>Real Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.RealConstantImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getRealConstant()
         * @generated
         */
        EClass REAL_CONSTANT = eINSTANCE.getRealConstant();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.BooleanConstantImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getBooleanConstant()
         * @generated
         */
        EClass BOOLEAN_CONSTANT = eINSTANCE.getBooleanConstant();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.EnumConstantImpl <em>Enum Constant</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.EnumConstantImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getEnumConstant()
         * @generated
         */
        EClass ENUM_CONSTANT = eINSTANCE.getEnumConstant();

        /**
         * The meta object literal for the '{@link tefkat.engine.runtime.impl.InjectionImpl <em>Injection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.InjectionImpl
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getInjection()
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
         * The meta object literal for the '<em>Collection</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.Collection
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getCollection()
         * @generated
         */
        EDataType COLLECTION = eINSTANCE.getCollection();

        /**
         * The meta object literal for the '<em>List</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.util.List
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getList()
         * @generated
         */
        EDataType LIST = eINSTANCE.getList();

        /**
         * The meta object literal for the '<em>List Array</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getListArray()
         * @generated
         */
        EDataType LIST_ARRAY = eINSTANCE.getListArray();

        /**
         * The meta object literal for the '<em>Context</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.Context
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getContext()
         * @generated
         */
        EDataType CONTEXT = eINSTANCE.getContext();

        /**
         * The meta object literal for the '<em>Tefkat Exception</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.TefkatException
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getTefkatException()
         * @generated
         */
        EDataType TEFKAT_EXCEPTION = eINSTANCE.getTefkatException();

        /**
         * The meta object literal for the '<em>Binding</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.Binding
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getBinding()
         * @generated
         */
        EDataType BINDING = eINSTANCE.getBinding();

        /**
         * The meta object literal for the '<em>Resolution Exception</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.ResolutionException
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getResolutionException()
         * @generated
         */
        EDataType RESOLUTION_EXCEPTION = eINSTANCE.getResolutionException();

        /**
         * The meta object literal for the '<em>Not Ground Exception</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see tefkat.engine.runtime.NotGroundException
         * @see tefkat.engine.runtime.impl.RuntimePackageImpl#getNotGroundException()
         * @generated
         */
        EDataType NOT_GROUND_EXCEPTION = eINSTANCE.getNotGroundException();

    }

} //RuntimePackage
