package com.sperez.jhs;

public interface HandlerInterface {
    void handle();
    void setupInputOutput(ReaderWriter inputOutput);
    Request getRequestObject();
    void setRequestObject(Request requestObject);
}