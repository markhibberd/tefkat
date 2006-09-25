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
package tefkat.model.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tefkat.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TefkatFactoryImpl extends EFactoryImpl implements TefkatFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TefkatFactory init() {
        try {
            TefkatFactory theTefkatFactory = (TefkatFactory)EPackage.Registry.INSTANCE.getEFactory("http:///TefkatModel-2.5.ecore"); 
            if (theTefkatFactory != null) {
                return theTefkatFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TefkatFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TefkatFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TefkatPackage.CONTAINER_EXTENT: return createContainerExtent();
            case TefkatPackage.REFERENCE_EXTENT: return createReferenceExtent();
            case TefkatPackage.ABSTRACT_VAR: return createAbstractVar();
            case TefkatPackage.VAR_SCOPE: return createVarScope();
            case TefkatPackage.TRULE: return createTRule();
            case TefkatPackage.TRANSFORMATION: return createTransformation();
            case TefkatPackage.NAMESPACE_DECLARATION: return createNamespaceDeclaration();
            case TefkatPackage.PATTERN_DEFN: return createPatternDefn();
            case TefkatPackage.QUERY: return createQuery();
            case TefkatPackage.INSTANCE_REF: return createInstanceRef();
            case TefkatPackage.AND_TERM: return createAndTerm();
            case TefkatPackage.OR_TERM: return createOrTerm();
            case TefkatPackage.NOT_TERM: return createNotTerm();
            case TefkatPackage.IF_TERM: return createIfTerm();
            case TefkatPackage.TRACKING_USE: return createTrackingUse();
            case TefkatPackage.FEATURE_VALUE_PAIR: return (EObject)createFeatureValuePair();
            case TefkatPackage.PATTERN_USE: return createPatternUse();
            case TefkatPackage.CONDITION: return createCondition();
            case TefkatPackage.MOF_INSTANCE: return createMofInstance();
            case TefkatPackage.MOF_ORDER: return createMofOrder();
            case TefkatPackage.VAR_USE: return createVarUse();
            case TefkatPackage.COLLECTION_EXPR: return createCollectionExpr();
            case TefkatPackage.FUNCTION_EXPR: return createFunctionExpr();
            case TefkatPackage.FEATURE_EXPR: return createFeatureExpr();
            case TefkatPackage.STRING_CONSTANT: return createStringConstant();
            case TefkatPackage.INT_CONSTANT: return createIntConstant();
            case TefkatPackage.REAL_CONSTANT: return createRealConstant();
            case TefkatPackage.BOOLEAN_CONSTANT: return createBooleanConstant();
            case TefkatPackage.ENUM_CONSTANT: return createEnumConstant();
            case TefkatPackage.INJECTION: return createInjection();
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
            case TefkatPackage.COLLECTION:
                return createCollectionFromString(eDataType, initialValue);
            case TefkatPackage.LIST:
                return createListFromString(eDataType, initialValue);
            case TefkatPackage.LIST_ARRAY:
                return createListArrayFromString(eDataType, initialValue);
            case TefkatPackage.TEFKAT_EXCEPTION:
                return createTefkatExceptionFromString(eDataType, initialValue);
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
            case TefkatPackage.COLLECTION:
                return convertCollectionToString(eDataType, instanceValue);
            case TefkatPackage.LIST:
                return convertListToString(eDataType, instanceValue);
            case TefkatPackage.LIST_ARRAY:
                return convertListArrayToString(eDataType, instanceValue);
            case TefkatPackage.TEFKAT_EXCEPTION:
                return convertTefkatExceptionToString(eDataType, instanceValue);
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
    public AbstractVar createAbstractVar() {
        AbstractVarImpl abstractVar = new AbstractVarImpl();
        return abstractVar;
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
    public NamespaceDeclaration createNamespaceDeclaration() {
        NamespaceDeclarationImpl namespaceDeclaration = new NamespaceDeclarationImpl();
        return namespaceDeclaration;
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
    public TefkatPackage getTefkatPackage() {
        return (TefkatPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static TefkatPackage getPackage() {
        return TefkatPackage.eINSTANCE;
    }

} //TefkatFactoryImpl
