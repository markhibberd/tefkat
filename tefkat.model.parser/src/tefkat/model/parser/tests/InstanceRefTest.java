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

import tefkat.model.parser.TefkatLexerTokenTypes;
import tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
 */
public class InstanceRefTest extends ParserTestCase {

    /**
     * @param name
     */
    public InstanceRefTest(String name) {
        super(name);
    }

    public void testInstanceRef() {
        TefkatParser parser = setupParser("<http://tefkat.sourceforge.net/tutorial1/relational.ecore#//Table>");
        try {
            parser.objectlit();
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        assertTrue(true);
        
    }

    public void testMissingInstanceRef() {
        ignoreError = true;
        TefkatParser parser = setupParser("<http://tefkat.sourceforge.net/tutorial1/relational.ecore#//DoesNotExist>");
        try {
            parser.objectlit();
        } catch (RecognitionException e) {
            assertTrue(e.getMessage(), e.getMessage().startsWith("Could not resolve instance reference: "));
            return;
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        fail("Instance reference should not have been resolved.");
    }

    public void testBadSyntaxInstanceRefs() {
        String dev = "http:";
        String path = "/tefkat.sourceforge.net/tutorial1/";
        String file = "relational.ecore";
        String xpath = "#//Table";
        String[] inputs = {
            "<" + dev + " /" + path + file + xpath + ">",
            "<" + dev + "/" + path + " " + file + xpath + ">",
            "<" + dev + "/" + path + file + " " + xpath + ">",
        };

        for (int i = 0; i < inputs.length; i++) {
            try {
                TefkatParser parser = setupParser(inputs[i]);
                parser.objectlit();
            } catch (NullPointerException e) {
                StackTraceElement ste = e.getStackTrace()[0];
                assertTrue("Expected NPE due to missing fragment", "getEObject".equals(ste.getMethodName()));
                continue;
            } catch (RuntimeException e) {
                assertTrue(e.getMessage(), e.getMessage().startsWith("Cannot create a resource for"));
                continue;
            } catch (TokenStreamException e) {
                assertTrue(e.getMessage(), e.getMessage().startsWith("unexpected char"));
                continue;
            } catch (RecognitionException e) {
                boolean result = e.getMessage().startsWith("expecting " + TefkatParser._tokenNames[TefkatLexerTokenTypes.LANGLE]) |
                                 e.getMessage().startsWith("expecting " + TefkatParser._tokenNames[TefkatLexerTokenTypes.URITOK]) |
                                 e.getMessage().startsWith("expecting " + TefkatParser._tokenNames[TefkatLexerTokenTypes.RANGLE]);
                assertTrue(e.getMessage(), result);
                continue;
            }
            fail(inputs[i] + " should be a syntax error");
        }
    }
}
