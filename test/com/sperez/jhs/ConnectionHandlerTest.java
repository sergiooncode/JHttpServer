package com.sperez.jhs;

import com.sperez.jhs.mocks.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ConnectionHandlerTest {

    @Test
    public void testGetConnectionSocket() throws Exception {

    }

    @Test
    public void testConnect() throws Exception {
        ServerSocketInterface listenSocket = new MockServerSocket();
        ConnectionHandler handler = new ConnectionHandler(listenSocket);

        handler.connect();

        assertThat(handler.getConnectionSocket(), instanceOf(MockSocket.class));
    }

    @Test
    public void testDisconnect() throws Exception {

    }
}