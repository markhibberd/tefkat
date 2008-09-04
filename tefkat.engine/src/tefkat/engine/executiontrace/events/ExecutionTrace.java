package tefkat.engine.executiontrace.events;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import tefkat.engine.executiontrace.model.ExecutiontraceFactory;
import tefkat.engine.executiontrace.model.Trace;

public class ExecutionTrace {
    private final Resource resource;
    public ExecutionTrace(ResourceSet resources) {
        this.resource = resources.createResource(URI.createFileURI("/tmp/execution-trace.xmi"));
    }

    public void trace(String type, EObject... refs) {
        trace(type, null, refs);
    }

    public void trace(String type, String stuff, EObject... refs) {
        Trace trace = ExecutiontraceFactory.eINSTANCE.createTrace();
        resource.getContents().add(trace);
        if (stuff != null) trace.setStuff(stuff);
        trace.setType(type);
        EList<EObject> target = trace.getRefs();
        for (EObject ref : refs) if (ref != null) target.add(ref);
    }

    public void save() {
        try {
            resource.save(new HashMap<Object, Object>());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void crude(String message) {
        System.out.println(message);
    }

}
