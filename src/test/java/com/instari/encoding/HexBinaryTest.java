package com.instari.encoding;

import org.junit.Test;

import static org.junit.Assert.*;

public class HexBinaryTest {

    @Test
    public void testBinaryToHex() throws Exception {

        byte[] bytes = {0x48, 0x65, 0x6c, 0x6c, 0x6f}; // Hello
        String expectedHex = "48656c6c6f";

        String actualHex = HexBinary.binaryToHex(bytes);
        assertEquals("bin2hex", expectedHex, actualHex);

    }

    @Test
    public void testHexToBinary() throws Exception {

        String hex = "48656c6c6f";
        byte[] expectedBytes = {0x48, 0x65, 0x6c, 0x6c, 0x6f}; // Hello

        byte[] actualBytes = HexBinary.hexToBinary(hex);
        assertArrayEquals("hex2bin", expectedBytes, actualBytes);

    }
}