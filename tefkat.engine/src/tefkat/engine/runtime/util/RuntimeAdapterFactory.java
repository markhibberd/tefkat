/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tefkat.engine.runtime.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tefkat.engine.runtime.RuntimePackage
 * @generated
 */
public class RuntimeAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static RuntimePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = RuntimePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch the delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RuntimeSwitch modelSwitch =
        new RuntimeSwitch() {
            public Object caseExtent(Extent object) {
                return createExtentAdapter();
            }
            public Object caseContainerExtent(ContainerExtent object) {
                return createContainerExtentAdapter();
            }
            public Object caseReferenceExtent(ReferenceExtent object) {
                return createReferenceExtentAdapter();
            }
            public Object caseVar(Var object) {
                return createVarAdapter();
            }
            public Object caseVarScope(VarScope object) {
                return createVarScopeAdapter();
            }
            public Object casePatternScope(PatternScope object) {
                return createPatternScopeAdapter();
            }
            public Object caseTRule(TRule object) {
                return createTRuleAdapter();
            }
            public Object caseTransformation(Transformation object) {
                return createTransformationAdapter();
            }
            public Object caseNamespaceDeclaration(NamespaceDeclaration object) {
                return createNamespaceDeclarationAdapter();
            }
            public Object casePatternDefn(PatternDefn object) {
                return createPatternDefnAdapter();
            }
            public Object caseQuery(Query object) {
                return createQueryAdapter();
            }
            public Object caseTerm(Term object) {
                return createTermAdapter();
            }
            public Object caseSourceTerm(SourceTerm object) {
                return createSourceTermAdapter();
            }
            public Object caseTargetTerm(TargetTerm object) {
                return createTargetTermAdapter();
            }
            public Object caseCompoundTerm(CompoundTerm object) {
                return createCompoundTermAdapter();
            }
            public Object caseSimpleTerm(SimpleTerm object) {
                return createSimpleTermAdapter();
            }
            public Object caseExpression(Expression object) {
                return createExpressionAdapter();
            }
            public Object caseInstanceRef(InstanceRef object) {
                return createInstanceRefAdapter();
            }
            public Object caseAndTerm(AndTerm object) {
                return createAndTermAdapter();
            }
            public Object caseOrTerm(OrTerm object) {
                return createOrTermAdapter();
            }
            public Object caseNotTerm(NotTerm object) {
                return createNotTermAdapter();
            }
            public Object caseIfTerm(IfTerm object) {
                return createIfTermAdapter();
            }
            public Object caseTrackingUse(TrackingUse object) {
                return createTrackingUseAdapter();
            }
            public Object caseFeatureValuePair(Map.Entry object) {
                return createFeatureValuePairAdapter();
            }
            public Object casePatternUse(PatternUse object) {
                return createPatternUseAdapter();
            }
            public Object caseMofTerm(MofTerm object) {
                return createMofTermAdapter();
            }
            public Object caseCondition(Condition object) {
                return createConditionAdapter();
            }
            public Object caseMofInstance(MofInstance object) {
                return createMofInstanceAdapter();
            }
            public Object caseMofOrder(MofOrder object) {
                return createMofOrderAdapter();
            }
            public Object caseCompoundExpr(CompoundExpr object) {
                return createCompoundExprAdapter();
            }
            public Object caseSimpleExpr(SimpleExpr object) {
                return createSimpleExprAdapter();
            }
            public Object caseVarUse(VarUse object) {
                return createVarUseAdapter();
            }
            public Object caseCollectionExpr(CollectionExpr object) {
                return createCollectionExprAdapter();
            }
            public Object caseFunctionExpr(FunctionExpr object) {
                return createFunctionExprAdapter();
            }
            public Object caseFeatureExpr(FeatureExpr object) {
                return createFeatureExprAdapter();
            }
            public Object caseStringConstant(StringConstant object) {
                return createStringConstantAdapter();
            }
            public Object caseIntConstant(IntConstant object) {
                return createIntConstantAdapter();
            }
            public Object caseRealConstant(RealConstant object) {
                return createRealConstantAdapter();
            }
            public Object caseBooleanConstant(BooleanConstant object) {
                return createBooleanConstantAdapter();
            }
            public Object caseEnumConstant(EnumConstant object) {
                return createEnumConstantAdapter();
            }
            public Object caseInjection(Injection object) {
                return createInjectionAdapter();
            }
            public Object defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    public Adapter createAdapter(Notifier target) {
        return (Adapter)modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Extent <em>Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Extent
     * @generated
     */
    public Adapter createExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.ContainerExtent <em>Container Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.ContainerExtent
     * @generated
     */
    public Adapter createContainerExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.ReferenceExtent <em>Reference Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.ReferenceExtent
     * @generated
     */
    public Adapter createReferenceExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Var <em>Var</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Var
     * @generated
     */
    public Adapter createVarAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.VarScope <em>Var Scope</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.VarScope
     * @generated
     */
    public Adapter createVarScopeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.PatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.PatternScope
     * @generated
     */
    public Adapter createPatternScopeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.TRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.TRule
     * @generated
     */
    public Adapter createTRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Transformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Transformation
     * @generated
     */
    public Adapter createTransformationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.NamespaceDeclaration <em>Namespace Declaration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.NamespaceDeclaration
     * @generated
     */
    public Adapter createNamespaceDeclarationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.PatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.PatternDefn
     * @generated
     */
    public Adapter createPatternDefnAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Query
     * @generated
     */
    public Adapter createQueryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Term <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Term
     * @generated
     */
    public Adapter createTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.SourceTerm <em>Source Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.SourceTerm
     * @generated
     */
    public Adapter createSourceTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.TargetTerm <em>Target Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.TargetTerm
     * @generated
     */
    public Adapter createTargetTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.CompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.CompoundTerm
     * @generated
     */
    public Adapter createCompoundTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.SimpleTerm <em>Simple Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.SimpleTerm
     * @generated
     */
    public Adapter createSimpleTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Expression
     * @generated
     */
    public Adapter createExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.InstanceRef <em>Instance Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.InstanceRef
     * @generated
     */
    public Adapter createInstanceRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.AndTerm <em>And Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.AndTerm
     * @generated
     */
    public Adapter createAndTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.OrTerm <em>Or Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.OrTerm
     * @generated
     */
    public Adapter createOrTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.NotTerm <em>Not Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.NotTerm
     * @generated
     */
    public Adapter createNotTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.IfTerm <em>If Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.IfTerm
     * @generated
     */
    public Adapter createIfTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.TrackingUse <em>Tracking Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.TrackingUse
     * @generated
     */
    public Adapter createTrackingUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Feature Value Pair</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createFeatureValuePairAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.PatternUse <em>Pattern Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.PatternUse
     * @generated
     */
    public Adapter createPatternUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.MofTerm <em>Mof Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.MofTerm
     * @generated
     */
    public Adapter createMofTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Condition
     * @generated
     */
    public Adapter createConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.MofInstance <em>Mof Instance</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.MofInstance
     * @generated
     */
    public Adapter createMofInstanceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.MofOrder <em>Mof Order</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.MofOrder
     * @generated
     */
    public Adapter createMofOrderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.CompoundExpr <em>Compound Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.CompoundExpr
     * @generated
     */
    public Adapter createCompoundExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.SimpleExpr <em>Simple Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.SimpleExpr
     * @generated
     */
    public Adapter createSimpleExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.VarUse <em>Var Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.VarUse
     * @generated
     */
    public Adapter createVarUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.CollectionExpr <em>Collection Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.CollectionExpr
     * @generated
     */
    public Adapter createCollectionExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.FunctionExpr <em>Function Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.FunctionExpr
     * @generated
     */
    public Adapter createFunctionExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.FeatureExpr <em>Feature Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.FeatureExpr
     * @generated
     */
    public Adapter createFeatureExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.StringConstant <em>String Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.StringConstant
     * @generated
     */
    public Adapter createStringConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.IntConstant <em>Int Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.IntConstant
     * @generated
     */
    public Adapter createIntConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.RealConstant <em>Real Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.RealConstant
     * @generated
     */
    public Adapter createRealConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.BooleanConstant <em>Boolean Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.BooleanConstant
     * @generated
     */
    public Adapter createBooleanConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.EnumConstant <em>Enum Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.EnumConstant
     * @generated
     */
    public Adapter createEnumConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.engine.runtime.Injection <em>Injection</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.engine.runtime.Injection
     * @generated
     */
    public Adapter createInjectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //RuntimeAdapterFactory
