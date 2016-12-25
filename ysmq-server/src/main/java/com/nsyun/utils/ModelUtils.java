package com.nsyun.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

public class ModelUtils {
	
	/**
	 * 
	 * @Title: ModelUtils
	 * @Description: 两个实体对象属性值copy 忽略copy的属性
	 * @param: @param source
	 * @return: void
	 * @throws
	 */
	public static String[] getIgnoreProperties (Object source) {
		try {
			Field[] fields = source.getClass().getDeclaredFields();
			String[] ignoreProperties = new String[fields.length];
			
			int count = 0;
			for (Field field : fields) {
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				Method method = source.getClass().getMethod("get" + getMethodName(field.getName()));
				Object objVal = method.invoke(source); //调用getter方法获取属性值
				if (StringUtils.isEmpty(objVal)) {
					ignoreProperties[count] = field.getName();
					count++;
				}
			}
			return ignoreProperties;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: ModelUtils
	 * @Description: 把一个字符串首字母大写
	 * @param: @param fieldName
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	private static String getMethodName (String fieldName) {
		byte[] items = fieldName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
