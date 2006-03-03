/*
 * Created on 28/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.model.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamHiddenTokenFilter;
import antlr.debug.MessageAdapter;
import antlr.debug.MessageEvent;

/**
 * @author lawley
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
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
                    System.err.println("parser error: " + e);
                }

                public void reportWarning(MessageEvent e) {
                    Resource.Diagnostic diag = new DiagnosticImpl((TefkatMessageEvent) e);
                    TefkatResourceImpl.this.getWarnings().add(diag);
                    System.err.println("parser warning: " + e);
                }
                
            });
            if (null != parserListeners) {
                for (int i = 0; i < parserListeners.size(); i++) {
                    parser.addParserListener((ParserListener) parserListeners.get(i));
                }
            }
            parser.transformation(this);
        } catch (RecognitionException e) {
            throw new IOException(e.toString());
        } catch (TokenStreamException e) {
            throw new IOException(e.toString());
        }
    }

    static class DiagnosticImpl extends Exception implements Resource.Diagnostic {
        
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