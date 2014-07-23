package com.sperez.jhs;

public class ClientHandler implements Runnable {
    private ConnectionHandler connectionHandler;
    private ReaderWriter readerWriter;
    private RequestHandler requestHandler;
    private ResponseHandler responseHandler;
    private Request request;

    public ClientHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.requestHandler = new RequestHandler();
        this.responseHandler = new ResponseHandler();
    }

    private void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionHandler);
        requestHandler.setupInputReader(readerWriter);
        responseHandler.setupOutputWriter(readerWriter);
    }

    public void run() {
        try {
            setupInputOutput();
            requestHandler.handle();
            request = requestHandler.getRequestObject();
            responseHandler.handle(request);
        } catch(Exception e) {
            e.printStackTrace();
        }

        finally {
            readerWriter.closeWriter();
            readerWriter.closeReader();
            connectionHandler.disconnect();
        }
    }
}