package io.windfree.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Base64Util {
    public static byte[] decode(String data) throws UnsupportedEncodingException
    {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] raw = decoder.decode(String.join("",data));
        return raw;
       /*
        String decodedString = new String(decodedBytes, "UTF-8");
        return decodedString;
        */
    }

    public static String encode(String data) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString;
        encodedString = encoder.encodeToString(data.getBytes("UTF-8"));
        return encodedString;
    }
}


