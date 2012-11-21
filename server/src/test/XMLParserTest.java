package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.xmlservice.XMLParserService;

import domain.Router;


public class XMLParserTest {

	@Test //ʹ����JUnit4���в���
	public void testReadXml(){
		try{
			//�ļ���λ���ڸù��̵�WebRoot/xmlfile/�ļ���
			File file=new File("WebRoot/xmlfile/limaoyuan.xml");
			InputStream inStream=new FileInputStream(file);
			//ע�⣬�ҵ�limaoyuan.xml����ļ��Ƿ��ڹ��̵�srcĿ¼�µģ���Ȼ��Ҳ���Զ�ȡ����Ŀ¼���ļ�
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
