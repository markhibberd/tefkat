package tefkat.plugin.stats;

import static tefkat.plugin.stats.Validator.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import tefkat.model.AndTerm;
import tefkat.model.NotTerm;
import tefkat.model.Term;
import tefkat.plugin.dom.TefkatDOM;

public class AnnotatedDocument {
    private final IDocument doc;
    private final IAnnotationModel model;
    private final TefkatDOM dom;
    private final List<Annotation> annotations = new ArrayList<Annotation>();
    private final Map<Position,AnnotatedTerm> annotationPositions = new HashMap<Position,AnnotatedTerm>();

    public AnnotatedDocument(IDocument doc, IDocumentProvider prov, IEditorInput editorInput, TefkatDOM dom) {
        notNull(doc, prov, editorInput, dom);
        this.doc = doc;
        this.model = prov.getAnnotationModel(editorInput);
        this.dom = dom;
    }

    public void clear() {
        for (Annotation a : annotations) {
            model.removeAnnotation(a);
        }
        annotations.clear();
    }

    public void annotate(Term t, AnnotationType type, String msg) {
        if (!AnnotatingStatsListener.isEnabled()) return;
        TextSection area = dom.find(t);
        if (area == null) return; // FIXME MH this is dodge, but not all terms actually exist... mmmm
        area = refine(t, area);
        annotate(t, type, area, msg);
    }

    private void annotate(Term t, AnnotationType type, TextSection area, String msg) {
        Annotation a = new Annotation(type.id, false, msg);
        annotations.add(a);
        a.setText(msg);
        Position position = area.toPosition();
        Position overlap = overlap(position);
//        if (overlap != null) {
//            System.err.println("Overlap has occurred, " + t + "  -- overlaps with --  " + annotationPositions.get(overlap).term);
//            return;
//        }
        annotationPositions.put(position, new AnnotatedTerm(a, t));
        model.addAnnotation(a, position);
    }

    private Position overlap(Position newy) {
        for (Position old : annotationPositions.keySet()) {
            if (old.overlapsWith(newy.offset, newy.length)) return old;
        }
        return null;
    }

    private TextSection refine(Term t, TextSection orig) {
        try {
            // FIXME pattern match to refine selection
            if (t instanceof AndTerm) {
            } else if (t instanceof NotTerm) {
                if (doc.get(orig.start, 3).toLowerCase().equals("not")) {
                    return new TextSection(orig.start, orig.start + 3);
                }
            }
        } catch (BadLocationException e) {
            // fall through, default behavior is good
        }
        return orig;
    }


    private static class AnnotatedTerm {
        private final Annotation annotation;
        private final Term term;
        private AnnotatedTerm(Annotation a, Term t) {
            this.annotation = a;
            this.term = t;
        }
    }

}
