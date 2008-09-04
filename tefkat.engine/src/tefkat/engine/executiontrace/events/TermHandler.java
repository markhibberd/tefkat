package tefkat.engine.executiontrace.events;

import tefkat.engine.Node;
import tefkat.engine.events.EventContext;
import tefkat.engine.events.EventHandler;
import tefkat.model.Term;

public class TermHandler implements EventHandler {
    private final ExecutionTrace trace;

    public TermHandler(ExecutionTrace trace) {
        this.trace = trace;
    }

    public void handle(EventContext context, Class<?> type) {
        Node node = context.resolve(Node.class);
        Term term = node.selectedLiteral();
        String result = node.isSuccess() ? "Success" :
                        node.isFailure() ? "Failure" :
                                           "Non-Leaf";
        trace.crude("Evaluated node: " + node);
        trace.crude("     with term: " + term);
        trace.crude("        result: " + result);

        trace.trace("Term evaluation", "Result: " + result, term);
    }
}
