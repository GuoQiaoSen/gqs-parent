package com.gqs.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
public class AESUtil1 {

	private static final int keyLenght = 16;
	private static final String defaultV = "0";
	private final static String PK = "AES/ECB/PKCS5Padding";
	/**
	 * 加密
	 *
	 * @param key
	 *            密钥
	 * @param src
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content,String password){
		byte[] rawKey = toMakekey(password, keyLenght, defaultV).getBytes();// key.getBytes();
		byte[] result;
		try {
			result = encrypt(rawKey, content.getBytes("utf-8"));
			return StringtoBase64(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 解密
	 *
	 * @param key
	 *            密钥
	 * @param encrypted
	 *            待揭秘文本
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String content,String password) {
		byte[] rawKey = toMakekey(password, keyLenght, defaultV).getBytes();// key.getBytes();
		byte[] enc = Base64toByte(content);
		byte[] result;
		try {
			result = decrypt(rawKey, enc);
			return new String(result, "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 密钥key ,默认补的数字，补全16位数，以保证安全补全至少16位长度,android和ios对接通过
	 * @param str
	 * @param strLength
	 * @param val
	 * @return
	 */
	private static String toMakekey(String str, int strLength, String val) {

		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(str).append(val);
				str = buffer.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	/**
	 * 真正的加密过程
	 * 1.通过密钥得到一个密钥专用的对象SecretKeySpec
	 * 2.Cipher 加密算法，加密模式和填充方式三部分或指定加密算 (可以只用写算法然后用默认的其他方式)Cipher.getInstance("AES");
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance(PK);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(src);
		return encrypted;
	}

	/**
	 * 真正的解密过程
	 *
	 * @param key
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance(PK);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}
		public static byte[] Base64toByte(String hexString) {
			return Base64.decodeBase64(hexString);
	}
		public static String StringtoBase64(byte[] buf) {
		return encodeBase64String(buf);
	}
		
	public static String encodeBase64String(final byte[] binaryData) {
	    return StringUtils.newStringUtf8(Base64.encodeBase64(binaryData, false,true));
	}
	
	public static void main(String[] args) {
		String s = "{\"phone\":\"18210302397\",\"code\":\"123456\",\"token\":\"4c64f4c2-1bd4-4eda-bd9a-300351aec31d\"}";
		System.out.println("s:" + s);
		String s1 = AESUtil1.encrypt(s,"abcdefgabcdefg12");
		System.out.println("s1:" + s1);
		System.out.println("-------------------------------");
		s1="OZkB3wGPKkXxzjvBk8zhUia69aTOLTLHpjjCkRwEbXw0ZWEq2eKpCw-e7vGMORou1m7W55mbRm7RgPYeGwWV8WBh9D9Hb3sbPZfxIofg1hZIb1c6YTIV4OcBEuMeDrjo";
		String s2 = AESUtil1.decrypt(s1, "abcdefgabcdefg12");
		System.out.println(s2);
		
	}
	

}