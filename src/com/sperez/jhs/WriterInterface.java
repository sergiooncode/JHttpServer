package com.sperez.jhs;

public interface WriterInterface {
    public void writeMessage(Response responseObject);
    public void sendAll();
    public void close();
}