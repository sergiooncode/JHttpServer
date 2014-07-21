package com.sperez.jhs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

class FileHandlingUtils {
    static String lengthOfByteArray(String path) {
        int length = 0;
        try {
            length = Files.readAllBytes(Paths.get(path)).length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(length);
    }

    static String readTextFile(String path, Charset encoding) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }

    static String readImageFile(String path) {
        byte[] sourceFileInBytes;
        String encoded = "";
        try {
            sourceFileInBytes = Files.readAllBytes(Paths.get(path));
            encoded = Base64.getEncoder().encodeToString(sourceFileInBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }
}