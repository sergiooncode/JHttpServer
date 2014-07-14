package com.sperez.jhs;

public class Main {
    public static void main (String[] args) {
        ArgumentsParser parser = new ArgumentsParser(args);
        ServerSocketInterface listenSocket = new RealServerSocket(parser.getPort());
        SimpleServer server = new SimpleServer(listenSocket);

        server.run();
    }
}