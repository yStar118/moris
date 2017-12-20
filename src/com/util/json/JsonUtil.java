package com.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 把对象转成json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj) {
		String json_str = "";
		try {
			json_str = objectMapper.writer().writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json_str;
	}

	/**
	 * 把字符串转成List，统一保存时使用
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> toList(String str) {
		List<LinkedHashMap<String, Object>> list = null;
		try {
			list = objectMapper.readValue(str, List.class);// 把list对象转成LinkedhashMap,然后根据HashMap来取值
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 字符串返回一个对象，{\"username\":\"张三\"}返回User对象
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(Class clazz, String str) {
		Object object = null;
		try {
			object = objectMapper.readValue(str, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 生成操作后的json串，{success:false,msgText:'删除失败'}
	 * 
	 * @param b
	 * @param msg
	 * @return
	 */
	public static String createOperaStr(boolean b, String msg) {
		return "{\"success\":" + b + ",\"msgText\":\"" + msg + "\"}";
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}," + "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
		List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) JsonUtil.toObject(List.class, json);
		for (LinkedHashMap<String, Object> aList : list) {
			Set<String> set = aList.keySet();
			for (String key : set) {
				System.out.println(key + ":" + aList.get(key));
			}
		}
	}




	@SuppressWarnings("unchecked")
	public static HashMap<String, String> toHashmap(String str) {
		HashMap<String, String> hashMap = null;
		try {
			hashMap = objectMapper.readValue(str, HashMap.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashMap;
	}
}
