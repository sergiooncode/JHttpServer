package com.sperez.jhs;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusCodeHandlerTest {
    @Test
    public void testGetStatusLine200() throws Exception {
        assertEquals("HTTP/1.1 200 OK", StatusCodeHandler.getStatusLine("200"));
    }

    @Test
    public void testGetStatusLine404() throws Exception {
        assertEquals("HTTP/1.1 404 Not Found", StatusCodeHandler.getStatusLine("404"));
    }

    @Test
    public void testGetStatusLine405() throws Exception {
        assertEquals("HTTP/1.1 405 Method Not Allowed", StatusCodeHandler.getStatusLine("405"));
    }
}