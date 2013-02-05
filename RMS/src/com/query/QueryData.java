package com.query;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.entity.LassHeartbeat;
import com.entity.Router;
import com.util.DBConnection;

public class QueryData {
	//所有router Information
	public static  List query(String  id){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  router";
		List li = new ArrayList();
		try {
			if (id.equalsIgnoreCase("level")){
				sql=sql+" where  level ='Alert' ";
			}else if(!id.equalsIgnoreCase("all")){
				sql=sql+" where  serial like '%"+id+"%' order by time desc";
			}
			//MessageDialog.openError(new Shell(),"SQL","Query SQL  =  "+sql);
			pst = conn.prepareStatement(sql);
			//MessageDialog.openError(new Shell(),"SQL","开始  1 "+pst);
			rs=pst.executeQuery();
			//MessageDialog.openError(new Shell(),"SQL","开始 2");
			if(rs!=null){
				//System.out.println("query.siz  =  "+rs.getRow());
				//MessageDialog.openError(new Shell(),"first date","query.siz  =  "+rs.getRow());
			while(rs.next()){
				Router router=new Router();
				router.setId(rs.getInt("id"));
	            router.setSerial(rs.getString("serial"));	
				router.setLevel(rs.getString("level"));
				router.setMessage(rs.getString("message"));
				if(rs.getTimestamp("time")!=null){
					router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
				}
				router.setClearedBy(rs.getString("clearedBy"));
				router.setCaseid(rs.getString("caseid"));
				router.setStatus(rs.getInt("status"));
				router.setCasedby(rs.getString("casedby"));
				//insert into  router(serial,level,message,time,clearedBy) values('2UM11AEK006CB','',?,?,?)
				li.add(router);
			}
			}
		}catch (SQLException e) {
			//MessageDialog.openError(new Shell(),"first date","query fail "+e.getMessage());
			System.out.println("query fail ");
			e.printStackTrace();
			//MessageDialog.openError(new Shell(),"first date","query fail "+e.toString());
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
				if(rs.getTimestamp("time")!=null){
					router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
				}
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
				if(rs.getTimestamp("time")!=null){
					time=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time"));
				}
			
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
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=3 order by id desc;";
			List li = new ArrayList();
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
		            router.setSerial(rs.getString("serial"));	
					router.setMessage(rs.getString("message"));
					if(rs.getTimestamp("time")!=null){
						router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
					}
					router.setClearedBy(rs.getString("clearedBy"));
					router.setCaseid(rs.getString("caseid"));
					router.setStatus(rs.getInt("status"));
					router.setCasedby(rs.getString("casedby"));
					
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
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=7 order by id desc;";
			List li = new ArrayList();
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
		            router.setSerial(rs.getString("serial"));	
					router.setMessage(rs.getString("message"));
					if(rs.getTimestamp("time")!=null){
						router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
					}
					router.setClearedBy(rs.getString("clearedBy"));
					router.setCaseid(rs.getString("caseid"));
					router.setStatus(rs.getInt("status"));
					router.setCasedby(rs.getString("casedby"));
					
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
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=30 order by id desc;";
			List li = new ArrayList();
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
		            router.setSerial(rs.getString("serial"));	
				
					router.setMessage(rs.getString("message"));
					if(rs.getTimestamp("time")!=null){
						router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
					}
					router.setClearedBy(rs.getString("clearedBy"));
					router.setCaseid(rs.getString("caseid"));
					router.setStatus(rs.getInt("status"));
					router.setCasedby(rs.getString("casedby"));
					
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
		
		/**
		 * 获取固定天数的历史报警
		 * @param day 天数
		 * @return
		 */
		public List<Router> queryAlertHistoryByDay(int day){
			
			List<Router> list=new ArrayList<Router>();
			if(day>0){
				Connection conn = DBConnection.getConnection();
				PreparedStatement pst = null;
				ResultSet rs = null;
				String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<="+day+" order by id desc;";
				try {
					pst = conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs!=null){
					while(rs.next()){
						Router router=new Router();
			            router.setSerial(rs.getString("serial"));	
					
						router.setMessage(rs.getString("message"));
						if(rs.getTimestamp("time")!=null){
							router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
						}
						router.setClearedBy(rs.getString("clearedBy"));
						router.setCaseid(rs.getString("caseid"));
						router.setStatus(rs.getInt("status"));
						router.setCasedby(rs.getString("casedby"));
						
						list.add(router);
					}
					}
				}catch (SQLException e) {
					e.printStackTrace();
				} finally{
					DBConnection.free(rs, null, pst, conn);
				}	
			}
			return list;
		}
	
        //大于一个小时的数据--错过的心跳数据
		public  String  queryMoreOne(){
			Connection conn = DBConnection.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select time FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>time and id=(SELECT max(id) FROM router r order by id)";
			String time="";
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
					while(rs.next()){
						if(rs.getTimestamp("time")!=null){
							time=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time"));
						}
					
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
			String  sql="select * FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>time) and id=(SELECT max(id) FROM router r order by id)";
			List list = new ArrayList();
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
		            router.setSerial(rs.getString("serial"));	
		            if(rs.getTimestamp("time")!=null){
						router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
					}
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
						if(rs.getTimestamp("time")!=null){
							router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
						}
						list1.add(router);
					}
					
				}
			} catch (SQLException e) {
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
	
	
	//最后的Hearbeat
	public List queryPastlast(String serial_no){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="";
		if(serial_no!=null&&!"".equals(serial_no)){
			sql="SELECT id,deviceId,MAX(createDatetime) as time FROM heartbeat where deviceId='"+serial_no+"' GROUP BY deviceId";
		}else{
			sql="SELECT id,deviceId,MAX(createDatetime) as time FROM heartbeat GROUP BY deviceId";
		}
		
//		String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
		List<LassHeartbeat> li = new ArrayList<LassHeartbeat>();
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				LassHeartbeat hearbeat=new LassHeartbeat();
				hearbeat.setDeviceId(rs.getString("deviceId"));
				if(rs.getTimestamp("time")!=null){
					hearbeat.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
				}
				li.add(hearbeat);
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  li;
	}
	
	
	// miss  Hearbeat
	
	
	//最后的Hearbeat
	public List queryPastmisshearbeat(String serial_no){
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="";
		if(serial_no!=null&&!"".equals(serial_no)){
			sql="SELECT id,deviceId,tt.aa AS ti FROM (SELECT id,deviceId,MAX(createDatetime) AS aa FROM heartbeat where deviceId='"+serial_no+"' GROUP BY deviceId ) AS tt WHERE DATE_SUB(SYSDATE(),INTERVAL 2 HOUR)>tt.aa";
		}else{
			sql="SELECT id,deviceId,tt.aa AS ti FROM (SELECT id,deviceId,MAX(createDatetime) AS aa FROM heartbeat  GROUP BY deviceId ) AS tt WHERE DATE_SUB(SYSDATE(),INTERVAL 2 HOUR)>tt.aa";
		}
		
//		String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
		List<LassHeartbeat> li = new ArrayList<LassHeartbeat>();
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				LassHeartbeat hearbeat=new LassHeartbeat();
				hearbeat.setDeviceId(rs.getString("deviceId"));
				if(rs.getTimestamp("ti")!=null){
					hearbeat.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("ti")));
				}
				li.add(hearbeat);
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}		
	
		return  li;
	}
	
	/**
	 * 获得最近多少天的丢失心跳记录
	 * @param day 天数
	 * @return 丢失心跳记录
	 */
	public List<LassHeartbeat> queryMissedHeartBeatByDay(int day){
		List<LassHeartbeat> list=new ArrayList<LassHeartbeat>();
		if(day>0){
			
			Connection conn = DBConnection.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql="";
			
			sql="SELECT id,deviceId,tt.aa AS ti FROM (SELECT hb.id,hb.deviceId,MAX(hb.createDatetime) AS aa FROM (select * from heartbeat WHERE DATEDIFF(SYSDATE(),createDatetime)<="+day+" order by id desc) as hb GROUP BY deviceId ) AS tt WHERE DATE_SUB(SYSDATE(),INTERVAL 2 HOUR)>tt.aa";
			
	//		String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
			try {
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					LassHeartbeat hearbeat=new LassHeartbeat();
					hearbeat.setDeviceId(rs.getString("deviceId"));
					if(rs.getTimestamp("ti")!=null){
						hearbeat.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("ti")));
					}
					list.add(hearbeat);
				}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(rs, null, pst, conn);
			}	
		
		}
		
		return list;
	}
	
	
	/**
	 * 获得所有未解决的报警
	 * @return
	 */
	public List<Router> queryUnresolvedAlertHistory(){
		
		List<Router> list=new ArrayList<Router>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="select * FROM router WHERE level='1' and status=0 order by id desc;";
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				Router router=new Router();
	            router.setSerial(rs.getString("serial"));	
			
				router.setMessage(rs.getString("message"));
				if(rs.getTimestamp("time")!=null){
					router.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
				}
				router.setClearedBy(rs.getString("clearedBy"));
				
				list.add(router);
			}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}	
		return list;
	}
	
}
