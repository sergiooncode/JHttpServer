package com.sperez.jhs;

public class RequestHandler {
    private ReaderWriter reader;
    private String rawRequest;
    private Request requestObject;
    private String requestLine;
    private String requestBody;
    private String requestMethod;
    private String requestedResource;
    private String protocol;

    protected void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    public void createRequestObject() {
        parseRequest();
        requestObject = new Request(requestMethod, requestedResource, protocol, requestBody);
    }

    public Request getRequestObject() {
        return requestObject;
    }

    public void setupInputReader(ReaderWriter reader) {
        this.reader = reader;
    }

    protected String getRequestLine() {
        return requestLine;
    }

    protected String getRequestBody() {
        return requestBody;
    }

    private void parseRequestLine() {
        String[] splittedRequestLine = requestLine.split(" ");
        requestMethod = splittedRequestLine[0];
        requestedResource = splittedRequestLine[1];
        protocol = splittedRequestLine[2];
    }

    protected void parseRequest() {
        if(!rawRequest.equals("")) {
            String[] splittedRequestLinePlusHeadersAndBody;
            splittedRequestLinePlusHeadersAndBody = rawRequest.split("\r\n\r\n");

            String requestLinePlusHeaders;
            requestLinePlusHeaders = splittedRequestLinePlusHeadersAndBody[0];
            requestLine = requestLinePlusHeaders;

            parseRequestLine();

            if (splittedRequestLinePlusHeadersAndBody.length > 1) {
                requestBody = splittedRequestLinePlusHeadersAndBody[1];
            }
        } else {
            requestLine = "";
        }
    }

    public void readRequest() {
        rawRequest = reader.readLine();
        System.out.println("Request: " + rawRequest);
    }
}