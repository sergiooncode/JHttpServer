package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private ConnectionHandler connectionHandler;
    private ReaderWriter readerWriter;
    private ResponseHandler responseHandler;
    private RequestHandler requestHandler;
    private Request request;

    public SimpleServer (ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    private void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionHandler);
        requestHandler.setupInputReader(readerWriter);
        responseHandler.setupOutputWriter(readerWriter);
    }

    public void setupResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void setupRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void handleClient() {
        requestHandler.handle();
        request = requestHandler.getRequestObject();
        responseHandler.handle(request);
    }

    private void connect() {
        connectionHandler.connect();
    }

    private void disconnect() {
        connectionHandler.disconnect();
    }

    public void run (){
        keepGoing = true;

        while(keepGoing()){
            connect();
            setupInputOutput();
            handleClient();
            disconnect();
        }
    }

    private boolean keepGoing() {
        return keepGoing;
    }
}