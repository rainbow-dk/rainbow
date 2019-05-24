package com.java.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 根据长度排序，最长的在前面
 * @author gyp
 *
 */
public class LengthComparator implements Comparator<String>{

	public int compare(String o1, String o2) {
		return o2.length() - o1.length();
	}
	
	public static void main(String[] args) {
		ArrayList<String> t = new ArrayList<String>();
		t.add("12");
		t.add("1");
		t.add("123");
		Collections.sort(t, new LengthComparator());
		System.out.println(t);
	}
}
