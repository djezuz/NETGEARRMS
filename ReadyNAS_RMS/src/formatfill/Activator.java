package formatfill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
public class Activator implements BundleActivator {
	public static Map<String,String> map=new HashMap<String,String>();
	private static BundleContext context;
	public static final String PLUGIN_ID = "FormatFill";
	public static List<String> list=new ArrayList<String>();
	static BundleContext getContext() {
		return context;
	}
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		StringBuffer str=FileTool.getStringBuffer(getRoot()+"File/strings");
		String s=str.toString();
		String[] keys={"S_SMART_ERRORS_REALLOCATED_SECTOR_MESG=","S_SMART_ERRORS_ATA_ERROR_MESG=","S_SMART_STATUS_FAILED="};
		for(String key:keys){
			if(s.contains(key)){
				String value=s.substring(s.indexOf(key));
				value=value.substring(0,value.indexOf("\n"));
				if(value.contains("=")){
					map.put(value.substring(0,value.indexOf("=")), value.substring(value.lastIndexOf("=")+1));
					list.add(value.substring(value.lastIndexOf("=")+2,value.length()-1));
				}
			}
		}
		
		str=FileTool.getStringBuffer(getRoot()+"File/Combined.str");
		String[] keys_1={"DISK_OVERTEMP_BODY::::","POWER_OUT_OF_SPEC::::","FAN + HAS_FAILED::::"};
		s=str.toString();
		for(String key:keys_1){
			if(s.contains(key)){
				String value=s.substring(s.indexOf(key));
				value=value.substring(0,value.indexOf("\n"));
				if(value.contains("::::")){
					map.put(value.substring(0,value.indexOf("::::")), value.substring(value.lastIndexOf("::::")+4));
					list.add(value.substring(value.lastIndexOf("::::")+4));
				}
			}
		}
		FileTool.execute();
	}
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
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
}
