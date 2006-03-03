/*
 * Created on 3/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.engine;

import org.eclipse.emf.ecore.resource.Resource;

import com.dstc.tefkat.model.Extent;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.Term;
import com.dstc.tefkat.model.Transformation;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class TefkatListenerAdapter implements TefkatListener {

    /**
     * 
     */
    public TefkatListenerAdapter() {
        super();
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#started()
     */
    public void started() {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#stopped()
     */
    public void stopped() {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#breakpoint(com.dstc.tefkat.model.Term)
     */
    public void breakpoint(Term term) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#suspended()
     */
    public void suspended() {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#resumed()
     */
    public void resumed() {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#info(java.lang.String)
     */
    public void info(String message) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#warning(java.lang.String)
     */
    public void warning(String message) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#warning(java.lang.String)
     */
    public void error(String message, Throwable cause) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#transformationStarted(com.dstc.tefkat.model.Transformation, org.eclipse.emf.ecore.resource.Resource[], org.eclipse.emf.ecore.resource.Resource[], org.eclipse.emf.ecore.resource.Resource, com.dstc.tefkat.engine.Binding)
     */
    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#transformationFinished()
     */
    public void transformationFinished() {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#resourceLoaded(org.eclipse.emf.ecore.resource.Resource)
     */
    public void resourceLoaded(Resource res) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#evaluateRule(com.dstc.tefkat.model.TRule, com.dstc.tefkat.engine.Binding, boolean)
     */
    public void evaluateRule(TRule rule, Binding context, boolean cached) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#evaluateSource(com.dstc.tefkat.model.TRule, com.dstc.tefkat.engine.Binding)
     */
    public void evaluateSource(TRule rule, Binding context) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#evaluateTarget(com.dstc.tefkat.model.TRule, com.dstc.tefkat.engine.Binding)
     */
    public void evaluateTarget(TRule rule, Binding context) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#enterTree(com.dstc.tefkat.engine.Tree)
     */
    public void enterTree(Tree tree) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#exitTree(com.dstc.tefkat.engine.Tree)
     */
    public void exitTree(Tree tree) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#enterTerm(com.dstc.tefkat.engine.Node)
     */
    public void enterTerm(Node node) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#exitTerm(com.dstc.tefkat.engine.Node)
     */
    public void exitTerm(Node node) {
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.engine.TefkatListener#delayTerm(com.dstc.tefkat.engine.Node)
     */
    public void delayTerm(Node node) {
    }

}
