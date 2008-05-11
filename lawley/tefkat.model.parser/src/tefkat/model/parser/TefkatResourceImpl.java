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
package tefkat.model.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import tefkat.model.MofInstance;
import tefkat.model.NamespaceDeclaration;
import tefkat.model.Transformation;
import tefkat.model.util.TefkatSwitch;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageAdapter;
import antlr.debug.MessageEvent;

/**
 * @author lawley
 * 
 */
public class TefkatResourceImpl extends XMIResourceImpl {

    private List parserListeners;

    /**
     *  
     */
    public TefkatResourceImpl() {
        super();
    }

    /**
     * @param uri
     */
    public TefkatResourceImpl(URI uri, List listeners) {
        super(uri);
        parserListeners = listeners;
    }

    public void doLoad(InputStream inputStream, Map options) throws IOException {
        TefkatLexer lexer = new TefkatLexer(inputStream);
        // use nonstandard token object
        lexer.setTokenObjectClass("antlr.CommonHiddenStreamToken");
        // create the filter
        TokenStreamHiddenTokenFilter filter = new TokenStreamHiddenTokenFilter(lexer);
        // hide not discard
        filter.hide(TefkatParser.COMMENT);
        filter.hide(TefkatParser.WS);

        try {
            TefkatParser parser = new TefkatParser(filter);
            parser.addMessageListener(new MessageAdapter() {
                public void reportError(MessageEvent e) {
                    Resource.Diagnostic diag = new DiagnosticImpl((TefkatMessageEvent) e);
                    TefkatResourceImpl.this.getErrors().add(diag);
//                    System.err.println("parser error: " + e);
                }

                public void reportWarning(MessageEvent e) {
                    Resource.Diagnostic diag = new DiagnosticImpl((TefkatMessageEvent) e);
                    TefkatResourceImpl.this.getWarnings().add(diag);
//                    System.err.println("parser warning: " + e);
                }
                
            });
            if (null != parserListeners) {
                for (int i = 0; i < parserListeners.size(); i++) {
                    parser.addParserListener((ParserListener) parserListeners.get(i));
                }
            }
            parser.setResource(this);
            parser.transformation();
        } catch (RecognitionException e) {
            throw new IOException(e.toString());
        } catch (TokenStreamException e) {
            throw new IOException(e.toString());
        }
    }

    public void doSave(OutputStream outputStream, Map options) throws IOException {
        // TODO Auto-generated method stub
        super.doSave(outputStream, options);
//        new Visitor().visit(new PrintStream(outputStream), this);
    }
  
    // TODO implement me
    static class Visitor extends TefkatSwitch {
        String transformation;
        Set srcExtents = new HashSet();
        Set tgtExtents = new HashSet();
        
        public void visit(PrintStream stream, Resource res) {
            for (Iterator itr = res.getContents().iterator(); itr.hasNext(); ) {
                EObject obj = (EObject) itr.next();
                if (null == doSwitch(obj)) {
                    // do something
                    System.err.println(obj.eClass().getName());
                }
            }
            stream.print(transformation);
            for (Iterator itr = srcExtents.iterator(); itr.hasNext(); ) {
                stream.print(itr.next());
                stream.print(", ");
            }
            stream.print(" -> ");
            for (Iterator itr = srcExtents.iterator(); itr.hasNext(); ) {
                stream.print(itr.next());
                stream.print(", ");
            }
            stream.println();
        }

        public Object doSwitch(EObject theEObject) {
            final Object doSwitch = doSwitch(theEObject.eClass(), theEObject);
            System.err.println(theEObject.eClass().getName() + " = " + doSwitch);
            return doSwitch;
        }

        protected Object doSwitch(EClass theEClass, EObject theEObject) {
            System.err.println(modelPackage);
            System.err.println(theEClass.eContainer());
            if (theEClass.eContainer() == modelPackage) {
                return doSwitch(theEClass.getClassifierID(), theEObject);
            }
            else {
                List eSuperTypes = theEClass.getESuperTypes();
                System.err.println("yy " + (eSuperTypes.isEmpty() ? "true" : eSuperTypes.get(0).toString()));
                return
                    eSuperTypes.isEmpty() ?
                        defaultCase(theEObject) :
                        doSwitch((EClass)eSuperTypes.get(0), theEObject);
            }
        }

        public Object caseTransformation(Transformation object) {
            System.err.println(object);
            transformation = "TRANSFORMATION " + object.getName() + " : ";
            return object;
        }

        public Object caseNamespaceDeclaration(NamespaceDeclaration object) {
            return null;
        }

        public Object caseMofInstance(MofInstance object) {
            if (null != object.getExtent()) {
                final String name = object.getExtent().getName();
                if (object.isTarget()) {
                    tgtExtents.add(name);
                } else {
                    srcExtents.add(name);
                }
            }
            return null;
        }

        public Object defaultCase(EObject object) {
            System.err.println("default: " + object.eClass().getName());
            return null;
        }
        
    }

    static class DiagnosticImpl extends Exception implements Resource.Diagnostic {
        
        /**
         * 
         */
        private static final long serialVersionUID = -3608026548482115750L;

        private TefkatMessageEvent event;

        public DiagnosticImpl(TefkatMessageEvent e) {
            event = e;
        }

        /* (non-Javadoc)
         * @see org.eclipse.emf.ecore.resource.Resource.Diagnostic#getMessage()
         */
        public String getMessage() {
            return event.getText();
        }

        /* (non-Javadoc)
         * @see org.eclipse.emf.ecore.resource.Resource.Diagnostic#getLocation()
         */
        public String getLocation() {
            return event.getLocation();
        }

        /* (non-Javadoc)
         * @see org.eclipse.emf.ecore.resource.Resource.Diagnostic#getLine()
         */
        public int getLine() {
            return event.getLine();
        }

        /* (non-Javadoc)
         * @see org.eclipse.emf.ecore.resource.Resource.Diagnostic#getColumn()
         */
        public int getColumn() {
            return event.getColumn();
        }
        
    }
    
}
