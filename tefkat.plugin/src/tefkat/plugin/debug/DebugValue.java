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
 * 
 */

package tefkat.plugin.debug;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import tefkat.engine.Binding;
import tefkat.engine.DynamicObject;


/**
 * @author lawley
 *
 */
public class DebugValue extends AbstractDebugElement implements IValue {

    private Object val;
    private AbstractStackFrame frame;

    /**
     * @param target
     */
    public DebugValue(AbstractStackFrame frame, Object val) {
        super((DebugTarget) frame.getDebugTarget());
        
        this.frame = frame;
        this.val = val;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
     */
    public String getReferenceTypeName() throws DebugException {
        return null == val ? "null" : String.valueOf(val.getClass());
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValue#getValueString()
     */
    public String getValueString() throws DebugException {
        String result;
        if (val instanceof DynamicObject) {
            EObjectImpl eVal = (EObjectImpl) val;
            result = "_" + eVal.hashCode() + ":?" + eVal.eClass().getName();
        } else if (val instanceof EObject) {
            EObject eVal = (EObject) val;
            result = "_" + eVal.hashCode() + ":" + eVal.eClass().getName();
        } else if (val instanceof List) {
            result = "[...] " + ((List) val).size();
        } else if (val instanceof Map) {
            result = "{...} " + ((Map) val).size();
        } else if (val instanceof Binding) {
            result = "{...} " + ((Binding) val).entrySet().size();
        } else if (null == val) {
            result = null;
        } else {
            result = String.valueOf(val);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValue#isAllocated()
     */
    public boolean isAllocated() throws DebugException {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValue#getVariables()
     */
    public IVariable[] getVariables() throws DebugException {
        IVariable[] subVariables;
        if (val instanceof EObject) {
            EObject eVal = (EObject) val;
            List features = eVal.eClass().getEAllStructuralFeatures();
            subVariables = new IVariable[features.size()];
            for (int i = 0; i < subVariables.length; i++) {
                EStructuralFeature feature = (EStructuralFeature) features.get(i);
                subVariables[i] = new DebugVariable(frame, feature.getName(), eVal.eGet(feature));
            }
        } else if (val instanceof List) {
            List lVal = (List) val;
            subVariables = new IVariable[lVal.size()];
            for (int i = 0; i < subVariables.length; i++) {
                subVariables[i] = new DebugVariable(frame, String.valueOf(i), lVal.get(i));
            }
        } else if (val instanceof Map) {
            Map mVal = (Map) val;
            subVariables = new IVariable[mVal.size()];
            int i = 0;
            for (Iterator itr = mVal.entrySet().iterator(); itr.hasNext(); ) {
                Map.Entry entry = (Map.Entry) itr.next();
                subVariables[i++] = new DebugVariable(frame, entry.getKey(), entry.getValue());
            }
        } else if (val instanceof Binding) {
            Binding mVal = (Binding) val;
            subVariables = new IVariable[mVal.entrySet().size()];
            int i = 0;
            for (Iterator itr = mVal.entrySet().iterator(); itr.hasNext(); ) {
                Map.Entry entry = (Map.Entry) itr.next();
                subVariables[i++] = new DebugVariable(frame, entry.getKey(), entry.getValue());
            }
        } else {
            subVariables = new IVariable[0];
        }
        return subVariables;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IValue#hasVariables()
     */
    public boolean hasVariables() throws DebugException {
        return val instanceof EObject || val instanceof List || val instanceof Map || val instanceof Binding;
    }

    public boolean equals(Object obj) {
        if (null != val && obj instanceof DebugValue) {
            DebugValue dv = (DebugValue) obj;
            return val.equals(dv.val);
        }
        return false;
    }
    
    public int hashCode() {
        return null == val ? -1 : val.hashCode() + 42;
    }

}
