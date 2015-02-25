package com.instari.encryption;

import com.instari.encoding.Base64;
import com.instari.hashing.Hash;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import sun.security.rsa.RSAKeyPairGenerator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by arik on 2/23/15.
 */
public class RSA {

    private static final String PRIVATE_KEY_PREFIX = "-----BEGIN RSA PRIVATE KEY-----\n";
    private static final String PRIVATE_KEY_SUFFIX = "\n-----END RSA PRIVATE KEY-----\n";

    private static final String PUBLIC_KEY_PREFIX = "-----BEGIN PUBLIC KEY-----\n";
    private static final String PUBLIC_KEY_SUFFIX = "\n-----END PUBLIC KEY-----\n";

    public static RSAKeyPair generateKeyPair() throws NoSuchAlgorithmException, IOException, NoSuchProviderException {

        return generateKeyPair(2048);
        // return null;

    }

    public static RSAKeyPair generateKeyPair(int size) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(size);

        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKeyInfo pki = PrivateKeyInfo.getInstance(keyPair.getPrivate().getEncoded());
        RSAPrivateKeyStructure cryptographyStandard1PrivateKey = RSAPrivateKeyStructure.getInstance(pki.getPrivateKey());
        byte[] cryptographyStandard1PrivateKeyBytes = cryptographyStandard1PrivateKey.getEncoded();

        String privateKey = PRIVATE_KEY_PREFIX + Base64.encode(cryptographyStandard1PrivateKeyBytes) + PRIVATE_KEY_SUFFIX;
        String publicKey = PUBLIC_KEY_PREFIX + Base64.encode(keyPair.getPublic().getEncoded()) + PUBLIC_KEY_SUFFIX;

        return new RSAKeyPair(privateKey, publicKey);

    }

    public static String encryptWithPublic(String data, String publicKey){
        return null;
    }

    public static String decryptWithPrivate(String data, String privateKey){
        return null;
    }

    public static String encryptWithPrivate(String data, String privateKey){
        return null;
    }

    public static String decryptWithPublic(String data, String publicKey){
        return null;
    }

    public static String sign(String data, String privateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = signatureHash(data);
        return encryptWithPrivate(hash, privateKey);
    }

    public static boolean isSignatureValid(String data, String signature, String publicKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = signatureHash(data);
        String foundValue = decryptWithPublic(signature, publicKey);
        return hash.equals(foundValue);
    }

    private static String signatureHash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return Hash.sha512(input);
    }

    static class RSAKeyPair{

        private String privateKey;
        private String publicKey;

        private RSAKeyPair(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

}


