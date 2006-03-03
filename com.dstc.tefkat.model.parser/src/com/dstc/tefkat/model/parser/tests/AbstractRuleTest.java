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
*  File:     AbstractRuleTest.java
*
*  History:  Created on 22/07/2004 by lawley
*
*/
package com.dstc.tefkat.model.parser.tests;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.dstc.tefkat.model.ExtentVar;
import com.dstc.tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AbstractRuleTest extends ParserTestCase {
    /**
     * @param name
     */
    public AbstractRuleTest(String name) {
        super(name);
    }

    public void testAbstractRule() {
        TefkatParser parser = setupParser("ABSTRACT RULE foo ;");
        try {
            parser.trule(t, (ExtentVar) t.getVars().get(0), (ExtentVar) t.getVars().get(1));
        } catch (RecognitionException e) {
            fail(e.toString());
        } catch (TokenStreamException e) {
            fail(e.toString());
        }
        assertTrue(true);
        
    }
}
