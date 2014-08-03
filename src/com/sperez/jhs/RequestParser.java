package com.sperez.jhs;

import java.util.ArrayList;

class RequestParser  {
    final private String CRLF = "\r\n";
    final private String DOUBLE_CRLF = "\r\n\r\n";
    private String requestLineAndHeaders;
    private String requestLine;
    private String requestBody;
    private String requestMethod;
    private String requestedResource;
    private String protocol;
    private String rawRequest;
    private ArrayList<String> requestHeaders = new ArrayList<String>();

    public RequestParser(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    void parseRequest() {
        try {
            parseRequestLinePlusHeadersAndBody();
            parseRequestLineAndHeaders();
            parseRequestLine();
        } catch(Exception e) {
            rawRequest = "GET / HTTP/1.1" + DOUBLE_CRLF;
        }
    }

    void parseRequestLinePlusHeadersAndBody() {
        String[] splittedRawRequest = rawRequest.split(DOUBLE_CRLF);
        parseWhenOnlyLineAndHeaders(splittedRawRequest);
        parseWhenIsCompleteRequest(splittedRawRequest);
    }

    private void parseWhenIsCompleteRequest(String[] stringArray) {
        if(isCompleteRequest(stringArray)) {
            requestLineAndHeaders = rawRequest.split(DOUBLE_CRLF)[0];
            requestBody = rawRequest.split(DOUBLE_CRLF)[1];
        }
    }


    private void parseWhenOnlyLineAndHeaders(String[] stringArray){
        if(hasOnlyRequestLineAndHeaders(stringArray)) {
            requestLineAndHeaders = rawRequest.split(DOUBLE_CRLF)[0];
            requestBody = "";
        }
    }

    void parseRequestLineAndHeaders() {
        String[] splittedRequestLineAndHeaders = requestLineAndHeaders.split(CRLF);
        requestLine = splittedRequestLineAndHeaders[0];
        for(int i=1; i < splittedRequestLineAndHeaders.length; i++) {
            requestHeaders.add(splittedRequestLineAndHeaders[i]);
        }
    }

    void parseRequestLine() {
        String[] splittedRequestLine = requestLine.split(" ");
        requestMethod = splittedRequestLine[0];
        requestedResource = splittedRequestLine[1];
        protocol = splittedRequestLine[2];
    }

    private boolean isCompleteRequest(String[] stringArray) {
        return stringArray.length > 1;
    }

    private boolean hasOnlyRequestLineAndHeaders(String[] stringArray) {
        return stringArray.length == 1;
    }

    String getRequestLineAndHeaders() {
        return requestLineAndHeaders;
    }

    ArrayList<String> getRequestHeaders() {
        return requestHeaders;
    }

    String getRequestLine() {
        return requestLine;
    }

    String getRequestBody() {
        return requestBody;
    }

    String getRequestMethod() {
        return requestMethod;
    }

    String getRequestedResource() {
        return requestedResource;
    }

    String getRequestProtocol() {
        return protocol;
    }
}