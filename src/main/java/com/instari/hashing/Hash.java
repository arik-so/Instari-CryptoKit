package com.instari.hashing;

import com.instari.encoding.HexBinary;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arik on 2/23/15.
 */
public class Hash {

    public static String md5(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] hash = digest(input, "MD5");

        return exportHash(hash);

    }

    public static String sha256(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] hash = digest(input, "SHA-256");

        return exportHash(hash);

    }

    public static String sha512(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] hash = digest(input, "SHA-512");

        return exportHash(hash);

    }

    private static byte[] digest(String input, String digestionName) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] bytesOfMessage = input.getBytes("UTF-8");

        MessageDigest digestion = MessageDigest.getInstance(digestionName);
        return digestion.digest(bytesOfMessage);

    }

    private static String exportHash(byte[] binary){

        return HexBinary.binaryToHex(binary).toLowerCase();

    }

}
