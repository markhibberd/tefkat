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
public class IdentifierStmtTest extends ParserTestCase {

    /**
     * Constructor for ImportStmtTest.
     * @param name
     */
    public IdentifierStmtTest(String name) {
        super(name);
    }

    public void testValidIdentifier() {
        TefkatParser parser = setupParser("hile");
        try {
            parser.fname();
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
    }
}
