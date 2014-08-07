package com.sperez.jhs;

public class ClientHandler implements Runnable {
    private SocketInterface connectionSocket;
    private ReaderWriter readerWriter;
    private HandlerInterface requestHandler, responseHandler;
    private Request request;
    private ResponseBuilder responseBuilder;
    private LogicInterface logic;

    public ClientHandler(SocketInterface connectionSocket, int port, String publicDir) {
        this.connectionSocket = connectionSocket;
        this.requestHandler = new RequestHandler();
        this.logic = new RoutingLogic();
        this.responseBuilder = new ResponseBuilder(logic);
        this.responseHandler = new ResponseHandler(port, publicDir, responseBuilder);
    }

    public void run() {
        try {
            setupInputOutput();
            handleRequest();
            handleResponse();
        }
        finally {
            readerWriter.closeWriter();
            readerWriter.closeReader();
            connectionSocket.disconnect();
        }
    }

    private void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionSocket);
        requestHandler.setupInputOutput(readerWriter);
        responseHandler.setupInputOutput(readerWriter);
    }

    private void handleRequest() {
        requestHandler.handle();
        request = requestHandler.getRequestObject();
    }

    private void handleResponse() {
        responseHandler.setRequestObject(request);
        responseHandler.handle();
    }
}