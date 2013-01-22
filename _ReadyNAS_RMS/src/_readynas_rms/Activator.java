package _readynas_rms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		System.out.println("already running.....:");

        ///frontview/bin/lang/en-us/strings   /frontview/bin/lang/zh-cn/strings
		StringBuffer str = FileTool.getStringBuffer("/frontview/bin/lang/en-us/strings");
		String s = str.toString();
		str0=str.toString();
		/*String[] keys = { "S_SMART_ERRORS_REALLOCATED_SECTOR_MESG=",
				"S_SMART_ERRORS_ATA_ERROR_MESG=", "S_SMART_STATUS_FAILED=" };
		for (String key : keys) {
			if (s.contains(key)) {
				String value = s.substring(s.indexOf(key));
				value = value.substring(0, value.indexOf("\n"));
				if (value.contains("=")) {
					map.put(value.substring(0, value.indexOf("=")),
							value.substring(value.lastIndexOf("=") + 1));
					list.add(value.substring(value.lastIndexOf("=") + 2,
							value.length() - 1));
				}
			}
		}*/
        //frontview/ui/resource/language/zh-cn/Combined.str
		///frontview/ui/resource/language/en-us/Combined.str
		str = FileTool.getStringBuffer("/frontview/ui/resource/language/en-us/Combined.str");
		String[] keys_1 = { "DISK_OVERTEMP_BODY::::", "POWER_OUT_OF_SPEC::::",
				"FAN + HAS_FAILED::::" };
		s = str.toString();
		str1=str.toString();
		/*for (String key : keys_1) {
			if (s.contains(key)) {
				String value = s.substring(s.indexOf(key));
				value = value.substring(0, value.indexOf("\n"));
				if (value.contains("::::")) {
					map.put(value.substring(0, value.indexOf("::::")),
							value.substring(value.lastIndexOf("::::") + 4));
					list.add(value.substring(value.lastIndexOf("::::") + 4));
				}
			}
		}*/
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
