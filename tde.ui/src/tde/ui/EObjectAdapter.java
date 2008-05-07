package tde.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IActionFilter;

import tde.popup.actions.TDEQuestionsActionFilter;

public class EObjectAdapter implements IAdapterFactory {
    public Object getAdapter(Object adaptableObject, Class adapter) {
        System.err.println("getAdapter: " + adapter.getName() );
        if (adaptableObject instanceof EObject && adapter == IActionFilter.class) {
            return TDEQuestionsActionFilter.INSTANCE;
        }
        return null;
    }
    public Class[] getAdapterList() {
        System.err.println("getAdapterList");
        return new Class[] { IActionFilter.class };
    }
}
