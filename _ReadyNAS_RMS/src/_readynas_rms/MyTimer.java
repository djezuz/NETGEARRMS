package _readynas_rms;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;



public class MyTimer extends TimerTask {
	
	
	public static final String PLUGIN_ID = "ReadyNAS_RMS";
	public static ArrayList<String> last=new ArrayList<String>();
	public void run() {
		StringBuffer list=new StringBuffer();
		StringBuffer str = FileTool.getStringBuffer("/var/log/frontview/status.log-old");
		/*System.out.println("------------------------------------");
		String value=str.toString().trim();
		String tmp=value.substring(value.lastIndexOf("\n")+1);
		System.out.println("----tmp----"+tmp);
		if(!last.equals("")&&value.contains(last)){
			if(!last.equals(tmp)){
				value=value.substring(value.indexOf(last)+last.length()).trim();
			}else{
				return;
			}
		}
		last=tmp;
		System.out.println("-----------Activator.list-"+Activator.list.size());
		for(String s:Activator.list){
			System.out.println(s+"............................s"+s);
			System.out.println(value.contains(s));
			System.out.println(value);
			System.out.println("++++++++++++++++++++++++++");
			System.out.println(s);
			if(value.contains(s)){
				
				String s0=value.substring(value.indexOf(s));
				s0=s0.substring(0,s0.indexOf("\n"));
				String s1=value.substring(0,value.indexOf(s));
				s1=s1.substring(s1.lastIndexOf("\n")+1);
				list.append(s1+s0+"\n");
			}
		}
		System.out.println(list.toString()+"..................soap--");
		getCall(list.toString());*/
		getinputstream(Activator.str0, Activator.str1,str.toString());
	}
	
	public  static String getinputstream(String str0,String str1,String str){
		
		String serial_no = FileTool.getS_no();
		System.out.println("调用WSDL:"+serial_no);
		String url="http://192.168.9.61:8080/server/services/login";
		 
		Service service=new Service();
		Call call;
		String rec="";
		try{
			call=(Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("success1");
			String laststr="";
			if(last.size()>0){
				laststr=last.get(0);
			}
			String arrlist=(String) call.invoke(new Object[]{str0,str1,str,serial_no,laststr});
			last.add(0, arrlist);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("~~~~~调用完成:"+rec);
		return rec;
		
	}
	
	
	
	public static Object getCall(String value){
		String serial_no = FileTool.getS_no();
		System.out.println(serial_no);
		String url="http://192.168.9.61:8080/server/services/login";
		 			 
		//StringBuffer str=FileTool.getStringBuffer(getRoot()+"META-INF/config.ini");
		/*StringBuffer str=new StringBuffer("http://192.168.9.61:8080/server/services/login");
		System.out.println(str);
		String [] s=str.toString().split("=");
		String  url=s[1];
		System.out.println(url);*/
		Service service=new Service();
		Call call;
		String rec="";
		try{
			call=(Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("success");
			rec=(String) call.invoke(new Object[]{value,serial_no,"","",""});
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(rec);
		return rec;
	}
	
	
//	public static String getRoot(){
//		String path = null;
//		try {
//			path = Activator.getContext().getBundle().getEntry("").getPath();
//
//			// path = FileLocator.toFileURL(
//			// Platform.getBundle(PLUGIN_ID).getEntry("")).getPath();
//			// path=Platform.getBundle(PLUGIN_ID).getLocation();
//			path = path.substring(path.indexOf("/") + 1, path.length());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(path);
//		return path;
//	} 
}
