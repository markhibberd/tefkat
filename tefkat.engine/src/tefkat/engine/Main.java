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

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;

import tefkat.config.TefkatConfig.Configuration;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.config.TefkatConfig.impl.TefkatConfigPackageImpl;
import tefkat.engine.view.RadialTreeLayoutAlgorithm;
import tefkat.engine.view.ResourceSetModel;
import tefkat.engine.view.ResourceView;
import tefkat.engine.view.Visualiser;
import tefkat.model.ContainerExtent;
import tefkat.model.Extent;
import tefkat.model.PatternUse;
import tefkat.model.ReferenceExtent;
import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Transformation;
import tefkat.model.parser.TefkatResourceFactory;


/**
 * @author lawley
 * 
 */
public class Main {

    private static final int DELAY = 1;

    private static final TefkatResourceFactory TEFKAT_RESOURCE_FACTORY = new TefkatResourceFactory();
    
    private static final Resource.Factory XMI_RESOURCE_FACTORY = new XMIResourceFactoryImpl();
    
    private static final Resource.Factory XSD_RESOURCE_FACTORY = new XSDResourceFactoryImpl();

    private static boolean quiet = false;

    private static boolean force = false;
    
    private static boolean visualize = false;

    private static boolean layout = false;

    private static ResourceSetModel model;

    private static ResourceView graph;

    private static void usage(String message) {
        System.err.println(message);
        System.err.println("usage: tefkat [-quiet] [-debug] [-force] [-fixpoint] [-statistics] [-save] [-layout] [-vis mtsT] [-mapURI from to]*");
        System.err.println("\t[-conf configURI | -transformation mappingURI");
        System.err.println("\t[-source srcURI | -sourceVar var=srcURI]* [-target targetURI]* [-trace traceURI]]");
        System.exit(1);
    }
    
    public static void main(String[] args) {
        boolean mapVis = false, targetVis = false, sourceVis = false, traceVis = false;
        boolean debugger = false;
        String configURI = null;
        String transformationURI = null;
        List sourceURIs = new ArrayList();
        List targetURIs = new ArrayList();
        String traceURI = null;
        boolean saveResult = false;
        JFrame frame = null;
        
        final Tefkat engine = new Tefkat();
        engine.addTefkatListener(new TefkatListenerAdapter() {

            public void resourceLoaded(Resource res) {
                if (!quiet) {
                    System.err.println("Loaded " + res.getURI());
                }
                displayDiagnostics("Warning", res.getWarnings());
                final List<Diagnostic> errors = res.getErrors();
                displayDiagnostics("Error", errors);
                if (errors.size() > 0) {
                    System.exit(-1);
                }
            }

            private void displayDiagnostics(final String prefix, final List<Diagnostic> errors) {
                for (final Iterator<Diagnostic> itr = errors.iterator(); itr.hasNext(); ) {
                    final Diagnostic e = itr.next();
                    System.err.println(prefix + ": (" + e.getLine() + ", " + e.getColumn() + ") " + e.getMessage());
                }
            }
            
            public void info(String message) {
                if (!quiet) {
                    System.err.println("Info: " + message);
                }
            }
            
            public void warning(String message) {
                System.err.println("Warn: " + message);
            }
            
            public void error(String message, Throwable cause) {
                if (cause instanceof RuntimeException) {
                    cause.printStackTrace();
                } else {
                    System.err.println("Error: " + message);
                    for (StackTraceElement elt: cause.getStackTrace()) {
                        if (elt.getClassName().startsWith("tefkat")) {
                            System.err.println("  at " + elt);
//                            break;
                        }
                    }
                }
            }

        });
        
        ResourceSet rs = engine.getResourceSet();
        Map URIMap = rs.getURIConverter().getURIMap();
        Map parameters = new HashMap();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-layout")) {
                layout = true;
            } else if (args[i].equals("-debug")) {
                debugger = true;
            } else if (args[i].equals("-force")) {
                force = true;
            } else if (args[i].equals("-quiet")) {
                quiet = true;
            } else if (args[i].equals("-fixpoint")) {
                engine.setIncremental(false);
            } else if (args[i].equals("-statistics")) {
                engine.setPrintingStats(true);
            } else if (args[i].equals("-save")) {
                saveResult = true;
            } else if (args[i].equals("-vis")) {
                visualize = true;
                i += 1;
                for (int j = 0; j < args[i].length(); j++) {
                    char c = args[i].charAt(j);
                    switch (c) {
                    case 'm':
                        mapVis = true;
                        break;
                    case 's':
                        sourceVis = true;
                        break;
                    case 't':
                        targetVis = true;
                        break;
                    case 'T':
                        traceVis = true;
                        break;
                    default:
                        usage("Unknown flag for visualisation: " + c);
                    }
                }
            } else if (args[i].equals("-mapURI")) {
                URI uri1 = URI.createURI(args[i+1]);
                URI uri2 = URI.createURI(args[i+2]);
                URIMap.put(uri1, uri2);
                URIConverter.URI_MAP.put(uri1, uri2);
                i += 2;
            } else if (args[i].equals("-conf")) {
                i += 1;
                configURI = args[i];
            } else if (args[i].equals("-transformation")) {
                i += 1;
                transformationURI = args[i];
            } else if (args[i].equals("-source")) {
                i += 1;
                sourceURIs.add(args[i]);
            } else if (args[i].equals("-sourceVar")) {
                i += 1;
                int idx = args[i].indexOf("=");
                String var = args[i].substring(0, idx);
                String uri = args[i].substring(idx+1);
                parameters.put(var, uri);
            } else if (args[i].equals("-target")) {
                i += 1;
                targetURIs.add(args[i]);
            } else if (args[i].equals("-trace")) {
                i += 1;
                traceURI = args[i];
            } else {
                usage("Unknown flag: " + args[i]);
            }
        }
        
        TefkatConfigPackageImpl.init();
        
        engine.registerFactory("qvt", TEFKAT_RESOURCE_FACTORY);
        engine.registerFactory("xsd", XSD_RESOURCE_FACTORY);
        engine.registerFactory("wsdl", XSD_RESOURCE_FACTORY);
        engine.registerFactory("*", XMI_RESOURCE_FACTORY);

        try {
            
            if (debugger) {
                System.err.println("debugging mode");
                createDebuggerListener(engine);
            }
            
            if (configURI != null) {
                if (visualize) {
                    frame = createUI(rs, new ArrayList(0), layout);
                    createVisualisationListener(engine, mapVis, sourceVis, targetVis, traceVis);
                }

                Resource config = rs.getResource(URI.createURI(configURI), true);
                for (Iterator configItr = config.getContents().iterator(); configItr.hasNext(); ) {
                    Object obj = configItr.next();
                    if (obj instanceof Configuration) {
                    	Configuration configuration = (Configuration) obj;
                    	for (Iterator pkgClsItr = configuration.getPackageClasses().iterator(); pkgClsItr.hasNext(); ) {
                    		String pkgClsName = (String) pkgClsItr.next();
                    		Class pkgClass = Class.forName(pkgClsName);
                    		pkgClass.getDeclaredField("eINSTANCE").get(null);
                    	}
                        for (Iterator transItr = configuration.getTransformationTasks().iterator(); transItr.hasNext(); ) {
                            TransformationTask task = (TransformationTask) transItr.next();

                            if (task.isEnabled()) {
                                // override trace URI
                                if (null != traceURI) {
                                    task.getTrace().setLocationUri(traceURI);
                                }
                                // override target URIs
                                if (targetURIs.size() > 0) {
                                    List targets = task.getTargetModels();
                                    for (int i = 0; i < targets.size(); i++) {
                                        ((Model) targets.get(i)).setLocationUri((String) targetURIs.get(i));
                                    }
                                }
                                engine.transform(task, saveResult, force);
                            }
                        }
                    } else {
                        System.err.println("Warning: " + obj + " is not a Configuration instance.");
                    }
                }
            } else {
                if (transformationURI == null) {
                    usage("Must specify a config URI or a transformation URI");
                }
                int j;
                Resource transformation = rs.getResource(URI.createURI(transformationURI), true);
                for (Iterator itr = parameters.entrySet().iterator(); itr.hasNext(); ) {
                    Map.Entry entry = (Map.Entry) itr.next();
                    URI uri = URI.createURI((String) entry.getValue());
                    entry.setValue(rs.getResource(uri, true));
                }
                Resource[] srcs = new Resource[sourceURIs.size()];
                j = 0;
                for (Iterator itr = sourceURIs.iterator(); itr.hasNext(); j++) {
                    URI uri = URI.createURI((String) itr.next());
                    srcs[j] = rs.getResource(uri, true);
                }
                Resource[] tgts = new Resource[targetURIs.size()];
                j = 0;
                for (Iterator itr = targetURIs.iterator(); itr.hasNext(); j++) {
                    URI uri = URI.createURI((String) itr.next());
                    tgts[j] = rs.createResource(uri);
                }
                Resource trace = null;
                if (traceURI != null) {
                    trace = rs.createResource(URI.createURI(traceURI));
                }

                if (visualize) {
                    List resList = new ArrayList();
                    if (mapVis) {
                        resList.add(transformation);
                    }
                    if (sourceVis) {
                        resList.addAll(Arrays.asList(srcs));
                    }
                    if (targetVis) {
                        resList.addAll(Arrays.asList(tgts));
                    }
                    if (traceVis) {
                        resList.add(trace);
                    }

                    frame = createUI(engine.getResourceSet(), resList, layout);
                    createVisualisationListener(engine, mapVis, sourceVis, targetVis, traceVis);
                }

                System.out.println("transforming...");
                engine.transform(transformation, parameters, srcs, tgts, trace, false);
                if (saveResult) {
                    Map options = new HashMap();
                    options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
                    options.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData());
                    for (int i = 0; i < tgts.length; i++) {
                        setObjectIds(tgts[i]);
                        tgts[i].save(options);
                    }
		    if (null != trace) {
                        setObjectIds(trace);
			trace.save(options);
		    }
                }
                System.out.println("...done");
            }

            if (visualize) {
                final JFrame theFrame = frame;
                // Be thread-safe
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        theFrame.repaint();
                    }
                });
                Thread.sleep(5000);
            }

        } catch (ResolutionException e) {
        } catch (Throwable t) {
            t.printStackTrace();
//        } finally {
//             System.exit(0);
        }

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
        if (null != obj.eContainer()) {
            xres.getContents().remove(obj);
        }
        Object[] children = obj.eContents().toArray();
        for (int i = children.length - 1; i >= 0; i--) {
            fixObjectId(xres, (EObject) children[i]);
        }
    }
    
    /**
     * @param engine
     */
    private static void createDebuggerListener(final Tefkat engine) {
        engine.addTefkatListener(new TefkatListenerAdapter() {
            
            int depth = 0;

            public void started() {
                engine.pause();
            }
            
            public void suspended() {
                try {
                    int c;
                    do {
                        c = System.in.read();
                    } while (c >= 0 && c != '\n');
                        
                    if (c < 0) {
                        engine.setInterrupted(true);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                engine.step();
            }
            
            public void enterTerm(Node node) {
                depth++;
                indent(depth, '>');
                System.err.print(" " + node.selectedLiteral() + " ? ");
            }

            public void exitTerm(Node node) {
                System.err.println(node.getBindings());
                indent(depth, '<');
                System.err.println(" " + !node.isFailure());
                depth--;
            }

            public void delayTerm(Node node) {
                System.err.println(node.getBindings());
                indent(depth, '-');
                System.err.println(" delayed");
                depth--;
            }
            
            private void indent(int n, char c) {
                for (int i = 0; i < n; i++) {
                    System.err.print(c);
                }
            }

        });
    }

    /**
     * @param engine
     */
    private static void createVisualisationListener(Tefkat engine, final boolean mapVis, final boolean sourceVis, final boolean targetVis, final boolean traceVis) {
        engine.addTefkatListener(new TefkatListenerAdapter() {

            public void resourceLoaded(Resource res) {
                System.err.println("Loaded " + res.getURI());
            }
            
            public void transformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
                if (mapVis) {
                    model.addResource(transformation.eResource());
                }
                if (sourceVis) {
                    for (int i = 0; i < srcs.length; i++) {
                    	if (srcs[i] instanceof ContainerExtent) {
                    		model.addResource(((ContainerExtent) srcs[i]).getResource());
                    	} else {
                    		for (Iterator itr = ((ReferenceExtent) srcs[i]).getResources().iterator(); itr.hasNext(); ) {
                    			model.addResource((Resource) itr.next());
                    		}
                    	}
                    }
                }
                if (targetVis) {
                    for (int i = 0; i < tgts.length; i++) {
                    	if (tgts[i] instanceof ContainerExtent) {
                    		model.addResource(((ContainerExtent) tgts[i]).getResource());
                    	} else {
                    		for (Iterator itr = ((ReferenceExtent) tgts[i]).getResources().iterator(); itr.hasNext(); ) {
                    			model.addResource((Resource) itr.next());
                    		}
                    	}
                    }
                }
                if (traceVis) {
                	if (trace instanceof ContainerExtent) {
                		model.addResource(((ContainerExtent) trace).getResource());
                	} else {
                		for (Iterator itr = ((ReferenceExtent) trace).getResources().iterator(); itr.hasNext(); ) {
                			model.addResource((Resource) itr.next());
                		}
                	}
                }
            }

            public void evaluateRule(TRule rule, Binding context, boolean cached) {
                if (mapVis) {
                    ExtentUtil.highlightNode(rule, ExtentUtil.RULE_EVAL);
                }
            }

            public void enterTerm(Node node) {
                if (mapVis) {
                    Term term = node.selectedLiteral();
                    if (term instanceof PatternUse) {
                        ExtentUtil.highlightNode(((PatternUse) term)
                                .getDefn(), ExtentUtil.TERM_ENTER);
                    }
                    ExtentUtil.highlightNode(term, ExtentUtil.TERM_ENTER);
                }
            }

            public void exitTerm(Node node) {
                if (mapVis) {
                    Term term = node.selectedLiteral();
                    if (term instanceof PatternUse) {
                        ExtentUtil.highlightNode(((PatternUse) term)
                                .getDefn(), ExtentUtil.TERM_EXIT);
                    }
                    ExtentUtil.highlightNode(term, ExtentUtil.TERM_EXIT);
                }
            }

            public void delayTerm(Node node) {
                if (mapVis) {
                    Term term = node.selectedLiteral();
                    if (term instanceof PatternUse) {
                        ExtentUtil.highlightNode(((PatternUse) term).getDefn(), ExtentUtil.TERM_DELAY);
                    }
                    ExtentUtil.highlightNode(term, ExtentUtil.TERM_DELAY);
                }
            }

        });
    }

    private static JFrame createUI(ResourceSet resourceSet, List resources,
            boolean layout) {
        model = new ResourceSetModel(resourceSet);
        graph = new ResourceView(model);

        for (int i = 0; i < resources.size(); i++) {
            model.addResource((Resource) resources.get(i));
        }

        ToolTipManager.sharedInstance().registerComponent(graph);

        try {
            final RadialTreeLayoutAlgorithm layoutAlg = new RadialTreeLayoutAlgorithm();
            final Properties props = new Properties();
            props.setProperty(RadialTreeLayoutAlgorithm.KEY_WIDTH, "1200");
            props.setProperty(RadialTreeLayoutAlgorithm.KEY_HEIGHT, "1000");
            layoutAlg.perform(graph, true, props);

            if (layout) {
                final LayoutThread layoutThread = new LayoutThread(props, layoutAlg);
                layoutThread.start();
                
                graph.getModel().addGraphModelListener(
                        new GraphModelListener() {
                            public void graphChanged(GraphModelEvent evt) {
                                Object[] inserted = evt.getChange().getInserted();
                                if (null == inserted || inserted.length == 0) {
                                    return;
                                }
                                if (graph.getModel().getRootCount() > 0) {
                                    layoutThread.requestLayout();
                                    // layoutAlg.perform(graph, true, props);
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        graph.setMinimumSize(new Dimension(1200, 1000));
        final JFrame frame = new JFrame();
        frame.getContentPane().add(new JScrollPane(graph));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // Be thread-safe
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });

        ExtentUtil.addExtentListener(new Visualiser(graph, DELAY));
        
        return frame;
    }

    private static final class LayoutThread extends Thread {
        private transient boolean layoutRequested = false;

        private final Properties props;

        private final RadialTreeLayoutAlgorithm layoutAlg;

        LayoutThread(Properties props, RadialTreeLayoutAlgorithm layoutAlg) {
            this.props = props;
            this.layoutAlg = layoutAlg;
        }

        synchronized public void requestLayout() {
            layoutRequested = true;
            if (!isAlive()) {
                System.err.println("launch");
                start();
            }
            notifyAll();
            System.err.println("requested");
        }

        public void run() {
            boolean doLayout = false;
            
            while (true) {
                synchronized (this) {
                    if (layoutRequested) {
                        doLayout = true;
                        layoutRequested = false;
                    } else {
                        doLayout = false;
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                if (doLayout) {
                	long start = System.currentTimeMillis();
                    try {
                        System.err.println("start...");
                        layoutAlg.perform(graph, true, props);
                        System.err.println("...stop");
                    } catch (Throwable t) {
                        t.printStackTrace();
                    } finally {
                    	long end = System.currentTimeMillis();
                    	System.err.println("layout time: " + (end - start));
                    }
                }
            }
        }
    }

}
