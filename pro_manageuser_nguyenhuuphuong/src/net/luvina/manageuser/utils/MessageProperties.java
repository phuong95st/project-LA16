/**
 * Copyright(C) 2016 Luvina Software Company
 * MessageJaProperties.java, Aug 7, 2016 nhphuong
 */
package net.luvina.manageuser.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author nhphuong
 *
 */
@SuppressWarnings("unchecked")
public class MessageProperties {
	static private Map<String, String> data = new HashMap<String, String>();

    /**
     *
     */
    static {
        Properties prop = new Properties();
        try {
            prop.load(MessageProperties.class.getResourceAsStream(("/message_ja.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> en  = (Enumeration<String>)prop.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
            data.put(key, prop.getProperty(key));
        }
    }



    /**
     * getData from file properties
     * @param key key
     * @return String
     */
    static public String getMessage(String key) {
        String string = "";
        if (data.containsKey(key)) {
            string = data.get(key);
        }
        return string;
    }
}