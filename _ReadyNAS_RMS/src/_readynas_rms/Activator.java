package _readynas_rms;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	public static Map<String,String> map=new HashMap<String,String>();
	private static BundleContext context;
	public static final String PLUGIN_ID = "ReadyNAS_RMS";
	public static List<String> list=new ArrayList<String>();
    public static String str0=null;
    public static String str1=null;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		list.clear();
		MyTimer.seriallist.clear();
		System.out.println("already running.....:");

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
//            System.err.println("IOException " + e.getMessage());
        	MyTimer.seriallist.add("Not found");
        }
		
		///frontview/bin/lang/en-us/strings   /frontview/bin/lang/zh-cn/strings
		StringBuffer str = FileTool.getStringBuffer("/frontview/bin/lang/en-us/strings");
		String s = str.toString();
		str0=str.toString();
		str = FileTool.getStringBuffer("/frontview/ui/resource/language/en-us/Combined.str");
		s = str.toString();
		str1=str.toString();
		FileTool.execute();

		System.out.println("--------------end-----------------");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
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
