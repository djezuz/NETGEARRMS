package ReadyNAS_RMS;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;



public class SOAPClient4XG {
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		synchronized (in) {
			synchronized (out) {
				byte[] buffer = new byte[256];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1)
						break;
					out.write(buffer, 0, bytesRead);
				}
			}
		}
	}

	public static String localRun(String s){//获取本地数据
		BufferedReader in = null;
		try {
			String SOAPUrl = "http://routerlogin.net:5000/soap/server_sa/";
			String xmlFile2Send =getRoot()+"File/weattherreq.xml";
			URL url = new URL(SOAPUrl);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			FileInputStream fin = new FileInputStream(xmlFile2Send);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			copy(fin, bout);
			fin.close();
			byte[] b = bout.toByteArray();
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", s);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			out.write(b);
			out.close();
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			 in = new BufferedReader(isr);
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null){
			sb.append(inputLine+"\n");
			}
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String remoteRun(String a,String fcml){//第一次请求远程认证获得sessionId
		URL url;
		String string=null;
		String sessionId="";
		try {
			url = new URL("https://genie.netgear.com/fcp/authenticate");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			PrintWriter ouputstream = new PrintWriter(httpConn.getOutputStream());
			String body = "<authenticate type=\"basic\" username=\"siteviewgenietest@gmail.com\" password=\"siteview\"/>";
		    ouputstream.write(body);
		    ouputstream.flush();
		    httpConn.connect();
		    String cookieVal = null;
			String key=null;
			     for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null; i++ ) {
			               if (key.equalsIgnoreCase("set-cookie")) {
			                cookieVal = httpConn.getHeaderField(i);
			                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
			                sessionId=sessionId+cookieVal+";";
			               }
			            }
			System.out.println("sessionId:"+sessionId);
			if(sessionId!=null){
				string = connectionRun(sessionId, a, fcml);
			}
		    InputStream inputstream= httpConn.getInputStream();
		    byte[] b = new byte[1024*100];
		    int len=0;
		    int temp=0;
		    while((temp=inputstream.read())!=-1){
		    	b[len] = (byte)temp;
		    	len++;
		    }
		   return string+sessionId;
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	public static String connectionRun(String sessionId,String a,String fcml){//获得sessionId后进行数据交互
		URL url;
		String result=null;
		try {
			url = new URL("https://genie.netgear.com/fcp/"+a);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Cookie", sessionId);
			PrintWriter ouputstream = new PrintWriter(httpConn.getOutputStream());
			String body = fcml;
		    ouputstream.write(body);
		    ouputstream.flush();
		    httpConn.connect();
		    InputStream inputstream= httpConn.getInputStream();
		    byte[] b = new byte[1024*100];
		    int len=0;
		    int temp=0;
		    while((temp=inputstream.read())!=-1){
		    	b[len] = (byte)temp;
		    	len++;
		    }
		   result=new String(b,0,len);
		   //System.out.println(result);
		   return result;
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	public static void connection(String a,Map<String,String> map){//连接到远程 
		URL url;
		try {
			url = new URL("https://genie.netgear.com/fcp/"+a);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			PrintWriter ouputstream = new PrintWriter(httpConn.getOutputStream());
			
			StringBuffer str = new StringBuffer("<"+a);
			
			Set set = map.keySet();
			for(Iterator it = set.iterator(); it.hasNext();){
				String key = (String)it.next();
				String value =  map.get(key);
				str.append(" "+key+"=\""+value+"\"");
			}
			str.append("/>");
			String body = str.toString();
		    ouputstream.write(body);
		    ouputstream.flush();
		    httpConn.connect();
		    InputStream inputstream= httpConn.getInputStream();
		    byte[] b = new byte[1024*100];
		    int len=0;
		    int temp=0;
		    while((temp=inputstream.read())!=-1){
		    	b[len] = (byte)temp;
		    	len++;
		    }
		    System.out.println(new String(b,0,len));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	public static final String PLUGIN_ID = "Genie";
	public static String getRoot(){
		  String path=null;
		  try {
		    path = FileLocator.toFileURL(
		    Platform.getBundle(PLUGIN_ID).getEntry("")).getPath();
		    path = path.substring(path.indexOf("/") + 1, path.length());
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		  return path;
		} 
	
	public void AuthenticateUserAndPassword(String username ,String password)
	{
	}
}
