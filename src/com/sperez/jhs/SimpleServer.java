package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private ConnectionHandler connectionHandler;
    private ClientHandler handler;

    public SimpleServer(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void run() {
        keepGoing = true;

        while ( keepGoing()) {
            connect();
            handleClient();
        }
    }

    public void setupClientHandler(ClientHandler handler) {
        this.handler = handler;
    }

    private void handleClient() {
        handler.handle();
    }

    private void connect() {
        connectionHandler.connect();
    }

    private boolean keepGoing() {
        return keepGoing;
    }
}