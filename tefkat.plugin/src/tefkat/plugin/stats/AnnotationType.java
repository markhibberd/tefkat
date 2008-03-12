package tefkat.plugin.stats;

public enum AnnotationType {
    ALWAYS("tefkat.plugin.stats.always"),
    NEVER("tefkat.plugin.stats.never"),
    SOMETIMES("tefkat.plugin.stats.sometimes");

    public final String id;
    private AnnotationType(String id) { this.id = id; }
}
