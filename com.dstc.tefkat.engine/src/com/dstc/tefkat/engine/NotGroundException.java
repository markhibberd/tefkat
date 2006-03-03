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
*  Project:  com.dstc.tefkat.engine
*
*  File:     NotGroundException.java
*
*  History:  Created on 13/09/2004 by lawley
*
*/
package com.dstc.tefkat.engine;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotGroundException extends Exception {

    /**
     * @param message
     */
    public NotGroundException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public NotGroundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public NotGroundException(Throwable cause) {
        super(cause);
    }
}
