package tefkat.engine.runtime.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;

import tefkat.engine.runtime.CompoundTerm;
import tefkat.engine.runtime.IfTerm;
import tefkat.engine.runtime.NotTerm;
import tefkat.engine.runtime.PatternDefn;
import tefkat.engine.runtime.PatternUse;
import tefkat.engine.runtime.TefkatException;
import tefkat.engine.runtime.Term;
import tefkat.engine.runtime.TrackingUse;
import tefkat.engine.runtime.VarScope;
import tefkat.engine.runtime.util.RuntimeSwitch;

/**
 * Stratification is determined with respect to the tracking classes
 * referenced in TrackingUse terms.  Each of readers, writers, neg_readers,
 * and neg_writers maps from either a type (EClass) to a set of VarScopes (TRule
 * or PatternDefn) that have the associated dependency on that type, or from
 * a PatternDefn to a set of VarScopes that have the associated dependency on that
 * PatternDefn (to capture transitive dependencies).
 * 
 * @author lawley
 *
 */
public class Stratifier extends RuntimeSwitch {
    public final Map readers = new HashMap();
    public final Map writers = new HashMap();
    public final Map neg_readers = new HashMap();
    public final Map neg_writers = new HashMap();
    
    boolean negated = false;
    VarScope scope;
    
    public void check(VarScope scope, Term term) throws TefkatException {
        this.negated = false;
        this.scope = scope;
        
        try {
            check(term);
        } catch (IllegalStateException e) {
            throw new TefkatException("Illegal Transformation specification.", e);
        }
    }
    
    private void store(Map map, Object key) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet());
        }
        Set set = (Set) map.get(key);
        set.add(scope);
    }
    
    private Object check(Term term, boolean newNegated) {
        boolean oldNegated = negated;
        negated = newNegated;
        Object result = check(term);
        negated = oldNegated;
        return result;
    }

    private Object check(Term term) {
        return doSwitch(term);
    }

    /* (non-Javadoc)
     * @see tefkat.model.util.RuntimeSwitch#caseNotTerm(tefkat.model.NotTerm)
     */
    public Object caseNotTerm(NotTerm object) {
        return check((Term) object.getTerm().get(0), true);
    }
    
    /* (non-Javadoc)
     * @see tefkat.model.util.RuntimeSwitch#caseIfTerm(tefkat.model.IfTerm)
     */
    public Object caseIfTerm(IfTerm object) {
        check((Term) object.getTerm().get(1));
        check((Term) object.getTerm().get(2));
        return check((Term) object.getTerm().get(0), true);
    }
    
    /**
     * Return the Set of dependency items to add the current scope (TRule or PatternDefn) to.
     * @throws TefkatException 
     * 
     * @see tefkat.model.util.RuntimeSwitch#caseTrackingUse(tefkat.model.TrackingUse)
     */
    public Object caseTrackingUse(TrackingUse object) {
        EClass key = object.getTracking();
        List keys = null;
        Map map;
        if (negated) {
            if (object.isTarget()) {
                throw new IllegalStateException("Cannot create a tracking instance inside a negation: " + object);
            } else {
                map = neg_readers;
                // Affected by all superclasses as well
            }
        } else {
            if (object.isTarget()) {
                map = writers;
                // Affects all subclasses as well
                keys = key.getEAllSuperTypes();
            } else {
                map = readers;
                // Affected by all superclasses as well
            }
        }
        store(map, key);
        if (null != keys) {
            for (final Iterator itr = keys.iterator(); itr.hasNext(); ) {
                store(map, itr.next());
            }
        }
        return this;
    }
    
    /* (non-Javadoc)
     * @see tefkat.model.util.RuntimeSwitch#casePatternUse(tefkat.model.PatternUse)
     */
    public Object casePatternUse(PatternUse object) {
        PatternDefn key = object.getDefn();
        if (null == key) {
            // special case for "println"
            return null;
        }
        Map map;
        /*
         * A Rule/Pattern cannot be in a stratum less than that of
         * a Pattern that it invokes.
         */
        if (negated) {
            map = neg_readers;
        } else {
            // FIXME - need to handle TEMPLATEs specially -- "construct"
            // transitive call patterns to propagate direct dependencies.
            map = readers;
        }
        store(map, key);
        return this;
    }
    
    /* (non-Javadoc)
     * @see tefkat.model.util.RuntimeSwitch#caseCompoundTerm(tefkat.model.CompoundTerm)
     */
    public Object caseCompoundTerm(CompoundTerm object) {
        List terms = object.getTerm();
        for (final Iterator termItr = terms.iterator(); termItr.hasNext(); ) {
            Term term = (Term) termItr.next();
            check(term);
        }
        return null;
    }

}

