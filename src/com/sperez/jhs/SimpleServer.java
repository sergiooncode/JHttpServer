package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing = true;
    private SocketInterface connectionSocket;
    private ReaderWriter readerWriter;
    private String requestMessageLine;
    private ServerSocketInterface listenSocket;

    public SimpleServer (ServerSocketInterface listenSocket) {
        this.listenSocket = listenSocket;
    }

    private void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionSocket);
    }

    private void connectToClient(){
        connectionSocket = listenSocket.accept();
    }

    private String readRequest(){
        String request = readerWriter.readLine();
        System.out.println("Request: " + request);
        return request;
    }

    private void sendResponse(String response){
//        readerWriter.writeLine(response);
        readerWriter.writeAll(response);
        readerWriter.sendAll();
    }

    private void disconnect(){
        connectionSocket.close();
    }

    private String makeResponse(){
        ResponseMaker maker = new ResponseMaker();
        return maker.makeResponse(requestMessageLine);
    }

    public void run (){
        while(keepGoing){
            connectToClient();
            setupInputOutput();
            requestMessageLine = readRequest();
            sendResponse(makeResponse());
            disconnect();
        }
    }
}