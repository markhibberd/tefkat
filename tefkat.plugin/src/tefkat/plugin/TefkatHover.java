package tefkat.plugin;

import java.util.Iterator;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;

@SuppressWarnings("unchecked") // raw iterators
public class TefkatHover implements IAnnotationHover, ITextHover {
    public String getHoverInfo(ISourceViewer viewer, int line) {
        IAnnotationModel model = viewer.getAnnotationModel();
        IDocument doc = viewer.getDocument();
        if (line > doc.getNumberOfLines()) return null;
        int offset = getLineOffset(doc, line);
        int length = getLineLength(doc, line);
        for (Iterator<Annotation> iter = model.getAnnotationIterator(); iter.hasNext();) {
            Annotation a = iter.next();
            if (model.getPosition(a).overlapsWith(offset, length)) return a.getText();
        }
        return null;
    }

    public String getHoverInfo(ITextViewer viewer, IRegion region) {
        if (!(viewer instanceof ISourceViewer)) return null;
        ISourceViewer sv = (ISourceViewer) viewer;
        int offset = region.getOffset();
        IAnnotationModel model = sv.getAnnotationModel();
        for (Iterator<Annotation> iter = model.getAnnotationIterator(); iter.hasNext();) {
            Annotation a = iter.next();
            Position pos = model.getPosition(a);
            if (pos.includes(offset))  return a.getText();
        }
        return null;
    }

    // TODO-MH this is naive implementation to support displaying annotation hovers
    //         at this stage i don't intend to implement anything more complex, but
    //         this is where the ranges need to be defined if it is to be done.
    public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
        return new Region(offset, 0);
    }

    private int getLineLength(IDocument doc, int line) {
        try {
            return doc.getLineLength(line);
        } catch (BadLocationException e) {
            return 0;
        }
    }

    private int getLineOffset(IDocument doc, int line) {
        try {
            return doc.getLineOffset(line);
        } catch (BadLocationException e) {
            return 0;
        }
    }
}
