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
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getRequestedResource() {
        return requestedResource;
    }

    public ArrayList<String> getRequestHeaders() {return requestHeaders;}
}