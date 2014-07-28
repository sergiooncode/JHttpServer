package com.sperez.jhs;

import com.sperez.jhs.mocks.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ConnectionHandlerTest {
    private ServerSocketInterface listenSocket;
    private ConnectionHandler handler;

    @Before
    public void setUp() {
        listenSocket = new MockServerSocket();
        handler = new ConnectionHandler(listenSocket);
    }

    @Test
    public void testConnect() throws Exception {
        handler.connect();

        assertThat(handler.getConnectionSocket(), instanceOf(MockSocket.class));
    }

    @Test
    public void testDisconnect() throws Exception {
        handler.connect();
        handler.disconnect();

        assertTrue(handler.getConnectionSocket().isClosed());
    }
}