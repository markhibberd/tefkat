/*
 * Created on 17/03/2004
 * @author michael j lawley
 */
package com.dstc.tefkat.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import tefkat.engine.Tefkat;
import tefkat.model.TefkatException;
import tefkat.model.impl.TefkatPackageImpl;
import tefkat.model.parser.TefkatResourceFactory;

/**
 * @author lawley
 *
 * Implements a REST-based interface to Tefkat.
 * 
 */
public class TefkatServlet extends HttpServlet {

    private static String SRC_XMI = "srcXMI";
    private static String SRC_URI = "srcURI";
    private static String SRC_FILE = "srcFile";
    private static String XMORPH_QVT = "xmorphQVT";
    private static String XMORPH_XMI = "xmorphXMI";
    private static String XMORPH_URI = "xmorphURI";
    private static String XMORPH_FILE = "xmorphFile";

    private static final TefkatResourceFactory TEFKAT_RESOURCE_FACTORY = new TefkatResourceFactory();

    private static Map SERIALIZATION_OPTIONS;

    static {
        SERIALIZATION_OPTIONS = new HashMap();
        SERIALIZATION_OPTIONS.put(
            XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
            Boolean.TRUE);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getPathInfo();

        if ("/transform".equals(path)) {
            doTransform(
                req.getParameter(SRC_URI),
                req.getParameter(XMORPH_URI),
                resp);
        } else if ("/parse".equals(path)) {
            doParse(req.getParameter(XMORPH_URI), resp);
        } else if ("/xmorph".equals(path)) {
            doPrint(req.getParameter(XMORPH_URI), resp);
        } else {
            super.doGet(req, resp);
            // resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown path: '" + path + "' for " + req.getRequestURI());
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getPathInfo();

        if ("/transform".equals(path)) {
            handleTransform(req, resp);
        } else if ("/parse".equals(path)) {
            handleParse(req, resp);
        } else if ("/xmorph".equals(path)) {
            handlePrint(req, resp);
        } else {
            super.doPost(req, resp);
        }
    }

    private void handleTransform(
        HttpServletRequest req,
        HttpServletResponse resp)
        throws IOException, ServletException {
        File directory =
            (File) getServletContext().getAttribute(
                "javax.servlet.context.tmpdir");

        String src = req.getParameter(SRC_XMI);
        String srcURI = req.getParameter(SRC_URI);
        if (null != src && src.length() > 0) {
            File srcFile = File.createTempFile("src", ".xmi", directory);
            FileWriter out = new FileWriter(srcFile);
            out.write(src);
            out.close();

            srcURI = srcFile.toURL().toString();
        }

        String xmorphXMI = req.getParameter(XMORPH_XMI);
        String xmorphQVT = req.getParameter(XMORPH_QVT);
        String xmorphURI = req.getParameter(XMORPH_URI);
        if (null != xmorphXMI && xmorphXMI.length() > 0) {
            File xmorphFile = File.createTempFile("xmorph", ".qvt", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphXMI);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }
        if (null != xmorphQVT && xmorphQVT.length() > 0) {
            File xmorphFile = File.createTempFile("xmorph", ".qvt", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphQVT);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }

        if (null == srcURI || srcURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Need one of " + SRC_XMI + " or " + SRC_URI + " in FORM data.");
            return;
        } else if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing "
                    + XMORPH_QVT
                    + " or "
                    + XMORPH_XMI
                    + " or "
                    + XMORPH_URI
                    + " in FORM data");
            return;
        }

        doTransform(srcURI, xmorphURI, resp);
    }

    private void handleParse(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        File directory =
            (File) getServletContext().getAttribute(
                "javax.servlet.context.tmpdir");

        String xmorphXMI = req.getParameter(XMORPH_XMI);
        String xmorphQVT = req.getParameter(XMORPH_QVT);
        String xmorphURI = req.getParameter(XMORPH_URI);
        if (null != xmorphXMI && xmorphXMI.length() > 0) {
            File xmorphFile =
                File.createTempFile("xmorph", ".xmorph", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphXMI);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }
        if (null != xmorphQVT && xmorphQVT.length() > 0) {
            File xmorphFile = File.createTempFile("xmorph", ".qvt", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphQVT);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }

        if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing "
                    + XMORPH_QVT
                    + " or "
                    + XMORPH_XMI
                    + " or "
                    + XMORPH_URI
                    + " in FORM data");
            return;
        }

        doParse(xmorphURI, resp);
    }

    private void handlePrint(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        File directory =
            (File) getServletContext().getAttribute(
                "javax.servlet.context.tmpdir");

        String xmorphXMI = req.getParameter(XMORPH_XMI);
        String xmorphQVT = req.getParameter(XMORPH_QVT);
        String xmorphURI = req.getParameter(XMORPH_URI);
        if (null != xmorphXMI && xmorphXMI.length() > 0) {
            File xmorphFile =
                File.createTempFile("xmorph", ".xmorph", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphXMI);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }
        if (null != xmorphQVT && xmorphQVT.length() > 0) {
            File xmorphFile = File.createTempFile("xmorph", ".qvt", directory);
            FileWriter out = new FileWriter(xmorphFile);
            out.write(xmorphQVT);
            out.close();

            xmorphURI = xmorphFile.toURL().toString();
        }

        if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing "
                    + XMORPH_QVT
                    + " or "
                    + XMORPH_XMI
                    + " or "
                    + XMORPH_URI
                    + " in FORM data");
            return;
        }

        doPrint(xmorphURI, resp);
    }

    private void doTransform(
        String srcURI,
        String xmorphURI,
        HttpServletResponse resp)
        throws IOException, ServletException {

        if (null == srcURI || srcURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing " + SRC_URI + " from URL");
            return;
        } else if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing " + XMORPH_URI + " from URL");
            return;
        }

        Tefkat engine = new Tefkat();
        engine.registerFactory("qvt", TEFKAT_RESOURCE_FACTORY);
        engine.registerFactory("tfk", TEFKAT_RESOURCE_FACTORY);

        ResourceSet rs = engine.getResourceSet();
        Resource xmorph = rs.getResource(URI.createURI(xmorphURI), true);
        Resource src = rs.getResource(URI.createURI(srcURI), true);

        File directory =
            (File) getServletContext().getAttribute(
                "javax.servlet.context.tmpdir");

        // maybe should use actual transformation request URL?
        Resource tgt = rs.createResource(URI.createURI(directory.getAbsolutePath().concat(File.pathSeparator).concat("target.xmi")));

        OutputStream out = resp.getOutputStream();
        try {
	    // Use same extent for target and tracking model instances
            engine.transform(xmorph, src, tgt, tgt);
            resp.setContentType("text/xml");
            tgt.save(out, SERIALIZATION_OPTIONS);
            out.close();
        } catch (TefkatException e) {
            throw new ServletException("Transformation failed.", e);
        }
    }

    private void doParse(String xmorphURI, HttpServletResponse resp)
        throws IOException {

        if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing " + XMORPH_URI + " from URL");
            return;
        }

        Tefkat engine = new Tefkat();
        engine.registerFactory("qvt", TEFKAT_RESOURCE_FACTORY);
        engine.registerFactory("tfk", TEFKAT_RESOURCE_FACTORY);

        ResourceSet rs = engine.getResourceSet();
        Resource xmorph = rs.getResource(URI.createURI(xmorphURI), true);
        resp.setContentType("text/xml");
        OutputStream out = resp.getOutputStream();
        xmorph.save(out, SERIALIZATION_OPTIONS);
        out.close();
    }

    private void doPrint(String xmorphURI, HttpServletResponse resp)
        throws IOException {

        if (null == xmorphURI || xmorphURI.length() == 0) {
            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Missing " + XMORPH_URI + " from URL");
            return;
        }

        ResourceSet resourceSet = new ResourceSetImpl();
        Map map = resourceSet
            .getResourceFactoryRegistry()
            .getExtensionToFactoryMap();
        Resource.Factory xmiFactory = new XMIResourceFactoryImpl();
        map.put("ecore", xmiFactory);
        map.put("xmi", xmiFactory);
        map.put("xmorph", xmiFactory);
        map.put("qvt", TEFKAT_RESOURCE_FACTORY);
        map.put("tfk", TEFKAT_RESOURCE_FACTORY);

        URI uri = URI.createURI(xmorphURI);
        Resource resource = resourceSet.getResource(uri, true);

        resp.setContentType("text/plain");
        try {
            OutputStream out = resp.getOutputStream();
            TefkatPackageImpl.init();
//            TransMofWalker.walk(null, resource, out);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
