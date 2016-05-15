/**
 * 
 */
package com.sprSecurity.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;

public class ClassUtil {

	private static final Logger LOG = Logger.getLogger(ClassUtil.class);

	public static String getSimpleClassName(String className) {
		return ClassUtils.getShortClassName(className);
	}

	public static Object getFieldValue(Field field, Object obj) {
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static Class<?> getClass(String className) {
		try {
			Class<?> clazz = (Class<?>) Class.forName(className);
			return clazz;
		} catch (ClassNotFoundException e) {
			String msg = "Class[" + className + "] is not found";
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		}
	}

	public static URL getClassUrlLocation(Class<?> clazz) {
		String classLocation = clazz.getName().replace('.', '/') + ".class";
		URL classLocationUrl = clazz.getClassLoader().getResource(classLocation);

		String protocol = classLocationUrl.getProtocol();
		classLocation = classLocationUrl.getFile();
		String packageLocation = clazz.getPackage().getName().replace('.', '/');
		int indexOfPackagePath = classLocation.lastIndexOf(packageLocation);
		classLocation = protocol + ":" + classLocation.substring(0, indexOfPackagePath);

		try {
			return new URL(classLocation);
		} catch (MalformedURLException e) {
			String msg = "MalformedURLException is occured while instiate URL from string[" + classLocation + "]";
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		}
	}

	public static File getClassDirectoryFileLocation(Class<?> clazz) {
		String classLocation = clazz.getName().replace('.', '/') + ".class";
		URL classLocationUrl = clazz.getClassLoader().getResource(classLocation);

		classLocation = classLocationUrl.getFile();
		String packageLocation = clazz.getPackage().getName().replace('.', '/');
		int indexOfPackagePath = classLocation.lastIndexOf(packageLocation);
		int indexOfProtocal = classLocation.indexOf('/');
		classLocation = classLocation.substring(indexOfProtocal + 1, indexOfPackagePath);

		return new File(classLocation);

	}

	public static List<Field> getDTOClassFields(Class<?> dtoClass) {
		List<Field> fieldsList = new ArrayList<Field>();
		for (Field field : Arrays.asList(dtoClass.getSuperclass().getDeclaredFields())) {
			if (!Modifier.isStatic(field.getModifiers())) {
				fieldsList.add(field);
			}
		}

		for (Field field : Arrays.asList(dtoClass.getDeclaredFields())) {
			if (!Modifier.isStatic(field.getModifiers())) {
				fieldsList.add(field);
			}
		}
		return fieldsList;
	}

	public static void addClassPathUrlToClassLoader(URL classUrl, URLClassLoader classLoader) {

		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(classLoader, new Object[] { classUrl });
		} catch (Throwable e) {
			String msg = "Failed to add classUrl[" + classUrl + "]";
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		}

	}

	public static void addClassPathUrlToSystemClassLoader(URL classUrl) {
		addClassPathUrlToClassLoader(classUrl, (URLClassLoader) ClassLoader.getSystemClassLoader());
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			String msg = "Failed to Instante class[" + clazz + "]";
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		} catch (IllegalAccessException e) {
			String msg = "Failed to Instante class[" + clazz + "]";
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		}
	}
}
