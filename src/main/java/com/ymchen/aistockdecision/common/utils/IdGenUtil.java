package com.ymchen.aistockdecision.common.utils;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author
 * @version
 **/
public class IdGenUtil {

    private static char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static SecureRandom random = new SecureRandom();
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 基于Base62编码的SecureRandom随机生成bytes.
     */
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return encodeBase62(randomBytes);
    }


    /**
     * 将data编码成Base62的字符串
     * @param data
     * @return
     */
    public static String encodeBase62(byte[] data) {
        StringBuffer sb = new StringBuffer(data.length * 2);
        int pos = 0, val = 0;
        for (int i = 0; i < data.length; i++) {
            val = (val << 8) | (data[i] & 0xFF);
            pos += 8;
            while (pos > 5) {
                char c = encodes[val >> (pos -= 6)];
                sb.append(
                        /**/c == 'i' ? "ia" :
                                /**/c == '+' ? "ib" :
                                /**/c == '/' ? "ic" : c + "");
                val &= ((1 << pos) - 1);
            }
        }
        if (pos > 0) {
            char c = encodes[val << (6 - pos)];
            sb.append(
                    /**/c == 'i' ? "ia" :
                            /**/c == '+' ? "ib" :
                            /**/c == '/' ? "ic" : c + "");
        }
        return sb.toString();
    }
    /**
     * 将字符串解码成byte数组
     * @param string
     * @return
     */
    public static byte[] decodeBase62(String string) {
        if(string==null){
            return null;
        }
        char[] data=string.toCharArray();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(string.toCharArray().length);
        int pos = 0, val = 0;
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            if (c == 'i') {
                c = data[++i];
                c =
                        /**/c == 'a' ? 'i' :
                        /**/c == 'b' ? '+' :
                        /**/c == 'c' ? '/' : data[--i];
            }
            val = (val << 6) | encodes[c];
            pos += 6;
            while (pos > 7) {
                baos.write(val >> (pos -= 8));
                val &= ((1 << pos) - 1);
            }
        }
        return baos.toByteArray();
    }
}
