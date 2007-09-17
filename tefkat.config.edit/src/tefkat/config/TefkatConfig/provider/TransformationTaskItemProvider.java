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
package tefkat.config.TefkatConfig.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tefkat.config.TefkatConfig.TefkatConfigFactory;
import tefkat.config.TefkatConfig.TefkatConfigPackage;
import tefkat.config.TefkatConfig.TransformationTask;

/**
 * This is the item provider adapter for a {@link tefkat.config.TefkatConfig.TransformationTask} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TransformationTaskItemProvider
    extends ItemProviderAdapter
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransformationTaskItemProvider(AdapterFactory adapterFactory) {
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

            addEnabledPropertyDescriptor(object);
            addModePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Enabled feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addEnabledPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TransformationTask_enabled_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TransformationTask_enabled_feature", "_UI_TransformationTask_type"),
                 TefkatConfigPackage.Literals.TRANSFORMATION_TASK__ENABLED,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Mode feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addModePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TransformationTask_mode_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TransformationTask_mode_feature", "_UI_TransformationTask_type"),
                 TefkatConfigPackage.Literals.TRANSFORMATION_TASK__MODE,
                 false,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRANSFORMATION);
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__SOURCE_MODELS);
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TARGET_MODELS);
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRACE);
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__PROPERTIES);
            childrenFeatures.add(TefkatConfigPackage.Literals.TRANSFORMATION_TASK__URI_MAP);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns TransformationTask.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TransformationTask"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText(Object object) {
        TransformationTask transformationTask = (TransformationTask)object;
        return getString("_UI_TransformationTask_type") + " " + transformationTask.isEnabled();
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

        switch (notification.getFeatureID(TransformationTask.class)) {
            case TefkatConfigPackage.TRANSFORMATION_TASK__ENABLED:
            case TefkatConfigPackage.TRANSFORMATION_TASK__MODE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRANSFORMATION:
            case TefkatConfigPackage.TRANSFORMATION_TASK__SOURCE_MODELS:
            case TefkatConfigPackage.TRANSFORMATION_TASK__TARGET_MODELS:
            case TefkatConfigPackage.TRANSFORMATION_TASK__TRACE:
            case TefkatConfigPackage.TRANSFORMATION_TASK__PROPERTIES:
            case TefkatConfigPackage.TRANSFORMATION_TASK__URI_MAP:
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
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRANSFORMATION,
                 TefkatConfigFactory.eINSTANCE.createModel()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__SOURCE_MODELS,
                 TefkatConfigFactory.eINSTANCE.createModel()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TARGET_MODELS,
                 TefkatConfigFactory.eINSTANCE.createModel()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRACE,
                 TefkatConfigFactory.eINSTANCE.createModel()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__PROPERTIES,
                 TefkatConfigFactory.eINSTANCE.create(TefkatConfigPackage.Literals.PROPERTY)));

        newChildDescriptors.add
            (createChildParameter
                (TefkatConfigPackage.Literals.TRANSFORMATION_TASK__URI_MAP,
                 TefkatConfigFactory.eINSTANCE.create(TefkatConfigPackage.Literals.URI_MAP_ENTRY)));
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
            childFeature == TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRANSFORMATION ||
            childFeature == TefkatConfigPackage.Literals.TRANSFORMATION_TASK__SOURCE_MODELS ||
            childFeature == TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TARGET_MODELS ||
            childFeature == TefkatConfigPackage.Literals.TRANSFORMATION_TASK__TRACE;

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
        return TefkatConfigEditPlugin.INSTANCE;
    }

}
