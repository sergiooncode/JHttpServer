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

    @Test
    public void testGetStatusLine301() throws Exception {
        assertEquals("HTTP/1.1 301 Moved Permanently", StatusCodeHandler.getStatusLine("301"));
    }

    @Test
    public void testGetStatusLine206() throws Exception {
        assertEquals("HTTP/1.1 206 Partial Content", StatusCodeHandler.getStatusLine("206"));
    }

    @Test
    public void testGetStatusLine204() throws Exception {
        assertEquals("HTTP/1.1 204 No Content", StatusCodeHandler.getStatusLine("204"));
    }
}