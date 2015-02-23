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

        String key = "41ef3f238f5c2438abd342c71fb0bb66aeee835b20bfcde24080811d51387c22";
        String initializationVector = "984f31a9f7150e4ea8982e31e715e30d";
        String data = "Hello!";
        String expectedOutput = "wL99DQ1wPkIDYIYIRR13Vw==";

        String actualOutput = AES.encrypt(data, key, initializationVector);

        System.out.println("Actual output: "+actualOutput);

    }

    @Test
    public void testDecrypt() throws Exception {

    }
}