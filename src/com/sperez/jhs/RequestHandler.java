package com.sperez.jhs;

public class RequestHandler {
    private ReaderWriter reader;
    private String rawRequest;
    private Request requestObject;
    private String requestLine;

    protected void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    protected String getRequestLine() {
        return requestLine;
    }
}