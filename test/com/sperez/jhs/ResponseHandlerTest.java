package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ResponseHandlerTest {
    private ResponseHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = new ResponseHandler();
    }

    @Test
    public void testCreateResponseObject() throws Exception {
        Request requestObject;
        requestObject = new Request("GET", "/", "HTTP/1.1", "Hello World!");
        handler.createResponseObject(requestObject);

        assertThat(handler.getResponseObject(), instanceOf(Response.class));
    }

    @Test
    public void testCreatingResponseWithStatusLine200() throws Exception {
        Request requestObject;
        Response responseObject;
        requestObject = new Request("GET", "/", "HTTP/1.1", "");
        handler.createResponseObject(requestObject);
        responseObject = handler.getResponseObject();

        assertEquals("HTTP/1.1 200 OK", responseObject.getStatusLine());
    }
}