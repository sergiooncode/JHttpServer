package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;

public class RequestHandlerTest {
    final private String CRLF = "\r\n\r\n";
    private RequestHandler handler;

    private String getRequestLineAndHeaders() {
        return handler.getRequestLineAndHeaders();
    }

    private String getRequestLine() {
        return handler.getRequestLine();
    }

    private String getRequestHeaders(int index) {
        return handler.getRequestHeaders().get(index);
    }

    private String getRequestBody() {
        return handler.getRequestBody();
    }

    protected String getRequestMethod() {
        return handler.getRequestMethod();
    }

    protected String getRequestedResource() {
        return handler.getRequestedResource();
    }

    protected String getRequestProtocol() {
        return handler.getRequestProtocol();
    }

    private void assertEmptyHttpRequestObject() {
        assertEquals("", getRequestLineAndHeaders());
        assertEquals("", getRequestBody());
    }

    private void parseRequestBasedOnDoubleCRLF(String rawRequest) {
        handler.setRawRequest(rawRequest);
        handler.parseRequestLinePlusHeadersAndBody();
    }

    @Before
    public void setUp() throws Exception {
        handler = new RequestHandler();
    }

    @Test
    public void testParseEmptyRequest() {
        parseRequestBasedOnDoubleCRLF("");

        assertEmptyHttpRequestObject();
    }

    @Test
    public void testParseRequestWithOnlyTwoCRLF() {
        parseRequestBasedOnDoubleCRLF(CRLF + CRLF);

        assertEmptyHttpRequestObject();
    }

    @Test
    public void testParseRequestWithOddNumberOfCRLF() {
        parseRequestBasedOnDoubleCRLF(CRLF + CRLF + CRLF);

        assertEquals("", getRequestLineAndHeaders());
        assertEquals(CRLF, getRequestBody());
    }

    @Test
    public void testParseRequestWithWordBeforeTwoCRLF() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF + CRLF);

        assertEquals("Get", getRequestLineAndHeaders());
        assertEquals("", getRequestBody());
    }

    @Test
    public void testParseRequestWithWordAfterTwoCRLF() {
        parseRequestBasedOnDoubleCRLF(CRLF + CRLF + "Something");

        assertEquals("", getRequestLineAndHeaders());
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithGetWordAfterTwoCRLF() {
        parseRequestBasedOnDoubleCRLF(CRLF + CRLF + "Something");

        assertEquals("", getRequestLineAndHeaders());
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithOnlyRequestLineAndBody() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF +
                CRLF + "Something");

        assertEquals("Get", getRequestLineAndHeaders());
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithIncompleteRequestLinePlusOneHeaderAndBody() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF + "Accept: text/plain" + CRLF +
                CRLF + "Something");
        handler.parseRequestLineAndHeaders();

        assertEquals("Get", getRequestLine());
        assertEquals("Accept: text/plain", getRequestHeaders(0));
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithIncompleteRequestLinePlusTwoHeadersAndBody() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF + "Accept: text/plain" + CRLF +
                "Accept-Charset: utf-8" + CRLF +
                CRLF + "Something");
        handler.parseRequestLineAndHeaders();

        assertEquals("Get", getRequestLine());
        assertEquals("Accept: text/plain", getRequestHeaders(0));
        assertEquals("Accept-Charset: utf-8", getRequestHeaders(1));
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithIncompleteRequestLinePlusSeveralHeadersAndBody() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF + "Accept: text/plain" + CRLF +
                "Accept-Charset: utf-8" + CRLF + "Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT" + CRLF +
                CRLF + "Something");
        handler.parseRequestLineAndHeaders();

        assertEquals("Get", getRequestLine());
        assertEquals("Accept: text/plain", getRequestHeaders(0));
        assertEquals("Accept-Charset: utf-8", getRequestHeaders(1));
        assertEquals("Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT", getRequestHeaders(2));
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithCompleteRequestLinePlusSeveralHeadersAndBody() {
        parseRequestBasedOnDoubleCRLF("Get / HTTP/1.1" + CRLF + "Accept: text/plain" + CRLF +
                "Accept-Charset: utf-8" + CRLF + "Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT" + CRLF +
                CRLF + "Something");
        handler.parseRequestLineAndHeaders();
        handler.parseRequestLine();

        assertEquals("Get", getRequestMethod());
        assertEquals("/", getRequestedResource());
        assertEquals("HTTP/1.1", getRequestProtocol());
    }
}