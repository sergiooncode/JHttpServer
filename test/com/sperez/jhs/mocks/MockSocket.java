package com.sperez.jhs.mocks;

import com.sperez.jhs.SocketInterface;

import java.io.InputStream;
import java.io.OutputStream;

public class MockSocket implements SocketInterface {

    @Override
    public void close() {

    }

    @Override
    public InputStream getInput() {
        return null;
    }

    @Override
    public OutputStream getOutput() {
        return null;
    }
}