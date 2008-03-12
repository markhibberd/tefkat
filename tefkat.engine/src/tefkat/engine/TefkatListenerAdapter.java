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

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Transformation;


/**
 * @author lawley
 *
 */
public abstract class TefkatListenerAdapter implements TefkatListener {

    /**
     *
     */
    public TefkatListenerAdapter() {
        super();
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#started()
     */
    public void started() {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#stopped()
     */
    public void stopped() {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#breakpoint(tefkat.model.Term)
     */
    public void breakpoint(Term term) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#suspended()
     */
    public void suspended() {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#resumed()
     */
    public void resumed() {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#info(java.lang.String)
     */
    public void info(String message) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#warning(java.lang.String)
     */
    public void warning(String message) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#warning(java.lang.String)
     */
    public void error(String message, Throwable cause) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#transformationStarted(tefkat.model.Transformation, org.eclipse.emf.ecore.resource.Resource[], org.eclipse.emf.ecore.resource.Resource[], org.eclipse.emf.ecore.resource.Resource, tefkat.engine.Binding)
     */
    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#transformationFinished()
     */
    public void transformationFinished() {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#transformationFinished()
     */
    public void transformationFinished(Transformation transformation) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#resourceLoaded(org.eclipse.emf.ecore.resource.Resource)
     */
    public void resourceLoaded(Resource res) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#evaluateRule(tefkat.model.TRule, tefkat.engine.Binding, boolean)
     */
    public void evaluateRule(TRule rule, Binding context, boolean cached) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#evaluateSource(tefkat.model.TRule, tefkat.engine.Binding)
     */
    public void evaluateSource(TRule rule, Binding context) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#evaluateTarget(tefkat.model.TRule, tefkat.engine.Binding)
     */
    public void evaluateTarget(TRule rule, Binding context) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#enterTree(tefkat.engine.Tree)
     */
    public void enterTree(Tree tree) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#exitTree(tefkat.engine.Tree)
     */
    public void exitTree(Tree tree) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#enterTerm(tefkat.engine.Node)
     */
    public void enterTerm(Node node) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#exitTerm(tefkat.engine.Node)
     */
    public void exitTerm(Node node) {
    }

    /* (non-Javadoc)
     * @see tefkat.engine.TefkatListener#delayTerm(tefkat.engine.Node)
     */
    public void delayTerm(Node node) {
    }

}
