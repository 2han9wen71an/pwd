package com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class GeneralUtil {
	public static void main(String[] args) {
		System.out.println(genRandomNum(8));
		System.out.println(getRandom(8));
	}

	// 这个生成不重复随机数
	public static String genRandom(int j) {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		for (int i = 0; i < j; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	public static String genRandomNum(int j) {
		int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < j) {
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String getRandom(int j) {
		Random r = new Random();
		String code = "";
		for (int i = 0; i < j; ++i) {
			int temp = r.nextInt(52);
			char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
			code += x;
		}
		return code;
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String GetWebUrl(String url) {
		// 使用正则表达式过滤，
		String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
		String str = "";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(re);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(url);
		// 若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
		if (matcher.matches()) {
			str = url;
		} else {
			String[] split2 = url.split(re);
			if (split2.length > 1) {
				String substring = url.substring(0, url.length() - split2[1].length());
				str = substring;
			} else {
				str = split2[0];
			}
		}
		return str;
	}

	public static String EchoMsg(String code, String msg, int count, Object data) {
		Map map = new HashMap();
		if (code != null) {
			map.put("code", code);
		}
		if (msg != null) {
			map.put("msg", msg);
		}
		if (count != 0) {
			map.put("count", count);
		}
		if (data != null) {
			map.put("data", data);
		}
		String str = new Gson().toJson(map);
		return str;
	}

	public static String getcookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();// 创建一个cookie集合并拿到cookie放入创建好的cookie集合里面
		// 遍历cookie集合并判断是否有自己想要的指定cookie如果有则返回指定cookie的值，如果没有则返回空字符串
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return "";
	}
}
