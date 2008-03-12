package tefkat.plugin.stats;

import java.net.URI;

import org.eclipse.emf.common.CommonPlugin;

import tefkat.engine.Binding;
import tefkat.model.Extent;
import tefkat.model.Transformation;
import tefkat.plugin.TefkatModelEditor.TefkatTextEditor;
import tefkat.plugin.stats.TermStats.TermStat;

public class AnnotatingStatsListener extends TefkatStatisticsListener {
    private URI file;
    private TefkatTextEditor textEditor;

    // set-up in transformationStarted / torn-down in transformationFinished
    // this does not allow for concurrent transformations, but that is consistent
    // with the engine/plugin design
    private AnnotatedDocument annotatedDocument;
    private Transformation transformation;

    public AnnotatingStatsListener(TefkatTextEditor textEditor, URI uri) {
        this.file = uri;
        this.textEditor = textEditor;
    }

    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        if (!associatedResource(transformation)) return;
        System.out.println("Starting transformation...");
        this.transformation = transformation;
        this.annotatedDocument = this.textEditor.createAnnotatedDocument();
    }

    public void transformationFinished() { /* nothing*/ }
    public void transformationFinished(Transformation transformation) {
        if (!associatedResource(transformation)) return;
        System.out.println("Finished transformation...");
        computeAllStats();
        printAllStats();
        for (TermStat stat : this.termStats.termStats.values()) {
            this.annotatedDocument.annotate(stat.term(), stat.type());
        }
    }

    private boolean associatedResource(Transformation transformation) {
        org.eclipse.emf.common.util.URI resouce = transformation.eResource().getURI();
        String fileURIString = CommonPlugin.asLocalURI(resouce).toString();
        URI uri = URI.create(fileURIString);
        return file.equals(uri);
    }
}
