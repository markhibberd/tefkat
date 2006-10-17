package tefkat.model;

import java.util.List;

public class StratificationException extends TefkatException {
    
    List[] strata;

    public StratificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public StratificationException(String message) {
        super(message);
    }

    public StratificationException(Throwable cause) {
        super(cause);
    }

    public StratificationException(List[] strata, String message) {
        super(message);
        this.strata = strata;
    }
    
    public List[] getStrata() {
        return strata;
    }

}
