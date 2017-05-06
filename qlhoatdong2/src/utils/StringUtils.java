/**
 * Copyright(C) 2017 Luvina software company
 * StringUtils.java, 25-04-2017 Tran
 */
package utils;

import java.text.Normalizer;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * 
 * @author Tran
 * 
 */
public class StringUtils {
	public static String removeAccent(String s) {

		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public static String escapeFullName(String fullName) {
		String nameAccent = removeAccent(fullName);
		StringTokenizer st = new StringTokenizer(nameAccent.trim(), " ");
		int countTokens = st.countTokens();
		if (countTokens > 1) {
			int i = 1;
			while (st.hasMoreElements()) {
				if (i == countTokens) {
					return st.nextToken().toLowerCase();
				}
				st.nextToken();
				i++;
			}
		}

		return st.nextToken().toLowerCase();
	}

}
