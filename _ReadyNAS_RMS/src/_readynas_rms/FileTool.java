package _readynas_rms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

public class FileTool {
	public static StringBuffer getStringBuffer(String fileName) {
		StringBuffer str = new StringBuffer();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(fileName)),"UTF-8"));
			String s = "";
			while ((s = br.readLine()) != null) {
				str.append(s+"\n");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print(str);
		System.out.println("=========================================");
		return str;
		
	}

	public static void execute() {
		Timer timer = new Timer();
		MyTimer myTimeTask = new MyTimer();
		Date date = new Date();
		long timestamp = 1000*60;
		timer.schedule(myTimeTask, date, timestamp);
	}
	public static void main(String[]args){
		//System.out.println(getStringBuffer("d://Combined.str").toString());
		System.out.println(getS_no());
	}
	
	public static String getS_no(){
		String s_no = "";
		String sessionId="";
		String jsessionId="";
		String DeviceConfig="";
		
		String key="";
		String value="";
		String result="";
		String name="";
		String model="";
		//第一次发送请求
		Map<String,String> map = new HashMap<String,String>();
		map.put("init", "<init type=\"ui\" fcmb=\"true\"/>");
		Set set=map.keySet();
		for(Iterator it=set.iterator(); it.hasNext();){
			   key  =   (String)   it.next();
			   value=   map.get(key);
			  result=SOAPClient4XG.remoteRun(key,value);
			  System.out.println("第一次返回的结果:"+result);
			  if(!(result.equals(""))&&!(result.equals("<authenticate result=\"401\" description=\"Unauthorized\"/>"))){
				  String str=result.substring(0,result.lastIndexOf(">")+1);
				  sessionId=result.substring(result.lastIndexOf(">")+1);
				  
				  sessionId.substring(0, sessionId.lastIndexOf("=")+1);
				  jsessionId=sessionId.substring(sessionId.lastIndexOf("=")+1);
				  
				  String []a=jsessionId.split(";");
				  DeviceConfig=a[0];
			  }  
		} 
		//第二次发送请求
		if(!(result.equals(""))&&!(result.equals("<authenticate result=\"401\" description=\"Unauthorized\"/>"))){
			String str=result.substring(result.indexOf("name=")+5);
			name=  str.substring(str.indexOf('"')+1, str.indexOf('"', 1));	
		}
		String a="send?n="+name;
		String fcml="<fcml to=\"router@portal\" from=\""+name+"@portal\" _tracer=\"8\"><get/></fcml>";
		result=SOAPClient4XG.connectionRun(sessionId, a, fcml);
		if(result.equals("")){
			result="200 ok";
		}
		System.out.println("第二次返回的结果:"+result);
		
		//第三次发送请求并获取model以及model的序列号
		String a1="receive?n="+name;
		String fcml1="<DeviceConfig.ConfigurationStarted=\""+DeviceConfig+"\"/>";
		result=SOAPClient4XG.connectionRun(sessionId, a1, fcml1);
		System.out.println("第三次返回的结果:"+result);
		if(!(result.equals(""))&&!(result.equals("<authenticate result=\"401\" description=\"Unauthorized\"/>"))){
			String str=result.substring(result.indexOf("model=")+6);
			model=str.substring(str.indexOf('"')+1, str.indexOf('"', 1));
			String string=result.substring(result.indexOf("serial=")+7);
			String number=string.substring(string.indexOf('"')+1, string.indexOf('"', 1));
			System.out.println("model:"+model);
			System.out.println("设备序列号"+number);
			s_no = number;
		}
		return s_no;
	}

}
