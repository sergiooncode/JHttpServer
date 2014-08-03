package com.sperez.jhs;

class StatusCodeHandler {
    public static String getStatusLine(String code) {
        String statusLine = "";

        if (code.equals("200")) {
            statusLine = "HTTP/1.1 200 OK";
        } else if (code.equals("404")) {
            statusLine = "HTTP/1.1 404 Not Found";
        } else if (code.equals("405")) {
            statusLine = "HTTP/1.1 405 Method Not Allowed";
        } else if (code.equals("301")) {
            statusLine = "HTTP/1.1 301 Moved Permanently";
        } else if (code.equals("206")) {
            statusLine = "HTTP/1.1 206 Partial Content";
        }
        return statusLine;
    }
}