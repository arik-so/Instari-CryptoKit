package com.instari.encryption;

import com.instari.hashing.Hash;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arik on 2/23/15.
 */
public class RSA {

    public static RSAKeyPair generateKeyPair(){

        String privateKey = null;
        String publicKey = null;

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


