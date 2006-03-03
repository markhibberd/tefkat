/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package com.dstc.tefkat.model.provider;


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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;

import com.dstc.tefkat.model.PatternDefn;
import com.dstc.tefkat.model.TefkatFactory;
import com.dstc.tefkat.model.TefkatPackage;

/**
 * This is the item provider adpater for a {@link com.dstc.tefkat.model.PatternDefn} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PatternDefnItemProvider
    extends VarScopeItemProvider
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
    public PatternDefnItemProvider(AdapterFactory adapterFactory) {
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

            addParameterVarPropertyDescriptor(object);
            addSourcePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Parameter Var feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addParameterVarPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_PatternDefn_parameterVar_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_PatternDefn_parameterVar_feature", "_UI_PatternDefn_type"),
                 TefkatPackage.eINSTANCE.getPatternDefn_ParameterVar(),
                 true));
    }

    /**
     * This adds a property descriptor for the Source feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSourcePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_PatternDefn_source_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_PatternDefn_source_feature", "_UI_PatternDefn_type"),
                 TefkatPackage.eINSTANCE.getPatternDefn_Source(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Collection getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(TefkatPackage.eINSTANCE.getPatternDefn_Term());
        }
        return childrenFeatures;
    }

    /**
     * This returns PatternDefn.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImage(Object object) {
        return getResourceLocator().getImage("full/obj16/PatternDefn");
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText(Object object) {
        String label = ((PatternDefn)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_PatternDefn_type") :
            getString("_UI_PatternDefn_type") + " " + label;
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

        switch (notification.getFeatureID(PatternDefn.class)) {
            case TefkatPackage.PATTERN_DEFN__SOURCE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case TefkatPackage.PATTERN_DEFN__TERM:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
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

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createAndTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createOrTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createNotTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createIfTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createTrackingUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createPatternUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createCondition()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createMofInstance()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createMofOrder()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.eINSTANCE.getPatternDefn_Term(),
                 TefkatFactory.eINSTANCE.createInjection()));
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
