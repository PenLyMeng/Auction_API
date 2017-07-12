package org.khmeracademy.auction.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class HrdGeneratorUI {

	// Generate Private Field
	public static List<String> generateClassProperty(String paramFromSerializeArray) {
		List<String> str = new ArrayList<>();
		for (String s : paramFromSerializeArray.split("=&")) {
			s = s.toLowerCase();
			if (s.contains("id") || s.contains("age")) {
				str.add("private int " + s + ";");
			} else {
				str.add("private String " + s + ";");
			}
		}
		return str;
	}

	// Create Form Data from Multipart request
	@SuppressWarnings("unchecked")
	public static MultiValueMap<String, Object> createFormData(Object obj) throws Exception {

		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();

		// Get All Fields in Class
		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {
			// Set Field to be accesable
			field.setAccessible(true);
			//System.out.println(field.getName() + " : " + field.get(obj));
			
			// Checking Field Type
			if (field.getType() == String.class) {
				// Encode to avoid showing ????? Character on Sending to API
				String encodedValue = URLEncoder.encode(field.get(obj) + "", "UTF-8");
				// Add Param & Value
				formData.add(field.getName(), encodedValue);

			} else if (field.getType() == int.class || field.getType() == Integer.class){
				formData.add(field.getName(), (int) field.get(obj));
				
			} else if (field.getType() == long.class || field.getType() == Long.class){
				formData.add(field.getName(), (long) field.get(obj));
				
			} else if (field.getType() == double.class || field.getType() == Double.class){
				formData.add(field.getName(), (double) field.get(obj));
				
			} else if (field.getType() == float.class || field.getType() == Float.class){
				formData.add(field.getName(), (float) field.get(obj));
				
			} else if (field.getType() == boolean.class || field.getType() == Boolean.class){
				formData.add(field.getName(), (boolean) field.get(obj));
				
			} else if(field.getType() == MultipartFile.class || field.getType() == CommonsMultipartFile.class) { // Single File
				MultipartFile file = ((MultipartFile) (field.get(obj)));
				formData.add(field.getName(), new FileMessageResource(file.getBytes(), file.getOriginalFilename()));

			} else if (field.getType() == List.class) { // Check List Type
				// Get Generic Type from current List
				ParameterizedType genericType = (ParameterizedType) field.getGenericType();
				// Get Generic Class
				Class<?> genericClass = (Class<?>) genericType.getActualTypeArguments()[0];

				// Check Generic Class
				// Multiple File
				if (genericClass == MultipartFile.class  || genericClass == CommonsMultipartFile.class) {
					List<MultipartFile> files = ((List<MultipartFile>) (field.get(obj)));
					for (MultipartFile file : files) {
						formData.add(field.getName(),
								new FileMessageResource(file.getBytes(), file.getOriginalFilename()));
					}

				} else if (genericClass == String.class) { // If List<String>
					List<String> values = ((List<String>) (field.get(obj)));
					for (String str : values) {
						formData.add(field.getName(), str);
					}
				}
			} else {
				formData.add(field.getName(), field.get(obj));
			}
		}
		return formData;
	}

	@SuppressWarnings("rawtypes")
	public static Object decodeUnicodeFromObject(Object obj) throws Exception {
		Class<?> cls = obj.getClass();
		Method method;
		// String parameter
		Class[] paramString = new Class[1];
		paramString[0] = String.class;

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getType() == String.class) {
				String decodedValue = URLDecoder.decode(field.get(obj) + "", "UTF-8");
				method = cls.getDeclaredMethod(
						"set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1),
						paramString);
				method.invoke(obj, decodedValue);
			}
		}
		return obj;
	}
	
	public static void main(String[] args) {
		for (String string : generateClassProperty("productname=&prodescript=&category=&brand=&qty=&prostatus")) {
			System.out.println(string);
		}
	}
}
