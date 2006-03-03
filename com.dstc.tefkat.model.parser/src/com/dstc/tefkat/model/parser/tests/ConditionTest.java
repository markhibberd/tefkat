/*
 * 
 * Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2005.
 * Unpublished work.  All Rights Reserved.
 * 
 * The software contained on this media is the property of the
 * DSTC Pty Ltd.  Use of this software is strictly in accordance
 * with the license agreement in the accompanying licence.html
 * file.  If your distribution of this software does not contain
 * a licence.html file then you have no rights to use this
 * software in any manner and should contact DSTC at the address
 * below to determine an appropriate licensing arrangement.
 * 
 *   DSTC Pty Ltd
 *   Level 7, G.P. South
 *   Staff House Road
 *   University of Queensland
 *   Qld, 4072
 *   Australia
 *   Tel: +61 7 3365 4310
 *   Fax: +61 7 3365 4311
 *   Email: enquiries@dstc.edu.au
 * 
 * This software is being provided "AS IS" without warranty of
 * any kind.  In no event shall DSTC Pty Ltd be liable for
 * damage of any kind arising out of or in connection with
 * the use or performance of this software.
 * 
 * Created on 22/03/2005
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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
