package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseMakerTest {
    private ResponseMaker maker;
    private String request;

    @Before
    public void setUp() throws Exception {
        maker = new ResponseMaker();
    }

    @Test
    public void testMakingResponse200() throws Exception {
        request = "GET / HTTP/1.1";

        assertEquals("HTTP/1.1 200 OK\r\n", maker.makeResponse(request));
    }

    @Test
    public void testMakingResponse404() throws Exception {
        request = "GET /foobar HTTP/1.1";

        assertEquals("HTTP/1.1 404 Not Found\r\n", maker.makeResponse(request));
    }
}