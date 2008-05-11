package tefkat.engine.runtime;

public class TefkatException extends Exception {
    
    public TefkatException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public TefkatException(String message) {
        super(message);
    }
    
    public TefkatException(Throwable cause) {
        super(cause);
    }
    
}
