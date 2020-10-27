package com.pinhuba.ueditor.config;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Utils {

	public static Set<String> getSet(final String stringList, final String delimiter) {
		if (isEmpty(delimiter))
			throw new IllegalArgumentException("Argument 'delimiter' shouldn't be empty!");
		if (isEmpty(stringList))
			return new HashSet<String>();

		Set<String> set = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(stringList, delimiter);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (isNotEmpty(tmp)) // simple empty filter
				set.add(tmp.toLowerCase());
		}
		return set;
	}

	public static Set<String> getSet(final String stringList) {
		return getSet(stringList, "|");
	}

	public static boolean isEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(final String str) {

		if (isEmpty(str))
			return true;

		for (char c : str.toCharArray()) {
			if (!Character.isWhitespace(c))
				return false;
		}

		return true;
	}

	public static boolean isNotBlank(final String str) {
		return !isBlank(str);
	}
}