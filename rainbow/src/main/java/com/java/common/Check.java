package com.java.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	// 用户名  以字母开头，长度在6~18之间，只能包含字母、数字和下划线	
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
	
	// 昵称 长度在6~18之间，只能包含字母、数字、下划线和中文
	public static final String REGEX_NICKNAME = "^[a-zA-Z0-9_\u4e00-\u9fa5]{6,18}$";
	
	// 密码 长度在6~18之间，只能包含字母、数字、下划线
	public static final String REGEX_PASSWORD = "^\\w{6,18}$";
	
	// 邮箱
	public static final String REGEX_EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
	
	// 中文
	public static final String REGEX_CHINESE = "^[\u4E00-\u9FA5]+$";
	
	// QQ 5到10的任意数字
	public static final String REGEX_QQ = "^\\d{5,10}$";
	
	// IP
	public static final String REGEX_IP = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}";
	
	// URL 可以有端口，可以是ip
	public static final String REGEX_URL = "^(http|https)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
			+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
			+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
			+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
			+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
			+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
			+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
			+ "[a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
	
	// 非零正整数
	public static final String REGEX_POSITIVE_INTEGER = "^\\+?[1-9][0-9]*$";
	
	// 非零负整数
	public static final String REGEX_NEGATIVE_INTEGER = "^\\-[1-9][0-9]*$";
	
	// 钱  可以有且最多两个小数，允许隔三位一个逗号，允许以0开头，不允许负数
	public static final String REGEX_MONEY = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";
	
	// 手机号码
	public static final String REGEX_CELL_PHONE = "";
	
	// 固定电话号码
	public static final String REGEX_PHONE = "";
	
	
	public static boolean check(String regex, String arg){
		if (null == arg) {
			return true;
		}
		return Pattern.matches(regex, arg);
	}
	
	public static boolean requiredCheck(String regex, String arg){
		if (null == arg) {
			return false;
		}
		return Pattern.matches(regex, arg);
	}
	

	/**
	 * 判断字符串是否含有中文
	 */
	public static Boolean hasChinese(String arg) {
		if (null == arg) {
			return false;
		}
		String regex = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(arg);
		return m.find();
	}
	
	public static void main(String[] args) {
		System.out.println(check(REGEX_IP, "256.0.0.0"));
	}
}
