package tefkat.plugin.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tefkat.engine.Node;
import tefkat.model.Term;

public class TermStats {
    public Map<Node,NodeStat> nodeStats = new HashMap<Node,NodeStat>();
    public Map<Term,TermStat> termStats = new HashMap<Term,TermStat>();

    public void entered(Node n) {
        ++node(n).entered;
    }

    public void exited(Node n) {
        ++node(n).exited;
    }

    public void delayed(Node n) {
        ++node(n).delayed;
    }

    private TermStat term(Term t) {
        TermStat s = termStats.get(t);
        if (s == null) {
            s = new TermStat(t);
            termStats.put(t, s);
        }
        return s;
    }

    private NodeStat node(Node n) {
        NodeStat s = nodeStats.get(n);
        if (s == null) {
            s = new NodeStat();
            nodeStats.put(n, s);
        }
        return s;
    }

    private static class NodeStat {
        int entered = 0;
        int exited = 0;
        int delayed = 0;
    }

    public static class TermStat implements Comparable<TermStat> {
        final Term t;
        TermStat(Term t) { this.t = t; };
        public int success = 0;
        public int failure = 0;
        public int nonleaf = 0;

        public boolean success() {
            return success == 0 && failure == 0 && nonleaf > 0;
        }

        public boolean failure() {
            return success == 0 && failure > 0 && nonleaf == 0;
        }

        private int attempts() {
            return this.success + this.failure + this.nonleaf;
        }

        public AnnotationType type() {
            return success() ? AnnotationType.ALWAYS :
                    failure() ? AnnotationType.NEVER :
                        AnnotationType.SOMETIMES;
        }

        private double percentSuccess() {
            return (double) nonleaf / attempts() * 100;
        }

        public int compareTo(TermStat other) {
            Integer thisAttempts = Integer.valueOf(this.attempts());
            Integer otherAttempts = Integer.valueOf(other.attempts());
            return thisAttempts.compareTo(otherAttempts);
        }

        public String percent() {
            if (success != 0) throw new Error();
            return String.format("%2.2f", percentSuccess());
        }

        public Term term() {
            return t;
        }
    }

    int successfulLeaf = 0;
    int unsuccessfulLeaf = 0;
    int nonLeafNode = 0;

    public void printAllStats() {
        System.out.println();

        List<TermStat> stats = new ArrayList<TermStat>(termStats.values());
        Collections.sort(stats);
        for (TermStat stat : stats) {
            System.out.printf("Term (%s) ::> \n   [attempts=%d success=%d, failure=%d, non-leaf=%d] [success=%%%+1.1f, failure=%%%+1.1f, non-leaf=%%%+1.1f]\n\n", stat.t, stat.attempts(), stat.success, stat.failure, stat.nonleaf, (double) stat.success / stat.attempts() * 100, (double) stat.failure / stat.attempts() * 100, (double) stat.nonleaf / stat.attempts() * 100);
        }

        System.out.println(":: Successful Leaf Nodes: " + successfulLeaf);
        System.out.println(":: Unsuccessful Leaf Nodes: " + unsuccessfulLeaf);
        System.out.println(":: Non-Leaf Nodes: " + nonLeafNode);
        System.out.println(":: Node Count: " + nodeStats.keySet().size());
        System.out.println(":: Term Count: " + termStats.keySet().size());


    }

    public void computeAllStats() {
        successfulLeaf = 0;
        unsuccessfulLeaf = 0;
        nonLeafNode = 0;
        for (Node n : nodeStats.keySet()) {
            if (n.isSuccess()) {
                ++successfulLeaf;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).success;
                }
            } else if (n.isFailure()) {
                ++unsuccessfulLeaf;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).failure;
                }
            } else {
                ++nonLeafNode;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).nonleaf;
                }
            }
        }
    }


}
