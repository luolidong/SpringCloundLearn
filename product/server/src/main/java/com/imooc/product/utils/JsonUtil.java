package com.imooc.product.utils;

import com.google.gson.Gson;

/**
 * Created by 廖师兄
 * 2018-02-21 10:40
 */
public class JsonUtil {

    private static Gson gson = new Gson();

    /**
     * 转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {

        return gson.toJson(object);
    }

	/**
	 * json转对象
	 * @param string
	 * @param classType
	 * @return
	 */
	public static Object fromJson(String string, Class classType) {
        return gson.fromJson(string, classType);
	}
//
//
//	/**
//	 * json转对象
//	 * @param string
//	 * @param typeReference
//	 * @return
//	 */
//	public static Object fromJson(String string, TypeReference typeReference) {
//		try {
//			return objectMapper.readValue(string, typeReference);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
