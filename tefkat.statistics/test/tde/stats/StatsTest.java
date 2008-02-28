package tde.stats;

import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;

import junit.framework.TestCase;

import tefkat.engine.Tefkat;
import tefkat.model.TefkatException;
import static tde.stats.data.ResourceReader.uri;
import static tde.stats.data.ResourceReader.load;
import static tde.stats.data.ResourceReader.create;
import static tde.stats.data.ResourceReader.resource;

public final class StatsTest extends TestCase {
    private Tefkat engine;
    private TefkatStatisticsListener stats;
    private ResourceSet rs;

    protected void setUp() {
        engine = new Tefkat();
        engine.setPrintingStats(true);
        stats = new TefkatStatisticsListener();
        engine.addTefkatListener(stats);
        rs = engine.getResourceSet();
        // FIXME should be using non-global uri converter map
        URIConverter.URI_MAP.put(uri("http://simpleuml"), resource("models/simpleuml.ecore"));
        URIConverter.URI_MAP.put(uri("http://relational"), resource("models/relational.ecore"));
        load(rs, "models/simpleuml.ecore");
        load(rs, "models/relational.ecore");
    }

    protected void tearDown() {
        URIConverter.URI_MAP.remove(uri("http://simpleuml"));
        URIConverter.URI_MAP.remove(uri("http://relational"));
    }

    public void DONTtestUml2Rel() throws TefkatException {
        Resource transform = load(rs, "transformations/uml2rel.qvt");
        Resource[] sourceExtent = { load(rs, "instances/simpleuml1.xmi") };
        Resource[] targetExtent = { create(rs, "output/relational1.xmi") };
        Resource trace = create(rs, "output/uml2rel.trace.xmi");
        engine.transform(transform, new HashMap<String,Object>(), sourceExtent, targetExtent, trace, false);
        stats.printAllStats();

    }

    public void testContrived() throws Exception {
        Resource transform = load(rs, "transformations/contrived.qvt");
        Resource[] sourceExtent = { load(rs, "instances/contrived.xmi") };
        Resource[] targetExtent = { create(rs, "output/contrived.xmi") };
        Resource trace = create(rs, "output/contrived.trace.xmi");
        engine.transform(transform, new HashMap<String,Object>(), sourceExtent, targetExtent, trace, false);
        stats.printAllStats();

        trace.save(new HashMap<Object,Object>());
        targetExtent[0].save(new HashMap<Object,Object>());
    }

    public void testContrivedWithBug() throws Exception {
        Resource transform = load(rs, "transformations/contrivedWithBug.qvt");
        Resource[] sourceExtent = { load(rs, "instances/contrived.xmi") };
        Resource[] targetExtent = { create(rs, "output/contrivedWithBug.xmi") };
        Resource trace = create(rs, "output/contrivedWithBug.trace.xmi");
        engine.transform(transform, new HashMap<String,Object>(), sourceExtent, targetExtent, trace, false);
        stats.printAllStats();

        trace.save(new HashMap<Object,Object>());
        targetExtent[0].save(new HashMap<Object,Object>());
    }
}
