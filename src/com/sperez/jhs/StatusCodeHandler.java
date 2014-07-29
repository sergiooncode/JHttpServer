package com.sperez.jhs;

class StatusCodeHandler {
    static String getStatusLine(String code) {
        String statusLine = "";

        if (code.equals("200")) {
            statusLine = "HTTP/1.1 200 OK";
        } else if (code.equals("404")) {
            statusLine = "HTTP/1.1 404 Not Found";
        } else if (code.equals("405")) {
            statusLine = "HTTP/1.1 405 Method Not Allowed";
        }
        return statusLine;
    }
}