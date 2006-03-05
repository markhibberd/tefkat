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

package com.dstc.tefkat.plugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ToggleNatureAction implements IObjectActionDelegate {

    private IProject selectedProject;

    /**
     * Constructor for Action1.
     */
    public ToggleNatureAction() {
        super();
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // ignore active part
    }

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) {
        if (selectedProject == null) {
            return;
        }
        try {
            boolean hasNature = selectedProject.hasNature(TefkatPlugin.TEFKAT_NATURE);
            if (!hasNature) {
                TefkatPlugin.getPlugin().addAutoBuildNature(selectedProject);
            } else {
                TefkatPlugin.getPlugin().removeAutoBuildNature(selectedProject);
            }
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        selectedProject = null;

        if (! (selection instanceof IStructuredSelection)) {
            return;
        }
        IStructuredSelection structuredSelection = (IStructuredSelection) selection;
        Object elt = structuredSelection.getFirstElement();
        if (elt != null && elt instanceof IAdaptable) {
            selectedProject = (IProject) ((IAdaptable) elt).getAdapter(IProject.class);
        }
    }

}
