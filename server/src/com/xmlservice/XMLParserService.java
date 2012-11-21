package com.xmlservice;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import domain.Router;

public class XMLParserService {
	public static List<Router> readXml(InputStream inStream)throws Exception{
		//sax��������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//����SAX������
		SAXParser parser=factory.newSAXParser();
		RouterHandler handler=new RouterHandler();
		parser.parse(inStream, handler); //ʹ��ָ����handler����inStream���н���
		List<Router> routers=handler.getrouters();//��������
		return routers;
	}
}
