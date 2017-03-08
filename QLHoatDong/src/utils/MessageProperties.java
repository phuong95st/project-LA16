/**
 * Copyright(C) 2017 Luvina software company
 * MessageProperties.java, Feb 16, 2017 nguyenhuuphuong
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author nguyenhuuphuong
 *
 */
public class MessageProperties {
	private static Map<String, String> data = new HashMap<String, String>();

	static {
		Properties prop = new Properties();
		try {
			prop.load(new BufferedReader(new InputStreamReader(
					MessageProperties.class
							.getResourceAsStream("/message.properties"))));

		} catch (IOException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			data.put(key, prop.getProperty(key));
		}
	}

	/**
	 * Lấy data từ key
	 * 
	 * @param key
	 *            key
	 * @return data
	 */
	public static String getData(String key) {
		String string = "";
		if (data.containsKey(key)) {
			string = data.get(key);
		}
		return string;
	}
}
