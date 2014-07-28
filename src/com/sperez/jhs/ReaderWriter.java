package com.sperez.jhs;

import java.io.*;
import java.io.IOException;

public class ReaderWriter {
    final private String CRLF = "\r\n";
    private InputStreamReader in;
    private BufferedReader inFromClient;
    private BufferedOutputStream out;
    private OutputStreamWriter outToClient;
    private SocketInterface connectionSocket;
    private Response responseObject;

    public ReaderWriter(ConnectionHandler connectionHandler) {
        connectionSocket = connectionHandler.getConnectionSocket();

        in = new InputStreamReader(connectionSocket.getInput());
        inFromClient = new BufferedReader (in);

        out = new BufferedOutputStream (connectionSocket.getOutput());
        outToClient = new OutputStreamWriter(out);
    }

    public void writeLine(Response responseObject) {
        this.responseObject = responseObject;
        writeStatusLine();
        writeHeaders();
        writeBody();
    }

    public void sendAll(){
        try{
            outToClient.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeReader() {
        try {
            inFromClient.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            outToClient.close();
            out.close();
        } catch (IOException e) {
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

    private void writeStatusLine() {
        try {
            outToClient.write(responseObject.getStatusLine() + CRLF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeaders() {
        try {
            for (int i = 0; i < responseObject.getResponseHeaders().size(); i++) {
                outToClient.write(responseObject.getResponseHeaders().get(i) + CRLF);
            }
            outToClient.write(CRLF);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void writeBody() {
        try {
            if (responseObject.getBinaryResponseBody() != null) {
                out.write(responseObject.getBinaryResponseBody());
            } else {
                outToClient.write(responseObject.getResponseBody());
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}