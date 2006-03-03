package com.dstc.tefkat.engine;

public class ResolutionException extends Exception {
    transient private Node node;

    public ResolutionException(Node node, String message, Exception exception) {
        super(message + (null == node ? "" : " -- " + node), exception);
        this.node = node;
    }

    public ResolutionException(Node node, String message) {
        super(message + (null == node ? "" : " -- " + node));
        this.node = node;
    }

    public Node getNode() {return node;}
}
