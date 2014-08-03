package com.sperez.jhs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    final private String CRLF = "\r\n";
    private RequestParser parser;

    private String getRequestLineAndHeaders() {
        return parser.getRequestLineAndHeaders();
    }

    private String getRequestLine() {
        return parser.getRequestLine();
    }

    private String getRequestHeaders(int index) {
        return parser.getRequestHeaders().get(index);
    }

    private String getRequestBody() {
        return parser.getRequestBody();
    }

    private String getRequestMethod() {
        return parser.getRequestMethod();
    }

    private String getRequestedResource() {
        return parser.getRequestedResource();
    }

    private String getRequestProtocol() {
        return parser.getRequestProtocol();
    }

    private void assertEmptyHttpRequest() {
        assertEquals("", getRequestLineAndHeaders());
        assertEquals("", getRequestBody());
    }

    private void parseRequestBasedOnDoubleCRLF(String rawRequest) {
        parser = new RequestParser(rawRequest);
        parser.parseRequestLinePlusHeadersAndBody();
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
        parser.parseRequestLineAndHeaders();

        assertEquals("Get", getRequestLine());
        assertEquals("Accept: text/plain", getRequestHeaders(0));
        assertEquals("Something", getRequestBody());
    }

    @Test
    public void testParseRequestWithIncompleteRequestLinePlusTwoHeadersAndBody() {
        parseRequestBasedOnDoubleCRLF("Get" + CRLF + "Accept: text/plain" + CRLF +
                "Accept-Charset: utf-8" + CRLF +
                CRLF + "Something");
        parser.parseRequestLineAndHeaders();

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
        parser.parseRequestLineAndHeaders();

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
        parser.parseRequestLineAndHeaders();
        parser.parseRequestLine();

        assertEquals("Get", getRequestMethod());
        assertEquals("/", getRequestedResource());
        assertEquals("HTTP/1.1", getRequestProtocol());
    }

    @Test
    public void testGetPartialContent() throws Exception {
        parseRequestBasedOnDoubleCRLF("GET /partial_content.txt HTTP/1.1" + CRLF + "Range: bytes=0-4" + CRLF +
                "Accept-Charset: utf-8" + CRLF + "Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT" + CRLF);
        parser.parseRequestLineAndHeaders();
        parser.parseRequestLine();

        assertEquals("GET /partial_content.txt HTTP/1.1", getRequestLine());
        assertEquals("Range: bytes=0-4", getRequestHeaders(0));
        assertEquals("Accept-Charset: utf-8", getRequestHeaders(1));
        assertEquals("Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT", getRequestHeaders(2));
    }
}