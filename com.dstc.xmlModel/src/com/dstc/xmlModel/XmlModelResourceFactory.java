/**
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
 *  Project:  com.dstc.xmlModel
 *
 *  File:     XmlModelResourceFactory.java
 *
 *  History:  Created on 27/04/2005 by lawley
 *
 *  @author lawley
 * 
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
