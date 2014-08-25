package com.sperez.jhs;

public class ResponseHandler implements ResponseHandlerInterface {
    private WriterInterface writer;
    private Response responseObject;
    private ResponseBuilder builder;
    private int port;
    private String publicDir;
    private Request requestObject;

    public ResponseHandler(int port, String publicDir, ResponseBuilder builder) {
        this.port = port;
        this.publicDir = publicDir;
        this.builder = builder;
    }

    public void setupOutput(WriterInterface writer) {
        this.writer = writer;
    }

    @Override
    public void handle() {
        createResponseObject();
        sendResponse();
    }

    Request getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Request requestObject) {
        builder.setRequestObject(requestObject);
    }

    Response getResponseObject() {
        return responseObject;
    }

    void createResponseObject() {
        setPortAndPublicDir();
        responseObject = builder.buildResponse();
    }

    private void setPortAndPublicDir() {
        builder.setPortAndPublicDir(port, publicDir);
    }

    private void sendResponse() {
        writer.writeMessage(responseObject);
        writer.sendAll();
        writer.close();
    }
}

//def status_message code
//        {
//        '200' => 'OK',
//        '404' => 'Not Found',
//        '500' => 'Server Error'
//        }[code]
//        end
//
//        def handle request
//        {
//        'POST' => PostHandler.new,
//        'PUT' => PutHandler.new,
//        'GET' => GetHandler.new,
//        }[request.method].handle
//        end