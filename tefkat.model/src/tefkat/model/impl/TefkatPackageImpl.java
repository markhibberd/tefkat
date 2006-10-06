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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tefkat.model.Var;
import tefkat.model.AndTerm;
import tefkat.model.BooleanConstant;
import tefkat.model.CollectionExpr;
import tefkat.model.CompoundExpr;
import tefkat.model.CompoundTerm;
import tefkat.model.Condition;
import tefkat.model.ContainerExtent;
import tefkat.model.EnumConstant;
import tefkat.model.Expression;
import tefkat.model.Extent;
import tefkat.model.FeatureExpr;
import tefkat.model.FunctionExpr;
import tefkat.model.IfTerm;
import tefkat.model.Injection;
import tefkat.model.InstanceRef;
import tefkat.model.IntConstant;
import tefkat.model.MofInstance;
import tefkat.model.MofOrder;
import tefkat.model.MofTerm;
import tefkat.model.NamespaceDeclaration;
import tefkat.model.NotTerm;
import tefkat.model.OrTerm;
import tefkat.model.PatternDefn;
import tefkat.model.PatternScope;
import tefkat.model.PatternUse;
import tefkat.model.PatternVar;
import tefkat.model.Query;
import tefkat.model.RealConstant;
import tefkat.model.ReferenceExtent;
import tefkat.model.SimpleExpr;
import tefkat.model.SimpleTerm;
import tefkat.model.SourceTerm;
import tefkat.model.StringConstant;
import tefkat.model.TRule;
import tefkat.model.TargetTerm;
import tefkat.model.TefkatException;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;
import tefkat.model.Term;
import tefkat.model.TrackingUse;
import tefkat.model.Transformation;
import tefkat.model.VarScope;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TefkatPackageImpl extends EPackageImpl implements TefkatPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass containerExtentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass referenceExtentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractVarEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass varScopeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass patternScopeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass transformationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass patternDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass termEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass targetTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compoundTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simpleTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass instanceRefEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass andTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass orTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass notTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ifTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass trackingUseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass featureValuePairEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass patternUseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mofTermEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass conditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mofInstanceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mofOrderEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compoundExprEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simpleExprEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass varUseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass collectionExprEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionExprEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass featureExprEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringConstantEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intConstantEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass realConstantEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass booleanConstantEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enumConstantEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass injectionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namespaceDeclarationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType collectionEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType listEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType listArrayEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType tefkatExceptionEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see tefkat.model.TefkatPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TefkatPackageImpl() {
        super(eNS_URI, TefkatFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TefkatPackage init() {
        if (isInited) return (TefkatPackage)EPackage.Registry.INSTANCE.getEPackage(TefkatPackage.eNS_URI);

        // Obtain or create and register package
        TefkatPackageImpl theTefkatPackage = (TefkatPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TefkatPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TefkatPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theTefkatPackage.createPackageContents();

        // Initialize created meta-data
        theTefkatPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTefkatPackage.freeze();

        return theTefkatPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtent() {
        return extentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContainerExtent() {
        return containerExtentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContainerExtent_Resource() {
        return (EAttribute)containerExtentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getReferenceExtent() {
        return referenceExtentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReferenceExtent_Resources() {
        return (EAttribute)referenceExtentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVar() {
        return abstractVarEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Scope() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVar_Name() {
        return (EAttribute)abstractVarEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Usages() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Superseded() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Superseder() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Extended() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVar_Extender() {
        return (EReference)abstractVarEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVarScope() {
        return varScopeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVarScope_Vars() {
        return (EReference)varScopeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVarScope_Name() {
        return (EAttribute)varScopeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVarScope_Comments() {
        return (EAttribute)varScopeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPatternScope() {
        return patternScopeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternScope_PatternDefn() {
        return (EReference)patternScopeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTRule() {
        return tRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTRule_Transformation() {
        return (EReference)tRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTRule_Src() {
        return (EReference)tRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTRule_Tgt() {
        return (EReference)tRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTRule_Extended() {
        return (EReference)tRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTRule_Superseded() {
        return (EReference)tRuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTRule_Abstract() {
        return (EAttribute)tRuleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTransformation() {
        return transformationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTransformation_TRule() {
        return (EReference)transformationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTransformation_ImportedPackages() {
        return (EAttribute)transformationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTransformation_NamespaceDeclarations() {
        return (EReference)transformationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPatternDefn() {
        return patternDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternDefn_PatternScope() {
        return (EReference)patternDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternDefn_Term() {
        return (EReference)patternDefnEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternDefn_ParameterVar() {
        return (EReference)patternDefnEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPatternDefn_Source() {
        return (EAttribute)patternDefnEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQuery() {
        return queryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuery_Term() {
        return (EReference)queryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuery_ParameterVar() {
        return (EReference)queryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTerm() {
        return termEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTerm_PatternDefn() {
        return (EReference)termEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTerm_Query() {
        return (EReference)termEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTerm_CompoundTerm() {
        return (EReference)termEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTerm_Context() {
        return (EReference)termEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSourceTerm() {
        return sourceTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSourceTerm_TRuleSrc() {
        return (EReference)sourceTermEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTargetTerm() {
        return targetTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTargetTerm_TRuleTgt() {
        return (EReference)targetTermEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompoundTerm() {
        return compoundTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompoundTerm_Term() {
        return (EReference)compoundTermEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSimpleTerm() {
        return simpleTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpression() {
        return expressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExpression_Expr() {
        return (EReference)expressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInstanceRef() {
        return instanceRefEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInstanceRef_Object() {
        return (EReference)instanceRefEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAndTerm() {
        return andTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOrTerm() {
        return orTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNotTerm() {
        return notTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIfTerm() {
        return ifTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTrackingUse() {
        return trackingUseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTrackingUse_Tracking() {
        return (EReference)trackingUseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTrackingUse_Features() {
        return (EReference)trackingUseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTrackingUse_TrackingName() {
        return (EAttribute)trackingUseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFeatureValuePair() {
        return featureValuePairEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFeatureValuePair_Value() {
        return (EReference)featureValuePairEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFeatureValuePair_Key() {
        return (EAttribute)featureValuePairEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPatternUse() {
        return patternUseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternUse_Defn() {
        return (EReference)patternUseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPatternUse_Arg() {
        return (EReference)patternUseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMofTerm() {
        return mofTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCondition() {
        return conditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCondition_Arg() {
        return (EReference)conditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCondition_Relation() {
        return (EAttribute)conditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMofInstance() {
        return mofInstanceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofInstance_TypeName() {
        return (EReference)mofInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofInstance_Instance() {
        return (EReference)mofInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMofInstance_Exact() {
        return (EAttribute)mofInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMofOrder() {
        return mofOrderEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofOrder_Lesser() {
        return (EReference)mofOrderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofOrder_Greater() {
        return (EReference)mofOrderEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofOrder_Instance() {
        return (EReference)mofOrderEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMofOrder_Feature() {
        return (EReference)mofOrderEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompoundExpr() {
        return compoundExprEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompoundExpr_Arg() {
        return (EReference)compoundExprEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSimpleExpr() {
        return simpleExprEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimpleExpr_Representation() {
        return (EAttribute)simpleExprEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVarUse() {
        return varUseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVarUse_Var() {
        return (EReference)varUseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCollectionExpr() {
        return collectionExprEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCollectionExpr_Unique() {
        return (EAttribute)collectionExprEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCollectionExpr_Ordered() {
        return (EAttribute)collectionExprEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFunctionExpr() {
        return functionExprEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionExpr_Function() {
        return (EAttribute)functionExprEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFeatureExpr() {
        return featureExprEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFeatureExpr_Operation() {
        return (EAttribute)featureExprEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFeatureExpr_Collect() {
        return (EAttribute)featureExprEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFeatureExpr_Feature() {
        return (EReference)featureExprEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringConstant() {
        return stringConstantEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntConstant() {
        return intConstantEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRealConstant() {
        return realConstantEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBooleanConstant() {
        return booleanConstantEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEnumConstant() {
        return enumConstantEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInjection() {
        return injectionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInjection_Name() {
        return (EAttribute)injectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInjection_Sources() {
        return (EReference)injectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInjection_Target() {
        return (EReference)injectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamespaceDeclaration() {
        return namespaceDeclarationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamespaceDeclaration_Prefix() {
        return (EAttribute)namespaceDeclarationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamespaceDeclaration_URI() {
        return (EAttribute)namespaceDeclarationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getCollection() {
        return collectionEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getList() {
        return listEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getListArray() {
        return listArrayEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTefkatException() {
        return tefkatExceptionEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TefkatFactory getTefkatFactory() {
        return (TefkatFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        extentEClass = createEClass(EXTENT);

        containerExtentEClass = createEClass(CONTAINER_EXTENT);
        createEAttribute(containerExtentEClass, CONTAINER_EXTENT__RESOURCE);

        referenceExtentEClass = createEClass(REFERENCE_EXTENT);
        createEAttribute(referenceExtentEClass, REFERENCE_EXTENT__RESOURCES);

        abstractVarEClass = createEClass(ABSTRACT_VAR);
        createEReference(abstractVarEClass, ABSTRACT_VAR__SCOPE);
        createEAttribute(abstractVarEClass, ABSTRACT_VAR__NAME);
        createEReference(abstractVarEClass, ABSTRACT_VAR__USAGES);
        createEReference(abstractVarEClass, ABSTRACT_VAR__SUPERSEDED);
        createEReference(abstractVarEClass, ABSTRACT_VAR__SUPERSEDER);
        createEReference(abstractVarEClass, ABSTRACT_VAR__EXTENDED);
        createEReference(abstractVarEClass, ABSTRACT_VAR__EXTENDER);

        varScopeEClass = createEClass(VAR_SCOPE);
        createEReference(varScopeEClass, VAR_SCOPE__VARS);
        createEAttribute(varScopeEClass, VAR_SCOPE__NAME);
        createEAttribute(varScopeEClass, VAR_SCOPE__COMMENTS);

        patternScopeEClass = createEClass(PATTERN_SCOPE);
        createEReference(patternScopeEClass, PATTERN_SCOPE__PATTERN_DEFN);

        tRuleEClass = createEClass(TRULE);
        createEReference(tRuleEClass, TRULE__TRANSFORMATION);
        createEReference(tRuleEClass, TRULE__SRC);
        createEReference(tRuleEClass, TRULE__TGT);
        createEReference(tRuleEClass, TRULE__EXTENDED);
        createEReference(tRuleEClass, TRULE__SUPERSEDED);
        createEAttribute(tRuleEClass, TRULE__ABSTRACT);

        transformationEClass = createEClass(TRANSFORMATION);
        createEReference(transformationEClass, TRANSFORMATION__TRULE);
        createEAttribute(transformationEClass, TRANSFORMATION__IMPORTED_PACKAGES);
        createEReference(transformationEClass, TRANSFORMATION__NAMESPACE_DECLARATIONS);

        namespaceDeclarationEClass = createEClass(NAMESPACE_DECLARATION);
        createEAttribute(namespaceDeclarationEClass, NAMESPACE_DECLARATION__PREFIX);
        createEAttribute(namespaceDeclarationEClass, NAMESPACE_DECLARATION__URI);

        patternDefnEClass = createEClass(PATTERN_DEFN);
        createEReference(patternDefnEClass, PATTERN_DEFN__PATTERN_SCOPE);
        createEReference(patternDefnEClass, PATTERN_DEFN__TERM);
        createEReference(patternDefnEClass, PATTERN_DEFN__PARAMETER_VAR);
        createEAttribute(patternDefnEClass, PATTERN_DEFN__SOURCE);

        queryEClass = createEClass(QUERY);
        createEReference(queryEClass, QUERY__TERM);
        createEReference(queryEClass, QUERY__PARAMETER_VAR);

        termEClass = createEClass(TERM);
        createEReference(termEClass, TERM__PATTERN_DEFN);
        createEReference(termEClass, TERM__QUERY);
        createEReference(termEClass, TERM__COMPOUND_TERM);
        createEReference(termEClass, TERM__CONTEXT);

        sourceTermEClass = createEClass(SOURCE_TERM);
        createEReference(sourceTermEClass, SOURCE_TERM__TRULE_SRC);

        targetTermEClass = createEClass(TARGET_TERM);
        createEReference(targetTermEClass, TARGET_TERM__TRULE_TGT);

        compoundTermEClass = createEClass(COMPOUND_TERM);
        createEReference(compoundTermEClass, COMPOUND_TERM__TERM);

        simpleTermEClass = createEClass(SIMPLE_TERM);

        expressionEClass = createEClass(EXPRESSION);
        createEReference(expressionEClass, EXPRESSION__EXPR);

        instanceRefEClass = createEClass(INSTANCE_REF);
        createEReference(instanceRefEClass, INSTANCE_REF__OBJECT);

        andTermEClass = createEClass(AND_TERM);

        orTermEClass = createEClass(OR_TERM);

        notTermEClass = createEClass(NOT_TERM);

        ifTermEClass = createEClass(IF_TERM);

        trackingUseEClass = createEClass(TRACKING_USE);
        createEReference(trackingUseEClass, TRACKING_USE__TRACKING);
        createEReference(trackingUseEClass, TRACKING_USE__FEATURES);
        createEAttribute(trackingUseEClass, TRACKING_USE__TRACKING_NAME);

        featureValuePairEClass = createEClass(FEATURE_VALUE_PAIR);
        createEReference(featureValuePairEClass, FEATURE_VALUE_PAIR__VALUE);
        createEAttribute(featureValuePairEClass, FEATURE_VALUE_PAIR__KEY);

        patternUseEClass = createEClass(PATTERN_USE);
        createEReference(patternUseEClass, PATTERN_USE__DEFN);
        createEReference(patternUseEClass, PATTERN_USE__ARG);

        mofTermEClass = createEClass(MOF_TERM);

        conditionEClass = createEClass(CONDITION);
        createEReference(conditionEClass, CONDITION__ARG);
        createEAttribute(conditionEClass, CONDITION__RELATION);

        mofInstanceEClass = createEClass(MOF_INSTANCE);
        createEReference(mofInstanceEClass, MOF_INSTANCE__TYPE_NAME);
        createEReference(mofInstanceEClass, MOF_INSTANCE__INSTANCE);
        createEAttribute(mofInstanceEClass, MOF_INSTANCE__EXACT);

        mofOrderEClass = createEClass(MOF_ORDER);
        createEReference(mofOrderEClass, MOF_ORDER__LESSER);
        createEReference(mofOrderEClass, MOF_ORDER__GREATER);
        createEReference(mofOrderEClass, MOF_ORDER__INSTANCE);
        createEReference(mofOrderEClass, MOF_ORDER__FEATURE);

        compoundExprEClass = createEClass(COMPOUND_EXPR);
        createEReference(compoundExprEClass, COMPOUND_EXPR__ARG);

        simpleExprEClass = createEClass(SIMPLE_EXPR);
        createEAttribute(simpleExprEClass, SIMPLE_EXPR__REPRESENTATION);

        varUseEClass = createEClass(VAR_USE);
        createEReference(varUseEClass, VAR_USE__VAR);

        collectionExprEClass = createEClass(COLLECTION_EXPR);
        createEAttribute(collectionExprEClass, COLLECTION_EXPR__UNIQUE);
        createEAttribute(collectionExprEClass, COLLECTION_EXPR__ORDERED);

        functionExprEClass = createEClass(FUNCTION_EXPR);
        createEAttribute(functionExprEClass, FUNCTION_EXPR__FUNCTION);

        featureExprEClass = createEClass(FEATURE_EXPR);
        createEAttribute(featureExprEClass, FEATURE_EXPR__OPERATION);
        createEAttribute(featureExprEClass, FEATURE_EXPR__COLLECT);
        createEReference(featureExprEClass, FEATURE_EXPR__FEATURE);

        stringConstantEClass = createEClass(STRING_CONSTANT);

        intConstantEClass = createEClass(INT_CONSTANT);

        realConstantEClass = createEClass(REAL_CONSTANT);

        booleanConstantEClass = createEClass(BOOLEAN_CONSTANT);

        enumConstantEClass = createEClass(ENUM_CONSTANT);

        injectionEClass = createEClass(INJECTION);
        createEAttribute(injectionEClass, INJECTION__NAME);
        createEReference(injectionEClass, INJECTION__SOURCES);
        createEReference(injectionEClass, INJECTION__TARGET);

        // Create data types
        collectionEDataType = createEDataType(COLLECTION);
        listEDataType = createEDataType(LIST);
        listArrayEDataType = createEDataType(LIST_ARRAY);
        tefkatExceptionEDataType = createEDataType(TEFKAT_EXCEPTION);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Add supertypes to classes
        containerExtentEClass.getESuperTypes().add(this.getExtent());
        referenceExtentEClass.getESuperTypes().add(this.getExtent());
        patternScopeEClass.getESuperTypes().add(this.getVarScope());
        tRuleEClass.getESuperTypes().add(this.getVarScope());
        transformationEClass.getESuperTypes().add(this.getPatternScope());
        patternDefnEClass.getESuperTypes().add(this.getVarScope());
        queryEClass.getESuperTypes().add(this.getPatternScope());
        sourceTermEClass.getESuperTypes().add(this.getTerm());
        targetTermEClass.getESuperTypes().add(this.getTerm());
        compoundTermEClass.getESuperTypes().add(this.getSourceTerm());
        simpleTermEClass.getESuperTypes().add(this.getSourceTerm());
        simpleTermEClass.getESuperTypes().add(this.getTargetTerm());
        instanceRefEClass.getESuperTypes().add(this.getExpression());
        andTermEClass.getESuperTypes().add(this.getCompoundTerm());
        andTermEClass.getESuperTypes().add(this.getTargetTerm());
        orTermEClass.getESuperTypes().add(this.getCompoundTerm());
        notTermEClass.getESuperTypes().add(this.getCompoundTerm());
        ifTermEClass.getESuperTypes().add(this.getCompoundTerm());
        ifTermEClass.getESuperTypes().add(this.getTargetTerm());
        trackingUseEClass.getESuperTypes().add(this.getSimpleTerm());
        patternUseEClass.getESuperTypes().add(this.getSimpleTerm());
        mofTermEClass.getESuperTypes().add(this.getSimpleTerm());
        conditionEClass.getESuperTypes().add(this.getSimpleTerm());
        mofInstanceEClass.getESuperTypes().add(this.getMofTerm());
        mofOrderEClass.getESuperTypes().add(this.getMofTerm());
        compoundExprEClass.getESuperTypes().add(this.getExpression());
        simpleExprEClass.getESuperTypes().add(this.getExpression());
        varUseEClass.getESuperTypes().add(this.getExpression());
        collectionExprEClass.getESuperTypes().add(this.getCompoundExpr());
        functionExprEClass.getESuperTypes().add(this.getCompoundExpr());
        featureExprEClass.getESuperTypes().add(this.getCompoundExpr());
        stringConstantEClass.getESuperTypes().add(this.getSimpleExpr());
        intConstantEClass.getESuperTypes().add(this.getSimpleExpr());
        realConstantEClass.getESuperTypes().add(this.getSimpleExpr());
        booleanConstantEClass.getESuperTypes().add(this.getSimpleExpr());
        enumConstantEClass.getESuperTypes().add(this.getCompoundExpr());
        injectionEClass.getESuperTypes().add(this.getTargetTerm());

        // Initialize classes and features; add operations and parameters
        initEClass(extentEClass, Extent.class, "Extent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        EOperation op = addEOperation(extentEClass, ecorePackage.getEBoolean(), "contains", 1, 1);
        addEParameter(op, ecorePackage.getEObject(), "instance", 1, 1);

        op = addEOperation(extentEClass, ecorePackage.getEObject(), "getObjectsByClass", 0, -1);
        addEParameter(op, ecorePackage.getEClass(), "theClass", 1, 1);
        addEParameter(op, ecorePackage.getEBoolean(), "isExactly", 1, 1);

        addEOperation(extentEClass, ecorePackage.getETreeIterator(), "getAllContents", 0, 1);

        op = addEOperation(extentEClass, null, "add");
        addEParameter(op, ecorePackage.getEObject(), "obj", 1, 1);

        op = addEOperation(extentEClass, null, "remove");
        addEParameter(op, ecorePackage.getEObject(), "obj", 1, 1);

        initEClass(containerExtentEClass, ContainerExtent.class, "ContainerExtent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerExtent_Resource(), ecorePackage.getEResource(), "resource", null, 1, 1, ContainerExtent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(referenceExtentEClass, ReferenceExtent.class, "ReferenceExtent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getReferenceExtent_Resources(), ecorePackage.getEResource(), "resources", null, 0, -1, ReferenceExtent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractVarEClass, Var.class, "Var", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVar_Scope(), this.getVarScope(), this.getVarScope_Vars(), "scope", null, 0, 1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVar_Name(), ecorePackage.getEString(), "name", null, 0, 1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVar_Usages(), this.getVarUse(), this.getVarUse_Var(), "usages", null, 0, -1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getVar_Superseded(), this.getVar(), this.getVar_Superseder(), "superseded", null, 0, -1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVar_Superseder(), this.getVar(), this.getVar_Superseded(), "superseder", null, 0, -1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVar_Extended(), this.getVar(), this.getVar_Extender(), "extended", null, 0, -1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVar_Extender(), this.getVar(), this.getVar_Extended(), "extender", null, 0, -1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(varScopeEClass, VarScope.class, "VarScope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVarScope_Vars(), this.getVar(), this.getVar_Scope(), "vars", null, 0, -1, VarScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVarScope_Name(), ecorePackage.getEString(), "name", null, 0, 1, VarScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVarScope_Comments(), ecorePackage.getEString(), "comments", null, 0, -1, VarScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(patternScopeEClass, PatternScope.class, "PatternScope", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPatternScope_PatternDefn(), this.getPatternDefn(), this.getPatternDefn_PatternScope(), "patternDefn", null, 0, -1, PatternScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tRuleEClass, TRule.class, "TRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTRule_Transformation(), this.getTransformation(), this.getTransformation_TRule(), "transformation", null, 1, 1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTRule_Src(), this.getSourceTerm(), this.getSourceTerm_TRuleSrc(), "src", null, 0, 1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTRule_Tgt(), this.getTargetTerm(), this.getTargetTerm_TRuleTgt(), "tgt", null, 0, -1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTRule_Extended(), this.getTRule(), null, "extended", null, 0, -1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTRule_Superseded(), this.getTRule(), null, "superseded", null, 0, -1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTRule_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, TRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(tRuleEClass, this.getCollection(), "getGoal", 1, 1);

        addEOperation(tRuleEClass, this.getCollection(), "getSourceTerms", 1, 1);

        addEOperation(tRuleEClass, this.getCollection(), "getOverrideTerms", 1, 1);

        addEOperation(tRuleEClass, this.getCollection(), "getTargetTerms", 1, 1);

        initEClass(transformationEClass, Transformation.class, "Transformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTransformation_TRule(), this.getTRule(), this.getTRule_Transformation(), "tRule", null, 0, -1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTransformation_ImportedPackages(), ecorePackage.getEString(), "importedPackages", null, 0, -1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTransformation_NamespaceDeclarations(), this.getNamespaceDeclaration(), null, "namespaceDeclarations", null, 0, -1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(transformationEClass, this.getListArray(), "getStrata", 0, 1);
        addEException(op, this.getTefkatException());

        op = addEOperation(transformationEClass, null, "addSupersedes");
        addEParameter(op, this.getTRule(), "superseder", 1, 1);
        addEParameter(op, this.getTRule(), "superseded", 1, 1);

        op = addEOperation(transformationEClass, null, "removeSupersedes");
        addEParameter(op, this.getTRule(), "superseder", 1, 1);
        addEParameter(op, this.getTRule(), "superseded", 1, 1);

        op = addEOperation(transformationEClass, this.getCollection(), "getSupersedingRules", 1, 1);
        addEParameter(op, this.getTRule(), "superseded", 1, 1);

        initEClass(namespaceDeclarationEClass, NamespaceDeclaration.class, "NamespaceDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNamespaceDeclaration_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, NamespaceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNamespaceDeclaration_URI(), ecorePackage.getEString(), "URI", null, 0, 1, NamespaceDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(patternDefnEClass, PatternDefn.class, "PatternDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPatternDefn_PatternScope(), this.getPatternScope(), this.getPatternScope_PatternDefn(), "patternScope", null, 1, 1, PatternDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPatternDefn_Term(), this.getTerm(), this.getTerm_PatternDefn(), "term", null, 1, 1, PatternDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPatternDefn_ParameterVar(), this.getVar(), null, "parameterVar", null, 0, -1, PatternDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPatternDefn_Source(), ecorePackage.getEBoolean(), "source", "true", 0, 1, PatternDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQuery_Term(), this.getTerm(), this.getTerm_Query(), "term", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuery_ParameterVar(), this.getVar(), null, "parameterVar", null, 0, -1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(termEClass, Term.class, "Term", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTerm_PatternDefn(), this.getPatternDefn(), this.getPatternDefn_Term(), "patternDefn", null, 0, 1, Term.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTerm_Query(), this.getQuery(), this.getQuery_Term(), "query", null, 0, 1, Term.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTerm_CompoundTerm(), this.getCompoundTerm(), this.getCompoundTerm_Term(), "compoundTerm", null, 0, 1, Term.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTerm_Context(), this.getVar(), null, "context", null, 0, 1, Term.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(termEClass, this.getVar(), "getExtent", 0, 1);

        addEOperation(termEClass, ecorePackage.getEBoolean(), "isTarget", 1, 1);

        initEClass(sourceTermEClass, SourceTerm.class, "SourceTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSourceTerm_TRuleSrc(), this.getTRule(), this.getTRule_Src(), "tRuleSrc", null, 0, 1, SourceTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(targetTermEClass, TargetTerm.class, "TargetTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTargetTerm_TRuleTgt(), this.getTRule(), this.getTRule_Tgt(), "tRuleTgt", null, 0, 1, TargetTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compoundTermEClass, CompoundTerm.class, "CompoundTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompoundTerm_Term(), this.getTerm(), this.getTerm_CompoundTerm(), "term", null, 0, -1, CompoundTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(simpleTermEClass, SimpleTerm.class, "SimpleTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExpression_Expr(), this.getCompoundExpr(), this.getCompoundExpr_Arg(), "expr", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(expressionEClass, this.getExpression(), "copy", 0, 1);

        initEClass(instanceRefEClass, InstanceRef.class, "InstanceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInstanceRef_Object(), ecorePackage.getEObject(), null, "object", null, 1, 1, InstanceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(andTermEClass, AndTerm.class, "AndTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(orTermEClass, OrTerm.class, "OrTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(notTermEClass, NotTerm.class, "NotTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(notTermEClass, this.getVar(), "getNonLocalVars", 0, -1);

        initEClass(ifTermEClass, IfTerm.class, "IfTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(ifTermEClass, this.getVar(), "getNonLocalVars", 0, -1);

        initEClass(trackingUseEClass, TrackingUse.class, "TrackingUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTrackingUse_Tracking(), ecorePackage.getEClass(), null, "tracking", null, 0, 1, TrackingUse.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTrackingUse_Features(), this.getFeatureValuePair(), null, "features", null, 0, -1, TrackingUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTrackingUse_TrackingName(), ecorePackage.getEString(), "trackingName", null, 1, 1, TrackingUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureValuePairEClass, Map.Entry.class, "FeatureValuePair", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFeatureValuePair_Value(), this.getExpression(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureValuePair_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(patternUseEClass, PatternUse.class, "PatternUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPatternUse_Defn(), this.getPatternDefn(), null, "defn", null, 1, 1, PatternUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPatternUse_Arg(), this.getExpression(), null, "arg", null, 0, -1, PatternUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mofTermEClass, MofTerm.class, "MofTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(conditionEClass, Condition.class, "Condition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCondition_Arg(), this.getExpression(), null, "arg", null, 0, -1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCondition_Relation(), ecorePackage.getEString(), "relation", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mofInstanceEClass, MofInstance.class, "MofInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMofInstance_TypeName(), this.getExpression(), null, "typeName", null, 1, 1, MofInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMofInstance_Instance(), this.getExpression(), null, "instance", null, 1, 1, MofInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMofInstance_Exact(), ecorePackage.getEBoolean(), "exact", null, 0, 1, MofInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mofOrderEClass, MofOrder.class, "MofOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMofOrder_Lesser(), this.getExpression(), null, "lesser", null, 1, 1, MofOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMofOrder_Greater(), this.getExpression(), null, "greater", null, 1, 1, MofOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMofOrder_Instance(), this.getExpression(), null, "instance", null, 1, 1, MofOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMofOrder_Feature(), this.getExpression(), null, "feature", null, 1, 1, MofOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compoundExprEClass, CompoundExpr.class, "CompoundExpr", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompoundExpr_Arg(), this.getExpression(), this.getExpression_Expr(), "arg", null, 0, -1, CompoundExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(simpleExprEClass, SimpleExpr.class, "SimpleExpr", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSimpleExpr_Representation(), ecorePackage.getEString(), "representation", null, 1, 1, SimpleExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(varUseEClass, VarUse.class, "VarUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVarUse_Var(), this.getVar(), this.getVar_Usages(), "var", null, 1, 1, VarUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(collectionExprEClass, CollectionExpr.class, "CollectionExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCollectionExpr_Unique(), ecorePackage.getEBoolean(), "unique", null, 1, 1, CollectionExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCollectionExpr_Ordered(), ecorePackage.getEBoolean(), "ordered", null, 1, 1, CollectionExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(functionExprEClass, FunctionExpr.class, "FunctionExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFunctionExpr_Function(), ecorePackage.getEString(), "function", null, 1, 1, FunctionExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureExprEClass, FeatureExpr.class, "FeatureExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureExpr_Operation(), ecorePackage.getEBoolean(), "operation", null, 1, 1, FeatureExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureExpr_Collect(), ecorePackage.getEBoolean(), "collect", null, 1, 1, FeatureExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFeatureExpr_Feature(), this.getExpression(), null, "feature", null, 1, 1, FeatureExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringConstantEClass, StringConstant.class, "StringConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(intConstantEClass, IntConstant.class, "IntConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(realConstantEClass, RealConstant.class, "RealConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(booleanConstantEClass, BooleanConstant.class, "BooleanConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(enumConstantEClass, EnumConstant.class, "EnumConstant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(injectionEClass, Injection.class, "Injection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getInjection_Name(), ecorePackage.getEString(), "name", null, 1, 1, Injection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInjection_Sources(), this.getExpression(), null, "sources", null, 0, -1, Injection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInjection_Target(), this.getVarUse(), null, "target", null, 1, 1, Injection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        initEDataType(collectionEDataType, Collection.class, "Collection", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(listEDataType, List.class, "List", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(listArrayEDataType, List[].class, "ListArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(tefkatExceptionEDataType, TefkatException.class, "TefkatException", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // 22July2004 - Added abstract attribute and removed opposite ends for extended/superseded
        createSupersededAnnotations();
    }

    /**
     * Initializes the annotations for <b>22July2004 - Added abstract attribute and removed opposite ends for extended/superseded</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createSupersededAnnotations() {
        String source = "22July2004 - Added abstract attribute and removed opposite ends for extended/superseded";		
        addAnnotation
          (tRuleEClass, 
           source, 
           new String[] {
           });
    }

} //TefkatPackageImpl
