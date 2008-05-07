package tefkat.plugin.stats;

import static tefkat.plugin.stats.Validator.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import tefkat.model.TRule;
import tefkat.plugin.dom.TefkatDOM;

public class AnnotatedCreationDocument {
    private final IDocument doc;
    private final IAnnotationModel model;
    private final TefkatDOM dom;
    private final List<Annotation> annotations = new ArrayList<Annotation>();
    private final Map<Position,AnnotatedRule> annotationPositions = new HashMap<Position,AnnotatedRule>();

    public AnnotatedCreationDocument(IDocument doc, IDocumentProvider prov, IEditorInput editorInput, TefkatDOM dom) {
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

    private static class AnnotatedRule {
        private final Annotation annotation;
        private final TRule rule;
        private AnnotatedRule(Annotation a, TRule r) {
            this.annotation = a;
            this.rule = r;
        }
    }

    public void addCreationHover(TRule rule, EObject key, double d) {
        if (!ObjectCreationListener.isEnabled()) return;
        TextSection area = dom.find(rule);
        if (area == null) {
            System.err.println("could not find rule");
            throw new RuntimeException("could not find rule");
        }
        area = refine(area);
        annotate(rule, area, "Created " + d + "% of object[" + EObjectUtil.buildToString(key) + "]");
    }

    private void annotate(TRule rule, TextSection area, String msg) {
        Annotation a = new Annotation("tefkat.plugin.stats.creationrate", false, msg);
        annotations.add(a);
        a.setText(msg);
        Position position = area.toPosition();
        annotationPositions.put(position, new AnnotatedRule(a, rule));
        model.addAnnotation(a, position);
    }

    private TextSection refine(TextSection orig) {
        return new TextSection(orig.start, orig.start + 4);
    }

}
