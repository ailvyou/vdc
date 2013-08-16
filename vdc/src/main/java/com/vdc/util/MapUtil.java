/**
 * 
 */
package com.vdc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * map的一些工具类
 * 
 * @author jack
 */
public class MapUtil {
	public static void main(String args[]) throws Exception {
//		CircleInfo circleInfo = new CircleInfo();
//		circleInfo.setCircleName("test");
//		circleInfo.setTypeCode("testcode");
//		circleInfo.setCreateUser(1L);
//		circleInfo.setUserCount(1L);
//		MapUtil.objectToMap(circleInfo);
	}

	/**
	 * 返回由对象的属性为key,值为map的value的Map集合
	 * 
	 * @param obj
	 *            Object
	 * @return mapValue Map<String,String>
	 * @throws Exception
	 */
	public static Map<String, String> objectToMap(Object object) throws Exception {
		Map<String, String> mapValue = new HashMap<String, String>();

		Class<?> cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (!"serialVersionUID".equals(name)) {
				String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
				Method methodGet = cls.getDeclaredMethod(strGet);
				Object obj = methodGet.invoke(object);
				if (obj != null) {
					String value = obj != null ? obj.toString() : "";
					mapValue.put(name, value);
				}
			}
		}

		return mapValue;
	}
}
