package com.sperez.jhs;

public class ResponseMaker {
    String makeResponse(String request) {
        String response = "";

        String[] tokenizedLine = request.split(" ");
        if (tokenizedLine[0].equals("GET")) {
            if (tokenizedLine[1].equals("/")) response = "HTTP/1.1 200 OK\r\n";
            if (tokenizedLine[1].equals("/foobar")) response = "HTTP/1.1 404 Not Found\r\n";
            if (tokenizedLine[1].equals("/image.jpeg")) response = "HTTP/1.1 200 OK\r\n" +
                    "Last-Modified: Tue, 10 Jun 2014 08:02:00 GMT\r\n" +
                    "Content-Type: image/jpeg\r\n" +
                    "Content-Length: 38400\r\n" +
                    "Server: WEBrick/1.3.1 (Ruby/1.9.2/2011-02-18)\r\n" +
                    "Connection: Keep-Alive\r\n";
        }
        return response;
    }
}