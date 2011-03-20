/*
 * Copyright (c) 2004- michael lawley and others.
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

import tefkat.config.TefkatConfig.ExecutionMode;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.engine.events.DefaultEvents;
import tefkat.engine.events.EventRegistry;
import tefkat.engine.events.EventWriter;
import tefkat.engine.events.Events;
import tefkat.engine.executiontrace.events.Bootstrap;
import tefkat.model.PatternScope;
import tefkat.model.Query;
import tefkat.model.ReferenceExtent;
import tefkat.model.Var;
import tefkat.model.ContainerExtent;
import tefkat.model.Extent;
import tefkat.model.NamespaceDeclaration;
import tefkat.model.TefkatException;
import tefkat.model.TefkatFactory;
import tefkat.model.Transformation;
import tefkat.model.impl.TefkatPackageImpl;
import tefkat.model.internal.IntMap;
import tefkat.model.internal.ModelUtils;
import tefkat.model.parser.ParserListener;
import tefkat.model.parser.TefkatResourceFactory;
import tefkat.model.util.TefkatAdapterFactory;

/**
 * @author lawley
 *
 */
public class Tefkat {

    static {
        // Ensure EMF runtime knows about the Tefkat metamodel
        TefkatPackageImpl.init();
    }

    private static final Map SERIALIZATION_OPTIONS;

    static {
        SERIALIZATION_OPTIONS = new HashMap();
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    }

    private ResourceSet _resourceSet;

    private final List engineListeners = new ArrayList();

    private final Resource.Factory XMI_RESOURCE_FACTORY = new XMIResourceFactoryImpl();
    private final TefkatResourceFactory TEFKAT_RESOURCE_FACTORY = new TefkatResourceFactory();
    private final Resource.Factory XSD_RESOURCE_FACTORY = new XSDResourceFactoryImpl();
    private final Resource.Factory ECORE_RESOURCE_FACTORY = new EcoreResourceFactoryImpl();
    private final Resource.Factory XML_RESOURCE_FACTORY = new GenericXMLResourceFactoryImpl();

    private final Map functions = new HashMap();

    private transient boolean isRunning = false;

    private RuleEvaluator ruleEvaluator;
    private boolean isIncremental = true;
    private boolean printingStats = false;

    private boolean paused;

    private List threads = new ArrayList();

    private Events events = new DefaultEvents();
    private Bootstrap excutiontrace = new Bootstrap();

    final private Adapter resourceLoadListener = new AdapterImpl() {
        public void notifyChanged(Notification notif) {
            if (Notification.SET == notif.getEventType()
                    && notif.getNotifier() instanceof Resource
                    && Resource.RESOURCE__IS_LOADED == notif
                            .getFeatureID(Resource.class)
                    && notif.getNewBooleanValue()) {
                Resource res = (Resource) notif.getNotifier();
                fireResourceLoaded(res);
                res.eAdapters().remove(resourceLoadListener);
            }
        }
    };

    final private Adapter resourceSetChangeListener = new AdapterImpl() {
        public void notifyChanged(Notification notif) {
            if (Notification.ADD == notif.getEventType()) {
                Resource res = (Resource) notif.getNewValue();
                res.eAdapters().add(resourceLoadListener);
            } else if (Notification.REMOVING_ADAPTER == notif.getEventType() && notif.getOldValue() instanceof Resource) {
                System.err.println("*********" + notif);
                Resource res = (Resource) notif.getOldValue();
                res.eAdapters().remove(resourceLoadListener);
            } else {
                fireInfo("ResourceSet event " + notif.getEventType() + ": " + notif.getNewValue());
            }
        }
    };

    void setResourceSet(ResourceSet rs) {
        if (null != _resourceSet) {
            if (_resourceSet.equals(rs)) {
                return;
            }
            throw new IllegalStateException("ResourceSet is already set - call clearResourceSet() first.");
        }
        _resourceSet = rs;
        // Need to use a new ExtendedMetaData instance to avoid cached ePackage instances.
        //
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData(_resourceSet.getPackageRegistry()));
        _resourceSet.getLoadOptions().putAll(SERIALIZATION_OPTIONS);
        
        registerFactory("qvt", TEFKAT_RESOURCE_FACTORY);
        registerFactory("xsd", XSD_RESOURCE_FACTORY);
        registerFactory("wsdl", XSD_RESOURCE_FACTORY);
        registerFactory("xmi", XMI_RESOURCE_FACTORY);
        registerFactory("tefkat", XMI_RESOURCE_FACTORY);
        registerFactory("ecore", ECORE_RESOURCE_FACTORY);
        registerFactory("xml", XML_RESOURCE_FACTORY);
        
        _resourceSet.eAdapters().add(resourceSetChangeListener);
    }
    
    public ResourceSet getResourceSet() {
        if (null == _resourceSet) {
            setResourceSet(new ResourceSetImpl());
        }
        return _resourceSet;
    }

    public void clearResourceSet() {
        if (null != _resourceSet) {
            _resourceSet.eAdapters().remove(resourceSetChangeListener);
            _resourceSet = null;
        }
    }

    public Tefkat() {
    }

    @Deprecated
    public boolean isIncremental() {
        return isIncremental;
    }
    
    @Deprecated
    public void setIncremental(boolean isIncremental) {
        throw new UnsupportedOperationException("non-incremental model is deprecated");
//        this.isIncremental = isIncremental;
//        if (null != ruleEvaluator) {
//            ruleEvaluator.INCREMENTAL = isIncremental;
//        }
    }
    
    public boolean isPrintingStats() {
        return printingStats;
    }

    public void setPrintingStats(boolean printingStats) {
        this.printingStats = printingStats;
    }

    public void addFunction(String name, Function function) {
        functions.put(name, function);
    }

    public void addTefkatListener(TefkatListener listener) {
        if (!engineListeners.contains(listener)) {
            engineListeners.add(listener);
        }
    }

    public void removeTefkatListener(TefkatListener listener) {
        engineListeners.remove(listener);
    }

    public boolean getInterrupted() {
        return null != ruleEvaluator && ruleEvaluator.getInterrupted();
    }

    public void setInterrupted(boolean state) {
        fireWarning("Transformation interrupted");
        if (null != ruleEvaluator) {
            ruleEvaluator.setInterrupted(state);
        }
        for (Iterator itr = threads.iterator(); itr.hasNext(); ) {
            Thread thread = (Thread) itr.next();
            thread.interrupt();
        }
    }

    public boolean getRunning() {
        return isRunning;
    }

    private void setRunning(boolean state) {
        isRunning = state;
    }

    public void registerFactory(String ext, Resource.Factory factory) {
        Map map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        map.put(ext, factory);
    }

    public Resource createTempResource(String prefix, String suffix) {
        String filename = prefix + suffix;
        // filename = File.createTempFile(prefix, suffix).getAbsolutePath();
        URI tmpURI = URI.createFileURI(filename);
        return getResourceSet().createResource(tmpURI);
    }

    public void transform(Resource transformation, Resource src, Resource tgt)
            throws TefkatException {
        Resource[] srcs = { src };
        Resource[] tgts = { tgt };
        transform(transformation, srcs, tgts);
    }

    public void transform(Resource transformation, Resource src, Resource tgt,
            Resource tracking) throws TefkatException {
        Resource[] srcs = { src };
        Resource[] tgts = { tgt };
        transform(transformation, srcs, tgts, tracking);
    }

    public void transform(Resource transformation, Resource[] srcs, Resource[] tgts)
            throws TefkatException {
        transform(transformation, srcs, tgts, null);
    }

    public void transform(Resource transformation, Resource[] srcs, Resource[] tgts,
            Resource tracking) throws TefkatException {
        transform(transformation, srcs, tgts, tracking, false);
    }
    
    public void transform(Resource transformation, Resource[] srcs, Resource[] tgts,
            Resource tracking, boolean force) throws TefkatException {
        transform(transformation, null, srcs, tgts, tracking, force);
    }
    
    public void transform(Resource transformation, Map parameters, Resource[] srcs, Resource[] tgts,
            Resource tracking, boolean force) throws TefkatException {
        
        setResourceSet(transformation.getResourceSet());

        final ContainerExtent[] srcEs = new ContainerExtent[srcs.length];
        final ContainerExtent[] tgtEs = new ContainerExtent[tgts.length];

        final ContainerExtent trackingE = TefkatFactory.eINSTANCE.createContainerExtent();

        for (int i = 0; i < srcs.length; i++) {
            srcEs[i] = TefkatFactory.eINSTANCE.createContainerExtent();
            srcEs[i].setResource(srcs[i]);
        }

        for (int i = 0; i < tgts.length; i++) {
            tgtEs[i] = TefkatFactory.eINSTANCE.createContainerExtent();
            tgtEs[i].setResource(tgts[i]);
        }
        
        if (null == tracking) {
            trackingE.setResource(createTempResource("tefkat-tracking", ".xmi"));
        } else {
            trackingE.setResource(tracking);
        }

        transform(transformation, parameters, srcEs, tgtEs, trackingE, force);
    }
    
    public void query(Resource query, Map parameters, Extent[] srcs) throws TefkatException {
        setResourceSet(query.getResourceSet());

        List contents = query.getContents();
        for (int j = 0; j < contents.size(); j++) {
            if (contents.get(j) instanceof Query) {
                Query q = (Query) contents.get(j);
                Binding context = setupQueryEnvironment(parameters, srcs, q);
                
                final long startTime = System.currentTimeMillis();

                final Collection answers = evaluateQuery(context, q);
                
                for (Object b: answers) {
                    System.out.println(b);
                }
                
                final long endTime = System.currentTimeMillis();

                if (printingStats) {
                    fireInfo(DynamicObject.counter + " Dynamic Objects");
                    fireInfo(Tree.counter + " Trees");
                    fireInfo(Node.counter + " Nodes");
                    fireInfo(Binding.counter + " Bindings");
                    fireInfo("Binding/Node Ratio: " + Binding.counter / (float) Node.counter);
                    
                    timings("Source", ruleEvaluator.srcResolver);
                    timings("Target", ruleEvaluator.tgtResolver);
                    for (int i = 0; i < ruleEvaluator.tgtResolver.elapsed.length; i++) {
                        fireInfo("TrackingUse breakdown: " + i + "  " + ruleEvaluator.tgtResolver.elapsed[i]);
                    }
                    
                    fireInfo("Query time: " + (endTime - startTime) / 1000.0 + "s");
                }
                
//                fireQueryFinished();
            }
        }

    }

    public Collection evaluateQuery(Binding context, Query q) throws ResolutionException, TefkatException {
        Tree.counter = 0;
        Node.counter = 0;
        Binding.counter = 0;
        DynamicObject.counter = 0;
        EventWriter writer = events.nu(q, getResourceSet(), excutiontrace);
        // fireQueryStarted(q, srcs, context);
        createRuleEvaluator(null, q, context, writer);

        if (paused) {
            ruleEvaluator.pause();
        }
        final Collection answers = ruleEvaluator.runQuery(q);
        writer.write(Query.class);
        return answers;
    }

    private Binding setupQueryEnvironment(Map parameters, Extent[] srcs, Query q) throws ResolutionException {
        Binding context = new Binding();
        List extentVars = q.getVars();

        for (final Iterator itr = extentVars.iterator(); itr.hasNext(); ) {
            final Var var = (Var) itr.next();
            final String varName = var.getName();
            if (parameters.containsKey(varName)) {
                context.add(var, parameters.get(varName));
            }
        }

        int idx = 0;
        for (int k = 0; k < srcs.length; k++) {
            Var var;
            do {
                if (idx >= extentVars.size()) {
                    throw new ResolutionException(null, "Too many parameters while processing " + srcs[k] + ".  Expected " + extentVars.size() + ", got approximately " + (parameters.size() + srcs.length));
                }
                var = (Var) extentVars.get(idx++);
            } while (context.lookup(var) != null);
            context.add(var, srcs[k]);
        }
        return context;
    }

    void transform(Resource transformation, Map parameters, Extent[] srcs, Extent[] tgts,
            Extent trackingExtent, boolean force) throws TefkatException {

        setResourceSet(transformation.getResourceSet());
        Transformation t = null;

        try {
            fireStart();

            List contents = transformation.getContents();
            for (int j = 0; j < contents.size(); j++) {
                if (contents.get(j) instanceof Transformation) {
                    t = (Transformation) contents.get(j);
                    Binding context = new Binding();
                    List extentVars = t.getVars();

                    for (final Iterator itr = extentVars.iterator(); itr.hasNext(); ) {
                        final Var var = (Var) itr.next();
                        final String varName = var.getName();
                        if (parameters.containsKey(varName)) {
                            context.add(var, parameters.get(varName));
                        }
                    }

                    int idx = 0;
                    for (int k = 0; k < srcs.length; k++) {
                        Var var;
                        do {
                            if (idx >= extentVars.size()) {
                                throw new ResolutionException(null, "Too many parameters while processing " + srcs[k] + ".  Expected " + extentVars.size() + ", got approximately " + (parameters.size() + srcs.length + tgts.length));
                            }
                            var = (Var) extentVars.get(idx++);
                        } while (context.lookup(var) != null);
                        context.add(var, srcs[k]);
                    }
                    for (int k = 0; k < tgts.length; k++) {
                        Var var;
                        do {
                            if (idx >= extentVars.size()) {
                                throw new ResolutionException(null, "Too many parameters found while processing " + srcs[k] + ".  Expected " + extentVars.size() + ", got approximately " + (parameters.size() + srcs.length + tgts.length));
                            }
                            var = (Var) extentVars.get(idx++);
                        } while (context.lookup(var) != null);
                        context.add(var, tgts[k]);
                    }

                    Tree.counter = 0;
                    Node.counter = 0;
                    Binding.counter = 0;
                    DynamicObject.counter = 0;

                    fireTransformationStarted(t, srcs, tgts, trackingExtent, context);
                    EventWriter writer = events.nu(t, getResourceSet(), excutiontrace);
                    createRuleEvaluator(trackingExtent, t, context, writer);

                    if (paused) {
                        ruleEvaluator.pause();
                    }
                    long startTime = System.currentTimeMillis();
                    ruleEvaluator.runTransformation(t, force);
                    long endTime = System.currentTimeMillis();

                    if (printingStats) {
                        fireInfo(DynamicObject.counter + " Dynamic Objects");
                        fireInfo(Tree.counter + " Trees");
                        fireInfo(Node.counter + " Nodes");
                        fireInfo(Binding.counter + " Bindings");
                        fireInfo("Binding/Node Ratio: " + Binding.counter / (float) Node.counter);
                        
                        timings("Source", ruleEvaluator.srcResolver);
                        timings("Target", ruleEvaluator.tgtResolver);
                        for (int i = 0; i < ruleEvaluator.tgtResolver.elapsed.length; i++) {
                            fireInfo("TrackingUse breakdown: " + i + "  " + ruleEvaluator.tgtResolver.elapsed[i]);
                        }
                    
//                    fireInfo("TrackingUse breakdown: " + 
//                            ruleEvaluator.tgtResolver.counter + "\t" +
//                            ruleEvaluator.tgtResolver.elapsed[0] + "\t" +
//                            ruleEvaluator.tgtResolver.elapsed[1] + "\t" +
//                            ruleEvaluator.tgtResolver.elapsed[2] + "\t" +
//                            ruleEvaluator.tgtResolver.elapsed[3]
//                            );
                    
                        fireInfo("Transformation time: " + (endTime - startTime) / 1000.0 + "s");
                    }
                    writer.write(Transformation.class);                    
                    fireTransformationFinished(t);
                }
            }
        } finally {
            fireStop();
        }
    }
    
    private void timings(String label, AbstractResolver resolver) {
        fireInfo("");
        fireInfo("    " + label + " Term\tCalls\tTime\tAvg (ms)");
        fireInfo("    ---------------------------------------");
        for (Iterator itr = resolver.elapsedTime.entries().iterator(); itr.hasNext(); ) {
            IntMap.Entry entry = (IntMap.Entry) itr.next();
            if (null != entry) {
                EClass cls = (EClass) entry.key;
                int count = resolver.callCount.get(cls);
                fireInfo("    " + cls.getName() + "\t" + count + "\t" + entry.value + "\t" + entry.value/(float)count);
            }
        }
    }
    
    /**
     * @param trackingExtent
     * @param scope
     * @param context
     * @throws ResolutionException
     */
    private void createRuleEvaluator(Extent trackingExtent, PatternScope scope, Binding context, EventWriter writer) throws ResolutionException {

        // Get all the transitively referenced Resources
        final Map nameMap = new HashMap();
        buildPackageNameMap(scope, nameMap);
        
//        try {
//            ModelUtils.resolveTrackingClassNames(t, nameMap);
//        } catch (TefkatException e) {
//            throw new ResolutionException(null, "Could not resolve tracking class references", e);
//        }
        
            ruleEvaluator = new RuleEvaluator(context, trackingExtent, nameMap, engineListeners, writer);
        ruleEvaluator.setInterrupted(false);
//        ruleEvaluator.INCREMENTAL = isIncremental;
        Evaluator evaluator = ruleEvaluator.getEvaluator();
        for (final Iterator itr = functions.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            String name = (String) entry.getKey();
            Function function = (Function) entry.getValue();
            evaluator.addFunction(name, function);
        }
    }

    private void buildPackageNameMap(PatternScope scope, Map nameMap) throws ResolutionException {
        final Map<String, Set<EPackage>> importedNamespaces = new HashMap<String, Set<EPackage>>();
        importedNamespaces.put(null, new HashSet<EPackage>());

        final EPackage.Registry registry = getResourceSet().getPackageRegistry();
        
        for (final Iterator itr = scope.getNamespaceDeclarations().iterator(); itr.hasNext(); ) {
            final NamespaceDeclaration nsd = (NamespaceDeclaration) itr.next();
            final String name = nsd.getPrefix();
            final String uriStr = nsd.getURI();
            Set<EPackage> packages = importedNamespaces.get(name);
            if (null == packages) {
                packages = new HashSet<EPackage>();
                importedNamespaces.put(name, packages);
            }
            importPackage(packages, registry, uriStr);
        }
        final Set<EPackage> nullPackages = importedNamespaces.get(null);
        if (null != scope.eResource()) {
            importPackage(nullPackages, registry, scope.eResource().getURI().toString());
        }

        for (final Iterator itr = scope.getImportedPackages().iterator(); itr.hasNext(); ) {
            final String uriStr = (String) itr.next();
            importPackage(nullPackages, registry, uriStr);
        }
        
        for (final Iterator itr = importedNamespaces.entrySet().iterator(); itr.hasNext(); ) {
            final Map.Entry entry = (Entry) itr.next();
            final String name = (String) entry.getKey();
            final Set packages = (Set) entry.getValue();
            ModelUtils.buildPackageNameMaps(packages, nameMap, name);
        }
    }

    private void importPackage(Set<EPackage> packages, EPackage.Registry registry, String uriStr) {
        final EPackage pkg = registry.getEPackage(uriStr);
        if (null != pkg) {
            packages.add(pkg);
        } else {
            final Resource res = getResourceSet().getResource(URI.createURI(uriStr), true);
            if (null == res) {
                fireWarning("Unable to load model for URI: " + uriStr);
                return;
            }

            if (false) {        // strict checking
                boolean found = false;

                for (Object o: res.getContents()) {
                    if (o instanceof EPackage) {
                        EPackage p = (EPackage) o;
                        if (uriStr.equals(p.getNsURI())) {
                            packages.add(p);
//                          registry.put(uriStr, p);
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    fireWarning("Unable to find EPackage with NsURI: " + uriStr);
                }
            } else {
                for (Object o: res.getContents()) {
                    if (o instanceof EPackage) {
                        EPackage p = (EPackage) o;
                        packages.add(p);
                    }
                }
            }
        }
    }

//    private void buildNameMap(Transformation t, Map nameMap) throws ResolutionException {
//        Map importedNamespaces = new HashMap();
//        importedNamespaces.put(null, new HashSet());
//
//        EPackage.Registry registry = getResourceSet().getPackageRegistry();
//        
//        for (final Iterator itr = t.getNamespaceDeclarations().iterator(); itr.hasNext(); ) {
//            NamespaceDeclaration nsd = (NamespaceDeclaration) itr.next();
//            String name = nsd.getPrefix();
//            String uriStr = nsd.getURI();
//            Set resources = (Set) importedNamespaces.get(name);
//            if (null == resources) {
//                resources = new HashSet();
//                importedNamespaces.put(name, resources);
//            }
//            importResource(resources, registry, uriStr);
//        }
//        Set resources = (Set) importedNamespaces.get(null);
//        resources.add(t.eResource());
//        for (final Iterator itr = t.getImportedPackages().iterator(); itr.hasNext(); ) {
//            String uriStr = (String) itr.next();
//            importResource(resources, registry, uriStr);
//        }
//        
//        for (final Iterator itr = importedNamespaces.entrySet().iterator(); itr.hasNext(); ) {
//            Map.Entry entry = (Entry) itr.next();
//            String name = (String) entry.getKey();
//            resources = findAllResources((Collection) entry.getValue());
//            ModelUtils.buildNameMaps(resources, nameMap, name);
//        }
//    }

//    private void importResource(Set resources, EPackage.Registry registry, String uriStr) throws ResolutionException {
//        // Avoid explicitly loading resources corresponding to packages that
//        // have already been loaded (or dynamically created!)
//        //
//        try {
//            if (registry.containsKey(uriStr)) {
//                resources.add(registry.getEPackage(uriStr).eResource());
//            } else {
//                // gracefully handle the case where an XSD has no targetNamespace
//                // (there can be only one :-)
//                EPackage nullPkg = registry.getEPackage(null);
//                if (null != nullPkg && uriStr.equals(nullPkg.getNsURI())) {
//                    resources.add(nullPkg.eResource());
//                } else {
//                    resources.add(getResource(uriStr));
//                }
//            }
//        } catch (IOException e) {
//            throw new ResolutionException(null, "Could not import model: " + uriStr, e);
//        }
//    }

    // Attempting to use a separate thread to allow hung resource loads to be interrupted
    private Resource getResource(final String location) throws IOException {
        final Resource[] res = { null };
        final Exception[] error = { null };
        Thread thread = new Thread() {
            public void run() {
                try {
                    URI uri = URI.createURI(location);
                    res[0] = getResourceSet().getResource(uri, true);
                } catch (Exception e) {
                    error[0] = e;
                } finally {
                    threads.remove(this);
                }
            }
        };
        threads.add(thread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            res[0] = null;
            error[0] = e;
        }
        if (null == res[0] || null != error[0]) {
            IOException e = new IOException("Failed to load Resource from " + location);
            if (null != error[0]) {
                e.initCause(error[0]);
            }
            throw e;
        } else if (null != res[0]) {
            List errors = res[0].getErrors();
            if (errors.size() > 0) {
                throw new IOException("Parse errors: " + errors);
            }
        }
        return res[0];
    }
    
    private Resource createResource(String location) {
        URI uri = URI.createURI(location);
        Resource res = getResourceSet().createResource(uri);
        return res;
    }
    
    
    /**
     * Takes a TefkatConfig.TransformationTask and performs the described
     * transformation.
     * 
     * @throws IOException
     * @throws TefkatException 
     */
    public void transform(TransformationTask task, boolean save, boolean force)
    throws IOException, TefkatException {
        try {
//            String classpathList = (String) task.getProperties().get("classpaths");
//            ClassLoader loader;
//            if (null != classpathList) {
//                String[] classpaths = classpathList.split("\\s*,\\s*");
//                URL[] urls = new URL[classpaths.length];
//                for (int i = 0; i < classpaths.length; i++) {
//                    urls[i] = new URL(classpaths[i]);
//                    System.err.println("[" + urls[i] + "]");
//                }
//                loader = new URLClassLoader(urls, this.getClass().getClassLoader());
//            } else {
//                loader = this.getClass().getClassLoader();
//            }
//            String packageList = (String) task.getProperties().get("packages");
//            if (null != packageList) {
//                String[] packages = packageList.split("\\s*,\\s*");
//                for (int i = 0; i < packages.length; i++) {
//                    System.err.println("<" + packages[i] + ">");
//                    Class c;
//                    c = loader.loadClass(packages[i]);
//                    //c = Class.forName(packages[i]);
//                    System.err.println(".eINSTANCE = " + c.getField("eINSTANCE").get(null));
//                }
//            }

            if (null != task.eResource()) {
                setResourceSet(task.eResource().getResourceSet());
            }
        
            List uriMaps = task.getUriMap();
        
            String incremental = (String) task.getProperties().get("incremental");
            if (null != incremental) {
                setIncremental(Boolean.valueOf(incremental).booleanValue());
            }
            String statistics = (String) task.getProperties().get("statistics");
            if (null != statistics) {
                setPrintingStats(Boolean.valueOf(statistics).booleanValue());
            }
            
            Resource transformationR = null;
            Map parameters = new HashMap();
            Resource[] sourcesR;
            Resource[] targetsR;
            Resource traceR = null;
            
            URIConverter converter = getResourceSet().getURIConverter();
            Map URIMap = converter.getURIMap();
            for (Iterator itr = uriMaps.iterator(); itr.hasNext(); ) {
                Map.Entry entry = (Map.Entry) itr.next();
                URI uri1 = URI.createURI((String) entry.getKey());
                URI uri2 = URI.createURI((String) entry.getValue());
                URIMap.put(uri1, uri2);
            }

            transformationR = getResource(task.getTransformation().getLocationUri());
            List sources = task.getSourceModels();
//            sourcesR = new Resource[sources.size()];
            List sourcesL = new ArrayList();
            for (int i = 0; i < sources.size(); i++) {
                final Model model = (Model) sources.get(i);
                final Resource resource = getResource((model).getLocationUri());
                final String varGroup = model.getVarGroup();
                
                // handle various guises of an un-specified varGroup
                if (varGroup != null && varGroup.trim().length() > 0) {
                    ReferenceExtent extent;
                    if (parameters.containsKey(varGroup)) {
                        extent = (ReferenceExtent) parameters.get(varGroup);
                    } else {
                        extent = TefkatFactory.eINSTANCE.createReferenceExtent();
                        parameters.put(varGroup, extent);
                    }
                    extent.getResources().add(resource);
                } else {
                    sourcesL.add(resource);
                }
            }
            sourcesR = (Resource[]) sourcesL.toArray(new Resource[sourcesL.size()]);

            List targets = task.getTargetModels();
            targetsR = new Resource[targets.size()];
            if (ExecutionMode.REPLACE_LITERAL.equals(task.getMode())) {
                for (int i = 0; i < targetsR.length; i++) {
                    targetsR[i] = createResource(((Model) targets.get(i)).getLocationUri());
                }
                if (null != task.getTrace()) {
                    traceR = createResource(task.getTrace().getLocationUri());
                }
            } else {
                for (int i = 0; i < targetsR.length; i++) {
                    targetsR[i] = getResource(((Model) targets.get(i)).getLocationUri());
                }
                if (null != task.getTrace()) {
                    traceR = getResource(task.getTrace().getLocationUri());
                    throw new UnsupportedOperationException("Not yet implemented: need to initialise internal structures wrt loaded trace model");
                }
            }

            transform(transformationR, parameters, sourcesR, targetsR, traceR, force);
            
            for (int i = 0; i < targetsR.length; i++) {
                setObjectIds(targetsR[i]);
                removeContainedObjectsFromResourceContents(targetsR[i]);
                if (save) {
                    targetsR[i].save(SERIALIZATION_OPTIONS);
                }
            }
            if (null != traceR) {
                setObjectIds(traceR);
                if (save) {
                    traceR.save(SERIALIZATION_OPTIONS);
                }
            }

//        } catch (ClassNotFoundException e) {
//            throw new ResolutionException(null, "Could not find requested model classes", e);
//        } catch (IllegalArgumentException e) {
//            throw new ResolutionException(null, "Could not initialise requested model classes", e);
//        } catch (SecurityException e) {
//            throw new ResolutionException(null, "Could not initialise requested model classes", e);
//        } catch (IllegalAccessException e) {
//            throw new ResolutionException(null, "Could not initialise requested model classes", e);
//        } catch (NoSuchFieldException e) {
//            throw new ResolutionException(null, "Could not initialise requested model classes", e);
        } finally {
            // clear out loaded resources so that a reload is forced
            // next time through - things may have changed so we don't
            // want any cached state.
            //
            clearResourceSet();
        }
    }

    private void removeContainedObjectsFromResourceContents(Resource resource) {
    	HashSet<EObject> toRemove = new HashSet<EObject>();
		for (EObject eobj : resource.getContents()) {
			if (null != eobj.eContainer()) {
				//resource.getContents().remove(eobj);
				//No concurrent collection removal!
				toRemove.add(eobj);
			}
		}
		resource.getContents().removeAll(toRemove);
		
	}

	/**
     * Sets the XMI IDs of the objects and avoids duplicates in the XMIResource
     * @param res
     */
    private static void setObjectIds(Resource res) {
        if (res instanceof XMIResource) {
            XMIResource xres = (XMIResource) res;
            Object[] roots = xres.getContents().toArray();
            for (int i = roots.length - 1; i >= 0; i--) {
                EObject obj = (EObject) roots[i];
                
                fixObjectId(xres, obj);
            }
        }
    }

    private static void fixObjectId(XMIResource xres, EObject obj) {
        // Set XMI ID if not already set
        if (null == xres.getID(obj)) {
            xres.setID(obj, String.valueOf(obj.hashCode()));
        }
        // remove direct containment for things that are transitively contained
        // MTH: taken from master, part of pattern fix?
        /* Moved into removeContainedObjectsFromResourceContents()
        if (null != obj.eContainer()) {
            xres.getContents().remove(obj);
        }
        */
        Object[] children = obj.eContents().toArray();
        for (int i = children.length - 1; i >= 0; i--) {
            fixObjectId(xres, (EObject) children[i]);
        }
    }

    public void pause() {
        paused = true;
        if (null != ruleEvaluator) {
            ruleEvaluator.pause();
        }
    }
    
    public void step() {
        ruleEvaluator.step();
    }

    public void stepReturn() {
        ruleEvaluator.stepReturn();
    }
    
    public void resume() {
        paused = false;
        if (null != ruleEvaluator) {
            ruleEvaluator.resume();
        }
    }

    private void fireResourceLoaded(Resource res) {
        for (Iterator itr = engineListeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).resourceLoaded(res);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void fireInfo(String message) {
        for (Iterator itr = engineListeners.iterator(); itr.hasNext(); ) {
            try {
                ((TefkatListener) itr.next()).info(message);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void fireWarning(String message) {
        for (Iterator itr = engineListeners.iterator(); itr.hasNext(); ) {
            try {
                ((TefkatListener) itr.next()).warning(message);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireStart() {
        setRunning(true);
        for (Iterator itr = engineListeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).started();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireStop() {
        setRunning(false);
        for (Iterator itr = engineListeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).stopped();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireTransformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace,
            Binding context) {
        for (Iterator itr = engineListeners.iterator(); itr.hasNext();) {
            try {
                ((TefkatListener) itr.next()).transformationStarted(transformation, srcs, tgts, trace, context);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private void fireTransformationFinished(Transformation transformation) {
        for (Iterator itr = engineListeners.iterator(); itr.hasNext();) {
            try {
                TefkatListener tl = ((TefkatListener) itr.next());
                tl.transformationFinished();
                tl.transformationFinished(transformation);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Given a list of Resources, transitively load all resources that contain referenced stuff.
     * 
     * @param resources
     * @return
     */
    static private final Set findAllResources(Collection resources) {
        Set result = new HashSet(resources);

        while (resources.size() > 0) {
            Set newResources = new HashSet();
            System.err.println("warning, skipping load of referenced resources");
            for (Iterator itr = resources.iterator(); false && itr.hasNext(); ) {
                Resource res = (Resource) itr.next();
System.err.println("findAllResources: " + res);
                Map refs = EcoreUtil.ExternalCrossReferencer.find(res);
                for (Iterator refItr = refs.keySet().iterator(); refItr.hasNext(); ) {
                    EObject o = (EObject) refItr.next();
                    Resource newRes = o.eResource();
                    // ignore things we've already seen
                    if (newRes != null && !result.contains(newRes)) {
                        newResources.add(newRes);
//                        fireResourceLoaded(newRes);
                    }
                }
            }
//            newResources.removeAll(result); // ignore things we've already seen
            result.addAll(newResources);    // remember everything for result
            resources = newResources;       // get ready to iterate over the new things
        }

        return result;
    }

    public void addParserListener(final ParserListener listener) {
        TEFKAT_RESOURCE_FACTORY.addParserListener(listener);
    }

    public void removeParserListener(final ParserListener listener) {
        TEFKAT_RESOURCE_FACTORY.removeParserListener(listener);
    }

    public EventRegistry registry() {
        return events;
    }
}
