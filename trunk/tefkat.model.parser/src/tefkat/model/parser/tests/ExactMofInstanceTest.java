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

import tefkat.model.ExtentVar;
import antlr.RecognitionException;
import antlr.TokenStreamException;

import tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
 */
public class ExactMofInstanceTest extends ParserTestCase {

    /**
     * Constructor for ExactMofInstanceTest.
     * @param name
     */
    public ExactMofInstanceTest(String name) {
        super(name);
    }

    public void testExactKeyword() {
        TefkatParser parser = setupParser("EXACT ClassName verName");
        try {
            parser.range(t, (ExtentVar) t.getVars().get(0), false, null);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
    }

    public void testNoExactKeyword() {
        TefkatParser parser = setupParser("ClassName verName");
        try {
            parser.range(t, (ExtentVar) t.getVars().get(0), false, null);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
    }

}
