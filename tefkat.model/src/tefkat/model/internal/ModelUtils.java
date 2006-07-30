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
package tefkat.model.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import tefkat.model.CompoundTerm;
import tefkat.model.PatternDefn;
import tefkat.model.TRule;
import tefkat.model.TefkatException;
import tefkat.model.Term;
import tefkat.model.TrackingUse;
import tefkat.model.Transformation;

/**
 * Some generic functions for interacting with Tefkat transformations and models.
 * 
 * @author lawley
 * 
 */
public abstract class ModelUtils {
    
    public final static String getFullyQualifiedName(Class klass) {
        return klass.getName();
    }
    
    public final static String getFullyQualifiedName(EClassifier eClassifier) {
        return getFullyQualifiedName(eClassifier.getEPackage()) + "::" + eClassifier.getName();
    }
    
    final static protected String getFullyQualifiedName(EPackage ePackage) {
        String name = "";
        while (null != ePackage) {
            name = "::" + ePackage.getName() + name;
            ePackage = ePackage.getESuperPackage();
        }
        return name;
    }
    
    public final static String getFullyQualifiedName(EStructuralFeature eFeature) {
        return getFullyQualifiedName(eFeature.getEContainingClass()) + "." + eFeature.getName();
    }
    
    /**
     * @param node
     * @param klass
     * @param featureName
     * @return @throws
     *         ResolutionException
     */
    final public static EStructuralFeature getFeature(EClass klass, String featureName) {
        EStructuralFeature feature = klass.getEStructuralFeature(featureName);
        
        if (null == feature) {
            String featureNames = null;
            List allFeatures = klass.getEAllStructuralFeatures();
            for (Iterator itr = allFeatures.iterator(); itr.hasNext();) {
                EStructuralFeature esf = (EStructuralFeature) itr.next();
                if (null == featureNames) {
                    featureNames = esf.getName();
                } else {
                    featureNames = featureNames + ", " + esf.getName();
                }
            }
            throw new RuntimeException("Cannot find feature '"
                    + featureName + "' for '" + klass.getName()
                    + ".  Valid features are: " + featureNames);
        }
        return feature;
    }
    
    /**
     * Lookup the supplied name map (containing both unqualifed and fully-qualified names) for the corresponding EClass.
     * Returns null if it is not found.
     * 
     * @param nameMap
     * @param name
     * @return
     */
    public final static EClassifier findClassifierByName(Map nameMap, String name) {
        if (!nameMap.containsKey(name)) {
            return null;
        }
        Object obj = nameMap.get(name);
        if (obj instanceof List) {
            throw new RuntimeException("Ambiguous name: " + name + " resolves to " + obj);
        }
        return (EClassifier) obj;
    }
    
    /**
     * Given a set of resources containing EPackages and EClasses, build a Map of
     * names to EClasses using both the unqualified and fully-qualifed names.
     * 
     * If there is a name clash, put null in the map.
     * 
     * @param resources
     * @return
     */
    public final static Map buildNameMaps(Collection resources, String namespace) {
        return buildNameMaps(resources, new HashMap(), namespace);
    }
    
    public final static Map buildNameMaps(Collection resources, Map nameMap, String namespace) {
        XSDEcoreBuilder xsdEcoreBuilder = null;
        for (Iterator itr = resources.iterator(); itr.hasNext();) {
            Resource res = (Resource) itr.next();
            // Do a tree iteration over the Resource, pruning on every
            // non-EPackage since only EPackages can (transitively) contain
            // EClassifiers
            TreeIterator treeItr = res.getAllContents();
            xsdEcoreBuilder = buildNameMaps(treeItr, nameMap, namespace, res.getResourceSet(), xsdEcoreBuilder);
        }
        if (null != xsdEcoreBuilder) {
            for (Iterator itr = xsdEcoreBuilder.getTargetNamespaceToEPackageMap().values().iterator(); itr.hasNext();) {
                EPackage pkg = (EPackage) itr.next();
                xsdEcoreBuilder = buildNameMaps(pkg.eAllContents(), nameMap, namespace, null, xsdEcoreBuilder);
            }
        }
        
        return nameMap;
    }
    
    private static XSDEcoreBuilder buildNameMaps(TreeIterator treeItr, Map nameMap, String namespace, final ResourceSet resourceSet, XSDEcoreBuilder xsdEcoreBuilder) {
        while (treeItr.hasNext()) {
            EObject obj = (EObject) treeItr.next();
            if (obj instanceof EClassifier) {
                EClassifier eClassifier = (EClassifier) obj;
                
                String fqName = ModelUtils.getFullyQualifiedName(eClassifier);
                addToMap(nameMap, fqName, eClassifier);
                
                String name = eClassifier.getName();
                addToMap(nameMap, name, eClassifier);
                
                if (null != namespace) {
                    addToMap(nameMap, '^' + namespace + fqName, eClassifier);
                    addToMap(nameMap, namespace + '^' + name, eClassifier);
                }
                
//              if (obj instanceof EEnum) {
//              EEnum eEnum = (EEnum) obj;
//              for (Iterator eItr = eEnum.getELiterals().iterator(); eItr.hasNext(); ) {
//              EEnumLiteral eLiteral = (EEnumLiteral) eItr.next();
//              nameMap.put(fqName + "::" + eLiteral.getName(), eLiteral);
//              }
//              }
            } else if (obj instanceof XSDSchema) {
                // Attempt to handle ecore models dynamically generated from xml schema 
                if (null == xsdEcoreBuilder) {
                    xsdEcoreBuilder = new XSDEcoreBuilder();
                }
                try {
                    xsdEcoreBuilder.generate((XSDSchema) obj);
                } catch (ClassCastException e) {
                    // FIXME ignore this for the moment -- it's patched in EMF's CVS HEAD
                    // see bugzilla -- https://bugs.eclipse.org/bugs/show_bug.cgi?id=136267
                    System.err.println(obj);
                    //e.printStackTrace();
                }
            }
            
            if (!(obj instanceof EPackage)) {
                treeItr.prune();
            }
        }
        return xsdEcoreBuilder;
    }
    
    private static void addToMap(Map nameMap, String name, EClassifier eClassifier) {
        if (nameMap.containsKey(name)) {
            if (!eClassifier.equals(nameMap.get(name))) {
                // Record a name-clash by storing a List of the clashing things
                Object clashObj = nameMap.get(name);
                if (clashObj instanceof List) {
                    ((List) clashObj).add(eClassifier);
                } else {
                    List allNames = new ArrayList();
                    allNames.add((EClassifier) clashObj);
                    allNames.add(eClassifier);
                    nameMap.put(name, allNames);
                }
            }
        } else {
            nameMap.put(name, eClassifier);
        }
    }
    
    public static void resolveTrackingClassNames(Transformation t, Map nameMap)
    throws TefkatException {
        for (Iterator itr = t.getPatternDefn().iterator(); itr.hasNext(); ) {
            PatternDefn pDefn = (PatternDefn) itr.next();
            resolveTrackingClassNames(pDefn.getTerm(), nameMap);
        }
        for (Iterator itr = t.getTRule().iterator(); itr.hasNext(); ) {
            TRule tRule = (TRule) itr.next();
            resolveTrackingClassNames(tRule.getSrc(), nameMap);
            resolveTrackingClassNames(tRule.getTgt(), nameMap);
        }
    }
    
    private static void resolveTrackingClassNames(List terms, Map nameMap)
    throws TefkatException {
        for (Iterator itr = terms.iterator(); itr.hasNext(); ) {
            Term term = (Term) itr.next();
            resolveTrackingClassNames(term, nameMap);
        }
    }
    
    private static void resolveTrackingClassNames(Term term, Map nameMap)
    throws TefkatException {
        if (term instanceof TrackingUse) {
            TrackingUse trackingUse = (TrackingUse) term;
            String tname = trackingUse.getTrackingName();
            EClassifier tracking = findClassifierByName(nameMap, tname);
            if (null == tracking) {
                throw new TefkatException("Undefined tracking class: " + tname);
            }
            if (!(tracking instanceof EClass)) {
                String type = (tracking instanceof EDataType) ? "an EDataType" :
                              (tracking instanceof EEnum) ? "an EEnum" :
                              "a " + tracking.getClass().getName();
                throw new TefkatException("Expected an EClass: " + tname + ", found " + type);
            }
            trackingUse.setTracking((EClass) tracking);
        } else if (term instanceof CompoundTerm) {
            CompoundTerm compoundTerm = (CompoundTerm) term;
            resolveTrackingClassNames(compoundTerm.getTerm(), nameMap);
        }
    }
    
}
