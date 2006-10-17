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
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.xsd.util.XSDResourceImpl;

import tefkat.model.internal.ModelUtils;
import tefkat.model.parser.ParserEvent;
import tefkat.model.parser.ParserListener;

import antlr.ANTLRException;
import antlr.RecognitionException;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageEvent;
import antlr.debug.MessageAdapter;

import tefkat.model.parser.TefkatLexer;
import tefkat.model.parser.TefkatMessageEvent;
import tefkat.model.parser.TefkatParser;

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
    
    private StyledText text;
    private TextEditor textEditor;
    
    private TefkatModelOutlinePage outline = null;

    private Map startCharMap = new HashMap();
    private Map endCharMap = new HashMap();

    /**
     * 
     */
    public TefkatModelEditor() {
        super();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    protected void createPages() {
        createPage0();
        createPage1();
//        IActionBars actionBars = getEditorSite().getActionBars();
//        System.out.println(actionBars.getGlobalActionHandler(ActionFactory.PRINT.getId()));
//        System.out.println(ActionFactory.PRINT);
//        actionBars.setGlobalActionHandler(ActionFactory.PRINT.getId(), ActionFactory.PRINT.create(getEditorSite().getWorkbenchWindow()));
    }

    private void createPage0() {
        textEditor = new TefkatTextEditor();
        textEditor.addPropertyListener(new IPropertyListener() {
            public void propertyChanged(final Object source, final int propId) {
                if (IWorkbenchPartConstants.PROP_DIRTY == propId && !textEditor.isDirty()) {
                    runner.requestParse();
                }
            }
        });

        try {
            int index = addPage(textEditor, getEditorInput());
            setPartName(getEditorInput().getName());
            setPageText(index, "Transformation");
        } catch (PartInitException e) {
            ErrorDialog.openError(
                           getSite().getShell(),
                           "Error creating nested text editor",
                           null,
                           e.getStatus());
        }
    }

    private void createPage1() {
        Composite composite = new Composite(getContainer(), SWT.NONE);
        FillLayout layout = new FillLayout();
        composite.setLayout(layout);
        text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
        text.setEditable(false);
        
        int index = addPage(composite);
        setPageText(index, "XMI Preview");
        runner.requestParse();
    }

    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);
        if (newPageIndex == 1) {
            runner.requestParse();
        }
    }
        
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#gotoMarker(org.eclipse.core.resources.IMarker)
     */
    public void gotoMarker(IMarker marker) {
        setActivePage(0);
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

    private final class TefkatTextEditor extends TextEditor {

        public TefkatTextEditor() {
            super();
            setSourceViewerConfiguration(new TefkatModelSourceViewerConfiguration());
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
            pageChange(0);
            super.selectAndReveal(selectionStart, selectionLength, revealStart,
                    revealLength);
        }

    }
    
    class ParserThread extends Thread {
        private boolean parseRequested = false;
        
        synchronized final public void requestParse() {
            parseRequested = true;
            if (!isAlive()) {
                start();
            }
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
                resource.deleteMarkers(PARSE_ERROR, false, IResource.DEPTH_INFINITE);
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

            parser.addMessageListener(new MessageAdapter() {
                public void reportError(MessageEvent e) {
                    if (e instanceof TefkatMessageEvent) {
                        createErrorMarker(resource, e.getText(), ((TefkatMessageEvent) e).getLine());
                    } else {
                        createErrorMarker(resource, e.getText(), lexer.getLine());
                    }
                }

                public void reportWarning(MessageEvent e) {
                    if (e instanceof TefkatMessageEvent) {
                        createWarningMarker(resource, e.getText(), ((TefkatMessageEvent) e).getLine());
                    } else {
                        createWarningMarker(resource, e.getText(), lexer.getLine());
                    }
                }
            });
            
            // store map of char position to parse terms
            startCharMap.clear();
            endCharMap.clear();
            parser.addParserListener(new ParserListener() {
            public void matched(ParserEvent e) {
                    startCharMap.put(e.getObj(), new Integer(e.getStartChar()));
                    endCharMap.put(e.getObj(), new Integer(e.getEndChar()));
               }
            });

            URI uri = URI.createPlatformResourceURI(resource.getFullPath().toString());
            final Resource res = resourceSet.createResource(uri);
        
            try {
                parser.setResource(res);
                final Transformation transformation = parser.transformation();
                res.save(out, SERIALIZATION_OPTIONS);

                ModelUtils.resolveTrackingClassNames(transformation, parser.trackingMap);
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
                createErrorMarker(resource, e.toString(), lexer.getLine());
            } finally {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        if (getContainer().isDisposed()) {
                            return;
                        }
                        if (null != outline) {
                            outline.setResource(res);
                        }
                        text.setText(xmi);
                    }
                });
            } catch (final RecognitionException e) {
                createErrorMarker(resource, e.toString(), e.getLine());
            } catch (final ANTLRException e) {
                createErrorMarker(resource, e.toString(), lexer.getLine());
            } catch (final Exception e) {
                e.printStackTrace();
                createErrorMarker(resource, e.toString(), lexer.getLine());
            }
        }

    }

    private void createErrorMarker(final IResource resource, final String message, final int line) {
    Display.getDefault().asyncExec(new Runnable() {
        public void run() {
        text.setText(message);
        try {
            Map map = new HashMap(3);
            map.put(IMarker.LINE_NUMBER, new Integer(line));
            map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
            map.put(IMarker.MESSAGE, message);
            createMarker(resource, map);
        } catch (CoreException e) {
            // TODO Log later
            e.printStackTrace();
        }
        }
    });
    }

    private void createWarningMarker(final IResource resource, final String message, final int line) {
	Display.getDefault().asyncExec(new Runnable() {
	    public void run() {
		text.setText(message);
		try {
		    Map map = new HashMap(3);
		    map.put(IMarker.LINE_NUMBER, new Integer(line));
		    map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
		    map.put(IMarker.MESSAGE, message);
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
                        if (!selection.isEmpty()) {
                            EObject obj = (EObject) ((IStructuredSelection) selection).getFirstElement();
                            Integer startChar = getStartChar(obj);
                            Integer endChar = getEndChar(obj);
                            if (null != startChar && null != endChar) {
                                int start = startChar.intValue();
                                int length = endChar.intValue() - start;
                                textEditor.setHighlightRange(start, length, true);
                                return;
                            }
                        }

                        textEditor.resetHighlightRange();
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
