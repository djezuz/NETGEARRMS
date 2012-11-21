package test;
import com.service.Mydesign;

public class TestmyDesign {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String args[]) throws Exception{
	
		test();
		
	}
	
	
	public static void test() throws Exception {
		Mydesign  mydesign=new Mydesign();
		String serial = "1";
		String level = "1";
		String message = "1";
		String time ="1";
		String clearedBy = "1";
		mydesign.login(serial, level, message, time, clearedBy);
	
		
		
	}
	
}
