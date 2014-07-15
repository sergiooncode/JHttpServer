package com.sperez.jhs;

public class ConnectionHandler {
    private ServerSocketInterface listenSocket;
    private SocketInterface connectionSocket;

    public ConnectionHandler(ServerSocketInterface listenSocket) {
        this.listenSocket = listenSocket;
    }

    public SocketInterface getConnectionSocket() {
        return connectionSocket;
    }

    public void connect(){
        connectionSocket = listenSocket.accept();
    }

    public void disconnect(){
        connectionSocket.close();
    }
}