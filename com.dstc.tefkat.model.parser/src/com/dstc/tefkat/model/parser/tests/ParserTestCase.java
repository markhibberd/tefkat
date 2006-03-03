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
 *  Project:  QVTModelParser
 *
 *  File:     ParserTestCase.java
 *
 *  History:  Created on 25/06/2004 by lawley
 *
 */

package com.dstc.tefkat.model.parser.tests;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.Transformation;
import com.dstc.tefkat.model.TefkatFactory;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageEvent;
import antlr.debug.MessageListener;
import antlr.debug.TraceEvent;

import com.dstc.tefkat.model.parser.TefkatLexer;
import com.dstc.tefkat.model.parser.TefkatParser;

import junit.framework.TestCase;

/**
 * @author lawley
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class ParserTestCase extends TestCase {

    public ParserTestCase(String name) {
        super(name);
    }

    protected Transformation t;
    protected TRule tr;
    
    boolean ignoreError;

    public void setUp() {
//        System.err.println("setUp " + getName());
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
        t.getVars().add(TefkatFactory.eINSTANCE.createExtentVar());
        t.getVars().add(TefkatFactory.eINSTANCE.createExtentVar());
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
