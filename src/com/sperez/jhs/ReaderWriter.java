package com.sperez.jhs;

import java.io.*;
import java.io.IOException;
import java.net.*;

public class ReaderWriter {
    private InputStreamReader in;
    private BufferedReader inFromClient;
    private BufferedOutputStream out;
    private OutputStreamWriter outToClient;
    private SocketInterface connectionSocket;

    public ReaderWriter(ConnectionHandler connectionHandler) {
        connectionSocket = connectionHandler.getConnectionSocket();

        in = new InputStreamReader(connectionSocket.getInput());
        inFromClient = new BufferedReader (in);

        out = new BufferedOutputStream (connectionSocket.getOutput());
        outToClient = new OutputStreamWriter(out);
//        outToClient = new OutputStreamWriter(new BufferedOutputStream (connectionSocket.getOutput()));
    }

    public void writeLine(String line) {
        try{
            outToClient.write(line);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeAll(String response){
        try{
            String[] tokenizedResponse = response.split("\r\n");
            for(int i=0; i < tokenizedResponse.length; i++){
                outToClient.write(tokenizedResponse[i]);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendAll(){
        try{
            outToClient.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public String readLine(){
        String lineReceived = "";
        try{
            lineReceived = inFromClient.readLine();

        } catch(IOException e){
            e.printStackTrace();
        }
        return lineReceived;
    }
}