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


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import tefkat.model.Var;
import tefkat.model.NotTerm;
import tefkat.model.TefkatPackage;
import tefkat.model.VarUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NotTermImpl extends CompoundTermImpl implements NotTerm {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NotTermImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TefkatPackage.Literals.NOT_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList getNonLocalVars() {
        EList nonLocalVars = new BasicEList();
        Set notVars = new HashSet();
        Set usages = new HashSet();

        // Find the Vars and VarUses that occur in the NOT
        for (Iterator itr = eAllContents(); itr.hasNext(); ) {
            Object obj = itr.next();
            
            if (obj instanceof VarUse) {
                notVars.add(((VarUse) obj).getVar());
                usages.add(obj);
            }
        }
        
        // Find just those Vars that are NOT only referenced in the NOT
        for (Iterator varItr = notVars.iterator(); varItr.hasNext(); ) {
            Var var = (Var) varItr.next();
            if (!usages.containsAll(var.getUsages())) {
                nonLocalVars.add(var);
            }
        }
        
        return nonLocalVars;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        return "not " + getTerm();
    }

} //NotTermImpl
