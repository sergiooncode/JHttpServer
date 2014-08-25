package com.sperez.jhs.mocks;

import com.sperez.jhs.SocketInterface;

import java.io.InputStream;
import java.io.OutputStream;

public class MockSocket implements SocketInterface {
    private boolean isClosed;

    public void disconnect() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public InputStream getInput() {
        return null;
    }

    public OutputStream getOutput() {
        return null;
    }
}