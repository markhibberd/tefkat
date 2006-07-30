/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 * $Id$
 */
package tefkat.model.provider;


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

import tefkat.model.FeatureExpr;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;

/**
 * This is the item provider adapter for a {@link tefkat.model.FeatureExpr} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureExprItemProvider
    extends CompoundExprItemProvider
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2006";

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureExprItemProvider(AdapterFactory adapterFactory) {
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

            addOperationPropertyDescriptor(object);
            addCollectPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Operation feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addOperationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_FeatureExpr_operation_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_FeatureExpr_operation_feature", "_UI_FeatureExpr_type"),
                 TefkatPackage.Literals.FEATURE_EXPR__OPERATION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Collect feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCollectPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_FeatureExpr_collect_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_FeatureExpr_collect_feature", "_UI_FeatureExpr_type"),
                 TefkatPackage.Literals.FEATURE_EXPR__COLLECT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
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
            childrenFeatures.add(TefkatPackage.Literals.FEATURE_EXPR__FEATURE);
        }
        return childrenFeatures;
    }

    /**
     * This returns FeatureExpr.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/FeatureExpr"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText(Object object) {
        FeatureExpr featureExpr = (FeatureExpr)object;
        return getString("_UI_FeatureExpr_type") + " " + featureExpr.isOperation();
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

        switch (notification.getFeatureID(FeatureExpr.class)) {
            case TefkatPackage.FEATURE_EXPR__OPERATION:
            case TefkatPackage.FEATURE_EXPR__COLLECT:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case TefkatPackage.FEATURE_EXPR__FEATURE:
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
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createInstanceRef()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createVarUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createCollectionExpr()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createFunctionExpr()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createFeatureExpr()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createStringConstant()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createIntConstant()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createRealConstant()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createBooleanConstant()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.FEATURE_EXPR__FEATURE,
                 TefkatFactory.eINSTANCE.createEnumConstant()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify =
            childFeature == TefkatPackage.Literals.COMPOUND_EXPR__ARG ||
            childFeature == TefkatPackage.Literals.FEATURE_EXPR__FEATURE;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2",
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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
