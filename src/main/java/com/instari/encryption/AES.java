package com.instari.encryption;

import com.instari.encoding.Base64;
import com.instari.encoding.HexBinary;
import com.instari.surrogate.apache.commons.codec.DecoderException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arik on 2/23/15.
 */
public class AES {

    private static final String ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static String generateInitializationVector() { // 128 bits or 32 bytes

        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // should never happen due to fixed algorithm
        }
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String generateKey() { // 256 bits or 64 bytes

        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // should never happen due to fixed algorithm
        }
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        byte[] key = secretKey.getEncoded();
        return HexBinary.binaryToHex(key);

    }

    public static String encrypt(String data, String key, String initializationVector) throws DecoderException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException { // returns a base64-encoded string

        TyrannyOverride.overrideTyranny();

        SecretKeySpec aesKeySpec = new SecretKeySpec(HexBinary.hexToBinary(key), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(HexBinary.hexToBinary(initializationVector));

        // Encrypt cipher
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null; // should never happen due to fixed cipher
        }
        cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());

        return Base64.encode(encrypted);

    }

    public static String decrypt(String base64Data, String key, String initializationVector) throws DecoderException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException { // accepts a base64-encoded string

        TyrannyOverride.overrideTyranny();

        SecretKeySpec aesKeySpec = new SecretKeySpec(HexBinary.hexToBinary(key), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(HexBinary.hexToBinary(initializationVector));

        // Decrypt cipher
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null; // should never happen due to fixed cipher
        }
        cipher.init(Cipher.DECRYPT_MODE, aesKeySpec, ivSpec);
        byte[] original = cipher.doFinal(Base64.decode(base64Data));

        return new String(original, Charset.forName("UTF-8"));

    }





}
