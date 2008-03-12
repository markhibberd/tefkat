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

package tefkat.plugin;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.xsd.util.XSDResourceImpl;

import tefkat.engine.Tefkat;
import tefkat.model.StratificationException;
import tefkat.model.Term;
import tefkat.model.Transformation;
import tefkat.model.parser.ParserEvent;
import tefkat.model.parser.ParserListener;
import tefkat.model.parser.TefkatLexer;
import tefkat.model.parser.TefkatMessageEvent;
import tefkat.model.parser.TefkatParser;
import tefkat.plugin.stats.AnnotatedDocument;
import tefkat.plugin.stats.AnnotatingStatsListener;
import tefkat.plugin.stats.IndexedEObjectLookup;
import tefkat.plugin.stats.TermStats.TermStat;
import antlr.ANTLRException;
import antlr.RecognitionException;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageAdapter;
import antlr.debug.MessageEvent;


import org.eclipse.emf.ecore.util.EcoreUtil;
/**
 * @author lawley
 *
 */
public class TefkatModelEditor extends MultiPageEditorPart {

    final private static String PARSE_ERROR = TefkatPlugin.PLUGIN_ID + ".parser.error";

    private ParserThread runner = new ParserThread();

    private static Map SERIALIZATION_OPTIONS;

    static {
        SERIALIZATION_OPTIONS = new HashMap();
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData());
        SERIALIZATION_OPTIONS.put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);

    }
    private int EDITOR_PAGE = -1;
    private int XMI_PAGE = -1;
    private int STRATIFICATION_PAGE = -1;

    private TefkatTextEditor textEditor;
    private StyledText xmiText;
    private StyledText stratificationText;

    private TefkatModelOutlinePage outline = null;

    // FIXME MH: yeh well - data structure time here - I couldn't find
    //           anything obvious in emf, maybe something like a feature
    //           map could be used. whatever the case, HashMap is definately
    //           not the one. EObject uses object identity only, requires
    //           org.eclipse.emf.ecore.util.EcoreUtil for object equality +
    //           hash. but..... i just don't have the time at the moment
    //           so sirNastyHackALot was born for the time being.
    private Map startCharMap = new HashMap();
    private Map endCharMap = new HashMap();

    private IndexedEObjectLookup termAreaLookup = new IndexedEObjectLookup();

    /**
     *
     */
    public TefkatModelEditor() {
        super();
    }

    private AnnotatingStatsListener annotatingStatsListener;

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        if (annotatingStatsListener != null) {
            TefkatPlugin.getPlugin().getTefkat().removeTefkatListener(annotatingStatsListener);
            annotatingStatsListener = null;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    public void createPages() {
        createEditorPage();
        createXMIPage();
        createStratificationPage();
//        IActionBars actionBars = getEditorSite().getActionBars();
//        System.out.println(actionBars.getGlobalActionHandler(ActionFactory.PRINT.getId()));
//        System.out.println(ActionFactory.PRINT);
//        actionBars.setGlobalActionHandler(ActionFactory.PRINT.getId(), ActionFactory.PRINT.create(getEditorSite().getWorkbenchWindow()));
    }

    private void createEditorPage() {
        textEditor = new TefkatTextEditor();
        textEditor.addPropertyListener(new IPropertyListener() {
            public void propertyChanged(final Object source, final int propId) {
                if (IWorkbenchPartConstants.PROP_DIRTY == propId && !textEditor.isDirty()) {
                    runner.requestParse();
                }
            }
        });
        try {
            EDITOR_PAGE = addPage(textEditor, getEditorInput());
            setPartName(getEditorInput().getName());
            setPageText(EDITOR_PAGE, "Transformation");

        } catch (PartInitException e) {
            ErrorDialog.openError(
                           getSite().getShell(),
                           "Error creating nested text editor",
                           null,
                           e.getStatus());
        }

        Tefkat engine = TefkatPlugin.getPlugin().getTefkat();
        annotatingStatsListener = new AnnotatingStatsListener(textEditor, ((IFileEditorInput) getEditorInput()).getFile().getLocationURI());
        engine.addTefkatListener(annotatingStatsListener);
        engine.setPrintingStats(true);

    }

    private void createXMIPage() {
//        EcoreEditor ecoreEditor = new org.eclipse.emf.ecore.presentation.EcoreEditor();
//        ecoreEditor.createPartControl(getContainer());
        Composite composite = new Composite(getContainer(), SWT.NONE);
        FillLayout layout = new FillLayout();
        composite.setLayout(layout);
        xmiText = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
        xmiText.setEditable(false);

        XMI_PAGE = addPage(composite);
        setPageText(XMI_PAGE, "XMI Preview");
    }

    private void createStratificationPage() {
        Composite composite = new Composite(getContainer(), SWT.NONE);
        FillLayout layout = new FillLayout();
        composite.setLayout(layout);
        stratificationText = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
        stratificationText.setEditable(false);

        STRATIFICATION_PAGE = addPage(composite);
        setPageText(STRATIFICATION_PAGE, "Stratification");
    }

    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);
        if (newPageIndex == XMI_PAGE) {
            runner.requestParse();
        } else if (newPageIndex == STRATIFICATION_PAGE) {
            runner.requestParse();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        getEditor(EDITOR_PAGE).doSave(monitor);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(EDITOR_PAGE, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#gotoMarker(org.eclipse.core.resources.IMarker)
     */
    public void gotoMarker(IMarker marker) {
        setActivePage(EDITOR_PAGE);
        IGotoMarker gotoMarker = (IGotoMarker) getEditor(0).getAdapter(IGotoMarker.class);
        if (gotoMarker != null) {
            gotoMarker.gotoMarker(marker);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    public final class TefkatTextEditor extends TextEditor {

        public TefkatTextEditor() {
            super();
            setSourceViewerConfiguration(new TefkatModelSourceViewerConfiguration());

        }

        public AnnotatedDocument createAnnotatedDocument() {
            ITextViewer textViewer = getSourceViewer();
            return new AnnotatedDocument(textViewer.getDocument(), getDocumentProvider(), getEditorInput(), termAreaLookup);
        }

         int idx = 0;
         public void addAnnotation(Resource r, Term t, TermStat stats) {
            ITextViewer textViewer = getSourceViewer();
            if (textViewer == null) {
                System.err.println("could not find text viewer");
                return;
            }

            IDocument document= textViewer.getDocument();

            if (document == null) {
                System.err.println("could not find document");
                return;
            }

            IDocumentProvider documentProvider= getDocumentProvider();
            if (documentProvider == null) {
                System.err.println("could not find documentProvider");
                return;
            }

            IAnnotationModel annotationModel= documentProvider.getAnnotationModel(getEditorInput());
            if (annotationModel == null) {
                System.err.println("could not find annotationModel");
                return;
            }

            EObject newT = sirNastyHackALot(t);
            Integer start = getStartChar(newT);
            Integer end = getEndChar(newT);
            if (start == null) throw new NullPointerException(verboseSirNastyHackALot(t));
            if (end == null) throw new NullPointerException();
            int offset = start.intValue();
            int length = end.intValue() - offset;

            String type;
            if (stats.success()) {
                type = "tefkat.plugin.stats.always";
            } else if (stats.failure()) {
                type = "tefkat.plugin.stats.never";
            } else {
                type = "tefkat.plugin.stats.sometimes";
            }
            System.err.println(t + "  %" + stats.percent());
            System.err.println(stats.success);
            System.err.println(stats.failure);
            System.err.println(stats.nonleaf);
            createAnnotation(annotationModel, type, stats.percent(), offset, length);

        }

         private String verboseSirNastyHackALot(Term in) {
             System.err.println("Matching: " + in);
             for (EObject out : (Set<EObject>) startCharMap.keySet()) {
                 System.err.println("  against: " + out);
                 if (out.toString().equals(in.toString())) {
                     return out.toString();
                 }
             }
             return in.toString();
        }

        private EObject sirNastyHackALot(EObject in) {
             for (EObject out : (Set<EObject>) startCharMap.keySet()) {
                 if (EcoreUtil.equals(out, in)) {
                     System.err.println("**EQUALS");
                     return out;
                 }
                 if (out.toString().equals(in.toString())) {
                     System.err.println("**TOSTRING");
                     return out;
                 }
                 if (out.toString().equals("not [" + in.toString() + "]")) {
                     System.err.println("**TOSTRING+NOT");
                     return out;
                 }
             }
             return in;
         }

        private void createAnnotation(IAnnotationModel annotationModel, String type, String msg, int offset, int length) {
            Annotation annotation = new Annotation(type, false, msg);
            annotation.setText(msg);
            Position position = new Position(offset, length);
            annotationModel.addAnnotation(annotation, position);
        }

        protected ISourceViewer createSourceViewer(Composite parent,
                IVerticalRuler ruler, int styles) {
            // TODO Auto-generated method stub
            return super.createSourceViewer(parent, ruler, styles);
        }

        /* (non-Javadoc)
         * @see org.eclipse.ui.IWorkbenchPart#dispose()
         */
        public void dispose() {
            // TODO Auto-generated method stub
            super.dispose();
        }

        /* (non-Javadoc)
         * @see org.eclipse.ui.texteditor.AbstractTextEditor#selectAndReveal(int, int, int, int)
         */
        protected void selectAndReveal(int selectionStart, int selectionLength,
                int revealStart, int revealLength) {
            pageChange(EDITOR_PAGE);
            super.selectAndReveal(selectionStart, selectionLength, revealStart,
                    revealLength);
        }

    }

    class ParserThread extends Thread {
        private volatile boolean parseRequested = false;

        synchronized final public void requestParse() {
            if (!parseRequested && !isAlive()) {
                start();
            }
            parseRequested = true;
            notify();
        }

        final public void run() {
            boolean doWork = false;

            while (!getContainer().isDisposed()) {
                synchronized (this) {
                    if (parseRequested) {
                        doWork = true;
                        parseRequested = false;
                    } else {
                        doWork = false;
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                if (doWork) {
                    try {
                        doParse();
                        try {
                            Thread.sleep(500);  // throttle the parsing
                        } catch (InterruptedException e) {
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }

        private void doParse() {
            if (null == textEditor.getEditorInput()) {
                // nothing to do
                return;
            }

//            TefkatPlugin.getPlugin().clearResourceSet();
//            ResourceSet resourceSet = TefkatPlugin.getPlugin().getResourceSet();
            ResourceSet resourceSet = new ResourceSetImpl();

            // Need to use a new ExtendedMetaData instance to avoid cached ePackage instances.
            //
            SERIALIZATION_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData(resourceSet.getPackageRegistry()));
            resourceSet.getLoadOptions().putAll(SERIALIZATION_OPTIONS);

            final IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
            try {
                resource.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);
                resource.deleteMarkers(PARSE_ERROR, true, IResource.DEPTH_INFINITE);
            } catch (CoreException e1) {
                // TODO Log this
            }

            String editorText = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput()).get();
            Reader in = new StringReader(editorText);
            final TefkatLexer lexer = new TefkatLexer(in);
            lexer.setTokenObjectClass("antlr.CommonHiddenStreamToken");
            // create the filter
            TokenStreamHiddenTokenFilter filter = new TokenStreamHiddenTokenFilter(lexer);
            // hide not discard
            filter.hide(TefkatParser.COMMENT);
            filter.hide(TefkatParser.WS);

            TefkatParser parser = new TefkatParser(filter);

            final MessageAdapter messageListener = new MessageAdapter() {
                            public void reportError(MessageEvent e) {
                                if (e instanceof TefkatMessageEvent) {
                                    final TefkatMessageEvent tme = (TefkatMessageEvent) e;
                                    final Object src = tme.getSource();
                                    createErrorMarker(resource, getMessage(tme.getLine(), e.getText()), tme.getLocation(), tme.getLine(), tme.getCharStart(), tme.getCharEnd());
                                } else {
                                    createErrorMarker(resource, e.getText(), lexer.getLine());
                                }
                            }

                            public void reportWarning(MessageEvent e) {
                                if (e instanceof TefkatMessageEvent) {
                                    final TefkatMessageEvent tme = (TefkatMessageEvent) e;
                                    createWarningMarker(resource, getMessage(tme.getLine(), e.getText()), tme.getLine(), tme.getCharStart(), tme.getCharEnd());
                                } else {
                                    createWarningMarker(resource, e.getText(), lexer.getLine());
                                }
                            }

                            private String getMessage(int line, String msg) {
                                Object[] args = {line, msg};
                                return new Formatter().format("%4d: %s", args).toString();
                            }
                        };
            parser.addMessageListener(messageListener);

            // store map of char position to parse terms
            startCharMap.clear();
            endCharMap.clear();
            termAreaLookup.clear();
            parser.addParserListener(new ParserListener() {
                public void matched(ParserEvent e) {
                    startCharMap.put(e.getObj(), new Integer(e.getStartChar()));
                    endCharMap.put(e.getObj(), new Integer(e.getEndChar()));
                    termAreaLookup.add((EObject) e.getObj(), e.getStartChar(), e.getEndChar());
                }
            });

            URI uri = URI.createPlatformResourceURI(resource.getFullPath().toString());
            final Resource res = resourceSet.createResource(uri);

            final OutputStream out = new ByteArrayOutputStream();
            final StringBuffer sb = new StringBuffer();
            try {
                parser.setResource(res);
                final Transformation transformation = parser.transformation();
                res.save(out, SERIALIZATION_OPTIONS);

//                ModelUtils.resolveTrackingClassNames(transformation, parser.trackingMap);
                printStrata(sb, transformation.getStrata());

            } catch (final RecognitionException e) {
                createErrorMarker(resource, e.toString(), e.getLine());
            } catch (final ANTLRException e) {
                createErrorMarker(resource, e.toString(), lexer.getLine());
            } catch (final StratificationException e) {
                createErrorMarker(resource, e.toString(), lexer.getLine());
                sb.append(e.getMessage()).append("\n");
                printStrata(sb, e.getStrata());
            } catch (final Exception e) {
                createErrorMarker(resource, e.getMessage(), "line " + lexer.getLine(), lexer.getLine(), 1, 1);
            } finally {
                parser.removeMessageListener(messageListener);
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        if (getContainer().isDisposed()) {
                            return;
                        }
                        if (null != outline) {
                            outline.setResource(res);
                        }
                        xmiText.setText(out.toString());
                        stratificationText.setText(sb.toString());
                    }
                });
            }
        }

        private void printStrata(final StringBuffer sb, final List[] strata) {
            for (int i = 0; i < strata.length; i++) {
                if (strata[i].size() > 0) {
                    sb.append("Stratum: ").append(i).append("\n");
                    for (Iterator itr = strata[i].iterator(); itr.hasNext(); ) {
                        Object scope = itr.next();
                        sb.append("\t").append(scope).append("\n");
                    }
                }
            }
        }

    }

    private void createErrorMarker(final IResource resource, final String message, final int line) {
        createErrorMarker(resource, message, "line " + line, line, -1, -1);
    }

    private void createErrorMarker(final IResource resource, final String message, final String location, final int line, final int start, final int end) {
        System.err.println(message);
    Display.getDefault().asyncExec(new Runnable() {
        public void run() {
        try {
            Map map = new HashMap(6);
            if (start >= 0) {
                map.put(IMarker.CHAR_START, new Integer(start));
            }
            if (end >= 0) {
                map.put(IMarker.CHAR_END, new Integer(end));
            }
            if (line > 0) {
                map.put(IMarker.LINE_NUMBER, new Integer(line));
            }
            map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
            map.put(IMarker.MESSAGE, message);
            map.put(IMarker.LOCATION, location);
            createMarker(resource, map);
        } catch (CoreException e) {
            // TODO Log later
            e.printStackTrace();
        }
        }
    });
    }

    private void createWarningMarker(final IResource resource, final String message, final int line) {
        createWarningMarker(resource, message, line, -1, -1);
    }

    private void createWarningMarker(final IResource resource, final String message, final int line, final int start, final int end) {
    Display.getDefault().asyncExec(new Runnable() {
        public void run() {
        try {
            Map map = new HashMap(6);
                    if (start >= 0) {
                        map.put(IMarker.CHAR_START, new Integer(start));
                    }
                    if (end >= 0) {
                        map.put(IMarker.CHAR_END, new Integer(end));
                    }
                    if (line > 0) {
                        map.put(IMarker.LINE_NUMBER, new Integer(line));
                    }
            map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
            map.put(IMarker.MESSAGE, message);
                    map.put(IMarker.LOCATION, message);
            createMarker(resource, map);
        } catch (CoreException e) {
            // TODO Log later
            e.printStackTrace();
        }
        }
    });
    }

    private void createMarker(final IResource resource, final Map map)
        throws CoreException {
        ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
            public void run(IProgressMonitor monitor) throws CoreException {
                IMarker marker = resource.createMarker(PARSE_ERROR);
                marker.setAttributes(map);
            }
        }, null);
    }

    public Object getAdapter(Class adapter) {

        if (adapter.equals(IContentOutlinePage.class)) {
            if (null == outline) {
                outline = new TefkatModelOutlinePage();
                outline.addSelectionChangedListener(new ISelectionChangedListener() {

                    public void selectionChanged(SelectionChangedEvent event) {
                        ISelection selection = event.getSelection();

                        int s = Integer.MAX_VALUE;
                        int e = Integer.MIN_VALUE;
                        for (final Iterator itr = ((IStructuredSelection) selection).iterator(); itr.hasNext(); ) {
                            final Object selectionElement = itr.next();

                            if (selectionElement instanceof EObject) {
                                EObject obj = (EObject) selectionElement;
                                Integer startChar = getStartChar(obj);
                                Integer endChar = getEndChar(obj);
                                if (null != startChar && startChar.intValue() >= 0 && null != endChar && endChar.intValue() >= 0) {
                                    s = Math.min(s, startChar.intValue());
                                    e = Math.max(e, endChar.intValue());
                                }
                            }
                        }
                        if (s <= e) {
                            textEditor.setHighlightRange(s, e - s, true);
                            textEditor.selectAndReveal(s, (e - s));
                        } else {
                            textEditor.resetHighlightRange();
                        }
                    }
                });
            }
            return outline;
        } else if (adapter.equals(ITextEditor.class)) {
            return textEditor;
        }

        return super.getAdapter(adapter);
    }

    public Integer getStartChar(EObject obj) {
        while (obj != null) {
            Integer pos = (Integer) startCharMap.get(obj);
            if (pos != null) {
                return pos;
            }
            obj = obj.eContainer();
        }
        return null;
    }

    public Integer getEndChar(EObject obj) {
        while (obj != null) {
            Integer pos = (Integer) endCharMap.get(obj);
            if (pos != null) {
                return pos;
            }
            obj = obj.eContainer();
        }
        return null;
    }

}
