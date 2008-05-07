package tde.tools;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;

public class ResourceUtil {
    private static final IWorkspaceRoot WORKSPACE_ROOT = ResourcesPlugin
            .getWorkspace().getRoot();

    private static final String PLATFORM_RESOURCE = "resource"; //$NON-NLS-1$

    public static final String PLATFORM_PROTOCOL = "platform"; //$NON-NLS-1$

    public static IFile getFile(ResourceSet set, URI uri) {
        IFile file = getPlatformFile(uri);
        if (file == null) {
            if (set != null) {
                URIConverter converter = set.getURIConverter();
                URI convertedUri = converter.normalize(uri);
                if (!uri.equals(convertedUri))
                    return getPlatformFile(convertedUri);
            }
        }
        return file;
    }

    public static IFile getPlatformFile(URI uri) {
        if (isPlatformResourceURI(uri)) {
            String fileString = URI.decode(uri.path());
            fileString = fileString.substring(PLATFORM_RESOURCE.length() + 1);
            IPath filePath = new Path(fileString);
            IResource resource = WORKSPACE_ROOT.findMember(filePath);
            if (resource == null)
                return WORKSPACE_ROOT.getFile(filePath);
            else if (resource.getType() == IResource.FILE)
                return (IFile) resource;
        }
        return null;
    }

    /**
     * Does the passed URI have the form platform:/resource/... ?
     *
     * @param uri
     * @return <code>true</code> if it is a platform resource protocol.
     *
     * @since 1.0.0
     */
    public static boolean isPlatformResourceURI(URI uri) {
        return PLATFORM_PROTOCOL.equals(uri.scheme())
                && PLATFORM_RESOURCE.equals(uri.segment(0));
    }

    public static IResource getFile(ResourceSet resourceSet, String location) {
        URI locationUri = URI.createURI(location);
        return getFile(resourceSet, locationUri);
    }
}
