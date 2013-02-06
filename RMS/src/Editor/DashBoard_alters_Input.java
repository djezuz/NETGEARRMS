package Editor;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.entity.LoginUser;

public class DashBoard_alters_Input implements IEditorInput{

	private LoginUser loginUser;
	
	public DashBoard_alters_Input(){
		
	}
	
	public DashBoard_alters_Input(LoginUser user){
		
		this.loginUser=user;
	}
	
	
	

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Unresolved Alerts";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "";
	}
	public boolean equals(Object obj) {  
	    if(null == obj) return false;  
	              
	    if(!(obj instanceof DashBoard_alters_Input)) return false;  
	              
	    if(!getName().equals(((DashBoard_alters_Input)obj).getName())) return false;  
	              
	    return true;  
	  } 
}

