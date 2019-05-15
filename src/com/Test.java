package com;

import java.util.ArrayList;

import com.entity.Pwd_pwd;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.util.GeneralUtil;

public class Test {
	public static void main(String[] args) {
		String strByJson = "[{'title':'测试1','descr':'测试1','username':'admin','pass':'pass','weburl':'google.com','key':''},{'title':'测试2','descr':'测试2','username':'admin','pass':'admin','weburl':'baidu.com','key':'xxxxx'}]";
		System.out.println(GeneralUtil.isJSON(strByJson));
		// Json的解析类对象
		JsonParser parser = new JsonParser();
		// 将JSON的String 转成一个JsonArray对象
		JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();
		Gson gson = new Gson();
		ArrayList<Pwd_pwd> list = new ArrayList<>();

		// 加强for循环遍历JsonArray
		for (JsonElement p : jsonArray) {
			// 使用GSON，直接转成Bean对象
			Pwd_pwd pjson = gson.fromJson(p, Pwd_pwd.class);
			list.add(pjson);
			JsonObject obj = p.getAsJsonObject();
			System.out.println(obj.get("key").getAsString().isEmpty());
		}
		for (int i = 0; i < list.size(); i++) {
			Pwd_pwd pwd = list.get(i);
			System.out.println(pwd.getTitle());
		}
	}
}
