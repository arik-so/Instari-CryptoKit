package com.instari.encryption;

import com.instari.encoding.HexBinary;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arik on 2/23/15.
 */
public class AES {

    public static String generateInitializationVector() throws NoSuchAlgorithmException { // 128 bits or 32 bytes

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // we need 256 bits
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String generateKey() throws NoSuchAlgorithmException { // 256 bits or 64 bytes

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // we need 256 bits
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String encrypt(String data, String key, String initializationVector){ // returns a base64-encoded string
        return null;
    }

    public static String decrypt(String data, String key, String initializationVector){ // accepts a base64-encoded string
        return null;
    }

}
