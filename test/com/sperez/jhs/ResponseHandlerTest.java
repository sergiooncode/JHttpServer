package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ResponseHandlerTest {
    private final int port = 4000;
    private final String publicDir = "/Users/sergioperezaranda";
    private ResponseHandler handler;
    private ResponseBuilder builder;
    private LogicInterface logic;
    private ArrayList<String> emptyHeaders;

    @Before
    public void setUp() throws Exception {
        logic = new RoutingLogic();
        builder = new ResponseBuilder(logic);
        handler = new ResponseHandler(port, publicDir, builder);
        emptyHeaders = new ArrayList<String>();
    }

    @Test
    public void testCreateResponseObject() throws Exception {
        Request requestObject;
        requestObject = new Request("GET", "/", "HTTP/1.1", emptyHeaders, "Hello World!");
        //handler.createResponseObject(requestObject);

        //assertThat(handler.getResponseObject(), instanceOf(Response.class));
    }

    @Test
    public void testCreatingResponseWithStatusLine200() throws Exception {
        Request requestObject;
        Response responseObject;
        requestObject = new Request("GET", "/", "HTTP/1.1", emptyHeaders, "");
        //handler.createResponseObject(requestObject);
        responseObject = handler.getResponseObject();

        //assertEquals("HTTP/1.1 200 OK", responseObject.getStatusLine());
    }
}