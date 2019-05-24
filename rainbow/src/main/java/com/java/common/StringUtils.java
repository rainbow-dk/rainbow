package com.java.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

	/**
	 * 如果传入的参数为null或者"null",返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String NullToSpace(String str) {
		if (str == null || str.equals("null")) {
			return "";
		} else {
			return str.toString();
		}
	}

	public static String NullToEmpty(String str) {
		if (str == null || str.equals("null")) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 如果传入的参数为null或者"null",返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String NullToSpace(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return NullToSpace(obj.toString());
		}
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		else
			return false;
	}

	/**
	 * 如果传入的参数为null或者"null",返回0，其他转为float类型后返回
	 * 
	 * @param obj
	 * @return
	 */
	public static float NullToZero(Object obj) {
		if (obj == null) {
			return 0f;
		} else {
			String objStr = obj.toString();
			if (objStr.equals("null")) {
				return 0f;
			} else {
				String regExp = "^\\d+(\\.\\d+)?$";
				if (validateStringWithReg(objStr, regExp)) {
					return Float.parseFloat(objStr);
				} else {
					return 0f;
				}
			}
		}
	}

	/**
	 * 验证字符串是否符合指定的正则表达式
	 * 
	 * @author 朱强
	 */
	public static boolean validateStringWithReg(String content, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		if (m.find()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 验证字符串是否符合指定的正则表达式，如果满足并取出该规则字符串
	 * 
	 * @author 朱强
	 */
	public static String getStringWithReg(String content, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		if (m.find()) {
			String target = content.substring(m.start(), m.end());
			return target;
		} else {
			return "";
		}
	}

	/**
	 * 四舍五入，把数据转为浮点数
	 * 
	 * @author 朱强
	 */
	public static String toFloat(float data, String patten) {
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(patten);
		return format.format(data);
	}

	//四舍五入保留N位小数
		public static double toDouble(double d,int n) {
			int pama = 1;
			for(int k = 0;k<n;k++) {
				pama = pama*10;
			}
			long l = Math.round(d*pama);
			return Double.parseDouble(String.valueOf(l))/pama;
		}


	/**
	 * 四舍五入，取字符串形式小数的几位小数，针对那种BigDecimal而用
	 * 
	 * @author 朱强
	 */
	public static String toFloat(String data, int precision) {
		if (data.equals("0E-10")) {
			return "0";
		} else {
			String before = data.substring(0, data.indexOf("."));
			String after = data.substring(data.indexOf(".") + 1).substring(0, precision);
			return before + "." + after;
		}
	}

	/**
	 * 将双引号替换成单引号
	 * 
	 * @param src
	 * @return
	 */
	public static String quoteDouble2Single(String src) {
		if (src == null && src.trim().equals(""))
			return "";
		src = src.replace("\"", "'");
		return src;
	}

	/**
	 * 将clob类型数据转换成字符串
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 */
	public static String clobtoString(Object clob) throws SQLException {
		Clob tmp = (Clob) clob;
		return clobtoString(tmp);
	}

	/**
	 * 将clob类型数据转换成字符串
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 */
	public static String clobtoString(Clob clob) throws SQLException {
		try {
			if (clob != null) {
				return clob.getSubString(((long) 1), ((int) (clob.length())));
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static String htmlTag2Empty(String html) {
		String regAll = "<p\\s*.*>\\s*.*\\s*</p>";
		String myRegex = "<[^>]*>";
		Pattern myPattern = Pattern.compile(regAll, Pattern.CASE_INSENSITIVE);
		Matcher myMatcher = myPattern.matcher(html);
		String myStr = "";
		while (myMatcher.find()) {
			myStr = myMatcher.group();
		}
		if (myStr == null || myStr.trim().equals(""))
			return html;
		String now = myStr.replaceAll(myRegex, "");
		return now;
	}

	/**
	 * 处理富文本框的方法
	 * 
	 * @param html
	 * @return
	 */
	public static String commentQuote2EmptySingle(String html) {
		if (html != null) {
			html = html.replace("<!--StartFragment-->", "");
			html = html.replace("<!--EndFragment-->", "");
			html = html.replace("<!--", "");
			html = html.replace("-->", "");
			html = html.replaceAll("\"", "'");
		}
		return html;
	}

	/**
	 * 对象转换字符串，如果对象为空将返回空
	 * 
	 * @param ob
	 * @return
	 */
	public static String objectToString(Object ob) {
		if (ob != null) {
			return ob.toString();
		} else {
			return "";
		}
	}

	/**
	 * 得到百分比
	 * 
	 * @param a分子
	 * @param b分母
	 * @return结果
	 */
	public static String percentAge(Object a, Object b) {
		if (a == null || a.toString().equals("") || b.toString().equals("0")) {
			return "0";
		}
		if (b == null || b.toString().equals("") || b.toString().equals("0")) {
			return "0";
		}

		double aa = Double.valueOf(a.toString());
		double bb = Double.valueOf(b.toString());

		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(aa / bb * 100);
	}

	/**
	 * 用于生成一个唯一标识
	 * 
	 * @return
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString();
		return uid;
	}

	/**
	 * 获取异常堆栈信息
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		t.printStackTrace(pw);
		return writer.toString();
	}

	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	public static String toDelimitedString(Object[] arr, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(arr)) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(prefix).append(arr[i]).append(suffix);
		}
		return sb.toString();
	}

	public static String toDelimitedString(Object[] arr, String delim) {
		return toDelimitedString(arr, delim, "", "");
	}

	public static String toCommaDelimitedString(Object[] arr) {
		return toDelimitedString(arr, ",");
	}

	public static String toDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	public static String toDelimitedString(Collection<?> coll, String delim) {
		return toDelimitedString(coll, delim, "", "");
	}

	public static String toCommaDelimitedString(Collection<?> coll) {
		return toDelimitedString(coll, ",");
	}

	public static boolean hasLength(String str) {
		return (str != null) && (str.length() > 0);
	}

	private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			while (i < len)
				if (Character.isWhitespace(str.charAt(i))) {
					if ((match) || (preserveAllTokens)) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
		} else if (separatorChars.length() == 1) {
			char sep = separatorChars.charAt(0);
			while (i < len)
				if (str.charAt(i) == sep) {
					if ((match) || (preserveAllTokens)) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
		} else {
			while (i < len) {
				char sep;
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if ((match) || (preserveAllTokens)) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					lastMatch = false;
					match = true;
					i++;
				}
			}
		}
		if ((match) || ((preserveAllTokens) && (lastMatch))) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String[] split(String str) {
		return split(str, null, -1);
	}

	public static String[] split(String str, String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	public static String[] split(String str, String separatorChars, int max) {
		return splitWorker(str, separatorChars, max, false);
	}

	public static String getUperCaseFirstChar(String str) {
		str = str.toLowerCase();
		String[] sts = str.split("_");
		str = "";
		for (String st : sts) {
			str += st.substring(0, 1).toUpperCase() + (st.substring(1) == null ? "" : st.substring(1));
		}
		return str;
	}
	
}