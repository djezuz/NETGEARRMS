package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.HeartBeat;
import com.util.DBConnection;

public class Mydesign {

	private Connection conn = null;

	public Mydesign() {
		conn = DBConnection.getConnection();
	}

	public String login(String serial_1, String level_1, String message_1,
			String time_1, String clearedBy_1) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int xx = 0;
		String sql = "insert into  router(serial,level,message,time,clearedBy) values(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, serial_1);
			pst.setString(2, level_1);
			pst.setString(3, message_1);
			pst.setString(4, time_1);
			pst.setString(5, clearedBy_1);
			xx = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, null, pst, conn);
		}
		System.out.println(serial_1);
		return "success," + xx;
	}
	
	//
	public String saveTmpvalue(String value) {

		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql=" select  *  from logreturnvalue ";
		int xx = 0;
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
				if(rs.next()){
					sql ="update logreturnvalue set logvalue='"+value+"' where id='12345678'";
					try {
						pst = conn.prepareStatement(sql);
						xx = pst.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						return "fail," + xx;
					}
				}else{
					sql = "insert into  logreturnvalue(id,logvalue) values(?,?)";
					try {
						pst = conn.prepareStatement(sql);
						pst.setString(1, "12345678");
						pst.setString(2, value);
//						pst.setString(3, new Date());
						xx = pst.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						return "fail," + xx;
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DBConnection.free(rs, null, pst, conn);
		}
		return "success," + xx;
	}

	public  String  queryLast(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT * from logreturnvalue WHERE id='12345678'";
		String time="";
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				time=rs.getString("logvalue");
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  time;
		
	}
	
	public void finsh() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getEncoding(String str) {
		String encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), "utf-8"))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), "utf-8"))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), "utf-8"))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), "utf-8"))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}

		return "";
	}
	
	/**
	 * 添加心跳记录
	 * @param deviceId 设备ID
	 * @return
	 */
	public boolean addHeartBeat(String deviceId){
		
		boolean flag=false;
		PreparedStatement pst = null;
		int xx = 0;
		String sql = "insert into  heartbeat(deviceId,createDatetime) values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, deviceId==null?"":deviceId.trim());
			pst.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			xx = pst.executeUpdate();
			if(xx>0){
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		} finally {
			DBConnection.free(null, null, pst, conn);
		}
		
		return flag;
		
	}
	
	/**
	 * 查询所有心跳记录
	 * @return
	 */
	public List<HeartBeat> queryAllHeartbeat(){
		
		List<HeartBeat> list=new ArrayList<HeartBeat>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from heartbeat order by id desc";
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
				while(rs.next()){
					HeartBeat hb=new HeartBeat();
					hb.setId(rs.getInt("id"));
					hb.setSerial(rs.getString("serial"));
					hb.setCreateDatetime(rs.getDate("createDatetime"));
					list.add(hb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, null, pst, conn);
		}
		return list;
		
	}
	
	public List<String> queryLostHeartbeat(){
		
		List<String> list=new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from heartbeat order by id desc";
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
				while(rs.next()){
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, null, pst, conn);
		}
		return list;
		
	}
	

}
