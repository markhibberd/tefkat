/*
 * Created on Apr 30, 2005
 *
 */
package tefkat.antTask;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tefkat.config.TefkatConfig.ExecutionMode;
import tefkat.config.TefkatConfig.Model;
import tefkat.config.TefkatConfig.TefkatConfigFactory;
import tefkat.config.TefkatConfig.TransformationTask;
import tefkat.engine.Tefkat;
import tefkat.model.TefkatException;

/**
 * This class implements an Ant task for running Tefkat.
 * 
 * When complete it will support specification of all parameters currently
 * supported by the config file tefkat.xml :
 * <ul>
 * <li>transformation URI</li>
 * <li>source model URIs</li>
 * <li>target model URIs</li>
 * <li>trace model URI</li>
 * <li>mode: REPLACE/UPDATE</li>
 * <li>URI maps</li>
 * </ul>
 * 
 * @author lawley
 */
public class TefkatTask extends Task {

    private TransformationTask task;
    private boolean save;
    private boolean force;
    
    /**
     * 
     */
    public TefkatTask() {
        super();
    }

    public void execute() throws BuildException {
//        super.execute();
        Tefkat engine = new Tefkat();
        try {
            engine.createTempResource("dummy", ".xmi").getContents().add(task);
            engine.transform(task, save, force);
        } catch (IOException e) {
            throw new BuildException(e);
        } catch (TefkatException e) {
            throw new BuildException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new BuildException(e);
        }
    }
    
    public void init() {
//        ResourceSet rs = new ResourceSetImpl();
//        System.err.println(rs);
//        Resource res = rs.createResource(URI.createFileURI("dummy.xml"));
//        System.err.println(res);
        task = TefkatConfigFactory.eINSTANCE.createTransformationTask();
        task.setEnabled(true);
//        res.getContents().add(task);
    }
    
    public void setSave(boolean doSave) {
        save = doSave;
    }
    
    public void setForce(boolean doForce) {
        force = doForce;
    }
    
    public void setTransformation(String str) {
        System.out.println("setTransformation: " + str);
        Model model = TefkatConfigFactory.eINSTANCE.createModel();
        model.setLocationUri(str);
        task.setTransformation(model);
    }
    
    public void setTraceURI(String str) {
        System.out.println("setTraceURI: " + str);
        Model model = TefkatConfigFactory.eINSTANCE.createModel();
        model.setLocationUri(str);
        task.setTrace(model);
    }
    
    public void setMode(Mode mode) {
        System.out.println("setMode: " + mode.getValue() + "\t" + ExecutionMode.get(mode.getValue()));
        task.setMode(ExecutionMode.get(mode.getValue()));
    }
    
    public void addSrc(Location location) {
        System.out.println("addSrc: " + location + "\t" + location.getClass().getSimpleName());
        task.getSourceModels().add(location.getModel());
    }
    
    public void addTgt(Location location) {
        System.out.println("addTgt: " + location);
        task.getTargetModels().add(location.getModel());
    }
    
    public static class Mode extends EnumeratedAttribute {
        public String[] getValues() {
            return new String[] {"REPLACE", "UPDATE"};
        }
    }
    
    public static class Location {
        
        private Model model = TefkatConfigFactory.eINSTANCE.createModel();
        
        public Location() {
        }
        
        public void setURI(String str) {
            System.out.println("setUri: " + str);
            model.setLocationUri(str);
        }
        
        Model getModel() {
            return model;
        }
    }
    
}
