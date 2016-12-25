package com.nsyun.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

	private static ResourceBundle bundle = null;

	static {
		bundle = ResourceBundle.getBundle("application", Locale.CHINESE);
	}

	public static String getProperty(String key) {
		return bundle.getString(key);
	}
	
}
