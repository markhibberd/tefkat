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
package tefkat.model.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tefkat.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tefkat.model.TefkatPackage
 * @generated
 */
public class TefkatAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TefkatPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TefkatAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TefkatPackage.eINSTANCE;
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
    protected TefkatSwitch modelSwitch =
        new TefkatSwitch() {
            public Object caseExtent(Extent object) {
                return createExtentAdapter();
            }
            public Object caseContainerExtent(ContainerExtent object) {
                return createContainerExtentAdapter();
            }
            public Object caseReferenceExtent(ReferenceExtent object) {
                return createReferenceExtentAdapter();
            }
            public Object caseAbstractVar(AbstractVar object) {
                return createAbstractVarAdapter();
            }
            public Object casePatternVar(PatternVar object) {
                return createPatternVarAdapter();
            }
            public Object caseTRuleVar(TRuleVar object) {
                return createTRuleVarAdapter();
            }
            public Object caseExtentVar(ExtentVar object) {
                return createExtentVarAdapter();
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
     * Creates a new adapter for an object of class '{@link tefkat.model.Extent <em>Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Extent
     * @generated
     */
    public Adapter createExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.ContainerExtent <em>Container Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.ContainerExtent
     * @generated
     */
    public Adapter createContainerExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.ReferenceExtent <em>Reference Extent</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.ReferenceExtent
     * @generated
     */
    public Adapter createReferenceExtentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.AbstractVar <em>Abstract Var</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.AbstractVar
     * @generated
     */
    public Adapter createAbstractVarAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.PatternVar <em>Pattern Var</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.PatternVar
     * @generated
     */
    public Adapter createPatternVarAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.TRuleVar <em>TRule Var</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.TRuleVar
     * @generated
     */
    public Adapter createTRuleVarAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.ExtentVar <em>Extent Var</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.ExtentVar
     * @generated
     */
    public Adapter createExtentVarAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.VarScope <em>Var Scope</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.VarScope
     * @generated
     */
    public Adapter createVarScopeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.PatternScope <em>Pattern Scope</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.PatternScope
     * @generated
     */
    public Adapter createPatternScopeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.TRule <em>TRule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.TRule
     * @generated
     */
    public Adapter createTRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Transformation <em>Transformation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Transformation
     * @generated
     */
    public Adapter createTransformationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.PatternDefn <em>Pattern Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.PatternDefn
     * @generated
     */
    public Adapter createPatternDefnAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Query
     * @generated
     */
    public Adapter createQueryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Term <em>Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Term
     * @generated
     */
    public Adapter createTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.SourceTerm <em>Source Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.SourceTerm
     * @generated
     */
    public Adapter createSourceTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.TargetTerm <em>Target Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.TargetTerm
     * @generated
     */
    public Adapter createTargetTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.CompoundTerm <em>Compound Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.CompoundTerm
     * @generated
     */
    public Adapter createCompoundTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.SimpleTerm <em>Simple Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.SimpleTerm
     * @generated
     */
    public Adapter createSimpleTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Expression
     * @generated
     */
    public Adapter createExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.InstanceRef <em>Instance Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.InstanceRef
     * @generated
     */
    public Adapter createInstanceRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.AndTerm <em>And Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.AndTerm
     * @generated
     */
    public Adapter createAndTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.OrTerm <em>Or Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.OrTerm
     * @generated
     */
    public Adapter createOrTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.NotTerm <em>Not Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.NotTerm
     * @generated
     */
    public Adapter createNotTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.IfTerm <em>If Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.IfTerm
     * @generated
     */
    public Adapter createIfTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.TrackingUse <em>Tracking Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.TrackingUse
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
     * Creates a new adapter for an object of class '{@link tefkat.model.PatternUse <em>Pattern Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.PatternUse
     * @generated
     */
    public Adapter createPatternUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.MofTerm <em>Mof Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.MofTerm
     * @generated
     */
    public Adapter createMofTermAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Condition
     * @generated
     */
    public Adapter createConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.MofInstance <em>Mof Instance</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.MofInstance
     * @generated
     */
    public Adapter createMofInstanceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.MofOrder <em>Mof Order</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.MofOrder
     * @generated
     */
    public Adapter createMofOrderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.CompoundExpr <em>Compound Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.CompoundExpr
     * @generated
     */
    public Adapter createCompoundExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.SimpleExpr <em>Simple Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.SimpleExpr
     * @generated
     */
    public Adapter createSimpleExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.VarUse <em>Var Use</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.VarUse
     * @generated
     */
    public Adapter createVarUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.CollectionExpr <em>Collection Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.CollectionExpr
     * @generated
     */
    public Adapter createCollectionExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.FunctionExpr <em>Function Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.FunctionExpr
     * @generated
     */
    public Adapter createFunctionExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.FeatureExpr <em>Feature Expr</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.FeatureExpr
     * @generated
     */
    public Adapter createFeatureExprAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.StringConstant <em>String Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.StringConstant
     * @generated
     */
    public Adapter createStringConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.IntConstant <em>Int Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.IntConstant
     * @generated
     */
    public Adapter createIntConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.RealConstant <em>Real Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.RealConstant
     * @generated
     */
    public Adapter createRealConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.BooleanConstant <em>Boolean Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.BooleanConstant
     * @generated
     */
    public Adapter createBooleanConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.EnumConstant <em>Enum Constant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.EnumConstant
     * @generated
     */
    public Adapter createEnumConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tefkat.model.Injection <em>Injection</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tefkat.model.Injection
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

} //TefkatAdapterFactory
