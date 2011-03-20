/**
 * 
 */
package tefkat.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.engine.trace.BoolAny;
import tefkat.engine.trace.IntAny;
import tefkat.engine.trace.ObjectAny;
import tefkat.engine.trace.StringAny;
import tefkat.engine.trace.Trace;
import tefkat.engine.trace.TraceFactory;
import tefkat.engine.trace.TracePackage;
import tefkat.model.Extent;
import tefkat.model.TRule;

class Injections {
    
    final private Map injections = new HashMap();
    final private Map traces = new HashMap();
    
    void loadTrace(final Extent extent) {
        final List traceInstances = extent.getObjectsByClass(TracePackage.eINSTANCE.getTrace(), false);
        for (final Iterator itr = traceInstances.iterator(); itr.hasNext(); ) {
            final Trace trace = (Trace) itr.next();

            final List keys = trace.getSources();
            final EObject target = trace.getTarget();
            store(keys, target);
            traces.put(target, keys);
        }
    }
    
    EObject lookup(Extent extent, List keys, TRule rule) {
        EObject obj = lookup(injections, keys, 0);
        Trace trace;
        if (null == obj) {
            obj = new DynamicObject();
            store(keys, obj);
            trace = createTrace(extent, keys, obj);
            traces.put(obj, trace);
        } else {
            trace = (Trace) traces.get(obj);
        }
        if (rule != null) {
            trace.getRules().add(rule);
        }
        ExtentUtil.highlightNode(obj, ExtentUtil.OBJECT_LOOKUP);
        ExtentUtil.highlightNode(trace, ExtentUtil.OBJECT_LOOKUP);
        
        return obj;
    }
    
    /**
     * @param extent
     * @param keys
     * @param obj
     * @throws Error
     */
    private Trace createTrace(Extent extent, List keys, EObject obj) throws Error {
        Trace trace = TraceFactory.eINSTANCE.createTrace();
        trace.setTarget(obj);
        if (obj instanceof DynamicObject) {
            DynamicObject dynObj = (DynamicObject) obj;
            dynObj.addReferenceFrom(trace, TracePackage.eINSTANCE.getTrace_Target());
        }
        
        extent.add(trace);

        List sources = trace.getSources();
        for (int i = 0; i < keys.size(); i++) {
            Object key = keys.get(i);
            if (key instanceof BindingPair) {
                key = ((BindingPair) key).getValue();
            }

            if (key instanceof EObject) {
                ObjectAny any = TraceFactory.eINSTANCE.createObjectAny();
                any.getValue().add((EObject) key);
                sources.add(any);

                // In case a target object is used as an injection parameter
                if (key instanceof DynamicObject) {
                    DynamicObject dynObj = (DynamicObject) key;
                    dynObj.addMultiReferenceFrom(any, TracePackage.eINSTANCE.getObjectAny_Value());
                }
            } else if (key instanceof String) {
                StringAny any = TraceFactory.eINSTANCE.createStringAny();
                any.setValue((String) key);
                sources.add(any);
            } else if (key instanceof Integer) {
                IntAny any = TraceFactory.eINSTANCE.createIntAny();
                any.setValue(((Integer) key).intValue());
                sources.add(any);
            } else if (key instanceof Boolean) {
                BoolAny any = TraceFactory.eINSTANCE.createBoolAny();
                any.setValue(((Boolean) key).booleanValue());
                sources.add(any);
            } else {
                throw new Error("Internal Error: trace support for " + key.getClass() + " not yet implemented.");
            }
        }
        return trace;
    }

    /**
     * Update the injections mapping
     * 
     * @param keys
     * @param value
     */
    private void store(List keys, EObject value) {
        store(injections, keys, value, 0);
    }
    
    private EObject lookup(Map map, List keys, int idx) {
        Object key = keys.get(idx);
        Object keyVal = map.get(key);
        
        if (null == keyVal) {
            return null;
        } else if ((idx + 1)  < keys.size() && keyVal instanceof Map) {
            return lookup((Map) keyVal, keys, idx + 1);
        } else {
            return (EObject) keyVal;
        }
    }
    
    private void store(Map map, List keys, EObject value, int idx) {
        Object key = keys.get(idx);
        
        if ((idx + 1) < keys.size()) {
            Map subMap = (Map) map.get(key);
            if (null == subMap) {
                subMap = new HashMap();
                map.put(key, subMap);
            }
            store(subMap, keys, value, idx + 1);
        } else {
            map.put(key, value);
        }
    }
}
