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
package tefkat.config.TefkatConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Execution Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see tefkat.config.TefkatConfig.TefkatConfigPackage#getExecutionMode()
 * @model
 * @generated
 */
public final class ExecutionMode extends AbstractEnumerator {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley Pty Ltd 2004-2005";

    /**
     * The '<em><b>REPLACE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>REPLACE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REPLACE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int REPLACE = 0;

    /**
     * The '<em><b>UPDATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>UPDATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UPDATE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int UPDATE = 1;

    /**
     * The '<em><b>REPLACE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #REPLACE
     * @generated
     * @ordered
     */
    public static final ExecutionMode REPLACE_LITERAL = new ExecutionMode(REPLACE, "REPLACE");

    /**
     * The '<em><b>UPDATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UPDATE
     * @generated
     * @ordered
     */
    public static final ExecutionMode UPDATE_LITERAL = new ExecutionMode(UPDATE, "UPDATE");

    /**
     * An array of all the '<em><b>Execution Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ExecutionMode[] VALUES_ARRAY =
        new ExecutionMode[] {
            REPLACE_LITERAL,
            UPDATE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Execution Mode</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Execution Mode</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExecutionMode get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ExecutionMode result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Execution Mode</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExecutionMode get(int value) {
        switch (value) {
            case REPLACE: return REPLACE_LITERAL;
            case UPDATE: return UPDATE_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ExecutionMode(int value, String name) {
        super(value, name);
    }

} //ExecutionMode
