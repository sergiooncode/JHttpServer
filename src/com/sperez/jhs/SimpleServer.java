package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private SocketInterface connectionSocket;
    private ReaderWriter readerWriter;
    private String requestMessageLine;
    private ServerSocketInterface listenSocket;
    private ResponseMaker maker;

    public SimpleServer (ServerSocketInterface listenSocket) {
        this.listenSocket = listenSocket;
    }

    public void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionSocket);
    }

    public void setupResponseMaker(ResponseMaker maker) {
        this.maker = maker;
    }

    public void connectToClient(){
        connectionSocket = listenSocket.accept();
    }

    public void disconnect(){
        connectionSocket.close();
    }

    public String readRequest(){
        String request = readerWriter.readLine();
        System.out.println("Request: " + request);
        return request;
    }

    public void sendResponse(String response){
        readerWriter.writeLine(response);
//        readerWriter.writeAll(response);
        readerWriter.sendAll();
    }

    protected void run (){
        keepGoing = true;

        while(keepGoing()){
            connectToClient();
            setupInputOutput();
            requestMessageLine = readRequest();
            sendResponse(maker.makeResponse(requestMessageLine));
            disconnect();
        }
    }

    protected boolean keepGoing() {
        return keepGoing;
    }
}