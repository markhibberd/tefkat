/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;

import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.OrTerm;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.Term;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Or Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class OrTermImpl extends CompoundTermImpl implements OrTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OrTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.OR_TERM;
    }

    public void match(Context context) throws ResolutionException {
        /**
         *  Create a node for each disjunct, distributing them into
         *  the remaining conjuncts of the goal.
         */
        Collection terms = getTerm();
        if (null == terms || terms.isEmpty()) {
//            context.error("Malformed (empty) OrTerm");
            context.fail();
        }

        for (Iterator itr = terms.iterator(); itr.hasNext(); ) {
            context.createBranch((Term) itr.next());
        }
    }

} //OrTermImpl