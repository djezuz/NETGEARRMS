package _readynas_rms;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class MyTimer extends TimerTask {

	public static Map<String, String> map = new HashMap<String, String>();
	public static Set<String> list = new HashSet<String>();
	public String last = "";
	public static final String PLUGIN_ID = "ReadyNAS_RMS";
	public static List<String> seriallist = new ArrayList<String>();
	public boolean iswrite=false;
	public String lastlog="";
	public void run() {
		StringBuffer str = FileTool
				.getStringBuffer("/var/log/frontview/status.log-old");
		getinputstream(Activator.str0, Activator.str1, str.toString());
	}

	public String getinputstream(String str0, String str1, String str) {

		// Random i = new Random();
		// int j = i.nextInt(5);

		if("Not found".equals(seriallist.get(0))){
			//取序列号值
			try {
	            Process process = Runtime.getRuntime().exec(" grep serial /proc/sys/dev/boot/info");
	            InputStreamReader ir = new InputStreamReader(process.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            String line;
	            while ((line = input.readLine()) != null) {
	                String endlog=line.trim();
	                endlog=  endlog.substring(endlog.indexOf(":")+1,endlog.length()).trim();
	                MyTimer.seriallist.add(endlog);
	                System.out.println("run cmd value=============="+endlog);
	                break;
	            }
	        } catch (java.io.IOException e) {
//	            System.err.println("IOException " + e.getMessage());
	        	MyTimer.seriallist.add("Not found");
	        	return "";
	        }
		}
		
		String serial_no = seriallist.get(0);

		String sussessvalue = parse(str0, str1, str).toString();
		String rec = "";
		System.out.println("call start:" + serial_no);
//		String url = "http://192.168.9.123:8082/server/services/login";
//		String url = "http://10.1.1.136:8080/server/services/login";
//		String url = "http://10.100.1.73:8080/server/services/login";
		String url="http://rnasmon.netgear.com/server/services/login";
		Service service;
		Call call;
		try {
			service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("success1");
			String arrlist = (String) call.invoke(new Object[] { serial_no,
					sussessvalue });
			if(iswrite){
				writelog(lastlog);
			}
			this.last = lastlog;
		} catch (Exception e) {
//			e.printStackTrace();
//			this.last="";
			System.out.println("~~~~~~ call wsdl fail;");
		}
		System.out.println("~~~~~call stop:" + rec);
		return rec;

	}

	public StringBuffer parse(String str0, String str1, String str) {
		iswrite=false;
		lastlog="";
//		System.out.println(" old value=" + last + "------ log value" + str);
		System.out.println(" old value=" + last );
		String s = str0;
		String[] keys = { "S_SMART_ERRORS_REALLOCATED_SECTOR_MESG=",
				"S_SMART_ERRORS_ATA_ERROR_MESG=", "S_SMART_STATUS_FAILED=" };
		for (String key : keys) {
			if (s.contains(key)) {
				String value = s.substring(s.indexOf(key));
				value = value.substring(0, value.indexOf("\n"));
				if (value.contains("=")) {
					if (map.get(value.substring(0, value.indexOf("="))) == null) {
						map.put(value.substring(0, value.indexOf("=")),
								value.substring(value.lastIndexOf("=") + 1));
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
//			System.out.println(key);
//			System.out.println(s);
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
						list.add(value.substring(value.lastIndexOf("::::") + 4));
					}
				}
			}
		}

		StringBuffer list1 = new StringBuffer();
		String value = str.toString().trim();
		String tmp = value.substring(value.lastIndexOf("\n") + 1);

		if ("".equals(this.last)) {
			String logvalue = getendlog();
			if (logvalue != null && !"".equals(logvalue)) {
				this.last = logvalue;
			}
		}
		if (!this.last.equals(tmp)) {
			iswrite=true;
			lastlog=tmp;
		}
		if (!last.equals("") && value.contains(last)) {
			value = value.substring(value.lastIndexOf(last) + last.length())
					.trim();
		}
		

		System.out.println("~~~   list.size" + list.size() + "~~~~~log value=:"
				+ value);

		String[] str_vlaue = value.split("\n");
		if (str_vlaue != null && str_vlaue.length > 0) {
			for (String ss : str_vlaue) {
				if (ss != null && !"".equals(ss)) {
					for (String s2 : list) {
//						System.out.println("!!!!list----" + s2);
						if (ss.contains(s2)) {
							System.out.println("success one value"+ss);
							list1.append(ss + "\n");
						}
					}
				}
			}
		}
//		System.out.println("success all value:"+list1.toString());
		return list1;

	}

	// 写log文件的最后一条
	public void writelog(String endlog) {
		System.out.println("endlog--------write-------"+endlog);
		try {
			File f = new File("/etc/endlog.txt");
			if (f.exists()) {
			} else {
				f.createNewFile();// 不存在则创建
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write("endlog@@@" + endlog);
			output.close();
		} catch (Exception e) {
			System.out.println("log write fail!!!");
//			e.printStackTrace();
		}
	}

	// 取log文件
	public String getendlog() {
		try {
			Process process = Runtime.getRuntime().exec(
					" grep endlog /etc/endlog.txt");
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				String endlog=line.trim();
				endlog=endlog.substring(endlog.indexOf("@@@")+3,endlog.length()).trim();
				System.out.println("endlog=======read=======" + endlog);
				return endlog;
			}
			return "";
		} catch (java.io.IOException e) {
			System.out.println("readfile endlog fail");
			return "";
		}
	}

}
