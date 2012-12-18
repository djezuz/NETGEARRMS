package formatfill;
import java.util.TimerTask;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

public class MyTimer extends TimerTask {
	
	
	public static final String PLUGIN_ID = "ReadyNAS_RMS";
	String last="";
	public void run() {
		StringBuffer list=new StringBuffer();
		StringBuffer str = FileTool.getStringBuffer(Activator.getRoot()+"File/status.log-old");
		String value=str.toString().trim();
		String tmp=value.substring(value.lastIndexOf("\n")+1);
		if(!last.equals("")&&value.contains(last)){
			if(!last.equals(tmp)){
				value=value.substring(value.indexOf(last)+last.length()).trim();
			}else{
				return;
			}
		}
		last=tmp;
		for(String s:Activator.list){
			if(value.contains(s)){
				String s0=value.substring(value.indexOf(s));
				s0=s0.substring(0,s0.indexOf("\n"));
				String s1=value.substring(0,value.indexOf(s));
				s1=s1.substring(s1.lastIndexOf("\n")+1);
				list.append(s1+s0+"\n");
			}
		}
		getCall(list.toString());
	}
	
	public static Object getCall(String value){
		String serial_no = FileTool.getS_no();
		System.out.println(serial_no);
		//String url="http://localhost:8080/server/services/login";
		
		FileTool fileTool =new FileTool();
		StringBuffer str=fileTool.getStringBuffer(getRoot()+"META-INF/config.ini");
		System.out.println(str);
		String [] s=str.toString().split("=");
		String  url=s[1];
		System.out.println(url);
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
	
	
	public static String getRoot(){
		  String path=null;
		  try {
		    path = FileLocator.toFileURL(
		    Platform.getBundle(PLUGIN_ID).getEntry("")).getPath();
			//  path=Platform.getBundle(PLUGIN_ID).getLocation();
		    path = path.substring(path.indexOf("/") + 1, path.length());
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		  System.out.println(path);
		  return path;
		} 
}
