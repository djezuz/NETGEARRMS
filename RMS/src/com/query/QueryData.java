package com.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Router;
import com.util.DBConnection;

public class QueryData {
	//查询所有router Information
	public static  List query(String  id){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  ROUTER";
		List li = new ArrayList();
		try {
			if (id.equalsIgnoreCase("level")){
				sql=sql+" where  level ='Alert' ";
			}else if(!id.equalsIgnoreCase("all")){
				sql=sql+" where  serial like '%"+id+"%'";
			}
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			//pst.setInt(0, id);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				Router router=new Router();
				System.out.println(rs.getString("serial"));
	            router.setSerial(rs.getString("serial"));	
				router.setLevel(rs.getString("level"));
				router.setMessage(rs.getString("message"));
				router.setTime(rs.getString("time"));
				router.setClearedBy(rs.getString("clearedBy"));
				
				li.add(router);
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  li;
	}
	//查询关于Alert信息
	public static  List querylevel(String  id){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  ROUTER";
		List li = new ArrayList();
		try {
			 if(!id.equalsIgnoreCase("")){
				sql=sql+" where  serial like '%"+id+"%' and level='Alert'";
			}else if(id.equalsIgnoreCase("")) {
				sql=sql+" where  level='Alert'";
			}
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			//pst.setInt(0, id);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				Router router=new Router();
				System.out.println(rs.getString("serial"));
	            router.setSerial(rs.getString("serial"));	
				router.setLevel(rs.getString("level"));
				router.setMessage(rs.getString("message"));
				router.setTime(rs.getString("time"));
				router.setClearedBy(rs.getString("clearedBy"));
				
				li.add(router);
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  li;
	}
	//查询最新时间的记录(id最大的记录)
	public  String  queryLast(){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT time from router WHERE id=(SELECT MAX(id) from router)";
		String time="";
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				time=rs.getString("time");
			
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  time;
		
	}
	//查询三天前的历史数据
		public List querypastThree(){
			Connection conn = DBConnection.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=3;";
			List li = new ArrayList();
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
		            router.setSerial(rs.getString("serial"));	
					router.setMessage(rs.getString("message"));
					router.setTime(rs.getString("time"));
					router.setClearedBy(rs.getString("clearedBy"));
					
					li.add(router);
				}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(rs, null, pst, conn);
			}		
		
			return  li;
			
		}
		//查询七天前的历史数据
				public List queryPastSeven(){
					Connection conn = DBConnection.getConnection();
					PreparedStatement pst = null;
					ResultSet rs = null;
					String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
					List li = new ArrayList();
					try {
						pst = conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs!=null){
						while(rs.next()){
							Router router=new Router();
				            router.setSerial(rs.getString("serial"));	
							router.setMessage(rs.getString("message"));
							router.setTime(rs.getString("time"));
							router.setClearedBy(rs.getString("clearedBy"));
							
							li.add(router);
						}
						}
					}catch (SQLException e) {
						e.printStackTrace();
					} finally{
						DBConnection.free(rs, null, pst, conn);
					}		
				
					return  li;
					
				}
		//查询30天前的历史数据
				public List queryPastThirty(){
					Connection conn = DBConnection.getConnection();
					PreparedStatement pst = null;
					ResultSet rs = null;
					String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=30;";
					List li = new ArrayList();
					try {
						pst = conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs!=null){
						while(rs.next()){
							Router router=new Router();
				            router.setSerial(rs.getString("serial"));	
						
							router.setMessage(rs.getString("message"));
							router.setTime(rs.getString("time"));
							router.setClearedBy(rs.getString("clearedBy"));
							
							li.add(router);
						}
						}
					}catch (SQLException e) {
						e.printStackTrace();
					} finally{
						DBConnection.free(rs, null, pst, conn);
					}		
				
					return  li;
					
				}
	
        //大于一个小时的数据--错过的心跳数据
				public  String  queryMoreOne(){
					Connection conn = DBConnection.getConnection();
					PreparedStatement pst = null;
					ResultSet rs = null;
					String  sql="select time FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>STR_TO_DATE(time,'%a %b %d %H:%i:%s HKT %Y') and id=(SELECT max(id) FROM router r order by id)";
					String time="";
					try {
						pst = conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs!=null){
							while(rs.next()){
								time=rs.getString("time");
							
							}
						}
					}catch (SQLException e) {
						e.printStackTrace();
					} finally{
						DBConnection.free(rs, null, pst, conn);
					}		
				
					return  time;
					
				}
					
				
	public static void main(String[] arg0){
		query("all");
	}
	
}
