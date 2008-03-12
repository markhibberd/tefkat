/*
 * Copyright (c) 2004- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 *
 */

package tefkat.engine;

import org.eclipse.emf.ecore.resource.Resource;

import tefkat.model.Extent;
import tefkat.model.TRule;
import tefkat.model.Term;
import tefkat.model.Transformation;


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
    public void transformationFinished(Transformation transformation);

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
