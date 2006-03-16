/*
 * Copyright (c) 2005- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 *
 */

package tefkat.engine;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import tefkat.model.AbstractVar;
import tefkat.model.internal.Util;


/**
 * For each tracking class:
 *   For each (direct & inherited) feature of the class:
 *     Create a value -> BitSet Map
 *
 *   Create an EObject ArrayList
 *
 *
 * For each instance of the class:
 *   Add to the ArrayList for the class
 *   For each feature of the class:
 *     Get the BitSet for the feature value
 *     Flip the bit corresponding to the arraylist index
 *
 * To handle inheritance, do the above for each superclass of the
 * instance as well.
 *
 *
 * To query:
 *   for each supplied non-Var feature value, get the corresponding BitSet
 *   AND the BitSets
 *   For each bit set in the result, get the object at index in the ArrayList
 *
 * 
 */
class TrackingManager {
    
    static interface Callback {
        public void match(EObject instance, Binding context);
    }
    
    Map instancesMap = new HashMap();
    Map indexMap = new HashMap();
    
    void query(EClass cls, Object[] query, Callback callback) throws ResolutionException {
        List instances = (List) instancesMap.get(cls);
        if (null == instances) {
            return;
        }
        
        // Initially, every instance is a candidate
        BitSet candidates = new BitSet();
        candidates.set(0, instances.size(), true);
        
        queryCandidates(cls, query, candidates, callback);
    }
    
    void query(EClass cls, Object[] query, EObject instance, Callback callback) throws ResolutionException {
        List instances = (List) instancesMap.get(cls);
        if (null == instances) {
            return;
        }
        
        // Initially, every instance is a candidate
        BitSet candidates = new BitSet();
        int index = instances.indexOf(instance);
        candidates.set(index, true);
        
        queryCandidates(cls, query, candidates, callback);
    }
    
    private void queryCandidates(EClass cls, Object[] query, BitSet candidates, Callback callback) throws ResolutionException {
        List instances = (List) instancesMap.get(cls);
        if (null == instances) {
            return;
        }

        List matches = new ArrayList();
        matches.add(candidates);
        
        // The set of feature, var pairs to get bindings for
        List vars = new ArrayList();
        
        for (int i = 0; i < query.length; i++) {
            Object[] field = (Object[]) query[i];
            EStructuralFeature feature = Util.getFeature(cls, (String) field[0]);
            List values = (List) field[1];

            if (values.size() == 1 && values.get(0) instanceof WrappedVar) {
                // Vars match everything
                vars.add(new Object[] {feature, ((WrappedVar) values.get(0)).getVar()});
            } else {
                List newMatches = new ArrayList();
                for (Iterator itr = values.iterator(); itr.hasNext(); ) {
                    Object value = itr.next();
                    BitSetKey key = new BitSetKey(cls, feature, value);
                    BitSet bs = (BitSet) indexMap.get(key);

                    for (Iterator mItr = matches.iterator(); mItr.hasNext(); ) {
                        BitSet mbs = (BitSet) mItr.next();
                        if (mbs.intersects(bs)) {
                            BitSet nbs = (BitSet) bs.clone();
                            nbs.and(mbs);
                            newMatches.add(nbs);
                        }
                    }
                }
                if (newMatches.size() == 0) {
                    return;
                }
                matches = newMatches;
            }
        }
        
        BitSet result = new BitSet();
        for (Iterator itr = matches.iterator(); itr.hasNext(); ) {
            BitSet bs = (BitSet) itr.next();
            result.or(bs);
        }
        
        for (int idx = result.nextSetBit(0); idx >= 0; idx = result.nextSetBit(idx + 1)) {
            EObject instance = (EObject) instances.get(idx);

            List oldBindings = new ArrayList();
            oldBindings.add(new Binding());
            
            for (Iterator fvItr = vars.iterator(); fvItr.hasNext(); ) {
                Object[] fv = (Object[]) fvItr.next();
                EStructuralFeature feature = (EStructuralFeature) fv[0];
                AbstractVar var = (AbstractVar) fv[1];
                Object value = instance.eGet(feature);
                List newBindings = null;

                if (feature.isMany()) {
                    newBindings = new ArrayList();
                    for (Iterator bindingItr = oldBindings.iterator(); bindingItr.hasNext(); ) {
                        Binding oldUnifier = (Binding) bindingItr.next();
                        for (Iterator valueItr = ((List) value).iterator(); valueItr.hasNext(); ) {
                            Binding unifier = new Binding(oldUnifier);
                            unifier.add(var, valueItr.next());
                            newBindings.add(unifier);
                        }
                    }
                    ExtentUtil.highlightEdge(instance, value, ExtentUtil.FEATURE_LOOKUP);
                } else {
                    for (Iterator bItr = oldBindings.iterator(); bItr.hasNext(); ) {
                        Binding binding = (Binding) bItr.next();
                        binding.add(var, value);
                    }
                }
            }
        }
        
        return;
    }
    
    /**
     * Assumes this method has not previously been called
     * with the same cls,instance combination and that all the
     * features of the instance have been set and will not change.
     * 
     * TODO check that static/dynamic instances won't hurt us
     * 
     * @param cls
     * @param instance
     */
    void add(EClass cls, EObject instance) {
        List instances = (List) instancesMap.get(cls);
        if (null == instances) {
            instances = new ArrayList();
            instancesMap.put(cls, instance);
        }
        int index = instances.size();
        instances.add(instance);
        
        for (Iterator itr = cls.getEAllStructuralFeatures().iterator(); itr.hasNext(); ) {
            EStructuralFeature feature = (EStructuralFeature) itr.next();
            Object value = instance.eGet(feature);
            
            BitSetKey key = new BitSetKey(cls, feature, value);
            BitSet bs = (BitSet) indexMap.get(key);
            if (null == bs) {
                bs = new BitSet();
                indexMap.put(key, bs);
            }
            
            bs.set(index, true);
        }
        
        for (Iterator itr = cls.getESuperTypes().iterator(); itr.hasNext(); ) {
            EClass supercls = (EClass) itr.next();
            add(supercls, instance);
        }
    }
    
    static class BitSetKey {
        
        private final int hashCode;
        private final EClass cls;
        private final EStructuralFeature feature;
        private final Object value;
        
        BitSetKey(EClass cls, EStructuralFeature feature, Object value) {
            this.cls = cls;
            this.feature = feature;
            this.value = value;
            hashCode = cls.hashCode() ^ feature.hashCode() ^ value.hashCode();
        }
        
        public int hashCode() {
            return hashCode;
        }
        
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other instanceof BitSetKey) {
                BitSetKey bs = (BitSetKey) other;
                return cls.equals(bs.cls) &&
                        feature.equals(bs.feature) &&
                        value.equals(bs.value);
            }
            return false;
        }
    }
}
