package com.sperez.jhs;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileHandlingUtils {
    final static Charset DEFAULT_ENCODING = Charset.defaultCharset();

    static String lengthOfFileInBytes(String path) {
        int length = 0;
        try {
            length = Files.readAllBytes(Paths.get(path)).length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(length);
    }

    static String readTextFile(String path) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, DEFAULT_ENCODING);
    }

    static byte[] readImageFile(String path) {
        byte[] sourceFileInBytes = null;
        try {
            sourceFileInBytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceFileInBytes;
    }

    static String listFilesInFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        String list = "";
        for (int i=0; i < listOfFiles.length; i++) {
            list = list + listOfFiles[i].getName() + "\n";
        }
        return list;
    }

    static boolean isTxtFile(String requestedResource) {
        return requestedResource.contains("txt");
    }

    static boolean isImage(String requestedResource) {
        return isJpeg(requestedResource) || isGif(requestedResource) || isPng(requestedResource);
    }

    static boolean isJpeg(String requestedResource) {
        return requestedResource.contains("jpeg");
    }

    static boolean isGif(String requestedResource) {
        return requestedResource.contains("gif");
    }

    static boolean isPng(String requestedResource) {
        return requestedResource.contains("png");
    }

    static boolean isFileInPublicAndIsNotDir(String path) {
        File file = createFileObject(path);
        return existsAndIsNotDir(file);
    }

    private static File createFileObject(String path) {
        File file = new File(path);
        return file;
    }

    private static boolean existsAndIsNotDir(File file) {
        return file.exists() && !file.isDirectory();
    }
}