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

    public ArrayList<String> getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public byte[] getBinaryResponseBody() {
        return binaryResponseBody;
    }
}