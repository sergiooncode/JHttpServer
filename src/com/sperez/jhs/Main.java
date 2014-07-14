package com.sperez.jhs;

public class Main {
    public static void main (String[] args) {
        ServerSocketInterface listenSocket;
        SimpleServer server;
        ArgumentsParser parser;
        ResponseMaker maker;

        parser = new ArgumentsParser(args);
        listenSocket = new RealServerSocket(parser.getPort());
        server = new SimpleServer(listenSocket);
        maker = new ResponseMaker();
        server.setupResponseMaker(maker);

        server.run();
    }
}