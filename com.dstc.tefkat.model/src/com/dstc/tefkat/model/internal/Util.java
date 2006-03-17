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
package com.dstc.tefkat.model.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author lawley
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class Util {

    public final static String getFullyQualifiedName(Class klass) {
        return klass.getName();
    }

    public final static String getFullyQualifiedName(EClassifier eClassifier) {
        return getFullyQualifiedName(eClassifier.getEPackage()) + "::"
                + eClassifier.getName();
    }

    final static protected String getFullyQualifiedName(EPackage ePackage) {
        String name = "";
        while (null != ePackage) {
            name = "::" + ePackage.getName() + name;
            ePackage = ePackage.getESuperPackage();
        }
        return name;
    }

    public final static String getFullyQualifiedName(
            EStructuralFeature eFeature) {
        return getFullyQualifiedName(eFeature.getEContainingClass()) + "."
                + eFeature.getName();
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
    public final static Map buildNameMaps(Collection resources) {
        return buildNameMaps(resources, new HashMap());
    }
    
    public final static Map buildNameMaps(Collection resources, Map nameMap) {
        for (Iterator itr = resources.iterator(); itr.hasNext();) {
            Resource res = (Resource) itr.next();
            // Do a tree iteration over the Resource, pruning on every
            // non-EPackage since only EPackages can (transitively) contain
            // EClassifiers
            for (TreeIterator resItr = res.getAllContents(); resItr.hasNext();) {
                EObject obj = (EObject) resItr.next();
                if (obj instanceof EClassifier) {
                    EClassifier eClassifier = (EClassifier) obj;

                    String fqName = Util.getFullyQualifiedName(eClassifier);
                    nameMap.put(fqName, eClassifier);

                    String name = eClassifier.getName();
                    if (nameMap.containsKey(name) && !eClassifier.equals(nameMap.get(name))) {
                        // Record a name-clash by storing a List of the clashing things
                        Object clashObj = nameMap.get(name);
                        if (clashObj instanceof List) {
                            ((List) clashObj).add(fqName);
                        } else {
                            List allNames = new ArrayList();
                            allNames.add(Util.getFullyQualifiedName((EClassifier) clashObj));
                            allNames.add(fqName);
                            nameMap.put(name, allNames);
                        }
                    } else {
                        nameMap.put(name, eClassifier);
                    }

//                    if (obj instanceof EEnum) {
//                        EEnum eEnum = (EEnum) obj;
//                        for (Iterator eItr = eEnum.getELiterals().iterator(); eItr.hasNext(); ) {
//                            EEnumLiteral eLiteral = (EEnumLiteral) eItr.next();
//                            nameMap.put(fqName + "::" + eLiteral.getName(), eLiteral);
//                        }
//                    }
                    
                    resItr.prune();
                } else if (!(obj instanceof EPackage)) {
                    resItr.prune();
                }
            }
        }
        return nameMap;
    }

}