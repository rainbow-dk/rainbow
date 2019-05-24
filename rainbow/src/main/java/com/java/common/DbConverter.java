package com.java.common;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DbConverter {
	/**
	 * 将ResultSet数据转换成List<Map>
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String,Object>> getDataTable(ResultSet rs) throws SQLException {
		List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String,Object> item = new HashMap<String,Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnTypeName(i).equals("CLOB")) {
					Clob colClob = rs.getClob(i);
					if (colClob == null) {
						putToMap(item, rsmd.getColumnName(i).toUpperCase(), "");
					} else {
						putToMap(item, rsmd.getColumnName(i).toUpperCase(),
								getClobtoString(rs.getClob(i)));
					}
				} else if (rsmd.getColumnTypeName(i).equals("DATE")) {
					Date d = null;
					try {
						d = new Date(rs.getTimestamp(i).getTime());
					} catch (Exception ex) {
						d = null;
					}
					if (d == null) {
						putToMap(item, rsmd.getColumnName(i).toUpperCase(), "");
					} else {
						putToMap(item, rsmd.getColumnName(i).toUpperCase(), d);
					}
				} else {
					putToMap(item, rsmd.getColumnName(i).toUpperCase(), rs
							.getObject(i));
				}
			}
			ls.add(item);
		}

		return ls;
	}

	private static void putToMap(Map<String,Object> item, String key, Object value) {
		if (value != null) {
			item.put(key, value);
		}
	}

	private static String getClobtoString(Clob clob) throws SQLException {
		return clob.getSubString(((long) 1), ((int) (clob.length())));
	}
	
}
