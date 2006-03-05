/**
 * <copyright>
 * </copyright>
 *
 */
package com.dstc.tefkat.model.provider;


import com.dstc.tefkat.model.TRuleVar;
import com.dstc.tefkat.model.TefkatPackage;

import java.util.Collection;
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

/**
 * This is the item provider adapter for a {@link com.dstc.tefkat.model.TRuleVar} object.
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
	public static final String copyright = "Copyright michael lawley 2003-2005";

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
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_extender_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_extender_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Extender(),
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Extended feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addExtendedPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_extended_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_extended_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Extended(),
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Superseder feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addSupersederPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_superseder_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_superseder_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Superseder(),
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Superseded feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addSupersededPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRuleVar_superseded_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRuleVar_superseded_feature", "_UI_TRuleVar_type"),
                 TefkatPackage.eINSTANCE.getTRuleVar_Superseded(),
                 true,
                 null,
                 null,
                 null));
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
