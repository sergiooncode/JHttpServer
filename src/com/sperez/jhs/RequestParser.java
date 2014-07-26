package com.sperez.jhs;

import java.util.ArrayList;

class RequestParser  {
    final private String CRLF = "\r\n\r\n";
    final private String DOUBLE_CRLF = "\r\n\r\n\r\n\r\n";
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
        parseRequestLinePlusHeadersAndBody();
        parseRequestLineAndHeaders();
        parseRequestLine();
    }

    void parseRequestLinePlusHeadersAndBody() {
        String[] splittedRawRequest = rawRequest.split(DOUBLE_CRLF);
        if(isRawRequestEmpty(splittedRawRequest)) {
            requestLineAndHeaders = "";
            requestBody = "";
        } else if(hasOnlyStringBefore2xCRLF(splittedRawRequest)) {
            requestLineAndHeaders = rawRequest.split(DOUBLE_CRLF)[0];
            requestBody = "";
        } else if(hasStringBeforeAndAfter2xCRLF(splittedRawRequest)) {
            requestLineAndHeaders = rawRequest.split(DOUBLE_CRLF)[0];
            requestBody = rawRequest.split(DOUBLE_CRLF)[1];
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

    private boolean hasStringBeforeAndAfter2xCRLF(String[] stringArray) {
        return stringArray.length > 1;
    }

    private boolean hasOnlyStringBefore2xCRLF(String[] stringArray) {
        return stringArray.length == 1;
    }

    private boolean isRawRequestEmpty(String[] stringArray) {
        return rawRequest.equals("") || stringArray.length == 0;
    }

    String getRequestLineAndHeaders() {
        return requestLineAndHeaders;
    }

    ArrayList<String> getRequestHeaders() {return requestHeaders;}

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