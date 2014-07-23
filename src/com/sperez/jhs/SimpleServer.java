package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private ConnectionHandler connectionHandler;

    public SimpleServer (ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void handleClient() {
        new Thread (new ClientHandler(connectionHandler)).start();
    }

    private void connect() {
        connectionHandler.connect();
    }

    public void run() {
        keepGoing = true;

        while(keepGoing()){
            connect();
            handleClient();
        }
    }

    private boolean keepGoing() {
        return keepGoing;
    }
}