package tefkat.plugin.stats;

import org.eclipse.jface.text.Position;

class TextSection {
    final int start;
    final int end;
    public TextSection(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public Position toPosition() {
        return new Position(start, end - start);
    }
}
