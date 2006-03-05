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

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author lawley
 *
 */
public class AllTests {

    public static void main(String[] args) {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.dstc.qvtparser.tests");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(IdentifierStmtTest.class));
        suite.addTest(new TestSuite(ImportStmtTest.class));
        suite.addTest(new TestSuite(InstanceRefTest.class));
        suite.addTest(new TestSuite(ExactMofInstanceTest.class));
        suite.addTest(new TestSuite(ConditionTest.class));
        //$JUnit-END$
        return suite;
    }
}
