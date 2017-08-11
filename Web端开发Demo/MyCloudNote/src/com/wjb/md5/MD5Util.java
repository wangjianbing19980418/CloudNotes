package com.wjb.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String getMd5(String input_text) {
		// 获得md5加密算法
		if (input_text != null && input_text.length() > 16) {
			return input_text;
		}
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 加密字符串
		digest.update(input_text.getBytes());
		// 获得加密结果
		byte[] bytes = digest.digest();
		StringBuilder builder = new StringBuilder();
		// 将加密结果转换为16进制字符串
		for (int i = 0; i < bytes.length; i++) {
			String s = Integer.toHexString(Math.abs(bytes[i]));
			builder.append(s);
		}
		return builder.toString();
	}

	public static void main(String args[]) {
		MD5Util md5 = new MD5Util();
		System.out.println(md5.getMd5("abc"));
	}
}
