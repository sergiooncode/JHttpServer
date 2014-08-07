package com.sperez.jhs;

public class Main {
    public static void main (String[] args) {
        ServerSocketInterface listenSocket;
        SimpleServer server;
        ArgumentsParser parser;

        parser = new ArgumentsParser(args);
        listenSocket = new RealServerSocket(parser.getPort());
        server = new SimpleServer(listenSocket, parser.getPort(), parser.getPublicDir());

        server.run();
    }
}