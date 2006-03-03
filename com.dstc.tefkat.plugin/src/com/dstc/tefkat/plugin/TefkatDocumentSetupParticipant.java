/*
 * Created on 28/01/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.DefaultPartitioner;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TefkatDocumentSetupParticipant implements IDocumentSetupParticipant {

    /* (non-Javadoc)
     * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
     */
    public void setup(IDocument document) {
        if (document instanceof IDocumentExtension3) {
            IDocumentExtension3 extension3 = (IDocumentExtension3) document;
            IDocumentPartitioner partitioner = new DefaultPartitioner(TefkatPlugin.getPlugin().getTefkatPartitionScanner(), TefkatPartitionScanner.TEFKAT_PARTITION_TYPES);
            extension3.setDocumentPartitioner(TefkatPlugin.TEFKAT_PARTITIONING, partitioner);
            partitioner.connect(document);
        }
    }

}
