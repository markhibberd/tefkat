package tde.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tefkat.engine.Node;
import tefkat.model.Term;

public class TermStats {
    private Map<Node,NodeStat> nodeStats = new HashMap<Node,NodeStat>();
    private Map<Term,TermStat> termStats = new HashMap<Term,TermStat>();

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

    private static class TermStat implements Comparable<TermStat> {
        final Term t;
        TermStat(Term t) { this.t = t; };
        int success = 0;
        int failure = 0;
        int nonleaf = 0;
        private int attempts() {
            return this.success + this.failure + this.nonleaf;
        }
        public int compareTo(TermStat other) {
            Integer thisAttempts = Integer.valueOf(this.attempts());
            Integer otherAttempts = Integer.valueOf(other.attempts());
            return thisAttempts.compareTo(otherAttempts);
        }
    }

    public void printAllStats() {
        int successfulLeaf = 0;
        int unsuccessfulLeaf = 0;
        int nonLeafNode = 0;
        for (Node n : nodeStats.keySet()) {
            NodeStat s = node(n);
//            System.out.println("Node (" + n + ") ::>");
//            System.out.println("        entered: " + s.entered);
//            System.out.println("        exited: " + s.exited);
//            System.out.println("        delayed: " + s.delayed);
            if (n.isSuccess()) {
                ++successfulLeaf;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).success;
                }
//                System.out.println("    Term Succeeded.");
            } else if (n.isFailure()) {
                ++unsuccessfulLeaf;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).failure;
                }

//                System.out.println("    Term Failed.");
            } else {
                ++nonLeafNode;
                Term t = n.selectedLiteral();
                if (t != null) {
                    ++term(t).nonleaf;
                }
//                System.out.println("    Non Leaf Node.");
            }
        }

//        for (Term t : termStats.keySet()) {
//            TermStat s = get(t);
//            System.out.println("Term (" + t + ") ::>");
//            System.out.println("        success: " + s.success);
//            System.out.println("        failure: " + s.failure);
//            System.out.println("        nonleaf: " + s.nonleaf);termStats
//        }

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


}
