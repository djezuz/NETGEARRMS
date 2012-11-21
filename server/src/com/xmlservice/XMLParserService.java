package com.xmlservice;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import domain.Router;

public class XMLParserService {
	public static List<Router> readXml(InputStream inStream)throws Exception{
		//sax解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//构造SAX解析器
		SAXParser parser=factory.newSAXParser();
		RouterHandler handler=new RouterHandler();
		parser.parse(inStream, handler); //使用指定的handler对流inStream进行解析
		List<Router> routers=handler.getrouters();//返回内容
		return routers;
	}
}
