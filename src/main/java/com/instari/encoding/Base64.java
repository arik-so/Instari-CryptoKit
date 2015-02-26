package com.instari.encoding;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by arik on 2/23/15.
 */
public class Base64 {

    public static String encode(String input) {

        Charset utf8Charset = Charset.forName("UTF-8");

        return encode(input.getBytes(utf8Charset));

    }

    public static String encode(byte[] binary) {

        return com.instari.surrogate.apache.commons.codec.binary.Base64.encodeBase64String(binary);

    }

    public static byte[] decode(String base64) throws IOException {

        return com.instari.surrogate.apache.commons.codec.binary.Base64.decodeBase64(base64);

    }

}
