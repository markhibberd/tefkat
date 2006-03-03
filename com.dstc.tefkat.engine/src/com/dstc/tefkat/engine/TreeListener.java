/*
 * Created on May 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.engine;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
