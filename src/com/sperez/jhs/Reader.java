package com.sperez.jhs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements ReaderInterface {
    private InputStreamReader in;
    private BufferedReader inFromClient;

    public Reader(SocketInterface connectionSocket) {
        in = new InputStreamReader(connectionSocket.getInput());
        inFromClient = new BufferedReader(in);
    }

    public String readMessage(){
        String lineReceived = "";
        try{
            lineReceived = inFromClient.readLine();

        } catch(IOException e){
            e.printStackTrace();
        }
        return lineReceived;
    }

    public void close() {
        try {
            inFromClient.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}