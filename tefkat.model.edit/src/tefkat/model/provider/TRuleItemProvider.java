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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tefkat.model.TRule;
import tefkat.model.TefkatFactory;
import tefkat.model.TefkatPackage;

/**
 * This is the item provider adapter for a {@link tefkat.model.TRule} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TRuleItemProvider
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
    public static final String copyright = "Copyright michael lawley Pty Ltd 2003-2007";

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TRuleItemProvider(AdapterFactory adapterFactory) {
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

            addExtendedPropertyDescriptor(object);
            addSupersededPropertyDescriptor(object);
            addAbstractPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
                 getString("_UI_TRule_extended_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRule_extended_feature", "_UI_TRule_type"),
                 TefkatPackage.Literals.TRULE__EXTENDED,
                 true,
                 false,
                 false,
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
                 getString("_UI_TRule_superseded_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRule_superseded_feature", "_UI_TRule_type"),
                 TefkatPackage.Literals.TRULE__SUPERSEDED,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Abstract feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAbstractPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TRule_abstract_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TRule_abstract_feature", "_UI_TRule_type"),
                 TefkatPackage.Literals.TRULE__ABSTRACT,
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
            childrenFeatures.add(TefkatPackage.Literals.TRULE__SRC);
            childrenFeatures.add(TefkatPackage.Literals.TRULE__TGT);
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
     * This returns TRule.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TRule"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText(Object object) {
        String label = ((TRule)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_TRule_type") :
            getString("_UI_TRule_type") + " " + label;
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

        switch (notification.getFeatureID(TRule.class)) {
            case TefkatPackage.TRULE__ABSTRACT:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case TefkatPackage.TRULE__SRC:
            case TefkatPackage.TRULE__TGT:
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
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createAndTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createOrTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createNotTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createIfTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createTrackingUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createPatternUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createCondition()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createMofInstance()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__SRC,
                 TefkatFactory.eINSTANCE.createMofOrder()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createAndTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createIfTerm()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createTrackingUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createPatternUse()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createCondition()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createMofInstance()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createMofOrder()));

        newChildDescriptors.add
            (createChildParameter
                (TefkatPackage.Literals.TRULE__TGT,
                 TefkatFactory.eINSTANCE.createInjection()));
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
            childFeature == TefkatPackage.Literals.TRULE__SRC ||
            childFeature == TefkatPackage.Literals.TRULE__TGT;

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
