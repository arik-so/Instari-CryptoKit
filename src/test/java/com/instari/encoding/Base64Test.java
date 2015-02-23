package com.instari.encoding;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class Base64Test {

    @Test
    public void testEncode() {
        byte[] bytes = {0x48, 0x65, 0x6c, 0x6c, 0x6f}; // Hello
        String base64String = Base64.encode(bytes);
        assertEquals("Base64 encoding", "SGVsbG8=", base64String);
    }

    @Test
    public void testDecode() throws IOException {

        String base64String = "SGVsbG8=";
        byte[] bytes = Base64.decode(base64String);
        byte[] expectation = {0x48, 0x65, 0x6c, 0x6c, 0x6f};

        assertArrayEquals("Base64 decoding", expectation, bytes);

    }
}