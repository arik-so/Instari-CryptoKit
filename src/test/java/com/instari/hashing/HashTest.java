package com.instari.hashing;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class HashTest {

    @Test
    public void testMD5() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String input = "Hello World!";
        String expectedOutput = "ed076287532e86365e841e92bfc50d8c";

        String actualOutput = Hash.md5(input);
        assertEquals("md5", expectedOutput, actualOutput);

    }

    @Test
    public void testSHA512() {

    }

    @Test
    public void testSHA256() {

    }
}