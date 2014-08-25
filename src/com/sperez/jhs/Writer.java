package com.sperez.jhs;

import java.io.*;

public class Writer implements WriterInterface {
    final private String CRLF = "\r\n";
    private BufferedOutputStream out;
    private OutputStreamWriter outToClient;
    private Response responseObject;

    public Writer(SocketInterface connectionSocket) {
        out = new BufferedOutputStream(connectionSocket.getOutput());
        outToClient = new OutputStreamWriter(out);
    }

    public void writeMessage(Response responseObject) {
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

    public void close() {
        try {
            outToClient.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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