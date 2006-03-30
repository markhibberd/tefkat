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
 */
package tefkat.model.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.model.PatternDefn;
import tefkat.model.StratificationException;
import tefkat.model.TRule;
import tefkat.model.TefkatException;
import tefkat.model.TefkatPackage;
import tefkat.model.Term;
import tefkat.model.Transformation;
import tefkat.model.VarScope;
import tefkat.model.internal.IntMap;
import tefkat.model.internal.Stratifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.model.impl.TransformationImpl#getTRule <em>TRule</em>}</li>
 *   <li>{@link tefkat.model.impl.TransformationImpl#getImportedPackages <em>Imported Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationImpl extends PatternScopeImpl implements Transformation {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2005";
    
    /**
     * The cached value of the '{@link #getTRule() <em>TRule</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTRule()
     * @generated
     * @ordered
     */
    protected EList tRule = null;
    
    /**
     * The cached value of the '{@link #getImportedPackages() <em>Imported Packages</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImportedPackages()
     * @generated
     * @ordered
     */
    protected EList importedPackages = null;
    
    /**
     * Maps from a TRule to a Collection of the TRules that supersede it
     * with respect to <em>this</em> Transformation.
     */
    private Map invertedSupersedesMap = new HashMap();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TransformationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void addSupersedes(TRule superseder, TRule superseded) {
        Collection supersedingRules = getSupersedingRules(superseded);
        supersedingRules.add(superseder);
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void removeSupersedes(TRule superseder, TRule superseded) {
        Collection supersedingRules = (Collection) invertedSupersedesMap.get(superseded);
        if (null == supersedingRules) {
            throw new Error("invertedSupersedesMap should contain entry for " + superseded);
        }
        supersedingRules.remove(superseder);
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Collection getSupersedingRules(TRule superseded) {
        Collection supersedingRules = (Collection) invertedSupersedesMap.get(superseded);
        if (null == supersedingRules) {
            supersedingRules = new ArrayList();
            invertedSupersedesMap.put(superseded, supersedingRules);
        }
        return supersedingRules;
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.eINSTANCE.getTransformation();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTRule() {
        if (tRule == null) {
            tRule = new EObjectContainmentWithInverseEList(TRule.class, this, TefkatPackage.TRANSFORMATION__TRULE, TefkatPackage.TRULE__TRANSFORMATION);
        }
        return tRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getImportedPackages() {
        if (importedPackages == null) {
            importedPackages = new EDataTypeUniqueEList(String.class, this, TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES);
        }
        return importedPackages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public List[] getStrata() throws TefkatException {
        // FIXME handle inheritance of tracking classes
        Collection rules = getTRule();
        Collection patterns = getPatternDefn();
        
        final IntMap levelMapping = new IntMap(2 * (rules.size() + patterns.size()) + 1);
        final Stratifier stratifier = new Stratifier();
        
        for (final Iterator ruleItr = rules.iterator(); ruleItr.hasNext(); ) {
            TRule rule = (TRule) ruleItr.next();
            Collection goal = rule.getGoal();
            
            levelMapping.put(rule, 0);
            
            for (final Iterator termItr = goal.iterator(); termItr.hasNext(); ) {
                Term term = (Term) termItr.next();
                stratifier.check(rule, term);
            }
        }
        
        for (final Iterator patternItr = patterns.iterator(); patternItr.hasNext(); ) {
            PatternDefn pattern = (PatternDefn) patternItr.next();

            levelMapping.put(pattern, 0);
            
            stratifier.check(pattern, pattern.getTerm());
        }
        
        List less = new ArrayList();
        List lesseq = new ArrayList();

//        fireInfo("    finding dependencies...");
        
        // FIXME - handle subtyping and stratification
        
        for (final Iterator itr = stratifier.readers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                lesseq.add(new Object[] {tc, scope});
//                System.out.println("PR: " + tc + " <= " + scope);
            }
        }

        for (final Iterator itr = stratifier.writers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                lesseq.add(new Object[] {scope, tc});
//                System.out.println("PW: " + scope + " <= " + tc);
            }
        }

        for (final Iterator itr = stratifier.neg_readers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                less.add(new Object[] {tc, scope});
//                System.out.println("NR: " + tc + " < " + scope);
            }
        }

        for (final Iterator itr = stratifier.neg_writers.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            Object tc = entry.getKey();
            Set set = (Set) entry.getValue();
            for (final Iterator sItr = set.iterator(); sItr.hasNext(); ) {
                Object scope = sItr.next();
                less.add(new Object[] {tc, scope});
//                System.out.println("NW: " + tc + " < " + scope);
            }
        }

//        fireInfo("    populating strata...");

        boolean done = false;
        
        int maxLevel = 0;
        for (boolean acyclic = true; !done && acyclic && maxLevel <= levelMapping.size(); ) {
            done = true;
            acyclic = false;
            for (final Iterator lItr = less.iterator(); lItr.hasNext(); ) {
                Object[] pair = (Object[]) lItr.next();
                int lidx = levelMapping.get(pair[0]);
                int gidx = levelMapping.get(pair[1]);
                if (lidx >= gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " >= " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + (lidx + 1));
                    levelMapping.put(pair[1], lidx + 1);
                    if (lidx >= maxLevel) {
                        maxLevel = lidx + 1;
                    }
//                    levelMapping.put(pair[1], maxLevel);
                    done = false;
                }
                acyclic |= (0 == lidx);
            }
            
            for (final Iterator lItr = lesseq.iterator(); lItr.hasNext(); ) {
                Object[] pair = (Object[]) lItr.next();
                int lidx = levelMapping.get(pair[0]);
                int gidx = levelMapping.get(pair[1]);
                if (lidx > gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " > " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + lidx);
                    levelMapping.put(pair[1], lidx);
                    if (lidx > maxLevel) {
                        maxLevel = lidx;
                    }
//                    levelMapping.put(pair[1], maxLevel);
                    done = false;
                }
                acyclic |= (0 == lidx);
            }
        }
        
        if (!done) {
            String s = orderString(levelMapping, less, lesseq);
            throw new StratificationException("Rules are not stratifiable: max level = " + maxLevel + s);
        }
        
        List[] strata = new List[maxLevel + 1];
        for (int level = 0; level < strata.length; level++) {
            strata[level] = new ArrayList();
        }
        
        for (final Iterator itr = rules.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.get(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }
        
        for (final Iterator itr = patterns.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.get(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }
        
        return strata;
    }
    
    private String orderString(final IntMap levelMapping, List less, List lesseq) {
        StringBuffer sb = new StringBuffer();
        for (final Iterator lItr = less.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.get(pair[0]);
            int gidx = levelMapping.get(pair[1]);
            if (true || lidx >= gidx) {
                sb.append(", ");
                sb.append(getName(pair[0]));
                sb.append(" < ");
                sb.append(getName(pair[1]));
            }
        }
        for (final Iterator lItr = lesseq.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.get(pair[0]);
            int gidx = levelMapping.get(pair[1]);
            if (true || lidx > gidx) {
                sb.append(", ");
                sb.append(getName(pair[0]));
                sb.append(" <= ");
                sb.append(getName(pair[1]));
            }
        }
        return sb.toString();
    }
    
    private String getName(Object obj) {
        if (obj instanceof EClass) {
            return ((EClass) obj).getName();
        } else if (obj instanceof VarScope) {
            return ((VarScope) obj).getName();
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRANSFORMATION__VARS:
                    return ((InternalEList)getVars()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicAdd(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__TRULE:
                    return ((InternalEList)getTRule()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TefkatPackage.TRANSFORMATION__VARS:
                    return ((InternalEList)getVars()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                    return ((InternalEList)getPatternDefn()).basicRemove(otherEnd, msgs);
                case TefkatPackage.TRANSFORMATION__TRULE:
                    return ((InternalEList)getTRule()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRANSFORMATION__VARS:
                return getVars();
            case TefkatPackage.TRANSFORMATION__NAME:
                return getName();
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                return getComments();
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                return getPatternDefn();
            case TefkatPackage.TRANSFORMATION__TRULE:
                return getTRule();
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return getImportedPackages();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRANSFORMATION__VARS:
                getVars().clear();
                getVars().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__NAME:
                setName((String)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                getComments().clear();
                getComments().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                getPatternDefn().clear();
                getPatternDefn().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                getTRule().addAll((Collection)newValue);
                return;
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                getImportedPackages().addAll((Collection)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRANSFORMATION__VARS:
                getVars().clear();
                return;
            case TefkatPackage.TRANSFORMATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                getComments().clear();
                return;
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                getPatternDefn().clear();
                return;
            case TefkatPackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                return;
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TefkatPackage.TRANSFORMATION__VARS:
                return vars != null && !vars.isEmpty();
            case TefkatPackage.TRANSFORMATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TefkatPackage.TRANSFORMATION__COMMENTS:
                return comments != null && !comments.isEmpty();
            case TefkatPackage.TRANSFORMATION__PATTERN_DEFN:
                return patternDefn != null && !patternDefn.isEmpty();
            case TefkatPackage.TRANSFORMATION__TRULE:
                return tRule != null && !tRule.isEmpty();
            case TefkatPackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return importedPackages != null && !importedPackages.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();
        
        String str = "";
        String sep = "";
        for (Iterator itr = getVars().iterator(); itr.hasNext(); ) {
            str = str + sep + itr.next();
            sep = ", ";
        }
        
        return name + "(" + str + ")";
    }
    
} //TransformationImpl
