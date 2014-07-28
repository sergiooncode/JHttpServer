package com.sperez.jhs;

class StatusCodeHandler {
    static String getStatusLine(int code) {
        String statusLine = "";

        switch (code) {
            case 200:   statusLine = "HTTP/1.1 200 OK";
                        break;
            case 404:   statusLine = "HTTP/1.1 404 Not Found";
                        break;
            case 405:   statusLine = "HTTP/1.1 405 Method Not Allowed";
        }
        return statusLine;
    }
}