/*
 *
 *  Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2004.
 *  Unpublished work.  All Rights Reserved.
 *
 *  The software contained on this media is the property of the
 *  DSTC Pty Ltd.  Use of this software is strictly in accordance
 *  with the license agreement in the accompanying LICENSE.DOC
 *  file.  If your distribution of this software does not contain
 *  a LICENSE.DOC file then you have no rights to use this
 *  software in any manner and should contact DSTC at the address
 *  below to determine an appropriate licensing arrangement.
 *
 *     DSTC Pty Ltd
 *     Level 7, G.P. South
 *     Staff House Road
 *     University of Queensland
 *     St Lucia, 4072
 *     Australia
 *     Tel: +61 7 3365 4310
 *     Fax: +61 7 3365 4311
 *     Email: enquiries@dstc.edu.au
 *
 *  This software is being provided "AS IS" without warranty of
 *  any kind.  In no event shall DSTC Pty Ltd be liable for
 *  damage of any kind arising out of or in connection with
 *  the use or performance of this software.
 *
 *  Project:  com.dstc.tefkat.plugin
 *
 *  File:     ToggleNatureAction.java
 *
 *  History:  Created on 31/05/2004 by lawley
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
