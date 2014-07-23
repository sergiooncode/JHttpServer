package com.sperez.jhs;

public class Main {
    public static void main (String[] args) {
        ServerSocketInterface listenSocket;
        SimpleServer server;
        ArgumentsParser parser;
        ConnectionHandler connectionHandler;

        parser = new ArgumentsParser(args);
        listenSocket = new RealServerSocket(parser.getPort());
        connectionHandler = new ConnectionHandler(listenSocket);
        server = new SimpleServer(connectionHandler);

        server.run();
    }
}