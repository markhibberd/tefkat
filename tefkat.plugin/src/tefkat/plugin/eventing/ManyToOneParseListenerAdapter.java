package tefkat.plugin.eventing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.model.parser.ParserEvent;
import tefkat.model.parser.ParserListener;
import tefkat.model.parser.TefkatParser;

/**
 * FIXME This class is a bit premature, even though there is only one engine,
 * a new instance of the parser is created each time.
 */
public class ManyToOneParseListenerAdapter implements ParserListener {
    private ConcurrentMap<Resource,ParserListener> resource2listener = new ConcurrentHashMap<Resource,ParserListener>();
    private static final ManyToOneParseListenerAdapter SINGLETON = new ManyToOneParseListenerAdapter();

    private static TefkatParser parser = null;

    public synchronized static void register(TefkatParser p) {
        reset();
        parser = p;
        parser.addParserListener(SINGLETON);
    }

    public synchronized static void reset() {
        if (parser != null) parser.removeParserListener(SINGLETON);
        parser = null;
    }

    public static void addListener(Resource transform, ParserListener target) {
        if (target == null) return;
        SINGLETON.resource2listener.put(transform, target);
    }

    public static void removeListener(Resource transform) {
        SINGLETON.resource2listener.remove(transform);
    }

    public void matched(ParserEvent e) {
//        Resource resource = null;
//        Object obj = e.getObj();
//        if (obj instanceof EObject) {
//            EObject eObj = (EObject) obj;
//            resource = eObj.eResource();
//        } else {
//            return;
//        }
//        if (resource == null) {
//            System.err.println("bader: " + obj);
//            return;
//        }
//        ParserListener target = resource2listener.get(resource);
//        if (target != null) target.matched(e);

        for (Resource r : resource2listener.keySet()) {
            resource2listener.get(r).matched(e);
        }
    }
}
