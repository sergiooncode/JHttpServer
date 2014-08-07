package com.sperez.jhs;

import java.io.*;
import java.net.*;
import java.io.IOException;

public class RealSocket implements SocketInterface {
    private Socket connectionSocket;

    public RealSocket(Socket connectionSocket){
        this.connectionSocket = connectionSocket;
    }

    public InputStream getInput(){
        InputStream input = null;
        try{
        input = connectionSocket.getInputStream();
        } catch(IOException e){
            e.printStackTrace();
        }
        return input;
    }

    public OutputStream getOutput(){
        OutputStream output = null;
        try{
            output = connectionSocket.getOutputStream();
        } catch(IOException e){
            e.printStackTrace();
        }
        return output;
    }

    public boolean isClosed() {
        return connectionSocket.isClosed();
    }

    public void disconnect() {
        try{
            connectionSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
