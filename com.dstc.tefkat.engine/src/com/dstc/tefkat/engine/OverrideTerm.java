/*
 * Copyright (c) 2004- michael lawley and others.
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

package com.dstc.tefkat.engine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import com.dstc.tefkat.model.CompoundTerm;
import com.dstc.tefkat.model.ExtentVar;
import com.dstc.tefkat.model.PatternDefn;
import com.dstc.tefkat.model.Query;
import com.dstc.tefkat.model.Term;

/**
 * @author lawley
 *
 */
public class OverrideTerm implements Term {

    private static final String UNIMPLEMENTED_METHOD = "This should never be called.";
    private List terms;
    
    public List getNegatedTerms() {
        if (null == terms) {
            terms = new ArrayList();
        }
        return terms;
    }
    
    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#getPatternDefn()
     */
    public PatternDefn getPatternDefn() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#setPatternDefn(com.dstc.tefkat.model.PatternDefn)
     */
    public void setPatternDefn(PatternDefn value) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#getQuery()
     */
    public Query getQuery() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#setQuery(com.dstc.tefkat.model.Query)
     */
    public void setQuery(Query value) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#getCompoundTerm()
     */
    public CompoundTerm getCompoundTerm() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#setCompoundTerm(com.dstc.tefkat.model.CompoundTerm)
     */
    public void setCompoundTerm(CompoundTerm value) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#getContext()
     */
    public ExtentVar getContext() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#setContext(com.dstc.tefkat.model.ExtentVar)
     */
    public void setContext(ExtentVar value) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.Term#getExtent()
     */
    public ExtentVar getExtent() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eClass()
     */
    public EClass eClass() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eResource()
     */
    public Resource eResource() {
        if (getNegatedTerms().size() > 0) {
            return ((EObject) getNegatedTerms().get(0)).eResource();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainer()
     */
    public EObject eContainer() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
     */
    public EStructuralFeature eContainingFeature() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
     */
    public EReference eContainmentFeature() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eContents()
     */
    public EList eContents() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eAllContents()
     */
    public TreeIterator eAllContents() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eIsProxy()
     */
    public boolean eIsProxy() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
     */
    public EList eCrossReferences() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public Object eGet(EStructuralFeature feature) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean)
     */
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    public void eSet(EStructuralFeature feature, Object newValue) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public boolean eIsSet(EStructuralFeature feature) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
     */
    public void eUnset(EStructuralFeature feature) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
     */
    public EList eAdapters() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
     */
    public boolean eDeliver() {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eSetDeliver(boolean)
     */
    public void eSetDeliver(boolean deliver) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.Notification)
     */
    public void eNotify(Notification notification) {
        throw new UnsupportedOperationException(UNIMPLEMENTED_METHOD);
    }

    public String toString() {
        return "NEG " + getNegatedTerms();
    }
}
