package com.instari.encryption;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class AESTest {


    @Test
    public void testGenerateInitializationVector() throws Exception {

        String generatedIV = AES.generateInitializationVector();
        assertEquals("AES IV length is 64 bytes", 32, generatedIV.length());

    }

    @Test
    public void testGenerateKey() throws NoSuchAlgorithmException {

        String generatedKey = AES.generateKey();
        assertEquals("AES key length is 64 bytes", 64, generatedKey.length());

    }

    @Test
    public void testEncrypt() throws Exception {

    }

    @Test
    public void testDecrypt() throws Exception {

    }
}