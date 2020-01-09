package com.zbcn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 利用反射工具包
 */
public class BeanUtils {

	private static Logger log = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 获取对象的属性（利用反射）
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Field findField(Class<?> clazz, String name){
		try
		{
			return clazz.getField(name);
		}
		catch (NoSuchFieldException ex)
		{
			return findDeclaredField(clazz, name);
		}

	}

	public static Field findDeclaredField(Class<?> clazz, String name) {
		try {
			return clazz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			if (clazz.getSuperclass() != null)
			{
				return findDeclaredField(clazz.getSuperclass(), name);
			}
			return null;
		}
	}

	/**
	 * 获取对象方法
	 * @param clazz
	 * @param methodName
	 * @param paramTypes
	 * @return
	 */
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes)
	{
		try
		{
			return clazz.getMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException ex)
		{
			return findDeclaredMethod(clazz, methodName, paramTypes);
		}
	}


	public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes){
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() != null)
			{
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		}
	}

	/**
	 * 获取对象属性值
	 * @param obj
	 * @param name
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Object getProperty(Object obj, String name) throws NoSuchFieldException {
		Object value = null;
		Field field = findField(obj.getClass(), name);
		if (field == null){
			throw new NoSuchFieldException("no such field [" + name + "]");
		}
		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		try {
			value = field.get(obj);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("获取对象属性值失败.",e);
		}
		field.setAccessible(accessible);
		return value;
	}

	/**
	 * 设置对象属性值
	 * @param obj
	 * @param name
	 * @param value
	 * @throws NoSuchFieldException
	 */
	public static void setProperty(Object obj, String name, Object value) throws NoSuchFieldException {
		Field field = findField(obj.getClass(), name);
		if (field == null){
			throw new NoSuchFieldException("no such field [" + name + "]");
		}
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try{
			field.set(obj, value);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		field.setAccessible(accessible);

	}

	/**
	 * 将对象属性转换为map
	 * @param obj
	 * @param map
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj, Map<String, Object> map){
		if(map == null){
			map = new HashMap<>();
		}
		if(Objects.nonNull(obj)){
			Class<?> clazz = obj.getClass();

			do {
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field :declaredFields){
					int mod = field.getModifiers();
					if(Modifier.isStatic(mod)){
						continue;
					}
					boolean accessible = field.isAccessible();
					field.setAccessible(true);
					try {
						map.put(field.getName(), field.get(obj));
					} catch (IllegalAccessException e) {
						log.warn("获取属性失败.属性名称:{}",field.getName(),e);
						//打印堆栈异常后继续运行
						e.printStackTrace();
					}
					field.setAccessible(accessible);
				}
				clazz = clazz.getSuperclass();
			}while (clazz != null);
		}
		return map;
	}

	/**
	 * 获得当前类的父类集合
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getSuperClassList(Class<?> clazz){
		List<Class<?>> clazzes = new ArrayList<>(3);
		clazzes.add(clazz);
		clazz = clazz.getSuperclass();
		while (clazz != null){
			clazzes.add(clazz);
			clazz = clazz.getSuperclass();
		}
		return Collections.unmodifiableList(clazzes);
	}
}
