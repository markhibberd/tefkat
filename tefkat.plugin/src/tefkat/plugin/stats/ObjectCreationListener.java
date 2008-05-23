package tefkat.plugin.stats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tefkat.engine.Binding;
import tefkat.engine.trace.Trace;
import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Transformation;
import tefkat.plugin.TefkatModelEditor.TefkatTextEditor;
import tefkat.plugin.dom.TefkatDOM;

public class ObjectCreationListener extends TefkatStatisticsListener {
    private static boolean enabled = false;
    private TefkatTextEditor textEditor;
    private Transformation transformation;
    private Extent trace;
    private AnnotatedCreationDocument document;
    private static EObject interest = null;

    public ObjectCreationListener(TefkatTextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public static void setInterested(EObject interest) { ObjectCreationListener.interest = interest; }

    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        System.out.println("Starting transformation...");
        this.trace = trace;
        this.transformation = transformation;
        if (this.document != null) document.clear();
        this.document = this.textEditor.createAnnotatedCreationDocument(TefkatDOM.getInstance(textEditor));
        document.clear();
    }

    @SuppressWarnings("unchecked")
    public void transformationFinished() {
        System.out.println("Ending transformation...");
        try {
            process();
        } catch (Throwable t) { t.printStackTrace(); }
    }

    private static final boolean DEMO = false;

    private void process() {
        if (DEMO) {
            demo();
            return;
        }
        if (!isEnabled()) return;
        Iterator<EObject> iter = this.trace.getAllContents();
        if (!iter.hasNext()) {
            System.err.println("No Trace");
            return;
        }
        EObject first = iter.next();
        Resource tracource = first.eResource();

        List<Tuple> builder = new LinkedList<Tuple>();
        for (EObject e : tracource.getContents()) {
            if (!(e instanceof Trace)) continue;
            Trace t = Trace.class.cast(e);
            EObject target = t.getTarget();
            boolean contains = false;
            for (Tuple tuple : builder) {
                if (tuple.equiv(target)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                builder.add(new Tuple(target));
            }

            Map<EObject,Integer> counter = null;
            for (Tuple tuple : builder) {
                if (tuple.equiv(target)) {
                    counter = tuple.counter;
                    break;
                }
            }
            for (EObject rule : t.getRules()) {
                if (!counter.containsKey(rule)) {
                    counter.put(rule, 0);
                }
                int count = counter.get(rule);
                ++count;
                counter.put(rule, count);
            }
        }

        realWIP(builder);
    }
    private class Tuple {
        final EObject key;
        final Map<EObject,Integer> counter = new HashMap<EObject, Integer>();;
        public Tuple(EObject key) { this.key = key; }
        public boolean equiv(EObject other) {
            return EcoreUtil.equals(key, other);
        }
    }



    private void demo() {
        double ex = 0;
        double post = 1;
        double under = 2;
        double total = 3;

        double pex = ex / total * 100;
        double ppost = post / total * 100;
        double punder = under / total * 100;

        String spex = String.format("%1.1f", pex);
        String sppost = String.format("%1.1f", ppost);
        String spunder = String.format("%1.1f", punder);
        for (TRule rule : (EList<TRule>) transformation.getTRule()) {
            if (rule.getName().equals("UnderGrads")) {
                this.document.addCreationHover((TRule) rule, "Created " + spunder + "% of class[UMLClass]");
            } else if (rule.getName().equals("PostGrads")) {
                this.document.addCreationHover((TRule) rule, "Created " + sppost + "% of class[UMLClass]");
            } else if (rule.getName().equals("ExStudent")) {
                this.document.addCreationHover((TRule) rule, "Created " + spex + "% of class[UMLClass]");
            }
        }

    }

    private void realWIP(List<Tuple> built) {
        for (Tuple tuple : built) {
            Map<EObject, Integer> count = tuple.counter;
            int total = total(count);
            double dt = total;
            EObject e = tuple.key;

            if (interest != null && !match(e)) continue;

            EClass eclass = e.eClass();
            String className = eclass.getName();
            StringBuilder attribs = new StringBuilder();

            for (EStructuralFeature f : eclass.getEStructuralFeatures()) {
                attribs.append(f.getName());
                attribs.append("=");
                Object param = e.eGet(f);
                if (param instanceof EObject) {
                    attribs.append(((EObject) param).eClass().getName());
                } else {
                    attribs.append(param);
                }
                attribs.append(",");
            }
            if (eclass.getFeatureCount() > 0) attribs.setLength(attribs.length() - 1);

            System.out.println("Created object: " + className + "("+ attribs + ")" + " " + total + " times, with following rules:");
            for (EObject rule : count.keySet()) {
                int c = count.get(rule);
                double dc = c;
                double d = ((dc/dt) * 100);
                System.out.println("    Rule(" + rule + "): " + d + "%");
                this.document.addCreationHover((TRule) rule, "Created " + d + "% of object[" + EObjectUtil.buildToString(tuple.key) + "]");
            }
        }
    }

    private boolean match(EObject e) {
        // FIXME need to define my own introspective equality method
        return EcoreUtil.equals(interest, e) || EObjectUtil.buildToString(interest).equals(EObjectUtil.buildToString(e));
    }

    private int total(Map<EObject, Integer> count) {
        int total = 0;
        for (EObject k : count.keySet()) total += count.get(k);
        return total;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        ObjectCreationListener.enabled = enabled;
    }
}
