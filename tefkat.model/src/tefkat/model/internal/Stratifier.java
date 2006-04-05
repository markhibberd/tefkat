package tefkat.model.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;

import tefkat.model.CompoundTerm;
import tefkat.model.IfTerm;
import tefkat.model.NotTerm;
import tefkat.model.PatternDefn;
import tefkat.model.PatternUse;
import tefkat.model.Term;
import tefkat.model.TrackingUse;
import tefkat.model.VarScope;
import tefkat.model.util.TefkatSwitch;

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
public class Stratifier extends TefkatSwitch {
    public final Map readers = new HashMap();
    public final Map writers = new HashMap();
    public final Map neg_readers = new HashMap();
    public final Map neg_writers = new HashMap();
    
    boolean negated = false;
    VarScope scope;
    
    public void check(VarScope scope, Term term) {
        this.negated = false;
        this.scope = scope;
        
        check(term);
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
     * @see tefkat.model.util.TefkatSwitch#caseNotTerm(tefkat.model.NotTerm)
     */
    public Object caseNotTerm(NotTerm object) {
        return check((Term) object.getTerm().get(0), true);
    }
    
    /* (non-Javadoc)
     * @see tefkat.model.util.TefkatSwitch#caseIfTerm(tefkat.model.IfTerm)
     */
    public Object caseIfTerm(IfTerm object) {
        check((Term) object.getTerm().get(1));
        check((Term) object.getTerm().get(2));
        return check((Term) object.getTerm().get(0), true);
    }
    
    /**
     * Return the Set of dependency items to add the current scope (TRule or PatternDefn) to.
     * 
     * @see tefkat.model.util.TefkatSwitch#caseTrackingUse(tefkat.model.TrackingUse)
     */
    public Object caseTrackingUse(TrackingUse object) {
        EClass key = object.getTracking();
        List keys = null;
        Map map;
        if (negated) {
            if (object.isTarget()) {
                map = neg_writers;
                // Affects all subclasses as well
            } else {
                map = neg_readers;
                // Affected by all superclasses as well
                keys = key.getEAllSuperTypes();
            }
        } else {
            if (object.isTarget()) {
                map = writers;
                // Affects all subclasses as well
            } else {
                map = readers;
                // Affected by all superclasses as well
                keys = key.getEAllSuperTypes();
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
     * @see tefkat.model.util.TefkatSwitch#casePatternUse(tefkat.model.PatternUse)
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
        	map = readers;
        }
        store(map, key);
        return this;
    }
    
    /* (non-Javadoc)
     * @see tefkat.model.util.TefkatSwitch#caseCompoundTerm(tefkat.model.CompoundTerm)
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