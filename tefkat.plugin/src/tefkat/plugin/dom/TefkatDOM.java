package tefkat.plugin.dom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.EObject;

import tefkat.model.parser.ParserEvent;
import tefkat.model.parser.ParserListener;
import tefkat.plugin.TefkatModelEditor.TefkatTextEditor;
import tefkat.plugin.stats.EqualsyIndexedEObject;
import tefkat.plugin.stats.TextSection;

/**
 * Initial skeleton of a more sophisticated DOM to help map CST ==> AST. See work in progress (tefkat.plugin.wip.objectmodel)
 */
public class TefkatDOM implements ParserListener {
    private static final ConcurrentHashMap<TefkatTextEditor, TefkatDOM> doc2dom = new ConcurrentHashMap<TefkatTextEditor, TefkatDOM>();
    private final List<EqualsyIndexedEObject> items = new ArrayList<EqualsyIndexedEObject>();

    public static TefkatDOM getInstance(TefkatTextEditor editor) {
        TefkatDOM dom = doc2dom.get(editor);
        if (dom != null) return dom;
        TefkatDOM newDom = new TefkatDOM(editor);
        dom = doc2dom.putIfAbsent(editor, newDom);
        return dom != null ? dom : newDom;
    }

    private TefkatTextEditor editor;
    private TefkatDOM(TefkatTextEditor editor) {
        this.editor = editor;
    }

    public void matched(ParserEvent e) {
        EObject obj = (EObject) e.getObj();
        int start = e.getStartChar();
        int end = e.getEndChar();
        items.add(new EqualsyIndexedEObject(obj, start, end));
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
        return null;
    }

    public void clear() {
        items.clear();
    }


}
