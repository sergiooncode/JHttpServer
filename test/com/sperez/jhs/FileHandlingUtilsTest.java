package com.sperez.jhs;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileHandlingUtilsTest {
    final private String publicDir = "/Users/sergioperezaranda/Mycodestore/JHttpServer/public";
    private byte[] readBytes(String path) {
        byte[] byteArray = null;
        try {
            byteArray = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    @Test
    public void testLengthOfByteArray() throws Exception {
        assertEquals("15", FileHandlingUtils.lengthOfFileInBytes(publicDir + "/file1"));
    }

    @Test
    public void testReadTextFile() throws Exception {
        assertEquals("file1 contents\n", FileHandlingUtils.readTextFile(publicDir + "/file1"));
    }

    @Test
    public void testReadJpegImageFile() throws Exception {
        String path = publicDir + "/image.jpeg";
        byte[] sourceFileInBytes = readBytes(path);

        assertThat(sourceFileInBytes, IsEqual.equalTo(FileHandlingUtils.readImageFile(path)));
    }

    @Test
    public void testReadGifImageFile() throws Exception {
        String path = publicDir + "/image.tiff";
        byte[] sourceFileInBytes = readBytes(path);

        assertThat(sourceFileInBytes, IsEqual.equalTo(FileHandlingUtils.readImageFile(path)));
    }

    @Test
    public void testReadPngImageFile() throws Exception {
        String path = publicDir + "/image.png";
        byte[] sourceFileInBytes = readBytes(path);

        assertThat(sourceFileInBytes, IsEqual.equalTo(FileHandlingUtils.readImageFile(path)));
    }
}