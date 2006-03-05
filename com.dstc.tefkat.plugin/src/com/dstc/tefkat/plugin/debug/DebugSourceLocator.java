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
