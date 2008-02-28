package tde.stats.data;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ResourceReader {
    public static URI uri(String uri) {
        return URI.createURI(uri);
    }
    public static URI resource(String resource) {
        return URI.createURI(ResourceReader.class.getResource(resource).toString());
    }
    public static Resource load(ResourceSet rs, String resource) {
        return rs.getResource(resource(resource), true);
    }
    public static Resource create(ResourceSet rs, String resource) {
        return rs.createResource(uri(resource));
    }

}
