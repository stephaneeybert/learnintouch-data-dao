package com.thalasoft.learnintouch.data.utils;

import java.nio.charset.Charset;
import java.util.UUID;

public class Common {

    public static String phpCompatibleMD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes(Charset.forName("UTF8")));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String generateUniqueId(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

}
