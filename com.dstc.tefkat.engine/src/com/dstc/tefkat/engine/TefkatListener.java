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
 *  File:     TefkatListener.java
 *
 *  History:  Created on 05/07/2004 by lawley
 *
 */
package com.dstc.tefkat.engine;

import org.eclipse.emf.ecore.resource.Resource;

import com.dstc.tefkat.model.Extent;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.Term;
import com.dstc.tefkat.model.Transformation;

/**
 * @author lawley
 *  
 */
public interface TefkatListener {
    public void started();

    public void stopped();
    
    public void breakpoint(Term term);
    
    public void suspended();
    
    public void resumed();

    public void info(String message);

    public void warning(String message);

    public void error(String message, Throwable cause);

    public void transformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace, Binding context);

    public void transformationFinished();
    
    public void resourceLoaded(Resource res);

    public void evaluateRule(TRule rule, Binding context, boolean cached);

    public void evaluateSource(TRule rule, Binding context);

    public void evaluateTarget(TRule rule, Binding context);

    public void enterTree(Tree tree);

    public void exitTree(Tree tree);

    public void enterTerm(Node node);

    public void exitTerm(Node node);

    public void delayTerm(Node node);
}
