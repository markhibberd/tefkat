/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package com.dstc.tefkat.model.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.TRuleVar;
import com.dstc.tefkat.model.TefkatPackage;

/**
 * This is the item provider adpater for a {@link com.dstc.tefkat.model.TRuleVar} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TRuleVarItemProvider
	extends AbstractVarItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright DSTC Pty Ltd 2003-2005";

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TRuleVarItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public List getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addExtenderPropertyDescriptor(object);
            addExtendedPropertyDescriptor(object);
            addSupersederPropertyDescriptor(object);
            addSupersededPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Extender feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addExtenderPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_extender_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_extender_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Extender(),
                 true));
    }

	/**
	 * This adds a property descriptor for the Extended feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addExtendedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getString("_UI_TRuleVar_extended_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_extended_feature", "_UI_TRuleVar_type"),
				 TefkatPackage.eINSTANCE.getTRuleVar_Extended(),
				 true)
				 {
				 	/**
				 	 * The possible extended vars is the union of all vars owned by rules that this rule extends
				 	 * For the moment I am assuming that superseded vars don't need to go here (there are no
				 	 * superseding references on TRuleVar at the moment, which is concerning)
				 	 */
				 	protected Collection getComboBoxObjects(Object object) {
				 		Collection result = new ArrayList();
				 		TRuleVar rv = (TRuleVar) object;
				 		TRule rule = (TRule) rv.getScope();
				 		Iterator extRules = rule.getExtended().iterator();
				 		while (extRules.hasNext()) {
				 			TRule extRule = (TRule) extRules.next();
				 			result.addAll(extRule.getVars());
				 		}
				 		return result;
				 	}
				 }
				 );
	}


    /**
     * This adds a property descriptor for the Superseder feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSupersederPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_superseder_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_superseder_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Superseder(),
                 true));
    }

    /**
     * This adds a property descriptor for the Superseded feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSupersededPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_superseded_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_superseded_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Superseded(),
                 true));
    }

    /**
     * This returns TRuleVar.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object getImage(Object object) {
        return getResourceLocator().getImage("full/obj16/TRuleVar");
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getText(Object object) {
        String label = ((TRuleVar)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_TRuleVar_type") :
            getString("_UI_TRuleVar_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void notifyChanged(Notification notification) {
        updateChildren(notification);
        super.notifyChanged(notification);
    }

    /**
     * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing all of the children that can be created under this object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResourceLocator getResourceLocator() {
        return TefkatModelEditPlugin.INSTANCE;
    }

}
