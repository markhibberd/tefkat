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

package tefkat.model.parser.tests;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
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
        TefkatParser parser = setupParser("IMPORT http://tefkat.sourceforge.net/tutorial1/relational.ecore");
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
        TefkatParser parser = setupParser("IMPORT http://tefkat.sourceforge.net/tutorial1/DoesNotExist.ecore");
        try {
            parser.importDecl(t);
        } catch (RecognitionException e) {
            assertTrue("Message is " + e.getMessage(), e.getMessage().startsWith("Unable to load model from URI:"));
            return;
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        fail("URI should not have been found");
    }
}
