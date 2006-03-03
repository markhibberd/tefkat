/*
 * Created on 4/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.ui.ISourcePresentation;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DebugSourceLocator implements ISourceLocator, ISourcePresentation {

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISourceLocator#getSourceElement(org.eclipse.debug.core.model.IStackFrame)
     */
    public Object getSourceElement(IStackFrame stackFrame) {
        if (stackFrame instanceof AbstractStackFrame) {
            Object element = ((AbstractStackFrame) stackFrame).getElement();
            return element;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ISourcePresentation#getEditorInput(java.lang.Object)
     */
    public IEditorInput getEditorInput(Object element) {
        if (element instanceof EObject) {
            URI uri = ((EObject) element).eResource().getURI();
            if ("platform".equals(uri.scheme()) && uri.segmentCount() > 1 && "resource".equals(uri.segment(0))) {
                StringBuffer path = new StringBuffer();
                for (int i = 1, size = uri.segmentCount(); i < size; i++) {
                    path.append('/');
                    path.append(uri.segment(i));
                }
                return new FileEditorInput((IFile) ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path.toString())));
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ISourcePresentation#getEditorId(org.eclipse.ui.IEditorInput, java.lang.Object)
     */
    public String getEditorId(IEditorInput input, Object element) {
        if (element instanceof EObject) {
            return "com.dstc.tefkat.plugin.TefkatModelEditor";
        }
        return null;
    }

}
