/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tefkat.engine.runtime.NamespaceDeclaration;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.StratificationException;
import tefkat.engine.runtime.TRule;
import tefkat.engine.runtime.TefkatException;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.Transformation;
import tefkat.engine.runtime.VarScope;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.TransformationImpl#getTRule <em>TRule</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TransformationImpl#getImportedPackages <em>Imported Packages</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.TransformationImpl#getNamespaceDeclarations <em>Namespace Declarations</em>}</li>
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
    public static final String copyright = "Copyright michael lawley 2004";

    final private Map sourceTermsMap = new HashMap();
    final private Map targetTermsMap = new HashMap();
    final private Map overrideTermsMap = new HashMap();

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
     * The cached value of the '{@link #getNamespaceDeclarations() <em>Namespace Declarations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespaceDeclarations()
     * @generated
     * @ordered
     */
    protected EList namespaceDeclarations = null;

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
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.TRANSFORMATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTRule() {
        if (tRule == null) {
            tRule = new EObjectContainmentWithInverseEList(TRule.class, this, RuntimePackage.TRANSFORMATION__TRULE, RuntimePackage.TRULE__TRANSFORMATION);
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
            importedPackages = new EDataTypeUniqueEList(String.class, this, RuntimePackage.TRANSFORMATION__IMPORTED_PACKAGES);
        }
        return importedPackages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNamespaceDeclarations() {
        if (namespaceDeclarations == null) {
            namespaceDeclarations = new EObjectContainmentEList(NamespaceDeclaration.class, this, RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS);
        }
        return namespaceDeclarations;
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
            
            levelMapping.putInt(rule, 0);
            
            for (final Iterator termItr = goal.iterator(); termItr.hasNext(); ) {
                Term term = (Term) termItr.next();
                stratifier.check(rule, term);
            }
        }
        
        for (final Iterator patternItr = patterns.iterator(); patternItr.hasNext(); ) {
            PatternDefn pattern = (PatternDefn) patternItr.next();

            levelMapping.putInt(pattern, 0);
            
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
//                System.err.println(pair);
//                System.err.println(pair[0]);
//                System.err.println(pair[1]);
                int lidx = levelMapping.getInt(pair[0]);
                int gidx = levelMapping.getInt(pair[1]);
                if (lidx >= gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " >= " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + (lidx + 1));
                    levelMapping.putInt(pair[1], lidx + 1);
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
                int lidx = levelMapping.getInt(pair[0]);
                int gidx = levelMapping.getInt(pair[1]);
                if (lidx > gidx) {
//                    System.out.println("fix " + getName(pair[0]) + " > " + getName(pair[1]) + ": " + lidx + " " + gidx + " -> " + lidx);
                    levelMapping.putInt(pair[1], lidx);
                    if (lidx > maxLevel) {
                        maxLevel = lidx;
                    }
//                    levelMapping.put(pair[1], maxLevel);
                    done = false;
                }
                acyclic |= (0 == lidx);
            }
        }
        
        List[] strata = new List[maxLevel + 1];
        for (int level = 0; level < strata.length; level++) {
            strata[level] = new ArrayList();
        }
        
        for (final Iterator itr = rules.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.getInt(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }
        
        for (final Iterator itr = patterns.iterator(); itr.hasNext(); ) {
            Object scope = itr.next();
            int level = levelMapping.getInt(scope);
//            System.out.println(level + ": " + scope);
            strata[level].add(scope);
        }
        
        if (!done) {
            String s = orderString(maxLevel, levelMapping, less, lesseq);
            throw new StratificationException(strata, "Rules are not stratifiable: " + s);
        }
        
        return strata;
    }
    
    private String orderString(int maxLevel, IntMap levelMapping, List less, List lesseq) {
        StringBuffer sb = new StringBuffer();
        for (final Iterator lItr = less.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.getInt(pair[0]);
            int gidx = levelMapping.getInt(pair[1]);
            if (maxLevel == lidx && maxLevel == gidx) {
                sb.append(", ");
                sb.append(getName(pair[0]));
                sb.append(" < ");
                sb.append(getName(pair[1]));
            }
        }
        for (final Iterator lItr = lesseq.iterator(); lItr.hasNext(); ) {
            Object[] pair = (Object[]) lItr.next();
            int lidx = levelMapping.getInt(pair[0]);
            int gidx = levelMapping.getInt(pair[1]);
            if (maxLevel == lidx && maxLevel == gidx) {
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
    
    static class IntMap extends HashMap {
        
        private static final long serialVersionUID = -935093331755581828L;

        public IntMap(int size) {
            super(size);
        }

        public void putInt(Object key, int value) {
            put(key, Integer.valueOf(value));
        }

        int getInt(Object key) {
            final Integer value = ((Integer) get(key));
            if (null != value) {
                return value.intValue();
            } else {
                return 0;       // default value
            }
        }
        
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.TRANSFORMATION__TRULE:
                return ((InternalEList)getTRule()).basicAdd(otherEnd, msgs);
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
            case RuntimePackage.TRANSFORMATION__TRULE:
                return ((InternalEList)getTRule()).basicRemove(otherEnd, msgs);
            case RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS:
                return ((InternalEList)getNamespaceDeclarations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.TRANSFORMATION__TRULE:
                return getTRule();
            case RuntimePackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return getImportedPackages();
            case RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS:
                return getNamespaceDeclarations();
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
            case RuntimePackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                getTRule().addAll((Collection)newValue);
                return;
            case RuntimePackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                getImportedPackages().addAll((Collection)newValue);
                return;
            case RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS:
                getNamespaceDeclarations().clear();
                getNamespaceDeclarations().addAll((Collection)newValue);
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
            case RuntimePackage.TRANSFORMATION__TRULE:
                getTRule().clear();
                return;
            case RuntimePackage.TRANSFORMATION__IMPORTED_PACKAGES:
                getImportedPackages().clear();
                return;
            case RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS:
                getNamespaceDeclarations().clear();
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
            case RuntimePackage.TRANSFORMATION__TRULE:
                return tRule != null && !tRule.isEmpty();
            case RuntimePackage.TRANSFORMATION__IMPORTED_PACKAGES:
                return importedPackages != null && !importedPackages.isEmpty();
            case RuntimePackage.TRANSFORMATION__NAMESPACE_DECLARATIONS:
                return namespaceDeclarations != null && !namespaceDeclarations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        return "TRANSFORMATION " + getName();
    }

    Map getSourceTermsMap() {
        return sourceTermsMap;
    }

    Map getTargetTermsMap() {
        return targetTermsMap;
    }

    Map getOverrideTermsMap() {
        return overrideTermsMap;
    }

} //TransformationImpl