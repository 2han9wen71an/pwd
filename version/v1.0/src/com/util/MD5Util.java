package com.util;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 */
public class MD5Util {
	private static String default_key = "U8l4Yu{+Hi(!";

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	public static String StringToMD5(String Str, String key) {
		int length = Str.length();
		key = (key != null) ? key : default_key;
		if (length > 3) {
			Str = string2MD5(Str.substring(0, 2) + key + Str.substring(2, length));
		} else {
			Str = string2MD5(Str);
		}
		return Str;
	}

	public static void main(String[] args) {
		System.out.println("a");
		System.out.println(StringToMD5("zhangwentian", "Zz"));
	}
}