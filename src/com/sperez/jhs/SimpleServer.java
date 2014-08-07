package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private ServerSocketInterface listenSocket;
    private int port;
    private String publicDir;

    public SimpleServer(ServerSocketInterface listenSocket, int port, String publicDir) {
        this.listenSocket = listenSocket;
        this.port = port;
        this.publicDir = publicDir;
    }

    public void run() {
        keepGoing = true;

        while (keepGoing()) {
            handleClient(connect());
        }
    }

    private void handleClient(SocketInterface connectionSocket) {
        new Thread (new ClientHandler(connectionSocket, port, publicDir)).start();
    }

    private SocketInterface connect() {
        return listenSocket.connect();
    }

    private boolean keepGoing() {
        return keepGoing;
    }
}