package com.sperez.jhs;

public interface ResponseHandlerInterface {
    void setupOutput(WriterInterface writer);
    void setRequestObject(Request requestObject);
    void handle();
}