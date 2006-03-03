/*
 *
 *  Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2004.
 *  Unpublished work.  All Rights Reserved.
 *
 *  The software contained on this media is the property of the
 *  DSTC Pty Ltd.  Use of this software is strictly in accordance
 *  with the license agreement in the accompanying LICENSE.DOC
 *  file.  If your distribution of this software does not contain
 *  a LICENSE.DOC file then you have no rights to use this
 *  software in any manner and should contact DSTC at the address
 *  below to determine an appropriate licensing arrangement.
 *
 *     DSTC Pty Ltd
 *     Level 7, G.P. South
 *     Staff House Road
 *     University of Queensland
 *     St Lucia, 4072
 *     Australia
 *     Tel: +61 7 3365 4310
 *     Fax: +61 7 3365 4311
 *     Email: enquiries@dstc.edu.au
 *
 *  This software is being provided "AS IS" without warranty of
 *  any kind.  In no event shall DSTC Pty Ltd be liable for
 *  damage of any kind arising out of or in connection with
 *  the use or performance of this software.
 *
 *  Project:  TefkatPlugin
 *
 *  File:     TefkatPlugin.java
 *
 *  History:  Created on 28/05/2004 by lawley
 *
 */

package com.dstc.tefkat.plugin;

import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.osgi.framework.BundleContext;

import com.dstc.tefkat.engine.Binding;
import com.dstc.tefkat.engine.Function;
import com.dstc.tefkat.engine.Tefkat;
import com.dstc.tefkat.engine.TefkatListener;
import com.dstc.tefkat.engine.TefkatListenerAdapter;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.Transformation;
import com.dstc.tefkat.model.parser.TefkatResourceFactory;
import com.dstc.tefkat.plugin.TefkatPreferencePage.URIMap;
import com.dstc.tefkat.config.TefkatConfig.ExecutionMode;
import com.dstc.tefkat.config.TefkatConfig.Model;
import com.dstc.tefkat.config.TefkatConfig.TefkatConfigFactory;
import com.dstc.tefkat.config.TefkatConfig.TransformationTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class TefkatPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "com.dstc.tefkat.plugin";

    public static final String TEFKAT_NATURE = PLUGIN_ID + ".TefkatNature";

    public static final String TEFKAT_BUILDER = PLUGIN_ID + ".TefkatBuilder";
    
    public static final String TEFKAT_PARTITIONING = PLUGIN_ID + ".TefkatPartitioning";
    
    public static final String PLUGIN_FUNCTION_SET = PLUGIN_ID + ".functionSet";

    public static final String CONFIGURATION_FILE = "tefkat.xml";

    public static final String URIMAP_PREFERENCE = "URIMap";

    public static final TefkatResourceFactory TEFKAT_RESOURCE_FACTORY = new TefkatResourceFactory();
    
    private static final Resource.Factory XMI_RESOURCE_FACTORY = new XMIResourceFactoryImpl();
    
    final static String TEFKAT_CONSOLE_NAME = "Tefkat";

    final static Color INFO_COLOR = new Color(null, 0, 0, 255);
    final static Color WARN_COLOR = new Color(null, 255, 0, 0);

    private static Map SERIALIZATION_OPTIONS;

    static {
        SERIALIZATION_OPTIONS = new HashMap();
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData());
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    }

    // The shared instance.
    private static TefkatPlugin plugin;

    // Resource bundle.
    private ResourceBundle resourceBundle;
    
    // The QVT parser wrapped as a resource factory
    static {
        Map map = Registry.INSTANCE.getExtensionToFactoryMap();
        map.put("qvt", TEFKAT_RESOURCE_FACTORY);
    }

    // The Tefkat Transformation Engine
    private Tefkat theEngine;
    
    private MessageConsole console;
    private MessageConsoleStream infoStream;
    private MessageConsoleStream warnStream;

    private TefkatListener consoleAdapter = new TefkatListenerAdapter() {

        public void info(String message) {
            infoStream.println("INFO: " + message);
        }

        public void warning(String message) {
            warnStream.println("WARN: " + message);
        }

        public void error(String message, Throwable cause) {
            warnStream.println("ERROR: " + message);
            StringWriter s = new StringWriter();
            cause.printStackTrace(new PrintWriter(s));
            warnStream.println(s.toString());
        }

        public void transformationStarted(Transformation transformation, Resource[] srcs, Resource[] tgts, Resource trace, Binding context) {
            info("Transformation started: " + transformation.getName());
        }

        public void transformationFinished() {
            info("Transformation finished");
        }

        public void resourceLoaded(Resource res) {
            info("Loaded (" + res.hashCode() + ") " + res.getURI());
        }

        public void evaluateRule(TRule rule, Binding context, boolean cached) {
            info("Evaluating " + rule.getName());
        }
        
//        public void enterTerm(Node node) {
//            info("Enter: " + node);
//        }
//
//        public void exitTerm(Node node) {
//            info("Exit:  " + node);
//        }
//
//        public void delayTerm(Node node) {
//            info("Delay: " + node);
//        }
    };
    
    private RuleBasedPartitionScanner scanner;

    /**
     * The constructor.
     */
    public TefkatPlugin() {
        plugin = this;
        try {
            resourceBundle = ResourceBundle.getBundle("com.dstc.tefkat.plugin.TefkatPluginResources");
        } catch (MissingResourceException x) {
            resourceBundle = null;
        }
    }

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        
        List list = convertFromString(getPreferenceStore().getString(URIMAP_PREFERENCE));
        for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
            URIMap map = (URIMap) itr.next();
            if (map.enabled) {
                URI sourceUri = URI.createURI(map.source);
                URI targetUri = URI.createURI(map.target);
                URIConverter.URI_MAP.put(sourceUri, targetUri);
            }
        }
    }
    /**
     * Returns the shared instance.
     */
    public static TefkatPlugin getPlugin() {
        return plugin;
    }

    /**
     * Returns the workspace instance.
     */
    public static IWorkspace getWorkspace() {
        return ResourcesPlugin.getWorkspace();
    }

    /**
     * Returns the string from the plugin's resource bundle, or 'key' if not
     * found.
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = TefkatPlugin.getPlugin().getResourceBundle();
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }
    
    static List convertFromString(String preferenceValue) {
        List list = new ArrayList();
        String[] values = preferenceValue.split("[\t\n]");
        for (int i = 2; i < values.length; i += 3) {
            URIMap map = new URIMap();
            map.enabled = Boolean.valueOf(values[i - 2]).booleanValue();
            map.source = values[i - 1];
            map.target = values[i];
            list.add(map);
        }
        return list;
    }
    
    static String convertToString(List list) {
        StringBuffer sb = new StringBuffer();
        for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
            URIMap map = (URIMap) itr.next();
            sb.append(map.enabled);
            sb.append("\t");
            sb.append(map.source);
            sb.append("\t");
            sb.append(map.target);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void addTefkatListener(TefkatListener listener) {
        getTefkat().addTefkatListener(listener);
    }

    public void removeTefkatListener(TefkatListener listener) {
        getTefkat().removeTefkatListener(listener);
    }

    public Tefkat getTefkat() {
        if (null == theEngine) {
            theEngine = new Tefkat();
            theEngine.registerFactory("qvt", TEFKAT_RESOURCE_FACTORY);

            ConsolePlugin plugin = ConsolePlugin.getDefault();
            IConsoleManager manager = plugin.getConsoleManager();
            IConsole[] existing = manager.getConsoles();
            // remove any old consoles
            for (int i = 0; i < existing.length; i++) {
                if (TEFKAT_CONSOLE_NAME.startsWith(existing[i].getName())) {
                    manager.removeConsoles(new IConsole[]{ existing[i] });
                }
            }

            console = new MessageConsole(TEFKAT_CONSOLE_NAME, null);
            manager.addConsoles(new IConsole[]{ console });
            manager.showConsoleView(console);
            infoStream = console.newMessageStream();
            warnStream = console.newMessageStream();
            
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    infoStream.setColor(INFO_COLOR);
                    warnStream.setColor(WARN_COLOR);
                }
            });

            theEngine.addTefkatListener(consoleAdapter);
            initFunctionSets();
        }
        return theEngine;
    }
    
    private void initFunctionSets() {
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(TefkatPlugin.PLUGIN_FUNCTION_SET);
        IConfigurationElement[] funcSetElements = extensionPoint.getConfigurationElements();
        for (int j = 0; j < funcSetElements.length; j++) {
            IConfigurationElement funcSet = funcSetElements[j];
            IConfigurationElement[] functionElements = funcSet.getChildren("function");
            for (int i = 0; i < functionElements.length; i++) {
                IConfigurationElement function = functionElements[i];
                String name = function.getAttribute("name");
                if (name != null && function.getAttribute("class") != null) {
                    try {
                        Object instance = function.createExecutableExtension("class");
                        if (instance instanceof Function) {
                            getTefkat().addFunction(name, (Function) instance);
                        } else {
                            String namespace = functionElements[i].getDeclaringExtension().getNamespace();
                            getLog().log(new Status(IStatus.ERROR, TefkatPlugin.PLUGIN_FUNCTION_SET, IStatus.OK, "Wrong type for function from " + namespace + ": " + name, null));
                        }
                    } catch (CoreException e) {
                        String namespace = functionElements[i].getDeclaringExtension().getNamespace();
                        getLog().log(new Status(IStatus.ERROR, TefkatPlugin.PLUGIN_FUNCTION_SET, IStatus.OK, "Error loading function from " + namespace + ": " + name, e));
                    }
                } else {
                    String namespace = functionElements[i].getDeclaringExtension().getNamespace();
                    getLog().log(new Status(IStatus.ERROR, TefkatPlugin.PLUGIN_FUNCTION_SET, IStatus.OK, "Invalid function from " + namespace + ": " + name, null));
                }
            }
        }
    }

    public void addAutoBuildNature(IProject project) throws CoreException {
        if (project.findMember(CONFIGURATION_FILE) == null) {
            URI uri = URI.createPlatformResourceURI(project.getName())
                    .appendSegment(CONFIGURATION_FILE);
            Resource res = getResourceSet().createResource(uri);
            res.getContents().add(
                    TefkatConfigFactory.eINSTANCE.createConfiguration());
            try {
                res.save(null);
            } catch (IOException e) {
                IStatus status = new Status(IStatus.ERROR,
                        TefkatPlugin.PLUGIN_ID, IStatus.OK,
                        "Failed to create Tefkat configuration", e);
                this.getLog().log(status);
                throw new CoreException(status);
            }
            project.refreshLocal(IResource.DEPTH_ONE, null);
        }

        if (project.hasNature(TEFKAT_NATURE)) {
            return;
        }

        IProjectDescription description = project.getDescription();
        String[] ids = description.getNatureIds();
        String[] newIds = new String[ids.length + 1];
        System.arraycopy(ids, 0, newIds, 0, ids.length);
        newIds[ids.length] = TEFKAT_NATURE;
        description.setNatureIds(newIds);
        project.setDescription(description, null);
    }

    public void removeAutoBuildNature(IProject project) throws CoreException {
        IProjectDescription description = project.getDescription();
        String[] ids = description.getNatureIds();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(TEFKAT_NATURE)) {
                String[] newIds = new String[ids.length - 1];
                System.arraycopy(ids, 0, newIds, 0, i);
                System.arraycopy(ids, i + 1, newIds, i, ids.length - i - 1);
                description.setNatureIds(newIds);
                project.setDescription(description, null);
                return;
            }
        }
    }

    public boolean getInterrupted() {
        return getTefkat().getInterrupted();
    }
    
    public void setInterrupted(boolean state) {
        getTefkat().setInterrupted(state);
    }
    
    public void pause() {
        getTefkat().pause();
    }
    
    public void step() {
        getTefkat().step();
    }
    
    public void resume() {
        getTefkat().resume();
    }
 
    public void run(TransformationTask task, IProgressMonitor monitor)
            throws CoreException {
        String transformation = task.getTransformation().getLocationUri();
        String trace = null;
        if (null != task.getTrace()) {
            trace = task.getTrace().getLocationUri();
        }
        List sourceModels = task.getSourceModels();
        List targetModels = task.getTargetModels();
        String[] sources = new String[sourceModels.size()];
        String[] targets = new String[targetModels.size()];

        for (int i = 0; i < sources.length; i++) {
            sources[i] = ((Model) sourceModels.get(i)).getLocationUri();
        }
        for (int i = 0; i < targets.length; i++) {
            targets[i] = ((Model) targetModels.get(i)).getLocationUri();
        }
        
        List props = task.getUriMap();

        execute(transformation, sources, targets, trace, task.getMode(),
                props, monitor);
    }

    public void execute(String transformation, String[] sourceModels,
            String[] targetModels, String trace, ExecutionMode mode,
            Collection props, final IProgressMonitor monitor) throws CoreException {

        Resource transformationR = null;
        Resource[] sourcesR = null;
        Resource[] targetsR = null;
        Resource traceR = null;

        try {
            monitor.subTask("Loading models " + transformation);

            URIConverter converter = getResourceSet().getURIConverter();
            Map URIMap = converter.getURIMap();
            for (Iterator itr = props.iterator(); itr.hasNext(); ) {
                Map.Entry entry = (Map.Entry) itr.next();
                URI uri1 = URI.createURI((String) entry.getKey());
                URI uri2 = URI.createURI((String) entry.getValue());
                URIMap.put(uri1, uri2);
            }

            transformationR = getResource(transformation, monitor);
            sourcesR = getResources(sourceModels, monitor);
            if (mode.equals(ExecutionMode.REPLACE_LITERAL)) {
                targetsR = createResources(targetModels);
                if (null != trace) {
                    traceR = createResource(trace);
                }
            } else {
                targetsR = getResources(targetModels, monitor);
                if (null != trace) {
                    traceR = getResource(trace, monitor);
                }
            }
            monitor.worked(1);
            
            if (monitor.isCanceled()) {
                throw new InterruptedException("Tefkat Build cancelled by user.");
            }
            
            monitor.subTask("Running " + transformation);
            Tefkat engine = getTefkat();
            TefkatListener monitorListener = new TefkatListenerAdapter() {
                
                public void info(String message) {
                    if (message.equalsIgnoreCase("stratification")) {
                        monitor.subTask(message);
                    }
                }
                
                public void evaluateRule(TRule rule, Binding context, boolean cached) {
                    monitor.subTask("Evaluating rule: " + rule.getName());
                }

                public void transformationStarted(Transformation transformation, Resource[] srcs, Resource[] tgts, Resource trace, Binding context) {
                    monitor.worked(2);
                }

                public void transformationFinished() {
                    monitor.worked(2);
                }
            };
            engine.addTefkatListener(monitorListener);
            EngineThread thread = new EngineThread(engine, transformationR, sourcesR, targetsR, traceR);
            thread.start();
            while (thread.isAlive()) {
                if (monitor.isCanceled()) {
                    getTefkat().setInterrupted(true);
                    thread.join(1000);
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (RuntimeException e1) {
                }
            }
            engine.removeTefkatListener(monitorListener);
            if (null != thread.getException()) {
                throw thread.getException();
            }

            monitor.subTask("Storing results " + transformation);
            for (int i = 0; i < targetsR.length; i++) {
                setObjectIds(targetsR[i]);
                targetsR[i].save(SERIALIZATION_OPTIONS);
            }
            if (null != traceR) {
                setObjectIds(traceR);
                traceR.save(SERIALIZATION_OPTIONS);
            }
            monitor.worked(1);
            
        } catch (Exception e) {
            String message = e.getMessage();
            if (null == message) {
                message = e.toString();
                e.printStackTrace();
            }
            IStatus status = new Status(IStatus.ERROR, TefkatPlugin.PLUGIN_ID,
                    IStatus.OK, "Tefkat failed: " + message, e);
            getLog().log(status);
            throw new CoreException(status);
        } finally {
            
            removeTefkatListener(consoleAdapter);
            theEngine = null;
            
            // clear out loaded resources so that a reload is forced
            // next time through - things may have changed so we don't
            // want any cached state.
            //
            if (true) {
                clearResourceSet();
//                getTefkat().registerFactory("qvt", new TefkatResourceFactory(getTefkat().getResourceSet()));
            } else {
//                unloadResource(traceE.getResource());
//                unloadResource(transformationE.getResource());
//                for (int i = 0; i < sourcesE.length; i++) {
//                    unloadResource(sourcesE[i].getResource());
//                }
//                for (int i = 0; i < targetsE.length; i++) {
//                    unloadResource(targetsE[i].getResource());
//                }

                unloadResources();
            }
        }
    }
    
    private void setObjectIds(Resource res) {
        if (res instanceof XMIResource) {
            XMIResource xres = (XMIResource) res;
            for (Iterator itr = xres.getAllContents(); itr.hasNext(); ) {
                EObject obj = (EObject) itr.next();
                if (null == xres.getID(obj)) {
                    xres.setID(obj, String.valueOf(obj.hashCode()));
                }
            }
        }
    }

    public Resource[] createResources(String[] modelURIs) {
        Resource[] resource = new Resource[modelURIs.length];

        for (int i = 0; i < modelURIs.length; i++) {
            resource[i] = createResource(modelURIs[i]);
        }

        return resource;
    }

    public Resource createResource(String modelURI) {
        try {
            URI uri = URI.createURI(modelURI);
            return getResourceSet().createResource(uri);
        } catch (Throwable t) {
            throw new RuntimeException("Could not create '" + modelURI + "': "
                    + t.getMessage(), t);
        }
    }
    
    private Resource getResource(String modelURI, IProgressMonitor monitor) throws IOException {
        Resource resource;
        try {
            monitor.subTask("Loading " + modelURI);
            URI uri = URI.createURI(modelURI);
            resource = getResourceSet().getResource(uri, true);
        } catch (Throwable t) {
            t.printStackTrace();
            IOException e = new IOException("Could not load '" + modelURI + "': " + t.getMessage());
            e.initCause(t);
            throw e;
        }

        List errors = resource.getErrors();
        if (errors.size() > 0) {
            String message = "";
            for (int i = 0; i < errors.size(); i++) {
                Resource.Diagnostic diag = (Resource.Diagnostic) errors.get(i);
                message += diag.getMessage() + ": " + diag.getLine() + ", " + diag.getColumn() + " of " + diag.getLocation() + "\n";
            }
            throw new IOException(message);
        }
        
        return resource;
    }
    
    private Resource[] getResources(String[] modelURIs, IProgressMonitor monitor) throws IOException {
        Resource[] resources = new Resource[modelURIs.length];

        for (int i = 0; i < modelURIs.length; i++) {
            resources[i] = getResource(modelURIs[i], monitor);
        }
        
        return resources;
    }
    
    private void unloadResource(Resource res) {
//        System.out.println("unloading " + res.getURI());
        res.getContents().clear();
        res.unload();
    }

    // This causes meta-models to also be unloaded so that we can deal
    // with changes to meta-models as well as their instances
    //
    private void unloadResources() {
        System.out.println("Unloading all resources...");
        List resources = getResourceSet().getResources();
        for (Iterator itr = resources.iterator(); itr.hasNext();) {
            Resource res = (Resource) itr.next();
            System.out.println("clearing  " + res.getURI());
            res.getContents().clear();
//        }
//        for (Iterator itr = resources.iterator(); itr.hasNext();) {
//            Resource res = (Resource) itr.next();
            System.out.println("unloading " + res.getURI());
            res.unload();
        }
//        System.out.println("Cleaned up " + resources.size() + " resources.");
//        resources.clear();
        System.out.println("...resources unloaded.");
    }

    private ResourceSet _resourceSet = null;
    
    public ResourceSet getResourceSet() {
        if (null == _resourceSet) {
            _resourceSet = new ResourceSetImpl();
            _resourceSet.getLoadOptions().putAll(SERIALIZATION_OPTIONS);

            Map map = _resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
            map.put("ecore", XMI_RESOURCE_FACTORY);
            map.put("xmi", XMI_RESOURCE_FACTORY);
            map.put("tefkat", XMI_RESOURCE_FACTORY);
            // map.put("qvt", TEFKAT_RESOURCE_FACTORY);
        }
        return _resourceSet;
    }
    
    public void clearResourceSet() {
        if (null != _resourceSet) {
            _resourceSet = null;
        }
        if (null != theEngine) {
            theEngine.clearResourceSet();
        }
    }

    private static class EngineThread extends Thread {
        private Tefkat engine;
        private Resource transformation;
        private Resource[] sources;
        private Resource[] targets;
        private Resource trace;
        
        private Exception exception;

        public EngineThread(final Tefkat engine, final Resource transformation, final Resource[] sources, final Resource[] targets, final Resource trace) {
            this.engine = engine;
            this.transformation = transformation;
            this.sources = sources;
            this.targets = targets;
            this.trace = trace;
            
            final IWorkbench workbench = PlatformUI.getWorkbench();
            workbench.getDisplay().syncExec(new Runnable() {
                public void run() {
                    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
                    IWorkbenchPage page = window.getActivePage();
                    IViewPart view = page.findView("com.dstc.tefkat.plugin.TefkatView");
                    if (null != view) {
                        TefkatListener listener = (TefkatListener) view.getAdapter(TefkatListener.class);
                        if (null != listener) {
                            engine.addTefkatListener(listener);
                        }
                    }
                }
            });

        }
        
        public Exception getException() {
            return exception;
        }

        public void run() {
            try {
                engine.transform(transformation, sources, targets, trace);
            } catch (Exception e) {
                exception = e;
            }
        }

    }

    public RuleBasedPartitionScanner getTefkatPartitionScanner() {
        if (scanner == null) {
            scanner = new TefkatPartitionScanner();
        }
        return scanner;
    }
    
}
