/*
 * Copyright (c) 2005- michael lawley and others.
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

/**
 * @author lawley
 *
 */
interface TreeListener {
    
    /**
     * Called as each new successful leaf Node is found.
     * 
     * @param node
     * @throws ResolutionException
     */
    public void solution(Node node) throws ResolutionException;
    
    /**
     * Called once when the Tree has finished being expanded.
     * Once called, there will be no further calls to solution.
     *
     * @param tree
     */
    public void completed(Tree tree);

}
