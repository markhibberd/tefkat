/*
 * Copyright (c) 2003- michael lawley and others.
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
package tefkat.model.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import antlr.TokenStreamHiddenTokenFilter;
import antlr.RecognitionException;
import antlr.TokenStreamException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tefkat.model.TRule;
import tefkat.model.Transformation;


/**
 * @author lawley
 *
 */
public abstract class Main {

    private static ResourceSet resourceSet = new ResourceSetImpl();

    public static void main(String[] args) {
    	tefkat.model.impl.TefkatPackageImpl.init();
        
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    System.out.println("Loading: " + args[i]);
                    InputStream stream = new FileInputStream(args[i]);
                    parseStream(stream, args[i], true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            parseStream(System.in, "<terminal>", false);
        }
    }

    private static void parseStream(InputStream stream, String name, boolean dumpXMI) {
        TefkatLexer lexer = new TefkatLexer(stream);
        // use nonstandard token object
        lexer.setTokenObjectClass("antlr.CommonHiddenStreamToken");
        // create the filter
        TokenStreamHiddenTokenFilter filter = new TokenStreamHiddenTokenFilter(lexer);
        // hide not discard
        //filter.hide(TefkatParser.COMMENT);
        filter.hide(TefkatParser.WS);
        
        TefkatParser parser = new TefkatParser(filter);
        try {
            URI uri = URI.createURI(name + ".tefkat");
            Resource resource = resourceSet.createResource(uri);
            System.err.println("Enter transformation:");
            parser.setResource(resource);
            Transformation t = parser.transformation();
            System.err.println("    " + t);
            System.err.println("vars");
            printList("\t", t.getVars());
//          System.err.println("tracking");
//          printList("\t",  t.getTracking());
            System.err.println("patterns");
            printList("\t",  t.getPatternDefn());
            System.err.println("rules");
            printList("\t",  t.getTRule());
            System.err.println("OK!");
                    
            if (dumpXMI) {
                dumpXMI(name + ".tefkat", t);
            }
            
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }
    }
    
    private static void printList(String prefix, List list) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Object o = itr.next();
            System.err.println(prefix + o);
            if (o instanceof TRule) {
                TRule tr = (TRule) o;
                printList(prefix + "ex" + prefix, tr.getExtended());
                printList(prefix + "su" + prefix, tr.getSuperseded());
            }
        }
    }
    
    private static void dumpXMI(String url, Transformation t) {
        try {
            System.out.println("Dumping XMI to " + url);
            URI uri = URI.createFileURI(url);
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("*", new org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl());
            // TODO - fix resource management
            Resource resource = resourceSet.createResource(uri);
            resource.getContents().add(t);
            resource.save(Collections.EMPTY_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
