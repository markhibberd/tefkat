/*
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

package com.dstc.tefkat.model.parser.tests;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.dstc.tefkat.model.ExtentVar;
import com.dstc.tefkat.model.parser.TefkatParser;

/**
 * @author lawley
 *
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
