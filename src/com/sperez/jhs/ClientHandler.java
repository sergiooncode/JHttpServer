package com.sperez.jhs;

public class ClientHandler {
    private ConnectionHandler connectionHandler;
    private ReaderWriter readerWriter;
    private RequestHandler requestHandler;
    private ResponseHandler responseHandler;
    private Request request;


    public ClientHandler(ConnectionHandler connectionHandler, int port, String publicDir) {
        this.connectionHandler = connectionHandler;
        this.requestHandler = new RequestHandler();
        this.responseHandler = new ResponseHandler(port, publicDir);
    }

    private void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionHandler);
        requestHandler.setupInputReader(readerWriter);
        responseHandler.setupOutputWriter(readerWriter);
    }

    public void handle() {
        try {
            setupInputOutput();
            requestHandler.handle();
            request = requestHandler.getRequestObject();
            responseHandler.setRequestObject(request);
            responseHandler.handle();
        }

        finally {
            readerWriter.closeWriter();
            readerWriter.closeReader();
            connectionHandler.disconnect();
        }
    }
}