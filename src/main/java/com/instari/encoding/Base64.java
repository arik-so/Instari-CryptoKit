package com.instari.encoding;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by arik on 2/23/15.
 */
public class Base64 {

    private static BASE64Encoder encoder = new BASE64Encoder();
    private static BASE64Decoder decoder = new BASE64Decoder();

    public static String encode(byte[] binary) {
        return encoder.encode(binary);
    }

    public static byte[] decode(String base64) throws IOException {
        return decoder.decodeBuffer(base64);
    }

}
