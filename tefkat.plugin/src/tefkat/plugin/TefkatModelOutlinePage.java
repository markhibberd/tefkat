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

package tefkat.plugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import tefkat.model.Var;
import tefkat.model.AndTerm;
import tefkat.model.MofInstance;
import tefkat.model.OrTerm;
import tefkat.model.Term;
import tefkat.model.Transformation;
import tefkat.model.VarScope;
import tefkat.model.VarUse;


/**
 * @author lawley
 *
 */
public class TefkatModelOutlinePage extends ContentOutlinePage {

    private String resId;
    final private Map objMap = new HashMap();
    final private Map idMap = new HashMap();
    
    /**
     * 
     */
    public TefkatModelOutlinePage() {
        super();
    }

    public void setResource(Resource res) {
        String id = res.getURI().toString();
        boolean reparse = id.equals(resId);

        resId = id;
        
        // leaks mem like a sieve without these two lines :-)
        idMap.clear();
        objMap.clear();

        objMap.put(id, res);
        idMap.put(res, id);
        if (!reparse) {
            getTreeViewer().setInput(id);
        } else {
            getTreeViewer().refresh(id);
        }
    }
    
    private String getID(Object obj) {
        if (null == obj) {
            return "";
        }
        
        String id = (String) idMap.get(obj);
        
        if (null == id) {
            if (obj instanceof Transformation) {
                id = ((Transformation) obj).getName();
            } else if (obj instanceof VarScope) {
                id = "SCOPE" + getID(((EObject) obj).eContainer()) + ((VarScope) obj).getName();
            } else if (obj instanceof Var) {
                id = "VAR" + getID(((EObject) obj).eContainer()) + ((Var) obj).getName();
            } else if (obj instanceof VarUse) {
                id = "USE" + getID(((EObject) obj).eContainer()) + getID(((VarUse) obj).getVar());
            } else if (obj instanceof AndTerm) {
                id = "AND" + getID(((EObject) obj).eContainer());
                for (Iterator itr = ((AndTerm) obj).getTerm().iterator(); itr.hasNext(); ) {
                    id = id + itr.next();
                }
            } else if (obj instanceof OrTerm) {
                id = "OR" + getID(((EObject) obj).eContainer());
                for (Iterator itr = ((OrTerm) obj).getTerm().iterator(); itr.hasNext(); ) {
                    id = id + itr.next();
                }
            } else if (obj instanceof MofInstance) {
                MofInstance inst = ((MofInstance) obj);
                id = "INST" + getID(((EObject) obj).eContainer()) + inst.getTypeName() + inst.getInstance();
            } else if (obj instanceof EObject) {
                id = obj.getClass().getName() + getID(((EObject) obj).eContainer()) + obj.hashCode();
            } else if (obj instanceof Resource) {
                id = ((Resource) obj).getURI().toString();
            } else {
                id = obj.getClass().getName() + obj.hashCode();
            }
            
            objMap.put(id, obj);
            idMap.put(obj, id);
        }
        
        return id;
    }

    private String[] getIDs(Object[] objects) {
        String[] strings = new String[objects.length];
        
        for (int i = 0; i < objects.length; i++) {
            strings[i] = getID(objects[i]);
        }
        
        return strings;
    }

    private String[] getIDs(Object[] objects, Object context) {
        if (null == context) {
            return getIDs(objects);
        }
        
        String[] strings = new String[objects.length + 1];
        
        for (int i = 0; i < objects.length; i++) {
            strings[i] = getID(objects[i]);
        }
        strings[objects.length] = getID(context);
        
        return strings;
    }
    
    /**
     * Return the real objects in the selection rather than their internal ID proxies
     * 
     * @see org.eclipse.ui.views.contentoutline.ContentOutlinePage#fireSelectionChanged(org.eclipse.jface.viewers.ISelection)
     */
    protected void fireSelectionChanged(ISelection selection) {
        Object[] idElts = ((IStructuredSelection) selection).toArray();
        Object[] elts = new Object[idElts.length];
        for (int i = 0; i < elts.length; i++) {
            elts[i] = objMap.get(idElts[i]);
        }
        super.fireSelectionChanged(new StructuredSelection(elts));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        super.createControl(parent);
        getTreeViewer().setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                Object obj = objMap.get(element);
                if (obj instanceof EClassifier) {
                    return ((EClassifier) obj).getName();
                } else if (obj instanceof ETypedElement) {
                    return ((ETypedElement) obj).getName() + ": " + ((ETypedElement) obj).getEType().getName();
                } else if (obj instanceof EPackage) {
                    return ((EPackage) obj).getName() + " <" + ((EPackage) obj).getNsURI() + ">";
                } else {
                    return String.valueOf(obj);
                }
            }
        });
        getTreeViewer().setContentProvider(new ITreeContentProvider() {

            public Object[] getChildren(Object elementID) {
                Object element = objMap.get(elementID); // map from String id to (E)Object

                if (element instanceof EObject) {
                    Object[] children = ((EObject) element).eContents().toArray();
                    if (element instanceof Term) {
                        return getIDs(children, ((Term) element).getContext());
                    } else {
                        return getIDs(children);
                    }
                } else {
//                    System.out.println("************* Parent element must be an EObject: " + element);
                    return null;
//                    throw new Error("Parent element must be an EObject");
                }
            }

            public Object getParent(Object elementID) {
                Object element = objMap.get(elementID); // map from String id to (E)Object
                if (element instanceof EObject) {
                    return ((EObject) element).eContainer();
                }
                return null;
            }

            public boolean hasChildren(Object elementID) {
                Object element = objMap.get(elementID); // map from String id to (E)Object
                boolean children = false;
                if (element instanceof EObject) {
                    children = !((EObject) element).eContents().isEmpty();
                }
                return children;
            }

            public Object[] getElements(Object elementID) {
                Object element = objMap.get(elementID); // map from String id to Object
                if (element instanceof Resource) {
                    Object[] children = ((Resource) element).getContents().toArray();
                    if (element instanceof Term) {
                        return getIDs(children, ((Term) element).getContext());
                    } else {
                        return getIDs(children);
                    }
                } else {
//                    System.out.println("************* Root element must be a Resource: " + element);
                    return null;
//                    throw new Error("Root element must be a Resource");
                }
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                if (viewer != null && newInput != null && oldInput == null) {
                    ((TreeViewer) viewer).refresh(newInput);
                }
            }

            public void dispose() {
            }
            
        });
    }

    public void dispose() {
        resId = null;
        objMap.clear();
        idMap.clear();
    }
    
}
