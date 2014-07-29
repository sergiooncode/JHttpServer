package com.sperez.jhs;

import java.util.ArrayList;

public class Response {
    private String statusLine;
    private ArrayList<String> responseHeaders;
    private String responseBody;
    private byte[] binaryResponseBody;

    public Response(String statusLine, ArrayList<String> responseHeaders, String responseBody, byte[] binaryResponseBody) {
        this.statusLine = statusLine;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.binaryResponseBody = binaryResponseBody;
    }

    public String getStatusLine() {
        return statusLine;
    }
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public ArrayList<String> getResponseHeaders() {
        return responseHeaders;
    }
    public void setResponseHeader(String header) {
        responseHeaders.add(header);
    }

    public String getResponseBody() {
        return responseBody;
    }
    public void setResponseBody(String body) {
        this.responseBody = body;
    }

    public byte[] getBinaryResponseBody() {
        return binaryResponseBody;
    }
    public void setBinaryResponseBody(byte[] binaryBody) {
        this.binaryResponseBody = binaryBody;
    }
}