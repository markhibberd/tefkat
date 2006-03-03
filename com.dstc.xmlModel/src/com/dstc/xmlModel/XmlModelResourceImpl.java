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
 *  File:     XmlModelResourceImpl.java
 *
 *  History:  Created on 27/04/2005 by lawley
 *
 */
package com.dstc.xmlModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.dstc.xmlModel.Attribute;
import com.dstc.xmlModel.Document;
import com.dstc.xmlModel.Element;
import com.dstc.xmlModel.Namespace;
import com.dstc.xmlModel.XmlModelFactory;

/**
 * 
 *
 * @author lawley
 */
public class XmlModelResourceImpl extends XMLResourceImpl {

    /**
     * 
     */
    public XmlModelResourceImpl() {
        super();
    }

    /**
     * @param uri
     */
    public XmlModelResourceImpl(URI uri) {
        super(uri);
    }

    public void doLoad(InputStream inputStream, Map options) throws IOException {
        try {
            InputSource inputSource = new InputSource(inputStream);
//            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setFeature("http://xml.org/sax/features/namespaces", true);
            reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            reader.setContentHandler(new XmlModelHandler());
            reader.parse(inputSource);
        } catch (SAXException e) {
//            Throwable t = e;
//            while (null != t) {
//                System.out.println("===============================");
//                t.printStackTrace();
//                t = t.getCause();
//            }

//            e.printStackTrace();
            IOException io = new IOException("Couldn't create SAX parser (SAXException).");
            io.initCause(e);
            throw io;
        } catch (FactoryConfigurationError e) {
//            e.printStackTrace();
            IOException io = new IOException("Couldn't create SAX parser (FactoryConfigurationError).");
            io.initCause(e);
            throw io;
        }
    }
    
    class XmlModelHandler extends DefaultHandler {
        
        Stack currentElement = new Stack();
        Map idMap = new HashMap();
        List actions = new ArrayList();
        List namespaces = new ArrayList();
        Document document;
        
        public XmlModelHandler() {
        }

        public void startDocument() throws SAXException {
            super.startDocument();
            document = XmlModelFactory.eINSTANCE.createDocument();
            getContents().add(document);
            
            namespaces.clear();
        }
        
        public void endDocument() throws SAXException {
            super.endDocument();
            
            // post-processing
            for (final Iterator itr = actions.iterator(); itr.hasNext(); ) {
                Runnable runnable = (Runnable) itr.next();
                runnable.run();
            }
            
        }
        
        public void startPrefixMapping(String prefix, String uri)
                throws SAXException {
            
            Namespace ns = XmlModelFactory.eINSTANCE.createNamespace();
            ns.setPrefix(prefix);
            ns.setUri(uri);

            namespaces.add(ns);
        }
        
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
try {
            boolean namespaceAware = "".equals(localName);
            if (namespaceAware) {
                localName = qName;
            }
            Element element = XmlModelFactory.eINSTANCE.createElement();
            element.setName(getSimpleName(localName, qName));
            element.setQName(qName);
            
            if (!currentElement.isEmpty()) {
                Element parent = (Element) currentElement.peek();
                element.setParent(parent);
            } else {
                document.setRoot(element);
            }
            
            element.getNamespaces().addAll(namespaces);
            namespaces.clear();

            int length = attributes.getLength();

            element.setNamespace(resolveNamespace(element, element.getQName()));
            
            List attrs = element.getAttributes();
            for (int i = 0; i < length; i++) {
                final Attribute attr = XmlModelFactory.eINSTANCE.createAttribute();
                attr.setName(getSimpleName(attributes.getLocalName(i), attributes.getQName(i)));
                attr.setQName(attributes.getQName(i));
                attr.setValue(attributes.getValue(i));
                attrs.add(attr);

                attr.setNamespace(resolveNamespace(element, attr.getQName()));
                
                String type = attributes.getType(i);
                if ("ID".equals(type)) {
                    idMap.put(attr.getValue(), element);
                } else if ("IDREF".equals(type)) {
                    actions.add(new Runnable() {
                        public void run() {
                            Element refElement = (Element) idMap.get(attr.getValue());
                            if (null != refElement) {
                                attr.setReference(refElement);
                            } else {
                                System.out.println("Warning: unresolved reference " + attr);
                            }
                        }
                    });
                }
            }

            currentElement.push(element);
} catch (RuntimeException e) {
    System.out.println("Error: " + localName);
    e.printStackTrace();
    throw e;
}
        }

        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            currentElement.pop();
        }
        
        private void xmlns(List list, String qName, String value) {
            String prefix = "";
            if (qName.startsWith("xmlns:")) {
                prefix = qName.substring(6);
            } else if (!qName.equals("xmlns")) {
                return;
            }

            Namespace ns = XmlModelFactory.eINSTANCE.createNamespace();
            ns.setPrefix(prefix);
            ns.setUri(value);
            list.add(ns);
        }
        
        private Namespace resolveNamespace(Element element, String qName) {
            List namespaces = element.getNamespaces();
            for (final Iterator itr = namespaces.iterator(); itr.hasNext(); ) {
                Namespace ns = (Namespace) itr.next();
                String prefix = ns.getPrefix();
                if (qName.startsWith(prefix + ":")) {
                    return ns;
                } else if (prefix.length() == 0 && qName.indexOf(':') < 0) {
                    return ns;
                }
            }
            if (null != element.getParent()) {
//                System.err.println("Look to parent...");
                return resolveNamespace(element.getParent(), qName);
            }
            return null;
        }
        
        private String getSimpleName(String localName, String qName) {
            if ("".equals(localName)) {
                int idx = qName.indexOf(':');
                if (idx >= 0) {
                    return qName.substring(idx + 1);
                } else {
                    return qName;
                }
            } else {
                return localName;
            }
        }
        
    }
    
}
