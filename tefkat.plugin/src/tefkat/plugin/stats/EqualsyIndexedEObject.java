package tefkat.plugin.stats;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class EqualsyIndexedEObject {
    private final EObject e;
    private final TextSection idx;
    public EqualsyIndexedEObject(EObject e, int start, int end) {
        this.e = e;
        this.idx = new TextSection(start, end);
    }
    public TextSection index() { return this.idx; }
    public boolean equalsy(EObject other) {
        return EcoreUtil.equals(e, other);
    }
}
