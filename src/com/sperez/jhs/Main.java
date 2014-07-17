package com.sperez.jhs;

public class Main {
    public static void main (String[] args) {
        ServerSocketInterface listenSocket;
        SimpleServer server;
        ArgumentsParser parser;
        RequestHandler requestHandler;
        ResponseHandler responseHandler;
        ConnectionHandler connectionHandler;

        parser = new ArgumentsParser(args);
        listenSocket = new RealServerSocket(parser.getPort());
        connectionHandler = new ConnectionHandler(listenSocket);
        server = new SimpleServer(connectionHandler);

        requestHandler = new RequestHandler();
        responseHandler = new ResponseHandler();
        server.setupRequestHandler(requestHandler);
        server.setupResponseHandler(responseHandler);

        server.run();
    }
}