package tefkat.plugin.stats;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

// FIXME MH: when I get time this could be replaced with a EObjectKeyMap, but for the
//           moment I don't care about the linear search performance hit
public class IndexedEObjectLookup {
    private final List<EqualsyIndexedEObject> items = new ArrayList<EqualsyIndexedEObject>();

    public void add(EObject e, int start, int end) {
        items.add(new EqualsyIndexedEObject(e, start, end));
    }

    public TextSection find(EObject e) {
        // FIXME MH: START nasty hack to ignore  the whole rule thing which always passes
        String string = e.toString();
        if ((string.startsWith("EXACT") || string.startsWith("(EXACT")) && string.contains("&&")) {
            return null;
        }
        // FIXME MH: END nasty hack

        for (EqualsyIndexedEObject item : items) {
            if (item.equalsy(e)) return item.index();
        }
        System.err.println("Could not find term: " + e);
//        Term t = (Term) e;
//        for (Iterator<EObject> iter = t.eAllContents(); iter.hasNext();) {
//            EObject o = iter.next();
//            System.err.println(o);
//            System.err.println(o.getClass());
//            if (o instanceof VarUse) {
//                System.err.println(((VarUse) o).getVar());
//            }
//        }
//        System.err.println(t.getCompoundTerm());
//        System.err.println(t.getContext());
//        System.err.println("&&&&&&&&&&&&&7");
        return null;
    }

    public void clear() {
        items.clear();
    }
}
