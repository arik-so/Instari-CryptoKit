package com.instari.encryption;

import com.instari.encoding.Base64;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSATest {

    @Test
    public void testGenerateKeyPair() throws Exception {

        RSA.RSAKeyPair keyPair = RSA.generateKeyPair(2048);

        assertEquals("Private key PKCS#1 conformity (length)", 1675, keyPair.getPrivateKey().length(), 10);
        assertEquals("Public key PKCS#1/PKCS#8 conformity (length)", 450, keyPair.getPublicKey().length(), 10);

    }

    @Test
    public void testEncryptWithPublic() throws Exception {

    }

    @Test
    public void testDecryptWithPrivate() throws Exception {

    }

    @Test
    public void testEncryptWithPrivate() throws Exception {

    }

    @Test
    public void testDecryptWithPublic() throws Exception {

    }

    @Test
    public void testSign() throws Exception {

    }

    @Test
    public void testIsSignatureValid() throws Exception {

    }
}