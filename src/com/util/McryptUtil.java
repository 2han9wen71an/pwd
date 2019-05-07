package com.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class McryptUtil {
	public static void main(String[] args) {
		System.out.println(decode("1b2bw6J3SWTCm8KVw53Cli81ZEvCmsOxw5bCucOUXADCiS3CoHMiw47Cs8KKwqdObEsEQmk=",
				"202cb962ac59075b964b07152d234b70"));
	}

	// 默认密钥
	private static String default_key = "U8l4Yu{+Hi(!";

	// 返回字符串的首个字符的 ASCII 值。
	public static int ord(String s) {
		return s.length() > 0 ? (s.getBytes(StandardCharsets.UTF_8)[0] & 0xff) : 0;
	}

	// 返回字符的 ASCII 值。
	public static int ord(char c) {
		return c < 0x80 ? c : ord(Character.toString(c));
	}

	/**
	 * Base64加密
	 * 
	 * @param key
	 * @return
	 */
	public static String Base64encode(String key) {
		byte[] bt = key.getBytes();
		return (new Base64().encodeToString(bt));
	}

	/**
	 * Baes64解密
	 * 
	 * @param key
	 * @return
	 */
	private static String Base64decode(String key) {
		byte[] bt = new Base64().decode(key);
		return new String(bt);
	}

	/**
	 * 字符加密，一次一密,可定时解密有效
	 * 
	 * @param string
	 *            str 原文
	 * @param string
	 *            key 密钥
	 * @param int
	 *            expiry 密文有效期,单位s,0 为永久有效
	 * @return string 加密后的内容
	 */
	public static String encode(String str, String key, int expiry) {
		int ckeyLength = 4;
		long time = new Date().getTime() / 1000;
		key = MD5Util.string2MD5((key != null) ? key : default_key);
		String keya = MD5Util.string2MD5(key.substring(0, 16)); // 做数据完整性验证
		String keyb = MD5Util.string2MD5(key.substring(16)); // 用于变化生成的密文
		String keyc = MD5Util.string2MD5(String.valueOf(time))
				.substring(MD5Util.string2MD5(String.valueOf(time)).length() - ckeyLength);// (初始化向量IV)
		// System.out.println("加密keyc为"+keyc);
		String cryptkey = keya + MD5Util.string2MD5(keya + keyc);
		int keyLength = cryptkey.length();
		// System.out.println("加密前的中间"+MD5Util.string2MD5(str +
		// keyb).substring(0, 16));
		str = String.format("%010d", expiry != 0 ? expiry + time : 0) + MD5Util.string2MD5(str + keyb).substring(0, 16)
				+ str;
		// System.out.println("加密前"+str);
		char c[] = str.toCharArray();
		List rndkey = new ArrayList();
		for (int i = 0; i <= 255; i++) {
			rndkey.add(ord(cryptkey.charAt(i % keyLength)));
		}
		int[] box = new int[256];
		for (int i = 0; i < box.length; i++) {
			box[i] = i;
		}
		// 打乱密匙簿，增加随机性
		for (int i = 0, j = 0; i < 256; i++) {
			j = (j + box[i] + (Integer) rndkey.get(i)) % 256;
			int k = box[i];
			box[i] = box[j];
			box[j] = k;
		}
		for (int a = 0, j = 0, i = 0; i < c.length; i++) {
			a = (a + 1) % 256;
			j = (j + box[a]) % 256;
			int k = box[a];
			box[a] = box[j];
			box[j] = k;
			c[i] = (char) (c[i] ^ (box[(box[a] + box[j]) % 256]));// 将明文转换成密文
		}
		String result = new String(c);
		// System.out.println("加密密文" + result);
		return keyc + Base64encode(result);
	}

	/**
	 * 字符解密，一次一密,可定时解密有效
	 * 
	 * @param str
	 *            密文
	 * @param key
	 *            解密密钥
	 * @return string 解密后的内容
	 */
	public static String decode(String str, String key) {
		int ckeyLength = 4;
		key = MD5Util.string2MD5((key != null) ? key : default_key);
		String keya = MD5Util.string2MD5(key.substring(0, 16)); // 做数据完整性验证
		String keyb = MD5Util.string2MD5(key.substring(16)); // 用于变化生成的密文
		String keyc = str.substring(0, ckeyLength);
		// System.out.println("解密keyc为"+keyc);
		String cryptkey = keya + MD5Util.string2MD5(keya + keyc);
		int keyLength = cryptkey.length();
		str = Base64decode(str.substring(ckeyLength));
		// System.out.println("解密密文" + str);
		char c[] = str.toCharArray();
		int stringLength = str.length();
		List rndkey = new ArrayList();
		for (int i = 0; i <= 255; i++) {
			rndkey.add(ord(cryptkey.charAt(i % keyLength)));
		}
		int[] box = new int[256];
		for (int i = 0; i < box.length; i++) {
			box[i] = i;
		}
		// 打乱密匙簿，增加随机性
		for (int i = 0, j = 0; i < 256; i++) {
			j = (j + box[i] + (Integer) rndkey.get(i)) % 256;
			int k = box[i];
			box[i] = box[j];
			box[j] = k;
		}
		for (int a = 0, j = 0, i = 0; i < c.length; i++) {
			a = (a + 1) % 256;
			j = (j + box[a]) % 256;
			int k = box[a];
			box[a] = box[j];
			box[j] = k;
			c[i] = (char) (c[i] ^ (box[(box[a] + box[j]) % 256]));// 将明文转换成密文
		}
		String result = new String(c);
		// System.out.println("解密后" + result);
		// System.out.println(result.substring(0, 10).equals("0000000000"));
		// System.out.println(Long.valueOf(result.substring(0, 10)) - new
		// Date().getTime() / 1000 > 0);
		// System.out.println("第一个参数："+result.substring(10,26));
		// System.out.println(result.substring(26) + keyb);
		// System.out.println("第二个参数："+MD5Util.string2MD5(result.substring(26) +
		// keyb).substring(0, 16));
		if (result.substring(0, 10).equals("0000000000")
				|| Long.valueOf(result.substring(0, 10)) - new Date().getTime() / 1000 > 0 && result.subSequence(10, 26)
						.equals(MD5Util.string2MD5(result.substring(26) + keyb).substring(0, 16))) {
			// System.out.println("解密成功");
			return result.substring(26);
		} else {
			return "解密失败";
		}
	}

}
