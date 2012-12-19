package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.util.DBConnection;

public class Mydesign {
	public static String login(String serial_1,String level_1,String message_1,String  time_1,String clearedBy_1){
		
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int xx = 0;
		String  sql="insert into  router(serial,level,message,time,clearedBy) values(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString( 1, serial_1);	
			pst.setString( 2, level_1);	
			pst.setString( 3, message_1);	
			pst.setString( 4, time_1);	
			pst.setString( 5, clearedBy_1);	
		    xx = pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
		System.out.println(serial_1);
		return "success,"+xx;
	}
	
	

}
