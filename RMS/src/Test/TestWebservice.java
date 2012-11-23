package Test;

import com.wsclient.TrainingPortalUser;
import com.wsclient.TrainingPortalUserSoap;
import com.wsclient.Users;

 
  
public class TestWebservice {     
	    public static void main(String[] args) {   
	    	TrainingPortalUser mcws = new TrainingPortalUser();   
	    	TrainingPortalUserSoap  mcwss = mcws.getTrainingPortalUserSoap();   
	        Users aos = mcwss.loginValidation("Ye.Zhang@netgear.com", "Netgear-123");   
	        System.out.println(aos.isSiteAdmin());
	        System.out.println(aos.getSite());
	        System.out.println(aos.getUsername());
	  
	    }   
	}  


