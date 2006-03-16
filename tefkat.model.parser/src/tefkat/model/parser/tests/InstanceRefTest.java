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
public class InstanceRefTest extends ParserTestCase {

    /**
     * @param name
     */
    public InstanceRefTest(String name) {
        super(name);
    }

    public void testInstanceRef() {
        TefkatParser parser = setupParser("<file:/home/lawley/workspace/QVTModelParser/tests/UMLRelationalTracking.ecore#//TableForClass>");
        try {
            parser.factor(t, null);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        assertTrue(true);
        
    }

    public void testMissingInstanceRef() {
        ignoreError = true;
        TefkatParser parser = setupParser("<file:/home/lawley/workspace/QVTModelParser/tests/UMLRelationalTracking.ecore#//DoesNotExist>");
        try {
            parser.factor(t, null);
        } catch (RecognitionException e) {
            assertTrue(e.getMessage(), e.getMessage().startsWith("Could not resolve instance reference: "));
            return;
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        fail("Instance reference should not have been resolved.");
    }

    public void testBadSyntaxInstanceRefs() {
        String dev = "file:";
        String path = "/home/lawley/workspace/QVTModelParser/tests/";
        String file = "UMLRelationalTracking.ecore";
        String xpath = "#//TableForClass";
        String[] inputs = {
            dev + " /" + path + file + xpath,
            dev + "/" + path + " " + file + xpath,
            dev + "/" + path + file + " " + xpath,
        };

        for (int i = 0; i < inputs.length; i++) {
            try {
                TefkatParser parser = setupParser(inputs[i]);
                parser.factor(t, null);
            } catch (TokenStreamException e) {
                assertTrue(e.getMessage().startsWith("unexpected char"));
                continue;
            } catch (RecognitionException e) {
                assertTrue(e.getMessage().startsWith("unexpected token"));
                continue;
            }
            fail(inputs[i] + " should be a syntax error");
        }
    }
}
