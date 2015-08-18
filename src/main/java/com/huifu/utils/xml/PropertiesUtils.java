/**
 * 
 */
package com.huifu.utils.xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:51:47
 * @author jack.liu 
 */
public class PropertiesUtils {
	
	/** 正则表达式 用于匹配属性的第一个字母 [a-zA-Z]} **/
	private static final String REGEX = "[a-zA-Z]";
	
	/**
	 * 获取指定方法的参数名
	 * 
	 * @param method
	 *            要获取参数名的方法
	 * @return 按参数顺序排列的参数名列表
	 */
	public static String[] getMethodParameterNamesByAnnotation(Method method) {
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		if (parameterAnnotations == null || parameterAnnotations.length == 0) {
			return null;
		}
		String[] parameterNames = new String[parameterAnnotations.length];
		int i = 0;
		for (Annotation[] parameterAnnotation : parameterAnnotations) {
			for (Annotation annotation : parameterAnnotation) {
				if (annotation instanceof Param) {
					Param param = (Param) annotation;
					parameterNames[i++] = param.value();
				}
			}
		}
		return parameterNames;
	}
	
	/**
	 * 获取某类中List对应的类型
	 * @param object
	 * @param clazz
	 */
	public static String getListObject(Object object) {
		Field[] fields = object.getClass().getDeclaredFields(); // 得到所有的fields

		for (Field f : fields) {
			Class fieldClazz = f.getType();
			if (fieldClazz.isAssignableFrom(List.class)){ // 【2】
				Type fc = f.getGenericType(); 
				if (fc == null){
					continue;
				}
				
				if (fc instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) fc;
					Class genericClazz = (Class) pt.getActualTypeArguments()[0]; 
					return genericClazz.getName();
				}
			}
		}
		return null;
	}

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public static Object getFieldValue(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取属性名数组
	 * 
	 * @param object
	 * @return
	 */
	public static List<String> getFiledName(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		List<String> fieldNames = new ArrayList<String>();

		for (Field field : fields)
			fieldNames.add(field.getName());

		return fieldNames;
	}

	public static Field[] getFileds(Object object) {
		return object.getClass().getDeclaredFields();
	}

	public static void setAttrValue(Object obj, String attribute, Object value) {
		String methodName = convertToMethodName(attribute, obj.getClass(), true);
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			/**
			 * 因为这里只是调用bean中属性的set方法，属性名称不能重复 所以set方法也不会重复，所以就直接用方法名称去锁定一个方法
			 * （注：在java中，锁定一个方法的条件是方法名及参数）
			 * **/
			if (method.getName().equals(methodName)) {
				Class[] parameterC = method.getParameterTypes();
				try {
					/**
					 * 如果是基本数据类型时（如int、float、double、byte、char、boolean）
					 * 需要先将Object转换成相应的封装类之后再转换成对应的基本数据类型 否则会报
					 * ClassCastException
					 **/
					if (parameterC[0] == int.class) {
						method.invoke(obj, ((Integer) value).intValue());
						break;
					} else if (parameterC[0] == float.class) {
						method.invoke(obj, ((Float) value).floatValue());
						break;
					} else if (parameterC[0] == double.class) {
						method.invoke(obj, ((Double) value).doubleValue());
						break;
					} else if (parameterC[0] == byte.class) {
						method.invoke(obj, ((Byte) value).byteValue());
						break;
					} else if (parameterC[0] == char.class) {
						method.invoke(obj, ((Character) value).charValue());
						break;
					} else if (parameterC[0] == boolean.class) {
						method.invoke(obj, ((Boolean) value).booleanValue());
						break;
					} else {
						method.invoke(obj, parameterC[0].cast(value));
						break;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String convertToMethodName(String attribute, Class objClass, boolean isSet) {
		/** 通过正则表达式来匹配第一个字符 **/
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(attribute);
		StringBuilder sb = new StringBuilder();
		/** 如果是set方法名称 **/
		if (isSet) {
			sb.append("set");
		} else {
			/** get方法名称 **/
			try {
				Field attributeField = objClass.getDeclaredField(attribute);
				/** 如果类型为boolean **/
				if (attributeField.getType() == boolean.class || attributeField.getType() == Boolean.class) {
					sb.append("is");
				} else {
					sb.append("get");
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		/** 针对以下划线开头的属性 **/
		if (attribute.charAt(0) != '_' && m.find()) {
			sb.append(m.replaceFirst(m.group().toUpperCase()));
		} else {
			sb.append(attribute);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {

		PropertiesUtils p = new PropertiesUtils();

		Class<PropertiesUtils> clazz = PropertiesUtils.class;
		Method method = clazz.getDeclaredMethod("method1", String.class, String.class);
		String[] parameterNames = PropertiesUtils.getMethodParameterNamesByAnnotation(method);
		System.out.println(Arrays.toString(parameterNames));
	}
}
