/*
 * Created on 8/03/2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.dstc.tefkat.model.parser;

// import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;

// import antlr.TokenStreamHiddenTokenFilter;

/**
 * @author lawley
 *
 */
public class TefkatResourceFactory implements Resource.Factory {
    private static URIConverter uriConverter = new URIConverterImpl();

    private List parserListeners = new ArrayList();

    public TefkatResourceFactory() {
    }

    public void addParserListener(ParserListener listener) {
        parserListeners.add(listener);
    }

    public void removeParserListener(ParserListener listener) {
        parserListeners.remove(listener);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
     */
    public Resource createResource(URI uri) {
    	return new TefkatResourceImpl(uri, parserListeners);
    }

}
