package Editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DashBoard_alters_Input implements IEditorInput{

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

