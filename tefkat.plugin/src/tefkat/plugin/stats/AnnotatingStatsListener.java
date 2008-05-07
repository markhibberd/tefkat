package tefkat.plugin.stats;

import tefkat.engine.Binding;
import tefkat.model.Extent;
import tefkat.model.Transformation;
import tefkat.plugin.TefkatModelEditor.TefkatTextEditor;
import tefkat.plugin.dom.TefkatDOM;
import tefkat.plugin.stats.TermStats.TermStat;

public class AnnotatingStatsListener extends TefkatStatisticsListener {
    private static boolean enabled = false;
    private TefkatTextEditor textEditor;

    // set-up in transformationStarted / torn-down in transformationFinished
    // this does not allow for concurrent transformations, but that is consistent
    // with the engine/plugin design
    private AnnotatedDocument annotatedDocument;
    private Transformation transformation;

    public AnnotatingStatsListener(TefkatTextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public void transformationStarted(Transformation transformation,
            Extent[] srcs, Extent[] tgts, Extent trace, Binding context) {
        this.transformation = transformation;
        if (this.annotatedDocument != null) annotatedDocument.clear();
        this.annotatedDocument = this.textEditor.createAnnotatedDocument(TefkatDOM.getInstance(textEditor));
        annotatedDocument.clear();
    }

    public void transformationFinished() {
        computeAllStats();
        printAllStats();
        for (TermStat stat : this.termStats.termStats.values()) {
            this.annotatedDocument.annotate(stat.term(), stat.type(), "success rate: " + stat.percent() + "%");
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        AnnotatingStatsListener.enabled = enabled;
    }
}
