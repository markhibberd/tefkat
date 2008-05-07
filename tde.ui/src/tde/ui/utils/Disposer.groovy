package tde.ui.utils

class Disposer {
    static void dispose(Object... targets) {
        targets.each {
            if (it?.metaClass?.respondsTo(it, "dispose")) it.dispose();
        }
    }
}