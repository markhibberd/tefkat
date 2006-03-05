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
 */

package com.dstc.tefkat.model.parser.tests;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import antlr.ANTLRException;
import antlr.RecognitionException;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageAdapter;
import antlr.debug.MessageEvent;

import com.dstc.tefkat.model.parser.ParserEvent;
import com.dstc.tefkat.model.parser.ParserListener;
import com.dstc.tefkat.model.parser.TefkatLexer;
import com.dstc.tefkat.model.parser.TefkatParser;

import junit.framework.TestCase;

/**
 * @author lawley
 *
 */
public class ParserMemoryTest extends TestCase {

    public ParserMemoryTest(String name) {
        super(name);
    }
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        URIConverter.URI_MAP.put(URI.createURI("http://www.dstc.com/bshct/openehr/rm/v0.0"), URI.createURI("file:///tmp/HealthRecords/com.dstc.bshct.openehr.rm/models/com.dstc.bshct.openehr.rm.v0.ecore"));
        URIConverter.URI_MAP.put(URI.createURI("http://www.dstc.com/bshct/openehr/adl/v1.0"), URI.createURI("file:///tmp/HealthRecords/com.dstc.bshct.openehr.adl/src/model/com.dstc.bshct.openehr.adl.v1.ecore"));
        URIConverter.URI_MAP.put(URI.createURI("http://www.w3.org/2002/xforms"), URI.createURI("file:///tmp/HealthRecords/com.dstc.xforms/src/model/xforms.ecore"));
    }
    
    public void testMemory() throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("/tmp/ADL2XFORM.qvt"));
        StringBuffer sb = new StringBuffer();
        String line = r.readLine();
        while (null != line) {
            sb.append(line).append("\n");
            line = r.readLine();
        }
        String str = sb.toString();
        // str = "TRANSFORMATION t: x -> y  RULE r1 FORALL Foo f MAKE Bar b; ";

        for (int i = 0; i < 100; i++) {
//            System.err.print(".");
          System.out.println("mem: " + Runtime.getRuntime().freeMemory());
          long startTime = System.currentTimeMillis();
          for (int j = 0; j < 20; j++) {
              doParse(str);
//              doParse("/tmp/ADL2XFORM.qvt");
          }
          long endTime = System.currentTimeMillis();
          System.out.println("time: " + (endTime - startTime) / 1000.0 + "s");
          Runtime.getRuntime().gc();
        }
        assertTrue(true);
    }

    private static Map SERIALIZATION_OPTIONS;

    static {
        SERIALIZATION_OPTIONS = new HashMap();
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
        SERIALIZATION_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData());
    }

    ResourceSet resourceSet = null;
    Map startCharMap = new HashMap();
    Map endCharMap = new HashMap();
    
    private void doParse(String string) throws FileNotFoundException {
        
        // resourceSet = null;
        
        if (null == resourceSet) {
            resourceSet = new ResourceSetImpl();
            Map map = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
            Resource.Factory xmiFactory = new XMIResourceFactoryImpl();
            map.put("*", xmiFactory);
//            map.put("xmi", xmiFactory);
        } else {
            // Clear out old resources including metamodels so that a
            // reload is forced in case they've changed on disk.
            // TODO - check that this really works - maybe we should just grab a new resourceset
            for (Iterator itr = resourceSet.getResources().iterator(); itr.hasNext();) {
                Resource res = (Resource) itr.next();
                res.getContents().clear();
            }
            for (Iterator itr = resourceSet.getResources().iterator(); itr.hasNext();) {
                Resource res = (Resource) itr.next();
                res.unload();
            }
        }
        
//        final IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
//        try {
//            resource.deleteMarkers(PARSE_ERROR, false, IResource.DEPTH_INFINITE);
//        } catch (CoreException e1) {
//            // TODO Log this
//        }

//        String editorText = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput()).get();
        Reader in = new StringReader(string);
        // Reader in = new FileReader(string);
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
                //System.out.println(e);
//                if (e instanceof TefkatMessageEvent) {
//                    createErrorMarker(resource, e.getText(), ((TefkatMessageEvent) e).getLine());
//                } else {
//                    createErrorMarker(resource, e.getText(), lexer.getLine());
//                }
            }

            public void reportWarning(MessageEvent e) {
//                if (e instanceof TefkatMessageEvent) {
//                    createWarningMarker(resource, e.getText(), ((TefkatMessageEvent) e).getLine());
//                } else {
//                    createWarningMarker(resource, e.getText(), lexer.getLine());
//                }
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

        URI uri = URI.createURI("dummy.xmi");
        assertNotNull("Couldn't create URI", uri);
        final Resource res = resourceSet.createResource(uri);
        assertNotNull("Couldn't create Resource", res);
    
        try {
            parser.transformation(res);
            final OutputStream out = new ByteArrayOutputStream();
            res.save(out, SERIALIZATION_OPTIONS);
            
            // leak.add(res);
            
//            Display.getDefault ().asyncExec (new Runnable () {
//                public void run () {
//                    if (getContainer().isDisposed()) {
//                        return;
//                    }
//                    if (null != outline) {
//                        outline.setResource(res);
//                    }
//                    text.setText(out.toString());
//                }
//            });
        } catch (final RecognitionException e) {
//            createErrorMarker(resource, e.toString(), e.getLine());
        } catch (final ANTLRException e) {
//            createErrorMarker(resource, e.toString(), lexer.getLine());
        } catch (final Exception e) {
            e.printStackTrace();
//            createErrorMarker(resource, e.toString(), lexer.getLine());
        }
//        System.out.println("mem: " + Runtime.getRuntime().freeMemory());
    }
    
    List leak = new ArrayList();

}
