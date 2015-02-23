package com.instari.encoding;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by arik on 2/23/15.
 */
public class HexBinary {

    public static String binaryToHex(byte[] binary){
        return Hex.encodeHexString(binary);
    }

    public static byte[] hexToBinary(String hex) throws DecoderException {
        return Hex.decodeHex(hex.toCharArray());
    }

}
