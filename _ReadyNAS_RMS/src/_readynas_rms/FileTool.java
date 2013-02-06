package _readynas_rms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
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
			System.out.println("get file FileNotFoundException");
//			e1.printStackTrace();
			return str;
		} catch (IOException e) {
			System.out.println("get file IOException");
//			e.printStackTrace();
			return str;
		}
//		System.out.print(str);
//		System.out.println("=========================================");
		return str;
		
	}

	public static void execute() {
		Timer timer = new Timer();
		MyTimer myTimeTask = new MyTimer();
		Date date = new Date();
		long timestamp = 1000*60;
		timer.schedule(myTimeTask, date, timestamp);
	}
	
	

}
