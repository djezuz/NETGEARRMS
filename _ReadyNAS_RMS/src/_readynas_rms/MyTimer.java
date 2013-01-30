package _readynas_rms;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;



public class MyTimer extends TimerTask {
	
	public static Map<String, String> map = new HashMap<String, String>();
	public static Set<String> list = new HashSet<String>();
	public String last="";
	public static final String PLUGIN_ID = "ReadyNAS_RMS";
	public void run() {
		StringBuffer str = FileTool.getStringBuffer("/var/log/frontview/status.log-old");
		getinputstream(Activator.str0, Activator.str1,str.toString());
	}
	
	public  String getinputstream(String str0,String str1,String str){
		
		String serial_no ="2UM11AEF00170";
		
		String sussessvalue=parse(str0,str1,str).toString();
		
		System.out.println("call start:"+serial_no);
		String url="http://192.168.9.61:8080/server/services/login";
		 
		Service service=new Service();
		Call call;
		String rec="";
		try{
			call=(Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("success1");
			String arrlist=(String) call.invoke(new Object[]{serial_no,sussessvalue});
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("~~~~~call stop:"+rec);
		return rec;
		
	}
	
	public  StringBuffer parse(String str0, String str1, String str) {

		String s = str0;
		String[] keys = { "S_SMART_ERRORS_REALLOCATED_SECTOR_MESG=",
				"S_SMART_ERRORS_ATA_ERROR_MESG=", "S_SMART_STATUS_FAILED=" };
		for (String key : keys) {

			if (s.contains(key)) {
				String value = s.substring(s.indexOf(key));
				value = value.substring(0, value.indexOf("\n"));
				if (value.contains("=")) {
					if (map.get(value.substring(0, value.indexOf("="))) == null) {
						map.put(value.substring(0, value.indexOf("=")), value
								.substring(value.lastIndexOf("=") + 1));
					}
					if (!list.contains(value.substring(
							value.lastIndexOf("=") + 2, value.length() - 1))) {
						list.add(value.substring(value.lastIndexOf("=") + 2,
								value.length() - 1));
					}

				}
			}
		}
		String[] keys_1 = { "DISK_OVERTEMP_BODY::::", "POWER_OUT_OF_SPEC::::",
				"FAN + HAS_FAILED::::" };
		s = str1.toString();
		for (String key : keys_1) {
			System.out.println(key);
			System.out.println(s);
			if (s.contains(key)) {
				String value = s.substring(s.indexOf(key));
				value = value.substring(0, value.indexOf("\n"));
				if (value.contains("::::")) {
					if (map.get(value.substring(0, value.indexOf("::::"))) == null) {
						map.put(value.substring(0, value.indexOf("::::")),
								value.substring(value.lastIndexOf("::::") + 4));
					}
					if (!list.contains(value.substring(value
							.lastIndexOf("::::") + 4))) {
						list
								.add(value
										.substring(value.lastIndexOf("::::") + 4));
					}
				}
			}
		}

		StringBuffer list1 = new StringBuffer();
		String value = str.toString().trim();
		String tmp = value.substring(value.lastIndexOf("\n") + 1);
		
//		if ("".equals(this.last)) {
//			String logvalue = mydesign.queryLast();
//			if (logvalue != null && !"".equals(logvalue)) {
//				this.last = logvalue;
//			}
//		}
//		if (!this.last.equals(tmp)) {
//			mydesign.saveTmpvalue(tmp);
//		}
		// save tmp
		if (!last.equals("") && value.contains(last)) {
			value = value.substring(value.lastIndexOf(last) + last.length())
					.trim();
		}
		this.last = tmp;

		System.out.println("~~~   list.size" + list.size() + "~~~~~log value=:"
				+ value);

		 String[] str_vlaue = value.split("\n");
		if (str_vlaue != null && str_vlaue.length > 0) {
			for (String ss : str_vlaue) {
				if (ss != null && !"".equals(ss)) {
					for (String s2 : list) {
						System.out.println("!!!!list----" + s2);
						if (ss.contains(s2)) {
//							System.out.println("success one value" + ss);
							list1.append(ss + "\n");
						}
					}
				}
			}
		}
//		System.out.println("success all value："+list1);
		return list1;

	}
	
	
}
