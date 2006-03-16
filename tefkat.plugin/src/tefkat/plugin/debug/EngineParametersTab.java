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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

/**
 * @author lawley
 *
 */
public class EngineParametersTab extends AbstractLaunchConfigurationTab {

    private Label configurationLabel;
    private Text configurationText;
    private Button forceButton;
    
    private class EngineTabListener extends SelectionAdapter implements ModifyListener {

        /* (non-Javadoc)
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(ModifyEvent e) {
            updateLaunchConfigurationDialog();
        }
        
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
//            Object source = e.getSource();
//            if (source == sourcesTable) {
//                setSourcesButtonsEnableState();
//            } else if (source == sourcesAddButton) {
//                handleSourcesAddButtonSelected();
//            } else if (source == sourcesEditButton) {
//                handleSourcesEditButtonSelected();
//            } else if (source == sourcesRemoveButton) {
//                handleSourcesRemoveButtonSelected();
//            } else if (source == targetsTable) {
//                setTargetsButtonsEnableState();
//            } else if (source == targetsAddButton) {
//                handleTargetsAddButtonSelected();
//            } else if (source == targetsEditButton) {
//                handleTargetsEditButtonSelected();
//            } else if (source == targetsRemoveButton) {
//                handleTargetsRemoveButtonSelected();
//            } else if (source == URIMapTable) {
//                setURIMapButtonsEnableState();
//            } else if (source == URIMapAddButton) {
//                handleURIMapAddButtonSelected();
//            } else if (source == URIMapEditButton) {
//                handleURIMapEditButtonSelected();
//            } else if (source == URIMapRemoveButton) {
//                handleURIMapRemoveButtonSelected();
//            }
        }

    }
    
    private EngineTabListener listener = new EngineTabListener();

    /**
     * 
     */
    public EngineParametersTab() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Font font = parent.getFont();

        Composite comp = new Composite(parent, SWT.NONE);
        setControl(comp);
        GridLayout topLayout = new GridLayout();
        comp.setLayout(topLayout);
        
        GridData gd;
        
        Composite configurationComp = new Composite(comp, SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        configurationComp.setLayoutData(gd);
        GridLayout configurationLayout = new GridLayout();
        configurationLayout.marginHeight = 0;
        configurationLayout.marginWidth = 0;
        configurationLayout.numColumns = 3;
        configurationComp.setLayout(configurationLayout);
        
        configurationLabel = new Label(configurationComp, SWT.NONE);
        configurationLabel.setText("Transformation Configuration");
        configurationLabel.setFont(font);
        
        configurationText = new Text(configurationComp, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        configurationText.setLayoutData(gd);
        configurationText.setFont(font);
        configurationText.addModifyListener(listener);
        
        Button configurationButton = createPushButton(configurationComp, "Browse...");
//        gd = new GridData(GridData.FILL_HORIZONTAL);
//        configurationButton.setLayoutData(gd);
        configurationButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                handleConfigurationButtonSelected();
            }
        });
        
        forceButton = new Button(configurationComp, SWT.CHECK);
        forceButton.setText("Continue after error");
        forceButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                handleForceButtonSelected();
            }
        });
    }

	private static Button createPushButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH);
		button.setFont(parent.getFont());
		if (label != null) {
			button.setText(label);
		}
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		button.setLayoutData(gd);
		return button;	
	}	

    private void handleForceButtonSelected() {
        updateLaunchConfigurationDialog();
    }
    
    private void handleConfigurationButtonSelected() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(),
                root,
                "Select the Tefkat configuration");
        
        // Do we want an initial selection?
        // Maybe, but it should look for tefkat.xml in the current project,
        // and there is no notion of a "current project" at this point.
        //
        String currentContainerString = configurationText.getText();
        if (currentContainerString != null && currentContainerString.length() > 0) {
            Path containerPath = new Path(currentContainerString);
            IResource currentResource = (IResource) root.findMember(containerPath);
            if (currentResource != null) {
                dialog.setInitialSelections(new Object[] {currentResource});
            }
        }
  
//        dialog.showClosedProjects(false);
        if (dialog.open() == ResourceSelectionDialog.OK) {
            Object[] results = dialog.getResult();
            if (results != null && results.length > 0 && (results[0] instanceof IResource)) {
                IResource resource = (IResource) results[0];
                String containerName = resource.getFullPath().toOSString();
                configurationText.setText(containerName);
            }
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(IEngineLaunchConfigurationConstants.CONFIGURATION_URI, "");
        configuration.setAttribute(IEngineLaunchConfigurationConstants.FORCE, false);
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration) {
        try {
            configurationText.setText(configuration.getAttribute(IEngineLaunchConfigurationConstants.CONFIGURATION_URI, ""));
        } catch (CoreException e) {
            configurationText.setText("");
        }
        try {
            forceButton.setSelection(Boolean.valueOf(configuration.getAttribute(IEngineLaunchConfigurationConstants.FORCE, "false")).booleanValue());
        } catch (CoreException e1) {
            forceButton.setSelection(false);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(IEngineLaunchConfigurationConstants.CONFIGURATION_URI, configurationText.getText());
        configuration.setAttribute(IEngineLaunchConfigurationConstants.FORCE, forceButton.getSelection());
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     */
    public String getName() {
        return "Tefkat Configuration";
    }

}
