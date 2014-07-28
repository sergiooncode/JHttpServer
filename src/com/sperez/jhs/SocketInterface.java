package com.sperez.jhs;

import java.io.InputStream;
import java.io.OutputStream;

public interface SocketInterface {
    void close();
    boolean isClosed();
    InputStream getInput();
    OutputStream getOutput();
}