package com.sperez.jhs;

public class RequestHandler {
    private ReaderWriter reader;
    private String rawRequest;
    private Request requestObject;
    private String requestLine;
    private String requestBody;

    protected void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    protected String getRequestLine() {
        return requestLine;
    }

    protected String getRequestBody() {
        return requestBody;
    }

    public void parseRequest() {
        String[] splittedRequestLinePlusHeadersAndBody = rawRequest.split("\r\n\r\n");

        String requestLinePlusHeaders = splittedRequestLinePlusHeadersAndBody[0];
        requestLine = requestLinePlusHeaders;

        if (splittedRequestLinePlusHeadersAndBody.length > 1){
            requestBody = splittedRequestLinePlusHeadersAndBody[1];
        }
    }
}