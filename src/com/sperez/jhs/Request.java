package com.sperez.jhs;

public class Request {
    private String requestMethod;
    private String protocol;
    private String requestBody;
    private String requestedResource;

    public Request(String requestMethod, String requestedResource, String protocol, String requestBody) {
        this.requestMethod = requestMethod;
        this.requestedResource = requestedResource;
        this.protocol = protocol;
        this.requestBody = requestBody;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getRequestedResource() {
        return requestedResource;
    }
}