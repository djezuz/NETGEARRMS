package com.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Router;
import com.mysql.jdbc.Statement;
import com.util.DBConnection;

public class QueryData {
	//所有router Information
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
			pst = conn.prepareStatement(sql);
			//pst.setInt(0, id);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				Router router=new Router();
				router.setId(rs.getInt("id"));
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
	//关于Alert信息
	public static  List querylevel(String  id){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  ROUTER";
		List li = new ArrayList();
		try {
			 if(!id.equalsIgnoreCase("")){
				sql=sql+" where  serial like '%"+id+"%' and level='Alert' ";
			}else if(id.equalsIgnoreCase("")) {
				sql=sql+" where  level='Alert' order by id DESC";
			}
			pst = conn.prepareStatement(sql);
			//pst.setInt(0, id);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				Router router=new Router();
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
	//最新时间的记录(id最大的记录)
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
	//三天前的历史数据
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
		//七天前的历史数据
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
		//30天前的历史数据
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
				
	  //停止心跳的记录数据
				public List queryStopHeartbeat(){
					Connection conn = DBConnection.getConnection();
					PreparedStatement pst = null;
					ResultSet rs = null;
					String  sql="select * FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>STR_TO_DATE(time,'%a %b %d %H:%i:%s HKT %Y') and id=(SELECT max(id) FROM router r order by id)";
					List list = new ArrayList();
					try {
						pst = conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs!=null){
						while(rs.next()){
							Router router=new Router();
				            router.setSerial(rs.getString("serial"));	
							router.setTime(rs.getString("time"));
							router.setClearedBy(rs.getString("clearedBy"));
							
							list.add(router);
						}
						}
					}catch (SQLException e) {
						e.printStackTrace();
					} finally{
						DBConnection.free(rs, null, pst, conn);
					}		
				
					return  list;
					
				}		
		//警告数据			
		public List queryAlertInfo(){
			Connection conn =DBConnection.getConnection();
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="SELECT *  from router WHERE  level='alert' order by id DESC";
			List list1 =new ArrayList();
			try {
				pst=conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
					while (rs.next()){
						Router router=new Router();
						router.setSerial(rs.getString("serial"));
						router.setTime(rs.getString("time"));
						list1.add(router);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBConnection.free(rs, null, pst, conn);
			}
			
			return list1;
				
		} 		
		// 删除一条记录
	public void deleteInfo(int id)
		{
		Connection conn = null;
		PreparedStatement pst=null;
		try {
		conn = DBConnection.getConnection();
		String sql = "delete from router where id="+id;
		pst=conn.prepareStatement(sql);
		System.out.println(sql);
		pst.executeUpdate();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
		
	public void saveInfo(Router router){
		Connection conn = null;
		PreparedStatement pst=null;
		try {
		conn = DBConnection.getConnection();
		String sql = "insert into router values ('"+router.getSerial()+"','"+router.getLevel()+"','"+router.getMessage()+"','"+router.getTime()+"')";
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();

		} catch (Exception ex) {
		ex.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
				
	public static void main(String[] arg0){
		query("all");
	}
	
}
