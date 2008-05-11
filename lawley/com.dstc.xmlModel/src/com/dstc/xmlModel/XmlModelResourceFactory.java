/*
 * Copyright (c) 2004- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 */
package com.dstc.xmlModel;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


public class XmlModelResourceFactory implements Resource.Factory {

    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(org.eclipse.emf.common.util.URI)
     */
    public Resource createResource(URI uri) {
        return new XmlModelResourceImpl(uri);
    }

    public static void main(String[] args) {
        String SAX_DRIVER = "org.xml.sax.driver";
        if (System.getProperty(SAX_DRIVER) == null) {
            System.setProperty(SAX_DRIVER, "org.apache.crimson.parser.XMLReaderImpl");
        }
        
        Resource.Factory factory = new XmlModelResourceFactory();
        ResourceSet rs = new ResourceSetImpl();
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", factory);
        
        loadResource(rs, "test.xml");
        System.out.println("---------------------------------------");
        loadResource(rs, "models/XML.ecore");
        System.out.println("---------------------------------------");
        loadResource(rs, "models/XML.genmodel");
    }
    
    private static void loadResource(ResourceSet rs, String uri) {
        Resource res = rs.getResource(URI.createURI(uri), true);
        printList("", res.getContents());
    }

    private static void printList(String prefix, List l) {
        for (Iterator itr = l.iterator(); itr.hasNext(); ) {
            Object o = itr.next();
            System.out.println(prefix + o);
            if (o instanceof EObject) {
                printList("  " + prefix, ((EObject) o).eContents());
            }
        }
    }
    
}
