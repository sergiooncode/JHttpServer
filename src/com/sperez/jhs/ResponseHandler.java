package com.sperez.jhs;

public class ResponseHandler implements HandlerInterface {
    private ReaderWriter writer;
    private Response responseObject;
    private ResponseBuilder responseBuilder;
    private int port;
    private String publicDir;
    private Request requestObject;

    public ResponseHandler(int port, String publicDir) {
        this.port = port;
        this.publicDir = publicDir;
    }

    @Override
    public void setupInputOutput(ReaderWriter writer) {
        this.writer = writer;
    }

    @Override
    public void handle() {
        createResponseObject();
        sendResponse();
    }

    public Request getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Request requestObject) {
        this.requestObject = requestObject;
    }

    Response getResponseObject() {
        return responseObject;
    }

    void createResponseObject() {
        responseObject = callBuilder().buildResponse();
    }

    private ResponseBuilder callBuilder() {
        responseBuilder = new ResponseBuilder(requestObject);
        setPortAndPublicDir();
        return responseBuilder;
    }

    private void setPortAndPublicDir() {
        responseBuilder.setPort(port);
        responseBuilder.setPublicDir(publicDir);
    }

    private void sendResponse() {
        writer.writeLine(responseObject);
        writer.sendAll();
        writer.closeWriter();
    }
}