package com.wsclient;
public class LoginServer {
	
	private String username;
	//调用webservice用户名和密码判断是否登录成功
	public boolean login(String username, String pass_word) 
	{ 
	    
	    	  TrainingPortalUser mcws = new TrainingPortalUser();   
		      TrainingPortalUserSoap  mcwss = mcws.getTrainingPortalUserSoap();   
		      Users aos = mcwss.loginValidation(username, pass_word); 
		      if((aos.getUsername().equals("")||aos.getUsername()==null)&&(aos.getSite()==null||aos.getSite().equals(""))){
		    	  return false;
		      }else{
		    	  this.username=aos.getUsername();
		      }
		      System.out.println(aos.siteAdmin);
	        return true; 
	     
	}
	
	/**
	 * 获得登陆用户名
	 * @return
	 */
	public String getUername() {
		return username;
	}
	
	
	

}
