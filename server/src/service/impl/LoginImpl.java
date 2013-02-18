package service.impl;
import service.Login;

import com.service.Mydesign;


public class LoginImpl implements Login{

	public Mydesign mydesign = null;
	public String success(String serial,String serial_no,String message,String  time,String clearedBy) {
		//可以调用数据库操作方法，也可以调用业务方法 
		System.out.print("---------------"+serial);
		String [] s0=serial.split("\n");
		String serial_1=null;
		String time_1=null;
		String level_1=null;
		String  message_1=null;
		String  clearedBy_1=null;
		for(String s1:s0){
			String []s2=s1.split("!!");
			serial_1=serial_no;
			 time_1=s2[1];
			 level_1=s2[0];
		      message_1=s2[3];
		      clearedBy_1=s2[2];
		      
		}
		Mydesign  mydesign=new Mydesign();
		mydesign.login(serial_1, level_1, message_1, time_1, clearedBy_1);
		
		return  "success" ;
	}

	public String success1(String serial_no, String log_value) {
		// TODO Auto-generated method stub
		
		//增加心跳功能
		if (mydesign == null) {
			mydesign = new Mydesign();
		}
		
		mydesign.addHeartBeat(serial_no);
		
		if (log_value.equals("")) {
			if (mydesign != null) {
				mydesign.finsh();
				mydesign=null;
			}
			System.out.println("~~~~~~no date done!");
			return "FALSE";
		}
		System.out.println("~~~~~call sussess! value=" + log_value);
		String[] s0 = log_value.split("\n");
		String serial_1 = null;
		String time_1 = null;
		String level_1 = null;
		String message_1 = null;
		String clearedBy_1 = null;
//		if (mydesign == null) {
//			mydesign = new Mydesign();
//		}
		for (String s1 : s0) {
			String[] s2 = s1.split("!!");
			System.out.println("waw" + s1);

			serial_1 = serial_no;
			time_1 = s2[1];
			level_1 = s2[0];
			message_1 = s2[3];
			clearedBy_1 = s2[2];
			mydesign.login(serial_1, level_1, message_1, time_1, clearedBy_1);
		}
		mydesign.finsh();
		mydesign=null;
		return "TRUE";
	}
	
	
	
}
