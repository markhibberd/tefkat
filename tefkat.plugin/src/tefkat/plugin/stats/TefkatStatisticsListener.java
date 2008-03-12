package tefkat.plugin.stats;

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.engine.Binding;
import tefkat.engine.Node;
import tefkat.engine.TefkatListener2;
import tefkat.engine.TefkatListenerAdapter;
import tefkat.engine.Tree;
import tefkat.engine.TreeListener;
import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Transformation;

public class TefkatStatisticsListener extends TefkatListenerAdapter implements
        TefkatListener2, TreeListener {
    protected TermStats termStats = new TermStats();
    private int resourceLoaded = 0;
    private int enterTree = 0;
    private int evaluateRule = 0;
    private int evaluateSource = 0;
    private int evaluateTarget = 0;
    private int exitTree = 0;
    private int treeAdded = 0;
    private int treeRemoved = 0;
    private int completedTree = 0;
    private int flounderedTree = 0;
    private int solvedTree = 0;
    private int successfulTree = 0;
    private int unsuccessfulTree = 0;

    public void resourceLoaded(Resource res) { ++resourceLoaded; }
    public void enterTree(Tree tree) { ++enterTree; }
    public void evaluateRule(TRule rule, Binding context, boolean cached) { ++evaluateRule; }
    public void evaluateSource(TRule rule, Binding context) { ++evaluateSource; }
    public void evaluateTarget(TRule rule, Binding context) { ++evaluateTarget; }
    public void exitTree(Tree tree) { ++exitTree; }
    public void treeAdded(Tree tree) { ++treeAdded; tree.addTreeListener(this); }
    public void treeRemoved(Tree tree) { ++treeRemoved; tree.removeTreeListener(this); }
    public void floundered(Tree tree) { ++flounderedTree; }
    public void solution(Binding answer) { ++solvedTree; }

    public void completed(Tree tree) {
        ++completedTree;
        if (tree.isSuccess()) {
            ++successfulTree;
        } else {
            ++unsuccessfulTree;
        }
    }

    public void delayTerm(Node node) { termStats.delayed(node); }
    public void enterTerm(Node node) { termStats.entered(node); }
    public void exitTerm(Node node) { termStats.exited(node); }


    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        System.out.println("Starting transformation...");
    }

    public void transformationFinished() {
        System.out.println("Finished transformation...");
    }

    public void info(String message) {
        System.out.println("info:    " + message);
    }

    public void warning(String message) {
        System.out.println("warning: " + message);
    }

    public void error(String message, Throwable cause) {
        System.out.println("error:   " + message + ": " + cause.getMessage());
    }

    public void computeAllStats() {
        this.termStats.computeAllStats();
    }
    public void printAllStats() {
        System.out.println();
        System.out.println("=================== TRANSFORMATION STATISTICS: BEGIN ===================");

        System.out.println("resourceLoaded:" + resourceLoaded);
        System.out.println("enterTree:" + enterTree);
        System.out.println("evaluateRule:" + evaluateRule);
        System.out.println("evaluateSource:" + evaluateSource);
        System.out.println("evaluateTarget:" + evaluateTarget);
        System.out.println("exitTree:" + exitTree);
        System.out.println("treeAdded:" + treeAdded);
        System.out.println("treeRemoved:" + treeRemoved);
        System.out.println("completedTree:" + completedTree);
        System.out.println("flounderedTree:" + flounderedTree);
        System.out.println("solvedTree:" + solvedTree);
        System.out.println("successfulTree:" + successfulTree);
        System.out.println("unsuccessfulTree:" + unsuccessfulTree);

        termStats.printAllStats();

        System.out.println();
        System.out.println("=================== TRANSFORMATION STATISTICS: END   ===================");
        System.out.println();
    }


}
