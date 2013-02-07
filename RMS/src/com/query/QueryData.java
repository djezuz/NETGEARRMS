package com.query;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  router";
		List li = new ArrayList();
		try {
			
			conn = DBConnection.getConnection();
			
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
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT *  FROM  ROUTER";
		List li = new ArrayList();
		try {
			conn = DBConnection.getConnection();
			
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
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="SELECT time from router WHERE id=(SELECT MAX(id) from router)";
		String time="";
		try {
			conn = DBConnection.getConnection();
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
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=3 order by id desc;";
			List li = new ArrayList();
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
					router.setId(rs.getInt("id"));
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
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=7 order by id desc;";
			List li = new ArrayList();
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
					router.setId(rs.getInt("id"));
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
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<=30 order by id desc;";
			List li = new ArrayList();
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					Router router=new Router();
					router.setId(rs.getInt("id"));
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
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				String  sql="select * FROM router WHERE level='1' and DATEDIFF(SYSDATE(),time)<="+day+" order by id desc;";
				try {
					conn = DBConnection.getConnection();
					pst = conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs!=null){
					while(rs.next()){
						Router router=new Router();
						router.setId(rs.getInt("id"));
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
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select time FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>time and id=(SELECT max(id) FROM router r order by id)";
			String time="";
			try {
				conn = DBConnection.getConnection();
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
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String  sql="select * FROM router WHERE date_sub(SYSDATE(),INTERVAL 1 Hour)>time) and id=(SELECT max(id) FROM router r order by id)";
			List list = new ArrayList();
			try {
				conn = DBConnection.getConnection();
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
			Connection conn =null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="SELECT *  from router WHERE  level='alert' order by id DESC";
			List list1 =new ArrayList();
			try {
				conn =DBConnection.getConnection();
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
		Connection conn = null;
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
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				LassHeartbeat hearbeat=new LassHeartbeat();
				hearbeat.setDeviceId(rs.getString("deviceId"));
				if(rs.getTimestamp("time")!=null){
					hearbeat.setCreateDatetime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("time")));
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
		Connection conn = null;
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
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs!=null){
			while(rs.next()){
				LassHeartbeat hearbeat=new LassHeartbeat();
				hearbeat.setDeviceId(rs.getString("deviceId"));
				if(rs.getTimestamp("ti")!=null){
					hearbeat.setCreateDatetime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("ti")));
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
			
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql="";
			
			sql="SELECT c.id,c.deviceId,c.createDatetime,d.caseid,d.casedby,d.cleartime FROM (" +
					"SELECT a.id,a.deviceId,a.createDatetime FROM(" +
					"SELECT aa.id,aa.deviceId,aa.createDatetime FROM(" +
					"SELECT hb.id,hb.deviceId,MAX(hb.createDatetime) createDatetime FROM (select * from heartbeat WHERE DATEDIFF(SYSDATE(),createDatetime)<=? order by id desc) as hb GROUP BY deviceId" +
					") AS aa WHERE DATE_SUB(SYSDATE(),INTERVAL 2 HOUR)>createDatetime " +
					") AS a LEFT JOIN (SELECT deviceId,MAX(misstime) misstime  FROM missheartbeat AS bb GROUP BY bb.deviceId" +
					") AS b ON a.deviceId =b.deviceId AND a.createDatetime=b.misstime " +
					") AS c LEFT JOIN missheartbeat d ON c.createDatetime = d.misstime AND c.deviceId=d.deviceId";
			
	//		String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, day);
				rs=pst.executeQuery();
				if(rs!=null){
				while(rs.next()){
					LassHeartbeat hearbeat=new LassHeartbeat();
					hearbeat.setId(rs.getInt("id"));
					hearbeat.setDeviceId(rs.getString("deviceId"));
					if(rs.getTimestamp("createDatetime")!=null){
						hearbeat.setCreateDatetime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("createDatetime")));
					}
					hearbeat.setCaseid(rs.getString("caseid"));
					hearbeat.setCasedby(rs.getString("casedby"));
					if(rs.getTimestamp("cleartime")!=null){
						hearbeat.setCleartime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("cleartime")));
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
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String  sql="select * FROM router WHERE level='1' and status=0 order by id desc;";
		try {
			conn = DBConnection.getConnection();
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
	
	/**
	 * Clear Case
	 * @param id 记录ID
	 * @param caseNum Case Number
	 * @return
	 */
	public Router clearInputCase(int id,String caseNum,String loginUsername){
		
		Router router=null;
		if(id>=0 && loginUsername!=null && !"".equals(loginUsername.trim()) && caseNum!=null){
			Connection conn = DBConnection.getConnection();
			PreparedStatement pst = null;
			String  sql="update router set caseid=?,status=?,casedby=? where id=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, caseNum.trim());
				pst.setInt(2, 1);
				pst.setString(3, loginUsername.trim());
				pst.setInt(4, id);
				int i=pst.executeUpdate();
				if(i>0){
					router=getRouterById(id);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(null, null, pst, conn);
			}	
		}
		return router;
		
	}
	
	
	/**
	 * Clear Router Case
	 * @param id 记录ID
	 * @param caseNum Case Number
	 * @return
	 */
	public Router clearRouterInputCase(int id,String caseNum,String loginUsername){
		
		Router router=null;
		if(id>=0 && loginUsername!=null && !"".equals(loginUsername.trim()) && caseNum!=null && !"".equals(caseNum.trim())){
			Connection conn = null;
			PreparedStatement pst = null;
			String  sql="update router set caseid=?,status=?,casedby=? where id=?";
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, caseNum.trim());
				pst.setInt(2, 1);
				pst.setString(3, loginUsername.trim());
				pst.setInt(4, id);
				int i=pst.executeUpdate();
				if(i>0){
					router=getRouterById(id);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(null, null, pst, conn);
			}	
		}
		return router;
		
	}
	
	/**
	 * 通过ID查询Router记录
	 * @param id
	 * @return
	 */
	public Router getRouterById(int id){
		
		Router router=null;
		
		Connection conn =null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		String  sql="select * from router where id=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				router=new Router();
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
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.free(rs, null, pst, conn);
		}	
		
		return router;
	}
	
	
	
	/**
	 * Clear HeartBeat Case
	 * @param deviceId
	 * @param createDT
	 * @param caseNum
	 * @param loginUsername
	 * @return
	 */
	public LassHeartbeat clearHBInputCase(String deviceId,String createDT,String caseNum,String loginUsername){
		
		LassHeartbeat hb=null;
		if(deviceId!=null && !"".equals(deviceId.trim()) && createDT!=null && !"".equals(createDT.trim()) && loginUsername!=null && !"".equals(loginUsername.trim()) && caseNum!=null && !"".equals(caseNum.trim())){
			Connection conn =null;
			PreparedStatement pst = null;
			String  sql="insert into missheartbeat(deviceId,misstime,caseid,casedby,cleartime) values(?,STR_TO_DATE(?,'%m/%d/%Y %k:%i:%s'),?,?,?)";
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, deviceId.trim());
				pst.setString(2, createDT.trim());
				pst.setString(3, caseNum.trim());
				pst.setString(4, loginUsername.trim());
				pst.setString(5,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
				int i=pst.executeUpdate();
				if(i>0){
					hb=queryOneMissedHeartBeat(deviceId.trim(),createDT.trim());
					if(hb==null){
						deleteHBInputCase(deviceId.trim(), createDT.trim());
					}
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(null, null, pst, conn);
			}	
		}
		return hb;
		
	}
	
	
	/**
	 * 删除Cleared HeartBeat Case
	 * @param deviceId
	 * @param createDT
	 * @param caseNum
	 * @param loginUsername
	 * @return
	 */
	public boolean deleteHBInputCase(String deviceId,String createDT){
		
		boolean flag=false;
		if(deviceId!=null && !"".equals(deviceId.trim()) && createDT!=null && !"".equals(createDT.trim()) ){
			Connection conn =null;
			PreparedStatement pst = null;
			String  sql="delete from missheartbeat where deviceId=?,misstime=STR_TO_DATE(?,'%m/%d/%Y %k:%i:%s')";
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, deviceId.trim());
				pst.setString(2, createDT.trim());
				int i=pst.executeUpdate();
				if(i>0){
					flag=true;
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(null, null, pst, conn);
			}	
		}
		return flag;
		
	}
	
	/**
	 * 获得丢失心跳记录
	 * @param day 天数
	 * @return 丢失心跳记录
	 */
	public LassHeartbeat queryOneMissedHeartBeat(String deviceId,String createDatetime){
		
		LassHeartbeat hearbeat=null;
		
		if(deviceId!=null && !"".equals(deviceId.trim()) && createDatetime!=null && !"".equals(createDatetime.trim())){
			
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql="";
			
			sql="select * from (SELECT c.id,c.deviceId,c.createDatetime,d.caseid,d.casedby,d.cleartime FROM (" +
					"SELECT a.id,a.deviceId,a.createDatetime FROM(" +
					"SELECT aa.id,aa.deviceId,aa.createDatetime FROM(" +
					"SELECT id,deviceId,MAX(createDatetime) createDatetime FROM heartbeat GROUP BY deviceId" +
					") AS aa WHERE DATE_SUB(SYSDATE(),INTERVAL 2 HOUR)>createDatetime " +
					") AS a LEFT JOIN (SELECT deviceId,MAX(misstime) misstime  FROM missheartbeat AS bb GROUP BY bb.deviceId" +
					") AS b ON a.deviceId =b.deviceId AND a.createDatetime=b.misstime " +
					") AS c LEFT JOIN missheartbeat d ON c.createDatetime = d.misstime AND c.deviceId=d.deviceId ) mhb where mhb.deviceId=? and mhb.createDatetime=STR_TO_DATE(?,'%m/%d/%Y %k:%i:%s')";
			
	//		String  sql="select * FROM router WHERE DATEDIFF(SYSDATE(),STR_TO_DATE(time,'%a %b %d %h:%i:%s HKT %Y'))<=7;";
			try {
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, deviceId.trim());
				pst.setString(2, createDatetime.trim());
				rs=pst.executeQuery();
				if(rs!=null){
					if(rs.next()){
						hearbeat=new LassHeartbeat();
						hearbeat.setId(rs.getInt("id"));
						hearbeat.setDeviceId(rs.getString("deviceId"));
						if(rs.getTimestamp("createDatetime")!=null){
							hearbeat.setCreateDatetime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("createDatetime")));
						}
						hearbeat.setCaseid(rs.getString("caseid"));
						hearbeat.setCasedby(rs.getString("casedby"));
						if(rs.getTimestamp("cleartime")!=null){
							hearbeat.setCleartime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(rs.getTimestamp("cleartime")));
						}
						
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBConnection.free(rs, null, pst, conn);
			}	
		
		}
		
		return hearbeat;
	}
	
}
