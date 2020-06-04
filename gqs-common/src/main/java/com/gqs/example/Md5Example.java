package com.gqs.example;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 *
 * 有两种加密方式
 * 1.使用java自带的工具类
 * 2.使用spring的工具类
 *
 * @author 郭乔森
 * @create 2020-04-14 9:46
 */
public class Md5Example {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String salt = null;
        String newPassword = DigestUtils.md5DigestAsHex(("123" + salt).getBytes());
        System.out.println(newPassword);
        String a = "guoqiaosen123";
//        stringToMD5(a);
//        testMd5(a);
    }

    /**
     * java自带jar工具MessageDigest实现
     * java.security.MessageDigest
     *
     * @param plainText
     * @return
     */
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        System.out.println(md5code);
        return md5code;
    }

    /**
     * spring自带的工具DigestUtils实现
     * org.springframework.util.DigestUtils
     * spring-core
     *
     * @throws NoSuchAlgorithmException
     */
    public static void testMd5(String a) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        /*// java自带工具包MessageDigest
        String resultString = MD5Utils.md5("123456");
        System.out.println(resultString);
        // e10adc3949ba59abbe56e057f20f883e
        String resultString1 = MD5Utils.md5("1234");
        System.out.println(resultString1);
        //81dc9bdb52d04dc20036dbd8313ed055*/

        // spring自带工具包DigestUtils
        System.out.println(DigestUtils.md5DigestAsHex(a.getBytes()));
        // 81dc9bdb52d04dc20036dbd8313ed055
    }
}
