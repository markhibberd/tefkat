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
package com.dstc.tefkat.model.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author lawley
 *
 */
public class TefkatResourceFactory implements Resource.Factory {

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
