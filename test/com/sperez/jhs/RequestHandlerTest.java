package com.sperez.jhs;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;


public class RequestHandlerTest {
    final private String CRLF = "\r\n\r\n";
    private RequestHandler handler;

    @Test
    public void testCreateRequestObject() throws Exception {
        handler = new RequestHandler();
        handler.setRawRequest("GET / HTTP/1.1" + CRLF +
                "Accept: text/plain" + CRLF +
                CRLF +
                "Hello World!");
        handler.createParser();
        handler.createRequestObject();

        assertThat(handler.getRequestObject(), instanceOf(Request.class));
    }
}