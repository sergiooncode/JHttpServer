package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentsParserTest {
    @Test
    public void testParsePortFirst() throws Exception {
        String[] args = {"-p", "5000", "-d", "/"};
        ArgumentsParser parser = new ArgumentsParser(args);

        assertEquals(5000, parser.getPort());
        assertEquals("/", parser.getPublicDir());
    }

    @Test
    public void testParsePublicDirFirst() throws Exception {
        String[] args = {"-d", "/public", "-p", "8080"};
        ArgumentsParser parser = new ArgumentsParser(args);

        assertEquals(8080, parser.getPort());
        assertEquals("/public", parser.getPublicDir());
    }
}