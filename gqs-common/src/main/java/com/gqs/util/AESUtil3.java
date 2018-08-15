package com.gqs.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @auther 郭乔森
 * @date 2018年8月15日 上午10:14:08
 */
public class AESUtil3 {
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";// 默认的加密算法
	private final static String password = "OWVmZWJiM2Q3ZDA1OWJmZjA5";
	private final static String iv = "1234567812345678";

	/**
	 * AES 加密操作
	 *
	 * @param content
	 *            待加密内容
	 * @param password
	 *            加密密码
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

			byte[] byteContent = content.getBytes();
			SecretKeySpec skeySpec = getSecretKey(password);
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密

			return Base64.encodeBase64String(result);// 通过Base64转码返回
		} catch (Exception ex) {
			Logger.getLogger(AESUtil3.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content) {

		try {
			// 实例化密码器
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password), ips);

			// 执行操作
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));

			return new String(result);
		} catch (Exception ex) {
			Logger.getLogger(AESUtil3.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String password) {
		return new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
	}

	public static void main(String[] args) {
		String s = "hello,您好aaa";

		System.out.println("s:" + s);

		String s1 = AESUtil3.encrypt(s);
		System.out.println("s1:" + s1);
		System.out.println("s2:" + AESUtil3.decrypt(s1));

	}
}
