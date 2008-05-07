package tefkat.plugin.eventing;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.engine.Binding;
import tefkat.engine.Node;
import tefkat.engine.Tefkat;
import tefkat.engine.TefkatListener;
import tefkat.engine.TefkatListenerAdapter;
import tefkat.engine.Tree;
import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Transformation;


/**
 * Hack to register one global listener (this) and have interested parties register with this one
 * to only receive events that for specified transformation (represented by URI).
 * <p>
 * See {@link #transformationStarted(Transformation, Extent[], Extent[], Extent, Binding)} for
 * thread local set up and {@link #stopped()} for thread local tear down;
 * <p>
 * Note it is designed that so that only one listener can be registered per transformation. This
 * could be updated or just worked around by having a core delegator, but that is something for
 * later. It is also intentionly a singleton to fit single engine model, and as such can only be registered
 * with one engine at a time.
 */
public class ManyToOneTefkatListenerAdapter implements TefkatListener {
    private static final TefkatListener DEFAULT = new TefkatListenerAdapter() {};
    private static final ManyToOneTefkatListenerAdapter SINGLETON = new ManyToOneTefkatListenerAdapter();

    private static Tefkat engine = null;

    private ThreadLocal<TefkatListener> current = new ThreadLocal<TefkatListener>() {
        protected TefkatListener initialValue() {
            return new TefkatListenerAdapter() {};
        }
    };
    private ConcurrentMap<URI,TefkatListener> uri2listener = new ConcurrentHashMap<URI, TefkatListener>();

    public synchronized static void register(Tefkat t) {
        reset();
        engine = t;
        engine.addTefkatListener(SINGLETON);
    }

    public synchronized static void reset() {
        if (engine != null) engine.removeTefkatListener(SINGLETON);
        engine = null;
    }

    public static void addListener(URI transform, TefkatListener target) {
        SINGLETON.uri2listener.put(transform, target);
    }

    public static void removeListener(URI transform) {
        SINGLETON.uri2listener.remove(transform);
    }

    public void breakpoint(Term term) {
        delegate().breakpoint(term);
    }

    public void delayTerm(Node node) {
        delegate().delayTerm(node);
    }

    public void enterTerm(Node node) {
        delegate().enterTerm(node);
    }

    public void enterTree(Tree tree) {
        delegate().enterTree(tree);
    }

    public void error(String message, Throwable cause) {
        delegate().error(message, cause);
    }

    public void evaluateRule(TRule rule, Binding context, boolean cached) {
        delegate().evaluateRule(rule, context, cached);
    }

    public void evaluateSource(TRule rule, Binding context) {
        delegate().evaluateSource(rule, context);
    }

    public void evaluateTarget(TRule rule, Binding context) {
        delegate().evaluateTarget(rule, context);
    }


    public void exitTerm(Node node) {
        delegate().enterTerm(node);
    }

    public void exitTree(Tree tree) {
        delegate().exitTree(tree);
    }

    public void info(String message) {
        delegate().info(message);
    }

    public void resourceLoaded(Resource res) {
        delegate().resourceLoaded(res);
    }

    public void resumed() {
        delegate().resumed();
    }

    public void started() {
        delegate().started();
    }

    public void stopped() {
        try {
            delegate().stopped();
        } finally {
            current.remove();
        }
    }

    public void suspended() {
        delegate().suspended();
    }

    public void transformationFinished() {
        delegate().transformationFinished();
    }


    public void transformationFinished(Transformation transformation) {
        delegate().transformationFinished(transformation);
    }


    public void transformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        setUpTransformState(transformation);
        delegate().transformationStarted(transformation, srcs, tgts, trace, context);
    }


    public void warning(String message) {
        delegate().warning(message);
    }

    private void setUpTransformState(Transformation transformation) {
        URI incoming = uri(transformation);
        TefkatListener target = uri2listener.get(incoming);
        current.set(target != null ? target : DEFAULT);
    }

    private URI uri(Transformation transformation) {
        org.eclipse.emf.common.util.URI resouce = transformation.eResource().getURI();
        String fileURIString = CommonPlugin.asLocalURI(resouce).toString();
        return URI.create(fileURIString);
    }

    private TefkatListener delegate() {
        return current.get();
    }
}


