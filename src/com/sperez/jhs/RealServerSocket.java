package com.sperez.jhs;

import java.net.*;
import java.io.IOException;

public class RealServerSocket implements ServerSocketInterface {
    private ServerSocket listenSocket;
    private int port;

    public RealServerSocket(int port) {
        this.port = port;
        try{
            listenSocket = new ServerSocket(port);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public RealSocket connect(){
        Socket connectionSocket = null;
        try{
            connectionSocket = listenSocket.accept();

            System.out.println ("Web server waiting for request on port " + port);

        } catch(IOException e){
            e.printStackTrace();
        }
        return new RealSocket(connectionSocket);
    }

    public void close() {
        try{
            listenSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}