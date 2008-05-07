/*
 * Copyright (c) 2004- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 *
 *
 */

package tefkat.plugin;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * @author lawley
 */
public class TefkatModelSourceViewerConfiguration extends SourceViewerConfiguration {
    private TefkatModelRuleBasedScanner scanner;
    private TefkatHover hover = new TefkatHover();

    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) { return hover; }
    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) { return hover; }
    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) { return hover; }

    protected TefkatModelRuleBasedScanner getTagScanner() {
        if (scanner == null) {
            scanner = new TefkatModelRuleBasedScanner();
        }
        return scanner;
    }

    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, TefkatPartitionScanner.MULTILINE_COMMENT };
    }

    public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
        return TefkatPlugin.TEFKAT_PARTITIONING;
    }

    public int getTabWidth(ISourceViewer sourceViewer) {
        return 8;   // godammit, a TAB is 8 spaces!
    }

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();
        reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

        DefaultDamagerRepairer dr;

        dr = new DefaultDamagerRepairer(getTagScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        dr = new DefaultDamagerRepairer(new SingleTokenScanner(new TextAttribute(TefkatModelRuleBasedScanner.MULTILINE_COMMENT_COLOR)));
        reconciler.setDamager(dr, TefkatPartitionScanner.MULTILINE_COMMENT);
        reconciler.setRepairer(dr, TefkatPartitionScanner.MULTILINE_COMMENT);

        return reconciler;
    }

    static class SingleTokenScanner extends BufferedRuleBasedScanner {
        public SingleTokenScanner(TextAttribute attribute) {
            setDefaultReturnToken(new Token(attribute));
        }
    }
}
