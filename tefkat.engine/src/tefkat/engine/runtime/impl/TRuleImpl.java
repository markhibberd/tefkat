/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.NotTerm;
import tefkat.engine.runtime.RuntimeFactory;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.SourceTerm;
import tefkat.engine.runtime.TRule;
import tefkat.engine.runtime.TargetTerm;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Transformation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TRule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#getTgt <em>Tgt</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#getSuperseded <em>Superseded</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TRuleImpl#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TRuleImpl extends VarScopeImpl implements TRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The cached value of the '{@link #getSrc() <em>Src</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected SourceTerm src = null;

    /**
     * The cached value of the '{@link #getTgt() <em>Tgt</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTgt()
     * @generated
     * @ordered
     */
    protected EList tgt = null;

    /**
     * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtended()
     * @generated
     * @ordered
     */
    protected EList extended = null;

    /**
     * The cached value of the '{@link #getSuperseded() <em>Superseded</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperseded()
     * @generated
     * @ordered
     */
    protected EList superseded = null;

    /**
     * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected static final boolean ABSTRACT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected boolean abstract_ = ABSTRACT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.TRULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transformation getTransformation() {
        if (eContainerFeatureID != RuntimePackage.TRULE__TRANSFORMATION) return null;
        return (Transformation)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTransformation(Transformation newTransformation, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTransformation, RuntimePackage.TRULE__TRANSFORMATION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransformation(Transformation newTransformation) {
        if (newTransformation != eInternalContainer() || (eContainerFeatureID != RuntimePackage.TRULE__TRANSFORMATION && newTransformation != null)) {
            if (EcoreUtil.isAncestor(this, newTransformation))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTransformation != null)
                msgs = ((InternalEObject)newTransformation).eInverseAdd(this, RuntimePackage.TRANSFORMATION__TRULE, Transformation.class, msgs);
            msgs = basicSetTransformation(newTransformation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TRULE__TRANSFORMATION, newTransformation, newTransformation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTerm getSrc() {
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSrc(SourceTerm newSrc, NotificationChain msgs) {
        SourceTerm oldSrc = src;
        src = newSrc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.TRULE__SRC, oldSrc, newSrc);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSrc(SourceTerm newSrc) {
        if (newSrc != src) {
            NotificationChain msgs = null;
            if (src != null)
                msgs = ((InternalEObject)src).eInverseRemove(this, RuntimePackage.SOURCE_TERM__TRULE_SRC, SourceTerm.class, msgs);
            if (newSrc != null)
                msgs = ((InternalEObject)newSrc).eInverseAdd(this, RuntimePackage.SOURCE_TERM__TRULE_SRC, SourceTerm.class, msgs);
            msgs = basicSetSrc(newSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TRULE__SRC, newSrc, newSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTgt() {
        if (tgt == null) {
            tgt = new EObjectContainmentWithInverseEList(TargetTerm.class, this, RuntimePackage.TRULE__TGT, RuntimePackage.TARGET_TERM__TRULE_TGT);
        }
        return tgt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExtended() {
        if (extended == null) {
            extended = new EObjectResolvingEList(TRule.class, this, RuntimePackage.TRULE__EXTENDED);
        }
        return extended;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperseded() {
        if (superseded == null) {
            superseded = new EObjectResolvingEList(TRule.class, this, RuntimePackage.TRULE__SUPERSEDED);
        }
        return superseded;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAbstract() {
        return abstract_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAbstract(boolean newAbstract) {
        boolean oldAbstract = abstract_;
        abstract_ = newAbstract;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.TRULE__ABSTRACT, oldAbstract, abstract_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTransformation((Transformation)otherEnd, msgs);
            case RuntimePackage.TRULE__SRC:
                if (src != null)
                    msgs = ((InternalEObject)src).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.TRULE__SRC, null, msgs);
                return basicSetSrc((SourceTerm)otherEnd, msgs);
            case RuntimePackage.TRULE__TGT:
                return ((InternalEList)getTgt()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                return basicSetTransformation(null, msgs);
            case RuntimePackage.TRULE__SRC:
                return basicSetSrc(null, msgs);
            case RuntimePackage.TRULE__TGT:
                return ((InternalEList)getTgt()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                return eInternalContainer().eInverseRemove(this, RuntimePackage.TRANSFORMATION__TRULE, Transformation.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                return getTransformation();
            case RuntimePackage.TRULE__SRC:
                return getSrc();
            case RuntimePackage.TRULE__TGT:
                return getTgt();
            case RuntimePackage.TRULE__EXTENDED:
                return getExtended();
            case RuntimePackage.TRULE__SUPERSEDED:
                return getSuperseded();
            case RuntimePackage.TRULE__ABSTRACT:
                return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                setTransformation((Transformation)newValue);
                return;
            case RuntimePackage.TRULE__SRC:
                setSrc((SourceTerm)newValue);
                return;
            case RuntimePackage.TRULE__TGT:
                getTgt().clear();
                getTgt().addAll((Collection)newValue);
                return;
            case RuntimePackage.TRULE__EXTENDED:
                getExtended().clear();
                getExtended().addAll((Collection)newValue);
                return;
            case RuntimePackage.TRULE__SUPERSEDED:
                getSuperseded().clear();
                getSuperseded().addAll((Collection)newValue);
                return;
            case RuntimePackage.TRULE__ABSTRACT:
                setAbstract(((Boolean)newValue).booleanValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                setTransformation((Transformation)null);
                return;
            case RuntimePackage.TRULE__SRC:
                setSrc((SourceTerm)null);
                return;
            case RuntimePackage.TRULE__TGT:
                getTgt().clear();
                return;
            case RuntimePackage.TRULE__EXTENDED:
                getExtended().clear();
                return;
            case RuntimePackage.TRULE__SUPERSEDED:
                getSuperseded().clear();
                return;
            case RuntimePackage.TRULE__ABSTRACT:
                setAbstract(ABSTRACT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RuntimePackage.TRULE__TRANSFORMATION:
                return getTransformation() != null;
            case RuntimePackage.TRULE__SRC:
                return src != null;
            case RuntimePackage.TRULE__TGT:
                return tgt != null && !tgt.isEmpty();
            case RuntimePackage.TRULE__EXTENDED:
                return extended != null && !extended.isEmpty();
            case RuntimePackage.TRULE__SUPERSEDED:
                return superseded != null && !superseded.isEmpty();
            case RuntimePackage.TRULE__ABSTRACT:
                return abstract_ != ABSTRACT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        return "RULE " + getName();
    }

    
    public Collection getGoal() {
        Collection goal = new ArrayList();
        
        goal.addAll(getSourceTerms());
        goal.addAll(getOverrideTerms());
        goal.addAll(getTargetTerms());
        
        return goal;
    }
    
    /**
     * Returns all the source Terms in the transitive closure of the extends and
     * supersedes relationship of the TRule.
     * 
     * @return
     */
    private Collection getSourceTerms() {
        Map sourceTermsMap = ((TransformationImpl) getTransformation()).getSourceTermsMap();
        List sourceTerms = (List) sourceTermsMap.get(this);
        if (null == sourceTerms) {
            sourceTerms = new ArrayList();
            sourceTermsMap.put(this, sourceTerms);

            Term srcTerm = getSrc();
            if (null != srcTerm) {
                sourceTerms.add(srcTerm);
            }

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRuleImpl extRule = (TRuleImpl) itr.next();
                sourceTerms.addAll(extRule.getSourceTerms());
            }

            for (Iterator itr = getSuperseded().iterator(); itr.hasNext();) {
                TRuleImpl supRule = (TRuleImpl) itr.next();
                sourceTerms.addAll(supRule.getSourceTerms());
            }
        }
        return sourceTerms;
    }

    /**
     * Returns all the overriding Terms in the transitive closure of the
     * extends and supersedes relationship of the TRule.
     * 
     * @return
     */
    private Collection getOverrideTerms() {
        Map overrideTermsMap = ((TransformationImpl) getTransformation()).getOverrideTermsMap();
        List overrideTerms = (List) overrideTermsMap.get(this);
        if (null == overrideTerms) {
            overrideTerms = new ArrayList();
            overrideTermsMap.put(this, overrideTerms);

            Collection supersedingRules = getTransformation().getSupersedingRules(this);
            for (Iterator itr = supersedingRules.iterator(); itr.hasNext();) {
                TRuleImpl supersedingRule = (TRuleImpl) itr.next();

                NotTerm override = RuntimeFactory.eINSTANCE.createNotTerm();
                override.getTerm().addAll(supersedingRule.getSourceTerms());
                overrideTerms.add(override);
            }

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRuleImpl extRule = (TRuleImpl) itr.next();
                overrideTerms.addAll(extRule.getOverrideTerms());
            }
        }

        return overrideTerms;
    }

    /**
     * Returns all the target Terms in the transitive closure of the extends
     * relationship of the TRule.
     * 
     * @return
     */
    private Collection getTargetTerms() {
        Map targetTermsMap = ((TransformationImpl) getTransformation()).getTargetTermsMap();
        List targetTerms = (List) targetTermsMap.get(this);
        if (null == targetTerms) {
            targetTerms = new ArrayList();
            targetTermsMap.put(this, targetTerms);

            targetTerms.addAll(getTgt());

            for (Iterator itr = getExtended().iterator(); itr.hasNext();) {
                TRuleImpl extRule = (TRuleImpl) itr.next();
                targetTerms.addAll(extRule.getTargetTerms());
            }
        }
        return targetTerms;
    }

} //TRuleImpl