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

package tefkat.model.parser.tests;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import tefkat.model.TRule;
import tefkat.model.TefkatFactory;
import tefkat.model.Transformation;

import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageEvent;
import antlr.debug.MessageListener;
import antlr.debug.TraceEvent;

import tefkat.model.parser.TefkatLexer;
import tefkat.model.parser.TefkatParser;

import junit.framework.TestCase;

/**
 * @author lawley
 *
 */
public abstract class ParserTestCase extends TestCase {

    public ParserTestCase(String name) {
        super(name);
    }

    protected Transformation t;
    protected TRule tr;
    
    boolean ignoreError;

    public void setUp() {
        System.err.println("setUp " + getName());
        ignoreError = false;
        ResourceSet rs = new ResourceSetImpl();
        Map map = rs.getResourceFactoryRegistry()
                    .getExtensionToFactoryMap();
        Resource.Factory xmiFactory = new XMIResourceFactoryImpl();
        map.put("ecore", xmiFactory);
        map.put("xmi", xmiFactory);
        Resource res = rs.createResource(URI.createURI("tmp.xmi"));
        t = TefkatFactory.eINSTANCE.createTransformation();
        t.setName("defaultTransformation");
        t.getVars().add(TefkatFactory.eINSTANCE.createVar());
        t.getVars().add(TefkatFactory.eINSTANCE.createVar());
        tr = TefkatFactory.eINSTANCE.createTRule();
        tr.setName("defaultRule");
        tr.setTransformation(t);
        res.getContents().add(t);
    }

    protected TefkatParser setupParser(String input) {
        Reader reader = new StringReader(input);
        TefkatLexer lexer = new TefkatLexer(reader);
        lexer.setTokenObjectClass("antlr.CommonHiddenStreamToken");
        TokenStreamHiddenTokenFilter filter = new TokenStreamHiddenTokenFilter(lexer);
        filter.hide(TefkatParser.WS);
        TefkatParser parser = new TefkatParser(filter);
        parser.setResource(t.eResource());
        parser.addMessageListener(new MessageListener() {

            public void reportError(MessageEvent event) {
                if (!ignoreError) {
                    assertTrue(event.toString(), false);
                }
            }

            public void reportWarning(MessageEvent arg0) {
            }

            public void doneParsing(TraceEvent arg0) {
            }

            public void refresh() {
            }
            
        });
        return parser;
    }

}
