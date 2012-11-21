package com.xmlservice;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import domain.Router;



public class RouterHandler extends DefaultHandler {
	private List<Router> routers=null;
	private Router router=null;
	private String tag="";
	
	public List<Router> getrouters() {
		return routers;
	}


	@Override
	public void startDocument() throws SAXException {
		routers=new ArrayList<Router>();
	}
	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("router".equals(qName)){
			router=new Router();
		}
		tag=qName;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(router!=null){
			String data=new String(ch,start,length);
			if("username".equals(tag)){
					router.setUsername(data);
			}else if("password".equals(tag)){
				router.setPassword(data);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(router!=null&&"router".equals(qName)){
			routers.add(router);
			router=null;
		}
		tag=null;
	}

}
