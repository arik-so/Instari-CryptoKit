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
    public void testSHA256() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String input = "Hello World!";
        String expectedOutput = "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069";

        String actualOutput = Hash.sha256(input);
        assertEquals("sha256", expectedOutput, actualOutput);

    }

    @Test
    public void testSHA512() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String input = "Hello World!";
        String expectedOutput = "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8";

        String actualOutput = Hash.sha512(input);
        assertEquals("sha512", expectedOutput, actualOutput);

    }
}