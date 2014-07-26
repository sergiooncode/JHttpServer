package com.sperez.jhs;

import java.util.ArrayList;

public class RequestHandler {
    private ReaderWriter reader;
    private String rawRequest;
    private RequestParser parser;
    private Request requestObject;

    public void handle() {
        readRequest();
        createRequestObject();
    }

    public void createRequestObject() {
        createParser();
        parser.parseRequest();
        requestObject = new Request(method(), resource(), protocol(), headers(), body());
    }

    public Request getRequestObject() {
        return requestObject;
    }

    public void setupInputReader(ReaderWriter reader) {
        this.reader = reader;
    }

    public void readRequest() {
        rawRequest = reader.readLine();
        System.out.println("Request: " + rawRequest);
    }

    void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    void createParser() {
        parser = new RequestParser(rawRequest);
    }

    private String body() {
        return parser.getRequestBody();
    }

    private ArrayList<String> headers() {
        return parser.getRequestHeaders();
    }

    private String protocol() {
        return parser.getRequestProtocol();
    }

    private String resource() {
        return parser.getRequestedResource();
    }

    private String method() {
        return parser.getRequestMethod();
    }
}