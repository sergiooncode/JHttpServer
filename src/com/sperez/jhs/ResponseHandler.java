package com.sperez.jhs;

public class ResponseHandler {
    private ReaderWriter writer;
    private Response responseObject;
    private String statusLine;

    public void setupOutputWriter(ReaderWriter writer) {
        this.writer = writer;
    }

    public void handle(Request requestObject) {
        createResponseObject(requestObject);
        sendResponse();
    }

    protected void createResponseObject(Request request) {
        if(request.getRequestMethod().equals("GET")) {
            if(request.getRequestedResource().equals("/")) {
                statusLine = "HTTP/1.1 200 OK";
            }

            if(request.getRequestedResource().equals("/foobar")) {
                statusLine = "HTTP/1.1 404 Not Found";
            }
        }

        responseObject = new Response(statusLine);
        statusLine = "";
    }

    protected Response getResponseObject() {
        return responseObject;
    }

    private void sendResponse(){
        writer.writeLine(responseObject);
        writer.sendAll();
    }
}