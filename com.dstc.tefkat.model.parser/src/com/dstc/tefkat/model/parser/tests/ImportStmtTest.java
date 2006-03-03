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
 *  File:     ImportStmtTest.java
 *
 *  History:  Created on 15/06/2004 by lawley
 *
 */

package com.dstc.tefkat.model.parser.tests;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.dstc.tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ImportStmtTest extends ParserTestCase {

    /**
     * Constructor for ImportStmtTest.
     * @param name
     */
    public ImportStmtTest(String name) {
        super(name);
    }

    public void testFileImport() {
        TefkatParser parser = setupParser("IMPORT file://home/lawley/workspace/QVTModelParser/tests/UMLRelationalTracking.ecore;");
        try {
            parser.importDecl(t);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        assertTrue(t.getImportedPackages().get(0) != null);
    }

    public void testFileImportFailure() {
        TefkatParser parser = setupParser("IMPORT file://home/lawley/workspace/QVTModelParser/tests/DoesNotExist.ecore;");
        try {
            parser.importDecl(t);
        } catch (RecognitionException e) {
            assertTrue(e.getMessage().startsWith("Unable to load tracking package from URI:"));
            return;
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        fail("URI should not have been found");
    }
}
