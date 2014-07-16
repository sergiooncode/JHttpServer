package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testParseEmptyRequest() {
        String rawRequest = "";
        RequestHandler handler = new RequestHandler();
        handler.setRawRequest(rawRequest);
        handler.parseRequest();

        assertEquals("", handler.getRequestLine());
    }
}