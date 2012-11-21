package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/router";
	private static String username = "root";
	private static String password = "root";

	
	static{		
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获得连接
	public static Connection getConnection(){
		Connection conn = null;
		try {			
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}
	
	
	//释放资源
	public static void free(ResultSet rs,Statement st,PreparedStatement pst,Connection conn){
		try{			
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(st!=null){
				st.close();
				st = null;
			}
			if(pst!=null){
				pst.close();
				pst = null;
			}
			if(conn!=null){
				conn.close();
				conn = null;
			}			
		} catch(SQLException e){
			e.printStackTrace();
		}	
	}
	
}
