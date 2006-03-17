/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 *
 */

package tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author lawley
 * 
 */
public class ExtentUtil {
    public static final int CLASS_LOOKUP = 1;

    public static final int CLASS_NAME_LOOKUP = 2;

    public static final int OBJECT_LOOKUP = 4;

    public static final int FEATURE_LOOKUP = 5;
    
    public static final int RULE_EVAL = 8;
    
    public static final int TERM_ENTER = 16;
    
    public static final int TERM_EXIT = 32;
    
    public static final int TERM_DELAY = 64;

    private ExtentUtil() {
    }
    
    private static List listeners = null;
    private static List getListeners() {
        if (null == listeners) {
            listeners = new ArrayList();
        }
        return listeners;
    }
    
    public static void addExtentListener(ExtentListener listener) {
        getListeners().add(listener);
    }

    public static void highlightEdge(Object src, Object dst, int reason) {
        if (null != listeners) {
            for (Iterator itr = getListeners().iterator(); itr.hasNext(); ) {
                ExtentListener listener = (ExtentListener) itr.next();
                listener.highlightEdge(src, dst, reason);
            }
        }
    }

    public static void highlightNode(Object object, int reason) {
        if (null != listeners) {
            for (Iterator itr = getListeners().iterator(); itr.hasNext(); ) {
                ExtentListener listener = (ExtentListener) itr.next();
                listener.highlightNode(object, reason);
            }
        }
    }

    public static void highlightNodes(Collection objects, int reason) {
        if (null != listeners) {
            for (Iterator itr = getListeners().iterator(); itr.hasNext(); ) {
                ExtentListener listener = (ExtentListener) itr.next();
                listener.highlightNodes(objects, reason);
            }
        }
    }

    public static void highlightNodes(Binding binding, int reason) {
        if (null != listeners) {
            for (Iterator itr = getListeners().iterator(); itr.hasNext(); ) {
                ExtentListener listener = (ExtentListener) itr.next();
                listener.highlightNodes(binding, reason);
            }
        }
    }
}

