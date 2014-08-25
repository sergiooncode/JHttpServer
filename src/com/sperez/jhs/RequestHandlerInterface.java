package com.sperez.jhs;

public interface RequestHandlerInterface {
    void setupInput(ReaderInterface reader);
    void handle();
    Request getRequestObject();
}