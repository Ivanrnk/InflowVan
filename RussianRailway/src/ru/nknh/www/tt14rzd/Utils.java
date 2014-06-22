package ru.nknh.www.tt14rzd;

import java.io.File;

class Utils {
	public final static String xls = "xls";
	public final static String xlsx = "xlsx";

	/*
	 * Get extension of a File
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1, s.length()).toLowerCase();
		}
		return ext;
	}
}
