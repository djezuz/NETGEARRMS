package Editor;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.entity.Router;

public class DashBoardInput implements IEditorInput{

	public List router;
	
	public DashBoardInput(List r) {
		// TODO Auto-generated constructor stub
		this.router = r;
	}

	public List getRouter() {
		return router;
	}

	public void setRouter(List router) {
		this.router = router;
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
		return "Main Page";
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
		              
		    if(!(obj instanceof DashBoardInput)) return false;  
		              
		    if(!getName().equals(((DashBoardInput)obj).getName())) return false;  
		              
		    return true;  
		  } 
}
