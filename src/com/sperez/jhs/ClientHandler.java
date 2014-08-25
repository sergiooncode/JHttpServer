package com.sperez.jhs;

public class ClientHandler implements Runnable {
    private SocketInterface connectionSocket;
    private WriterInterface writer;
    private ReaderInterface reader;
    private RequestHandlerInterface requestHandler;
    private ResponseHandlerInterface responseHandler;
    private Request request;

    public ClientHandler(SocketInterface connectionSocket, int port, String publicDir) {
        this.connectionSocket = connectionSocket;
        this.requestHandler = new RequestHandler();
        LogicInterface logic = new RoutingLogic();
        ResponseBuilder builder = new ResponseBuilder(logic);
        this.responseHandler = new ResponseHandler(port, publicDir, builder);
    }

    public void run() {
        try {
            setupInputOutput();
            handleRequest();
            handleResponse();
        }
        finally {
            writer.close();
            reader.close();
            connectionSocket.disconnect();
        }
    }

    private void setupInputOutput(){
        reader = new Reader(connectionSocket);
        writer = new Writer(connectionSocket);
        requestHandler.setupInput(reader);
        responseHandler.setupOutput(writer);
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