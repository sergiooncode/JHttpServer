package com.sperez.jhs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RequestTest {
    private ArrayList<String> headers;
    private Request requestObject;

    @Test
    public void testThereIsRangeHeader() throws Exception {
        headers = new ArrayList<String>();
        requestObject = new Request("GET", "/partial_content.txt", "HTTP/1.1", headers, "");
        requestObject.addRequestHeader("Range: bytes=0-4");

        assertEquals("Range: bytes=0-4", requestObject.getHeaderIfThereIs("Range:"));
    }

    @Test
    public void testIfThereIsIfMatchHeader() throws Exception {
        headers = new ArrayList<String>();
        requestObject = new Request("GET", "/patch-content.txt", "HTTP/1.1", headers, "");
        requestObject.addRequestHeader("If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4");

        assertEquals("If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4", requestObject.getHeaderIfThereIs("If-Match:"));
    }
}