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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tefkat.model.TRule;
import tefkat.model.Transformation;
import tefkat.model.impl.TermImpl;


/**
 * By re-writing all the rules taking in to account the extends and supersedes
 * relationships we can construct a single goal (with asserts) that can be
 * evaluated to perform the transformation.
 * 
 * Note that this requires us to be smart during evaluation and to re-expand the
 * tree after asserts have been performed. This will also include the tricky
 * case of negation-as-failure having to be undone!
 * 
 * For R1: T1 <- S1, R2: T2 <- S2, R3: T3 <- S3 where R2 extends R1 and R3
 * supersedes R1, construct:
 * 
 * <pre>
 * 
 *   sourceXMap{R1 =&gt; [S1], R2 =&gt; [S2, S1], R3 =&gt; [S3]}
 *   sourceSMap{R1 =&gt; [sourceXMap(R1), &tilde;sourceXMap(R3)],
 *              R2 =&gt; [sourceXMap(R2)],
 *              R3 =&gt; [sourceXMap(R3)]}
 *   targetMap{R1 =&gt; [T1], R2 =&gt; [T2, T1], R3 =&gt; [T3]}
 *  
 * </pre>
 * 
 * Note, we need to include T1 in the targetMap for R2 since R1 might be
 * abstract, so we can't rely on T1 having already been established.
 */

public class TransformationEvaluator {

    private Map sourceXMap = new HashMap();

    private Map sourceSMap = new HashMap();

    private Map targetMap = new HashMap();

    public TransformationEvaluator() {
    }

    public void evaluate(Transformation transformation, Binding context) {
        buildSourceGoals(transformation);
        buildTargetGoals(transformation);
//        RTTerm goal = buildTransformationGoal(transformation);

        // resolve(goal, context);
    }

    /**
     * For each TRule that extends a TRule, accumulate its source terms
     */
    private void buildSourceGoals(Transformation transformation) {
        for (Iterator ruleItr = transformation.getTRule().iterator(); ruleItr
                .hasNext();) {
            TRule rule = (TRule) ruleItr.next();
            collectSourceXTerms(rule);
        }

        for (Iterator ruleItr = transformation.getTRule().iterator(); ruleItr
                .hasNext();) {
            TRule rule = (TRule) ruleItr.next();
            collectSourceSTerms(rule);
        }
    }

    /**
     * Construct a composite list of source terms from this rule and all of the
     * rules it extends.
     * 
     * TODO Handle TRuleVar extension (maybe we can avoid this by just using
     * direct references from the VarUses and relaxing the scope rules?)
     */
    private void collectSourceXTerms(TRule rule) {
        if (!sourceXMap.containsKey(rule)) {
            List terms = new ArrayList();
            terms.add(rule.getSrc());
            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                collectSourceXTerms(extRule);
                terms.addAll((List) sourceXMap.get(extRule));
            }
            sourceXMap.put(rule, terms);
        }
    }

    /**
     * Construct a composite list of source terms from this rule and all of the
     * rules it supersedes.
     * 
     * TODO Handle TRuleVar extension (maybe we can avoid this by just using
     * direct references from the VarUses and relaxing the scope rules?)
     * 
     * TODO FIXME check transitive superseding semantics
     */
    private void collectSourceSTerms(TRule rule) {
        if (!sourceSMap.containsKey(rule)) {
            List terms = new ArrayList();
            terms.addAll((List) sourceXMap.get(rule));
//            for (Iterator itr = rule.getSuperseder().iterator(); itr.hasNext();) {
//                TRule supRule = (TRule) itr.next();
//                RTTerm rtNotTerm = new RTNotTerm();
//                rtNotTerm.getTerms().addAll((List) sourceXMap.get(supRule));
//                terms.add(rtNotTerm);
//            }
            sourceSMap.put(rule, terms);
        } else {
            // should not get here
        }
    }

    private void buildTargetGoals(Transformation transformation) {
        for (Iterator ruleItr = transformation.getTRule().iterator(); ruleItr
                .hasNext();) {
            TRule rule = (TRule) ruleItr.next();
            collectTargetTerms(rule);
        }
    }

    /**
     * Construct a composite list of target terms from this rule and all of the
     * rules it extends.
     * 
     * TODO Handle TRuleVar extension (maybe we can avoid this by just using
     * direct references from the VarUses and relaxing the scope rules?)
     */
    private void collectTargetTerms(TRule rule) {
        if (!targetMap.containsKey(rule)) {
            RTTerm rtAssertTerm = new RTAssertTerm();
            List terms = rtAssertTerm.getTerms();
            terms.addAll(rule.getTgt());
            for (Iterator itr = rule.getExtended().iterator(); itr.hasNext();) {
                TRule extRule = (TRule) itr.next();
                collectTargetTerms(extRule);
                terms.addAll(((RTTerm) targetMap.get(extRule)).getTerms());
            }
            targetMap.put(rule, rtAssertTerm);
        }
    }

//    private RTTerm buildTransformationGoal(Transformation transformation) {
//        RTTerm rtGoal = new RTOrTerm();
//        List goalTerms = rtGoal.getTerms();
//        for (Iterator ruleItr = transformation.getTRule().iterator(); ruleItr
//                .hasNext();) {
//            TRule rule = (TRule) ruleItr.next();
//            if (true/* !rule.isAbstract() */) {     // TODO add abstract flag to model
//                RTTerm rtRule = new RTAndTerm();
//                List ruleTerms = rtRule.getTerms();
//                ruleTerms.addAll((List) sourceSMap.get(rule));
//                ruleTerms.add(targetMap.get(rule));
//                goalTerms.add(rtRule);
//            }
//        }
//
//        return rtGoal;
//    }

}

abstract class RTTerm extends TermImpl {
    private List terms = null;

    public List getTerms() {
        if (null == terms) {
            terms = new ArrayList();
        }
        return terms;
    }
}

class RTOrTerm extends RTTerm {
}

class RTAndTerm extends RTTerm {
}

/**
 * Represents the negation of the conjunction of contained terms.
 */

class RTNotTerm extends RTTerm {
}

/**
 * Represents the assertion of the contained term.
 */

class RTAssertTerm extends RTTerm {
}

