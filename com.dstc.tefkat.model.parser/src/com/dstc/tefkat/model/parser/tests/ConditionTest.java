/*
 * Copyright (c) 2005- michael lawley and others.
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
package com.dstc.tefkat.model.parser.tests;

import java.util.ArrayList;
import java.util.List;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.dstc.tefkat.model.Condition;
import com.dstc.tefkat.model.Term;
import com.dstc.tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
 */
public class ConditionTest extends ParserTestCase {

    /**
     * @param name
     */
    public ConditionTest(String name) {
        super(name);
    }

    public void testEqual() {
        TefkatParser parser = setupParser("1 = 2;");
        try {
            List l = new ArrayList();
            Term term = parser.relation(tr, l);
            assertTrue(term instanceof Condition);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        
    }

    public void testLessThan() {
        TefkatParser parser = setupParser("1 < 2;");
        try {
            List l = new ArrayList();
            Term term = parser.relation(tr, l);
            assertTrue(term instanceof Condition);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        
    }

    public void testLessThanEqual() {
        TefkatParser parser = setupParser("1 <= 2;");
        try {
            List l = new ArrayList();
            Term term = parser.relation(t, l);
            assertTrue(term instanceof Condition);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        
    }

    public void testGreaterThan() {
        TefkatParser parser = setupParser("1 > 2;");
        try {
            List l = new ArrayList();
            Term term = parser.relation(t, l);
            assertTrue(term instanceof Condition);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        
    }

    public void testGreaterThanEqual() {
        TefkatParser parser = setupParser("1 >= 2;");
        try {
            List l = new ArrayList();
            Term term = parser.relation(t, l);
            assertTrue(term instanceof Condition);
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        
    }

}
