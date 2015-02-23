package com.instari.encryption;

import com.instari.encoding.Base64;
import com.instari.encoding.HexBinary;
import org.apache.commons.codec.DecoderException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arik on 2/23/15.
 */
public class AES {

    public static String generateInitializationVector() throws NoSuchAlgorithmException { // 128 bits or 32 bytes

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String generateKey() throws NoSuchAlgorithmException { // 256 bits or 64 bytes

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String encrypt(String data, String key, String initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, DecoderException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException { // returns a base64-encoded string

        TyrannyOverride.overrideTyranny();

        SecretKeySpec aesKeySpec = new SecretKeySpec(HexBinary.hexToBinary(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(HexBinary.hexToBinary(initializationVector));

        // Encrypt cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());

        return Base64.encode(encrypted);

    }

    public static String decrypt(String data, String key, String initializationVector){ // accepts a base64-encoded string

        TyrannyOverride.overrideTyranny();

        return null;
    }





}
