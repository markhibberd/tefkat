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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import tefkat.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see tefkat.model.TefkatPackage
 * @generated
 */
public class TefkatSwitch {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";

    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TefkatPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TefkatSwitch() {
        if (modelPackage == null) {
            modelPackage = TefkatPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case TefkatPackage.EXTENT: {
                Extent extent = (Extent)theEObject;
                Object result = caseExtent(extent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.CONTAINER_EXTENT: {
                ContainerExtent containerExtent = (ContainerExtent)theEObject;
                Object result = caseContainerExtent(containerExtent);
                if (result == null) result = caseExtent(containerExtent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.REFERENCE_EXTENT: {
                ReferenceExtent referenceExtent = (ReferenceExtent)theEObject;
                Object result = caseReferenceExtent(referenceExtent);
                if (result == null) result = caseExtent(referenceExtent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.ABSTRACT_VAR: {
                AbstractVar abstractVar = (AbstractVar)theEObject;
                Object result = caseAbstractVar(abstractVar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.PATTERN_VAR: {
                PatternVar patternVar = (PatternVar)theEObject;
                Object result = casePatternVar(patternVar);
                if (result == null) result = caseAbstractVar(patternVar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TRULE_VAR: {
                TRuleVar tRuleVar = (TRuleVar)theEObject;
                Object result = caseTRuleVar(tRuleVar);
                if (result == null) result = caseAbstractVar(tRuleVar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.EXTENT_VAR: {
                ExtentVar extentVar = (ExtentVar)theEObject;
                Object result = caseExtentVar(extentVar);
                if (result == null) result = caseAbstractVar(extentVar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.VAR_SCOPE: {
                VarScope varScope = (VarScope)theEObject;
                Object result = caseVarScope(varScope);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.PATTERN_SCOPE: {
                PatternScope patternScope = (PatternScope)theEObject;
                Object result = casePatternScope(patternScope);
                if (result == null) result = caseVarScope(patternScope);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TRULE: {
                TRule tRule = (TRule)theEObject;
                Object result = caseTRule(tRule);
                if (result == null) result = caseVarScope(tRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TRANSFORMATION: {
                Transformation transformation = (Transformation)theEObject;
                Object result = caseTransformation(transformation);
                if (result == null) result = casePatternScope(transformation);
                if (result == null) result = caseVarScope(transformation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.PATTERN_DEFN: {
                PatternDefn patternDefn = (PatternDefn)theEObject;
                Object result = casePatternDefn(patternDefn);
                if (result == null) result = caseVarScope(patternDefn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.QUERY: {
                Query query = (Query)theEObject;
                Object result = caseQuery(query);
                if (result == null) result = casePatternScope(query);
                if (result == null) result = caseVarScope(query);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TERM: {
                Term term = (Term)theEObject;
                Object result = caseTerm(term);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.SOURCE_TERM: {
                SourceTerm sourceTerm = (SourceTerm)theEObject;
                Object result = caseSourceTerm(sourceTerm);
                if (result == null) result = caseTerm(sourceTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TARGET_TERM: {
                TargetTerm targetTerm = (TargetTerm)theEObject;
                Object result = caseTargetTerm(targetTerm);
                if (result == null) result = caseTerm(targetTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.COMPOUND_TERM: {
                CompoundTerm compoundTerm = (CompoundTerm)theEObject;
                Object result = caseCompoundTerm(compoundTerm);
                if (result == null) result = caseSourceTerm(compoundTerm);
                if (result == null) result = caseTerm(compoundTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.SIMPLE_TERM: {
                SimpleTerm simpleTerm = (SimpleTerm)theEObject;
                Object result = caseSimpleTerm(simpleTerm);
                if (result == null) result = caseSourceTerm(simpleTerm);
                if (result == null) result = caseTargetTerm(simpleTerm);
                if (result == null) result = caseTerm(simpleTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.EXPRESSION: {
                Expression expression = (Expression)theEObject;
                Object result = caseExpression(expression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.INSTANCE_REF: {
                InstanceRef instanceRef = (InstanceRef)theEObject;
                Object result = caseInstanceRef(instanceRef);
                if (result == null) result = caseExpression(instanceRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.AND_TERM: {
                AndTerm andTerm = (AndTerm)theEObject;
                Object result = caseAndTerm(andTerm);
                if (result == null) result = caseCompoundTerm(andTerm);
                if (result == null) result = caseTargetTerm(andTerm);
                if (result == null) result = caseSourceTerm(andTerm);
                if (result == null) result = caseTerm(andTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.OR_TERM: {
                OrTerm orTerm = (OrTerm)theEObject;
                Object result = caseOrTerm(orTerm);
                if (result == null) result = caseCompoundTerm(orTerm);
                if (result == null) result = caseSourceTerm(orTerm);
                if (result == null) result = caseTerm(orTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.NOT_TERM: {
                NotTerm notTerm = (NotTerm)theEObject;
                Object result = caseNotTerm(notTerm);
                if (result == null) result = caseCompoundTerm(notTerm);
                if (result == null) result = caseSourceTerm(notTerm);
                if (result == null) result = caseTerm(notTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.IF_TERM: {
                IfTerm ifTerm = (IfTerm)theEObject;
                Object result = caseIfTerm(ifTerm);
                if (result == null) result = caseCompoundTerm(ifTerm);
                if (result == null) result = caseTargetTerm(ifTerm);
                if (result == null) result = caseSourceTerm(ifTerm);
                if (result == null) result = caseTerm(ifTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.TRACKING_USE: {
                TrackingUse trackingUse = (TrackingUse)theEObject;
                Object result = caseTrackingUse(trackingUse);
                if (result == null) result = caseSimpleTerm(trackingUse);
                if (result == null) result = caseSourceTerm(trackingUse);
                if (result == null) result = caseTargetTerm(trackingUse);
                if (result == null) result = caseTerm(trackingUse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.FEATURE_VALUE_PAIR: {
                Map.Entry featureValuePair = (Map.Entry)theEObject;
                Object result = caseFeatureValuePair(featureValuePair);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.PATTERN_USE: {
                PatternUse patternUse = (PatternUse)theEObject;
                Object result = casePatternUse(patternUse);
                if (result == null) result = caseSimpleTerm(patternUse);
                if (result == null) result = caseSourceTerm(patternUse);
                if (result == null) result = caseTargetTerm(patternUse);
                if (result == null) result = caseTerm(patternUse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.MOF_TERM: {
                MofTerm mofTerm = (MofTerm)theEObject;
                Object result = caseMofTerm(mofTerm);
                if (result == null) result = caseSimpleTerm(mofTerm);
                if (result == null) result = caseSourceTerm(mofTerm);
                if (result == null) result = caseTargetTerm(mofTerm);
                if (result == null) result = caseTerm(mofTerm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.CONDITION: {
                Condition condition = (Condition)theEObject;
                Object result = caseCondition(condition);
                if (result == null) result = caseSimpleTerm(condition);
                if (result == null) result = caseSourceTerm(condition);
                if (result == null) result = caseTargetTerm(condition);
                if (result == null) result = caseTerm(condition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.MOF_INSTANCE: {
                MofInstance mofInstance = (MofInstance)theEObject;
                Object result = caseMofInstance(mofInstance);
                if (result == null) result = caseMofTerm(mofInstance);
                if (result == null) result = caseSimpleTerm(mofInstance);
                if (result == null) result = caseSourceTerm(mofInstance);
                if (result == null) result = caseTargetTerm(mofInstance);
                if (result == null) result = caseTerm(mofInstance);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.MOF_ORDER: {
                MofOrder mofOrder = (MofOrder)theEObject;
                Object result = caseMofOrder(mofOrder);
                if (result == null) result = caseMofTerm(mofOrder);
                if (result == null) result = caseSimpleTerm(mofOrder);
                if (result == null) result = caseSourceTerm(mofOrder);
                if (result == null) result = caseTargetTerm(mofOrder);
                if (result == null) result = caseTerm(mofOrder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.COMPOUND_EXPR: {
                CompoundExpr compoundExpr = (CompoundExpr)theEObject;
                Object result = caseCompoundExpr(compoundExpr);
                if (result == null) result = caseExpression(compoundExpr);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.SIMPLE_EXPR: {
                SimpleExpr simpleExpr = (SimpleExpr)theEObject;
                Object result = caseSimpleExpr(simpleExpr);
                if (result == null) result = caseExpression(simpleExpr);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.VAR_USE: {
                VarUse varUse = (VarUse)theEObject;
                Object result = caseVarUse(varUse);
                if (result == null) result = caseExpression(varUse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.COLLECTION_EXPR: {
                CollectionExpr collectionExpr = (CollectionExpr)theEObject;
                Object result = caseCollectionExpr(collectionExpr);
                if (result == null) result = caseCompoundExpr(collectionExpr);
                if (result == null) result = caseExpression(collectionExpr);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.FUNCTION_EXPR: {
                FunctionExpr functionExpr = (FunctionExpr)theEObject;
                Object result = caseFunctionExpr(functionExpr);
                if (result == null) result = caseCompoundExpr(functionExpr);
                if (result == null) result = caseExpression(functionExpr);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.FEATURE_EXPR: {
                FeatureExpr featureExpr = (FeatureExpr)theEObject;
                Object result = caseFeatureExpr(featureExpr);
                if (result == null) result = caseCompoundExpr(featureExpr);
                if (result == null) result = caseExpression(featureExpr);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.STRING_CONSTANT: {
                StringConstant stringConstant = (StringConstant)theEObject;
                Object result = caseStringConstant(stringConstant);
                if (result == null) result = caseSimpleExpr(stringConstant);
                if (result == null) result = caseExpression(stringConstant);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.INT_CONSTANT: {
                IntConstant intConstant = (IntConstant)theEObject;
                Object result = caseIntConstant(intConstant);
                if (result == null) result = caseSimpleExpr(intConstant);
                if (result == null) result = caseExpression(intConstant);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.REAL_CONSTANT: {
                RealConstant realConstant = (RealConstant)theEObject;
                Object result = caseRealConstant(realConstant);
                if (result == null) result = caseSimpleExpr(realConstant);
                if (result == null) result = caseExpression(realConstant);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.BOOLEAN_CONSTANT: {
                BooleanConstant booleanConstant = (BooleanConstant)theEObject;
                Object result = caseBooleanConstant(booleanConstant);
                if (result == null) result = caseSimpleExpr(booleanConstant);
                if (result == null) result = caseExpression(booleanConstant);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.ENUM_CONSTANT: {
                EnumConstant enumConstant = (EnumConstant)theEObject;
                Object result = caseEnumConstant(enumConstant);
                if (result == null) result = caseCompoundExpr(enumConstant);
                if (result == null) result = caseExpression(enumConstant);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TefkatPackage.INJECTION: {
                Injection injection = (Injection)theEObject;
                Object result = caseInjection(injection);
                if (result == null) result = caseTargetTerm(injection);
                if (result == null) result = caseTerm(injection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Extent</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Extent</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExtent(Extent object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Container Extent</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Container Extent</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseContainerExtent(ContainerExtent object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Reference Extent</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Reference Extent</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseReferenceExtent(ReferenceExtent object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Abstract Var</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Abstract Var</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseAbstractVar(AbstractVar object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Pattern Var</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Pattern Var</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePatternVar(PatternVar object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>TRule Var</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>TRule Var</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTRuleVar(TRuleVar object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Extent Var</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Extent Var</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExtentVar(ExtentVar object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Var Scope</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Var Scope</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseVarScope(VarScope object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Pattern Scope</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Pattern Scope</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePatternScope(PatternScope object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>TRule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>TRule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTRule(TRule object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Transformation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Transformation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTransformation(Transformation object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Pattern Defn</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Pattern Defn</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePatternDefn(PatternDefn object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuery(Query object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTerm(Term object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Source Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Source Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSourceTerm(SourceTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Target Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Target Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTargetTerm(TargetTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Compound Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Compound Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCompoundTerm(CompoundTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Simple Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Simple Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSimpleTerm(SimpleTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExpression(Expression object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Instance Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Instance Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInstanceRef(InstanceRef object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>And Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>And Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseAndTerm(AndTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Or Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Or Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOrTerm(OrTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Not Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Not Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseNotTerm(NotTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>If Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>If Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIfTerm(IfTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Tracking Use</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Tracking Use</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTrackingUse(TrackingUse object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Feature Value Pair</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Feature Value Pair</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFeatureValuePair(Map.Entry object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Pattern Use</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Pattern Use</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePatternUse(PatternUse object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Mof Term</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Mof Term</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMofTerm(MofTerm object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCondition(Condition object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Mof Instance</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Mof Instance</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMofInstance(MofInstance object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Mof Order</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Mof Order</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMofOrder(MofOrder object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Compound Expr</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Compound Expr</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCompoundExpr(CompoundExpr object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Simple Expr</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Simple Expr</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSimpleExpr(SimpleExpr object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Var Use</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Var Use</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseVarUse(VarUse object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Collection Expr</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Collection Expr</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCollectionExpr(CollectionExpr object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Function Expr</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Function Expr</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFunctionExpr(FunctionExpr object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Feature Expr</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Feature Expr</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFeatureExpr(FeatureExpr object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>String Constant</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>String Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseStringConstant(StringConstant object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Int Constant</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Int Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIntConstant(IntConstant object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Real Constant</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Real Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRealConstant(RealConstant object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Boolean Constant</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Boolean Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseBooleanConstant(BooleanConstant object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Enum Constant</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Enum Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEnumConstant(EnumConstant object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Injection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Injection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInjection(Injection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //TefkatSwitch
