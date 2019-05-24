package com.java.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.oracle.webservices.internal.api.message.PropertySet.Property;

public class ConnectionUtils {
	
	public static Connection getConnection(){
		String dburl = "jdbc:oracle:thin:@192.168.0.19:1521:orcl";
		String username = "zybyy";
		String password = "ZYBYY";
		Properties props = new Properties();
		props.put("user", username);
		props.put("password", password);
		props.put("remarksReporting","true");
		return getConnection(dburl, props);
	}

	public static void close(Connection con) {
		if(null != con){
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static void close(Connection con, Statement stat) {
		if(null != stat){
			try {
				stat.close();
			} catch (SQLException e) {
			}
		}
		
		close(con);
	}
	
	public static void close(Connection con, Statement stat,ResultSet rs) {
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		close(con, stat);
	}
	
	private static Connection getConnection(String dburl, String username, String password){
		try {
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dburl, username, password);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static Connection getConnection(String dburl, Properties info){
		try {
			String driver = "oracle.jdbc.OracleDriver";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dburl, info);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
