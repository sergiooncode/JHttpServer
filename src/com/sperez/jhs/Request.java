package com.sperez.jhs;

import java.util.ArrayList;

public class Request {
    private String requestLine;
    private String requestMethod;
    private String requestBody;
    private String requestedResource;
    private String protocol;
    private ArrayList<String> requestHeaders;

    public Request(String requestMethod, String requestedResource, String protocol, ArrayList<String> requestHeaders, String requestBody) {
        this.requestMethod = requestMethod;
        this.requestedResource = requestedResource;
        this.protocol = protocol;
        this.requestLine = requestMethod + " " + requestedResource + " " + protocol;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    void addRequestHeader(String header) {
        requestHeaders.add(header);
    }

    public String getRequestedResource() {
        return requestedResource;
    }

    public boolean isGet() {
        return requestMethod.equals("GET");
    }

    public boolean isPut() {
        return requestMethod.equals("PUT");
    }

    public boolean isPost() {
        return requestMethod.equals("POST");
    }

    public boolean isOptions() {
        return requestMethod.equals("OPTIONS");
    }

    public boolean isHead() {
        return requestMethod.equals("HEAD");
    }

    public boolean isPatch() {
        return requestMethod.equals("PATCH");
    }

    public String getHeaderIfThereIs(String headerString) {
        String completeHeader = "";
        for(int i=0; i < requestHeaders.size(); i++) {
            if(requestHeaders.get(i).contains(headerString)) {
                completeHeader = requestHeaders.get(i);
            }
        }
        return completeHeader;
    }
}