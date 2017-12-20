package com.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能描述:加密工具类,支持MD5、SHA
 */
public class EncryptUtil {
	/**
	 * MD5加密
	 * @param inputText
	 * @return
	 */
	public static String getMd5(String inputText) {
		return encrypt(inputText, "md5");
	}

	/**
	 * sha加密 
	 * @param inputText
	 * @return
	 */
	public static String getSha(String inputText) {
		return encrypt(inputText, "sha-1");
	}

	/** 
	 * md5或者sha-1加密 
	 *  
	 * @param inputText 
	 *            要加密的内容 
	 * @param algorithmName 
	 *            加密算法名称：md5或者sha-1，不区分大小写 
	 * @return 
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			// m.digest(inputText.getBytes("UTF8"));
			return hex(s);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 返回十六进制字符串
	private static String hex(byte[] arr) {
		StringBuilder sb = new StringBuilder();
		for (byte anArr : arr) {
			sb.append(Integer.toHexString((anArr & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(EncryptUtil.getSha("123456"));
	}
}