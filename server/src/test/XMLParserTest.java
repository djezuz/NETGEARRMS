package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.xmlservice.XMLParserService;

import domain.Router;


public class XMLParserTest {

	@Test //使用了JUnit4进行测试
	public void testReadXml(){
		try{
			//文件的位置在该工程的WebRoot/xmlfile/文件下
			File file=new File("WebRoot/xmlfile/limaoyuan.xml");
			InputStream inStream=new FileInputStream(file);
			//注意，我的limaoyuan.xml这个文件是放在工程的src目录下的，当然你也可以读取其它目录的文件
			//InputStream inStream=XMLParserTest.class.getClassLoader().getResourceAsStream("limaoyuan.xml");
			List<Router> routers=XMLParserService.readXml(inStream);
			for(Router router:routers){
				System.out.println(router.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
