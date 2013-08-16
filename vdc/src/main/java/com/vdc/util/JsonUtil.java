package com.vdc.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * 实现json字符串与java对象之间的互相转换等功能
 * 
 * @author daniel
 * 
 */
public class JsonUtil {
	public static void main(String[] args) {
//		String json = "{'mailTo':'aaaa@tom.com','mailSubject':'aaaaa','mailContent':'bbbbbbb'}";
//		jsonToObject(json, MailDto.class);
//
//		String jsonList = "[{'mailTo':'aaaa@tom.com','mailSubject':'aaaaa','mailContent':'bbbbbbb'},{'mailTo':'1111@tom.com','mailSubject':'222','mailContent':'333'}]";
//		Type listType1 = new TypeToken<List<MailDto>>() {
//		}.getType();
//		jsonToObject(jsonList, listType1);
//
//		MailDto mailDto1 = new MailDto();
//		mailDto1.setMailTo("aaaaa@163.com");
//		mailDto1.setMailSubject("aaaaaa");
//		mailDto1.setMailContent("bbbbbb");
//		objectToJson(mailDto1);
//
//		List<MailDto> mailDtoList = new ArrayList<MailDto>();
//		MailDto mailDto = new MailDto();
//		mailDto.setMailTo("aaaaa@163.com");
//		mailDto.setMailSubject("aaaaaa");
//		mailDto.setMailContent("bbbbbb");
//		mailDtoList.add(mailDto);
//		mailDto = new MailDto();
//		mailDto.setMailTo("1111@163.com");
//		mailDto.setMailSubject("222");
//		mailDto.setMailContent("33333");
//		mailDtoList.add(mailDto);
//		Type listType = new TypeToken<List<MailDto>>() {
//		}.getType();
//		objectToJson(mailDtoList, listType);
	}

	/**
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	public static Object jsonToObject(String json, Class<?> clazz) {
		Object obj = null;
		try {
			obj = new Gson().fromJson(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将json字符串转换成java对象（支持泛型）
	 * 
	 * @param json
	 *            json字符串
	 * @param type
	 *            对象类型
	 * @return
	 */
	public static Object jsonToObject(String json, Type type) {
		Object obj = null;
		try {
			obj = new Gson().fromJson(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String objectToJson(Object obj) {
		String json = null;
		try {
			json = new Gson().toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将java对象转换成json字符串（支持泛型）
	 * 
	 * @param obj
	 *            对象
	 * @param type
	 *            对象类型
	 * @return
	 */
	public static String objectToJson(Object obj, Type type) {
		String json = null;
		try {
			json = new Gson().toJson(obj, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
