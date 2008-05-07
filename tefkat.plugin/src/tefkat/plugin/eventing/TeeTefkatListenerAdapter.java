package tefkat.plugin.eventing;

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.engine.Binding;
import tefkat.engine.Node;
import tefkat.engine.TefkatListener;
import tefkat.engine.Tree;
import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Transformation;


public class TeeTefkatListenerAdapter implements TefkatListener {
    private final TefkatListener x;
    private final TefkatListener y;

    public TeeTefkatListenerAdapter(TefkatListener x, TefkatListener y) {
        this.x = x;
        this.y = y;
    }

    public void breakpoint(Term term) {
        x.breakpoint(term);
        y.breakpoint(term);
    }

    public void delayTerm(Node node) {
        x.delayTerm(node);
        y.delayTerm(node);
    }

    public void enterTerm(Node node) {
        x.enterTerm(node);
        y.enterTerm(node);
    }

    public void enterTree(Tree tree) {
        x.enterTree(tree);
        y.enterTree(tree);
    }

    public void error(String message, Throwable cause) {
        x.error(message, cause);
        y.error(message, cause);
    }

    public void evaluateRule(TRule rule, Binding context, boolean cached) {
        x.evaluateRule(rule, context, cached);
        y.evaluateRule(rule, context, cached);
    }

    public void evaluateSource(TRule rule, Binding context) {
        x.evaluateSource(rule, context);
        y.evaluateSource(rule, context);
    }

    public void evaluateTarget(TRule rule, Binding context) {
        x.evaluateTarget(rule, context);
        y.evaluateTarget(rule, context);
    }


    public void exitTerm(Node node) {
        x.enterTerm(node);
        y.enterTerm(node);
    }

    public void exitTree(Tree tree) {
        x.exitTree(tree);
        y.exitTree(tree);
    }

    public void info(String message) {
        x.info(message);
        y.info(message);
    }

    public void resourceLoaded(Resource res) {
        x.resourceLoaded(res);
        y.resourceLoaded(res);
    }

    public void resumed() {
        x.resumed();
        y.resumed();
    }

    public void started() {
        x.started();
        y.started();
    }

    public void stopped() {
        x.stopped();
        y.stopped();
    }

    public void suspended() {
        x.suspended();
        y.suspended();
    }

    public void transformationFinished() {
        x.transformationFinished();
        y.transformationFinished();
    }

    public void transformationFinished(Transformation transformation) {
        x.transformationFinished(transformation);
        y.transformationFinished(transformation);
    }

    public void transformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        x.transformationStarted(transformation, srcs, tgts, trace, context);
        y.transformationStarted(transformation, srcs, tgts, trace, context);
    }

    public void warning(String message) {
        x.warning(message);
        y.warning(message);
    }
}


