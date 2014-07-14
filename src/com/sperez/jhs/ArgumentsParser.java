package com.sperez.jhs;

public class ArgumentsParser {
    private int port;
    private String publicDir;

    public ArgumentsParser(String[] args) {
        if (args[0].equals("-p")) {
            port = Integer.parseInt(args[1]);
            publicDir = args[3];
        }

        if (args[0].equals("-d")){
            port = Integer.parseInt(args[3]);
            publicDir = args[1];
        }
    }

    int getPort() {
        return port;
    }

    String getPublicDir() {
        return publicDir;
    }
}