package com.wsclient;
public class LoginServer {
	//����webservice�û����������ж��Ƿ��¼�ɹ�
	public boolean login(String username, String pass_word) 
	   { 
	    
	    	  TrainingPortalUser mcws = new TrainingPortalUser();   
		      TrainingPortalUserSoap  mcwss = mcws.getTrainingPortalUserSoap();   
		      Users aos = mcwss.loginValidation(username, pass_word); 
		      if((aos.getUsername().equals("")||aos.getUsername()==null)&&(aos.getSite()==null||aos.getSite().equals(""))){
		    	  return false;
		      }
		      System.out.println(aos.siteAdmin);
	        return true; 
	     
	   } 

}
