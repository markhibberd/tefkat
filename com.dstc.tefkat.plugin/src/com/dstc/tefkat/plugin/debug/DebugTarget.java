/*
 * Created on 11/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.dstc.tefkat.engine.Binding;
import com.dstc.tefkat.engine.Node;
import com.dstc.tefkat.engine.OverrideTerm;
import com.dstc.tefkat.engine.Tefkat;
import com.dstc.tefkat.engine.TefkatListener;
import com.dstc.tefkat.engine.Tree;
import com.dstc.tefkat.model.Extent;
import com.dstc.tefkat.model.TRule;
import com.dstc.tefkat.model.Term;
import com.dstc.tefkat.model.Transformation;
import com.dstc.tefkat.model.parser.ParserEvent;
import com.dstc.tefkat.model.parser.ParserListener;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DebugTarget extends AbstractDebugElement implements IDebugTarget, ParserListener {
    
    private ILaunch launch;
    private IThread[] threads;
    
    private Tefkat engine;
    private Node currentNode = null;
    private TRule currentRule = null;
    private Stack nodes = new Stack();
    private Stack trees = new Stack();
    private boolean suspended;
    private Map startCharMap = new WeakHashMap();
    private Map endCharMap = new WeakHashMap();

    int suspendCount;

    /**
     * 
     */
    public DebugTarget(ILaunch launch, Tefkat tefkat) {
        super(null);
        
        target = this;
        this.launch = launch;
        this.engine = tefkat;
        
        final DebugThread thread = new DebugThread(this);
        threads = new IThread[] {thread};
        
        engine.addTefkatListener(new TefkatListener() {
            int depth = 0;
            boolean initial = true;

            public void started() {
//                System.out.println("STARTED");
                suspendCount = 0;
                initial = true;
                fireCreationEvent();
                engine.pause();
                // breakpoint(null);
            }

            public void stopped() {
                fireTerminateEvent();
            }
            
            public void breakpoint(Term t) {
//                System.out.println("BREAKPOINT");
                suspended = true;
                thread.setStepping(true);
                fireSuspendEvent(DebugEvent.BREAKPOINT);
            }
            
            public void suspended() {
//                System.out.println("SUSPENDED");
                suspended = true;
                suspendCount++;
                thread.setStepping(true);
                if (initial) {
                    initial = false;
                    fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
                } else {
                    fireSuspendEvent(DebugEvent.STEP_END);
                }
            }
            
            public void resumed() {
                suspended = false;
                thread.setStepping(false);
                fireResumeEvent(DebugEvent.STEP_OVER);
            }

            public void info(String message) {}

            public void warning(String message) {}

            public void error(String message, Throwable cause) {}

            public void resourceLoaded(Resource res) {}

            public void transformationStarted(Transformation transformation, Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {}

            public void transformationFinished() {}

            public void evaluateRule(TRule rule, Binding context, boolean cached) {
                currentRule = rule;
            }

            public void evaluateSource(TRule rule, Binding context) {}

            public void evaluateTarget(TRule rule, Binding context) {}

            public void enterTerm(Node node) {
                currentNode = node;
                nodes.set(nodes.size()-1, node);
//                depth++;
//                indent(depth, '>');
//                System.err.print(" " + node.selectedLiteral() + " ? ");
            }

            public void exitTerm(Node node) {
                currentNode = node;
                nodes.set(nodes.size()-1, node);
//                System.err.println(node.binding());
//                indent(depth, '<');
//                System.err.println(" " + !node.isFailure());
//                depth--;
            }

            public void delayTerm(Node node) {
                currentNode = node;
                nodes.set(nodes.size()-1, node);
//                System.err.println(node.binding());
//                indent(depth, '-');
//                System.err.println(" delayed");
//                depth--;
            }
            
            private void indent(int n, char c) {
                for (int i = 0; i < n; i++) {
                    System.err.print(c);
                }
            }

            public void enterTree(Tree tree) {
                trees.push(tree);
                nodes.push(null);
            }

            public void exitTree(Tree tree) {
                Object top = trees.pop();
                if (!tree.equals(top)) {
                    System.err.println("ERROR: tree stack out of sync");
                }
                nodes.pop();
            }
 
        });
    }

    /**
     * Returns the current stack frames in the target.
     * 
     * @return the current stack frames in the target
     * @throws DebugException if unable to perform the request
     */
    protected IStackFrame[] getStackFrames() throws DebugException {
        List frames = new ArrayList();
        
        for (Node node = currentNode; node != null; ) {
            frames.add(new NodeSelectedLiteralStackFrame(threads[0], node));
            node = node.getParentNode();
        }
        frames.add(new RuleStackFrame(threads[0], currentRule));

        return (IStackFrame[]) frames.toArray(new IStackFrame[frames.size()]);
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getProcess()
     */
    public IProcess getProcess() {
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getLaunch()
     */
    public ILaunch getLaunch() {
        return launch;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
     */
    public IThread[] getThreads() throws DebugException {
        return threads;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
     */
    public boolean hasThreads() throws DebugException {
        return getThreads().length > 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getName()
     */
    public String getName() throws DebugException {
        return "Tefkat Debug";
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint)
     */
    public boolean supportsBreakpoint(IBreakpoint breakpoint) {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate() {
        return !isTerminated();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated() {
        return !engine.getRunning() || engine.getInterrupted();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException {
        engine.setInterrupted(true);
        engine.step();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
     */
    public boolean canResume() {
        return !isTerminated() && isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
     */
    public boolean canSuspend() {
        return !isTerminated() && !isSuspended();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
     */
    public boolean isSuspended() {
        return suspended;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#resume()
     */
    public void resume() throws DebugException {
        engine.resume();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
     */
    public void suspend() throws DebugException {
        engine.pause();
    }
    
    public void step() {
        engine.step();
    }

    public void stepReturn() {
        engine.stepReturn();
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
     */
    public void breakpointAdded(IBreakpoint breakpoint) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint, org.eclipse.core.resources.IMarkerDelta)
     */
    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointChanged(org.eclipse.debug.core.model.IBreakpoint, org.eclipse.core.resources.IMarkerDelta)
     */
    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
     */
    public boolean canDisconnect() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#disconnect()
     */
    public void disconnect() throws DebugException {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#isDisconnected()
     */
    public boolean isDisconnected() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#supportsStorageRetrieval()
     */
    public boolean supportsStorageRetrieval() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#getMemoryBlock(long, long)
     */
    public IMemoryBlock getMemoryBlock(long startAddress, long length)
            throws DebugException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.dstc.tefkat.model.parser.ParserListener#matched(com.dstc.tefkat.model.parser.ParserEvent)
     */
    public void matched(ParserEvent event) {
        startCharMap.put(event.getObj(), new Integer(event.getStartChar()));
        endCharMap.put(event.getObj(), new Integer(event.getEndChar()));
    }
    
    public int getStartChar(EObject obj) {
        while (obj != null) {
            // look up position
            Integer pos = (Integer) startCharMap.get(obj);
            if (pos != null) {
                return pos.intValue();
            }
            // not found so go to parent
            if (obj instanceof OverrideTerm) {
                obj = (EObject) ((OverrideTerm) obj).getNegatedTerms().get(0);
            } else {
                obj = obj.eContainer();
            }
        }
        return -1;
    }
    
    public int getEndChar(EObject obj) {
        while (obj != null) {
            // look up position
            Integer pos = (Integer) endCharMap.get(obj);
            if (pos != null) {
                return pos.intValue();
            }
            // not found so go to parent
            if (obj instanceof OverrideTerm) {
                obj = (EObject) ((OverrideTerm) obj).getNegatedTerms().get(0);
            } else {
                obj = obj.eContainer();
            }
        }
        return -1;
    }

}
