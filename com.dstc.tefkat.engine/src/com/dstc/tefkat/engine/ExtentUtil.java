/*
 * Created on 26/08/2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.dstc.tefkat.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author lawley
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
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

