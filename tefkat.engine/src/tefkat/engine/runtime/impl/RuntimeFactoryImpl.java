/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tefkat.engine.runtime.*;

import tefkat.model.TefkatException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RuntimeFactoryImpl extends EFactoryImpl implements RuntimeFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static RuntimeFactory init() {
        try {
            RuntimeFactory theRuntimeFactory = (RuntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http:///TefkatRuntime-1.0.ecore"); 
            if (theRuntimeFactory != null) {
                return theRuntimeFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new RuntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case RuntimePackage.CONTAINER_EXTENT: return createContainerExtent();
            case RuntimePackage.REFERENCE_EXTENT: return createReferenceExtent();
            case RuntimePackage.VAR: return createVar();
            case RuntimePackage.VAR_SCOPE: return createVarScope();
            case RuntimePackage.TRULE: return createTRule();
            case RuntimePackage.TRANSFORMATION: return createTransformation();
            case RuntimePackage.NAMESPACE_DECLARATION: return createNamespaceDeclaration();
            case RuntimePackage.PATTERN_DEFN: return createPatternDefn();
            case RuntimePackage.QUERY: return createQuery();
            case RuntimePackage.INSTANCE_REF: return createInstanceRef();
            case RuntimePackage.AND_TERM: return createAndTerm();
            case RuntimePackage.OR_TERM: return createOrTerm();
            case RuntimePackage.NOT_TERM: return createNotTerm();
            case RuntimePackage.IF_TERM: return createIfTerm();
            case RuntimePackage.TRACKING_USE: return createTrackingUse();
            case RuntimePackage.FEATURE_VALUE_PAIR: return (EObject)createFeatureValuePair();
            case RuntimePackage.PATTERN_USE: return createPatternUse();
            case RuntimePackage.CONDITION: return createCondition();
            case RuntimePackage.MOF_INSTANCE: return createMofInstance();
            case RuntimePackage.MOF_ORDER: return createMofOrder();
            case RuntimePackage.VAR_USE: return createVarUse();
            case RuntimePackage.COLLECTION_EXPR: return createCollectionExpr();
            case RuntimePackage.FUNCTION_EXPR: return createFunctionExpr();
            case RuntimePackage.FEATURE_EXPR: return createFeatureExpr();
            case RuntimePackage.STRING_CONSTANT: return createStringConstant();
            case RuntimePackage.INT_CONSTANT: return createIntConstant();
            case RuntimePackage.REAL_CONSTANT: return createRealConstant();
            case RuntimePackage.BOOLEAN_CONSTANT: return createBooleanConstant();
            case RuntimePackage.ENUM_CONSTANT: return createEnumConstant();
            case RuntimePackage.INJECTION: return createInjection();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case RuntimePackage.COLLECTION:
                return createCollectionFromString(eDataType, initialValue);
            case RuntimePackage.LIST:
                return createListFromString(eDataType, initialValue);
            case RuntimePackage.LIST_ARRAY:
                return createListArrayFromString(eDataType, initialValue);
            case RuntimePackage.CONTEXT:
                return createContextFromString(eDataType, initialValue);
            case RuntimePackage.TEFKAT_EXCEPTION:
                return createTefkatExceptionFromString(eDataType, initialValue);
            case RuntimePackage.BINDING:
                return createBindingFromString(eDataType, initialValue);
            case RuntimePackage.RESOLUTION_EXCEPTION:
                return createResolutionExceptionFromString(eDataType, initialValue);
            case RuntimePackage.NOT_GROUND_EXCEPTION:
                return createNotGroundExceptionFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case RuntimePackage.COLLECTION:
                return convertCollectionToString(eDataType, instanceValue);
            case RuntimePackage.LIST:
                return convertListToString(eDataType, instanceValue);
            case RuntimePackage.LIST_ARRAY:
                return convertListArrayToString(eDataType, instanceValue);
            case RuntimePackage.CONTEXT:
                return convertContextToString(eDataType, instanceValue);
            case RuntimePackage.TEFKAT_EXCEPTION:
                return convertTefkatExceptionToString(eDataType, instanceValue);
            case RuntimePackage.BINDING:
                return convertBindingToString(eDataType, instanceValue);
            case RuntimePackage.RESOLUTION_EXCEPTION:
                return convertResolutionExceptionToString(eDataType, instanceValue);
            case RuntimePackage.NOT_GROUND_EXCEPTION:
                return convertNotGroundExceptionToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ContainerExtent createContainerExtent() {
        ContainerExtentImpl containerExtent = new ContainerExtentImpl();
        return containerExtent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReferenceExtent createReferenceExtent() {
        ReferenceExtentImpl referenceExtent = new ReferenceExtentImpl();
        return referenceExtent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Var createVar() {
        VarImpl var = new VarImpl();
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarScope createVarScope() {
        VarScopeImpl varScope = new VarScopeImpl();
        return varScope;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRule createTRule() {
        TRuleImpl tRule = new TRuleImpl();
        return tRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transformation createTransformation() {
        TransformationImpl transformation = new TransformationImpl();
        return transformation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamespaceDeclaration createNamespaceDeclaration() {
        NamespaceDeclarationImpl namespaceDeclaration = new NamespaceDeclarationImpl();
        return namespaceDeclaration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternDefn createPatternDefn() {
        PatternDefnImpl patternDefn = new PatternDefnImpl();
        return patternDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Query createQuery() {
        QueryImpl query = new QueryImpl();
        return query;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InstanceRef createInstanceRef() {
        InstanceRefImpl instanceRef = new InstanceRefImpl();
        return instanceRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AndTerm createAndTerm() {
        AndTermImpl andTerm = new AndTermImpl();
        return andTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrTerm createOrTerm() {
        OrTermImpl orTerm = new OrTermImpl();
        return orTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotTerm createNotTerm() {
        NotTermImpl notTerm = new NotTermImpl();
        return notTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IfTerm createIfTerm() {
        IfTermImpl ifTerm = new IfTermImpl();
        return ifTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TrackingUse createTrackingUse() {
        TrackingUseImpl trackingUse = new TrackingUseImpl();
        return trackingUse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry createFeatureValuePair() {
        FeatureValuePairImpl featureValuePair = new FeatureValuePairImpl();
        return featureValuePair;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternUse createPatternUse() {
        PatternUseImpl patternUse = new PatternUseImpl();
        return patternUse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Condition createCondition() {
        ConditionImpl condition = new ConditionImpl();
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MofInstance createMofInstance() {
        MofInstanceImpl mofInstance = new MofInstanceImpl();
        return mofInstance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MofOrder createMofOrder() {
        MofOrderImpl mofOrder = new MofOrderImpl();
        return mofOrder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarUse createVarUse() {
        VarUseImpl varUse = new VarUseImpl();
        return varUse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CollectionExpr createCollectionExpr() {
        CollectionExprImpl collectionExpr = new CollectionExprImpl();
        return collectionExpr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionExpr createFunctionExpr() {
        FunctionExprImpl functionExpr = new FunctionExprImpl();
        return functionExpr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureExpr createFeatureExpr() {
        FeatureExprImpl featureExpr = new FeatureExprImpl();
        return featureExpr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StringConstant createStringConstant() {
        StringConstantImpl stringConstant = new StringConstantImpl();
        return stringConstant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntConstant createIntConstant() {
        IntConstantImpl intConstant = new IntConstantImpl();
        return intConstant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RealConstant createRealConstant() {
        RealConstantImpl realConstant = new RealConstantImpl();
        return realConstant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanConstant createBooleanConstant() {
        BooleanConstantImpl booleanConstant = new BooleanConstantImpl();
        return booleanConstant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EnumConstant createEnumConstant() {
        EnumConstantImpl enumConstant = new EnumConstantImpl();
        return enumConstant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Injection createInjection() {
        InjectionImpl injection = new InjectionImpl();
        return injection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Collection createCollectionFromString(EDataType eDataType, String initialValue) {
        return (Collection)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertCollectionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List createListFromString(EDataType eDataType, String initialValue) {
        return (List)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertListToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List[] createListArrayFromString(EDataType eDataType, String initialValue) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertListArrayToString(EDataType eDataType, Object instanceValue) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Context createContextFromString(EDataType eDataType, String initialValue) {
        return (Context)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertContextToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TefkatException createTefkatExceptionFromString(EDataType eDataType, String initialValue) {
        return (TefkatException)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTefkatExceptionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Binding createBindingFromString(EDataType eDataType, String initialValue) {
        return (Binding)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBindingToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResolutionException createResolutionExceptionFromString(EDataType eDataType, String initialValue) {
        return (ResolutionException)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertResolutionExceptionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotGroundException createNotGroundExceptionFromString(EDataType eDataType, String initialValue) {
        return (NotGroundException)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNotGroundExceptionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimePackage getRuntimePackage() {
        return (RuntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static RuntimePackage getPackage() {
        return RuntimePackage.eINSTANCE;
    }

} //RuntimeFactoryImpl
