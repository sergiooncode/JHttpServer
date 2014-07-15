package com.sperez.jhs;

public class SimpleServer {
    private boolean keepGoing;
    private ConnectionHandler connectionHandler;
    private ReaderWriter readerWriter;
    private String requestMessageLine;
    private ResponseMaker maker;

    public SimpleServer (ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void setupInputOutput(){
        readerWriter = new ReaderWriter(connectionHandler);
    }

    public void setupResponseMaker(ResponseMaker maker) {
        this.maker = maker;
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
            connectionHandler.connect();
            setupInputOutput();
            requestMessageLine = readRequest();
            sendResponse(maker.makeResponse(requestMessageLine));
            connectionHandler.disconnect();
        }
    }

    protected boolean keepGoing() {
        return keepGoing;
    }
}