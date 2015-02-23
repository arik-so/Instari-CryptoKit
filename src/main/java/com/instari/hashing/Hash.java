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

        byte[] bytesOfMessage = input.getBytes("UTF-8");

        MessageDigest digestion = MessageDigest.getInstance("MD5");
        byte[] hash = digestion.digest(bytesOfMessage);

        return exportHash(hash);

    }

    public static String sha512(String input){
        throw new NotImplementedException();
    }

    public static String sha256(String input){
        throw new NotImplementedException();
    }

    private static String exportHash(byte[] binary){

        return HexBinary.binaryToHex(binary).toLowerCase();

    }

}
